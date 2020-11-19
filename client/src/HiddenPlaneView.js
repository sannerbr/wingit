import { useState, useEffect, useContext } from 'react';
import PlaneCard from "./PlaneCard";
import AuthContext from "./AuthContext"

export default function HiddenPlaneView() {
  const [planes, setPlanes] = useState([]);

  const auth = useContext(AuthContext);

  const getHiddenPlanes = () => {
    let obj = {
        method: 'GET',
        headers: {
          token: 'Bearer ' + auth.user.token
        }
      }
    fetch(`http://localhost:8080/api/planes/hidden/all`, obj)
      .then(response => response.json())
      .then(data => setPlanes(data));
  }

  useEffect(getHiddenPlanes, [auth.user.token]);

  return (
    <>
      {
        planes.length === 0 &&
        <h2 className="text-white my-5">There are currently no discontinued planes</h2>
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