# The Basics of Python

## Basic Data Types

python  | Java
---     | ---
int     | int
float   | double
str     | String
bool    | Boolean

## Some Differences Between **Data Types** in Python vs Java

 * Everything in python is an object. There are no primitive data types.

 * Casting is a function call rather than an operator. e.g. `(int) "51"` in Java is written as `int("51")` in python.

 * Boolean values are capitalized. i.e. `true` and `false` are written as `True` and `False` in python.

 * The boolean operators `&&`, `||`, and `!` are written as `and`, `or`, and `not` in python.

## Basic Data Structures

There are 2 basic data structures in python (technically 3 but we'll only be going over 2 today). Python doesn't actually have arrays. Instead it has **lists** and **tuples**

A **list** is actually fairly similar to an array in java, but you can add items, take away items, and do a lot of other things that you can't directly do to arrays in java. Java actually has lists but you have to import them. Lists literals have the following syntax `[a, b, c]`

A **tuple** is also pretty similar to an array, but you can't change any of the elements (kind of similar to how Strings work in java). tuple literals have the following syntax `(a, b, c)`

A big difference between Java and Python is that you can have lists and tuples that contain objects of different types. For instance `['hello', 1, 3.2]` is a valid list in python.

## Assignment in Python

Another big difference from Java to python is the way things are typed. Java is what we call **statically typed** meaning that when you declare a variable or method or parameter you need to specificy the type. e.g. `int x = 1;`.

Python on the other hand is **dynamically typed**, meaning you don't have to specify the type. e.g. `x = 1` is a valid statement in python that will assign the integer value 1 to the variable x. There's also no need for semicolons in python.

## Import Statements and Function and Method Calls

Importing libraries is almost exactly the same as it is in Java. The only diference is that python has additional "syntactic sugar" for importing specific classes and renaming things. This lecture won't go over any of that but it might be interesting to do a bit of research on.

Libraries in python are called modules. To import a module it is simply
`import module_name`. You can then access any classes, functions or global variables in the modlue by using the . operator.

method and function calls work ecatly the way they do in java.

e.g. to find the area of a circle with a radius of 5:

```python
import math
radius = math.pi*math.pow(5, 2)
```

## More Minor Differences from Java to Python

### Exponentiation Operator

The last example used `math.pow` to showcase using a function from an imported module. However, it is more common that you'll see the \*\* operator to perform exponentiation. e.g. `5**2`

### Division

Starting from Python 3, the division operator performs actual division even if you're dividing 2 integers. This means that dividing any two types of numbers will always return a `float`.

Python 3 still has the capability to perform integer division however. Simply use the `//` operator. This will also work for any mixture of `int`s and `float`s. Integer division will always return an integer

### Comments and Docstrings

Python uses `#` symbol for comments. e.g. `# This is a comment in python`

If you're reading other python code, you might run into triple quotes. This is actually what's called a docstring (usually). The first instance of text wrapped in triple quotes that python finds within a function, method, class or module is assigned as the documentation of that scope. Triple quotes can actully be used as regular strings but this is uncommon.

## Control Flow

### Basics

Basic control flow is similar to java. if, else, and while statements are pretty much the same. the only difference is that you don't *need* parentheses (you can still include them if you want to, and in fact they come in handy when you have a long expression that you'd rather break up into multiple lines). At the end of your control statement, before the code that goes inside of its scope, you need a colon. Everything within the scope of the control statement needs to be indented one more level than the control statement itself.

> **A quick note about scopes**: Python doesn't use curly braces for scoping. Python detects scope changes by the level of indentation. e.g.

```python
i = 0
while (i < 3):
    print(i)
    i += 1

print()
while i < 6:
    print(i)
    i += 1
```

will produce the folloing output
```
0
1
2

3
4
5
```

In python `else if` is written as `elif`

### For Loops

Python doesn't actually have the standard for loop that you see in most languages. Python's for loop is actually a for each loop. What this means is that for loops in python atually iterate over each element in a list or "iterable"

The python for loop syntax is defined as `for element in iterebale` where element is a name defined every for loop. and iterable can be a list, a tuple, another data type that suports iteration or a special function called a generator. e.g.

```python
sentence = ['Hello', 'my', 'name', 'is', 'Josie']
for word in sentence:
    print(word)
```

will output

```
Hello
my
name
is
Josie
```

> **A quick note about generators** I won't go into much detail about these now, but a generator is essentially a function that remembers the values inside of its scope every time it's called. python has a lot of functions that return generators as a way to make writing for loops more intuitive. One of the most common generator functions is `range`. when you pass one positve integer argument into range, it gives you a generator that returns all the values from 0 to the number before the one you passed. e.g.

```python
for i in range(5):
    print(i)
```

will output

```
0
1
2
3
4
```

`range` is a very useful function, and I encourage you all to read the documentation on it. Some other useful generator functions are `zip`, `filter`, `map`

## Defining Functions

defining functions in python is very different from java. Defining a function looks like

```python
def foo(parameter, another_parameter):
    return 1
``` 

This defines a function named foo that takes 2 parameters and always returns 1. There are a lot more to functions. For instance, parameters can have default values, functions can have any number of unspecified parameters using some special syntax called packing. Both of these are more advanced features, and this lecture won't go over them.
