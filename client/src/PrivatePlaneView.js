import { useState, useEffect } from 'react';
import PlaneCard from "./PlaneCard";


export default function CommercialPlane() {
  const [planes, setPlanes] = useState([]);

  const getCommercialPlanes = () => {
    fetch(`http://localhost:8080/api/private-planes`)
      .then(response => response.json())
      .then(data => setPlanes(data));
  }

  //Commented out until controller set up
  //    useEffect(getCommercialPlanes(), []);

  return (
    <>
      {planes.length > 0 && planes.map(plane => (
        <div className="row justify-content-md-center my-5">
          <PlaneCard
            key={planes.planeId}
            plane={plane}
            pathname="/private-planes/"
          />
        </div>
      ))}
      <div>
        <div className="row justify-content-md-center my-5">
          <PlaneCard pathname="Private" />
        </div>
        <div className="row justify-content-md-center my-5">
          <PlaneCard pathname="Plane" />
        </div>
        <div className="row justify-content-md-center my-5">
          <PlaneCard pathname="Cards" />
        </div>
      </div>
    </>
  )
}