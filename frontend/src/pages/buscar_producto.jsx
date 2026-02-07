import './buscar_producto.css'
import FilaProducto from '../components/fila_producto'
import MenuBuscar from '../components/menu_buscar'
import { useEffect, useState } from 'react'
import { apiRequest } from '../services/api'

const BuscarProducto = ({ n }) => {
    const [dato, setDato] = useState("")
    const [pizzas, setPizzas] = useState([])
    const filtrar = (e) => {
        setDato(e.target.value)
        console.log(e.target.value)
    }

    const filtrarProducto = async () => {
        return apiRequest(`/api/producto/listar/${dato}`, {
            metodo: "GET"
        })
    }

    useEffect(() => {
        const tmpfunc = async () => {
            const tmp = await filtrarProducto()
            setPizzas(tmp)
        }

        tmpfunc()
        console.log(pizzas)
    },

        [dato])


    return (
        <>
            <section className='buscar-producto-section'>
                <h2>Buscar Producto</h2>
                <form onChange={filtrar}>
                    <input type="text" placeholder='Ingrese un producto' name='buscador' />
                </form>

                <div className='busqueda-div'>
                    <FilaProducto campoUno="ID" campoDos="Nombre" campoTres="Precio" campoCuatro="Gestionar" />
                    <div className='filas-productos'>

                        {
                            pizzas!=null ?(
                                pizzas.map((p, index) => (
                                <FilaProducto key={index} campoUno={p.id} campoDos={p.nombreProducto} campoTres={p.precio} campoCuatro={<MenuBuscar id={p.id} nombre={p.nombreProducto} precio={p.precio} />}/>
                            ))
                            ):(
                                <p>No hay productos que coincidan con la busqueda</p>
                            )
                                
                            
                            


                        }
                    </div>
                </div>
            </section>
        </>
    )
}

export default BuscarProducto