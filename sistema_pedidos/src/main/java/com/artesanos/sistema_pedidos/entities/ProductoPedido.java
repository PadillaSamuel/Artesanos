package com.artesanos.sistema_pedidos.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "producto_pedido")
public class ProductoPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_productoxpedido")
    Integer id;
    @Column(name = "precio_momento")
    Integer precioMomento;
    @Column(name = "subtotal_pedido")
    Integer subtotalPedido;
    @Column(name = "cantidad")
    Integer cantidadProducto;

    public ProductoPedido() {
    }
}
