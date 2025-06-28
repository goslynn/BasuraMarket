import React, { useDebugValue, useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom'; 
import cartIcon from '../assets/svg/carrito.svg';
import verIcon from '../assets/svg/ver.svg';
import Skeleton from 'react-loading-skeleton';
import 'react-loading-skeleton/dist/skeleton.css';

const Productos = ({ url }) => {
  const [productos, setProductos] = useState([]);
  const [cargando, setCargando] = useState(true);
  const navigate = useNavigate();  

  function verProducto(id) {
    navigate(`/producto/${id}`); 
    window.scrollTo({ top: 0 });
  }

  const renderSkeleton = () => (
    <div className='container'>
        <div className="row g-4">
          {Array.from({ length: 8 }).map((_, i) => (
            <div className="col-12 col-sm-6 col-md-4 col-lg-3" key={i}>
              <div className="border p-3 rounded-3 h-100 shadow-sm">
                <Skeleton height={250} />
                <div className="d-flex flex-column justify-content-between mt-3">
                  <p className="text-muted text-center small mb-1">
                    <Skeleton width="60%" />
                  </p>
                  <h5 className="text-center fw-semibold"><Skeleton /></h5>
                  <p className="text-muted small"><Skeleton count={2} /></p>
                  <div className="text-center mt-auto d-flex justify-content-around">
                    <Skeleton width={100} height={36} />
                    <Skeleton width={36} height={36} circle />
                  </div>
                </div>
              </div>
            </div>
          ))}
        </div>
    </div>
  );

  useEffect(() => {
    //consumir la api de los productos
    fetch(url)
        .then(res => res.json())
        .then(data => {
            setProductos(data.products);
        })
        .catch(error => console.error('Error al obtener productos:', error));
    // ejecucion del loader
        setTimeout(()=>{
            setCargando(false);
        },800)
    }, []);



if(cargando){
  return renderSkeleton()
}else{

  return (
    <div className="container my-4">
      {cargando
        ? renderSkeleton()
        : (
          <div className="row g-4">
            {productos.map(producto => (
              <div className="col-12 col-sm-6 col-md-4 col-lg-3" key={producto.id}>
                <div className="border p-3 rounded-3 h-100 shadow-sm">
                  <img
                    src={producto.image}
                    alt={producto.title}
                    className="card-img-top p-3"
                    style={{ height: '250px', objectFit: 'contain' }}
                  />
                  <div className="d-flex flex-column justify-content-between mt-3">
                    <p className="text-muted text-center small mb-1">{producto.category}</p>
                    <h5 className="text-center fw-semibold">
                      {producto.title.length > 15
                        ? producto.title.slice(0, 15) + '...'
                        : producto.title
                      }
                    </h5>
                    <p className="text-muted small">
                      {producto.description.length > 50
                        ? producto.description.slice(0, 50) + '...'
                        : producto.description
                      }
                    </p>
                    <div className="text-center mt-auto d-flex gap-2 justify-content-around">
                      <button className="btn btn-outline-success">
                        <img src={cartIcon} alt="carrito-icon" className=" cart-icon-carta" width={20} height={20} />
                        Añadir al carro
                      </button>
                      <a  onClick={()=> verProducto(producto.id)} className="btn btn-outline-success">
                        <img src={verIcon}  alt="ver-icon" className='cart-icon-carta' width={20} height={20} />
                      </a>
                    </div>
                  </div>
                </div>
              </div>
            ))}
          </div>
        )
      }
    </div>
  );
}
};

export default Productos;
