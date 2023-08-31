package com.chinahrt.app.shitu

import MainView
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import wechat.OpenSDK

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        OpenSDK.registerApp(this,"wxdab815a00288fddd")

        setContent {
            MainView()
        }
    }
}