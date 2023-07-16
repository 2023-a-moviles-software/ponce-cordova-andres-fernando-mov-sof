import com.andresponce.deber.dao.DaoFactory
import eventoLugar.vista.PantallaPrincipal
import java.math.BigDecimal

fun main(){
    /*val pantallaPrincipal = PantallaPrincipal().apply {
        isVisible = true
    }*/

    for(lugar in DaoFactory.daoFactory.getLugarDao().obtenerTodos()){
        println(lugar.nombre)
    }
}