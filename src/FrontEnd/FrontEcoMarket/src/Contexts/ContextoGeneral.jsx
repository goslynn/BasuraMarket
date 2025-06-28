import { createContext, useState} from "react"
import React from 'react'

export const ThemeContext = createContext()

const contextoGeneral = ({ children }) => {
    const [formulario, setFormulario] = useState({
      nombre: "",
      edad: "",
      rut:"",
      nacimiento:"",
      correo: "",
      contra: ""
    })
  return (
    <ThemeContext.Provider value={{ formulario,setFormulario }}>
        {children}
    </ThemeContext.Provider>
  )
}

export default contextoGeneral