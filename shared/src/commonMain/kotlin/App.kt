import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.resource
import wechat.OpenSDK
import wechat.WXScene

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun App() {
    val scope = rememberCoroutineScope()
    MaterialTheme {
        Scaffold(topBar = { LargeTopAppBar(title = { Text("微信SDK Example") }) }) {
            Column(modifier = Modifier.padding(it)) {

                Button(onClick = {
                    scope.launch {

                        val thumb = resource("demo@4x.png").readBytes()

                        OpenSDK.shareUrl(
                            "https://www.baidu.com",
                            "百度一下",
                            "谷歌一下",
                            thumb,
                            WXScene.Session
                        )

                    }

                }) { Text("分享百度") }
            }
        }
    }
}

expect fun getPlatformName(): String