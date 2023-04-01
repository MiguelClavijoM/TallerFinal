package org.example.Repos;

import org.example.Entiedades.Usuarios;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

  public class UsuarioRepo implements ICrud {

    private String cadenaConexion;

    public UsuarioRepo() {
      try {
        DriverManager.registerDriver(new org.sqlite.JDBC());
        cadenaConexion = "jdbc:sqlite:banco.db";
      } catch (SQLException e) {
        System.err.println("Error de conexión con la base de datos: " + e);
      }

    }

    private void conexionUsuario() {
      try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
        System.out.println("Se conectó exitosamente.");
      } catch (SQLException e) {
        System.out.println("Error de conexión: " + e.getMessage());
      }
    }

    @Override
    public void guardar(Object objeto) {
      try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
        Usuarios usuarios = (Usuarios) objeto;
        String sentenciaSql = "INSERT INTO Usuarios (nombre, apellido, cedula) VALUES ('" + usuarios.getNombre() + "', '" + usuarios.getApellido() + "', '" + usuarios.getCedula() + "');";

        Statement sentencia = conexion.createStatement();
        sentencia.execute(sentenciaSql);
      } catch (SQLException e) {
        System.err.println("Error de conexión: " + e);
      } catch (Exception e) {
        System.err.println("Error " + e.getMessage());
      }

    }

    @Override
    public void eliminar(String cedula) {
      try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
        String sentenciaSql = "DELETE FROM Usuarios WHERE cedula = '" + cedula + "';";
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
        Usuarios usuarios = (Usuarios) objeto;
        String sentenciaSql = "UPDATE Usuarios SET nombre = '" + usuarios.getNombre() + "', apellido = '"
          + usuarios.getApellido() + "' WHERE cedula = '" + usuarios.getCedula() + "';";
        Statement sentencia = conexion.createStatement();
        sentencia.execute(sentenciaSql);
      } catch (SQLException e) {
        System.err.println("Error de conexión: " + e);
      } catch (Exception e) {
        System.err.println("Error " + e.getMessage());
      }
    }

    @Override
    public Object buscar(String cedula) {
      try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
        String sentenciaSQL = "SELECT * FROM Usuarios WHERE identificacion = ?";
        PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
        sentencia.setString(1, cedula);
        ResultSet resultadoConsulta = sentencia.executeQuery();
        if (resultadoConsulta != null && resultadoConsulta.next()) {
          Usuarios usuarios = null;
          String nombre = resultadoConsulta.getString("nombre");
          String apellido = resultadoConsulta.getString("apellido");
          String cedulaResultado = resultadoConsulta.getString("cedula");

          usuarios = new Usuarios(nombre, apellido, cedulaResultado);
          return usuarios;
        }

      } catch (SQLException e) {
        System.err.println("Error de conexión: " + e);
      }
      return null;
    }

    @Override
    public List<?> listar() {
      List<Usuarios> usuariosList = new ArrayList<Usuarios>();

      try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
        String sentenciaSql = "SELECT * FROM Usuarios";
        PreparedStatement sentencia = conexion.prepareStatement(sentenciaSql);
        ResultSet resultadoConsulta = sentencia.executeQuery();

        if (resultadoConsulta != null) {
          while (resultadoConsulta.next()) {
            Usuarios usuarios = null;
            int id = resultadoConsulta.getInt("id");
            String nombre = resultadoConsulta.getString("nombre");
            String apellido = resultadoConsulta.getString("apellido");
            String cedula = resultadoConsulta.getString("cedula");

            usuarios = new Usuarios(nombre, apellido, cedula);
            usuarios.add(usuarios);
          }
          return usuariosList;
        }

      } catch (SQLException e) {
        System.err.println("Error de conexión: " + e);
      }
      return null;

    }

    @Override
    public void actualizarId(Object objeto, String id) {
      try (Connection conexion = DriverManager.getConnection(cadenaConexion)) {
        Usuarios usuarios = (Usuarios) objeto;
        String sentenciaSql = "UPDATE Usuarios SET nombre = '" + usuarios.getNombre() + "', apellido = '"
          + usuarios.getApellido() + "'cedula = '" + usuarios.getCedula() + "' WHERE id = " + id
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

