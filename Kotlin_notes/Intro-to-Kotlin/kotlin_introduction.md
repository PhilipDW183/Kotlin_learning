# Introduction to Kotlin

## Variables

- We create variables by declaring them for example:

```
val age = 5
```

- Here:
    - `val` is a special word used by Kotlin indicating that what follows is the name of
    a variable
    - `age` is the name of the variable
    - `=` makes the value of `age` be the same as the value on its right

- Important:
    - A variable declared using the `val` keyword can only be set once. 
    You cannot change its value later in the program
    - You can declare a changeable variable with the `var` keyword

- To then use a variable inside a print statement you need to surrounding it with: `${variable}`

- `String` is a variable type
- `Int` is a variable type


## Functions

- `fun` is the word used in Kotlin to main a function.
- This is then followed by the name such as `fun main()`
    - `main` is the first, or main, function that is called when you run the program. Every 
    Kotlin program needs a function named `main`.
    - Inside the paranthesis you can put information for the function to use. The input
    into the function is called "arguments"
    - The `{}` following the parantheses include the code that the function will run
- `println()` is used to print in Kotlin 
- The Smallest program is thus:

```
fun main() {
    println("Happy Birthday")
}
```

- You can also use `print` which will print a line without a line break
- To manually input the line break you can add `\n`

### Function names

- The name of a function always starts with a lowercase letter and a verb
- The name should describe what the function does
- For Example:

```
fun printBorder(){

}
```

- The second word inside the name starts with upper case. This style is called "camel case"

### Function arguments

- An argument can be passed in a function but this must be given a type. For example:

```
fun printBorder(border: String) {
    repeat(23) {
        print("=")
    }
    println()
}
```

- The argument:
    -  `border` is the name of the argument
    - This is followed by a `:`
    - The word `String` is a description of what kind of argument it is


## Kotlin functionality

- `repeat()` statement repeats the intsruction the specified number of times
    - This statement is referred to as a loop
    - Inside the parentheses is the number of repetitions
    - followed by curly braces `{}` which specifies the code to repeat
- For example:

```
fun printBorder(){
    repeat(23){
        print("=")
    }
    println()
}
```

- Will print "=" 23 times.
