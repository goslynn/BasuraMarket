import React from 'react' 
import CardFiltros from '../Componentes/Tienda/CardFiltros.jsx'
import Productos from '../Componentes/Productos.jsx'
const url = "https://fakestoreapi.in/api/products?limit=20";


const Tienda = () => {
    return (
        <section className='mt-4'>            
            <div className='mt-5 mb-3'>
                <CardFiltros></CardFiltros>
            </div>
            <div>
                <Productos url={url}></Productos>
            </div>
        </section>        
        
    )
}

export default Tienda