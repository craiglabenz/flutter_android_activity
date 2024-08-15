package com.example.android_activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

import androidx.compose.material3.Text
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant
import com.example.android_activity.ui.theme.AppTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material.*


class MainActivity: FlutterActivity() {
    private val CHANNEL = "com.example.android_activity"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        //super.configureFlutterEngine(flutterEngine)

        GeneratedPluginRegistrant.registerWith(flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler {
            call: MethodCall, result: MethodChannel.Result ->
                when (call.method) {
                    "launchActivity" -> {
                        try{
                            val _counter = call.arguments  // Takes an object, in this case it's int
                            val intent = Intent(this@MainActivity, SecondActivity::class.java)
                            intent.putExtra("_counter", _counter.toString());
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
                            Text("" + getIntent()?.getExtras()?.getString("_counter"))
                            Text("Moar Stuff")
                        }
                    }
        }
    }
}