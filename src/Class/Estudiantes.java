/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author nacho
 */
public class Estudiantes {
    ConexionDB cn;
    private String carnet,nombres,apellidos,direccion,telefono,genero,email,fecha_nacimiento;
    private int id;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }
    
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn=new ConexionDB();
            cn.abrir_conexion();
            String query;
            query="Select id_estudiante as id,carnet,nombres,apellidos,direccion,telefono,genero,email,fecha_nacimiento from estudiantes;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            
            String encabezado[]={"ID","CARNET","NOMBRES","APELLIDOS","DIRECCION","TELEFONO","GENERO","EMAIL","NACIMIENTO"};
            tabla.setColumnIdentifiers(encabezado);
            
            String datos[]=new String[9];
            while(consulta.next()){
                datos[0]=consulta.getString("id");
                datos[1]=consulta.getString("carnet");
                datos[2]=consulta.getString("nombres");
                datos[3]=consulta.getString("apellidos");
                datos[4]=consulta.getString("direccion");
                datos[5]=consulta.getString("telefono");
                datos[6]=consulta.getString("genero");
                datos[7]=consulta.getString("email");
                datos[8]=consulta.getString("fecha_nacimiento");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
            
        }catch(SQLException ex){
            System.out.println("Algo salio mal... "+ex.getMessage());
        }
        return tabla;
    }
    
    public void agregar(){
        try{
            PreparedStatement parametro;
            String query="INSERT INTO db_parcial2.estudiantes(carnet,nombres,apellidos,direccion,telefono,genero,email,fecha_nacimiento) VALUES(?,?,?,?,?,?,?,?);";
            cn=new ConexionDB();
            cn.abrir_conexion();
            parametro=(PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getCarnet());
            parametro.setString(2,getNombres());
            parametro.setString(3,getApellidos());
            parametro.setString(4,getDireccion());
            parametro.setString(5,getTelefono());
            parametro.setString(6,getGenero());
            parametro.setString(7,getEmail());
            parametro.setString(8,getFecha_nacimiento());
            
            int executar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null,Integer.toString(executar)+"Datos ingresados correctamente...","Añadir",JOptionPane.INFORMATION_MESSAGE);
            
        }catch(HeadlessException | SQLException ex){
            System.out.println("Algo salió mal..."+ex.getMessage());
        }
    }
    public void modificar(){
        try{
            PreparedStatement parametro;
            String query="UPDATE db_parcial2.estudiantes set carnet=?,nombres=?,apellidos=?,direccion=?,telefono=?,genero=?,email=?,fecha_nacimiento=?"+"where id_estudiante=?";
            cn=new ConexionDB();
            cn.abrir_conexion();
            parametro=(PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getCarnet());
            parametro.setString(2,getNombres());
            parametro.setString(3,getApellidos());
            parametro.setString(4,getDireccion());
            parametro.setString(5,getTelefono());
            parametro.setString(6,getGenero());
            parametro.setString(7,getEmail());
            parametro.setString(8,getFecha_nacimiento());
            parametro.setInt(9,getId());
            
            int executar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null,Integer.toString(executar)+"Datos modificados correctamente...","Modificar",JOptionPane.INFORMATION_MESSAGE);
            
        }catch(HeadlessException | SQLException ex){
            System.out.println("Algo salió mal..."+ex.getMessage());
        }
    }
    public void eliminar(){
        try{
            PreparedStatement parametro;
            String query="DELETE FROM db_parcial2.estudiantes where id_estudiante=?";
            cn=new ConexionDB();
            cn.abrir_conexion();
            parametro=(PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1,getId());
            
            int executar = parametro.executeUpdate();
            cn.cerrar_conexion();
            JOptionPane.showMessageDialog(null,Integer.toString(executar)+" Datos eliminados correctamente...","Eliminar",JOptionPane.INFORMATION_MESSAGE);
            
        }catch(HeadlessException | SQLException ex){
            System.out.println("Algo salió mal..."+ex.getMessage());
        }
    }
}
