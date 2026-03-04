import { useNavigate } from 'react-router-dom'
import './gestionar_domis.css'
import volver from '../assets/flecha.png'

const GestionDomis=()=>{
    const navigate=useNavigate();

    return(
        <>
        <section className='sec-gestion-domis'>
            <button className='boton-sec-gestion-domis-volver' onClick={()=>navigate('/caja')}>
                <img src={volver} alt="" />
            </button>
            <button onClick={()=>navigate(`/tomar-pedido/${true}`)}>Nuevo</button>
            <button onClick={()=>navigate(`/pedidos/${true}`)}>Editar</button>
        </section>
            
        </>
    )
}

export default GestionDomis