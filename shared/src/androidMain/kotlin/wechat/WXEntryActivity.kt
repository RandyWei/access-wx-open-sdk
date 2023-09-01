package wechat

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.tencent.mm.opensdk.modelbase.BaseReq
import com.tencent.mm.opensdk.modelbase.BaseResp
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler

class WXEntryActivity : Activity(), IWXAPIEventHandler {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        OpenSDK.api.handleIntent(intent, this)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
        OpenSDK.api.handleIntent(intent, this)

    }

    override fun onReq(p0: BaseReq?) {

    }

    override fun onResp(resp: BaseResp?) {
        println("===========")
        println(resp?.errCode)
        println(resp?.errStr)
        println("===========")
        //分享后出现白屏，这里需要关掉这个activity
        finish()
    }
}