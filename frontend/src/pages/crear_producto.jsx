import { useEffect, useState } from 'react'
import { apiRequest } from '../services/api'
import './crear_producto.css'
import { useParams } from 'react-router-dom'

const CrearProducto = () => {
    const { id, nombre, precio } = useParams()



    const enviarProducto = async (cuerpo) => {
        return apiRequest("/api/producto/crear", {
            metodo: "POST",
            body: cuerpo
        })
    }

    const actualizarProducto = async (cuerpo) => {
        return apiRequest(`/api/producto/actualizar/${id}`, {
            metodo: "PUT",
            body: cuerpo
        })
    }

    const capturarDatos = async (e) => {
        e.preventDefault();
        const nombreProducto = e.target.nombrePizza.value;
        const precioProducto = e.target.precioPizza.value;

        const cuerpo = {
            nombreProducto: nombreProducto,
            precioProducto: precioProducto,
            combinable: true,
            activo: true
        };

        if (id != undefined) {
            const actualizar = await actualizarProducto(cuerpo);
        } else {
            const enviar = await enviarProducto(cuerpo);
        }


    }



    return (
        <>
            <section className='crear-producto-sec'>
                <form onSubmit={capturarDatos} className='form-crear-producto'>
                    <div>Nuevo Producto</div>
                    <div className='crear-producto-inputs'>
                        <label htmlFor="">Nombre del producto</label>
                        {id != undefined ? (
                            <input type="text" className='inputs' name='nombrePizza' defaultValue={nombre} />
                        ) : (
                            <input type="text" className='inputs' name='nombrePizza' />
                        )

                        }
                    </div>
                    <div className='crear-producto-inputs'>
                        <label htmlFor="">Precio del producto</label>
                        {id != undefined ? (
                            <input type="text" className='inputs' name='precioPizza' defaultValue={precio} />
                        ) : (
                            <input type="text" className='inputs' name='precioPizza' />
                        )

                        }

                    </div>
                    <button>Guardar</button>
                </form>

            </section>
        </>
    )
}

export default CrearProducto