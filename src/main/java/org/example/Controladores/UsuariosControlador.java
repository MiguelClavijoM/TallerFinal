package org.example.Controladores;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Entidades.Usuarios;
import org.example.Servicios.UsuariosServicio;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsuariosControlador extends HttpServlet {

  private UsuariosServicio usuariosServicio;
  private ObjectMapper mapper;

  public UsuariosControlador() {
    usuariosServicio = new UsuariosServicio();
    mapper = new ObjectMapper();
  }

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

    String path = request.getPathInfo();
    if (path == null) {
      List<Usuarios> usuarios = usuariosServicio.listarUsuarios();
      String json = mapper.writeValueAsString(usuarios);
      response.setContentType("application/json");
      response.getWriter().println(json);
    } else {
      switch (path) {
        case "/buscar":
          String id = request.getParameter("id");
          try {
            Usuarios usuario = usuariosServicio.buscarUsuarios(id);
            String json = mapper.writeValueAsString(usuario);
            response.setContentType("application/json");
            response.getWriter().println(json);
          } catch (Exception e) {
            response.setStatus(404);
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            String json = mapper.writeValueAsString(error);
            response.setContentType("application/json");
            response.getWriter().println(json);
          }
          break;
        default:
          response.setStatus(404);
          Map<String, String> error = new HashMap<>();
          error.put("error", "No se encontro el recurso");
          String json = mapper.writeValueAsString(error);
          response.setContentType("application/json");
          response.getWriter().println(json);
          break;
      }

    }

  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    String content = request.getContentType();

    if (content != null && content.equals("application/json")) {
      Map<String, Object> usuarioMap = mapper.readValue(request.getInputStream(), HashMap.class);
      try {
        usuariosServicio.guardarUsuarios(usuarioMap);
        response.setStatus(HttpServletResponse.SC_CREATED);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Usuario guardado con exito");
        String json = mapper.writeValueAsString(respuesta);
        response.setContentType("application/json");
        response.getWriter().println(json);

      } catch (Exception e) {
        response.setStatus(HttpServletResponse.SC_CONFLICT);
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());
        String json = mapper.writeValueAsString(error);
        response.setContentType("application/json");
        response.getWriter().println(json);
      }

    } else {
      response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
      Map<String, String> error = new HashMap<>();
      error.put("error", "El contenido debe ser JSON");
      String json = mapper.writeValueAsString(error);
      response.setContentType("application/json");
      response.getWriter().println(json);
    }
  }

  @Override
  protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String content = request.getContentType();
    if (content != null && content.equals("application/json")) {
      Map<String, Object> usuarioMap = mapper.readValue(request.getInputStream(), HashMap.class);

      try {
        int id = Integer.parseInt(request.getPathInfo().substring(1));
        usuariosServicio.actualizarUsuarios(id, usuarioMap);
        response.setStatus(HttpServletResponse.SC_OK);
        Map<String, String> respuesta = new HashMap<>();
        respuesta.put("mensaje", "Usuario actualizado con éxito");
        String json = mapper.writeValueAsString(respuesta);
        response.setContentType("application/json");
        response.getWriter().println(json);

      } catch (NumberFormatException e) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        Map<String, String> error = new HashMap<>();
        error.put("error", "El id debe ser un número entero");
        String json = mapper.writeValueAsString(error);
        response.setContentType("application/json");
        response.getWriter().println(json);
      } catch (Exception e) {
        response.setStatus(HttpServletResponse.SC_CONFLICT);
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());
        String json = mapper.writeValueAsString(error);
        response.setContentType("application/json");
        response.getWriter().println(json);
      }

    } else {
      response.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
      Map<String, String> error = new HashMap<>();
      error.put("error", "El contenido debe ser JSON");
      String json = mapper.writeValueAsString(error);
      response.setContentType("application/json");
      response.getWriter().println(json);
    }
  }
}
