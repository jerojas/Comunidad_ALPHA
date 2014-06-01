/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Entidades;

/**
 *
 * @author Jeovany
 */
public class Predicador {
    
    String nombre, genero,estadocivil,fecha_nacimiento,direccion,telefono,celular,email,pais,fecha_ingreso;
    int cedula;

    public Predicador(int cedula, String nombre, String genero, String estadocivil, String fecha_nacimiento, String direccion, String telefono, String celular, String email, String pais, String fecha_ingreso) {
        this.nombre = nombre;
        this.genero = genero;
        this.estadocivil = estadocivil;
        this.fecha_nacimiento = fecha_nacimiento;
        this.direccion = direccion;
        this.telefono = telefono;
        this.celular = celular;
        this.email = email;
        this.pais = pais;
        this.fecha_ingreso = fecha_ingreso;
        this.cedula = cedula;
    }

  

    

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setEstadocivil(String estadocivil) {
        this.estadocivil = estadocivil;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public String getEstadocivil() {
        return estadocivil;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCelular() {
        return celular;
    }

    public String getEmail() {
        return email;
    }

    public String getPais() {
        return pais;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public int getCedula() {
        return cedula;
    }
    
    
    
}
