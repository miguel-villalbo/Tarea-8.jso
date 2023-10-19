/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Usuario
 */
public class Empleado extends persona{
    private String codigo;
    private int id_Puesto;
    private Conexion cn;
    

    public Empleado() {
    }

    public Empleado(String codigo, int id_Puesto, int id, String nombres, String apellidos, String direccion, String telefono, String fecha_nacimiento) {
        super(id, nombres, apellidos, direccion, telefono, fecha_nacimiento);
        this.codigo = codigo;
        this.id_Puesto = id_Puesto;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getId_Puesto() {
        return id_Puesto;
    }

    public void setId_Puesto(int id_Puesto) {
        this.id_Puesto = id_Puesto;
    }
public DefaultTableModel leer(){
DefaultTableModel tabla = new DefaultTableModel();
try{
    cn = new Conexion();
    cn.abrir_conexion();
    String query ="SELECT e.id_Empleados as id,e.Codigo,e.Nombres,e.Apellidos,e.Dirección,e.Telefono,e.Fecha_Nacimiento,p.Puesto,p.id_Puesto FROM empleados as e, puestos as p where e.id_Puesto = p.id_Puesto;";
    ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
    String encabezado[] = {"id","codigo","nombres","apellidos","direccion","telefono","nacimiento","Puesto","id_Puesto"};
    tabla.setColumnIdentifiers(encabezado);
    String datos[] = new String[9];
    while (consulta.next()){
        datos[0] = consulta.getString("id");
        datos[1] = consulta.getString("codigo");
        datos[2] = consulta.getString("nombres");
        datos[3] = consulta.getString("apellidos");
        datos[4] = consulta.getString("direccion");
        datos[5] = consulta.getString("telefono");
        datos[6] = consulta.getString("fecha_nacimiento");
        datos[7] = consulta.getString("Puesto");
        datos[8] = consulta.getString("id_Puesto");
        tabla.addRow(datos);
    }
    cn.cerrar_conexion();
}catch(SQLException ex){
    System.out.println(ex.getMessage());
}
return tabla;
}
    @Override
    public int agregar(){
        int retorno = 0;
    try{
        PreparedStatement parametro;
        cn = new Conexion();
        String query="INSERT INTO empleados(Codigo,Nombres,Apellidos,Dirección,Telefono,Fecha_Nacimiento,id_Puesto)VALUES(?,?,?,?,?,?,?);";
        cn.abrir_conexion();
        parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
        parametro.setString(1, getCodigo());
        parametro.setString(2, getNombres());
        parametro.setString(3, getApellidos());
        parametro.setString(4, getDireccion());
        parametro.setString(5, getTelefono());
        parametro.setString(6, getFecha_nacimiento());
        parametro.setInt(7, getId_Puesto());
        
        retorno =parametro.executeUpdate();
        cn.cerrar_conexion();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
        retorno = 0;
    }
    return retorno;
    }
    @Override
    public int modificar (){
        int retorno = 0;
    try{
        PreparedStatement parametro;
        cn = new Conexion();
        String query="UPDATE empleados set Codigo=?,Nombres=?,Apellidos=?,Dirección=?,Telefono=?,Fecha_Nacimiento=?,id_Puesto=? where id_empleado = ?;";
        cn.abrir_conexion();
        parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
        parametro.setString(1, getCodigo());
        parametro.setString(2, getNombres());
        parametro.setString(3, getApellidos());
        parametro.setString(4, getDireccion());
        parametro.setString(5, getTelefono());
        parametro.setString(6, getFecha_nacimiento());
        parametro.setInt(7, getId_Puesto());
        parametro.setInt(8, getId());
        retorno =parametro.executeUpdate();
        cn.cerrar_conexion();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
        retorno = 0;
    }
    return retorno;
    }
    @Override
    public int eliminar (){
    int retorno = 0;
    try{
        PreparedStatement parametro;
        cn = new Conexion();
        String query="DELETE from empleados where id_empleado = ?;";
        cn.abrir_conexion();
        parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
        parametro.setInt(1, getId());
        retorno =parametro.executeUpdate();
        cn.cerrar_conexion();
    }catch(SQLException ex){
        System.out.println(ex.getMessage());
        retorno = 0;
    }
    return retorno;
    }
            
}
