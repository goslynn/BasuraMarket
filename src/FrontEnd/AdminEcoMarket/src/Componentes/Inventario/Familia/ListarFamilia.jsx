import useFetch from '../../useFetch';
import React, { useState } from 'react';
import ModalEliminar from "../../ModalEliminar.jsx";
import Skeleton from "react-loading-skeleton";
import 'react-loading-skeleton/dist/skeleton.css'; 
import EditarFamilia from './EditarFamilia.jsx';
import { Link } from 'react-router-dom';

const ListarFamilia = () => {
  const { data: familias, cargando } = useFetch("http://localhost:8080/api/v1/public/inventario/familia");

  const [mostrar, setMostrar] = useState(false);
  const [mostrarEdicion, setMostrarEdicion] = useState(false);
  const [familia, setFamilia] = useState(null); // Este será el item seleccionado para eliminar/editar

  const mostrarModal = (fami) => {
    setFamilia(fami);
    setMostrar(true);
  };

  const mostrarModalEditar = (fami) => {    
    setFamilia(fami);
    setMostrarEdicion(true);
  };

  const cerrarModal = () => {
    setFamilia(null);
    setMostrar(false);
  };

  const cerrarModalEditar = () => {
    setFamilia(null);
    setMostrarEdicion(false);
  };

  const mostrarSkeleton = () => (
    <table className="table table-bordered table-success table-hover mt-3">
      <thead>
        <tr>
          {[...Array(4)].map((_, i) => (
            <th key={i}><Skeleton /></th>
          ))}
        </tr>
      </thead>
      <tbody>
        {[...Array(3)].map((_, row) => (
          <tr key={row}>
            {[...Array(3)].map((_, col) => (
              <td key={col}><Skeleton /></td>
            ))}
            <td>
              <button className="btn"><Skeleton /></button>
              <button className="btn ms-3"><Skeleton /></button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );

  return (
    <>
      {mostrar && (
        <ModalEliminar
          cerrar={cerrarModal}
          item={familia}
          op={2} // 2 para familia
        />
      )}

      {mostrarEdicion && (
        <EditarFamilia
          cerrar={cerrarModalEditar}
          fami={familia}
        />
      )}

      <div className="container my-4 mt-5">
        <div className="card">
          <div className="card-body">
            <h3 className="mb-3 display-5 fw-bold text-success">Filtrar por:</h3>
            <div className="row gy-2 gx-3 align-items-center">
              <div className="col-12 col-sm-6 col-md-3">
                <input type="text" placeholder='Buscar Producto' className='form-control' />
              </div>
              <div className="col-12 col-sm-6 col-md-3">
                <select className="form-select" aria-label="Filtrar categoría">
                  <option>Categoría</option>
                  <option value="1">One</option>
                  <option value="2">Two</option>
                  <option value="3">Three</option>
                </select>
              </div>
              <div className="col-12 col-sm-6 col-md-3">
                <select className="form-select" aria-label="Filtrar precio">
                  <option value="">Precio</option>
                  <option value="1">De mayor a menor</option>
                  <option value="2">De menor a mayor</option>
                </select>
              </div>
              <div className="col-12 col-sm-6 col-md-3 text-sm-end">
                <Link className="btn btn-success w-100" to="/inventario/familia/crear-familia">
                  Crear
                </Link>
              </div>
            </div>
          </div>
        </div>
      </div>

      {cargando ? mostrarSkeleton() : (
        <table className="table table-bordered table-success table-hover mt-3">
          <thead>
            <tr>
              <th>#</th>
              <th>Nombre</th>
              <th>Descripción</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {familias && familias.map((fami, i) => (
              <tr key={fami.id_familia}>
                <td>{i + 1}</td>
                <td>{fami.nombre_familia}</td>
                <td>{fami.descripcion}</td>
                <td>
                  <button className="btn btn-success" onClick={() => mostrarModalEditar(fami)}>Editar</button>
                  <button className="btn btn-danger ms-2" onClick={() => mostrarModal(fami)}>Borrar</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </>
  );
};

export default ListarFamilia;
