package org.example;

import org.example.Entiedades.Usuarios;
import org.example.Repos.UsuarioRepo;

public class MainTemporal {
  public static void main(String[] args) {
    Usuarios persona = new Usuarios("Miguel", "Clavijo", "1112791423");
    UsuarioRepo personita = new UsuarioRepo();
    personita.guardar(persona);
  }
}
