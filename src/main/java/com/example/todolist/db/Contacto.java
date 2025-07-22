package com.example.todolist.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

import com.example.todolist.dtos.ContactoDTO;

public class Contacto {
    private Integer id;
    private String nombre;
    private String celular;
    private String correo;

    private Contacto(Integer id, String nombre, String celular, String correo){
        this.id = id;
        this.nombre = nombre;
        this.celular = celular;
        this.correo = correo;
    }
    public static ArrayList<Contacto> getAll() throws SQLException{
        ArrayList<Contacto> retorno = new ArrayList<>();
        Connection cx = Coneccion.Conectar();
        String sql = """
                select * from contacto;
                """;
        Statement st = cx.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while(rs.next()){
            retorno.add(new Contacto(rs.getInt(1), rs.getString(2),rs.getString(3), rs.getString(4)));
        }

        return retorno;
    }

    public static Contacto getContacto(Integer id) throws Exception{
        Connection cx = Coneccion.Conectar();
        Contacto retorno = null;
        String sql = """
                select * from contacto where id = ?;
                """;
        PreparedStatement pst = cx.prepareStatement(sql);
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();

        while(rs.next()){
            retorno = new Contacto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
        }
        rs.close();
        pst.close();
        cx.close();
        if(Objects.isNull(retorno)){
            throw new Exception("Contacto no disponible");
        }
        return retorno;
    }
    public ContactoDTO toDTO(){
        return new ContactoDTO(this.id, this.nombre, this.celular, this.correo);
    }
    public static Contacto crearContacto(String nombre, String celular, String correo) throws SQLException{
        Connection cx = Coneccion.Conectar();
        String sqlstr = """
                insert into contacto (nombre,celular,correo) values (?,?,?);
                """;
        PreparedStatement pst = cx.prepareStatement(sqlstr);
        pst.setString(1, nombre);
        pst.setString(2, celular);
        pst.setString(3, correo);
        pst.executeUpdate();

        ResultSet rs = pst.getGeneratedKeys();
        Integer id = null;
        while(rs.next()){
            id = rs.getInt(1);
        };

        rs.close();
        pst.close();
        cx.close();

        return new Contacto(id, nombre, celular, correo);
    }
    public static Contacto crearContacto(ContactoDTO contacto) throws SQLException{
        return crearContacto(contacto.getNombre(), contacto.getCelular(), contacto.getCorreo());
    }

    public static void deleteContacto(Integer id) throws SQLException{
        String sql = """
                delete from contacto where id = ?;
                """;
        Connection cx = Coneccion.Conectar();
        PreparedStatement pst = cx.prepareStatement(sql);
        pst.setInt(1, id);
        pst.executeUpdate();

        pst.close();
        cx.close();
        

    }


    public Integer getId() {
        return id;
    }



    public String getNombre() {
        return nombre;
    }



    public String getCelular() {
        return celular;
    }



    public String getCorreo() {
        return correo;
    }

    

    public void setNombre(String nombre) throws  SQLException{
        String sql = """
                update contacto set nombre = ? where id = ?;
                """;
        Connection cx = Coneccion.Conectar();
        PreparedStatement pst = cx.prepareStatement(sql);
        pst.setString(1, nombre);
        pst.setInt(2, this.id);
        pst.executeUpdate();
        pst.close();
        cx.close();
        this.nombre = nombre;
    }
    public void setCelular(String celular) throws SQLException{
        String sql = """
                update contacto set celular = ? where id = ?;
                """;
        Connection cx = Coneccion.Conectar();
        PreparedStatement pst = cx.prepareStatement(sql);
        pst.setString(1, celular);
        pst.setInt(2, this.id);
        pst.executeUpdate();
        pst.close();
        cx.close();
        this.celular = celular;
    }
    public void setCorreo(String correo) throws SQLException{
        String sql = """
                update contacto set correo = ? where id = ?;
                """;
        Connection cx = Coneccion.Conectar();
        PreparedStatement pst = cx.prepareStatement(sql);
        pst.setString(1, correo);
        pst.setInt(2, this.id);
        pst.executeUpdate();
        pst.close();
        cx.close();
        this.correo = correo;
    }
    @Override
    public String toString() {
        return "Contacto [id=" + id + ", nombre=" + nombre + ", celular=" + celular + ", correo=" + correo + "]";
    }

    

}
