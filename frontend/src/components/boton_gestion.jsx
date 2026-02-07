import { useNavigate } from 'react-router-dom'
import './boton_gestion.css'

const Boton = ({ imagen, nombre, className, ruta }) => {
    const navigate=useNavigate();
    return (
        <div className={`contenedor ${className}`} onClick={()=>{navigate(ruta)}}>
            <img src={imagen} alt={nombre} />
            <h3>{nombre}</h3>
        </div>
    )
}

export default Boton
