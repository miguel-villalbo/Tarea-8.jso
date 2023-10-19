/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
/**
 *
 * @author Usuario
 */
public class Puesto {
    private int id_Puesto;
    private String Puesto;
    private Conexion cn;
    public Puesto (){}
    public Puesto(int id_Puesto, String Puesto) {
        this.id_Puesto = id_Puesto;
        this.Puesto = Puesto;
    }

    public int getId_Puesto() {
        return id_Puesto;
    }

    public void setId_Puesto(int id_Puesto) {
        this.id_Puesto = id_Puesto;
    }

    public String getPuesto() {
        return Puesto;
    }

    public void setPuesto(String Puesto) {
        this.Puesto = Puesto;
    }
    
        public HashMap drop_Puesto(){
        HashMap<String,String> drop = new HashMap();
        try{
            cn = new Conexion();
            String query= "SELECT id_Puesto as id,Puesto FROM puestos;";
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()){
                drop.put(consulta.getString("id"), consulta.getString("Puesto"));
            }
            cn.cerrar_conexion();
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return drop;
    }
    
}
