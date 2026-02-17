package com.artesanos.sistema_pedidos.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.print.PrintService;
import javax.print.PrintServiceLookup;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artesanos.sistema_pedidos.dtos.ComandaDto;
import com.artesanos.sistema_pedidos.dtos.FacturaDto;
import com.artesanos.sistema_pedidos.services.PdfService;
import com.artesanos.sistema_pedidos.services.networkPrinterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "/api/impresora")
@Tag(name = "Impresora", description = "Envia informacion para imprimir")
public class ImpresoraController {
    private final PdfService pdfService;
    private final networkPrinterService networkPrinterService;

    public ImpresoraController(PdfService pdfService,
            networkPrinterService networkPrinterService) {
        this.networkPrinterService = networkPrinterService;
        this.pdfService = pdfService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "No hay impresoras en la red", content = @Content),
            @ApiResponse(responseCode = "200", description = "Listado de impresoras encontradas", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = List.class))))
    })
    @Operation(summary = "Obtener listado de impresoras")
    @PreAuthorize("hasAnyAuthority('ROLE_CAJA', 'ROLE_MESERA')")
    @GetMapping("/printers")
    public ResponseEntity<List<String>> listPrinters() {
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);

        List<String> impresoras = Arrays.stream(printServices)
                .map(PrintService::getName)
                .collect(Collectors.toList());
        if (impresoras.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(impresoras);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Error al imprimir factura, posible ip no encontrada", content = @Content),
            @ApiResponse(responseCode = "200", description = "Factura impresa")
    })
    @Operation(summary = "Enviar a imprimir factura")
    @PreAuthorize("hasAnyAuthority('ROLE_CAJA', 'ROLE_MESERA')")
    @PostMapping("/factura")
    public ResponseEntity<?> imprimirFactura(@RequestBody FacturaDto payload) {
        try {

            String printerIp = payload.getImpresoraIp();

            Map<String, Object> data = new HashMap<>();
            data.put("id", payload.getIdPedido());

            String mesa = payload.getNumeroMesa();
            data.put("mesa", (mesa == null || mesa.isBlank()) ? null : mesa);

            String domicilio = payload.getNombreDomicilio();
            data.put("nombreDomicilio", (domicilio == null || domicilio.isBlank()) ? null : domicilio);

            data.put("fecha", payload.getFechaFactura());
            data.put("pedido", payload.getProductos());
            data.put("total", payload.getTotal());
            byte[] pdf = pdfService.crearFactura(data);

            networkPrinterService.printPdfToNetworkPrinter(pdf, printerIp);

            return ResponseEntity.ok("Impresión enviada exitosamente a: " + printerIp);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error en el proceso de impresión: " + e.getMessage());
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Error al imprimir comanda, posible ip no encontrada", content = @Content),
            @ApiResponse(responseCode = "200", description = "Comanda impresa")
    })
    @Operation(summary = "Enviar a imprimir Comanda")
    @PreAuthorize("hasAnyAuthority('ROLE_CAJA', 'ROLE_MESERA')")
    @PostMapping("/comanda")
    public ResponseEntity<?> imprimirComanda(@RequestBody ComandaDto payload) {
        try {
            String printerIp = payload.getImpresoraIp();

            Map<String, Object> data = new HashMap<>();
            data.put("id", payload.getIdPedido());

            String mesa = payload.getNumeroMesa();
            data.put("mesa", (mesa == null || mesa.isBlank()) ? null : mesa);

            String domicilio = payload.getNombreDomicilio();
            data.put("nombreDomicilio", (domicilio == null || domicilio.isBlank()) ? null : domicilio);

            data.put("pedido", payload.getProductos());
            byte[] pdf = pdfService.crearComanda(data);

            networkPrinterService.printPdfToNetworkPrinter(pdf, printerIp);

            return ResponseEntity.ok("Impresión enviada exitosamente a: " + printerIp);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error en el proceso de impresión: " + e.getMessage());
        }
    }

    @Operation(summary = "Vista previa factura")
    @SuppressWarnings("null")
    @PostMapping("/invoice/test-view")
    public ResponseEntity<byte[]> testView(@RequestBody FacturaDto payload) {

        Map<String, Object> data = new HashMap<>();
        data.put("id", payload.getIdPedido());
        data.put("mesa", payload.getNumeroMesa());
        data.put("fecha", payload.getFechaFactura());
        data.put("nombreDomicilio", payload.getNombreDomicilio());
        data.put("pedido", payload.getProductos());
        data.put("total", payload.getTotal());

        byte[] pdf = pdfService.crearFactura(data);
        return ResponseEntity.ok()
                .header("Content-Disposition", "inline; filename=factura.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
    @Operation(summary = "Vista previa comanda")
    @SuppressWarnings("null")
    @PostMapping("/command/test-view")
    public ResponseEntity<byte[]> testViewComanda(@RequestBody ComandaDto payload) {

        Map<String, Object> data = new HashMap<>();
        data.put("id", payload.getIdPedido());
        data.put("mesa", payload.getNumeroMesa());
        data.put("nombreDomicilio", payload.getNombreDomicilio());
        data.put("pedido", payload.getProductos());

        byte[] pdf = pdfService.crearComanda(data);
        return ResponseEntity.ok()
                .header("Content-Disposition", "inline; filename=factura.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
