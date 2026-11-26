import Foundation
import HTTPTypes
import OpenAPIRuntime
import SwimResultsAPISupport
import SwimResultsMeetingAPI
import Testing

/// Returns a canned response for every request and records the request path.
private final class StubTransport: ClientTransport, @unchecked Sendable {
    let body: String
    var lastPath: String?
    var lastBaseURL: URL?
    init(body: String) { self.body = body }

    func send(_ request: HTTPRequest, body: HTTPBody?, baseURL: URL, operationID: String) async throws -> (HTTPResponse, HTTPBody?) {
        lastPath = request.path
        lastBaseURL = baseURL
        var response = HTTPResponse(status: .ok)
        response.headerFields[.contentType] = "application/json"
        return (response, HTTPBody(self.body))
    }
}

struct GeneratedClientTests {
    // Real payload shape from GET /meeting/v1/event/meet/IESC19, plus a Go zero time.
    private let events = """
    [{"_id":"65090ef59c01934b665a9bd3","number":1,"distance":100,"meeting":"IESC19","gender":"FEMALE",
      "style":{"_id":"64f0926ebdaab70007122eb5","name":"BUTTERFLY","aliases":["Schmetterling","FLY"]},
      "final":{},"part":{"number":1},"ordering":1,
      "added_at":"2023-09-19T03:01:09.904Z","updated_at":"0001-01-01T00:00:00Z"}]
    """

    @Test func decodesEventsWithGoTimestamps() async throws {
        let transport = StubTransport(body: events)
        let client = Client(serverURL: try Servers.Server1.url(), configuration: .swimResults, transport: transport)

        let output = try await client.getEventsByMeetId(path: .init(meetId: "IESC19"))
        let list = try output.ok.body.json ?? [] // Go sends null for an empty list

        #expect(transport.lastBaseURL?.absoluteString == "https://api.swimresults.de/meeting/v1")
        #expect(transport.lastPath == "/event/meet/IESC19")
        #expect(list.count == 1)
        #expect(list[0].number == 1)
        #expect(list[0].style.name == "BUTTERFLY")
        #expect(abs(list[0].addedAt.timeIntervalSince1970 - 1_695_092_469.904) < 0.001)
        #expect(list[0].updatedAt.isGoZeroTime)
    }
}
