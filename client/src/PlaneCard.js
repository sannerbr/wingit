import { Link } from 'react-router-dom';

export default function PlaneCard({ plane, pathname }) {
  //const planePathname = pathname + plane.planeId;
  const planePathname = "/"

  return (
    <div className="col-sm-10">
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
              <br/>
              <div className="row justify-content-center">
                <p className="card-text mx-4">{`Quantity Available: ${plane.quantity}`}</p>
                <p className="card-text mx-4">{`Price: ${plane.price}`}</p>
              </div>
            </div>
          </div>
          <div className="col-md-3">
            <div className="row justify-content-center">
              <Link to={planePathname} className="btn btn-secondary my-4">More Info</Link>
            </div>
            <div className="row justify-content-center">
              <button className="btn btn-primary">Add to Order</button>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}