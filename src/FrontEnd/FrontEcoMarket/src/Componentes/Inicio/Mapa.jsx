import React from 'react'
import Acordeon from './Acordeon'


const Mapa = () => {
  return (
<section className="container my-5" id="Mapa">
  <div className="row g-4 align-items-start">
    {/* Mapa */}
    <div className="col-12 col-md-6">
      {/* ratio 4:3, puedes cambiar a 16x9 con ratio-16x9 */}
      <div className="ratio ratio-4x3">
        <iframe
          src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3329.0138036448247!2d-70.67000449999999!3d-33.448946899999996!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x9662c45558896371%3A0xe38282e406413635!2sDuoc%20UC%3A%20Sede%20Alameda!5e0!3m2!1ses!2scl!4v1745432102000!5m2!1ses!2scl"
          allowFullScreen
          loading="lazy"
          referrerPolicy="no-referrer-when-downgrade"
        ></iframe>
      </div>
    </div>

    {/* Texto y Acordeón */}
    <div className="col-12 col-md-6">
      <h1 className="display-5 fw-bold text-success text-start">
        Visita nuestra sede o contáctanos 
      </h1>
      <p className="text-start">
        Visítanos en nuestra ubicación o contáctanos a través de los siguientes medios.
      </p>
      <hr />
      <div>
        <Acordeon />
      </div>
    </div>
  </div>
</section>

  )
}

export default Mapa