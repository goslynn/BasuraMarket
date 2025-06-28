import React from 'react';
import flecha from '../../assets/svg/flecha.svg';
import EcoMarketLogo from '../../assets/EcoMarket-logo.webp';

const Header = () => {
  return (
    <section className="container mt-5 mb-5">
      <div className="row d-flex align-items-center flex-column-reverse flex-lg-row">
        <div className="col-lg-6 text-center text-lg-start mt-4 mt-lg-0">
          <h1 className="display-4 fw-bold text-success mb-3">
            ¡Bienvenido a EcoMarket!
          </h1>
          <p className="lead text-muted mb-4 fs-4">
            Tu lugar favorito para productos ecológicos, saludables y responsables con el planeta 🌱.
          </p>
          <div className="d-flex justify-content-center justify-content-lg-start gap-3">
            <a className="btn btn-success d-flex align-items-center gap-2" href="#Populares">
              Ver Destacados
            <img src={flecha} alt="Flecha" width="20" height="20" className="me-2" />
            </a>
          </div>
        </div>
        <div className="col-lg-6 text-center mb-4 mb-lg-0">
          <img src={EcoMarketLogo} alt="logo-de-la-Empresa" className="img-fluid w-75 mx-auto" />
        </div>
      </div>
    </section>
  );
};

export default Header;
