/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicio;

import java.util.*;
import java.util.stream.Collectors;
import modelo.*;
import persistencia.Repositorio;

public class BibliotecaServicio {

    private final Repositorio<Libro> libros;
    private final Repositorio<Solicitud> solicitudes;

    public BibliotecaServicio(Repositorio<Libro> libros, Repositorio<Solicitud> solicitudes) {
        this.libros = libros;
        this.solicitudes = solicitudes;
    }

    // ---------- LIBROS ----------
    public Libro crearLibro(String titulo, String autor, String categoria) {
        requerido(titulo, "El título es obligatorio");
        requerido(autor, "El autor es obligatorio");
        Libro libro = new Libro(libros.siguienteId(), titulo.trim(), autor.trim(), categoria);
        libros.guardar(libro);
        return libro;
    }

    public void actualizarLibro(int id, String titulo, String autor, String categoria) {
        requerido(titulo, "El título es obligatorio");
        requerido(autor, "El autor es obligatorio");
        Libro libro = libros.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Libro no encontrado"));
        libro.setTitulo(titulo.trim());
        libro.setAutor(autor.trim());
        libro.setCategoria(categoria);
        libros.guardar(libro);
    }

    public void eliminarLibro(int id) {
        boolean enUso = solicitudes.listar().stream()
                .anyMatch(s -> s.getIdLibro() == id
                        && (s.getEstado() == Estado.PENDIENTE || s.getEstado() == Estado.APROBADA));
        if (enUso) throw new IllegalStateException("El libro tiene solicitudes activas");
        libros.eliminar(id);
    }

    public List<Libro> listarLibros() {
        return buscarLibros("");
    }

    public List<Libro> buscarLibros(String texto) {
        String q = texto == null ? "" : texto.trim().toLowerCase();
        return libros.listar().stream()
                .filter(l -> l.getTitulo().toLowerCase().contains(q)
                          || l.getAutor().toLowerCase().contains(q))
                .sorted(Comparator.comparing(Libro::getTitulo, String.CASE_INSENSITIVE_ORDER))
                .collect(Collectors.toList());
    }

    public String tituloDeLibro(int idLibro) {
        return libros.buscarPorId(idLibro).map(Libro::getTitulo).orElse("(eliminado)");
    }

    // ---------- SOLICITUDES ----------
    public Solicitud crearSolicitud(int idLibro, String solicitante) {
        requerido(solicitante, "El solicitante es obligatorio");
        Libro libro = libros.buscarPorId(idLibro)
                .orElseThrow(() -> new NoSuchElementException("Libro no encontrado"));
        if (!libro.isDisponible()) throw new IllegalStateException("El libro no está disponible");
        Solicitud s = new Solicitud(solicitudes.siguienteId(), idLibro, solicitante.trim());
        solicitudes.guardar(s);
        return s;
    }

    public void cambiarEstado(int idSolicitud, Estado nuevo) {
        Solicitud s = solicitudes.buscarPorId(idSolicitud)
                .orElseThrow(() -> new NoSuchElementException("Solicitud no encontrada"));
        Libro libro = libros.buscarPorId(s.getIdLibro())
                .orElseThrow(() -> new NoSuchElementException("El libro ya no existe"));

        switch (nuevo) {
            case APROBADA:
                if (s.getEstado() != Estado.PENDIENTE)
                    throw new IllegalStateException("Solo se aprueban solicitudes pendientes");
                if (!libro.isDisponible())
                    throw new IllegalStateException("El libro ya fue prestado");
                libro.setDisponible(false);
                break;
            case RECHAZADA:
                if (s.getEstado() != Estado.PENDIENTE)
                    throw new IllegalStateException("Solo se rechazan solicitudes pendientes");
                break;
            case DEVUELTA:
                if (s.getEstado() != Estado.APROBADA)
                    throw new IllegalStateException("Solo se devuelven préstamos aprobados");
                libro.setDisponible(true);
                break;
            default:
                throw new IllegalArgumentException("Estado no permitido");
        }
        s.setEstado(nuevo);
        libros.guardar(libro);
        solicitudes.guardar(s);
    }

    public void eliminarSolicitud(int id) {
        Solicitud s = solicitudes.buscarPorId(id)
                .orElseThrow(() -> new NoSuchElementException("Solicitud no encontrada"));
        if (s.getEstado() == Estado.APROBADA)
            throw new IllegalStateException("Primero registra la devolución");
        solicitudes.eliminar(id);
    }

    public List<Solicitud> listarSolicitudes() {
        return solicitudes.listar().stream()
                .sorted(Comparator.comparingInt(Solicitud::getId).reversed())
                .collect(Collectors.toList());
    }

    public Map<Estado, Long> resumenPorEstado() {
        return solicitudes.listar().stream()
                .collect(Collectors.groupingBy(Solicitud::getEstado,
                        () -> new EnumMap<>(Estado.class), Collectors.counting()));
    }

    private void requerido(String valor, String mensaje) {
        if (valor == null || valor.trim().isEmpty()) throw new IllegalArgumentException(mensaje);
    }
}