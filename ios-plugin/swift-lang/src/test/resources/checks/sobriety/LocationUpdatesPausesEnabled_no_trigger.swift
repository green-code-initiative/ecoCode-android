import Foundation
import SwiftUI

final class AppDelegate: NSObject, UIApplicationDelegate {
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {

        // Should not trigger
        let manager = CLLocationManager()
        manager.pausesLocationUpdatesAutomatically = true;

        return true
    }
}