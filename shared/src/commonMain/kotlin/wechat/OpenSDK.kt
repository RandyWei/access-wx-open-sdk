package wechat

expect object OpenSDK {
    fun shareUrl(url:String,title:String,desc:String,thumb:ByteArray,scene:WXScene)
}