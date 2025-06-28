import SideBar from '../Componentes/SideBar'
import '../css/AdminStyle.css'
import { Outlet } from 'react-router-dom'
const Administrador = () => {
  return (

    <section>      
        <SideBar />
      <div className='ps-5'>
        <div className="admin-content ps-4" style={{ flex: 1, padding: '1rem' }}>
          <Outlet />
        </div>
      </div>
    </section>

  )
}

export default Administrador