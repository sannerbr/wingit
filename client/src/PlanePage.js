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
          <strong>Description:</strong><p>{`${plane.description}`}</p>
          <strong>{`Price: $${plane.price}`}</strong>
          <br />
          <br />
          <table className="table table-bordered">
            <thead className="thead-dark">
              <tr>
                <th>Size</th>
                <th>Type</th>
                <th>Quantity Available</th>
                <th>Seating Capacity</th>
                <th>Height</th>
                <th>Length</th>
                <th>Wingspan</th>
                <th>Range</th>
              </tr>
            </thead>
              <tr>
                <td>{`${plane.size}`}</td>
                <td>{`${plane.type}`}</td>
                <td>{`${plane.quantity}`}</td>
                <td>{`${plane.seating_capacity}`}</td>
                <td>{`${plane.height}`}</td>
                <td>{`${plane.length}`}</td>
                <td>{`${plane.wingspan}`}</td>
                <td>{`${plane.range}`}</td>
              </tr>
          </table>
          <p> Order button</p>
          <p> conditionally render edit and delete buttons</p>

        </div>
      }


    </>


  )
}