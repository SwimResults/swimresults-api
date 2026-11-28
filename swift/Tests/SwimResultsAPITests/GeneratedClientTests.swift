import Foundation
import HTTPTypes
import OpenAPIRuntime
import SwimResultsAPISupport
import SwimResultsMeetingAPI
import SwimResultsStartAPI
import Testing

/// Returns a canned response for every request and records the request path.
private final class StubTransport: ClientTransport, @unchecked Sendable {
    let body: String
    let status: HTTPResponse.Status
    var lastPath: String?
    var lastBaseURL: URL?
    var lastAuthorization: String?
    init(body: String, status: HTTPResponse.Status = .ok) { self.body = body; self.status = status }

    func send(_ request: HTTPRequest, body: HTTPBody?, baseURL: URL, operationID: String) async throws -> (HTTPResponse, HTTPBody?) {
        lastPath = request.path
        lastBaseURL = baseURL
        lastAuthorization = request.headerFields[.authorization]
        var response = HTTPResponse(status: status)
        response.headerFields[.contentType] = "application/json" // Gin sets it even for 204
        return (response, self.body.isEmpty ? nil : HTTPBody(self.body))
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
        let client = SwimResultsMeetingAPI.Client(serverURL: try SwimResultsMeetingAPI.Servers.Server1.url(), configuration: .swimResults, transport: transport)

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

    @Test func acceptsEmptyNoContentResponse() async throws {
        let transport = StubTransport(body: "", status: .noContent)
        let client = SwimResultsStartAPI.Client(serverURL: try SwimResultsStartAPI.Servers.Server1.url(), configuration: .swimResults, transport: transport)

        let output = try await client.deleteHeatsByMeetingAndEvent(path: .init(meetId: "IESC19", eventId: 1))

        _ = try output.noContent
        #expect(transport.lastPath == "/heat/meet/IESC19/event/1")
    }

    @Test func encodesDatesInRequestedTimeZone() throws {
        let date = Date(timeIntervalSince1970: 1_795_854_600) // 2026-11-28T08:30:00Z
        #expect(try SwimResultsDateTranscoder().encode(date) == "2026-11-28T08:30:00.000Z")
        let berlin = SwimResultsDateTranscoder(encodingTimeZone: TimeZone(secondsFromGMT: 3600)!)
        #expect(try berlin.encode(date) == "2026-11-28T09:30:00.000+01:00")
        #expect(try berlin.decode("2026-11-28T09:30:00.000+01:00") == date)
        #expect(Date.goZeroTime.isGoZeroTime)
    }

    @Test func sendsBearerTokenAndUsesServerURL() async throws {
        let transport = StubTransport(body: "", status: .noContent)
        let client = SwimResultsStartAPI.Client(
            serverURL: SwimResultsServer.development.url(for: "start"), configuration: .swimResults, transport: transport,
            middlewares: [BearerTokenMiddleware { "abc" }])

        _ = try await client.deleteStartsByMeetingAndEvent(path: .init(meetId: "IESC19", eventId: 2)).noContent

        #expect(transport.lastBaseURL?.absoluteString == "https://api-dev.swimresults.de/start/v1")
        #expect(transport.lastAuthorization == "Bearer abc")
        #expect(Date.goZeroTime.nilIfGoZeroTime == nil)
    }
}
