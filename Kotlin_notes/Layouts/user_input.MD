# User input

## Classes and inheritance in Kotlin

- <b>Class hierarchy</b> - An arrangement where classes are organised in a
hierarchy of parents and children.
- <b>Child or subclass</b> - Any class that is below another in the 
hierarchy
- <b>PArent or superclass or base class </b> - Any class with 
one or more child classes
- <b> Root or top-level class </b> - The class at the top (or root) of the
class hierarchy
- <b> Inheritance </b> when a child class includes (or inherits) all of 
the properties and methods of its parent class. This allows you to share
and reuse code, which makes programs easier to understand and maintain.

An example of inheritance in Android is:

- The `View` is a rectangualr area on the screen that is responsible 
for drawing and event handling
- The `TextView` class is a subclass of the `View` class
- Further subclasses of `TextView` include `EditText` and `Button`

At the top of a documentation page for an Android class on the 
developer.andoird.com website, you can see the class hierarchy diagram.

### Superclass

Any class can be the base class of a class hierarchy or a parent of other
classes. An "Abstract" class is a class that cannot be instantiated because 
it is not fully implemented. This can be used to create a blueprint (class)
from which you can build the actual object instance. 

A common benefit of creating a superclass is to contain properties and 
functions that are common to all its subclasses. IF the values of 
propeties and implementations of functions are not known, then the class
can be made abstract e.g. a vegetable. 

The decleration of an abstract class starts with the `abstract` keyword 
such as:

``` Kotlin
abstract class Dwelling() {

}
```

You can then add attributes which have to be declared as `abstract` if they 
are not initialised, such as `buildingMaterial`:

``` Kotlin
abstract class Dwelling(){
    
    //declared as a val because building material won't change
    abstract val buildingMaterial: String
    
}
```

We can also add:

- A capacity property
- A number of residents that can  be passed as a parameter (`var` 
as it can change)
- A function to determine if the dwelling has room

such that:

``` Kotlin
abstract class Dwelling(private var residents: Int) {
   
   abstract val buildingMaterial: String
   abstract val capacity: Int
    
   fun hasRoom(): Boolean {
       return residents < capacity
   }
}
```

`residents` is marked as `private` which is a visibility modifier in Kotlin
meaning that `residents` property is only visible to (and can be used inside)
this class. It cannot be accessed from anywhere else in the programme. 

### Subclass

Below the `Dwelling` class that has created we can create a subclass
called `SquareCabin`. This has to be declared along with stating that it
inherits from the superclass like:

``` Kotlin
class SquareCabin(residents: Int) : Dwelling(residents)
```

Here, we use the `residents` as the property input from the `Dwelling` which
can then be declared when instantiating a new `SquareCabin` object. 

We need to add to this because we declared `abstract` values in the superclass
which need to be `instantiated` in the new class we create, or also declared 
abstract. For this we need to `override` the previous properties:

``` Kotlin
class SquareCabin(residents: Int) : Dwelling(residents) {
    override val buildingMaterial = "Wood"
    override val capacity = 6
}
```

We can thus access properties from a class using `dot` notation. To simplify
this however, we can use the `with` statement to access multiple properties 
together to "do all the following operations with this instance object".
e.g.:

``` Kotlin
with(squareCabin) {
    println("\nSquare Cabin\n============")
    println("Capacity: ${capacity}")
    println("Material: ${buildingMaterial}")
    println("Has room? ${hasRoom()}")
}
```

By default in Kotlin classes are final and cannot be subclassed or 
inherited from. You can only inherent from `abstract` classes or those
that are marked with the `open` keyword. i.e.:

``` Kotlin
open class RoundHut(residents: Int) : Dwelling(residents) {
   override val buildingMaterial = "Straw"
   override val capacity = 4
}
```

The finished code looks like:


``` Kotlin
fun main() {
    
    val squareCabin = SquareCabin(6)
    val roundHut = RoundHut(3)
    val roundTower = RoundTower(4)

    with(squareCabin) {
        println("\nSquare Cabin\n============")
        println("Capacity: ${capacity}")
        println("Material: ${buildingMaterial}")
        println("Has room? ${hasRoom()}")
    }
       
    with(roundHut) {
        println("\nRound Hut\n=========")
        println("Material: ${buildingMaterial}")
        println("Capacity: ${capacity}")
        println("Has room? ${hasRoom()}")
    }
       
    with(roundTower) {
        println("\nRound Tower\n==========")
        println("Material: ${buildingMaterial}")
        println("Capacity: ${capacity}")
        println("Has room? ${hasRoom()}")
    }
}


abstract class Dwelling(private var residents: Int) {
    abstract val buildingMaterial: String
    abstract val capacity: Int
       
    fun hasRoom(): Boolean {
       return residents < capacity
   }
}


class SquareCabin(residents: Int) : Dwelling(residents) {
    override val buildingMaterial = "Wood"
    override val capacity = 6
}


open class RoundHut(residents: Int) : Dwelling(residents) {
    override val buildingMaterial = "Straw"
    override val capacity = 4
}


class RoundTower(
    residents: Int,
    val floors: Int = 2) : RoundHut(residents) {

    override val buildingMaterial = "Stone"
    override val capacity = 4 * floors
}
```

