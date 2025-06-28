import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';

import './index.css';
import App from './App.jsx';

// Componentes comunes
import NavBar from './Componentes/Navegacion/NavBar.jsx';
import Footer from './Componentes/Footer.jsx';

// Rutas
import { Login } from './Routes/Login.jsx';
import { Registro } from './Routes/Registro.jsx';
import Tienda from './Routes/Tienda.jsx';
import VistaProducto from './Routes/VistaProducto.jsx';
import Carrito from './Routes/Carrito.jsx';


const router = createBrowserRouter([    
  {
    path:"/",
    element:<> 
        <NavBar/>
          <App/>
        <Footer></Footer>
        </>, 
    errorElement:<></>
  },
  {
    path:"/login",
    element:<Login/>, 
    errorElement:<></>
  },
  {
    path:"/registro",
    element:<Registro/>, 
    errorElement:<></>
  },
  {
    path:"/tienda",
    element:<> 
        <NavBar/>
          <Tienda/>
        <Footer></Footer>
        </>,  
    errorElement:<></>
  },
  
  {
    path:"/Producto/:id",
    element:<> 
        <NavBar/>
          <VistaProducto/>
        <Footer></Footer>
        </>,  
    errorElement:<></>
  },
  {
    path:"/carrito",
    element:<> 
        <NavBar/>
          <Carrito></Carrito>
          <Footer></Footer>
        </>,
    errorElement:<></>
  }
  
  
])

createRoot(document.getElementById('root')).render(
  <StrictMode>
      <RouterProvider router={router}>
      </RouterProvider>
  </StrictMode>
)
