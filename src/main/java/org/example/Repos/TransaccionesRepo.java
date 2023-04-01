package org.example.Repos;

import org.example.Entidades.Transacciones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class TransaccionesRepo implements ICrud{

  private String cadenaConexion;

  public TransaccionesRepo() {
    try {
      DriverManager.registerDriver(new org.sqlite.JDBC());
      cadenaConexion = "jdbc:sqlite:banco.db";
    } catch (SQLException e) {
      System.err.println("Error de conexión con la base de datos: " + e);
    }

  }

  private void conexionTransaccion() {
    try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
      System.out.println("Se conectó exitosamente.");
    } catch (SQLException e) {
      System.out.println("Error de conexión: " + e.getMessage());
    }
  }


  public void guardar(Object objeto) {
    try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
      Transacciones transacciones = (Transacciones) objeto;
      String sentenciaSql = "INSERT INTO Transacciones (fecha, hora, tipo_transaccion, monto, id_cuenta, tipo_cuentaDestino) VALUES " +
        "('" + transacciones.getFecha() + "', '" + transacciones.getHora() + "', '" + transacciones.getTipo_transaccion() + "'," +
        " '"+ transacciones.getMonto() +"', '"+ transacciones.getId_cuenta() +"', '"+ transacciones.getTipo_cuentaDestino() +"');";

      Statement sentencia = conexion.createStatement();
      sentencia.execute(sentenciaSql);
    } catch (SQLException e) {
      System.err.println("Error de conexión: " + e);
    } catch (Exception e) {
      System.err.println("Error " + e.getMessage());
    }

  }


  public void eliminar(String id_cuenta) {
    try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
      String sentenciaSql = "DELETE FROM Transacciones WHERE id_cuenta = '" + id_cuenta + "';";
      Statement sentencia = conexion.createStatement();
      sentencia.execute(sentenciaSql);
    } catch (SQLException e) {
      System.err.println("Error de conexión: " + e);
    } catch (Exception e) {
      System.err.println("Error " + e.getMessage());
    }
  }


  public void actualizar(Object objeto) {
    try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
      Transacciones transacciones = (Transacciones) objeto;
      String sentenciaSql = "UPDATE Transacciones SET fecha = " +
        "('" + transacciones.getFecha() + "', '" + transacciones.getHora() + "', '" + transacciones.getTipo_transaccion() + "'," +
        " '"+ transacciones.getMonto() +"', '"+ transacciones.getId_cuenta() +"', '"+ transacciones.getTipo_cuentaDestino() +"');";
      Statement sentencia = conexion.createStatement();
      sentencia.execute(sentenciaSql);
    } catch (SQLException e) {
      System.err.println("Error de conexión: " + e);
    } catch (Exception e) {
      System.err.println("Error " + e.getMessage());
    }
  }


  public Object buscar(String id_cuenta) {
    try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
      String sentenciaSQL = "SELECT * FROM Transacciones WHERE id_cuenta = ?";
      PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
      sentencia.setString(1, id_cuenta);
      ResultSet resultadoConsulta = sentencia.executeQuery();
      if (resultadoConsulta != null && resultadoConsulta.next()) {
        Transacciones transacciones = null;
        String fecha = resultadoConsulta.getString("fecha");
        String hora = resultadoConsulta.getString("hora");
        String tipo_transaccion = resultadoConsulta.getString("tipo_transaccion");
        Double monto = Double.valueOf(resultadoConsulta.getString("monto"));
        String id_cuentaResultado = resultadoConsulta.getString("id_cuentaResultado");
        String tipo_cuentaDestino = resultadoConsulta.getString("tipo_cuentaDestino");

        transacciones = new Transacciones(fecha, hora, tipo_transaccion, monto, id_cuentaResultado, tipo_cuentaDestino);
        return transacciones;
      }

    } catch (SQLException e) {
      System.err.println("Error de conexión: " + e);
    }
    return null;
  }


  public List<?> listar() {
    List<Transacciones> transaccionList = new ArrayList<>();

    try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
      String sentenciaSql = "SELECT * FROM Transacciones";
      PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
      ResultSet resultadoConsulta = sentencia.executeQuery();

      if (resultadoConsulta != null) {
        while (resultadoConsulta.next()) {
          Transacciones transacciones = null;
          int id = resultadoConsulta.getInt("id");
          String fecha = resultadoConsulta.getString("fecha");
          String hora = resultadoConsulta.getString("hora");
          String tipo_transaccion = resultadoConsulta.getString("tipo_transaccion");
          Double monto = Double.valueOf(resultadoConsulta.getString("monto"));
          String id_cuenta = resultadoConsulta.getString("id_cuenta");
          String tipo_cuentaDestino = resultadoConsulta.getString("tipo_cuentaDestino");

          transacciones = new Transacciones(fecha, hora, tipo_transaccion, monto, id_cuenta, tipo_cuentaDestino);
          transaccionList.add(transacciones);
        }
        return transaccionList;
      }

    } catch (SQLException e) {
      System.err.println("Error de conexión: " + e);
    }
    return null;

  }

  public void actualizarId(Object objeto, String id) {
    try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
      Transacciones transacciones = (Transacciones) objeto;
      String sentenciaSql = "UPDATE usuarios SET nombre = " +
        "('" + transacciones.getFecha() + "', '" + transacciones.getHora() + "', '" + transacciones.getTipo_transaccion() + "'," +
        " '"+ transacciones.getMonto() +"', '"+ transacciones.getId_cuenta() +"', '"+ transacciones.getTipo_cuentaDestino() +"');"+ "' WHERE id = " + id
        + ";";
      Statement sentencia = conexion.createStatement();
      sentencia.execute(sentenciaSql);
    } catch (SQLException e) {
      System.err.println("Error de conexión: " + e);
    } catch (Exception e) {
      System.err.println("Error " + e.getMessage());
    }
  }
}
