
package com.todocodeacademy.prueba.controller;

import com.todocodeacademy.prueba.Cliente;
import com.todocodeacademy.prueba.Paciente;
import com.todocodeacademy.prueba.Plato;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    
    @GetMapping("/suma/{n1}/{n2}")
    public int suma(@PathVariable int n1, @PathVariable int n2) {
        return n1 + n2;
    }
    
    @GetMapping("/imc")
    public String imc(@RequestParam float peso) {
        String ret = "";
        if(peso<=18.5) {
            ret = "Insuficiente";
        }
        if(peso>=18.6 && peso<=24.9) {
            ret = "Normal";
        }
        if(peso>=25 && peso<=29.9) {
            ret = "Sobrepeso";
        }
        if(peso>=30) {
            ret = "Obesidad";
        }
        
        return "IMC: " + ret;        
    }
    
    @PostMapping("/cliente")
    public void nuevoCliente(@RequestBody Cliente cli) {
        System.out.println("Cliente creado");
        System.out.println("Id: " + cli.getId());
        System.out.println("Nombre: " + cli.getNombre());
        System.out.println("Apellido: " + cli.getApellido());
    }
    
    @GetMapping("/cliente/traer")
    @ResponseBody
    public List<Cliente> traerClientes() {
        List<Cliente> clientes = new ArrayList<Cliente>();
        clientes.add(new Cliente(1L,"Cristiano", "Ronaldo"));
        clientes.add(new Cliente(2L,"Lionel", "Messi"));
        clientes.add(new Cliente(3L,"Carlos", "Tevez"));
        
        return clientes;
    }
    
    @GetMapping("/pruebaresponse")
    ResponseEntity<String> traerRespuesta() {
        return new ResponseEntity<>("Esto es un mensaje Response entity", HttpStatus.NOT_FOUND);
    }
    
    @GetMapping("/platos/traer")
    @ResponseBody
    public List<Plato> traerPlatos() {
        List<Plato> platos = new ArrayList<Plato>();
        platos.add(new Plato(1L,"Milanesa",2500,"Desc1"));
        platos.add(new Plato(2L,"Carne",3500,"Desc2"));
        platos.add(new Plato(3L,"Ravioles",3000,"Desc3"));
        
        return platos;
    }
    
    @GetMapping("/plato/traer")
    @ResponseBody
    public Plato traerPlato(@RequestParam Long id) {
        Plato plato = null;
        List<Plato> platos = traerPlatos();
        for(Plato p: platos) {
            if( p.getId()==id) {
                plato = p;
            }
        }
        
        return plato;
    }
    
    @GetMapping("/pacientes/traer")
    @ResponseBody
    public List<Paciente> traerPacientes() {
        List<Paciente> pacientes = new ArrayList<Paciente>();
        LocalDate fecha = LocalDate.parse("1980-12-23");
        pacientes.add(new Paciente(1L,"12345678","Carlos","Gomez",fecha));
        fecha = LocalDate.parse("1985-11-13");
        pacientes.add(new Paciente(2L,"11111111","Maria","Lopez",fecha));
        fecha = LocalDate.parse("1993-09-05");
        pacientes.add(new Paciente(3L,"22222222","Pedro","Garcia",fecha));
        fecha = LocalDate.parse("2010-09-05");
        pacientes.add(new Paciente(4L,"12312334","Jorge","PEPE",fecha));
        fecha = LocalDate.parse("2015-09-05");
        pacientes.add(new Paciente(5L,"83759545","Celeste","Garcia",fecha));
        
        return pacientes;
    }
    
    @GetMapping("/pacientes/traer/menores")
    @ResponseBody
    public List<Paciente> traerPacientesMenores() {
        List<Paciente> pacienteMenores = new ArrayList<>();
        
        List<Paciente> pacientes = traerPacientes();
        
        for(Paciente p: pacientes) {
            if( esMenor(p.getFechaNac()) ) {
                pacienteMenores.add(p);
            }
        }
        
        return pacienteMenores;
    }

    private boolean esMenor(LocalDate fechaNac) {
        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(fechaNac, fechaActual);
        int edad = periodo.getYears();

        // Definir la edad mínima para ser considerado mayor de edad (por ejemplo, 18)
        int edadMinima = 18;

        return edad < edadMinima;
    }
    
}
