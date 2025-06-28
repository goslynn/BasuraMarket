import EcoMarketLogo from '../assets/EcoMarket-logo.webp';
import axios from 'axios'
import { useEffect, useState } from 'react';

export function Login() {
//variables necesarias para  control de datos  


    const [formulario, setFormulario] = useState({
        email:"",
        pass:""
    });
    const handleChange =(e)=>{
        const {name,value} = e.target
        setFormulario({...formulario , [name]:value})        
    }
    const enviarDatosAPi = async (datos)=>{
        try{
            //Envio de datos a la api 
            const respuesta = await axios.post(url,{formulario});
            //si al respuesta es 200 es positiva
            if(respuesta.status >= 200 && respuesta.status  < 300){
                console.log(respuesta.status);
                console.log("redireccion");
                
            }else{
                console.log("fallido compare");

            }        
        }catch(err){
            console.error(err)
        }
    }


    function validarDatos(e){
        e.preventDefault();
        console.log(fom);
        
        if(pass == "" || email == ""){
            alert("Todos los campos deben ser completados")
        }else {
            enviarDatosAPi(formulario);
        }
        
    }


    return (
    <div className='container pb-5' style={{maxWidth:"500px"}}>
        <form className='card' onSubmit={validarDatos}>
            <div className='d-flex justify-content-center'>
                <a href="/">
                    <img src={EcoMarketLogo} className="logo" alt="volver" />
                </a>
            </div>
            <div className="container">
                <div>
                    <h2 className="fw-bold fs-1 m-0">Inicio de Sesión</h2>
                    <p>Ingresa con tu correo electrónico y contraseña.</p>
                </div>
                <div className='text-start mb-3'>
                    <label htmlFor="email">Correo:</label>
                    <input onChange={handleChange} type="email" name='email' className='form-control' placeholder='ingrese su Correo'/>
                </div>
                <div className='text-start  mb-3'>
                    <label htmlFor="contraseña">Contraseña:</label>
                    <input onChange={handleChange} type="password" name='pass' className='form-control' placeholder='ingrese su Contraseña'/>
                </div>
                
            </div>
            <button type='submit' className='mt-2 mb-2 btn btn-success' >Iniciar Sesión</button>
            <div className="d-flex align-items-center my-2">
                <hr className="flex-grow-1" />
                <a href='/registro' className='ms-4 me-4'>Crear una cuenta</a>
                <hr className="flex-grow-1" />
            </div>
            <a  href='#' className='fs-6 text-center'>Recuperar contraseña</a>
        </form>
    </div>
    );
}