import { Link, useHistory } from 'react-router-dom';
import { useContext } from 'react';

import AuthContext from './AuthContext';

import "./Home.css";

export default function PlaneCard({ plane, rerenderData }) {
  const auth = useContext(AuthContext)

  const history = useHistory();

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
          rerenderData();
        } else if (response.status === 404) {
          alert("Plane not found")
        } else {
          alert("Unknown Error Occured")
        }
      })
  }

  const makeVisible = (event) => {
      event.preventDefault();

      let obj = {
        method: 'PUT',
        headers: {
          token: 'Bearer ' + auth.user.token
        }
      }
      fetch(`http://localhost:8080/api/planes/visible/id/${plane.plane_id}`, obj)
        .then(response => {
          if(response.status === 204) {
            alert("Plane has been made visible to users")
            rerenderData();
          } else if (response.status === 404) {
            alert("Plane not found")
          } else {
            alert("Unknown Error Occured")
          }
        })
  }

  return (

    <div className="col-sm-10" >
      <div className="card bg-secondary border-dark mb-3 " >
        <div className="row">
          <div className="col-md-3">
            {
              plane.type === "COMMERCIAL" &&
              <img className="card" src="/commercialdelta.jpg"/>
            }
            {
              plane.type === "CARGO" &&
              <img className="card" src="/BelugaXL.jpg"/>
            }
            {
              plane.type === "PRIVATE" &&
              <img className="card" src="/private.jpg"/>
            }
          </div>
          <div className="col-md-6">
            <div className="card-body">
              <h4 className="card-title text-white">{`${plane.model.manufacturer.name} ${plane.model.name}`}</h4>
              <br />
              <div className="row justify-content-center">
                <p className="card-text mx-4 text-white">{`Quantity Available: ${plane.quantity}`}</p>
                <p className="card-text mx-4 text-white">{`Price: $${plane.price}`}</p>
              </div>
            </div>
          </div>
          <div className="col-md-3">
            <div className="row justify-content-center">
              <Link to={`planes/${plane.plane_id}`} className="btn btn-dark my-3 px-6 text-white">More Info</Link>
            </div>
            {
              plane.quantity === 0 &&
              <>
              <button className="btn btn-info" disabled>Order</button>
              <p className="text-danger">Out of Stock</p>
              </>
            }
            {
              (!auth.user || (auth.user && auth.user.hasRole("ROLE_USER"))) && plane.quantity > 0 &&
              <div className="row justify-content-center">
                <Link to={`/purchase/${plane.plane_id}`} className="btn btn-info text-white">Order</Link>
              </div>
            }

            {
              auth.user && auth.user.hasRole("ROLE_ADMIN") &&
              <>
                <div className="row justify-content-center mb-3">
                  <Link to={`edit/planes/${plane.plane_id}`} className="btn btn-warning mr-2 text-white">Edit</Link>
                  {
                    !plane.hidden &&
                    <button className="btn btn btn-danger" onClick={handleDelete}>Delete</button>
                  }
                </div>

                <div className="row justify-content-center mb-3">
                  {
                    plane.hidden &&
                    <button className="btn btn-success" onClick={makeVisible}>Make Available</button>
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