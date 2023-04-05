import Foundation
import UIKit

final class AppDelegate: NSObject, UIApplicationDelegate {
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {

        // Should trigger
        let batteryState = UIDevice.batteryStateDidChangeNotification
        NotificationCenter.default.addObserver(forName: batteryState, object: nil, queue: nil) { _ in }

        return true
    }
}