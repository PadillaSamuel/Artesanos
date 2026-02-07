import './fila_pedido.css'

const FilaPedido=({nombre,cantidad,precio,subtotal})=>{
    return(
        <>
            <section  className='ver-pedido-sec'>
                <div className='ver-pedido-div-uno'>{nombre}</div>
                <div className='ver-pedido-div-dos'>{cantidad}</div>
                <div className='ver-pedido-div-tres'>{precio}</div>
                <div className='ver-pedido-div-cuatro'>{subtotal}</div>
            </section>
        </>
    )
}

export default FilaPedido