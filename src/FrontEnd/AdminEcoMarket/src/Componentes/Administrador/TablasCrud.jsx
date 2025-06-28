import { useState, useEffect } from "react";
import { useFetch } from "./useFetch";
import Skeleton from "react-loading-skeleton";
import ModalEliminar from "../ModalEliminar.jsx";
import axios from "axios";

const TablaUsuarios = ({ tipo }) => {
  const [cargando, setCargando] = useState(true);
  const [mostrar, setMostrar] = useState(false);
  const [actualizar, setActualizar] = useState(false);
  const [elementoSeleccionado, setElementoSeleccionado] = useState(null);

  const usuarios = useFetch("http://localhost:8080/api/v1/public/usuario/getusuarios");
  const productos = useFetch("http://localhost:8080/api/v1/public/inventario/producto");
  const familias = useFetch("http://localhost:8080/api/v1/public/inventario/familia");
  const subfamilias = useFetch("http://localhost:8080/api/v1/public/inventario/subfamilia");

  useEffect(() => {
    if (
      usuarios.data &&
      productos.data &&
      familias.data &&
      subfamilias.data
    ) {
      setCargando(false);
    }
  }, [usuarios.data, productos.data, familias.data, subfamilias.data]);

  const mostrarModal = (item) => {
    setElementoSeleccionado(item);
    setMostrar(true);
  };

  const cerrarModal = () => {
    setElementoSeleccionado(null);
    setMostrar(false);
  };

  if (cargando) {
    return (
      <table className="table table-striped mt-3">
        <thead className="table-success">
          <tr>
            {[...Array(7)].map((_, i) => (
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
              <button className="btn btn-success"><Skeleton /></button>
              <button className="btn btn-danger ms-3"><Skeleton /></button>
            </td>
          </tr>
        </tbody>
      </table>
    );
  }

  return (
    <>
      {mostrar && (
        <ModalEliminar
          cerrar={cerrarModal}
          item={elementoSeleccionado}
          tipo={tipo}
        />
      )}

      {tipo === 1 && (
        <table className="table table-striped mt-3">
          <thead className="table-success">
            <tr>
              <th>#</th>
              <th>Nombre</th>
              <th>Correo</th>
              <th>Género</th>
              <th>Rol</th>
              <th>Fecha de Creación</th>
              <th>Activo</th>
              <th>Editar</th>
            </tr>
          </thead>
          <tbody>
            {usuarios.data.map((user, index) => (
              <tr key={user.idUsuario}>
                <td>{index + 1}</td>
                <td>{user.nombre}</td>
                <td>{user.correo}</td>
                <td>{user.genero}</td>
                <td>{user.rol}</td>
                <td>{user.fechaCreacion}</td>
                <td>{user.activo ? "Sí" : "No"}</td>
                <td>
                  <button className="btn btn-success">Editar</button>
                  <button className="btn btn-danger ms-3" onClick={() => mostrarModal(user)}>Eliminar</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      {tipo === 2 && (
        <table className="table table-bordered table-dark table-hover mt-3">
          <thead>
            <tr>
              <th>#</th>
              <th>Código</th>
              <th>Nombre</th>
              <th>Descripción</th>
              <th>Precio</th>
              <th>Subfamilias</th>
              <th>Activo</th>
              <th>Editar</th>
            </tr>
          </thead>
          <tbody>
            {productos.data.map((produ, i) => (
              <tr key={produ.idProducto}>
                <td>{i + 1}</td>
                <td>{produ.CodigoProducto}</td>
                <td>{produ.NombreProducto}</td>
                <td>{produ.Descripcion}</td>
                <td>{produ.Precio}</td>
                <td>{produ.idSubFamilia}</td>
                <td>{produ.Activo ? "Sí" : "No"}</td>
                <td>
                  <button className="btn btn-success">Editar</button>
                  <button className="btn btn-danger ms-2" onClick={() => mostrarModal(produ)}>Borrar</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      {tipo === 3 && (
        <table className="table table-bordered table-dark table-hover mt-3">
          <thead>
            <tr>
              <th>#</th>
              <th>Nombre</th>
              <th>Descripción</th>
              <th>Editar</th>
            </tr>
          </thead>
          <tbody>
            {familias.data.map((fam, i) => (
              <tr key={fam.id_familia}>
                <td>{i + 1}</td>
                <td>{fam.nombre_familia}</td>
                <td>{fam.descripcion}</td>
                <td>
                  <button className="btn btn-success">Editar</button>
                  <button className="btn btn-danger ms-2" onClick={() => mostrarModal(fam)}>Borrar</button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      )}

      {tipo === 4 && (
        <table className="table table-bordered table-dark table-hover mt-3">
          <thead>
            <tr>
              <th>#</th>
              <th>Nombre</th>
              <th>Descripción</th>
              <th>ID familia</th>
              <th>Activo</th>
              <th>Editar</th>
            </tr>
          </thead>
          <tbody>
            {subfamilias.data.map((sub, i) => (
              <tr key={sub.id_subfamilia}>
                <td>{i + 1}</td>
                <td>{sub.nombre_subfamilia}</td>
                <td>{sub.descripcion}</td>
                <td>{sub.id_familia}</td>
                <td>{sub.activo ? "Sí" : "No"}</td>
                <td>
                  <button className="btn btn-success">Editar</button>
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

export default TablaUsuarios;
