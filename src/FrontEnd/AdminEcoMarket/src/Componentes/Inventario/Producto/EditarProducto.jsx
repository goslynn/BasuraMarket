import axios from 'axios';
import React, { useState } from 'react';
import { data } from 'react-router-dom';
const EditarProducto = ({ produ, cerrar })  => {
const [datosProducto, setDatosProducto] = useState({
    idProducto: produ.idProducto ,
    NombreProducto: produ.NombreProducto ,
    CodigoProducto: produ.CodigoProducto ,
    idSubFamilia: produ.idSubFamilia,
    Descripcion: produ.Descripcion ,
    Precio: produ.Precio ,
    Activo: produ.Activo ,
  });
  const handleChange = (e) => {
    const { name, value } = e.target;
    setDatosProducto({ ...datosProducto, [name]: value });
  console.log(datosProducto); 
  };
    const handleConfirmar = () => {
        axios.put(`http://localhost:8080/api/v1/public/inventario/producto/${datosProducto.idProducto}`,datosProducto)
        .then(response => {
          console.log('Elemento actualizado:', response.data);
        })
        .catch(error => {
          console.error('Error al actualizado:', error);
        });
        cerrar();
  };    

  return (
    <>
      {/* Fondo desenfocado */}
      <div
        className="position-fixed top-0 start-0 w-100 h-100"
        style={{
          backdropFilter: "blur(6px)",
          backgroundColor: "rgba(0, 0, 0, 0.5)",
          zIndex: 1040,
        }}
      />

      {/* Modal Bootstrap conservado */}
      <div
        className="modal show d-block"
        tabIndex="-1"
        role="dialog"
        style={{ zIndex: 1050 }}
      >
        <div className="modal-dialog modal-lg">
          <div className="modal-content bg-dark text-white">
            <div className="modal-header">
              <h5 className="modal-title">Editar Datos del Producto</h5>
              <button
                type="button"
                className="btn-close btn-close-white"
                onClick={cerrar}
                onChange={handleChange}
              ></button>
            </div>
            <div className="modal-body">
                        <form className="row g-3">
                        <div className="col-md-6">
                            <label  className="form-label">Nombre del producto</label>
                            <input type="text" name='NombreProducto' className="form-control" value={datosProducto.NombreProducto} onChange={handleChange}/>
                        </div>
                        <div className="col-md-6">
                            <label  className="form-label">SKU:</label>
                            <input type="text" name="CodigoProducto" className="form-control" value={datosProducto.CodigoProducto} onChange={handleChange}/>
                        </div>
                        <div className="col-md-6">
                            <label  className="form-label">Familia</label>
                             <select id="inputState" className="form-select" >
                                <option>Choose...</option>
                            </select>
                        </div>
                        <div className="col-md-6">
                            <label  className="form-label">Sub Familia:</label>
                            <select id="inputState" className="form-select">
                                <option>Choose...</option>
                            </select>
                        </div>
                        <div className="col-md-12">
                            <label  className="form-label">Descripcion</label>
                            <input type="text" name='Descripcion' className="form-control"value={datosProducto.Descripcion} onChange={handleChange}/>
                        </div>
                        <div className="col-md-6">
                            <label  className="form-label">Precio</label>
                            <input type="text" name="Precio" className="form-control" value={datosProducto.Precio} onChange={handleChange}/>
                        </div>
                        <div className="col-md-6">
                            <label  className="form-label">Estado</label>
                             <select name="Activo" id="inputState" className="form-select">
                                <option>Activo</option>
                                <option>Inactivo</option>
                            </select>
                        </div>
                    </form>
            </div>
            <div className="modal-footer">
              <button className="btn btn-secondary" onClick={cerrar}>
                Cancelar
              </button>
              <button className="btn btn-success" onClick={handleConfirmar}>
                Confirmar
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  )
}

export default EditarProducto