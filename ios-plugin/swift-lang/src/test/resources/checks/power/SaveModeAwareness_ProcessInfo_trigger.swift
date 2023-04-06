import Foundation
import UIKit

final class AppDelegate: NSObject, UIApplicationDelegate {
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {

        // Should trigger
        let isLowPowerModeEnabled = ProcessInfo.processInfo.isLowPowerModeEnabled

        return true
    }
}