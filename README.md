# 📱 Flavors Lab

[![Flutter](https://img.shields.io/badge/Flutter-3.x-blue.svg)]()
[![Platform](https://img.shields.io/badge/Platform-Android%20%7C%20iOS-lightgrey.svg)]()
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

> A production-ready Flutter multi-environment architecture using **Flutter Flavors** for Dev & Production.

---

## 🚀 1. Overview

This repository provides a **production-grade, multi-environment Flutter setup** using flavors with clean separation between development and production builds.

### 🔑 Key Highlights
- One codebase → multiple deployable apps (**Dev / Prod**)
- Separate **App IDs, config, API URLs, icons**
- CI/CD-friendly structure (GitHub Actions, Codemagic, Bitrise…)
- Centralized runtime config (`FlavorConfig`)
- Zero manual editing before each build

---

## 🧱 2. Architecture & Environments

### 2.1 Flavor Strategy

The project defines two primary environments:

| Flavor | Usage | Visibility |
|--------|--------|-------------|
| **dev** | Local development, debugging | Not published |
| **prod** | Live production app | Store-ready |

Each flavor includes:
- Unique **application ID / bundle ID**
- Unique **app name** (+ optional icon)
- Distinct **base URL**
- Independent build output
- Flavor-specific release configuration

---

### 2.2 Flavor Configuration in Dart

```dart
enum Flavor { dev, prod }

class FlavorConfig {
  FlavorConfig._({
    required this.flavor,
    required this.appName,
    required this.baseUrl,
    required this.primaryColor,
    this.enableLogging = false,
  });

  final Flavor flavor;
  final String appName;
  final String baseUrl;
  final Color primaryColor;
  final bool enableLogging;

  static late FlavorConfig instance;

  static void dev() {
    instance = FlavorConfig._(
      flavor: Flavor.dev,
      appName: 'Flavors Lab (Dev)',
      baseUrl: 'https://dev.api.flavors-lab.com',
      primaryColor: Colors.green,
      enableLogging: true,
    );
  }

  static void prod() {
    instance = FlavorConfig._(
      flavor: Flavor.prod,
      appName: 'Flavors Lab',
      baseUrl: 'https://api.flavors-lab.com',
      primaryColor: Colors.blue,
      enableLogging: false,
    );
  }
}

🤖 3. Android Flavor Configuration

android {
    namespace = "com.example.flavors_lab"
    compileSdk = flutter.compileSdkVersion
    ndkVersion = flutter.ndkVersion

    defaultConfig {
        applicationId = "com.example.flavors_lab"
        minSdk = flutter.minSdkVersion
        targetSdk = flutter.targetSdkVersion
        versionCode = flutter.versionCode
        versionName = flutter.versionName
    }

    flavorDimensions += "env"

    productFlavors {
        create("dev") {
            dimension = "env"
            applicationId = "com.example.flavors_lab.dev"
            versionNameSuffix = "-dev"
            resValue("string", "app_name", "Flavors Lab (Dev)")
        }
        create("prod") {
            dimension = "env"
            applicationId = "com.example.flavors_lab"
            resValue("string", "app_name", "Flavors Lab")
        }
    }

    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

▶️ 4. Running the App
4.1 Requirements

Flutter 3.x

Android Studio / VS Code

Emulator or device

4.2 Install dependencies

flutter pub get

4.3 Run per flavor
Dev

flutter run --flavor dev -t lib/main_dev.dart

Production

flutter run --flavor prod -t lib/main.dart

📦 5. Build & Release
5.1 Build APKs

Dev (Internal testing)

flutter build apk --flavor dev -t lib/main_dev.dart

Production

flutter build apk --flavor prod -t lib/main.dart

