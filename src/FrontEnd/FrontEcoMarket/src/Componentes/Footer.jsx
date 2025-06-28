import React from "react";

export default function Footer() {
  return (
    <footer className="bg-success text-white py-4 w-100">
      <div className="d-flex flex-column flex-md-row justify-content-between align-items-center px-4 px-md-5">
        <div className="text-center text-md-start mb-3 mb-md-0">
          <h5 className="mb-1">Mi Sitio Web</h5>
          <p className="mb-0">© {new Date().getFullYear()} Todos los derechos reservados.</p>
        </div>

        <div className="d-flex gap-3">
          <a href="https://facebook.com" target="_blank" rel="noopener noreferrer" aria-label="Facebook">
            <img
              src="https://cdn.jsdelivr.net/gh/simple-icons/simple-icons/icons/facebook.svg"
              alt="Facebook"
              width="24"
              height="24"
              style={{ filter: "invert(1)" }}
            />
          </a>
          <a href="https://instagram.com" target="_blank" rel="noopener noreferrer" aria-label="Instagram">
            <img
              src="https://cdn.jsdelivr.net/gh/simple-icons/simple-icons/icons/instagram.svg"
              alt="Instagram"
              width="24"
              height="24"
              style={{ filter: "invert(1)" }}
            />
          </a>
          <a href="https://twitter.com" target="_blank" rel="noopener noreferrer" aria-label="Twitter">
            <img
              src="https://cdn.jsdelivr.net/gh/simple-icons/simple-icons/icons/twitter.svg"
              alt="Twitter"
              width="24"
              height="24"
              style={{ filter: "invert(1)" }}
            />
          </a>
        </div>
      </div>
    </footer>
  );
}
