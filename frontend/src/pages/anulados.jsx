import { useEffect, useState } from 'react'
import './anulado.css'
import { apiRequest } from '../services/api'
import BotonPedido from '../components/boton_pedido'
import volver from '../assets/flecha.png'
import { useNavigate } from 'react-router-dom'

const Anulados = () => {
    const [pedidos, setPedidos] = useState([])
    const navigate=useNavigate()

    const traerAnulados = async () => {
        return apiRequest('/api/pedidos/anulados', {
            metodo: 'GET'
        })
    }

    useEffect(() => {
        const funcion = async () => {
            const anul = await traerAnulados()
            setPedidos(anul)
        }
        funcion()
    }, [])

    return (
        <>

            <section className='sec-anulados'>
                <div className='header-ped-anulados'>
                    <button className='volv-ped-anul' onClick={()=>{navigate('/gestion-productos')}}>
                        <img src={volver} alt="" />
                    </button>
                    <h1 className='pedidos-anulados-title'>Pedidos anulados</h1>
                </div>
                
                <div className='pedidos-anulados-lista'>
                    {
                        pedidos != null ? (
                            pedidos.map(p => (
                                p.numeroMesa === 0 ? (
                                    <BotonPedido key={p.id} ruta={`/ver-pedido-domi/${p.id}/${p.nombreDomicilio}/resuelto`} nombreDomi={p.nombreDomicilio} num_pedido={p.id} />
                                ) : (
                                    <BotonPedido key={p.id} ruta={`/ver-pedido/${p.id}/${p.numeroMesa}/resuelto`} num_mesa={p.numeroMesa} num_pedido={p.id} />
                                )
                            ))
                        ) : (
                            <><p>No existen pedidos anulados</p></>
                        )
                    }
                </div>

            </section>
        </>
    )
}

export default Anulados