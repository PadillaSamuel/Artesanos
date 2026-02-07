import './pedidos.css'
import BotonPedido from '../components/boton_pedido'
import { apiRequest } from '../services/api'
import { useEffect, useState } from 'react'

const Pedidos = () => {
    const [cantidad, setCantidad] = useState(0)
    const [pedido, setPedido] = useState([])

    const listarPedidos = () => {
        return apiRequest("/api/pedidos/listar", {
            metodo: "GET",
        })
    }


    useEffect(() => {

        const cargar = async () => {
            const res = await listarPedidos()
            setPedido(res)
            setCantidad(res.length)
            console.log(res)
        }
        cargar()
    }
        , [])





    return (
        <>
            <section className='pedidos_section'>
                <h2>Pedidos</h2>
                <div className='scroll_pedidos'>
                    {
                        
                        pedido.map(p => (
                            
                            <BotonPedido key={p.id} ruta={`/ver-pedido/${p.id}/${p.numeroMesa}`} num_mesa={p.numeroMesa} num_pedido={p.id} />


                        ))
                    }


                </div>
            </section>
        </>
    )
}


export default Pedidos