/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import java.util.List;
import java.util.Optional;
import modelo.Identificable;

public interface Repositorio<T extends Identificable> {
    void guardar(T entidad);          // crea o actualiza
    Optional<T> buscarPorId(int id);
    List<T> listar();
    void eliminar(int id);
    int siguienteId();
}