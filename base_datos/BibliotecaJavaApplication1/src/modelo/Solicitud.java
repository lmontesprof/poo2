/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.time.LocalDate;

public class Solicitud implements Identificable {

    private final int id;
    private final int idLibro;
    private final String solicitante;
    private final LocalDate fecha;
    private Estado estado;

    // Solicitud nueva
    public Solicitud(int id, int idLibro, String solicitante) {
        this(id, idLibro, solicitante, LocalDate.now(), Estado.PENDIENTE);
    }

    // Solicitud reconstruida desde la base de datos
    public Solicitud(int id, int idLibro, String solicitante, LocalDate fecha, Estado estado) {
        this.id = id;
        this.idLibro = idLibro;
        this.solicitante = solicitante;
        this.fecha = fecha;
        this.estado = estado;
    }

    @Override public int getId() { return id; }
    public int getIdLibro() { return idLibro; }
    public String getSolicitante() { return solicitante; }
    public LocalDate getFecha() { return fecha; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
}