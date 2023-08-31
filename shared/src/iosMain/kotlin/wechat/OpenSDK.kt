package wechat

import cocoapods.WechatOpenSDK_XCFramework.SendMessageToWXReq
import cocoapods.WechatOpenSDK_XCFramework.WXApi
import cocoapods.WechatOpenSDK_XCFramework.WXLogLevelDetail
import cocoapods.WechatOpenSDK_XCFramework.WXMediaMessage
import cocoapods.WechatOpenSDK_XCFramework.WXWebpageObject
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.allocArrayOf
import kotlinx.cinterop.memScoped
import platform.Foundation.NSData
import platform.Foundation.create

actual object OpenSDK {

    fun registerApp(appId: String, universalLink: String, selfCheck: Boolean? = false) {
        if (selfCheck == true) {
            WXApi.startLogByLevel(WXLogLevelDetail) {
                println("WechatSDK:$it")
            }
        }
        WXApi.registerApp(appId, universalLink)
        if (selfCheck == true) {
            WXApi.checkUniversalLinkReady { step, result ->
                println("step:$step,result.success:${result?.success},result.errorInfo:${result?.errorInfo},result.suggestion:${result?.suggestion}")
            }
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    actual fun shareUrl(
        url: String,
        title: String,
        desc: String,
        thumb: ByteArray,
        scene: WXScene
    ) {

        val webpage = WXWebpageObject()
        webpage.webpageUrl = url

        val msg = WXMediaMessage()
        msg.title = title
        msg.setDescription(desc)
        //通过ByteArray创建一个NSData
        //memScoped是为了在这个区域使用完内存后自动释放
        msg.thumbData = memScoped {
            NSData.create(bytes = allocArrayOf(thumb), length = thumb.size.toULong())
        }
        msg.mediaObject = webpage

        val req = SendMessageToWXReq()
        req.message = msg
        req.scene = scene.ordinal

        WXApi.sendReq(req) {}

    }
}