import kotlin.math.PI

/**
 * Map
 * Exercise 1
 * You have a list of "green" numbers and a list of "red" numbers. Complete the code to print how many numbers there are in total.
 * @return void
 */
fun countTotalNumbers() {
    val greenNumbers = listOf(1, 4, 23)
    val redNumbers = listOf(17, 2)
    val totalCount = greenNumbers.count() + redNumbers.count()
    println("Total count of numbers: $totalCount")
}

/**
 * Map
 * Exercise 2
 * You have a set of protocols supported by your server. A user requests to use a particular protocol. Complete the program to check whether the requested protocol is supported or not.
 * @return void
 */
fun checkProtocolSupport() {
    val SUPPORTED = setOf("HTTP", "HTTPS", "FTP")
    val requested = "smtp"
    val isSupported = requested in SUPPORTED
    println("Support for $requested: $isSupported")
}

/**
 * Conditional expressions
 * Exercise 2
 * Using a when expression, update the following program so that it prints the corresponding actions when you input the names of game console buttons.
 * @param button The button to check (A, B, X, Y, or other)
 * @return String The action corresponding to the button
 */
fun getButtonAction(button: String): String {
    return when (button) {
        "A" -> "Yes"
        "B" -> "No"
        "X" -> "Menu"
        "Y" -> "Nothing"
        else -> "There is no such button"
    }
}

/**
 * Loops
 * Exercise 1
 * You have a program that counts pizza slices until there's a whole pizza with 8 slices. Refactor this program in two ways:
 * Use a while loop.
 * Use a do-while loop.
 * @return void
 */
fun countPizzaSlicesWhile() {
    var pizzaSlices = 0
    while (pizzaSlices < 7) {
        pizzaSlices++
        println("There's only $pizzaSlices slice/s of pizza :(")
    }
    pizzaSlices++
    println("There are $pizzaSlices slices of pizza. Hooray! We have a whole pizza! :D")

    var pizzaSlices2 = 0
    do {
        pizzaSlices2++
        println("There's only $pizzaSlices2 slice/s of pizza :(")
    } while (pizzaSlices2 < 7)
    pizzaSlices2++
    println("There are $pizzaSlices2 slices of pizza. Hooray! We have a whole pizza! :D")
}

/**
 * Loops
 * Exercise 2
 * Write a program that simulates the Fizz buzz game. Your task is to print numbers from 1 to 100 incrementally, replacing any number divisible by three with the word "fizz", and any number divisible by five with the word "buzz". Any number divisible by both 3 and 5 must be replaced with the word "fizzbuzz".
 * @return void
 */
fun fizzBuzz() {
    for (number in 1..100) {
        println(
            when {
                number % 15 == 0 -> "fizzbuzz"
                number % 3 == 0 -> "fizz"
                number % 5 == 0 -> "buzz"
                else -> "$number"
            }
        )
    }
}

/**
 * Loops
 * Exercise 3
 * You have a list of words. Use for and if to print only the words that start with the letter l.
 * @return void
 */
fun printWordsStartingWithL() {
    val words = listOf("dinosaur", "limousine", "magazine", "language")
    for (w in words) {
        if (w.startsWith("l"))
            println("Word starting with 'l': $w")
    }
}

/**
 * Functions
 * Exercise 1
 * Write a function called circleArea that takes the radius of a circle in integer format as a parameter and outputs the area of that circle.
 * @param radius The radius of the circle
 * @return Double The area of the circle
 */
fun circleArea(radius: Int): Double {
    val area = PI * radius * radius
    println("Area of circle with radius $radius: $area")
    return area
}

/**
 * Functions
 * Exercise 2
 * Rewrite the circleArea function from the previous exercise as a single-expression function.
 * @param radius The radius of the circle
 * @return Double The area of the circle
 */
fun circleAreaSingleExpression(radius: Int): Double {
    val area = PI * radius * radius
    println("Area of circle with radius $radius (single expression): $area")
    return area
}

/**
 * Functions
 * Exercise 3
 * You have a function that translates a time interval given in hours, minutes, and seconds into seconds. In most cases, you need to pass only one or two function
 * parameters while the rest are equal to 0. Improve the function and the code that calls it by using default parameter values and named arguments so that the code is
 * easier to read. 
 * @param hours The number of hours (default: 0)
 * @param minutes The number of minutes (default: 0)
 * @param seconds The number of seconds (default: 0)
 * @return Int Total time in seconds
 */
