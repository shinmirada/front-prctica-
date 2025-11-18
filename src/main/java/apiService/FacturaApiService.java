package apiService;

import java.util.List;
import java.util.Map;
import modelo.Factura;
import retrofit2.Call;
import retrofit2.http.*;

public interface FacturaApiService {

    // Obtener todas las facturas
    @GET("/api/facturas")
    Call<List<Factura>> getAllFacturas();

    // Obtener facturas por documento del usuario
    @GET("/api/facturas/documento/{documento}")
    Call<List<Factura>> getFacturasByUsuario(@Path("documento") String documento);

    // Factura por pedido + usuario
    @GET("/api/facturas/pedido/{pedidoId}/usuario/{documento}")
    Call<Factura> getFacturaByPedidoYUsuario(
            @Path("pedidoId") int pedidoId,
            @Path("documento") String documento
    );

    // Factura por ID + usuario
    @GET("/api/facturas/codigo/{facturaId}/usuario/{documento}")
    Call<Factura> getFacturaByIdYUsuario(
            @Path("facturaId") Long facturaId,
            @Path("documento") String documento
    );

    // ✅ CORRECCIÓN: Crear factura con Map en el body
    @POST("/api/facturas")
    Call<Factura> createFactura(@Body Map<String, Object> facturaData);
}