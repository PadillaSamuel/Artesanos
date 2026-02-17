package com.artesanos.sistema_pedidos.enums;

public enum EstadoPago {
    PENDIENTE, EFECTIVO, TRANSFERENCIA;

    public static EstadoPago fromString(String estado) {
        for (EstadoPago e : EstadoPago.values()) {
            if (e.name().equals(estado)) {
                return e;
            }
        }

        return PENDIENTE;
    }
}
