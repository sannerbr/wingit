import { useState, useEffect } from 'react';
import PlaneCard from "./PlaneCard";


export default function HiddenPlaneView() {
  const [planes, setPlanes] = useState([]);

  const getHiddenPlanes = () => {
    fetch(`http://localhost:8080/api/planes/hidden/all`)
      .then(response => response.json())
      .then(data => setPlanes(data));
  }

  useEffect(getHiddenPlanes, []);

  return (
    <>
      {
        planes.length === 0 &&
        <h2>There are currently no hidden planes</h2>
      }

      {planes.length > 0 && planes.map(plane => (
        <div className="row justify-content-md-center my-5">
          <PlaneCard
            key={plane.plane_id}
            plane={plane}
            rerenderData={getHiddenPlanes}
          />
        </div>
      ))}
    </>
  )
}