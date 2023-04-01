package org.example;

import org.example.Entidades.Transacciones;
import org.example.Entidades.Usuarios;
import org.example.Repos.TransaccionesRepo;
import org.example.Repos.UsuarioRepo;

public class MainTemporal {
  public static void main(String[] args) {
    Usuarios persona = new Usuarios("Miguel", "Clavijo", "1112791423");
    UsuarioRepo personita = new UsuarioRepo();
    personita.guardar(persona);

    Transacciones transaccion = new Transacciones("30/03/2023", "21:24", "deposito", tipo_transaccion, 5000.0,"654","Ahorros");
    TransaccionesRepo minitransaccion = new TransaccionesRepo();
    minitransaccion.guardar(transaccion);

  }



}
