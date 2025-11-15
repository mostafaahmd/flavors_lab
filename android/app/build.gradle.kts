plugins { 
    id("com.android.application")
    id("kotlin-android")
    // The Flutter Gradle Plugin must be applied after the Android and Kotlin Gradle plugins.
    id("dev.flutter.flutter-gradle-plugin")
}

android {
    namespace = "com.example.flavors_lab"
    compileSdk = flutter.compileSdkVersion
    ndkVersion = flutter.ndkVersion

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    defaultConfig {
        applicationId = "com.example.flavors_lab"
        minSdk = flutter.minSdkVersion
        targetSdk = flutter.targetSdkVersion
        versionCode = flutter.versionCode
        versionName = flutter.versionName
    }

    // ✅ هنا بنعرّف إن عندنا dimension اسمها env
    flavorDimensions += "env"

    // ✅ هنا بنعرّف الفليفورز نفسهم: dev و prod
    productFlavors {
        create("dev") {
            dimension = "env"
            applicationId = "com.example.flavors_lab.dev"
            versionNameSuffix = "-dev"
            // اسم الأب اللى هيظهر فى لانشر الجهاز للـ dev
            resValue("string", "app_name", "Flavors Lab DEV")
        }
        create("prod") {
            dimension = "env"
            applicationId = "com.example.flavors_lab"
            // اسم الأب اللى هيظهر فى لانشر الجهاز للـ prod
            resValue("string", "app_name", "Flavors Lab")
        }
    }

    buildTypes {
        release {
            // لسه بنوقّع بـ debug key عادى عشان التجربة
            signingConfig = signingConfigs.getByName("debug")
        }
    }
}

flutter {
    source = "../.."
}
