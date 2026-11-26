// Hand-written (not generated): shared client configuration for all SwimResults API targets.
import Foundation
import OpenAPIRuntime

/// Decodes the timestamps SwimResults services send. Go encodes `time.Time` as RFC 3339 with
/// optional fractional seconds: `2023-09-19T03:01:09.904Z` (Mongo, ms precision) and
/// `0001-01-01T00:00:00Z` (Go zero time = "not set"). Both are accepted; dates are encoded with
/// fractional seconds.
public struct SwimResultsDateTranscoder: DateTranscoder, @unchecked Sendable {
    private let lock = NSLock()
    private let fractional: ISO8601DateFormatter = {
        let f = ISO8601DateFormatter()
        f.formatOptions = [.withInternetDateTime, .withFractionalSeconds]
        return f
    }()
    private let plain: ISO8601DateFormatter = {
        let f = ISO8601DateFormatter()
        f.formatOptions = [.withInternetDateTime]
        return f
    }()

    public init() {}

    public func encode(_ date: Date) throws -> String {
        lock.lock(); defer { lock.unlock() }
        return fractional.string(from: date)
    }

    public func decode(_ string: String) throws -> Date {
        lock.lock(); defer { lock.unlock() }
        if let d = fractional.date(from: string) ?? plain.date(from: string) {
            return d
        }
        throw DecodingError.dataCorrupted(.init(codingPath: [], debugDescription: "Unsupported date: \(string)"))
    }
}

extension Configuration {
    /// Configuration to use for every SwimResults client:
    /// `Client(serverURL: try Servers.Server1.url(), configuration: .swimResults, transport: URLSessionTransport())`
    public static var swimResults: Configuration {
        Configuration(dateTranscoder: SwimResultsDateTranscoder())
    }
}

extension Date {
    /// Go's zero `time.Time` (`0001-01-01T00:00:00Z`), which the services use for "not set".
    public var isGoZeroTime: Bool { timeIntervalSince1970 <= -62_135_596_800 }
}
