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
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**
-keepclassmembers class kotlin.Metadata { *; }
-keepattributes *Annotation*
-keep @androidx.room.Entity class * { *; }
-keep @androidx.room.Dao interface * { *; }
-keep class * extends androidx.room.RoomDatabase { *; }
-keepclassmembers class * {
    @androidx.room.* <methods>;
    @androidx.room.* <fields>;
}
-keep class androidx.lifecycle.** { *; }
-dontwarn androidx.lifecycle.**
-dontwarn kotlinx.coroutines.**
-keepclassmembers class **.R$drawable {
    <fields>;
}
#FIREBASE
-keep class com.google.firebase.** { *; }
-dontwarn com.google.firebase.**
-keep class com.the.fitmate.data.InsertExersize { *; }
-keep class com.the.fitmate.data.UsersData { *; }
-keep class com.google.gson.** { *; }
-dontwarn com.google.gson.**
