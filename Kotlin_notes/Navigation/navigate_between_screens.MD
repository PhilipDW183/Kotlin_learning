# Navigate between screens

## Collections in Kotlin

A collection is a group of related items. This can be ordered or unordered,
and the items can be unique or not. As with lists Kotling distinguishes 
between mutable and immutable collections.

Another collection is a set. This is a group of items like a list but 
there can't be any duplications and the order doesn't matter. This can
be created like:

``` Kotlin
fun main() {
    val numbers = listOf(0, 3, 8, 4, 0, 5, 5, 8, 9, 2)
    val setOfNumbers = numbers.toSet()
    println("set:    ${setOfNumbers}")
}
```

To make it mutable you use:

``` Kotlin
val set1 = setOf(1,2,3)
val set2 = mutableSetOf(3,2,1)
```

Another type of collection is a `map` or `dictionary`. A map is a set of
key-value pairs, designed to make it easy to look up a value given a 
particular key. Keys are unique and each key maps to exactly one value,
but the values can have duplicates. This can be created as:

``` Kotlin
fun main() {
    val peopleAges = mutableMapOf<String, Int>(
        "Fred" to 30,
        "Ann" to 23
    )
    println(peopleAges)
}
```

To add more entries to the map we can use the `put()` function, passing
in the key and the value:

``` Kotlin
peopleAges.put("Barbara", 42)
```

or short hand notation to add entries:

``` Kotlin
peopleAges["Joe"] = 51
```

### working with collections

If collections are mutable, you can add or remove items. You can enumerate
all the items, find a particular items. or sometimes convert one type of 
collection to another. 

#### Useful functions:

- `forEach()`
    - goes through all the objects in a collection e.g.:
    ``` Kotlin
    peopleAges.forEach { print("${it.key} is ${it.value}, ") }
    ```
    - This uses the identifier `it` for specifying teh current item
    - The code is run inbetwen `{}`
- `map()`
    - Applies a transformation to each item in a collection e.g:
    ``` Kotlin
    println(peopleAges.map { "${it.key} is ${it.value}" }.joinToString(", ") )
    ```
    - Applies to each item and creates a new collection of the transformed
    items
    - The part in the curly braces `{}` defines the transformation to apply
    to each item.
- `filter()`
    - returns the items in a collection that match based on an expression
    - e.g:
    ``` Kotlin
    val filteredNames = peopleAges.filter { it.key.length < 4 }
    println(filteredNames)
    ```

### Lambdas and higher-order functions

A lambda expression is a function with no name that can be used immediately.
You can store functions in variables and classes, pass functions as 
argum,ents and even return functions. They can be treated like variables
of other types such as `Int`.

To enable this type of behaviour, Kotlin has something called <i> function
tyoes </i> where you can define a specific type of function based on its
input parameters and return values. This appears in the format:

``` Kotlin
(Int) -> Int
```

A function of the above type must take in a parameter of type `Int` and 
return a value of type `Int`. In a function type nottaion, the 
parameters are listed in parentheses (seperated by commas if there are
multiple parameters). Then there is an arrow `->` which is followed by the
return type. 

For examplea lambda could be stored which triples an integer:

``` Kotlin
fun main() {
    val triple: (Int) -> Int = { a: Int -> a * 3 }
    println(triple(5))
}
```

Which can also be shortened to:

``` Kotlin
val triple: (Int) -> Int = { it * 3 }
```

Following that, higher-order functions are when you
pass a function (in this case a lambda) to another
function, or returning a function from another 
function.

`map`, `filter` and `forEach` are higher-order
functions because they all took a function as a
parameter. 

For example, you can pass a function to `sortedWith()`
method to sort a list of strings e.g.

``` Kotlin
fun main() {
    val peopleNames = listOf("Fred", "Ann", "Barbara", "Joe")
    println(peopleNames.sorted())
    println(peopleNames.sortedWith { str1: String, str2: String -> str1.length - str2.length })
}
```

Examples in Android include `onClickListener` and `OnKeyListener`

## Activities and Intents

### Intents

An <i>intent</i> is an object representing some action to be performed
. The most common use for an intent is to launch an activity. There
are two types of intents - <b>implicit</b> and <b>explicit</b>. 

- An <b>explicit intent</b> is highly specific, where you know the
exact activity to be launched, often a screen in yur own app.
- An <b>implicit intent</b> is a bit more abstract where you tell the 
system the type of action, such as opening a link, composing an email,
or making a phone call, and the system is responsible for figuring
out how to fulfill the request. 

