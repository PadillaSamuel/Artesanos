package com.artesanos.sistema_pedidos.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProductoDto {
    Integer id;
    String nombreProducto;
    Integer cantidadProducto;
    Integer precioProducto;
}
