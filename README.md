# LoadingView Kotlin Library

https://github.com/parniyan7/DotLoading/assets/51333944/fe28bdb7-a5c1-47f4-a5f1-8f81a3f85264

LoadingView is a customizable view for displaying a loading animation consisting of multiple dots. This library provides a simple and easy-to-use interface for creating and controlling a loading animation, and allows customization of the dot count, size, spacing, and colors.

## UsageTo use LoadingView in your project, add the following dependency to your module's `build.gradle` file:

```groovy
implementation 'com.github.parniyan7:DotLoading:4.0.0'
```

In your layout file, add the `LoadingView` view:

```xml
<com.parniyan.parniyandotloading.LoadingView
    android:id="@+id/loadingView"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:dotCount="5"
    app:dotRadius="8dp"
    app:dotSpacing="100dp"
    app:dotMaxSize="32dp"
    app:dotMinSize="6dp"
    app:dotColors="@array/loading_dot_colors" />
```

You can customize the appearance of the `LoadingView` by setting the following attributes:
- `dotCount`: The number of dots in the loading animation (default 5).
- `dotRadius`: The radius of each dot in the loading animation (default 8dp).
- `dotSpacing`: The spacing between each dot in the loading animation (default 100dp).
- `dotMaxSize`: The maximum size of each dot in the loading animation (default 32dp).
- `dotMinSize`: The minimum size of each dot in the loading animation (default 6dp).
- `dotColors`: An array of colors to use for each dot in the loading animation (default `[#9984D4, #9984D4, #592E83, #592E83, #592E83]`).

In your code, you can start and stop the loading animation by calling the `startLoading()` and `stopLoading()` methods, respectively:

```kotlin
val loadingView = findViewById<LoadingView>(R.id.loadingView)

// Start the loading animation
loadingView.startLoading()

// Stop the loading animation
loadingView.stopLoading()
```

You can also customize the minimum size of each dot by calling the `setDotMinSize()` method:

```kotlin
loadingView.setDotMinSize(6f)
```

[![ ↗](https://jitpack.io/v/parniyan7/DotLoading.svg)]([https://jitpack.io/#parniyan7/DotLoading) ↗](https://jitpack.io/#parniyan7/DotLoading))
