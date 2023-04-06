import Foundation
import SwiftUIw
import CoreMotion

final class AppDelegate: NSObject, UIApplicationDelegate {
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {

        // Should not trigger
        UIApplication.shared.activityType = true

        return true
    }
}