// Sofern nicht gesondert in () gekennzeichnet, enthalten die Listings Kotlin-Code

//////////////////////////////////////////
// Listing 1: Flut von Klammern (Lisp)  //
//////////////////////////////////////////
(defun factorial (x)
    (if (zerop x)
        1
        (* x (factorial (- x 1)))))


//////////////////////////////////////////////////////////
// Listing 2: Fakultätsberechnung in Kotlin [imperativ] //
//////////////////////////////////////////////////////////

fun factorial(n: Int): Int {
    var res = 1
    for (i in 2..n)
        res *= i
    return res
}

///////////////////////////////////////////////////////////
// Listing 3: Fakultätsberechnung in Kotlin [funktional] //
///////////////////////////////////////////////////////////

fun factorial(n: Int): Int =
    (2..n).fold(1) { l, r -> l * r } )

//////////////////////////////////////////
// Listing 4: Lokale Typinferenz (Java) //
//////////////////////////////////////////

    // type inference for assignments
    var fib = List.of(0, 1, 1, 2, 3, 5, 8);

    // type inference for variables in for loops
    for (var number : fib) {
      System.out.println(number);
    }

///////////////////////////////////////////////////////////////
// Listing 5: Option-Typ mit Sealed Class und Records (Java) //
///////////////////////////////////////////////////////////////

public sealed interface Option<T> permits Some, None {
    Option<?> NONE = new None<>();

    static <T> Option<T> of(T t) {
    return new Some<>(t);
    }

    static <T> Option<T> empty() {
    var t = (Option<T>) NONE;
    return t;
    }

    default <R> Option<R> map(Function<? super T, 
                            ? extends R> mapper) {
    return isEmpty() ? empty() : Option.of(mapper.apply(get()));
    }

    default boolean isEmpty() {
    return this instanceof None;
    }

    T get();
}

record Some<T>(T value) implements Option<T> {
    public T get() {
    return value;
    }
}

record None<T>() implements Option<T> {
    public T get() {
    throw new NoSuchElementException("None.get");
    }
} 

/////////////////////////////////////////////////////
// Listing 6: Imperative und funktionale Varianten //
/////////////////////////////////////////////////////

fun getLengthImperative(str: String) {
    val stringLength = str.length
    println("The string's length: $stringLength")
    return stringLength
}

fun getLengthFunctional(str: String) = 
    str.let { it.length }
    .also { println("The string's length: $it") }

///////////////////////////////////////////////////////////////////////
// Listing 7: Transformations- und Filteroperationen auf Collections //
///////////////////////////////////////////////////////////////////////

val numbers = setOf(1, 2, 3)
println(numbers.map { it * 3 })
// [3, 6, 9]
println(numbers.mapIndexed { idx, value -> value * idx })
// [0, 2, 6]

val colors = listOf("red", "brown", "grey")
val animals = listOf("fox", "bear", "wolf")
println(colors.zip(animals))
// [(red, fox), (brown, bear), (grey, wolf)]

val numbers = listOf("one", "two", "three", "four")
println(numbers.associateWith { it.length })
// {one=3, two=3, three=5, four=4}
println(numbers.associateBy { it.first().uppercaseChar() })
// {O=one, T=three, F=four}

val numberSets = listOf(setOf(1, 2, 3), setOf(4, 5, 6), setOf(1, 2))
println(numberSets.flatten())
// [1, 2, 3, 4, 5, 6, 1, 2]

val numbers = listOf("one", "two", "three", "four")
println(numbers.filter { it.length > 3 })
//[three, four]


////////////////////////////////////////////
// Listing 8: Zugriff auf Nullable-Felder //
////////////////////////////////////////////

fun sendMail(user: User?) {
    // maybeName is of type String? as user might or might not be there
    val maybeName = jack?.name

    // guaranteedName is of type String as we tell the compiler we know
    // the user is present. Might lead to NullPointerException if we're wrong.
    val guaranteedName = jack!!.name

    // fallbackName is of type String as we declare an explicit fallback
    val fallbackName = jack?.name ?: "unknown"
}

///////////////////////////////////////////
// Listing 9: Flexibler Einsatz von when //
////////////////////////////////////////////

// when statement
when (x) {
    0, 1 -> print("x == 0 or x == 1")x
    else -> print("otherwise")
}

// when expression with assignment
val isBinaryNumber = when (x) {
    0, 1 -> true
    else -> false
}

// "pattern matching" instead of if-elseif chain
when {
    x.isOdd() -> print("x is odd")
    y.isEven() -> print("y is even")
    else -> print("x+y is odd")
}


//////////////////////////////////////////
// Listing 10: Scope Functions (Scala) //
//////////////////////////////////////////

def getLengthFunctional(str: String): Int = str.let(_.length)
    .also(it => println(s"The string's length: $it"))

extension [T](self: T) {
    def let[R](block: T => R): R = block(self)
    def also(block: T => Unit): T = {
    block(self)
    self
    }
}

///////////////////////////////////////////////////
// Listing 11: Context Receiver in Kotlin 1.6.20 //
///////////////////////////////////////////////////

interface LoggingContext {
    val log: Logger
}

context(LoggingContext)
fun startBusinessOperation() {
    log.info("Operation has started")
}

fun test(loggingContext: LoggingContext) {
    with(loggingContext) {
        startBusinessOperation()
    }
}
