# Add a button to an app

## Create the Kotlin Code

`IntRange` is another data type in otlin. It represents a range of
integer umbers from a startig point to an endpoint. This is specified
as:

```
val diceRange  = 1..6
```

You can specify the data type with:

```
val diceRange: IntRange = 1..6
```

We can extract a random number from this range using the 
`random()` function:

```
val randomNumber = diceRange.random()
```

We can create a Dice class to represent a rollable dice. 
Organising everything into a dice class is called encapsulation which
means that you enclose functionality that is logically related into a 
single place. This can be implemented as:

```
class Dice {
    var sides = 6

    fun roll() {
        val randomNumber: Int = (1..6).random()
        println(randomNumber)
    }
}
```

To then call this you would use:

```
fun main() {
    val myFirstDice = Dice()
    println(myFirstDice.sides)
    myFirstDice.roll()
}
```

If we instead want the function to return the value. The function
can be chanegd to:

```
fun main() {
    val myFirstDice = Dice()
    val diceRoll: Int = myFirstDice.roll()
    println("Your ${myFirstDice.sides} sided dice rolled ${diceRoll}")
}


class Dice {
    var sides = 6

    fun roll(): Int {
        val randomNumber: Int = (1..6).random()
        return randomNumber
    }
}
```

The dice can then be changed to alter the number of sides:

```
fun main() {
   
    val myFirstDice = Dice()
    val diceRoll: Int = myFirstDice.roll()
    println("Your ${myFirstDice.sides} sided dice rolled ${diceRoll}!")

    myFirstDice.sides = 20
    println("Your ${myFirstDice.sides} sided dice rolled ${myFirstDice.roll()}!")
}

class Dice {
    var sides = 6

    fun roll(): Int {
        val randomNumber: Int = (1..sides).random()
        return randomNumber
    }
}
```

Instead we can alter the class to accept an integer called `numSides` so that:

```

fun main() {
    val myFirstDice = Dice(6)
    val diceRoll = myFirstDice.roll()
    println("Your ${myFirstDice.numSides} sided dice rolled ${diceRoll}!")
}

class Dice (val numSides: Int) {

    fun roll(): Int {
        val randomNumber = (1..numSides).random()
        return randomNumber
    }
}

```

To simplify the code we want to be as concise as possible. This should reduce 
the `Dice` class such that:

```
class Dice (val numSides: Int) {

    fun roll(): Int {
        return (1..numSides).random()
    }
}

```

## Create an interactive App

An `Activity` provides the window in which your app draws its UI.
Typically and `Activity` takes up the whole screen of your running app.
Every app has one or more activities. The top-level or first activity is
often called the `MainActivity` and is provided by the project template. 
For example when the user scrolls throgh the list of apps on their device
and taps on the "Dice Roller" app icon the Android System will start up
the `MainActivity` of the app.

In the `MainActivity` code you need to provide details on the `Activity's`
layout and how the user should interact with it. 

For more complicated apps there may be multiple screens and more than one
`Activity`. Each `Activity` has a specific purpose.

The first `MainActivity` code already in the app is:

```
package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)
   }
}

```

Instead of calling a `main()` function. Android apps operate differently
by calling the `onCreate()` method of your `MainActivity` when your app
is opened for the first time.

In our current app this looks like:

```
   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)
   }
```

This sets up the `MainActivity` by using code from the imports and by 
setting the starting layout with `setContentView()`.

If you try to call `findViewById` before calling `setContentView()` then 
the app will crash because the view does not exist.

### Make the button interactive

We add code to find the vutton in the `onCreate()` method after 
`setContentView()` call. The `findViewById()` finds the `Button` in the 
layout. `R.id.button` is the resource ID for the `Button` which is a 
unique identified for it:

```
val rollButton: Button = findViewById(R.id.button)
```

Resource IDs are of the form: `R.<type>.<name>`.

The cde saves the reference to the `Button` object in a variable called 
`rollButton`, not the `Button` object itself.

<b>Important</b>: When it assigns an object to a variable, Kotlin doesn't
copy the entire object each time. It saves a reference to the object.

For somthing to happen when we click the button we can add a click lstener
bycalling `setOnClickListener()` method. Instead we use curly braces which
creates a lambda which contains instructions for what happens when the 
button is tapped. In this case we will display a Toast, which is a brief
message in the next step.

This is what the `MainActivity` should now look like:

```
class MainActivity : AppCompatActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)

       val rollButton: Button = findViewById(R.id.button)
       rollButton.setOnClickListener {
           val toast = Toast.makeText(this, "Dice Rolled!", Toast.LENGTH_SHORT)
           toast.show()
       }
   }
}
```

Instead we want to update the textView:

```
class MainActivity : AppCompatActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)

       val rollButton: Button = findViewById(R.id.button)
       rollButton.setOnClickListener {
           val resultTextView: TextView = findViewById(R.id.text)
           resultTextView.text = "6"
       }
   }
}
```

We can then make this more functional by integrating the `Dice` class that
was made before. This changes the full `MainActivity` to:

```
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // find the button by Id
        val rollButton: Button = findViewById(R.id.buton)

        // set an on click listener for when it is clicked
        rollButton.setOnClickListener{
            rollDice()
        }
    }

    private fun rollDice() {
        val dice = Dice(6)
        val diceRoll = dice.roll()
        val resultTextView: TextView = findViewById(R.id.text)
        resultTextView.text = diceRoll.toString()
    }
}

class Dice (val numSides: Int) {

    fun roll(): Int {
        return (1..numSides).random()
    }
}
```

### Clean up the code

#### Reformatting code:

- To select all the text use the keyboard shortcut `Control + A` on Windows.
or `Command+A` on Mac. 
- With all the text selected in the file to reformat the code use `Ctrl + Alt + L`
or `Command + Option + L` on Mac.

