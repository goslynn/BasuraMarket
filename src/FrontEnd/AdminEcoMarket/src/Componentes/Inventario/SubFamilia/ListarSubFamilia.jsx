import React, { useState } from 'react';
import useFetch from '../../useFetch';
import ModalEliminar from "../../ModalEliminar.jsx";
import Skeleton from "react-loading-skeleton";
import 'react-loading-skeleton/dist/skeleton.css';
import EditarSubFamilia from './EditarSubFamilia.jsx';

const ListarSubFamilia = () => {
  const { data: subFamilias, cargando } = useFetch("http://localhost:8080/api/v1/public/inventario/subfamilia");

  const [mostrar, setMostrar] = useState(false);
  const [mostrarEdicion, setMostrarEdicion] = useState(false);
  const [subFamilia, setSubFamilia] = useState(null);

  const mostrarModal = (sub) => {
    setSubFamilia(sub);
    setMostrar(true);
  };

  const mostrarModalEditar = (sub) => {
    
    setSubFamilia(sub);
    setMostrarEdicion(true);
  };

  const cerrarModal = () => {
    setSubFamilia(null);
    setMostrar(false);
  };

  const cerrarModalEditar = () => {
    setSubFamilia(null);
    setMostrarEdicion(false);
  };

  const mostrarSkeleton = () => (
    <table className="table table-bordered table-success table-hover mt-3">
      <thead>
        <tr>
          {[...Array(8)].map((_, i) => (
            <th key={i}><Skeleton /></th>
          ))}
        </tr>
      </thead>
      <tbody>
        {[...Array(3)].map((_, rowIdx) => (
          <tr key={rowIdx}>
            {[...Array(7)].map((_, colIdx) => (
              <td key={colIdx}><Skeleton /></td>
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
      {mostrar && subFamilia && (
        <ModalEliminar
          cerrar={cerrarModal}
          item={subFamilia}
          op={3} // 3 para subfamilia
        />
      )}

      {mostrarEdicion && subFamilia && (
        <EditarSubFamilia
          cerrar={cerrarModalEditar}
          sub={subFamilia}
          id={subFamilia.id_subfamilia}
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
                <a className="btn btn-success w-100" href="/inventario/subfamilia/crear-subfamilia">
                  Crear
                </a>
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
              <th>Familia</th>
              <th>Editar</th>
            </tr>
          </thead>
          <tbody>
            {subFamilias && subFamilias.map((sub, i) => (
              <tr key={sub.id_subfamilia}>
                <td>{i + 1}</td>
                <td>{sub.nombre_subfamilia}</td>
                <td>{sub.descripcion}</td>
                <td>{sub.id_familia}</td>
                <td>
                  <button className="btn btn-success" onClick={() => mostrarModalEditar(sub)}>Editar</button>
                  <button className="btn btn-danger ms-2" onClick={() => mostrarModal(sub)}>Borrar</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}
    </>
  );
};

export default ListarSubFamilia;
