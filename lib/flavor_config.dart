/*
الـ FlavorConfig هي كلاس يحتوي على كل البيانات الخاصة بالـ flavor
ده اللي هيخلي:

الـ UI تعرف إحنا dev ولا prod

تغير اللون والـ baseUrl حسب الـ flavor

ويجيبنا الـ flavor من الـ main.dart 

*/

import 'package:flutter/material.dart';

enum Flavor { dev, prod }

class FlavorConfig {
  FlavorConfig._({
    required this.flavor,
    required this.appName,
    required this.baseUrl,
    required this.primaryColor,
  });

  final Flavor flavor;
  final String appName;
  final String baseUrl;
  final Color primaryColor;

  static late FlavorConfig instance;

  static void dev() {
    instance = FlavorConfig._(
      flavor: Flavor.dev,
      appName: 'Flavors Lab DEV',
      baseUrl: 'https://dev.api.com',
      primaryColor: Colors.green,
    );
  }

  static void prod() {
    instance = FlavorConfig._(
      flavor: Flavor.prod,
      appName: 'Flavors Lab',
      baseUrl: 'https://api.com',
      primaryColor: Colors.blue,
    );
  }
}
