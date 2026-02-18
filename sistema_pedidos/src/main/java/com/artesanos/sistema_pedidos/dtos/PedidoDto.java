package com.artesanos.sistema_pedidos.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoDto {
    Integer id;
    Integer numeroMesa;
    Integer total;
    List<ProductoDetalleDto> productos;
    String nombreDomicilio;

    public PedidoDto(Integer id, Integer total, Integer numeroMesa, String nombreDomicilio) {
        this.id = id;
        this.total = total;
        this.numeroMesa = numeroMesa;
        this.nombreDomicilio = nombreDomicilio;
    }

    public PedidoDto(Integer id, Integer numeroMesa, String nombreDomicilio) {
        this.id = id;
        this.numeroMesa = numeroMesa;
        this.nombreDomicilio = nombreDomicilio;
    }
}
