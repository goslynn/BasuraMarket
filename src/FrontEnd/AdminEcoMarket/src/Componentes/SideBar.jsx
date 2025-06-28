import { Link } from 'react-router-dom';
import informes from '../assets/svg/informes.svg';
import userIcon from '../assets/svg/users.svg';
import sucursales from '../assets/svg/sucursales.svg';
import inventario from '../assets/svg/inventario.svg';
import volver from '../assets/svg/volver.svg';
import Logo from '../assets/logo_tipografico.png';

export const SideBar = () => {
  return (
    <div className="sidebar bg-success sidebar-narrow-unfoldable border-end">
      <div className="sidebar-header border-bottom">
        <div className="sidebar-brand">
          <img src={Logo} width={"40px"} />
        </div>
      </div>
      <ul className="sidebar-nav">
        <li className="nav-title text-white">Administrar Servicios:</li>
        <li className="nav-item">
          <div className="nav-link d-flex align-items-center text-white sidebar-item">
            <img src={inventario} alt="" width={"20px"} />
            <i className="nav-icon  text-white"></i> Inventario
          </div>
          <ul className="nav-group-items ms-4">
            <li className="nav-item sidebar-item text-white sidebar-item">
              <Link to="inventario/producto" className="nav-link text-white">Producto</Link>
            </li>
            <li className="nav-item text-white sidebar-item">
              <Link to="inventario/familia" className="nav-link text-white">Familia</Link>
            </li>
            <li className="nav-item text-white sidebar-item">
              <Link to="inventario/subfamilia" className="nav-link text-white">Subfamilia</Link>
            </li>
          </ul>
        </li>
        <li className="nav-item show sidebar-item">
          <Link to="informes" className="nav-link text-white sidebar-item">
            <img src={informes} alt="" width={"20px"} />
            <i className="nav-icon" ></i> Informes
          </Link>
        </li>
        <li className="nav-item sidebar-item">
          <Link to="control" className="nav-link text-white">
            <img src={userIcon} alt="" width={"20px"} />
            <i className="nav-icon "></i> Usuarios y roles
          </Link>
        </li>

        <li className="nav-item show text-white sidebar-item">
          <Link to="sucursales" className="nav-link text-white">
            <img src={sucursales} alt="" width={"20px"} />
            <i className="nav-icon "></i> Sucursales
          </Link>
        </li>

        <li className="nav-item mt-auto text-white">
          <Link to="/" className="nav-link text-white">
            <img src={volver} alt="" width={"20px"} />
            <i className="nav-icon "></i> Volver a Tienda
          </Link>
        </li>
      </ul>
    </div>
  );
};

export default SideBar;