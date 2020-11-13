import { useState, useEffect } from 'react';
import PlaneCard from "./PlaneCard";


export default function CommercialPlane() {
  const [planes, setPlanes] = useState([]);

  const getPrivatePlanes = () => {
    fetch(`http://localhost:8080/api/planes/private`)
      .then(response => response.json())
      .then(data => setPlanes(data));
  }

  useEffect(getPrivatePlanes, []);

  return (
    <>
      {planes.length > 0 && planes.map(plane => (
        <div className="row justify-content-md-center my-5">
          <PlaneCard
            key={plane.planeId}
            plane={plane}
          />
        </div>
      ))}
    </>
  )
}