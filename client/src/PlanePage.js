import { useParams , useHistory} from 'react-router-dom';
import { useState, useEffect, useContext } from 'react';
import { Link } from 'react-router-dom';

import AuthContext from './AuthContext'

export default function PlanePage() {
  let { planeId } = useParams();

  const auth = useContext(AuthContext)

  const history = useHistory();

  const [plane, setPlane] = useState(null);

  const getPlaneInfo = () => {
    fetch(`http://localhost:8080/api/planes/id/${planeId}`)
      .then(response => response.json())
      .then(data => setPlane(data))
  }

  useEffect(getPlaneInfo, [planeId]);

  const handleDelete = (event) => {
    event.preventDefault();

    let obj = {
      method: 'DELETE',
      headers: {
        token: 'Bearer ' + auth.user.token
      }
    }
    fetch(`http://localhost:8080/api/planes/id/${plane.plane_id}`, obj)
      .then(response => {
        if(response.status === 204) {
          alert("Plane deleted from visible catalog")
          history.goBack();
        } else if(response.status === 404) {
          alert("Plane not found")
        } else {
          alert("Unknown Error Occured")
        }
      })
  }

  const handleEditPath = (event) => {
    event.preventDefault();

    history.push(`/edit/planes/${plane.plane_id}`)
  }

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
          <div className="row justify-content-center my-3">
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
                    <th className="table-secondary">Size</th>
                    <td>{`${plane.size}`}</td>
                  </tr>
                  <tr>
                    <th className="table-secondary">Wingspan (ft.)</th>
                    <td>{`${plane.wingspan}`}</td>
                  </tr>
                  <tr>
                    <th className="table-secondary">Height (ft.)</th>
                    <td>{`${plane.height}`}</td>
                  </tr>
                  <tr>
                    <th className="table-secondary">Length (ft.)</th>
                    <td>{`${plane.length}`}</td>
                  </tr>
                  <tr>
                    <th className="table-secondary">Seating Capacity</th>
                    <td>{`${plane.seating_capacity}`}</td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <Link to={`/purchase/${plane.plane_id}`} className="btn btn-lg btn-primary">Purchase</Link>
          <br />
          <br />
          {
            auth.user && auth.user.hasRole("ROLE_ADMIN") &&
            <div>
              <button onClick={handleEditPath} className="btn btn btn-warning mr-2">Edit</button>
              <button className="btn btn btn-danger" onClick={handleDelete}>Delete</button>
            </div>
          }

        </div>
      }


    </>


  )
}