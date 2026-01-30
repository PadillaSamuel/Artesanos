import './login.css'
import loginImg from "../assets/artesanos_logo.jpg"
import { autenticar } from '../services/autenticacion'

const Login = () => {

    const loguearse=async (e)=>{
        e.preventDefault();
        

        const data=new FormData(e.target)

        const body={
            nombreUsuario:data.get("nombreUsuario"),
            contrasena:data.get("contrasena")
        }

        try {
            const res=await autenticar(body)
            localStorage.setItem("token",res.token)
            console.log("logueadoooooooo")
        } catch (error) {
            alert(error.message)
        }
    }

    return (
        <>
            <section className="sec">
                <form onSubmit={loguearse}>
                    <img src={loginImg} alt="Login" />
                    <h3>Iniciar Sesión</h3>
                    <div className='user-contain'>
                        <label htmlFor="" className='lbl'>Nombre de usuario</label>
                        <input type="text" name="nombreUsuario" placeholder="Ingrese su nombre" className='user' />
                    </div>
                    <div className='passw-contain'>
                        <label htmlFor="" className='lbl'>Contraseña</label>
                        <input type="password" name="contrasena" placeholder='Ingrese su contraseña' className='passw' />

                    </div>
                    <div className='contain-iniciar'>
                        <button className='iniciar'>Iniciar</button>
                    </div>

                </form>
                
            </section>
        </>
    )
}

export default Login;