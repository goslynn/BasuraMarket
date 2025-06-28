import axios from 'axios';
import React, { useState } from 'react';
import useFetch from '../../useFetch';
const EditarSubFamilia = ({ sub, id, cerrar }) => {
  const { data: familias} = useFetch("http://localhost:8080/api/v1/public/inventario/familia");

    const [datosSubFamilia, setDatosSubFamilia] = useState(() => ({
      id_subfamilia: sub?.id_subfamilia ,
      id_familia: sub?.id_familia ,
      nombre_subfamilia: sub?.nombre_subfamilia,
      descripcion: sub?.descripcion,
    }));

  const handleChange = (e) => {
    const { name, value } = e.target;
    setDatosSubFamilia({ ...datosSubFamilia, [name]: value });
  };

  const handleConfirmar = () => {
    console.log('Datos a enviar:', datosSubFamilia);
    axios.put(`http://localhost:8080/api/v1/public/inventario/subfamilia/${sub.id_subfamilia}`, datosSubFamilia)
      .then((response) => {
        console.log('Elemento actualizado:', response.data);
      })
      .catch((error) => {
        console.error('Error al actualizar:', error);
      });
    cerrar();
  };

  return (
    <>
      {/* Fondo desenfocado */}
      <div
        className="position-fixed top-0 start-0 w-100 h-100"
        style={{
          backdropFilter: 'blur(6px)',
          backgroundColor: 'rgba(0, 0, 0, 0.5)',
          zIndex: 1040,
        }}
      />

      {/* Modal Bootstrap */}
      <div className="modal show d-block" tabIndex="-1" role="dialog" style={{ zIndex: 1050 }}>
        <div className="modal-dialog modal-lg">
          <div className="modal-content bg-dark text-white">
            <div className="modal-header">
              <h5 className="modal-title">Editar Datos de la Subfamilia</h5>
              <button
                type="button"
                className="btn-close btn-close-white"
                onClick={cerrar}
              ></button>
            </div>
            <div className="modal-body">
              <div className="row g-3">
                <div className="col-md-6">
                  <label className="form-label">Nombre de la Subfamilia</label>
                  <input
                    type="text"
                    name="nombre_subfamilia"
                    className="form-control"
                    value={datosSubFamilia.nombre_subfamilia}
                    onChange={handleChange}
                  />
                </div>

                <div className="col-md-12">
                  <label className="form-label">Descripción</label>
                  <input
                    type="text"
                    name="descripcion"
                    className="form-control"
                    value={datosSubFamilia.descripcion}
                    onChange={handleChange}
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
                    <label  className="form-label">Estado:</label>
                      <select id="inputState" className="form-select">
                        <option value={true}>Activo</option>
                        <option value={false}>Inactivo</option>
                      </select>
                </div>
              </div>
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
  );
};

export default EditarSubFamilia;
