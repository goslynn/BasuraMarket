import React from 'react'
import flecha from '../../assets/svg/flecha.svg';
import EcoMarketLogo from '../../assets/EcoMarket-logo.webp';

const Dashboard = () => {
  return (
    <section className="container d-flex justify-content-center mt-5 mb-5">
          <div className="row d-flex align-items-center flex-column-reverse flex-lg-row">
            <div className="col-lg-6 text-center text-lg-start mt-4 mt-lg-0">
              <h1 className="display-1 fw-bold text-success mb-3">
                ¡Bienvenido Administrador!
              </h1>
              <p className="lead text-muted mb-4 fs-4">
                Administra, crea, elimina y edita toda la información de tu negocio.
              </p>

            </div>
            <div className="col-lg-6 text-center mb-4 mb-lg-0">
              <img src={EcoMarketLogo} alt="logo-de-la-Empresa" className="img-fluid w-75 mx-auto" />
            </div>
          </div>
        </section>
  )
}
export default Dashboard