import Foundation
import SwiftUI
import AVFoundation

final class AppDelegate: NSObject, UIApplicationDelegate {
    func application(
        _ application: UIApplication,
        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]? = nil
    ) -> Bool {

        let device = AVCaptureDevice.default(for: AVMediaType.video)
        device.torchMode = .on
   
        return true
    }
}