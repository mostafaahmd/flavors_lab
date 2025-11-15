import 'package:flutter/material.dart';
import 'flavor_config.dart';
import 'main.dart' show MyApp;

void main() {
  // ده اللى هيشتغل فى ال dev
  FlavorConfig.dev();
  runApp(const MyApp());
}
