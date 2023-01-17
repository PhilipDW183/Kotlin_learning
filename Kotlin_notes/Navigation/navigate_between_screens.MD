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