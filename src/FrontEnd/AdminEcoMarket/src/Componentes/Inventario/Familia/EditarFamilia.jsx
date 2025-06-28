import axios from 'axios';
import React, { useEffect, useState } from 'react';

const EditarProducto = ({ fami, cerrar }) => {
  const [datosFamilia, setDatosFamilia] = useState({
    id_familia: "",
    nombre_familia: "",
    descripcion: "",
  });

  useEffect(() => {
    if (fami) {
      setDatosFamilia({
        id_familia: fami.id_familia ,
        nombre_familia: fami.nombre_familia ,
        descripcion: fami.descripcion ,
      });
    }
  }, [fami]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setDatosFamilia({ ...datosFamilia, [name]: value });
  };

  const handleConfirmar = () => {
    console.log('Datos a enviar:', datosFamilia);
    
    axios.put(`http://localhost:8080/api/v1/public/inventario/familia/${fami.id_familia}`, datosFamilia)
      .then(response => {
        console.log('Elemento actualizado:', response.data);
      })
      .catch(error => {
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
          backdropFilter: "blur(6px)",
          backgroundColor: "rgba(0, 0, 0, 0.5)",
          zIndex: 1040,
        }}
      />

      {/* Modal */}
      <div className="modal show d-block" tabIndex="-1" role="dialog" style={{ zIndex: 1050 }}>
        <div className="modal-dialog modal-lg">
          <div className="modal-content bg-dark text-white">
            <div className="modal-header">
              <h5 className="modal-title">Editar Datos de la Familia</h5>
              <button
                type="button"
                className="btn-close btn-close-white"
                onClick={cerrar}
              ></button>
            </div>
            <div className="modal-body">
              <form className="row g-3">
                <div className="col-md-6">
                  <label className="form-label">Nombre</label>
                  <input
                    type="text"
                    name="nombre_familia"
                    className="form-control"
                    value={datosFamilia.nombre_familia}
                    onChange={handleChange}
                  />
                </div>
                <div className="col-md-12">
                  <label className="form-label">Descripción</label>
                  <input
                    type="text"
                    name="descripcion"
                    className="form-control"
                    value={datosFamilia.descripcion}
                    onChange={handleChange}
                  />
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
  );
};

export default EditarProducto;
