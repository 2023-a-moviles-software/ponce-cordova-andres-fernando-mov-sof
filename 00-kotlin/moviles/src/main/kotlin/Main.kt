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
){
    //var cedula: String = "" (Public es por defecto)
    //private valorCalculado: Int = 0 (private)
    init {
        this.numeroUno; this.numeroDos;
        numeroUno; numeroDos;
        println("Inicializando")
    }
}