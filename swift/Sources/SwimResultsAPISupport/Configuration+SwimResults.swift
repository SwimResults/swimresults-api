// Hand-written (not generated): shared client configuration for all SwimResults API targets.
import Foundation
import OpenAPIRuntime

/// Decodes the timestamps SwimResults services send. Go encodes `time.Time` as RFC 3339 with
/// optional fractional seconds: `2023-09-19T03:01:09.904Z` (Mongo, ms precision) and
/// `0001-01-01T00:00:00Z` (Go zero time = "not set"). Both are accepted; dates are encoded with
/// fractional seconds, in UTC unless an `encodingTimeZone` is given.
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

    /// - Parameter encodingTimeZone: offset to write into encoded timestamps (default UTC, `Z`).
    ///   Some routes read the time zone from the timestamp itself, e.g. start-service
    ///   `POST /heat/meet/{meet_id}/events/start_estimation_date` with `update_time_zone`.
    public init(encodingTimeZone: TimeZone? = nil) {
        if let encodingTimeZone {
            fractional.timeZone = encodingTimeZone
        }
    }

    public func encode(_ date: Date) throws -> String {
        // Foundation formats years before 1582 in the Julian calendar (Go's zero time would come out as
        // 0001-01-03), Go uses the proleptic Gregorian one: write the zero time literally.
        if date.isGoZeroTime { return "0001-01-01T00:00:00Z" }
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

    /// Like `.swimResults`, but request timestamps are written with the offset of `timeZone`
    /// (`2026-11-28T09:30:00.000+01:00`) instead of UTC.
    public static func swimResults(encodingIn timeZone: TimeZone) -> Configuration {
        Configuration(dateTranscoder: SwimResultsDateTranscoder(encodingTimeZone: timeZone))
    }
}

extension Date {
    /// Go's zero `time.Time` (`0001-01-01T00:00:00Z`), which the services use for "not set".
    public var isGoZeroTime: Bool { timeIntervalSince1970 <= -62_135_596_800 }

    /// Go's zero `time.Time`, to send "not set" where a request field is required. Equal to what
    /// `SwimResultsDateTranscoder` decodes from `0001-01-01T00:00:00Z` (Foundation reads year 1 as Julian).
    public static let goZeroTime = Date(timeIntervalSince1970: -62_135_769_600)

    /// `nil` for Go's zero time, so "not set" can be handled as an optional.
    public var nilIfGoZeroTime: Date? { isGoZeroTime ? nil : self }
}
