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
public class Seminario {
    
     String nombre, fecha, hora_inicio, hora_fin, observaciones, fecha_creacion ;
    int idseminario, predicador;
    private String descripcionPredicador;

    public Seminario(int idseminario, String nombre, String fecha, String hora_inicio, String hora_fin, String observaciones, int predicador, String fecha_creacion) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.observaciones = observaciones;
        this.fecha_creacion = fecha_creacion;
        this.idseminario = idseminario;
        this.predicador = predicador;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public void setIdseminario(int idseminario) {
        this.idseminario = idseminario;
    }

    public void setPredicador(int predicador) {
        this.predicador = predicador;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public int getIdseminario() {
        return idseminario;
    }

    public int getPredicador() {
        return predicador;
    }

     public String getDescripcionPredicador() {
        return descripcionPredicador;
    }

    public void setDescripcionPredicador(String descripcionPredicador) {
        this.descripcionPredicador = descripcionPredicador;
    }
    
}
