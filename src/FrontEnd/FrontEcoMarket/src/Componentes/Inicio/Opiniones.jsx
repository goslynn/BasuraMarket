import React, { useState, useEffect } from 'react'
import CardOpiniones from './CardOpiniones'

const Opiniones = () => {
  const url = "https://dummyjson.com/comments?limit=4"
  const [opiniones, setOpiniones] = useState([])

  useEffect(() => {
    fetch(url)
      .then(res => res.json())
      .then(data => setOpiniones(data.comments))
      .catch(err => console.error(err))
  }, [])  // <-- la dependencia vacía para que sólo corra una vez

  return (
    <section className="container">
      <div className="row justify-content-center text-center mb-4">
        <div className="col-12">
          <h2 className="display-5 fw-bold text-success" >
            ¿Qué opinan de nosotros?
          </h2>
          <p className="lead text-muted fs-4">
            Conoce las opiniones de nuestros clientes y qué opinan de nuestro servicio
          </p>
        </div>
      </div>

      <div className="row g-4 justify-content-center">
        {opiniones.map(opinion => (
          <div
            key={opinion.id}
            className="col-12 col-sm-6 col-md-4 col-lg-3"
          >
            <CardOpiniones opinion={opinion} />
          </div>
        ))}
      </div>
    </section>
  )
}

export default Opiniones
