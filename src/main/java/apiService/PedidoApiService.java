/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apiService;

import enums.Estado;
import java.util.List;
import modelo.Pedido;

import retrofit2.Call;
import retrofit2.http.*;

public interface PedidoApiService {

    //  Obtener todos los pedidos
    @GET("/api/pedidos")
    Call<List<Pedido>> getAllPedidos();

    //  Obtener pedido por ID
    @GET("/api/pedidos/{id}")
    Call<Pedido> getPedidoById(@Path("id") int id);

    //  Crear un nuevo pedido
    // Body obligatorio: idPlato, esDomicilio
    @POST("/api/pedidos")
    Call<Pedido> createPedido(@Body Pedido pedido);

    //  Actualizar estado del pedido
    // Body: el estado (Ej: "DESPACHADO", "FINALIZADO")
    @PATCH("/api/pedidos/{id}/estado")
    Call<Pedido> updateEstado(@Path("id") int id, @Body Estado estado);

    //  Eliminar pedido (solo si está FINALIZADO)
    @DELETE("/api/pedidos/{id}")
    Call<Void> deletePedido(@Path("id") int id);

    //  Buscar pedidos por estado
    @GET("/api/pedidos/estado/{estado}")
    Call<List<Pedido>> getPedidosByEstado(@Path("estado") String estado);
    
    //Buscar pedidos por cliente
    @GET("/api/pedidos/cliente/{documento}")
    Call<List<Pedido>> getPedidosByCliente(@Path("documento") String documento);
}