Generally, when showing an activity in your own app you use an 
explicit intent. But for actions that don't necessarily involve the 
current app, e.g. an extenrla link, you'd use <b>implicit intent</b>.

### Setting up an explicit intent

In the case of the word app, we can create an intent to launch the 
`DetailActivity` which is the second list of words.

For this we need to:
- Set up something that will trigger the new activity
- Get a reference to the context
- Create an intent passing in the context and the class name of the
dest activity to create the new object behind the scenes
- Pass in any extra data
- Start the activity on the context object.

In this case the code looks like:

``` Kotlin
 // create a listener for the button
        holder.button.setOnClickListener{
            // get a reference to the context
            val context = holder.itemView.context
            // create an intent passing in the context and the class name of the dest activity
            // this creates a DetailActivity object behind the scenes
            val intent = Intent(context, DetailActivity::class.java)
            // this passes the extra data, in this case the letter, to the new activity
            intent.putExtra("letter", holder.button.text.toString())
            // then start activity on the context object passing the intent
            context.startActivity(intent)
        }
```

which is implemented in the `onBindViewHolder()` of the `LetterAdapter`
class. 

The information then passed from the `MainActivity` can be retried in the 
`DetailActivity` using:

``` Kotlin
val letterId = intent?.extras?.getString("letter").toString()
```

- The `intent` property is part of any activity and keeps a reference to
the intent that was used to launch the activity
- The `extras` property is of type `Bundle` which provides a way to access 
all extras passed into the intent
- Both are marked with question mark as both properties are nullable. To 
safely access a property or call a function on a `null` object, you put 
a `?` after the name.
- We add `toString()` to ensure `letter` is a `String` and not `null`

Currently, both the code to perform the intent and retrive the selected 
letter hardcard the name of the extra `letter`. While this works for the 
small example, it is not the best approach for large apps where you have
many more intent extras to keep track off. We need a way to define a 
constant that can be used across multiple classes while keeping the code
organised. 

For this there is a Kotlin feature that can be used to seperate constants
and make them usable without a particular instance of the class called 
[companion objects](https://kotlinlang.org/docs/object-declarations.html).
This is similar to other objects, such as instances of a class, but only
a single instance of a companion object will exist for the duration of your
program which is why this is sometimes called the 
[singleton pattern](https://en.wikipedia.org/wiki/Singleton_pattern). In 
this case we will use a companion object to refactor the code for the 
"letter" extra.

For this we create a companion object:

``` Kotlin
    companion object {
        const val LETTER = "letter"
    }
```

call this in `DetailActivity` `onCreate()`:

``` Kotlin
val letterId = intent?.extras?.getString(LETTER).toString()
```

And use this in the `putExtra` of `LetterAdapter`:

``` Kotlin
intent.putExtra(DetailActivity.LETTER, holder.button.text.toString())
```

### Setting up an implicit activity

In this case we are going to use the dictionary functionalkity provided by
Google search to show the definition of each word, using the devices 
browser to show the search page.

For this we can add an `onClickListener` to each word as:

``` Kotlin 
        holder.button.setOnClickListener {
            // create a URI for the destination
            val queryUrl: Uri = Uri.parse("${DetailActivity.SEARCH_PREFIX}${item}")
            // create a new intent
            // use ActionView which takes a URI which the system knows to process
            // this using the user's web browser
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            // start the activity by passing in the intent
            context.startActivity(intent)
        }
```

### Set up menu and icons

We can now add a menu option so that users can toggle between list and grid 
layout for the letters. 

The top of apps are called app bar. The menu option is set up from the `MainActivity`
and uses `menu` in the `res` folder. These allow you to add buttons and menus
to the app bar.

### Summary

- Explicit intents are used to navigate specific activity in the app
- Implicit intents correspond to specific actions and let the system
determine how to fulfill the intent
- Menu options allow you to add buttons and menuts to the app bar
- Companion objects provide a way to associate reusable constants with a
type rather than instance of the type
- To perform an intent:
    - Get a reference to the context
    - Create an `Intent` object providing either an activity or intent type
    (depending on whether explicit or implicit)
    - Pass any needed data by calling `putExtra`
    - Call `startActivity()` passing the `intent` object


Commonly used intents can be found [here](https://developer.android.com/guide/components/intents-common?authuser=1)