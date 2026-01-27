import './tomar_pedido.css'
import arrow from '../assets/flecha.png'
import FilaTomarPedido from '../components/fila_tomar_pedido'

const TomarPedido = () => {
    return (
        <>
            <section className='tomar-pedido-sec'>
                <div className='main'>
                    <div className='tomar-pedido-div-uno'>
                        <button className='tomar-pedido-boton-volver'>
                            <img src={arrow} alt="" />
                        </button>
                    </div>
                    <div className='tomar-pedido-div-dos'>
                        <label htmlFor="" className='numero-mesa'>Número de mesa</label>
                        <input type="text" placeholder='Ej: mesa 1' className='input-mesa' />
                    </div>
                    <div className='tomar-pedido-div-tres'>

                        <div>
                            <h3>Pedido</h3>
                        </div>

                        <div className='items-pedido'>
                            <FilaTomarPedido/>
                            <FilaTomarPedido/>
                            <FilaTomarPedido/>
                            <FilaTomarPedido/>
                            <FilaTomarPedido/>
                            <FilaTomarPedido/>
                        </div>

                    </div>
                    <div className='tomar-pedido-div-cuatro'>
                        <button>Añadir producto</button>
                    </div>
                </div>

                <footer className='tomar-pedido-div-cinco'>
                    <div className='div-confirmar-pedido'>
                        <button className='button-confirmar-pedido'>Confirmar pedido</button>
                        <button className='button-anular-pedido'>Anular pedido</button>
                    </div>

                    <h3>Total</h3>
                    <h3>$0</h3>
                </footer>
            </section>

        </>
    )
}

export default TomarPedido