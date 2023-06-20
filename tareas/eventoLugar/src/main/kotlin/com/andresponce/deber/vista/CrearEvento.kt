/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package eventoLugar.vista

import com.andresponce.deber.configuracion.EstandarTiempo
import com.andresponce.deber.dao.DaoFactory
import com.andresponce.deber.modelo.Evento
import java.awt.EventQueue
import java.awt.event.ActionEvent
import java.time.LocalDate
import java.time.LocalTime
import java.util.logging.Level
import java.util.logging.Logger
import javax.swing.*

/**
 *
 * @author escritorio.virtual20
 */
class CrearEvento(): JFrame() {
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    private var parent: JFrame? = null
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private fun initComponents() {
        jLabel1 = JLabel()
        jTextFieldNombre = JTextField()
        jLabel2 = JLabel()
        jTextFieldDescripcion = JTextField()
        jLabel3 = JLabel()
        jLabel4 = JLabel()
        jTextFieldFecha = JTextField()
        jLabel5 = JLabel()
        jTextFieldHora = JTextField()
        jComboBoxLugar = JComboBox()
        jLabel6 = JLabel()
        jTextFieldDuracion = JTextField()
        jTextFieldPrecio = JTextField()
        jLabel7 = JLabel()
        jButtonCrearEvento = JButton()
        defaultCloseOperation = DISPOSE_ON_CLOSE
        jLabel1!!.text = "Nombre:"
        jLabel2!!.text = "Descripción:"
        jLabel3!!.text = "Lugar:"
        jLabel4!!.text = "Fecha (yyyy-mm-dd):"
        jLabel5!!.text = "Hora de inicio (hh:mm:ss):"
        jLabel6!!.text = "Duración (hh:mm:ss):"
        jLabel7!!.text = "Precio de entrada:"
        jButtonCrearEvento!!.text = "Crear"
        jButtonCrearEvento!!.addActionListener { evt -> jButtonCrearEventoActionPerformed(evt) }
        val layout = GroupLayout(contentPane)
        contentPane.layout = layout
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(
                    layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(
                                    layout.createSequentialGroup()
                                        .addGroup(
                                            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel1)
                                                .addComponent(jLabel3)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel7)
                                                .addComponent(jLabel6)
                                        )
                                        .addGap(24, 24, 24)
                                        .addGroup(
                                            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(jTextFieldDescripcion, GroupLayout.Alignment.TRAILING)
                                                .addComponent(
                                                    jComboBoxLugar,
                                                    GroupLayout.Alignment.TRAILING,
                                                    0,
                                                    GroupLayout.DEFAULT_SIZE,
                                                    Short.MAX_VALUE.toInt()
                                                )
                                                .addComponent(jTextFieldFecha, GroupLayout.Alignment.TRAILING)
                                                .addComponent(jTextFieldHora)
                                                .addComponent(jTextFieldNombre)
                                                .addComponent(jTextFieldDuracion)
                                                .addComponent(jTextFieldPrecio)
                                        )
                                )
                        )
                        .addContainerGap()
                )
                .addGroup(
                    layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(jButtonCrearEvento)
                        .addContainerGap(168, Short.MAX_VALUE.toInt())
                )
        )
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(
                    layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                            layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(
                                    jTextFieldNombre,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.PREFERRED_SIZE
                                )
                        )
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(
                            layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(
                                    jTextFieldDescripcion,
                                    GroupLayout.PREFERRED_SIZE,
                                    79,
                                    GroupLayout.PREFERRED_SIZE
                                )
                        )
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(
                            layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(
                                    jComboBoxLugar,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.PREFERRED_SIZE
                                )
                        )
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(
                            layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(
                                    jTextFieldFecha,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.PREFERRED_SIZE
                                )
                        )
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(
                            layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(
                                    jTextFieldHora,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.PREFERRED_SIZE
                                )
                        )
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(
                            layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(
                                    jTextFieldDuracion,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.PREFERRED_SIZE
                                )
                        )
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(
                            layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel7)
                                .addComponent(
                                    jTextFieldPrecio,
                                    GroupLayout.PREFERRED_SIZE,
                                    GroupLayout.DEFAULT_SIZE,
                                    GroupLayout.PREFERRED_SIZE
                                )
                        )
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonCrearEvento)
                        .addContainerGap(12, Short.MAX_VALUE.toInt())
                )
        )
        pack()
    } // </editor-fold>//GEN-END:initComponents

    private fun jButtonCrearEventoActionPerformed(evt: ActionEvent) { //GEN-FIRST:event_jButtonCrearEventoActionPerformed
        try {
            val nombre = jTextFieldNombre!!.text
            val descripcion = jTextFieldDescripcion!!.text
            val lugarID = (jComboBoxLugar!!.selectedItem as String).split(" - ")[0].toInt()
            val lugar = DaoFactory.daoFactory.getLugarDao().obtenerPorId(lugarID)
            val fecha = LocalDate.parse(jTextFieldFecha!!.text, EstandarTiempo.FORMATO_FECHA)
            val hora = LocalTime.parse(jTextFieldHora!!.text, EstandarTiempo.FORMATO_HORA)
            val duracion = LocalTime.parse(jTextFieldDuracion!!.text, EstandarTiempo.FORMATO_HORA)
            val precio = jTextFieldPrecio!!.text.toDouble()
            val evento = lugar?.let {
                Evento(
                    nombre = nombre,
                    descripcion = descripcion,
                    lugar = it,
                    fecha = fecha,
                    horaInicio = hora,
                    duracion = duracion,
                    precioDeEntrada = precio
                )
            }

            DaoFactory.daoFactory.getEventoDao().insertar(evento!!)

            JOptionPane.showMessageDialog(
                null,
                "Evento creado correctamente",
                "Evento creado",
                JOptionPane.INFORMATION_MESSAGE
            )
            (this.parent as PantallaPrincipal).actualizarTablas()
            dispose()
        }catch (e: Exception){
            JOptionPane.showMessageDialog(this, "Error al crear el evento\n${e.message}", "Error", JOptionPane.ERROR_MESSAGE)
        }
    }

    private fun actualizarLugares() {
        val lugares = DaoFactory.daoFactory.getLugarDao().obtenerTodos()
        jComboBoxLugar!!.removeAllItems()
        lugares.forEach { lugar -> jComboBoxLugar!!.addItem("${lugar.id} - ${lugar.nombre}") }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private var jButtonCrearEvento: JButton? = null
    private var jComboBoxLugar: JComboBox<String>? = null
    private var jLabel1: JLabel? = null
    private var jLabel2: JLabel? = null
    private var jLabel3: JLabel? = null
    private var jLabel4: JLabel? = null
    private var jLabel5: JLabel? = null
    private var jLabel6: JLabel? = null
    private var jLabel7: JLabel? = null
    private var jTextFieldDescripcion: JTextField? = null
    private var jTextFieldDuracion: JTextField? = null
    private var jTextFieldFecha: JTextField? = null
    private var jTextFieldHora: JTextField? = null
    private var jTextFieldNombre: JTextField? = null
    private var jTextFieldPrecio: JTextField? = null // End of variables declaration//GEN-END:variables

    /**
     * Creates new form CrearEvento
     */
    init {
        initComponents()
        actualizarLugares()
    }

    constructor(parent: JFrame?) : this() {
        this.parent = parent
    }

    companion object {
        /**
         * @param args the command line arguments
         */
        @JvmStatic
        fun main(args: Array<String>) {
            /* Set the Nimbus look and feel */
            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
            /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
            try {
                for (info in UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus" == info.name) {
                        UIManager.setLookAndFeel(info.className)
                        break
                    }
                }
            } catch (ex: ClassNotFoundException) {
                Logger.getLogger(CrearEvento::class.java.name).log(Level.SEVERE, null, ex)
            } catch (ex: InstantiationException) {
                Logger.getLogger(CrearEvento::class.java.name).log(Level.SEVERE, null, ex)
            } catch (ex: IllegalAccessException) {
                Logger.getLogger(CrearEvento::class.java.name).log(Level.SEVERE, null, ex)
            } catch (ex: UnsupportedLookAndFeelException) {
                Logger.getLogger(CrearEvento::class.java.name).log(Level.SEVERE, null, ex)
            }
            //</editor-fold>

            /* Create and display the form */EventQueue.invokeLater { CrearEvento().isVisible = true }
        }
    }
}