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

## Use RecyclerView to display a scrollable list

`RecyclerView` is designed to be very efficienct, even wiht large lists,
by reusing/recycling, the views that have scrolled off screen. When a list
is scrol off the screen, `RecyclerView` resues that view for the next list
item about to be displayed. This means that the item is filled with new 
content that scrolls onto the screen. This saves a lot of processing time 
and helps lists scroll more smoothly. 

### Packages

Developers will often seperate the classes that work with data and classes
that build into the UI.

A package name can be anything, as long as it is globally unique; no other
published package anywhere can have the same name. Conventions to name 
these packages include:

- The package name is usually structured from general to specific with 
each part of the name in lowercase letters and seperated by a period.
- It is a convention ot use a domain, usually that of the business, as
the first part of the name
- You can choose the names of the packages to indicate what's inside the
package and how teh packages relate to each other
- For code examples like this one, `com.example` followed by the name of
the appp is commonly used.

### Create the affirmations data class

An object instanbce of `Affirmation` represents one affrimation and 
contains the resource Id of the string with the affirmation.

Developers often use `model` as the package name for classes that model
(or represent) the data. 

### Create a class to be a data source

Data displayed in the app may come from different sources. The data may
not be in the format needed. We can hide the data preperation in a 
seperate `Datasource` class that prepares the data for the app. 

Preparing data is a seperate concern, put the `Datasource` class in a 
seperate <b>data</b> package.

### Adding a recyler view

#### Add a Recyler view to the layout

1. Replace the `ConstraintLayout` with a `FrameLayout`
2. Add the `RecyclerView` to the FrameLayout
3. Add `app:layoutManager="LinearLayoutManager"` to set linear layout
4. To allow to scroll verticall add `android:scrollbars="vertical"`

This should then take the form:

``` Kotlin
<FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <androidx.recyclerview.widget.RecyclerView
        android:id="@+id/recycler_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:scrollbars="vertical"
        app:layoutManager="LinearLayoutManager" />

</FrameLayout>
```

#### Implement an Adapted for the Recycler View

The app needs a way to take the data from `Datasource` and format it so
that each `Affirmation` can be displayed as an item in the `RecyclerView`.

<i>Adapter</i> is a desig pattern that adapts the data into something that
can be used by `RecyclerView`. In this case we need an adapted that can 
take an `Affirmation` instance form the list returned by `loadAffirmations`
and turn it into a list item view so that it can be displayed in the 
`RecyclerView`.

When the app is run, `RecyclerView` uses the adapted to figure out how to 
display the data on screen. `RecyclerView` asks tha adapted to create a 
new list item view for the first data item in the list. Once it has the 
view, it asks tha adapted to provide the data to draw the item. This 
process repeats until the `RecyclerView` doesn't need any more view to
fill the screen. 

An adapted has multiple parts:

##### Create a layout for items

Each item in the `RecyclerView` has its own layout which is defined in 
a seperate layout file. In this case we only display a string so we
use a `TextView`. This takes the form:

``` XML
<?xml version="1.0" encoding="utf-8"?>
<TextView xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/item_title"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```

`wrap` content is specified for the `layout_width` and `layout_height`
and we don't need a `ViewGroup` around the layout as this will later
be inflated and added as a child to the parent `RecyclerView`. 


##### Create an ItemAdapter Class

We need to add a parameter to the constructor of the `ItemAdapter` 
so that we can pass a list of affirmations to the adapter. 

The `ItemAdapter` needs information on how to resolve the string 
resources. This and other information about the app is stored in a
`Context` object instance that we pass to the `ItemAdapter` instance.

##### Create a ViewHolder

`RecyclerView` doesn't interact directly with item views but deals
with `ViewHolders` instead. A `ViewHolder` represents a single list
item view in `RecyclerView` and can be reused when possible. A 
`ViewHolder` instance holds references to the individual views within a 
list item layout. This makes it easier to update the list item view
with new data. They also add information that `RecyclerView` uses to 
efficiently move views around the screen.

#### Modify the MainActivity to use a RecyclerView

To finish we need to use the `DataSource` and `ItemAdapter` classes
to create and display items in the `RecylcerView`. This is done in
`MainActivity`.

- Load in the dataset: `val myDataset = Datasource().loadAffirmations()`
- Reference the `RecyclerView`: 
`val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)` 
- Tell the `RecyclerView` to use the `ItemAdapter` that was created:
`recyclerView.adapter = ItemAdapter(this, myDataset)`

### Summary:

- `RecylerView` widget helps to display a list of data
- `RecyclerView` uses the adapter pattern to adapt and display the data
- `ViewHolder` creates and holds the views for `RecyclerView`
- `RecyclerView` comes with inbuilt `LayoutManagers`. This delegates
how items are laid out to `LayoutManagers`

To implement the adapter:

- Create a new class for the adapater e.g. `ItemAdapter`
- Create a customer `ViewHolder` class that represents a single list
item view. Extend from `RecyclerView.ViewHolder` class
- Modify the `ItemAdapter` class to extend from `RecyclerView.Adapter`
class with the customer `ViewHolder` class.
- Implement these methods within the adapter: `getItems()`,
`onCreateViewHolder()` and `onBindViewHolder()`.

## Display a list of images using cards

### Add support for images in the Affirmation class

