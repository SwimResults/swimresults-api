// Hand-written (not generated): building blocks every SwimResults app needs around the clients.
import Foundation
import HTTPTypes
import OpenAPIRuntime

/// Base URLs of the SwimResults services behind the outer proxy: `https://api.swimresults.de/<service>/v1`.
public enum SwimResultsServer {
    case production
    case development

    public var host: String {
        switch self {
        case .production: "https://api.swimresults.de"
        case .development: "https://api-dev.swimresults.de"
        }
    }

    /// e.g. `SwimResultsServer.production.url(for: "meeting")` → `https://api.swimresults.de/meeting/v1`
    public func url(for service: String) -> URL {
        URL(string: "\(host)/\(service)/v1")!
    }
}

/// Sets `Authorization: Bearer <token>` on every request. `token` is asked for each request, so it can
/// return a freshly refreshed access token (e.g. AppAuth's `performAction`).
public struct BearerTokenMiddleware: ClientMiddleware {
    private let token: @Sendable () async throws -> String

    public init(token: @escaping @Sendable () async throws -> String) {
        self.token = token
    }

    public func intercept(
        _ request: HTTPRequest, body: HTTPBody?, baseURL: URL, operationID: String,
        next: @Sendable (HTTPRequest, HTTPBody?, URL) async throws -> (HTTPResponse, HTTPBody?)
    ) async throws -> (HTTPResponse, HTTPBody?) {
        var request = request
        request.headerFields[.authorization] = "Bearer \(try await token())"
        return try await next(request, body, baseURL)
    }
}
