
import React from 'react'
import { AnimatePresence, motion } from 'framer-motion';

const FormPersonal = () => {
  
  return (
    <motion.div
      key="FormPersonal"
      initial={{ opacity: 0, x: -50 }}
      animate={{ opacity: 1, x: 0 }}
      exit={{ opacity: 0, x: 50 }}
      transition={{ duration: 0.3 }}
    >
      <div className="mb-3">
        <label>Nombre:</label>
        <input type="text" name="nombre"  className="form-control" />
      </div>
      <div className="mb-3">
        <label>RUT:</label>
        <input type="text" name="rut"  className="form-control" />
      </div>
      <div className="mb-3">
        <label>Fecha de nacimiento:</label>
        <input type="date" name="nacimiento"  className="form-control" />
      </div>
      <div className="mb-3">
        <label>Género:</label>
        <select name="genero"  className="form-control">
          <option value="">Seleccionar</option>
          <option value="masculino">Masculino</option>
          <option value="femenino">Femenino</option>
          <option value="otro">Otro</option>
        </select>
      </div>
      <div className="mb-3">
        <label>Teléfono:</label>
        <input type="tel" name="telefono" className="form-control" />
      </div>
      
    </motion.div>
  )
}
export default FormPersonal