import { useParams, useHistory } from 'react-router-dom';
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
        if (response.status === 204) {
          alert("Plane deleted from visible catalog")
          history.goBack();
        } else if (response.status === 404) {
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
        <div className="row justify-content-center">
          <div className="col-md-10">
            <h1 className="py-4 text-white">{`${plane.model.manufacturer.name} ${plane.model.name}`}</h1>

            {
              plane.type === "COMMERCIAL" &&
              <img className="page" src="/commercialdelta.jpg"/>
            }
            {
              plane.type === "CARGO" &&
              <img className="page" src="/BelugaXL.jpg"/>
            }
            {
              plane.type === "PRIVATE" &&
              <img className="page" src="/private.jpg"/>
            }
            <br />
            <strong className="text-white">Description:</strong><p className="text-white mt-2">{`${plane.description}`}</p>
            <strong className="text-white"><p>{`Price: $${plane.price}`}</p></strong>
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
                    <tr className="bg-secondary">
                      <th className="table-secondary">Size</th>
                      <td className="text-white">{`${plane.size}`}</td>
                    </tr>
                    <tr className="bg-secondary">
                      <th className="table-secondary">Wingspan (ft.)</th>
                      <td className="text-white">{`${plane.wingspan}`}</td>
                    </tr>
                    <tr className="bg-secondary">
                      <th className="table-secondary">Height (ft.)</th>
                      <td className="text-white">{`${plane.height}`}</td>
                    </tr>
                    <tr className="bg-secondary">
                      <th className="table-secondary">Length (ft.)</th>
                      <td className="text-white">{`${plane.length}`}</td>
                    </tr>
                    <tr className="bg-secondary">
                      <th className="table-secondary">Seating Capacity</th>
                      <td className="text-white">{`${plane.seating_capacity}`}</td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
            {
              plane.quantity === 0 &&
              <>
              <button className="btn btn-lg btn-info" disabled>Order</button>
              <p className="text-danger">Out of Stock</p>
              </>
            }
            {
              (!auth.user || (auth.user && auth.user.hasRole("ROLE_USER"))) && plane.quantity > 0 &&
              <Link to={`/purchase/${plane.plane_id}`} className="btn btn-lg btn-info">Order</Link>
            }
            <br />
            {
              auth.user && auth.user.hasRole("ROLE_ADMIN") &&
              <div>
                <button onClick={handleEditPath} className="btn btn-warning mr-2 text-white">Edit</button>
                <button className="btn btn btn-danger" onClick={handleDelete}>Delete</button>
              </div>
            }
            <br />
          </div>
        </div>
      }


    </>


  )
}