We can then also modify functions in the hierarcy as:

``` Kotlin
/**
* Program that implements classes for different kinds of dwellings.
* Shows how to:
* Create class hierarchy, variables and functions with inheritance,
* abstract class, overriding, and private vs. public variables.
*/

import kotlin.math.PI
import kotlin.math.sqrt

fun main() {
   val squareCabin = SquareCabin(6, 50.0)
   val roundHut = RoundHut(3, 10.0)
   val roundTower = RoundTower(4, 15.5)

   with(squareCabin) {
       println("\nSquare Cabin\n============")
       println("Capacity: ${capacity}")
       println("Material: ${buildingMaterial}")
       println("Floor area: ${floorArea()}")
   }

   with(roundHut) {
       println("\nRound Hut\n=========")
       println("Material: ${buildingMaterial}")
       println("Capacity: ${capacity}")
       println("Floor area: ${floorArea()}")
       println("Has room? ${hasRoom()}")
       getRoom()
       println("Has room? ${hasRoom()}")
       getRoom()
       println("Carpet size: ${calculateMaxCarpetLength()}")
   }

   with(roundTower) {
       println("\nRound Tower\n==========")
       println("Material: ${buildingMaterial}")
       println("Capacity: ${capacity}")
       println("Floor area: ${floorArea()}")
       println("Carpet Length: ${calculateMaxCarpetLength()}")
   }
}


/**
* Defines properties common to all dwellings.
* All dwellings have floorspace,
* but its calculation is specific to the subclass.
* Checking and getting a room are implemented here
* because they are the same for all Dwelling subclasses.
*
* @param residents Current number of residents
*/
abstract class Dwelling(private var residents: Int) {
   abstract val buildingMaterial: String
   abstract val capacity: Int

   /**
    * Calculates the floor area of the dwelling.
    * Implemented by subclasses where shape is determined.
    *
    * @return floor area
    */
   abstract fun floorArea(): Double

   /**
    * Checks whether there is room for another resident.
    *
    * @return true if room available, false otherwise
    */
   fun hasRoom(): Boolean {
       return residents < capacity
   }

   /**
    * Compares the capacity to the number of residents and
    * if capacity is larger than number of residents,
    * add resident by increasing the number of residents.
    * Print the result.
    */
   fun getRoom() {
       if (capacity > residents) {
           residents++
           println("You got a room!")
       } else {
           println("Sorry, at capacity and no rooms left.")
       }
   }

   }

/**
* A square cabin dwelling.
*
*  @param residents Current number of residents
*  @param length Length
*/
class SquareCabin(residents: Int, val length: Double) : Dwelling(residents) {
   override val buildingMaterial = "Wood"
   override val capacity = 6

   /**
    * Calculates floor area for a square dwelling.
    *
    * @return floor area
    */
   override fun floorArea(): Double {
       return length * length
   }

}

/**
* Dwelling with a circular floorspace
*
* @param residents Current number of residents
* @param radius Radius
*/
open class RoundHut(
       residents: Int, val radius: Double) : Dwelling(residents) {

   override val buildingMaterial = "Straw"
   override val capacity = 4

   /**
    * Calculates floor area for a round dwelling.
    *
    * @return floor area
    */
   override fun floorArea(): Double {
       return PI * radius * radius
   }

   /**
    *  Calculates the max length for a square carpet
    *  that fits the circular floor.
    *
    * @return length of square carpet
    */
    fun calculateMaxCarpetLength(): Double {
        return sqrt(2.0) * radius
    }



}

/**
* Round tower with multiple stories.
*
* @param residents Current number of residents
* @param radius Radius
* @param floors Number of stories
*/
class RoundTower(
       residents: Int,
       radius: Double,
       val floors: Int = 2) : RoundHut(residents, radius) {

   override val buildingMaterial = "Stone"

   // Capacity depends on the number of floors.
   override val capacity = floors * 4

   /**
    * Calculates the total floor area for a tower dwelling
    * with multiple stories.
    *
    * @return floor area
    */
   override fun floorArea(): Double {
       return super.floorArea() * floors
   }
}
```

Where the `super` keyword is used to reference functions and 
properties in the parent class e.g. the `RoundTower.floorArea`. 

## Xml layouts

UI elements that are provided by Andoird include:

- `EditText` - for entering and editing text
- `TextView` - to display text like the service question and tip amount
- `RadioButton` -  aselectable radio button for each tip option
- `RadioGroup` - to group the radio button options
- `Switch` - an on/off toggle for choosing whether to round up the tip or 
not

An XML document contains a hierarchy of UI elements on a screen for which
`ViewGroups` can contain more `ViewGroups` and so on. For example a
`ConstraintLayout` can contain `Buttons`, `TextView`, `ImageViews` or other
views. 

Each elemt starts and ends with a tag and each tag starts with a `<` and ends
with a `>`. This may look something like:

