package com.example.todolist.controllers;

import com.example.todolist.db.Contacto;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.todolist.dtos.ContactoDTO;

@RestController
public class ContactoC {

    @GetMapping("/contacto")
    public ResponseEntity<Object> getMethodName() {
        try {
            ArrayList<ContactoDTO> retorno = Contacto.getAll().stream().map(e -> {
                return e.toDTO();
            }).collect(Collectors.toCollection(ArrayList::new));
            return new ResponseEntity<Object>(retorno, HttpStatus.OK);
        } catch (SQLException e) {
            Map<String, String> error = new HashMap<>();
            error.put("Error", e.getMessage());
            return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/contacto/{id}")
    public ResponseEntity getMethodName(@PathVariable Integer id) {
        try {
            ContactoDTO retorno = Contacto.getContacto(id).toDTO();
            return new ResponseEntity(retorno, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("Mensaje", e.getMessage());
            return new ResponseEntity(error, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/contacto")
    public ResponseEntity postMethodName(@RequestBody ContactoDTO dato) {
        try {
            if (Objects.isNull(dato.getCelular()) || Objects.isNull(dato.getNombre())
                    || Objects.isNull(dato.getCorreo())) {
                throw new Exception("Solicitud con cuerpo invalido");
            }
            ContactoDTO nuevo = Contacto.crearContacto(dato).toDTO();
            return new ResponseEntity(nuevo, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("Mensaje", e.getMessage());
            return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PatchMapping("/contacto/{id}")
    public ResponseEntity parche(@PathVariable Integer id, @RequestBody Map<String, Object> datos) {
        try {
            Contacto contacto1 = Contacto.getContacto(id);
            for (String key : datos.keySet()) {
                switch (key) {
                    case "nombre":
                        contacto1.setNombre(datos.get(key).toString());
                        break;
                    case "celular":
                        contacto1.setCelular(datos.get(key).toString());
                        break;
                    case "correo":
                        contacto1.setCelular(datos.get(key).toString());
                        break;
                    default:
                        break;
                }
            }
            return new ResponseEntity(contacto1.toDTO(), HttpStatus.OK);

        } catch (Exception e) {
            Map<String,String> error = new HashMap<>();
            error.put("Mensaje", e.getMessage());

            return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/contacto/{id}")
    public ResponseEntity postMethodName(@PathVariable Integer id, @RequestBody ContactoDTO actualizacion) {
        try {
            if(Objects.isNull(actualizacion.getNombre()) || Objects.isNull(actualizacion.getCelular())|| Objects.isNull(actualizacion.getCorreo())){
             throw new Exception("Cuerpo de solicitud incompleta");
            }
        Contacto con = Contacto.getContacto(id);
        con.setNombre(actualizacion.getNombre());
        con.setCelular(actualizacion.getCelular());
        con.setCorreo(actualizacion.getCorreo());

        return new ResponseEntity(con.toDTO(), HttpStatus.OK);
        } catch (Exception e) {
            Map<String,String> error = new HashMap<>();
            error.put("Mensaje", e.getMessage());
            return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
