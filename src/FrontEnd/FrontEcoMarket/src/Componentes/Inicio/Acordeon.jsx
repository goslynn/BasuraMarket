import React from 'react';

const Acordeon = () => {
  return (
    <div className="accordion" id="accordionExample">
      {/* Ítem 1 */}
      <div className="accordion-item">
        <h2 className="accordion-header" id="headingOne">
          <button
            className="accordion-button collapsed"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#collapseOne"
            aria-expanded="false"
            aria-controls="collapseOne"
          >
            Dirección
          </button>
        </h2>
        <div
          id="collapseOne"
          className="accordion-collapse collapse"
          aria-labelledby="headingOne"
          data-bs-parent="#accordionExample"
        >
          <div className="accordion-body">
            <strong>Av. España 8.</strong> 8350708 Santiago, Región Metropolitana.
          </div>
        </div>
      </div>

      {/* Ítem 2 */}
      <div className="accordion-item">
        <h2 className="accordion-header" id="headingTwo">
          <button
            className="accordion-button collapsed"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#collapseTwo"
            aria-expanded="false"
            aria-controls="collapseTwo"
          >
            Teléfono
          </button>
        </h2>
        <div
          id="collapseTwo"
          className="accordion-collapse collapse"
          aria-labelledby="headingTwo"
          data-bs-parent="#accordionExample"
        >
          <div className="accordion-body">
            <strong>Teléfono Móvil:</strong> +56 9 8765 4321
          </div>
        </div>
      </div>

      {/* Ítem 3 */}
      <div className="accordion-item">
        <h2 className="accordion-header" id="headingThree">
          <button
            className="accordion-button collapsed"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#collapseThree"
            aria-expanded="false"
            aria-controls="collapseThree"
          >
            Correo
          </button>
        </h2>
        <div
          id="collapseThree"
          className="accordion-collapse collapse"
          aria-labelledby="headingThree"
          data-bs-parent="#accordionExample"
        >
          <div className="accordion-body">
            <strong>Correo Electrónico:</strong> contacto@ecomarket.com
          </div>
        </div>
      </div>

      {/* Ítem 4 */}
      <div className="accordion-item">
        <h2 className="accordion-header" id="headingFour">
          <button
            className="accordion-button collapsed"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#collapseFour"
            aria-expanded="false"
            aria-controls="collapseFour"
          >
            Horario de atención
          </button>
        </h2>
        <div
          id="collapseFour"
          className="accordion-collapse collapse"
          aria-labelledby="headingFour"
          data-bs-parent="#accordionExample"
        >
          <div className="accordion-body">
            Lunes a Viernes: 9:00 AM - 6:00 PM<br />
            Sábados: 10:00 AM - 2:00 PM<br />
            Domingos: Cerrado
          </div>
        </div>
      </div>
    </div>
  );
};

export default Acordeon;