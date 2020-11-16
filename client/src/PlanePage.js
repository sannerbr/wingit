import { useParams } from 'react-router-dom';
import { useState, useEffect } from 'react';

export default function PlanePage() {
  let { planeId } = useParams();

  const [plane, setPlane] = useState(null);



  const getPlaneInfo = () => {
    fetch(`http://localhost:8080/api/planes/id/${planeId}`)
      .then(response => response.json())
      .then(data => setPlane(data))
  }

  useEffect(getPlaneInfo, [planeId]);

  return (
    <>
      {
        plane &&
        <div>
          <h1 className="py-4">{`${plane.model.manufacturer.name} ${plane.model.name}`}</h1>
          <p>Image</p>
          <p>{`${plane.description}`}</p>
          <p>table Here</p>
          <p> Order button</p>
          <p> conditionally render edit and delete buttons</p>

        </div>
      }


    </>


  )
}