import { useState, useEffect } from 'react';
import PlaneCard from "./PlaneCard";


export default function PrivatePlaneView() {
  const [planes, setPlanes] = useState([]);

  const getPrivatePlanes = () => {
    fetch(`http://localhost:8080/api/planes/private`)
      .then(response => response.json())
      .then(data => setPlanes(data));
  }

  useEffect(getPrivatePlanes, []);

  return (
    <>
      {
        planes.length === 0 &&
        <h2>There are currently no available private planes for sale</h2>
      }

      {planes.length > 0 && planes.map(plane => (
        <div className="row justify-content-md-center my-5">
          <PlaneCard
            key={plane.plane_id}
            plane={plane}
          />
        </div>
      ))}
    </>
  )
}