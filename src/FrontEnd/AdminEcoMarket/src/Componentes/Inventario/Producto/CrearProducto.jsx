import React, { useState } from 'react';
import axios from 'axios';
import useFetch from '../../useFetch';

const CrearProducto = () => {
  const { data: familias, cargando: cargandoFamilias } = useFetch("http://localhost:8080/api/v1/public/inventario/familia");
  const { data: subfamilias, cargando: cargandoSubfamilias } = useFetch("http://localhost:8080/api/v1/public/inventario/subfamilia");

  const [datosProducto, setDatosProducto] = useState({
    idProducto: "",
    NombreProducto: "",
    CodigoProducto: "",
    idSubFamilia: "",
    Descripcion: "",
    Precio: "",
    Activo: true,
    imagen: null,
  });

  const handleChange = (e) => {
    const { name, value } = e.target;

    if (name === "Activo") {
      setDatosProducto({ ...datosProducto, [name]: value === "true" });
    } else {
      setDatosProducto({ ...datosProducto, [name]: value });
    }
  };

  const handleImagen = (e) => {
    setDatosProducto({ ...datosProducto, imagen: e.target.files[0] });
  };
  const vaciarDatos = () => {
    setDatosProducto({ 
    idProducto: "",
    NombreProducto: "",
    CodigoProducto: "",
    idSubFamilia: "",
    Descripcion: "",
    Precio: "",
    Activo: true,
    imagen: null,
    });
  }


  const handleSubmit = (e) => {
    e.preventDefault();
    axios.post("http://localhost:8080/api/v1/public/inventario/producto", datosProducto)
      .then(response => {
        console.log("Producto creado exitosamente:", response.data);
        vaciarDatos();
        alert("Producto creado exitosamente");
      })
      .catch(error => {   
        console.error("Error al crear el producto:", error);
      }
    );

    // Aquí puedes hacer una petición POST al backend
  };

  return (
    <div className="container mt-5">
      <div className="row g-4 p-4 mb-5 shadow-lg rounded bg-white">
        <div className="row g-3" >
          <div className="col-12">
            <h2 className="text-success fw-bold">Agrega un nuevo producto</h2>
            <p>Detalles básicos del producto</p>
          </div>

          <div className="border rounded p-3 mb-3 row g-3">
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

            <div className="col-md-6">
              <label className="form-label fw-bold">Familia</label>
              <select
                className="form-select"
                name="idFamilia"
                onChange={handleChange}
              >
                <option value="">Seleccione una familia...</option>
                {familias && familias.map((fami) => (
                  <option key={fami.id_familia} value={fami.id_familia}>
                    {fami.nombre_familia}
                  </option>
                ))}
              </select>
            </div>

            <div className="col-md-6">
              <label className="form-label fw-bold">Subfamilia</label>
              <select
                className="form-select"
                name="idSubFamilia"
                onChange={handleChange}
                required
              >
                <option value="">Seleccione una subfamilia...</option>
                {subfamilias && subfamilias.map((sub) => (
                  <option key={sub.id_subfamilia} value={sub.id_subfamilia}>
                    {sub.nombre_subfamilia}
                  </option>
                ))}
              </select>
            </div>

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
                <option value={true}>Activo</option>
                <option value={false}>Inactivo</option>
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

          <div className="col-12 card p-3">
            <label className="form-label fw-bold">Imagen del Producto</label>
            <input
              type="file"
              className="form-control"
              name="imagen"
              accept="image/*"
              onChange={handleImagen}
            />
          </div>

          <div className="col-12 mt-4 d-flex gap-2">
            <button type="submit" className="btn btn-success" onClick={handleSubmit}>Crear Producto</button>
            <button type="button" className="btn btn-secondary">Vista Previa</button>
            <a href="/inventario/producto" className="btn btn-danger ms-auto">Volver</a>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CrearProducto;
