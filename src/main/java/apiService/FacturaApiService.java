/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package apiService;

import java.util.List;
import modelo.Factura;

import retrofit2.Call;
import retrofit2.http.*;

public interface FacturaApiService {

    //  Obtener todas las facturas
    @GET("/api/facturas")
    Call<List<Factura>> getAllFacturas();

    //  Obtener facturas por documento del usuario
    @GET("/api/facturas/documento/{documento}")
    Call<List<Factura>> getFacturasByUsuario(@Path("documento") String documento);

    //  Obtener factura por código
    @GET("/api/facturas/codigo/{codigo}")
    Call<Factura> getFacturaByCodigo(@Path("codigo") String codigo);

    //  Crear una nueva factura
    // Body: usuarioDoc, pedidoId, hora
    @POST("/api/facturas")
    Call<Factura> createFactura(@Body Factura factura);
}