import { Link, useHistory } from 'react-router-dom';
import { useContext } from 'react';

import AuthContext from './AuthContext'

export default function PlaneCard({ plane, rerenderData }) {
  const auth = useContext(AuthContext)

  const history = useHistory();

  const handleDelete = (event) => {
    event.preventDefault();

    let obj = {
      method: 'DELETE'
    }
    fetch(`http://localhost:8080/api/planes/id/${plane.plane_id}`, obj)
      .then(response => {
        if (response.status === 204) {
          alert("Plane deleted from visible catalog")
          rerenderData();
        } else if (response.status === 404) {
          alert("Plane not found")
        } else {
          alert("Unknown Error Occured")
        }
      })
  }

  const makeVisible = () => {

  }

  return (

    <div className="col-sm-10">
      {console.log(plane)}
      <div className="card mb-3">
        <div className="row">
          <div className="col-md-3">
            <br />
            <br />
            <p>Image</p>
          </div>
          <div className="col-md-6">
            <div className="card-body">
              <h4 className="card-title">{`${plane.model.manufacturer.name} ${plane.model.name}`}</h4>
              <br />
              <div className="row justify-content-center">
                <p className="card-text mx-4">{`Quantity Available: ${plane.quantity}`}</p>
                <p className="card-text mx-4">{`Price: $${plane.price}`}</p>
              </div>
            </div>
          </div>
          <div className="col-md-3">
            <div className="row justify-content-center">
              <Link to={`planes/${plane.plane_id}`} className="btn btn-secondary my-3 px-6">More Info</Link>
            </div>
            {
              (!auth.user || (auth.user && auth.user.hasRole("ROLE_USER"))) &&
              <div className="row justify-content-center">
                <Link to={`/purchase/${plane.plane_id}`} className="btn btn-lg btn-primary">Purchase</Link>
              </div>
            }

            {
              auth.user && auth.user.hasRole("ROLE_ADMIN") &&
              <>
                <div className="row justify-content-center mb-3">
                  <Link to={`edit/planes/${plane.plane_id}`} className="btn btn btn-warning mr-2">Edit</Link>
                  {
                    !plane.hidden &&
                    <button className="btn btn btn-danger" onClick={handleDelete}>Delete</button>
                  }
                </div>

                <div className="row justify-content-center mb-3">
                  {
                    plane.hidden &&
                    <button className="btn btn btn-danger" onClick={makeVisible}>Make Available</button>
                  }
                </div>
              </>
            }
          </div>
        </div>
      </div>
    </div>
  )
}