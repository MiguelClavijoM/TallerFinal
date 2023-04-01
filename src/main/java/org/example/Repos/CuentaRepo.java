package org.example.Repos;

import org.example.Entidades.Cuentas;
import org.example.Entidades.Usuarios;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

  public class CuentaRepo implements ICrud {

    private String cadenaConexion;

    public CuentaRepo() {
      try {
        DriverManager.registerDriver(new org.sqlite.JDBC());
        cadenaConexion = "jdbc:sqlite:banco.db";
      } catch (SQLException e) {
        System.err.println("Error de conexión con la base de datos: " + e);
      }

    }

    private void conexionCuenta() {
      try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
        System.out.println("Se conectó exitosamente.");
      } catch (SQLException e) {
        System.out.println("Error de conexión: " + e.getMessage());
      }
    }

    @Override
    public void guardar(Object objeto) {
      try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
        Cuentas cuentas = (Cuentas) objeto;
        String sentenciaSql = "INSERT INTO Cuentas (numero_cuenta, saldo, tipo_cuenta, id_usuario) VALUES ('" + cuentas.getNumero_cuenta() + "', '" + cuentas.getSaldo() + "', '" + cuentas.getTipo_cuenta() + "',  '" + cuentas.getId_usuario() + "');";

        Statement sentencia = conexion.createStatement();
        sentencia.execute(sentenciaSql);
      } catch (SQLException e) {
        System.err.println("Error de conexión: " + e);
      } catch (Exception e) {
        System.err.println("Error " + e.getMessage());
      }

    }

    @Override
    public void eliminar(String id_usuario) {
      try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
        String sentenciaSql = "DELETE FROM Cuentas WHERE id_usuario = '" + id_usuario + "';";
        Statement sentencia = conexion.createStatement();
        sentencia.execute(sentenciaSql);
      } catch (SQLException e) {
        System.err.println("Error de conexión: " + e);
      } catch (Exception e) {
        System.err.println("Error " + e.getMessage());
      }
    }

    @Override
    public void actualizar(Object objeto) {
      try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
        Cuentas cuentas = (Cuentas) objeto;
        String sentenciaSql = "UPDATE Usuarios SET numero_cuenta = '" + cuentas.getTipo_cuenta() + "', saldo = '"
          + cuentas.getSaldo() + "', tipo_cuenta = '" + cuentas.getTipo_cuenta()+ "' + id_usuario = '" + cuentas.getId_usuario()+ "';";
        Statement sentencia = conexion.createStatement();
        sentencia.execute(sentenciaSql);
      } catch (SQLException e) {
        System.err.println("Error de conexión: " + e);
      } catch (Exception e) {
        System.err.println("Error " + e.getMessage());
      }
    }

    @Override
    public Object buscar(String id_usuario) {
      try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
        String sentenciaSQL = "SELECT * FROM Cuentas WHERE id_usuario = ?";
        PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
        sentencia.setString(1, id_usuario);
        ResultSet resultadoConsulta = sentencia.executeQuery();
        if (resultadoConsulta != null && resultadoConsulta.next()) {
          Cuentas cuentas = null;
          String numero_cuenta = resultadoConsulta.getString("numero_cuenta");
          Double saldo = Double.valueOf(resultadoConsulta.getString("saldo"));
          String tipo_cuenta = resultadoConsulta.getString("tipo_cuenta");
          String id_usuarioResultado = resultadoConsulta.getString("id_usuario");

          cuentas = new Cuentas(numero_cuenta, saldo, id_usuarioResultado, tipo_cuenta);
          return cuentas;
        }

      } catch (SQLException e) {
        System.err.println("Error de conexión: " + e);
      }
      return null;
    }

    @Override
    public List<?> listar() {
      List<Cuentas> cuentasList = new ArrayList<Cuentas>();

      try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
        String sentenciaSql = "SELECT * FROM Cuentas";
        PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
        ResultSet resultadoConsulta = sentencia.executeQuery();

        if (resultadoConsulta != null) {
          while (resultadoConsulta.next()) {
            Cuentas cuentas = null;
            int id = resultadoConsulta.getInt("id");
            String numero_cuenta = resultadoConsulta.getString("numero_cuenta");
            Double saldo = Double.valueOf(resultadoConsulta.getString("saldo"));
            String tipo_cuenta = resultadoConsulta.getString("tipo_cuenta");
            String id_usuario = resultadoConsulta.getString("id_usuario");

            cuentas = new Cuentas(numero_cuenta, saldo, tipo_cuenta, id_usuario);
            cuentasList.add(cuentas);
          }
          return cuentasList;
        }

      } catch (SQLException e) {
        System.err.println("Error de conexión: " + e);
      }
      return null;

    }

    @Override
    public void actualizarId(Object objeto, String id) {
      try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
        Cuentas cuentas = (Cuentas) objeto;
        String sentenciaSql = "UPDATE Usuarios SET numero_cuenta = '" + cuentas.getTipo_cuenta() + "', saldo = '"
          + cuentas.getSaldo() + "'tipo_cuenta = '" + cuentas.getTipo_cuenta() +"' + id_usuario = '" + cuentas.getId_usuario()+ "' WHERE id = " + id
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
