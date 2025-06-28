import { Link } from 'react-router-dom';
import menu from '../../assets/svg/menu.svg'
import informes from '../../assets/svg/informes.svg'
import userIcon from '../../assets/svg/users.svg'
import sucursales from '../../assets/svg/sucursales.svg'
import inventario from '../../assets/svg/inventario.svg'
import volver from '../../assets/svg/volver.svg'


 export const SideBar = () => {
  // Dentro del componente:

  return (
<div className="sidebar sidebar-dark sidebar-narrow-unfoldable border-end" >
  <div className="sidebar-header border-bottom">
    <div className="sidebar-brand">
      <img src={menu} width={"40px"} />
    </div>
  </div>
  <ul className="sidebar-nav">
    <li className="nav-title">Administrar Servicios:  </li>
        <li className="nav-item show">
      <Link to="informes" className="nav-link " >
        <img src={informes} alt="" width={"20px"} />
        <i className="nav-icon cil-puzzle"></i> Informes 
      </Link >
    </li>
        <li className="nav-item">
      <Link to="inventario" className="nav-link">
        <img src={inventario} alt="" width={"20px"} />
        <i className="nav-icon cil-speedometer"></i>  Inventario
      </Link >
    </li>
    <li className="nav-item" >
      <Link to="usuarios" className="nav-link" >
        <img src={userIcon} alt="" width={"20px"} />
        <i className="nav-icon cil-speedometer"></i> Usuarios
      </Link>
    </li>
    <li className="nav-item show">
      <Link to="sucursales" className="nav-link " >
        <img src={sucursales} alt="" width={"20px"} />
        <i className="nav-icon cil-puzzle"></i> Sucursales
      </Link >
    </li>

    <li className="nav-item mt-auto">
      <Link to="/" className="nav-link" >
        <img src={volver} alt="" width={"20px"} />
        <i className="nav-icon cil-cloud-download"></i> Volver a Tienda
        </Link >
    </li>
    
  </ul>
</div>
  )
}

export default SideBar