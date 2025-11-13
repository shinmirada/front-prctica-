/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apiService;


import java.util.List;
import modelo.Plato;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PlatoApiService {

    // Obtener todos los platos
    @GET("/api/platos")
    Call<List<Plato>> getAllPlatos();

    // Obtener un plato por su ID
    @GET("/api/platos/{id}")
    Call<Plato> getPlatoById(@Path("id") int id);
    
    // crear plato
    @POST("/api/platos")
    Call<Plato> createPlato(@Body Plato plato);
    
    
    // editar plato pero solo por su id
    @PATCH("/api/platos/{id}")
    Call<Plato> cambiarDatos(@Path("id") int id,@Body Plato plato);
    
    
    //eliminar 
    @DELETE("/api/platos/{id}")// solo elimina por id aunq sea la misma ruta el metoo es diferente
    Call<Void> deletePlato(@Path("id") int id);
    
}