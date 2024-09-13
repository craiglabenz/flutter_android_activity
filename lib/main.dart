import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

const platformMethodChannel = MethodChannel(
  'com.example.flutter_android_activity',
);

void main() {
  runApp(const MainApp());
}

class MainApp extends StatelessWidget {
  const MainApp({super.key});

  void _launchAndroidActivity() {
    platformMethodChannel.invokeMethod(
      'launchActivity',
      {'message': 'Hello from Flutter'},
    );
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: const Center(
          child: Text('Hello World!'),
        ),
        floatingActionButton: FloatingActionButton(
          onPressed: _launchAndroidActivity,
          tooltip: 'Launch Android activity',
          child: const Icon(Icons.launch),
        ),
      ),
    );
  }
}
