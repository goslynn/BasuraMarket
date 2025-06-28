import EcoMarketLogo from '../assets/EcoMarket-logo.webp';
import FormPersonal from '../Componentes/Registro/FormPersonal';
import FormContacto from '../Componentes/Registro/FormContacto';
import  {ThemeContext} from '../Contexts/ContextoGeneral.jsx'

// siempre se hacen los locos como si no les hubieran dicho nada 


import { useState } from 'react';
// nombre - rut - nacimiento - genero - tel - idtipocliente  ====>  contraseña correo 
  export function Registro() {
    const [cambio,setCambio] = useState(true)

  return(
      <div className='container pb-5 d-flex justify-content-center'>
          <div className='card' style={{maxWidth:"500px"}}>
              <div className='text-center'>
                <img src={EcoMarketLogo} href="/" alt="LogoDeEmpresa" width={"100"}/>
                <h1 className='display-8'>{cambio ? "Crear cuenta" : "Datos personales"}</h1>
              </div>
              <div className="card-body">
                {cambio ? <FormContacto/> : <FormPersonal/> }
              </div>
              <button onClick={()=>{setCambio(!cambio)}} className='mt-2 mb-2 btn btn-success' >{cambio ? "Siguiente" : "Volver"} </button>
              <a  href='/login' className='fs-6 text-center'>Ya tengo cuenta</a>
          </div>
        </div>
  );
  }

