package org.example.Entidades;

public class Usuarios {
  private String id;
  private String nombre;
  private String apellido;
  private String cedula;

  public Usuarios(String nombre, String apellido, String cedula) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.cedula = cedula;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getCedula() {
    return cedula;
  }

  public void setCedula(String cedula) {
    this.cedula = cedula;
  }

}