fun intervalInSeconds(hours: Int = 0, minutes: Int = 0, seconds: Int = 0): Int {
    val totalSeconds = ((hours * 60) + minutes) * 60 + seconds
    println("$hours hours, $minutes minutes, $seconds seconds = $totalSeconds seconds")
    return totalSeconds
}

/**
 * Lambda expressions
 * Exercise 1
 * You have a list of actions supported by a web service, a common prefix for all requests, and an ID of a particular resource. To request an action title over the
 * resource with ID: 5, you need to create the following URL: https://example.com/book-info/5/title. Use a lambda expression to create a list of URLs from the list of
 * actions.
 * @return void
 */
fun createUrls() {
    val actions = listOf("title", "year", "author")
    val prefix = "https://example.com/book-info"
    val id = 5
    val urls = actions.map { action -> "$prefix/$id/$action" }
    println("Generated URLs: $urls")
}

/**
 * Lambda expressions
 * Exercise 2
 * Write a function that takes an Int value and an action (a function with type () -> Unit) which then repeats the action the given number of times. Then use this function
 * to print “Hello” 5 times.
 * @param n Number of times to repeat the action
 * @param action The action to repeat
 * @return void
 */
fun repeatN(n: Int, action: () -> Unit) {
    println("Repeating action $n times:")
    for (i in 1..n) {
        action()
    }
}

/**
 * Properties
 * Exercise 2
 * Declare the additional data classes that are needed for this code to compile.
 * @property firstName First name of the person
 * @property lastName Last name of the person
 */
data class Name(val firstName: String, val lastName: String)

/**
 * @property street Street address
 * @property city City information
 */
data class Address(val street: String, val city: City)

/**
 * @property name Name of the city
 * @property country Country code
 */
data class City(val name: String, val country: String)

/**
 * @property name Person's full name
 * @property address Person's address
 * @property ownsAPet Whether the person owns a pet (default: true)
 */
data class Person(val name: Name, val address: Address, val ownsAPet: Boolean = true)

/**
 * Null safety
 * Exercise
 * You have the employeeById function that gives you access to a database of employees of a company. Unfortunately, this function returns a value of the Employee?
 * type, so the result can be null. Your goal is to write a function that returns the salary of an employee when their id is provided, or 0 if the employee is missing from
 * the database.
 * @property name Employee's name
 * @property salary Employee's salary
 */
data class Employee(val name: String, var salary: Int)

/**
 * @param id Employee ID
 * @return Employee? The employee object or null if not found
 */
fun employeeById(id: Int) = when(id) {
    1 -> Employee("Maria", 20)
    2 -> null
    3 -> Employee("Jan", 21)
    4 -> Employee("Anna", 23)
    else -> null
}

/**
 * @param id Employee ID
 * @return Int The employee's salary or 0 if employee not found
 */
fun salaryById(id: Int): Int {
    val salary = employeeById(id)?.salary ?: 0
    println("Salary for employee $id: $salary")
    return salary
}

fun main() {
    println("\n=== Map Exercise 1 ===")
    countTotalNumbers()
    
    println("\n=== Map Exercise 2 ===")
    checkProtocolSupport()
    
    println("\n=== Conditional expressions Exercise 2 ===")
    println("Button A action: ${getButtonAction("A")}")
    
    println("\n=== Loops Exercise 1 ===")
    countPizzaSlicesWhile()
    
    println("\n=== Loops Exercise 2 ===")
    fizzBuzz()
    
    println("\n=== Loops Exercise 3 ===")
    printWordsStartingWithL()
    
    println("\n=== Functions Exercise 1 ===")
    circleArea(2)
    
    println("\n=== Functions Exercise 2 ===")
    circleAreaSingleExpression(2)
    
    println("\n=== Functions Exercise 3 ===")
    intervalInSeconds(hours = 1, minutes = 20, seconds = 15)
    intervalInSeconds(minutes = 1, seconds = 25)
    intervalInSeconds(hours = 2)
    intervalInSeconds(minutes = 10)
    intervalInSeconds(hours = 1, seconds = 1)
    
    println("\n=== Lambda expressions Exercise 1 ===")
    createUrls()
    
    println("\n=== Lambda expressions Exercise 2 ===")
    repeatN(5) { println("Hello") }
    
    println("\n=== Properties Exercise 2 ===")
    val person = Person(
        Name("Piotr", "Makowski"),
        Address("ul. Kwiatowa 123", City("Warszawa", "PL")),
        ownsAPet = false
    )
    println("Created person: $person")
    
    println("\n=== Null safety Exercise ===")
    println("Total salaries: ${(1..5).sumOf { id -> salaryById(id) }}")
}
