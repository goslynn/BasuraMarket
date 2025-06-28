  import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import './index.css';
import { useState } from 'react';

// Componentes comunes

import Dashboard from './Componentes/Administrador/Dashboard.jsx';
import Administrador from './Routes/Administrador.jsx';
import NotFound from './Routes/NotFound.jsx';
import ListarProductos from './Componentes/Inventario/Producto/ListarProductos.jsx';
import ListarFamilia from './Componentes/Inventario/Familia/ListarFamilia.jsx';
import ListarSubFamilia from './Componentes/Inventario/SubFamilia/ListarSubFamilia.jsx';
import CrearProducto from './Componentes/Inventario/Producto/CrearProducto.jsx';
import CrearFamilia from './Componentes/Inventario/Familia/CrearFamilia.jsx';
import ControlUsuario from './Componentes/Usuarios/ControlUsuario.jsx';
const router = createBrowserRouter([    
  {
    path: "",
    element: <Administrador />,
    errorElement: <NotFound/>,
    children: [
      {
        index: true,
        element: <Dashboard />
      },
      // Rutas de inventario
      {
        path: "inventario/producto",
        element: <ListarProductos />,
      },
      {
        path: "inventario/producto/crear-producto",
        element: <CrearProducto />,
      },
      {
        path: "inventario/familia",
        element: <ListarFamilia />,
      },
      {
        path: "/inventario/familia/crear-familia",
        element: <CrearFamilia />,
      },
      {
        path: "inventario/subfamilia",
        element: <ListarSubFamilia />,
      },
      {
        path: "/inventario/subfamilia/crear-subfamilia",
        element: <CrearFamilia />,
      },
      // Rutas de Usuario
      {
        path: "control",
        element: <ControlUsuario/>,
      },
      {
        path: "control-usuario",
        element: <ControlUsuario/>,
      },
    ]
  }
]);


createRoot(document.getElementById('root')).render(
  <StrictMode>
      <RouterProvider router={router}>
      </RouterProvider>
  </StrictMode>
)
