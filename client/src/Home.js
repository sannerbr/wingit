import {Link} from 'react-router-dom';

import "./Home.css"


export default function Home() {
  return (
    <>
    <div id="photo">
      <img className="resize" src="https://genesis10-my.sharepoint.com/personal/acoburn_dev-10_com/Documents/Microsoft Teams Chat Files/kisspng-fixed-wing-aircraft-airplane-icon-a5-air-travel-air-tickets-5ad7fee2a1e344.7417056715241049306631.png"/>
      <h1 className="display-1 text-white">WingIt</h1>
    </div>

      <div className="container pt-lg-5">
        <div className="row justify-content-md-center" >
          <div className="col-sm-4">
            <div className="card bg-secondary">
              <div className="card-body">
                <p className="card-text text-white">We supply the most commercial planes to anyone. Period.</p>
                <Link to="/commercial-planes" className="btn btn-info">Commercial Planes</Link>
              </div>
            </div>
          </div>
          <div className="col-sm-4">
            <div className="card bg-secondary">
              <div className="card-body">
                <p className="card-text text-white">Our cargo planes are top tier. Fully certifiably awesome</p>
                <Link to="/cargo-planes" className="btn btn-info">Cargo Planes</Link>
              </div>
            </div>
          </div>
          <div className="col-sm-4">
            <div className="card bg-secondary">
              <div className="card-body">
                <p className="card-text text-white">The private jets we offer are the best on the market.
                 Click here if you're rich</p>
                <Link to="/private-planes" className="btn btn-info">Private Planes</Link>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  )
}