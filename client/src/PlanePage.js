import { useParams } from 'react-router-dom';
import { useState, useEffect } from 'react';
import {Link} from 'react-router-dom';

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

          <img src="https://assets.newatlas.com/dims4/default/ff4c6b5/2147483647/strip/true/crop/798x532+1+0/resize/1200x800!/quality/90/?url=http%3A%2F%2Fnewatlas-brightspot.s3.amazonaws.com%2Farchive%2F747-8freighter.jpg" alt="Boeing 747"></img>
          <br />
          <strong>Description:</strong><p>{`${plane.description}`}</p>
          <strong>{`Price: $${plane.price}`}</strong>
          <br />
          <br />
          <p>{`${plane.description}`}</p>
          <div className="row justify-content-center">
            <div className="col-md-8">
              <table className="table table-striped table-hover">
                <thead className="table-dark">
                  <tr>
                    <th></th>
                    <th>{`${plane.model.manufacturer.name} ${plane.model.name}`}</th>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>Size</td>
                    <td>{`${plane.size}`}</td>
                  </tr>
                  <tr>
                    <td>Wingspan</td>
                    <td>{`${plane.wingspan}`}</td>
                  </tr>
                  <tr>
                    <td>Height</td>
                    <td>{`${plane.height}`}</td>
                  </tr>
                  <tr>
                    <td>Length</td>
                    <td>{`${plane.length}`}</td>
                  </tr>
                  <tr>
                    <td>Seating Capacity</td>
                    <td>{`${plane.seating_capacity}`}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <Link to="/purchase" className="btn btn-sm btn-primary">Purchase</Link>
          <br />
          <br />
          <button className="btn btn-sm btn-warning mr-2"> Edit </button>
          <button className="btn btn-sm btn-danger">Delete</button>
          <p> conditionally render edit and delete buttons</p>

        </div>
      }


    </>


  )
}