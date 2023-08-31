package wechat

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.tencent.mm.opensdk.constants.ConstantsAPI
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject
import com.tencent.mm.opensdk.openapi.IWXAPI
import com.tencent.mm.opensdk.openapi.WXAPIFactory

actual object OpenSDK {

    internal lateinit var api: IWXAPI
    fun registerApp(context: Context, appId: String) {
        api = WXAPIFactory.createWXAPI(context, appId)
        api.registerApp(appId)

        context.registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, p1: Intent?) {
                api.registerApp(appId)
            }
        }, IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP))
    }

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
        msg.description = desc
        msg.thumbData = thumb
        msg.mediaObject = webpage

        val req = SendMessageToWX.Req()
        req.message = msg
        req.scene = scene.ordinal

        api.sendReq(req)
    }
}