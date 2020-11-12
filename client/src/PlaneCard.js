import { Link } from 'react-router-dom';

export default function PlaneCard({ plane, pathname }) {
    //const planePathname = pathname + plane.planeId;
    const planePathname = "/"

    return(
        <div className="col-sm-10">
            <div className="card">
              <div className="card-body">
                <p className="card-text">{`${pathname}`}</p>
                <p className="card-text">desc</p>
                <Link to={planePathname} className="btn btn-primary">This Plane</Link>
              </div>
            </div>
        </div>
        
    )
}