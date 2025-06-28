import React from 'react'
import { motion } from 'framer-motion';

const FormContacto = () => {
  return (
    <div>
        <motion.div
      key="FormContacto"
      initial={{ opacity: 0, x: 50 }}
      animate={{ opacity: 1, x: 0 }}
      exit={{ opacity: 0, x: -50 }}
      transition={{ duration: 0.3 }}
    >
      <div className="mb-3">
        <label>Correo:</label>
        <input type="email" name="correo"  className="form-control" />
      </div>
      <div className="mb-3">
        <label>Contraseña:</label>
        <input type="password" name="contraseña"  className="form-control" />
      </div>
    </motion.div>
    </div>
  )
}

export default FormContacto