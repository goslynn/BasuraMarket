import {React,useState, useEffect}  from 'react'
import { useParams } from 'react-router-dom';
import cartIcon  from '../assets/svg/carrito.svg'
import Skeleton from 'react-loading-skeleton';
import Populares from '../Componentes/Inicio/Populares';
const RenderProducto = () => {
  return (
    <div className="container my-5">
      <div className="col-12 col-sm-6 col-md-6 col-lg-6 mb-4">
        {/* Columna Imagen */}
        <div className="col block d-flex justify-content-center">
          <Skeleton height={400} width={400} />
        </div>

        {/* Columna Detalles */}
        <div className="col">
          <div className="text-start mb-3">
            <h1 className="fs-3 fw-bold">
              <Skeleton width={250} />
            </h1>
            <strong className="fs-5 d-block mb-2">
              <Skeleton width={120} />
            </strong>
            <hr />
          </div>
          <div className="text-start mb-4">
            <h3 className="fw-bold fs-5">
              <Skeleton width={150} />
            </h3>
            <p className="text-muted">
              <Skeleton width={550} height={100} />
            </p>
          </div>
          <div>
            <button className="btn btn-success d-flex align-items-center" type="button" disabled>
              <Skeleton width={150} height={30} />
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

const VistaProducto = () => {
    const [producto,setProducto] = useState({})
    const [cargando,setCargando] = useState(true)
    const { id } = useParams(); 

    useEffect(() => {
      fetch(`https://fakestoreapi.in/api/products/${id}`)
        .then(res => res.json())
        .then(data => {
          setProducto(data.product);
          
        })
        .catch(error => console.error('Error al cargar producto:', error));
    }, [id]);
    
    useEffect(()=>{
        setTimeout(()=>{
          setCargando(false)
        },800)
    },[cargando])




  if(cargando){
    return <RenderProducto></RenderProducto>
  }else{
    return (
      <div className="container mt-5">

        <div className="row ">
            <div className="col-12 col-sm-6 col-md-6 col-lg-6 mb-4">
              <img src={producto.image} alt="" className="img-fluid w-100" />
            </div>
          <div className="col">
            <div className="text-start">
              <h1 className="fs-3 fw-bold">{producto.title.slice(0, 50)}</h1>
              <strong className="fs-5">Precio: ${producto.price}.000</strong>
              <hr />
              
            </div>
            <div className="text-start">
              <h3 className="fw-bold fs-5">Descripción:</h3>
              <p className="text-disable">{producto.description.slice(0, 500)}.</p>
            </div>
            <div>
            <button className="btn btn-success d-flex align-items-center" type="submit">
              <img src={cartIcon} alt="Carrito" width="20" height="20" className="me-2" /> 
              Añadir al Carrito 
            </button>
            </div>
          </div>
        </div>
        <div>
            <Populares></Populares>
        </div>
      </div>
    )
  }
}

export default VistaProducto