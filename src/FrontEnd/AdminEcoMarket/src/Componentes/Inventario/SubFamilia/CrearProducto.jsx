import React, { useState } from 'react';

const CrearProducto = () => {
  const [datosProducto, setDatosProducto] = useState({
    idProducto: "",
    NombreProducto: "",
    CodigoProducto: "",
    idSubFamilia: "",
    Descripcion: "",
    Precio: "",
    Activo: true,
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setDatosProducto({ ...datosProducto, [name]: value });
    console.log(datosProducto);
  };

  return (
    <div className="container mt-5 ">
      <div className="row g-4 p-4 mb-5 shadow-lg rounded bg-white ">
        <form className="row g-3 ">
          <div className="col-12 m-0">
            <h2>Agrega un nuevo producto</h2>
            <p>Detalles básicos del producto</p>
          </div>

          <div className="border rounded p-3 mb-3 row g- mt-0">
            <div className="col-md-6">
              <label className="form-label fw-bold">Nombre del producto</label>
              <input
                type="text"
                name="NombreProducto"
                className="form-control"
                placeholder="Ej: Jabón líquido"
                value={datosProducto.NombreProducto}
                onChange={handleChange}
                required
              />
            </div>

            <div className="col-md-6">
              <label className="form-label fw-bold">SKU</label>
              <input
                type="text"
                name="CodigoProducto"
                className="form-control"
                placeholder="Ej: SKU123456"
                value={datosProducto.CodigoProducto}
                onChange={handleChange}
                required
              />
            </div>

            {/* Clasificación */}
            <div className="col-md-6">
              <label className="form-label fw-bold">Familia</label>
              <select
                className="form-select"
                name="idFamilia"
                onChange={handleChange}
                required
              >
                <option value="">Seleccione una familia...</option>
                <option value="1">Vegano</option>
                <option value="2">Vegetariano</option>
                {/* Agrega más según tu backend */}
              </select>
            </div>

            <div className="col-md-6">
              <label className="form-label fw-bold">Sub Familia</label>
              <select
                className="form-select"
                name="idSubFamilia"
                onChange={handleChange}
                required
              >
                <option value="">Seleccione una subfamilia...</option>
                <option value="10">Sin lactosa</option>
                <option value="11">Sin gluten</option>
                {/* Agrega más según tu backend */}
              </select>
            </div>

            {/* Atributos del Producto */}
            <div className="col-md-6">
              <label className="form-label fw-bold">Precio</label>
              <input
                type="number"
                name="Precio"
                className="form-control"
                placeholder="Ej: 19990"
                value={datosProducto.Precio}
                onChange={handleChange}
                required
              />
            </div>

            <div className="col-md-6">
              <label className="form-label fw-bold">Estado</label>
              <select
                name="Activo"
                className="form-select"
                value={datosProducto.Activo}
                onChange={handleChange}
              >
                <option value="Activo">Activo</option>
                <option value="Inactivo">Inactivo</option>
              </select>
            </div>

            <div className="col-12">
              <label className="form-label fw-bold">Descripción</label>
              <textarea
                name="Descripcion"
                className="form-control"
                placeholder="Ej: Producto biodegradable, apto para uso diario..."
                rows="3"
                value={datosProducto.Descripcion}
                onChange={handleChange}
              />
            </div>
          </div>

          {/* Imagen del producto */}
          <div className="col-12 card p-3">
            <label className="form-label fw-bold">Imagen del Producto</label>
            <label className="form-label text-muted">Subir imagen del producto</label>
            <input
              type="file"
              className="form-control"
              name="imagen"
              accept="image/*"
              onChange={handleChange}
            />
          </div>

          {/* Botones */}
          <div className="col-12 mt-4 d-flex gap-2">
            <button type="submit" className="btn btn-success">Crear Producto</button>
            <button type="button" className="btn btn-secondary">Vista Previa</button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default CrearProducto;