#### commenting Code

It is common to add a comment for each class and each method you write. Use the 
`/**` and `*/` symbols at the beginning and end of your comment to tell the 
system that thet are not code that can be ignored during execution.

A comment on a class:

```
/**
* This activity allows the user to roll a dice and view the result
* on the screen.
*/
class MainActivity : AppCompatActivity() {
```

A commend on a method:

```
/**
* Roll the dice and update the screen with the result.
*/
private fun rollDice() {
```

## Add contidional Behaviour in Kotlin

Examples of conditionals in Kotlin are:

```
    if (num >4) {
        println("The variable is greater than 4")
    } else if (num == 4) {
        println("The variable is equal to 4")
    } else {
        println("The variable is less than 4")
    }
```

A `when` statement can be used when there may be multiple else if 
statements. This starts with the keyword `when`, folloed by parentheses 
`()` in which the value to test goes, followed by `{}` for the code to 
execute under different conditions. 

an example of this is:

```
fun main() {
    val myFirstDice = Dice(6)
    val rollResult = myFirstDice.roll()
    val luckyNumber = 4

    when (rollResult) {
        luckyNumber -> println("You won!")
        1 -> println("So sorry! You rolled a 1. Try again!")
        2 -> println("Sadly, you rolled a 2. Try again!")
        3 -> println("Unfortunately, you rolled a 3. Try again!")
        5 -> println("Don't cry! You rolled a 5. Try again!")
        6 -> println("Apologies! You rolled a 6. Try again!")
    }
}


class Dice(val numSides: Int) {
    fun roll(): Int {
        return (1..numSides).random()
    }
}
```

Where you put the the value you are comparing to then the action
you want to take if it is true. For example the first one can be 
read as is `rollResult` is equal to `luckNumber` then print `You Won!`.
i.e. following:

```
when (variable) {
    matches-value -> execute-this-code
    matches-value -> execute-this-code
    ...
}
```

You can then add an else statement:

```
when (x) {
    1 -> print("x == 1")
    2 -> print("x == 2")
    else -> {
        print("x is neither 1 nor 2")
    }
}
```

To catch all other conditions. `else` is necessary when all possible cases
are not covered.

## Set image resource

You can use `setImageResource` to change the image that is displayed 
in an `ImageView`.

## Write unit tests

Test functions must first be annotated with the `@ Test` annotation 
imported from the `org.junit.test` library. This lets the compiler
know that the following method is a test, which lets it run as such.

Following the annotation you have a function decleration i.e.:

```
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
```

Assertion methods are the end goal of a unit test. Ultimately you
want to assert that a result obtained from your code is in a particular
state. Common assertions include:

- assertEquals()
- assertNotEquals()
- assertThat()
- assertTrue()
- assertFalse()
- assertNull()
- assertNotNull()

For our dice generator we can test that the number returned by the dice is
within the range specified. For example:

```
class ExampleUnitTest {
    @Test
    fun generates_number() {
        val dice = Dice(6)
        val rollResult = dice.roll()
        assertTrue(
            "The value of rollResult was not between 1 and 6",
            rollResult in 1..6
        )
    }
}
```

## Intro to debugging

In an Android App the best practice for logging output is using the `Log` class. 
There are several functions for logging output taking teh form:

- `Log.v()`
- `Log.d()`
- `Log.i()`
- `Log.w()`
- `Log.e()`

These methods take two parameters:

- The first is the `tag` which is a string that identifies the source 
of the log message (such as the name of the class that logged the text)
- The second is the log message

An example is adding:

```
private const val TAG = "MainActivity" 
```

To the `MainActivity` class. The log tag is typically declared
outside of the class (before the class decleration). It is declared
as private so that it will only be accessible in that file i.e. `MainActivit.kt`.

You can then add a new function to the `MainActivity` class called `logging()`
as:

```
fun logging() {
    Log.v(TAG, "Hello, world!")
}
```

And call `logging()` in the `onCreate()` method:

```
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    logging()
}
```

The logs then appear when the app is running in the Logcat window at
the bottom of the screen. 

### Log levels

The reason different log functions exist, named with different letters, is because they correspond to different log levels. Depending on what type of information you want to output, you would use a different log level to help you filter it in the Logcat output. There are five main log levels that you'll use regularly.

| Log level | Use case | Example |
| --------- | -------- | ------- |
| ERROR | ERROR logs report that something went seriously wrong, such as the reason why an app crashed. | `Log.e(TAG, "The cake was left in the oven for too long and burned.").` |
| WARN | WARN logs are less severe than an error but still report something that should be fixed to avoid a more serious error. An example might be if you call a function that's deprecated, meaning that its use is discouraged in favor of a newer alternative. | `Log.w(TAG, "This oven does not heat evenly. You may want to turn the cake around halfway through to promote even browning.")`|
| INFO | INFO logs provide useful information, such as an operation being successfully completed. | `Log.i(TAG, "The cake is ready to be served.").println("The cake has cooled.")`|
| DEBUG | DEBUG logs contain information that may be useful when investigating an issue. These logs are not present in release builds such as one you'd publish on the Google Play Store. | `Log.d(TAG, "Cake was removed from the oven after 55 minutes. Recipe calls for the cake to be removed after 50 - 60 minutes.")` |
| VERBOSE | As the name implies, verbose is the least specific log level. What's considered a debug log, versus a verbose log, is a bit subjective, but generally, a verbose log is something that can be removed after a feature is implemented, whereas a debug log may still be useful for debugging. These logs are also not included in release builds. | `Log.v(TAG, "Put the mixing bowl on the counter.")Log.v(TAG, "Grabbed the eggs from the refrigerator.")Log.v(TAG, "Plugged in the stand mixer.")` |


