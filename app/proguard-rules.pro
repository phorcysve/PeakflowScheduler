# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Keep Firebase classes
-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }

# Keep MPAndroidChart classes
-keep class com.github.mikephil.charting.** { *; }

# Keep OkHttp classes
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }

# Keep Hilt classes
-keep class dagger.hilt.** { *; }
-keep class * extends dagger.hilt.android.internal.managers.ViewComponentManager { *; }

# Keep ViewModels
-keep class * extends androidx.lifecycle.ViewModel { *; }
-keep class * extends androidx.lifecycle.AndroidViewModel { *; }

# Keep data classes
-keepclassmembers class * {
    @androidx.annotation.Keep *;
}

# Keep Serializable classes
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Keep custom application class
-keep class com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it.PeakflowApp { *; }

# Keep UserData class
-keep class com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it.UserData { *; }

# Keep User class for Firebase
-keep class com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it.User { *; }

# Keep notification classes
-keep class com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it.NotificationHelper { *; }

# Keep analytics classes
-keep class com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it.AnalyticsHelper { *; }

# Keep service classes
-keep class com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it.AppCheckerService { *; }

# Keep adapter classes
-keep class com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it.ListViewAdapter** { *; }

# Keep constants
-keep class com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it.Constants { *; }

# Keep extensions
-keep class com.peakflow.you_must_not_cheat_yourself_your_parents_raised_a_soldier_prove_it.ExtensionsKt { *; }

# Remove logging in release
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
}

# Optimize
-optimizations !code/simplification/arithmetic,!code/simplification/cast,!field/*,!class/merging/*
-optimizationpasses 5
-allowaccessmodification

# Keep native methods
-keepclasseswithmembernames class * {
    native <methods>;
}

# Keep enum classes
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Keep Parcelable classes
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

# Keep R classes
-keep class **.R$* {
    public static <fields>;
}