```
<ConstraintLayout>
    <TextView
        text="Hello World!"/>
</ConstraintLayout>
```

In this example the `ConstraintLayout` is written with sperate start and 
end tags because it needs to be able to hold other elements inside it.

Elements inside can represent:

- `xmlns` - XML namespace and each line defines a schema or vocabularly for 
attributes related to those words
- The `android:` namespace marks attributes that are defined by the andoird 
system

Padding is similar to margins but it adds space to the inside of the
`ConstraintLayout` instead of adding space to the outside.

`android:id`s should be written with words seperated with `_` and all 
start with lower case words.

If you set `android:layout_width="0dp"` it will try to match the width
of the constraints that are set on that `View`.

You can specify `android:inputType` to constraint the type of text that 
a user can input. 

You can make a list of exclusive options with `RadioButtons` grouped with
a `RadioGroup`. This can be vertical or horizontal and you can specify which
`RadioButton` should be selected initially.

A `Switch` can be used to let the user toggle between two options. You can add
a label to a `Switch` without using a seperate `TextView`. 

Each child of a `ConstraintLayout` needs to have a vertical and horizontal 
constraint. names of the constrait attributes follow the form: `layout_constraint<Source>_to<Target>Of`. To make a `View` as wide as the 
`ConstraintLayout` it's in, constraint the start and end to the start and 
end of the parent and set the width to `0dp`. 

## Calculate the tip

In order to calculate the tip the code will need to access all of the UI 
elements to read the input from the user. The Andoird framework provides 
`findViewById()` that given the ID of a `View` will return a refermce to
it. This approach works but as you add more views and the UI becomes more
complex then this can become combersone.

For convenience, Andoird also provides a feature called `view binding`. This
makes it easier and faster to call methods on the views in the UI. For this
we need to enable view binding for your app in Gradle.

In `Gradle Scripts/build.gradle (module: Tip_Time.app)` in the `android`
section we add:

``` Kotlin
buildFeatures {
    viewBinding = true
}
```

and sync the gradle. 

Now, in the `onCreate()` method in the `MainActivity` class, instead of
calling `findViewById()` for each `View` in your app, we will create and
initialise a binding obect once:


``` Kotlin
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

```

This line:

``` Kotlin
lateinit var binding: ActivityMainBinding
```

declares a top-level variable in the class for the binding object. It is defined
at this level because it will be used across mutliple methods in `MainActivity`
class. 

The `lateinit` keyword is something new as it is a promise that your code will
initalise the variable before using it. If you don't then the app will crash.

This line:

``` Kotlin
binding = ActivityMainBinding.inflate(layoutInflater)
```

initialises the binding object which will be used to access `Views` in the 
`activity_main.xml` layout.

Then we need to set the content view of the activity. Instead of passing the 
resource ID of the layout: `R.layout.actiivity_main`, this specifies the
root of the hierarchy of views in the app: `binding.root`:

``` Kotlin
setContentView(binding.root)
```

Now, win a reference is needed to a `View` in the app, you can get it from
the `binding` object instead of calling `findViewById()`. The `binding` 
objevct automatically defines references for every `View` in the app that
has an Id. This is much more concise so that often you won't need to create
a variable to hold the reference for a `View`, rather just use it directly 
from the binding object:

``` Kotlin
// Old way with findViewById()
val myButton: Button = findViewById(R.id.my_button)
myButton.text = "A button"

// Better way with view binding
val myButton: Button = binding.myButton
myButton.text = "A button"

// Best way with view binding and no extra variable
binding.myButton.text = "A button"
```

For this though, gthe name of the binding class is generated by converting
the name of the XML file to Pascal case and adding the `Binding` to the end.
This means that the reference for each view is generated by removing
underscores and converting the view name to camel case.

If we wanted to pass a formatted or variable string reference back to 
a string we can adjust the string to: 
`<string name="tip_amount">Tip Amount: %s</string>` where the `%s` is 
where the formatted currency will be inserted. This is then manipulated
in Kotlin with: 
`binding.tipResult.text = getString(R.string.tip_amount, formattedTip)`.



### Tips

- <i>Gradle</i> is the automated build system used by Android Studio. 
Whenever you change code, add a resource, or make other changes to your app
gradle figures out what has changed and takes the necessary steps to rebuild
your app. 

- `?:` is the elvis operator. This returns its first operand if that 
operand is `true`, and otherwise and evalutes and returns its second
operand. In the case of `Kotlin` the first result must be an object 
reference. If the resulting object reference is not `null` it is returned.
Otherwise the value of the seconond operand, which may be `null` 
is returned. If the second operand is null, the operator is also able
to throw and exception. i.e. if the first operator is `null` then the second
operator will be returned:

``` Kotlin
val cost = stringInTextField.toDoubleOrNull() ?: return
```

- Use the `CheckedRadioButtonId` attribute of a `RadioGroup` to find which
`RadioButton` is selected. 

- Use string Parameters like `%s` to create dynamic strings that can easily
be translated into other languages

