package com.artesanos.sistema_pedidos.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PedidoDto {
    Integer id;
    List<ProductoDto> productos;
}
