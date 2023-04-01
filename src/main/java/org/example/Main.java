package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
  public static void main(String[] args) {
    try {
      DriverManager.registerDriver(new org.sqlite.JDBC());
      String cadenaConexion = "jdbc:sqlite:banco.db";
      String sql = "CREATE TABLE Usuarios(\n" +
        "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
        "nombre TEXT NOT NULL,\n" +
        "apellido TEXT NOT NULL,\n" +
        "cedula TEXT NOT NULL UNIQUE\n" +
        ");\n" +

        "CREATE TABLE Cuentas(\n" +
        "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
        "numero_cuenta TEXT NOT NULL UNIQUE,\n" +
        "saldo REAL NOT NULL,\n" +
        "tipo_cuenta TEXT NOT NULL,\n" +
        "id_usuario INTEGER NOT NULL,\n" +
        "FOREIGN KEY(id_usuario) REFERENCES usuario(id)\n" +
        ");\n" +

        "CREATE TABLE Transacciones(\n" +
        "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
        "fecha TEXT NOT NULL,\n" +
        "hora TEXT NOT NULL,\n" +
        "tipo_transaccion TEXT NOT NULL,\n" +
        "monto REAL NOT NULL,\n" +
        "id_cuenta INTEGER NOT NULL,\n" +
        "tipo_cuentaDestino TEXT,\n" +
        "FOREIGN KEY(id_cuenta) REFERENCES cuenta(id)\n" +
        ");";

      Connection conexion = DriverManager.getConnection(cadenaConexion);
      Statement sentencia = conexion.createStatement();
      sentencia.execute(sql);

    } catch (SQLException e) {
      System.err.println("Error de conexión con la base de datos: " + e);
    }

  }

}
