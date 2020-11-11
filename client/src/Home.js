import {Link} from 'react-router-dom';



export default function Home() {
  return (
    <>
      <div className="container pt-lg-5">
        <div className="row justify-content-md-center" >
          <div className="col-sm-4">
            <div className="card">
              <div className="card-body">
                <p className="card-text">We supply the most commercial planes to anyone. Period.</p>
                <Link to="/commercial-planes" className="btn btn-primary">Commercial Planes</Link>
              </div>
            </div>
          </div>
          <div className="col-sm-4">
            <div className="card">
              <div className="card-body">
                <p className="card-text">Our cargo planes are top tier. Fully certifiably awesome</p>
                <Link to="/cargo-planes" className="btn btn-primary">Cargo Planes</Link>
              </div>
            </div>
          </div>
          <div className="col-sm-4">
            <div className="card">
              <div className="card-body">
                <p className="card-text">The private jets we offer are the best on the market. Click here if you're rich</p>
                <Link to="/private-planes" className="btn btn-primary">Private Planes</Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  )
}