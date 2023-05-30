import java.util.Date

fun main(args: Array<String>) {
    println("Hello World!")

    // INMUTABLES (NO se reasignan "=")
    val inmutable: String = "Andrés";
    // inmutable = "Fernando";

    // Mutables (Re asignar)
    var mutable: String = "Andrés";
    mutable = " Fernando";

    //Val mejor que Var

    //Duck Typing
    var ejemploVariable = "Andrés Ponce";
    val edadEjemplo: Int = 12;
    ejemploVariable.trim();

    //Variables primitivas
    val nombreProfesor: String = "Adrián Eguez";
    val sueldo: Double = 1.2;
    val estadoCivil: Char = 'C'
    val mayorEdad: Boolean = true
    val fechaNacimiento: Date = Date();

    //Switch (WHEN haces tus memes en Kotlin)
    val estadoCivilWhen = "C"
    when (estadoCivilWhen) {
        ("C") -> {
            println("Casado")
        }
        ("S") -> {
            println("Soltero")
        }
        else -> {
            println("No sabemos")
        }
    }

    //If-Else
    val esSoltero = (estadoCivilWhen == "S")
    val coqueteo = if (esSoltero) "Sí" else "No"

    calcularSueldo(10.00);
    //Parámetros nombrados
    calcularSueldo(10.00, bonoEspecial = 20.00);
    calcularSueldo(bonoEspecial = 20.00, sueldo = 10.00, tasa = 14.00)

    val sumaUno = Suma(1,1);
    val sumaDos = Suma(null, 1)
    val sumaTres = Suma(1, null)
    val sumaCuatro = Suma(null, null)

    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()

    println(Suma.pi)
    println(Suma.elevarAlCuadrado(2))
    println(Suma.historialSumas)
    
    //ARREGLOS

    //Tipos de arreglos

    //Arreglo estático
    val arregloEstatico: Array<Int> = arrayOf<Int>(1, 2, 3)
    println(arregloEstatico)

    //Arreglo dinámico
    val arregloDinamico: ArrayList<Int> = arrayListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    //Operadores -> Sirven para los arreglos

    //FOR EACH -> Unit
    //Itera un arreglo
    val respuestaForEach: Unit = arregloDinamico.forEach {
        valorActual: Int ->
            println("Valor actual: $valorActual")
    }

    arregloDinamico.forEach { println(it) } //it (en inglés "eso") significa el elemento iterado

    arregloEstatico.forEachIndexed { index: Int, valorActual ->
        println("$index.- Valor $valorActual")
    }

    //MAP -> Muta el arreglo (Cambia el arreglo)
    //1) Enviamos el nuevo valor de la iteración
    //2) Nos devuelve un NUEVO ARREGLO con los valores modificados

    val respuestaMap: List<Double> = arregloDinamico.map {
        valorActual: Int ->
            return@map valorActual.toDouble() + 100.0;
    }

    println(respuestaMap)
    val respuestaMapDos = arregloDinamico.map { it + 15 }

    //Filter -> FILTRAR EL ARREGLO
    //1) Devolver una expresión TRUE o FALSE
    //2) Nuevo arreglo filtrado

    val respuestaFilter: List<Int> = arregloDinamico.filter {
        valorActual: Int ->
            val mayoresACinco: Boolean = valorActual > 5
            return@filter mayoresACinco
    }

    val respuestaFilterDos = arregloDinamico.filter { it <= 5 }
    println(respuestaFilter)
    println(respuestaFilterDos)

    //OR AND
    //OR -> ANY (Alguno cumple) -> Boolean
    //AND -> ALL (Todos cumplen) -> Boolean

    val respuestaAny: Boolean = arregloDinamico.any {
        valorActual: Int ->
            return@any valorActual > 5
    }

    println(respuestaAny) //true

    val respuestaAll: Boolean = arregloDinamico.all {
        valorActual: Int ->
            return@all valorActual > 5
    }

    println(respuestaAll) //false

    //REDUCE -> VALOR ACUMULADO
    //Valor acumulado = 0 (Siempre 0 en lenguaje Kotlin)
    //[1, 2, 3, 4, 5] -> Sumeme todos los valores del arreglo
    //valorIteracion1 = valorEmpieza + 1 = 0 + 1 = 1 -> Iteración 1
    //valorIteracion2 = valorIteracion1 + 2 = 1 + 2 = 3 -> Iteración 2
    //valorIteracion3 = valorIteracion2 + 3 = 3 + 3 = 6 -> Iteración 3
    //valorIteracion4 = valorIteracion3 + 4 = 6 + 4 = 10 -> Iteración 4
    //valorIteracion5 = valorIteracion4 + 5 = 10 + 5 = 15 -> Iteración 5

    val respuestaReduce: Int = arregloDinamico.reduce { //acumulado = 0 -> Siempre empieza en 0
        acumulado: Int, valorActual: Int ->
            return@reduce acumulado + valorActual //1
    }
    println(respuestaReduce) //55


}

//Funciones
//void -> Unit
fun imprimirNombre(nombre: String): Unit {
    println("Nombre: ${nombre}") //Template strings
}

fun calcularSueldo(
    sueldo: Double, //Requerido
    tasa: Double = 12.00, //Opcional (defecto)
    bonoEspecial: Double? = null, //Opción null -> nullable
): Double {
    if (bonoEspecial == null) {
        return sueldo * (100 / tasa)
    } else {
        return sueldo * (100 / tasa) + bonoEspecial
    }
}

//Clases Java
abstract class NumerosJava {
    protected val numeroUno: Int;
    private val numeroDos: Int;

    constructor(
        uno: Int,
        dos: Int
    ) {
        this.numeroUno = uno;
        this.numeroDos = dos;
        println("Inicializando")
    }
}

//Clases Kotlin
abstract class Numeros( //Constructor PRIMARIO
    //Ejemplo:
    //uno: Int (Parámetro (sin modificador de acceso))
    //private var uni: Int //Propiedad pública clase numeros.uno
    //var uno: Int //Propiedad de la clase (por defecto es PUBLIC)
    //public var uno: Int,
    protected val numeroUno: Int, //Propiedad de la clase protected numeros.numeroUno
    protected val numeroDos: Int //Propiedad de la clase protected numeros.numeroDos
) {
    //var cedula: String = "" (Public es por defecto)
    //private valorCalculado: Int = 0 (private)
    init {
        this.numeroUno; this.numeroDos;
        numeroUno; numeroDos;
        println("Inicializando")
    }
}

class Suma(
    unoParametro: Int,
    dosParametro: Int
) : Numeros(unoParametro, dosParametro) {//Extendiendo y mandando los parámetros (super)
    init {
        this.numeroUno
        this.numeroDos
    }

    constructor(//Segundo constructor
        uno: Int?,
        dos: Int
    ):this(
        if(uno == null) 0 else uno,
        dos
    )

    constructor(//Tercer constructor
        uno: Int,
        dos: Int?
    ):this(
        uno,
        if(dos == null) 0 else dos
    )

    constructor(//Cuarto constructor
        uno: Int?,
        dos: Int?
    ):this(
        if(uno == null) 0 else uno,
        if(dos == null) 0 else dos
    )

    public fun sumar(): Int{
        val total = this.numeroUno + numeroDos
        agregarHistorial(total)
        return total;
    }

    companion object{
        val pi = 3.14

        fun elevarAlCuadrado(num: Int): Int{
            return num * num
        }

        val historialSumas = ArrayList<Int>()

        fun agregarHistorial(valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma)
        }
    }
}