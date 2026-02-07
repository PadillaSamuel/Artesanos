import './ver_pedido.css'
import FilaPedido from '../components/fila_pedido'
import { useParams } from 'react-router-dom'
import { apiRequest } from '../services/api';
import { useEffect, useState } from 'react';

const VerPedido = ({ n_pedido, n_mesa }) => {
    const [pedido, setPedido] = useState([])
    const [cantidad, setCantidad] = useState(0)
    const { id, mesa} = useParams();
    const [total,setTotal]=useState(0)

    const pedidoPorId = async () => {
        return apiRequest(`/api/detallePedido/${id}`, {
            metodo: "GET"
        })
    }

    const confirmarPedido=async()=>{
        return apiRequest(`/api/pedidos/actualizar/${id}/RESUELTO`,{
            metodo:"PUT"
        })
    }

    const resolverPedido=async()=>{
        const tmp=await confirmarPedido()
    }
    useEffect(() => {
        const traerPedido = async () => {
            const tmp = await pedidoPorId()
            setCantidad(tmp.length)
            setPedido(tmp)
            console.log(tmp)
            const suma=tmp.reduce((cnt,p)=>{
                return cnt+p.subtotalPedido
            },0)
            setTotal(suma)
        }
        traerPedido()
    }, [])

    



    return (
        <>
            <section className='fila-pedido-sec'>
                <div className='fila-pedido-div-uno'>
                    <div className='fila-pedido-text-pedido'>
                        <h3>Pedido {id}</h3>
                    </div>
                    <div className='fila-pedido-text-mesa'>
                        <h3>Mesa N.{mesa}</h3>
                    </div>
                </div>
                <div className='fila-pedido-div-dos'>
                    <div className='fila-pedido-index'>
                        <h3>Productos</h3>
                        <h3>Cantidad</h3>
                        <h3>Precio</h3>
                        <h3>Total</h3>
                    </div>
                    <div className='filas_ver'>
                        
                        {
                            pedido.map((p,index)=>(
                                <FilaPedido key={index} nombre={p.nombreProducto} cantidad={p.cantidadProducto} precio={p.precioMomento} subtotal={p.subtotalPedido}/>
                            ))
                        }

                    </div>
                    <div className='fila-pedido-total'>
                        <h3>Total</h3>
                        <div>${total}</div>
                    </div>
                </div>
                <div className='fila-pedido-div-tres'>
                    <div>
                        <button className='fila-boton-uno'>Anular Pedido</button>
                    </div>
                    <div>
                        <button className='fila-boton-dos' onClick={resolverPedido}>Confirmar Pago</button>
                    </div>
                </div>
            </section>
        </>
    )
}

export default VerPedido