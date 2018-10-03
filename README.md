# Lgi-android

Log library and utils

* [goto jitpack.io](https://jitpack.io/)

### How to install

#### **Step 1.** Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

```groove
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

#### **Step 2.** Add the dependency

```groove
implementation 'com.github.torindev:Lgiandroid:0.1.02'
```

**That's it.**

### How to use

```java
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // set logging only on debug mode
        Lgi.sLog = BuildConfig.DEBUG;
    }
}
```
