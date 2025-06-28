import React, { useState } from 'react';
import useFetch from '../../useFetch'; 
import ModalEliminar from "../../ModalEliminar.jsx";
import Skeleton from "react-loading-skeleton";
import 'react-loading-skeleton/dist/skeleton.css'; 
import EditarProducto from './EditarProducto.jsx';
import { Link } from 'react-router-dom';

const ListarProductos = () => {
  const { data: productos, cargando: cargando } = useFetch("http://localhost:8080/api/v1/public/inventario/producto");
  const { data: subfamilias, cargando: cargandoSubfamilias } = useFetch("http://localhost:8080/api/v1/public/inventario/subfamilia");

  const [mostrar, setMostrar] = useState(false);
  const [mostrarEdicion, setMostrarEdicion] = useState(false);
  const [producto, setProducto] = useState(null);

  const mostrarModal = (produ) => {
    setProducto(produ);
    setMostrar(true,);
  };
  const mostrarModalEditar = (produ) => {
    setProducto(produ);

    setMostrarEdicion(true,);
  };
  const cerrarModal = () => {
    setProducto(null);
    setMostrar(false);
  };
    const cerrarModalEditar = () => {
    setProducto(null);
    setMostrarEdicion(false);
  };

  const handleCrear = () => {
    // Acción al hacer clic en "Crear"
    console.log("Crear producto");
  };
const mostrarSkeleton = () => {
    if (cargando) {
        return (
            <table className="table table-bordered table-success table-hover mt-3">
              <thead className="">
                <tr>
                  {[...Array(8)].map((_, i) => (
                    <th key={i}><Skeleton /></th>
                  ))}
                </tr>
              </thead>
              <tbody>
                <tr>
                  {[...Array(7)].map((_, i) => (
                    <td key={i}><Skeleton /></td>
                  ))}
                  <td>
                    <button className="btn "><Skeleton /></button>
                    <button className="btn  ms-3"><Skeleton /></button>
                  </td>
                </tr><tr>
                  {[...Array(7)].map((_, i) => (
                    <td key={i}><Skeleton /></td>
                  ))}
                  <td>
                    <button className="btn "><Skeleton /></button>
                    <button className="btn  ms-3"><Skeleton /></button>
                  </td>
                </tr><tr>
                  {[...Array(7)].map((_, i) => (
                    <td key={i}><Skeleton /></td>
                  ))}
                  <td>
                    <button className="btn "><Skeleton /></button>
                    <button className="btn  ms-3"><Skeleton /></button>
                  </td>
                </tr>
              </tbody>
            </table>
    );
    
  }

}

  return (
    <>
      {mostrar && (
        console.log("1", producto),
        <ModalEliminar
          cerrar={cerrarModal}
          item={producto}
          op={1} // 1 para producto
        />
      )}
      {mostrarEdicion && (
        <EditarProducto
          cerrar={cerrarModalEditar}
          produ={producto}
        />
      )}

      <div className="container my-4 mt-5  ">
        <div className="card">
          <div className="card-body">
            <h3 className="mb-3 display-5 fw-bold text-success">Filtrar por:</h3>
            <div className="row gy-2 gx-3 align-items-center">
              <div className="col-12 col-sm-6 col-md-3">
                <input type="text" placeholder='Buscar Producto' className='form-control' />
              </div>
              <div className="col-12 col-sm-6 col-md-3">
              <select className="form-select" name="idFamilia" >
                <option value="">Seleccione una familia...</option>
                {subfamilias && subfamilias.map((sub) => (
                  <option key={sub.id_subfamilia} value={sub.id_subfamilia}>
                    {sub.nombre_subfamilia}
                  </option>
                ))}
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
                <a className="btn btn-success w-100" href="/inventario/producto/crear-producto">
                  Crear
                </a>
              </div>
            </div>
          </div>
        </div>
      </div>
      {cargando ? mostrarSkeleton() : 
      <table className="table table-bordered table-success table-hover mt-3">
        <thead>
          <tr>
            <th>#</th>
            <th>Código</th>
            <th>Nombre</th>
<th style={{ maxWidth: '100px', whiteSpace: 'nowrap', overflow: 'hidden', textOverflow: 'ellipsis' }}>
  Descripción
</th>
            <th>Precio</th>
            <th>Subfamilias</th>
            <th>Activo</th>
            <th>Editar</th>
          </tr>
        </thead>
        <tbody>
          {productos && productos.map((produ, i) => (
            <tr key={produ.idProducto}>
              <td>{i + 1}</td>
              <td>{produ.CodigoProducto}</td>
              <td>{produ.NombreProducto}</td>
              <td>{produ.Descripcion}</td>
              <td>{produ.Precio}</td>
              <td>{produ.idSubFamilia}</td>
              <td>{produ.Activo ? "Sí" : "No"}</td>
              <td style={{ maxWidth: '200px', whiteSpace: 'nowrap', overflow: 'hidden', textOverflow: 'ellipsis' }}>
                <button className="btn btn-success" onClick={() => mostrarModalEditar(produ)}>Editar</button>
                <button className="btn btn-danger ms-2" onClick={() => mostrarModal(produ)}>Borrar</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
}
    </>
  );
};

export default ListarProductos;
