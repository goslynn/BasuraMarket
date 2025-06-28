const CardFiltros = ({ text, onclick }) => {


    return (
      <div className="container my-4 mt-5">
        <div className="card">
          <div className="card-body">
            <h3 className="mb-3 display-5 fw-bold text-success">Filtrar por:</h3>
            <div className="row gy-2 gx-3 align-items-center">
              <div className="col-12 col-sm-6 col-md-3">
                  <input type="text" placeholder='Buscar Producto' className='form-control' />
              </div>
              <div className="col-12 col-sm-6 col-md-3">
                <select className="form-select" aria-label="Filtrar categoría 3">
                  <option>Categoría</option>
                  <option value="1">One</option>
                  <option value="2">Two</option>
                  <option value="3">Three</option>
                </select>
              </div>
              <div className="col-12 col-sm-6 col-md-3">
                <select className="form-select" aria-label="Filtrar categoría 3">
                  <option value="">Precio</option>
                  <option value="1">De mayor a menor</option>
                  <option value="2">De menor a mayor</option>
                </select>
              </div>
              <div className="col-12 col-sm-6 col-md-3 text-sm-end">
                <button className="btn btn-success w-100" onClick={onclick}>
                  { text }
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  
};

export default CardFiltros;
