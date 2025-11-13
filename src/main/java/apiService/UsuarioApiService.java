/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apiService;

import java.util.List;
import modelo.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UsuarioApiService {

    // Obtener todos los usuarios
    @GET("/api/usuarios")
    Call<List<Usuario>> getAllUsuarios();

    // Obtener usuario por documento
    @GET("/api/usuarios/documento/{documento}")
    Call<Usuario> getUsuarioByDocumento(@Path("documento") String documento);

    // Obtener usuario por nombre de usuario
    @GET("/api/usuarios/nombreUsuario/{username}")
    Call<Usuario> getUsuarioByUsername(@Path("username") String username);

    // Crear nuevo usuario (por ejemplo, para registrar un cliente)
    @POST("/api/usuarios")
    Call<Usuario> createUsuario(@Body Usuario usuario);

    // Login (solo usuario y contraseña en el body)
    @POST("/api/usuarios/login")
    Call<Usuario> login(@Body Usuario usuario);
    
    //Obtener uuarios por rol
    @GET("/api/usuarios/rol/{rol}")
    Call<List<Usuario>> getUsuariosByRol(@Path("rol") String rol);

}