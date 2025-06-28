import {React} from 'react'
import Productos from '../Productos'

const Populares = () => {
  let limite =  "?limit=4"
  const url = `https://fakestoreapi.in/api/products${limite}`;
  
  return (
  
      <section className="container my-5" id="Populares">
      <div className="row justify-content-center">
        <div className="col-12 text-center">
          <h2 className="display-5 fw-bold text-success mb-2">
            Conoce nuestros productos
          </h2>
          <p className="lead text-muted fs-4 mb-4">
            Descubre los productos más populares y amados por nuestros clientes
          </p>
        </div>

        <div className="col-12">
          <Productos url={url} />
        </div>

        <div className="col-12 text-center mt-4">
          <a href="/tienda" className="btn btn-success">
            Ver Tienda
          </a>
        </div>
      </div>
    </section>
  
  )
}

export default Populares