We need to add a property in the `Affirmation` data class to hold a value 
for an image resource ID. This means a single `Affirmation` object instance
will contain a resource ID for the text and image resource ID as inputs:

``` Kotlin
data class Affirmation(val stringResourceId: Int, val imageResourceId: Int)
```

Users however could accidentally pass in the arguments in the wrong order.
To avoird this we can use Resource anotations to trigger a warning if 
the user supplies the wrong type of ID:

``` Kotlin
package com.example.affirmations.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

// pass in the string resource ID when creating an affirmation
data class Affirmation(
    @StringRes val stringResourceId: Int, 
    @DrawableRes val imageResourceId: Int
    )
```

### Initialise list of affrimations with images

We now need to update the `DataSource` class to include a link to the 
image as well.

``` Kotlin
package com.example.affirmations.data

import com.example.affirmations.R
import com.example.affirmations.model.Affirmation

class Datasource() {

    fun loadAffirmations(): List<Affirmation> {
        return listOf<Affirmation>(
            Affirmation(R.string.affirmation1, R.drawable.image1),
            Affirmation(R.string.affirmation2, R.drawable.image2),
            Affirmation(R.string.affirmation3, R.drawable.image3),
            Affirmation(R.string.affirmation4, R.drawable.image4),
            Affirmation(R.string.affirmation5, R.drawable.image5),
            Affirmation(R.string.affirmation6, R.drawable.image6),
            Affirmation(R.string.affirmation7, R.drawable.image7),
            Affirmation(R.string.affirmation8, R.drawable.image8),
            Affirmation(R.string.affirmation9, R.drawable.image9),
            Affirmation(R.string.affirmation10, R.drawable.image10)
        )
    }
}
```

### Add an `ImageView` to the list item layout

We need to biew add ab `ImageView` to the item layout. For this we need
to place them in a `ViewGroup`. To arrange them in a vertical layout we
use the `LinearLayout`:

``` XML
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:orientation="vertical">
    
    <ImageView
        android:id="@+id/item_image"
        android:layout_width="match_parent"
        android:layout_height="194dp"
        android:importantForAccessibility="no"
        android:scaleType="centerCrop"/>

    <TextView
        android:id="@+id/item_title"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />

</LinearLayout>
```

### Update the item adapter to set the image

The `ItemViewHolder` instance should now hold a reference to the
`TextView` and the `ImageView` in the list item layout:

``` Kotlin
lass ItemViewHolder(private val view: View): RecyclerView.ViewHolder(view) {
    val textView: TextView = view.findViewById(R.id.item_title)
    val imageView: ImageView = view.findViewById(R.id.item_image)
}
```

Edit the `onBindViewHolder` to set the image resource:

``` Kotlin
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text = context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)
    }
```

### Polishing the UI

We can make the app look more polished such as:

- Adding Padding
- Use cardsDispla
    - A [`CardView`](https://m2.material.io/develop/android/components/cards/) 
    provides an easy way to contain a grop of views while 
    providing a consistent style for the container. 
- Change the App theme colors
- Change the app icon

## Test Lists and Adapters

Tests are not suppored contain logic, they are only supposed to test it. 
They should also not manipulate values or conduct any real computation.

The unit tests should only call the piece of code from the app that is 
necessary for the test and test the values or state of teh code that 
results from calling that code. UI tests should only test for the 
expected state of the user integrace.

### Instrumentation test

To create instrumentation tests we need:

1. Add a test runner to the newly created class:
```Kotlin
@RunWith(AndroidJUnit4::class)
```
2. Make an activity scenario rule for the main activity:
```
@get:Rule
val activity = ActivityScenarioRule(MainActivity::class.java)
```

We need to add a dependency in the `build.gradle` file. Here:

- `implementation` is for dependencies that will be used in the application
itself
- `testImplementation` is for dependencies that are used in unit tests
- `androidTestImplementation` is for dependencies that are used in 
instrumentation tests.

In this case we add a dependency to interact with `RecyclerViews` in 
instrumentatinon tests:

``` gradle
dependencies {
    ...
    androidTestImplementation 'androidx.test.espresso:espresso-contrib:3.4.0'
}
```

We can then test the `RecyclerView` e.g. scrolling to an individual item:

``` Kotlin
   @Test
    private fun scroll_to_item(){
        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions
                .scrollToPosition<RecyclerView.ViewHolder>(9)
        )

        onView(withText(R.string.affirmation10))
            .check(matches(isDisplayed()))
    }

```

Other actions that can be tested can be found [here](https://developer.android.com/reference/androidx/test/espresso/contrib/RecyclerViewActions?authuser=1).

### Unit Tests

``` Kotlin
package com.example.affirmations

import android.content.Context
import com.example.affirmations.adapter.ItemAdapter
import com.example.affirmations.model.Affirmation
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock

class AffirmationsAdapterTests {

    // create a mock instance of context becaus we run on aJVM
    // not an actual device
    private val context = mock(Context::class.java)

    @Test
    fun adapater_size() {
        val data = listOf(
            Affirmation(R.string.affirmation1, R.drawable.image1),
            Affirmation(R.string.affirmation2, R.drawable.image2)
        )
        val adapter = ItemAdapter(context, data)
        assertEquals("ItemAdapter is not the correct size", data.size, adapter.itemCount)
    }

}
```