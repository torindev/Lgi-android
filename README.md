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
implementation 'com.github.torindev:Lgiandroid:1.0.1'
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

### Методы LgiUtils

```java
static boolean isOnline(Context context)
```

Возвращает true, если есть доступ в инет (2g, 3g,  4g и тд или wifi)

***

```java
static boolean isWifiNetworkAvailable(Context context)
```

Возвращает true, если включен wifi на устройстве. Не проверяет наличие доступа в инет.

***

```java
static void showKeyboard(EditText editText)
```
Показывает клавиатуру для переданного EditText

***

```java
static boolean hideKeyboard(@NonNull Activity activity)
```

Прячет клавиатуру

***

```java
static void addFilterAllCapsToEditText(EditText editText)
```

Добавляет фильт к переданному EditText, который заставляет последнего использовать только верхний регистр букв. При добавлении фильтра, прошлые фильтры не стираются.

***

```java
static int dpToPx(@NonNull final Context context, final float dp)
```

Конвертирует переданные dp в пиксели относительно текущей плотности.

***

```java
static int screenHeight(@NonNull final Activity context)
```

Возвращает видимую высоту экрана. Если используются наэкранные кнопки, высота будет относительно свободного простарнтсва.

***

```java
static int screenWidth(@NonNull final Activity context)
```

Ширина экрана

***

```java
static double getInches(Activity activity)
```

Возвращает диагональ в дюймах, значение примерное.

***

```java
static Point locationOnScreen(@NonNull final View view)
```
Возвращает координаты переданного View На экране

***

```java





