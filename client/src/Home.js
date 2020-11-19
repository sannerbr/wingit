import {Link} from 'react-router-dom';

import "./Home.css"


export default function Home() {
  return (
    <>
    <div id="photo">
      <img className="resize" src="/fixed-wing.png"/>
      <h1 className="display-1 text-white" id="title">WingIt</h1>
    </div>

      <div className="container pt-lg-5">
        <div className="row justify-content-md-center" >
          <div className="col-sm-4">
            <div className="card bg-secondary">
            <img className="card" src="/commercialdelta.jpg"/>
              <div className="card-body">
                <p className="card-text text-white">We're committed to providing the highest quality commercial aviation. Our superior design, efficiency, and flying experience lead us to provide only the best.</p>
                <Link to="/commercial-planes" className="btn btn-info">Commercial Planes</Link>
              </div>
            </div>
          </div>
          <div className="col-sm-4">
            <div className="card bg-secondary">
            <img className="card" src="/BelugaXL.jpg"/>
              <div className="card-body">
                <p className="card-text text-white">Whether you are looking to deliver oversized, hazardous or highly urgent goods, our cargo selection will be sure to fit your needs.</p>
                <Link to="/cargo-planes" className="btn btn-info">Cargo Planes</Link>
              </div>
              
            </div>
          </div>
          <div className="col-sm-4">
            <div className="card bg-secondary">
            <img className="card" src="/private.jpg"/>
              <div className="card-body">
                <p className="card-text text-white">Take your flying experience to new heights with our selection of luxary private jets, and find the aircraft to fit your unique travel needs. Trust us, you won't be disappointed.</p>
                <Link to="/private-planes" className="btn btn-info">Private Planes</Link>
              </div>
              
            </div>
          </div>
        </div>
      </div>
    </>
  )
}