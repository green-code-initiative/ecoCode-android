import Foundation
import SwiftUI
import CLLocationManager

final class AppDelegate: NSObject, UIApplicationDelegate {
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {

        // Should not trigger
        CLLocationManager.CLActivityType
        return true
    }
}
