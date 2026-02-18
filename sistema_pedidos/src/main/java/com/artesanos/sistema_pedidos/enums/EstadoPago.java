package com.artesanos.sistema_pedidos.enums;

public enum EstadoPago {
    NO_PAGO, EFECTIVO, TRANSFERENCIA, DATAFONO;

    public static EstadoPago fromString(String estado) {
        for (EstadoPago e : EstadoPago.values()) {
            if (e.name().equals(estado)) {
                return e;
            }
        }

        return NO_PAGO;
    }
}
