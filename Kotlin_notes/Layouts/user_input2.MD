# User input 2

## Change the app theme

A color can be represented as 3 hexadecimal numbers, #00-#FF (0-255),
represnting the red, green, and blue components of that color. The higher 
the number the more of that component there is. A color can also be
defined by including an alpha value which represents the transparency 
( #00-#FF). Examples include:

``` XML
<color name="black">#FF000000</color>
<color name="white">#FFFFFFFF</color>
```

A style can specify attributes for a `View` such as font color, font size,
background color and much more. A Theme is a collection of styles that are
applied to an entire app, activity or view hierarchy. When you apply a 
theme to an app, activity, view or view group, the theme is applied to that
element and all of its children. These can also be applied to non-view
elements such as the status bar and window background.

The theme sysem groups colors into 12 named attributes related to color
to be used. The theme doesn't need to specify all of them. These are:

| #  | Name | Theme Attribute |
| -- | ---- | --------------- |
| 1 | Primary | colorPrimary |
| 2 | Primary Variant | colorPrimaryVariant |
| 3 | Secondary | colorSecondary |
| 4 | Secondary Variant | colorSecondaryVariant |
| 5 | Background | colorBackground | 
| 6 | Surface | colorSurface | 
| 7 | Error | colorError |
| 8 | On Primary | colorOnPrimary |
| 9 | On Secondary | colorOnSecondary |
| 10 | On Background | colorOnBackground |
| 11 | On Surface | colorOnSurface |
| 12 | On Error | colorOnError |

Colors that are not defined will inherit from the color of the parent 
theme. 

The easiest way to pick colors is with the [color tool](https://m2.material.io/resources/color/#!/?view.left=0&view.right=0) which provides a pallete of 
predefined colors and lets you see how they look when used by different
UI elements.

From this you can select the primary and seconday colors for your app,
choosing whether this is supported by the a light or dark primary 
variant. 

You can also specify a dark them. They use a dark surface color with limited
color accents. It is recommend to use lighted tones (200-50) in a dark theme 
rather than the default color scheme (saturated tones from 900-500). In this
case we can use the light colors as gthe main colors and the primary colors
as the variant. This is done under the `themes.xml (night)` file. 

## Change the app icon

In the `mipmap` folder there are the launcher icons for square and round 
depending on the software that the device is running. The extras as `mdpi`, 
`hdpi` and so on that qualify the file depending on the screen density of 
the device. 

As of Android 8.0 (API level 26) there is support for adaptive launcher 
icons, which allow for more flexibility and interesting visual effects.
For developers that means the icon is made up of two layers:

- A foreground
- A background

## Create a more polished user experience

[Materical components](https://m3.material.io/components) are common UI
widgets that make it easier to implement material styling in your app. 
By using Material components the app will operate in a more consistent
way alongside other apps on the user's device. That way the UI patterns learned in
one app can be carried over to the next one. 

The Material Design Components (MDC) libaray needs to be included as a 
dependency in the project. This should be a line in the modules gradle
file:

```
dependencies {
    ...
    implementation 'com.google.android.material:material:<version>'
}
```

### Icons

Icons are symbols that can help users understand a user interface by visually
communicating the intended function. For Icons, instead of providing different
versions of bitmap images for different screen densities, the recommended 
practice is to use vector drawables. These are represented as XML files
that store the instruction on how to create an image rather than saving the 
actual pixels that make up that image. They can be scaled up or down without
any loss of visual quality or increase in file size. These can be:

- Filled
- Outlines
- Rounded
- Two-Tone
- Sharp

To create these you right click on `res`, click `new` and go to 
`vector` asset and make a clip-art. 

Support for Vector drawables was not added until Android 5.0 (API level 21). 
To make the app work on old versions of android add the `vectorDrawables` 
element to the app's `build.gradle` file:

```
android {
  defaultConfig {
    ...
    vectorDrawables.useSupportLibrary = true
   }
   ...
}
```

We can use `ImageViews` to display icons in the app. 

### Styles

A (style)[https://medium.com/androiddevelopers/android-styling-themes-vs-styles-ebe05f917578] 
is collection of view attributes values for a single type of widget. For example 
a `TextView` style can specify font color, font size and backrgound color. By 
extracting these attributes into a style you can easily apply the style 
to multiple views in the layout and maintain it in a single place. 

To create styles we can create a new file in the `res/values` folder called
`styles.XML`. 

When styling a component you should generally extend from a parent style of
the widget type you are using. This is important for two reasons:

- Makes sure all important default values are set on the component
- Style will continue to inherit any future changes to that parent style

The recommend naming convention for styles is substituting `MaterialComponents`
with you app's name (`TipTime`). This moves changes into its own namespace
and eliminates the possibility for future conflicts when Material Components
introduce new stykes e.g. `Widge.TipTime.TextView` inherits from parent 
style `Widget.MaterialComponents.TextView`. In the styles file this will look
like:

``` XML
<?xml version="1.0" encoding="utf-8"?>
<resources>

    <style name="Widget.TipTime.TextView" parent="Widget.MaterialComponents.TextView">
    </style>
    
</resources>
```

We can override attributes by stating them and then what they should be. For 
example:

``` XML
<?xml version="1.0" encoding="utf-8"?>
<resources>

    <style name="Widget.TipTime.TextView" parent="Widget.MaterialComponents.TextView">
        <item name="android:minHeight">48dp</item>
        <item name="android:gravity">center_vertical</item>
        <item name="android:textAppearance">?attr/textAppearanceBody1</item>
    </style>

</resources>
```

Which in this case `android:minHeight` sets a minimum height of 48dp on the
`TextView` which is the smallest height for any should according to the 
[Material Design guidelines](https://m3.material.io/components/lists/#specs).
Also `gravity` is used to centrer text and we set the text appearance according
to predefined styles. 

These styles can then be added to the `View` by adding a `style` input such as:

``` XML
<TextView
    android:id="@+id/service_question"
    style="@style/Widget.TipTime.TextView"
    ... />
```

Note: If you specify an attribute in a style (e.g. `android:textSize = 18sp`)
and also specify that same attribute in the layout file e.g. 
`android:textSize = 14sp`, the value set in the layout will actually
what is seen on screen. 

We can then set a similar style on switch but this has to be defined 
seperately e.g.:

``` XML
<style name="Widget.TipTime.CompoundButton.Switch" parent="Widget.MaterialComponents.CompoundButton.Switch">
   <item name="android:minHeight">48dp</item>
   <item name="android:gravity">center_vertical</item>
   <item name="android:textAppearance">?attr/textAppearanceBody1</item>
</style>

```

And then the same for the `RadioButton` widget:

``` XML
<style name="Widget.TipTime.CompoundButton.RadioButton"
parent="Widget.MaterialComponents.CompoundButton.RadioButton">
   <item name="android:paddingStart">8dp</item>
   <item name="android:textAppearance">?attr/textAppearanceBody1</item>
</style>
```

In addition to this you can also make a `dimens.XML` file in the same folder
to improve the manageability of frequently-used values e.g. the repeatadly used
`minHeight`:

``` XML dimens.XML
<resources>
   <dimen name="min_text_height">48dp</dimen>
</resources>
```

Where you would updte the `styles.XML` file to use the 
`@dimen/min_text_height` attribute as:

``` XML
...
<style name="Widget.TipTime.TextView" parent="Widget.MaterialComponents.TextView">
    <item name="android:minHeight">@dimen/min_text_height</item>
    <item name="android:gravity">center_vertical</item>
    <item name="android:textAppearance">?attr/textAppearanceBody1</item>
</style>
...
```

The styles for the `RadioButton` and `Switch` styles are not added to
the `Views` themselves but can be instead added to the themes file. This 
is because like colors you can also set `radioButtonStyle` and 
`switchStyle` theme attributes that can be set. The style resources provided
for these will be applied to every radio button and every switch button in the 
view hierarchy that the theme applies to. 

There is also a theme attribute for `textInputStyle` where the specified
style resource will be applied to all text input fields within the app. 

The `themes.xml` file can be edited to add:

``` XML

<item name="textInputStyle">@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox</item>
<item name="radioButtonStyle">@style/Widget.TipTime.CompoundButton.RadioButton</item>
<item name="switchStyle">@style/Widget.TipTime.CompoundButton.Switch</item>
```

which should be added to both the day and night time themes.


### Rotating the device

This will adjust the view of the device. To solve the bug add a `ScrollView` 
around the `ConstraintLayout` such that:

```
<ScrollView
   xmlns:android="http://schemas.android.com/apk/res/android"
   xmlns:app="http://schemas.android.com/apk/res-auto"
   xmlns:tools="http://schemas.android.com/tools"
   android:layout_height="match_parent"
   android:layout_width="match_parent">

   <androidx.constraintlayout.widget.ConstraintLayout
       android:layout_width="match_parent"
       android:layout_height="wrap_content"
       android:padding="16dp"
       tools:context=".MainActivity">

       ...
   </ConstraintLayout>

</ScrollView>
```

### Hide keyborad on Enter Key

We can set up a listener to close the onscreen keyboard when the enter key has 
has been hit. This can be implemented as:

``` Kotlin
private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
   if (keyCode == KeyEvent.KEYCODE_ENTER) {
       // Hide the keyboard
       val inputMethodManager =
           getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
       inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
       return true
   }
   return false
}
```

And:

``` Kotlin
override fun onCreate(savedInstanceState: Bundle?) {
   ...

   setContentView(binding.root)

   binding.calculateButton.setOnClickListener { calculateTip() }

   binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)
   }
}
```

### Adjust the tint of vector drawables

The color of vector drawables can be changed using the `android:tint`
property.

If there is a `tint` present then this will override the `fillColor` 
directive for the drawable.

## Instrumentation tests

These tests are created under `src/androidTest/Java/com.example.tiptime`
folder.

The first test is to write one for a 20% tip.

Instrumentation tests require an (InstrumentationTestRunner)[https://developer.android.com/reference/android/test/InstrumentationTestRunner?authuser=1]
which allows the test to execute on a device or emulator. There are several
that are available but for this we will use the `AndroidJUnit4` test 
runner. To specify the test runner we annottate the class with:

``` Kotlin
@RunWith(AndroidJUnit4::class)
class CalculatorTests {

}
```

The app consists of a single activity, `MainActivity`. In order to 
interact with the activity, your test case must first launch it. Thus,
this should be added inside the `CaclulatorTests` class:

``` Kotlin
@get:Rule() 
val activity = ActivityScenarioRule(MainActivity::class.java)
```

`ActivityScenarioRule` comes from the AndroidX test library. This tells
the device to launch an activity specified by the developer. `@get:Rule` 
says to launch this before every test in the class.

We then need to test the logic in the app itself. This course primarily uses
Espresso for unstrumentation tests which lets you interact with UI 
components through code. 

This takes the form:

``` Kotlin
@RunWith(AndroidJUnit4::class)
class CalculatorTests {
    // launch MainActivity activity
    // get:Rule says to launch beffore every test
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_18_percent_tip() {
        // onView function takes a ViewMatched object parameter
        // this is aUI component which matches a particular component
        // in this case match an ID
        // this returns a ViewInteraction which is an object we can
        // then interact with
        // in this case we type text
        // and close the keyboard
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("50.00"))
            .perform(ViewActions.closeSoftKeyboard())

        // we then find the calculate button
        // and click it
        onView(withId(R.id.calculate_button))
            .perform(click())


        // and check that the tip result is "$9.00"
        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("$9.00"))))

    }
}
```

To learn more go to (Espresso basics)[https://developer.android.com/training/testing/espresso/basics?authuser=1] page.


