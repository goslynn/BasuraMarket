import { useState, useEffect } from 'react';
import axios from 'axios';

const useFetch = (url) => {
  const [data, setData] = useState(null);
  const [cargando, setCargando] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    let isMounted = true; // Evitar que actualice el estado si el componente se desmonta

    const fetchData = async () => {
      setCargando(true);
      try {
        const response = await axios.get(url);
        if (isMounted) {
          setData(response.data);
          setCargando(false);
        }
      } catch (err) {
        if (isMounted) {
          setError(err.message || 'Error al cargar los datos');
          setCargando(false);
        }
      }
    };

    fetchData();

    return () => {
      isMounted = false;
    };
  }, [url]);

  return { data, cargando, error };
};

export default useFetch;
