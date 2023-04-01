package org.example.Entidades;

public class Cuentas {
  private int id;
  private String numero_cuenta;
  private double saldo;
  private String tipo_cuenta;
  private String id_usuario;

  public Cuentas(String numero_cuenta, double saldo, String tipo_cuenta, String id_usuario) {
    this.numero_cuenta = numero_cuenta;
    this.saldo = saldo;
    this.tipo_cuenta = tipo_cuenta;
    this.id_usuario = id_usuario;
  }

  public String getNumero_cuenta() {
    return numero_cuenta;
  }

  public void setNumero_cuenta(String numero_cuenta) {
    this.numero_cuenta = numero_cuenta;
  }

  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }

  public String getTipo_cuenta() {
    return tipo_cuenta;
  }

  public void setTipo_cuenta(String tipo_cuenta) {
    this.tipo_cuenta = tipo_cuenta;
  }

  public String getId_usuario() {
    return id_usuario;
  }

  public void setId_usuario(String id_usuario) {
    this.id_usuario = id_usuario;
  }
}
