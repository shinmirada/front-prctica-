package apiService;

import java.util.List;
import modelo.EstadoUpdateDTO;
import modelo.Pedido;
import modelo.PedidoRequestDTO;

import retrofit2.Call;
import retrofit2.http.*;

public interface PedidoApiService {

    @GET("/api/pedidos")
    Call<List<Pedido>> getAllPedidos();

    @GET("/api/pedidos/{id}")
    Call<Pedido> getPedidoById(@Path("id") int id);

    @POST("/api/pedidos")
    Call<Pedido> createPedido(@Body PedidoRequestDTO pedidoRequest); 

    // ✅ Usar DTO type-safe
    @PATCH("/api/pedidos/{id}/estado")
    Call<Pedido> updateEstado(@Path("id") int id, @Body EstadoUpdateDTO estadoDTO);

    @DELETE("/api/pedidos/{id}")
    Call<Void> deletePedido(@Path("id") int id);

    @GET("/api/pedidos/estado/{estado}")
    Call<List<Pedido>> getPedidosByEstado(@Path("estado") String estado);
    
    @GET("/api/pedidos/cliente/{documento}")
    Call<List<Pedido>> getPedidosByCliente(@Path("documento") String documento);
}