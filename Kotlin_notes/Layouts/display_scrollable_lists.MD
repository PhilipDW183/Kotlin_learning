# Display scrollable lists

## Lists in Kotlin

A list is collection of items with a specific order. There are type types
in Kotlin:

- Read-only list: `List` cannot be modified after you create it
- Mutable list: `MutableList` can be modified after you create it, meaning
you can add, remove, or update its elements.

When using a `List` or `MutableList` you must specify the type of element
that it will contain. For example `List<Int>` holds a list of integers
and `List<String>` holds a list of strings. 

### List

An example is a read only list of integers seperated by commas:

``` Kotlin
fun main() {
    val numbers: List<int> = listOf(1,2,3,4,5,6)
}
```

If the tyoe of variable can be guessed (or inferred) based on the value
on the right hand side of the assignment operator (=) then we can omit the
datatype the variables i.e.:

``` Kotlin
fun main() {
    val numbers = listOf(1, 2, 3, 4, 5, 6)
}
```

We access items using square bracket notation i.e. `numbers[0]` or using
`numbers.get(0)`. We can also use `.first()` and `.last()` as part of a
list. Futher useful functions include:

- `.contains()` 
- `.reverse()`
- `.sorted()`

### Mutable list

In contrast we can create Mutable lists using `mutableListOf()`. This can 
be declared as either:

``` Kotlin
val entrees = mutableListOf<String>()
```

or:

``` Kotlin
val entrees: MutableList<String> = mutableListOf()
```

We can add items using `.add()` and add multiple items using `.addAll()` 
with a list passed in. Other functionality includes:

- `.remove(value)`
- `.removeAt(index)`
- `.clear()`
- `.isEmpty()`

### Loop through a list

We can use a `while` loop to loop over a list e.g.:

``` Kotlin
val guestsPerFamily = listOf(2, 4, 1, 3)
var totalGuests = 0
var index = 0
while (index < guestsPerFamily.size) {
    totalGuests += guestsPerFamily[index]
    index++
}
println("Total Guest Count: $totalGuests")
```

or a for loop:

``` Kotlin
val names = listOf("Jessica", "Henry", "Alicia", "Jose")
for (name in names) {
    println("$name - Number of characters: ${name.length}")
}
```


### putting it all together

#### Variable arguments

- `vararg` modifier allows you to pass a variable number of arguments of
the same tgype into a function or constructor. For example:

```

open class Item(val name: String, val price: Int) {
    override fun toString(): String {
        return name
    }
}

class Vegetables(vararg val toppings: String) : Item("Vegetable", 5) 

```

allows to pass in multiple toppings. 

#### Builder pattern

The builder pattern is a design pattern in programming that allows you to
build up a complex object in a step by step approach.

Kotlin lrovides the keyword `this` to reference the current object 
instance. Within the `addItem()` and `addAll()` methods you can return
the current order by returning `this`:

``` Kotlin
fun addItem(newItem: Item): Order {
    itemList.add(newItem)
    return this
}

fun addAll(newItems: List<Item>): Order {
    itemList.addAll(newItems)
    return this
}
```

This allows you to chain calls together. For example:

``` Kotlin
val order4 = Order(4).addItem(Noodles()).addItem(Vegetables("Cabbage", "Onion"))
ordersList.add(order4)
```

While it is not mandatory to chain these calls, it is a common and 
recommended practice that takes advantage of the function's return value.

Their resulting code is:

``` Kotlin
open class Item(val name: String, val price: Int)

class Noodles : Item("Noodles", 10) {
    override fun toString(): String {
        return name
    }
}

class Vegetables(vararg val toppings: String) : Item("Vegetables", 5) {
    override fun toString(): String {
        if (toppings.isEmpty()) {
            return "$name Chef's Choice"
        } else {
            return name + " " + toppings.joinToString()
        }
    }
}

class Order(val orderNumber: Int) {
    private val itemList = mutableListOf<Item>()

    fun addItem(newItem: Item): Order {
        itemList.add(newItem)
        return this
    }

    fun addAll(newItems: List<Item>): Order {
        itemList.addAll(newItems)
        return this
    }

    fun print() {
        println("Order #${orderNumber}")
        var total = 0
        for (item in itemList) {
            println("${item}: $${item.price}")
            total += item.price
        }
        println("Total: $${total}")
    }
}

fun main() {
    val ordersList = mutableListOf<Order>()

    // Add an item to an order
    val order1 = Order(1)
    order1.addItem(Noodles())
    ordersList.add(order1)

    // Add multiple items individually
    val order2 = Order(2)
    order2.addItem(Noodles())
    order2.addItem(Vegetables())
    ordersList.add(order2)

    // Add a list of items at one time
    val order3 = Order(3)
    val items = listOf(Noodles(), Vegetables("Carrots", "Beans", "Celery"))
    order3.addAll(items)
    ordersList.add(order3)

    // Use builder pattern
    val order4 = Order(4)
        .addItem(Noodles())
        .addItem(Vegetables("Cabbage", "Onion"))
    ordersList.add(order4)

    // Create and add order directly
    ordersList.add(
        Order(5)
            .addItem(Noodles())
            .addItem(Noodles())
            .addItem(Vegetables("Spinach"))
    )

    // Print out each order
    for (order in ordersList) {
        order.print()
        println()
    }
}
```