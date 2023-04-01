package org.example.Servicios;

import org.example.Entidades.Transacciones;
import org.example.Entidades.Usuarios;
import org.example.Repos.ICrud;
import org.example.Repos.UsuarioRepo;

import java.util.List;
import java.util.Map;

public class TransaccionesServicio {
  private ICrud repositorioTransacciones;

  public TransaccionesServicio() {
    repositorioTransacciones = new UsuarioRepo();
  }

  public void guardarTransacciones(Map datos) {
    String nombre = (String) datos.get("nombre");
    String fecha = (String) datos.get("fecha");
    String hora = (String) datos.get("hora");
    String tipo_transaccion = (String) datos.get("tipo_transaccion");
    Double monto = (Double) datos.get("monto");
    String id_cuenta = (String) datos.get("id_cuenta");
    String tipo_cuentaDestino = (String) datos.get("tipo_cuentaDestino");

    Transacciones nuevaTransaccion = new Transacciones(nombre, fecha, hora,tipo_transaccion, monto, id_cuenta, tipo_cuentaDestino);
    repositorioTransacciones.guardar(nuevaTransaccion);
  }

  public List<Transacciones> listarTrnsacciones() {
    return (List<Transacciones>) repositorioTransacciones.listar();
  }

  public Transacciones buscarTransaccion(String identificador) throws Exception {
    Object transaccion = repositorioTransacciones.buscar(identificador);
    if (transaccion == null) {
      throw new Exception("No se encontro la transaccion");
    }
    return (Transacciones) transaccion;
  }

  public void eliminarTransacciones(String identificador) {
    repositorioTransacciones.eliminar(identificador);
  }

  public void actualizarTransacciones(Map datos) {
    String nombre = (String) datos.get("nombre");
    String fecha = (String) datos.get("fecha");
    String hora = (String) datos.get("hora");
    String tipo_transaccion = (String) datos.get("tipo_transaccion");
    Double monto = (Double) datos.get("monto");
    String id_cuenta = (String) datos.get("id_cuenta");
    String tipo_cuentaDestino = (String) datos.get("tipo_cuentaDestino");

    Transacciones nuevaTransaccion = new Transacciones(nombre, fecha, hora,tipo_transaccion, monto, id_cuenta, tipo_cuentaDestino);
    repositorioTransacciones.actualizar(nuevaTransaccion);
  }

  public void actualizarTransaccionId(Map datos, String id) {
    String nombre = (String) datos.get("nombre");
    String fecha = (String) datos.get("fecha");
    String hora = (String) datos.get("hora");
    String tipo_transaccion = (String) datos.get("tipo_transaccion");
    Double monto = (Double) datos.get("monto");
    String id_cuenta = (String) datos.get("id_cuenta");
    String tipo_cuentaDestino = (String) datos.get("tipo_cuentaDestino");

    Transacciones nuevaTransaccion = new Transacciones(nombre, fecha, hora,tipo_transaccion, monto, id_cuenta, tipo_cuentaDestino);
    repositorioTransacciones.actualizarId(nuevaTransaccion, id);
  }
}
