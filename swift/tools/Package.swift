// swift-tools-version:5.9
// Build-time tooling only (not part of the published package): pins the generator CLI.
import PackageDescription

let package = Package(
    name: "codegen-tools",
    platforms: [.macOS(.v13)],
    dependencies: [
        .package(url: "https://github.com/apple/swift-openapi-generator", exact: "1.14.4"),
    ],
    targets: []
)
