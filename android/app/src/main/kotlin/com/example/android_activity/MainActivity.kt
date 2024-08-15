package com.example.android_activity

import android.content.Intent
import androidx.activity.ComponentActivity
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity: FlutterActivity() {
    private val CHANNEL = "nativeChannel"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
            call: MethodCall, result: MethodChannel.Result ->
                when (call.method) {
                    "launchActivity" -> {
                        try{
                            val intent = Intent(this@MainActivity, SecondActivity::class.java)
                            startActivity(intent)
                        } catch (e: Exception){}
                            result.success(true)
                        }
                        else -> {}
                }
        }
    }
}

class SecondActivity : ComponentActivity()