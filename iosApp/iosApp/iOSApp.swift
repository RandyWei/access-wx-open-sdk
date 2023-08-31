import UIKit
import shared

@main
class AppDelegate:UIResponder,UIApplicationDelegate{
    
    var window: UIWindow?
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        
        window = UIWindow()
        window?.rootViewController = Main_iosKt.MainViewController()
        window?.makeKeyAndVisible()
        
        OpenSDK.shared.registerApp(appId:"****", universalLink: "****",selfCheck:false)
        
        return true
    }
    
    func application(_ application: UIApplication, handleOpen url: URL) -> Bool {
        return OpenSDK.shared.handleOpenUrl(url:url)
    }
    
    func application(_ application: UIApplication, open url: URL, sourceApplication: String?, annotation: Any) -> Bool {
        return OpenSDK.shared.handleOpenUrl(url:url)
    }
    
    func application(_ application: UIApplication, continue userActivity: NSUserActivity, restorationHandler: @escaping ([UIUserActivityRestoring]?) -> Void) -> Bool {
        return OpenSDK.shared.handleOpenUniversalLink(userActivity:userActivity)
    }

}
