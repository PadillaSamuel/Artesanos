package com.artesanos.sistema_pedidos.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class ComandaDto {
    Integer idPedido;
    String impresoraIp;
    String numeroMesa;
    String nombreDomicilio;
    List<ProductoDetalleDto> productos;
}
// {
//     "idPedido": 101,
//     "nombreDomicilio": "",
//     "impresoraIp": "192.168.0.100",
//     "numeroMesa": "5",
//     "productos": [
//         {
//             "nombreProducto": "Pizza Hawaiana",
//             "cantidadProducto": 2,
//             "precioMomento": 22000,
//             "subtotalPedido": 44000,
//             "peticionCliente": ""
//         },
//         {
//             "nombreProducto": "Pizza Pepperoni",
//             "cantidadProducto": 1,
//             "precioMomento": 25000,
//             "subtotalPedido": 29000,
//             "peticionCliente": "Con adición de queso parmesano"
//         },
//         {
//             "nombreProducto": "Pizza Pepperoni",
//             "cantidadProducto": 1,
//             "precioMomento": 25000,
//             "subtotalPedido": 25000,
//             "peticionCliente": "Sin queso, bien tostada"
//         }, 
//         {
//             "nombreProducto": "Pizza espanta brujas",
//             "cantidadProducto": 2,
//             "precioMomento": 25000,
//             "subtotalPedido": 25000,
//             "peticionCliente": "Para llevar 2"
//         }
//     ]
// }