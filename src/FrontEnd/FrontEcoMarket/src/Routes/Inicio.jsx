
import Populares from '../Componentes/Inicio/Populares.jsx';
import Header from '../Componentes/Inicio/Header.jsx';
import Opiniones from '../Componentes/Inicio/Opiniones.jsx';
import Mapa from '../Componentes/Inicio/Mapa.jsx';
import { Navigate, useNavigate } from 'react-router-dom';

export function Inicio() {
    const navegacion = () =>{
        navigate("/Mapa");
    }
    return (
        <div className="container text-center py-5">
            
            <Header></Header>
            <Populares></Populares>
            <div id="Opiniones">
                <Opiniones></Opiniones>
            </div>
            <Mapa></Mapa>
        </div>
    );
}
export default Inicio
