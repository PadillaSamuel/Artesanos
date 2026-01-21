package com.artesanos.sistema_pedidos.entities;

import java.time.LocalDate;

import com.artesanos.sistema_pedidos.enums.EstadoPedido;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    LocalDate fechaPedido;
    Integer totalPedido;
    Integer numeroMesa;
    @Enumerated(EnumType.STRING)
    EstadoPedido estadoPedido;

    public Pedido(){}
}
