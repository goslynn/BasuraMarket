import React from 'react'

const CardOpiniones = ({ opinion }) => {
  return (
    <div className="card h-100">
      <div className="testimonial-wrapper p-4 d-flex flex-column">
        <div className="text-center mb-3">
          <img
            src={`https://unavatar.io/x/${opinion.user.username}`}
            alt="foto-de-usuario"
            width="50"
            height="50"
            className="rounded-circle"
          />
        </div>
        <div className="text-center mb-2">
          {[...Array(opinion.rating || 5)].map((_, i) => (
            <span key={i} className="text-warning">★</span>
          ))}
        </div>
        <div className="flex-grow-1">
          <p className="uagb-heading-text mb-2">
            {opinion.body}
          </p>
        </div>
        <div className="mt-auto">
          <p className="uagb-desc-text fw-bold mb-0">
            {opinion.user.fullName || opinion.user.username}
          </p>
        </div>
      </div>
    </div>
  )
}

export default CardOpiniones
