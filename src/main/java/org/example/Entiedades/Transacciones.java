package org.example.Entiedades;

import java.util.Date;

public class Transacciones {
  private String id;
  private Date fecha;
  private Date hora;
  private String tipo_transaccion;
  private double monto;
  private String id_cuenta;
  private String tipo_cuentaDestino;

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }

  public Date getHora() {
    return hora;
  }

  public void setHora(Date hora) {
    this.hora = hora;
  }

  public String getTipo_transaccion() {
    return tipo_transaccion;
  }

  public void setTipo_transaccion(String tipo_transaccion) {
    this.tipo_transaccion = tipo_transaccion;
  }

  public double getMonto() {
    return monto;
  }

  public void setMonto(double monto) {
    this.monto = monto;
  }

  public String getId_cuenta() {
    return id_cuenta;
  }

  public void setId_cuenta(String id_cuenta) {
    this.id_cuenta = id_cuenta;
  }

  public String getTipo_cuentaDestino() {
    return tipo_cuentaDestino;
  }

  public void setTipo_cuentaDestino(String tipo_cuentaDestino) {
    this.tipo_cuentaDestino = tipo_cuentaDestino;
  }
}
