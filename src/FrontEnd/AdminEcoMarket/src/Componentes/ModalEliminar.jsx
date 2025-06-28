import axios from 'axios';

const ModalEliminar = ({ cerrar, item, op }) => {  
  const handleConfirmar = async () => {
    try {
      if (op === 1) {
        await axios.delete(`http://localhost:8080/api/v1/public/inventario/producto/${item.idProducto}`);
        console.log('Producto eliminado');
      } else if (op === 2) {
        await axios.delete(`http://localhost:8080/api/v1/public/inventario/familia/${item.id_familia}`);
        console.log('Familia eliminada');
      } else if (op === 3) {
        await axios.delete(`http://localhost:8080/api/v1/public/inventario/subfamilia/${item.id_subfamilia}`);
        console.log('Subfamilia eliminada');
      }
    } catch (error) {
      console.error('Error al eliminar:', error);
    } finally {
      cerrar();
    }
  };

  return (
    <>
      <div
        className="position-fixed top-0 start-0 w-100 h-100"
        style={{
          backdropFilter: "blur(6px)",
          backgroundColor: "rgba(0, 0, 0, 0.5)",
          zIndex: 1040,
        }}
      />
      <div
        className="modal show d-block"
        tabIndex="-1"
        role="dialog"
        style={{ zIndex: 1050 }}
      >
        <div className="modal-dialog">
          <div className="modal-content bg-dark text-white">
            <div className="modal-header">
              <h5 className="modal-title">Confirmar eliminación</h5>
              <button
                type="button"
                className="btn-close btn-close-white"
                onClick={cerrar}
              ></button>
            </div>
            <div className="modal-body">
              <p>
                ¿Estás seguro que deseas eliminar{" "}
                <strong>
                  {item?.nombre_familia ||
                    item?.NombreProducto ||
                    item?.nombre_subfamilia}
                </strong>
                ?
              </p>
            </div>
            <div className="modal-footer">
              <button className="btn btn-secondary" onClick={cerrar}>
                Cancelar
              </button>
              <button className="btn btn-danger" onClick={handleConfirmar}>
                Eliminar
              </button>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};

export default ModalEliminar;
