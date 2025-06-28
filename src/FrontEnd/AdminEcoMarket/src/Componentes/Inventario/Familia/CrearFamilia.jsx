import React, { useState } from 'react';
import axios from 'axios';
const CrearProducto = () => {

  const [datosFamilia, setdatosFamilia] = useState({
    nombre_familia: "",
    Descripcion: "",
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setdatosFamilia({ ...datosFamilia, [name]: value });
  };

  const vaciarDatos = () => {
    setdatosFamilia({ 
    nombre_familia: "",
    Descripcion: "",
    });
  }


  const handleSubmit = (e) => {
    e.preventDefault();
    axios.post("http://localhost:8080/api/v1/public/inventario/familia", datosFamilia)
      .then(response => {
        console.log("Producto creado exitosamente:", response.data);
        vaciarDatos();
        alert("Producto creado exitosamente");
      })
      .catch(error => {   
        console.error("Error al crear el producto:", error);
      }
    );
  }
  return (
    <div className="container mt-5">
      <div className="row g-4 p-4 mb-5 shadow-lg rounded bg-white">
        <form className="row g-3" onSubmit={handleSubmit}>
          <div className="col-12 m-0">
            <h2>Agrega una nueva Familia</h2>
            <p>Detalles básicos del producto</p>
          </div>

          <div className="border rounded p-3 mb-3 row g- mt-0">
            <div className="col-md-6">
              <label className="form-label fw-bold">Nombre del producto</label>
              <input
                type="text"
                name="nombre_familia"
                className="form-control"
                placeholder="Ej: Jabón líquido"
                value={datosFamilia.nombre_familia}
                onChange={handleChange}
                required
              />
            </div>

            <div className="col-md-6">
              <label className="form-label fw-bold">Descripcion</label>
              <input
                type="text"
                name="descripcion"
                className="form-control"
                placeholder="Ej: Producto de con facil Biodegradación"
                value={datosFamilia.descripcion}
                onChange={handleChange}
                required
              />
            </div>
          </div>

          <div className="col-12 mt-4 d-flex gap-2">
            <button type="submit" className="btn btn-success">Crear Producto</button>
            <a type="button" href='/inventario/familia' className="btn btn-secondary">Volver</a>
          </div>
        </form>
      </div>
    </div>
  );
};

export default CrearProducto;
