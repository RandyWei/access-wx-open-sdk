import UIKit

@main
class AppDelegate:UIResponder,UIApplicationDelegate{
    
    var window: UIWindow?
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        
        window = UIWindow()
        window?.rootViewController = Main_iosKt.MainViewController()
        window?.makeKeyAndVisible()
        
        OpenSDK.shared.registerApp(appId:"wxdab815a00288fddd", universalLink: "https://applinks.shituzaixian.com/app/")
        
        return true
    }
}
