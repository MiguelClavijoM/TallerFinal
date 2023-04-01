package org.example.Servicios;

import org.example.Entidades.Usuarios;
import org.example.Repos.ICrud;
import org.example.Repos.UsuarioRepo;

import java.util.List;
import java.util.Map;

public class UsuariosServicio {
  private ICrud repositorioUsuario;

  public UsuariosServicio() {
    repositorioUsuario = new UsuarioRepo();
  }

  public void guardarUsuarios(Map datos) {
    String nombre = (String) datos.get("nombre");
    String apellido = (String) datos.get("apellido");
    String cedula = (String) datos.get("cedula");

    Usuarios nuevoUsuario = new Usuarios(nombre, apellido, cedula);
    repositorioUsuario.guardar(nuevoUsuario);
  }

  public List<Usuarios> listarUsuarios() {
    return (List<Usuarios>) repositorioUsuario.listar();
  }

  public Usuarios buscarUsuarios(String identificador) throws Exception {
    Object usuario = repositorioUsuario.buscar(identificador);
    if (usuario == null) {
      throw new Exception("No se encontro el usuario");
    }
    return (Usuarios) usuario;
  }

  public void eliminarUsuarios(String identificador) {
    repositorioUsuario.eliminar(identificador);
  }

  public void actualizarUsuarios(Map datos) {
    String nombre = (String) datos.get("nombre");
    String apellido = (String) datos.get("apellido");
    String cedula = (String) datos.get("cedula");

    Usuarios nuevoUsuario = new Usuarios(nombre, apellido, cedula);
    repositorioUsuario.actualizar(nuevoUsuario);
  }

  public void actualizarUsuariosId(Map datos, String id) {
    String nombre = (String) datos.get("nombre");
    String apellido = (String) datos.get("apellido");
    String cedula = (String) datos.get("cedula");

    Usuarios nuevoUsuario = new Usuarios(nombre, apellido, cedula);
    repositorioUsuario.actualizarId(nuevoUsuario, id);
  }
}
