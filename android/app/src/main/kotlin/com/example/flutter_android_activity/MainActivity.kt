package com.example.flutter_android_activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity: FlutterActivity() {
    private val CHANNEL = "com.example.flutter_android_activity"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
            call: MethodCall, result: MethodChannel.Result ->
                when (call.method) {
                    "launchActivity" -> {
                        try{
                            val message = call.arguments  // Takes an object, in this case it's a String
                            val intent = Intent(this@MainActivity, SecondActivity::class.java)
                            intent.putExtra("message", message.toString())
                            startActivity(intent)
                        } catch (e: Exception){}
                            result.success(true)
                        }
                        else -> {}
                }
        }
    }
}

class SecondActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
                    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                        Column {
                            Text(text = "Second Activity")
                            Text("" + getIntent()?.getExtras()?.getString("message"))
                            Button(onClick = {  finish() }) {
                                Text("Exit")
                            }
                        }
                    }
        }
    }
}