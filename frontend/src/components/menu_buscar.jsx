import './menu_buscar.css'
import editar from '../assets/editar.png'
import eliminar from '../assets/boton-x.png'
import { useNavigate } from 'react-router-dom'

const MenuBuscar=({id,nombre,precio})=>{
    const navigate=useNavigate()
    return(
        <>
            <div className='menu'>
                <img src={editar} alt="" className='menu-img' onClick={()=>navigate(`/editar-producto/${id}/${nombre}/${precio}`)}/>
                <img src={eliminar} alt="" className='menu-img'/>
            </div>
        </>
    )
}

export default MenuBuscar