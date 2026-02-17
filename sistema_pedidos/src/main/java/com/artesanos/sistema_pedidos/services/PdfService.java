package com.artesanos.sistema_pedidos.services;

import java.util.Map;

public interface PdfService {
    public byte[] crearFactura(Map<String, Object> data);
    public byte[] crearComanda(Map<String, Object> data);
}
