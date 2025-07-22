package com.example.todolist.dtos;

public class ContactoDTO {
private Integer id;
private String nombre;
private String celular;
private String correo;

public ContactoDTO (){

}

public ContactoDTO(String nombre, String celular, String correo){
    this.id = null;
    this.nombre = nombre;
    this.celular = celular;
    this.correo = correo;

}

public ContactoDTO(Integer id,String nombre, String celular, String correo){
    this.id = id;
    this.nombre = nombre;
    this.celular = celular;
    this.correo = correo;

}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }


}
