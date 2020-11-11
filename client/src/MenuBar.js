import React from 'react';
import {
  BrowserRouter as Router,
  Route,
  Link
} from 'react-router-dom';

export default function MenuBar() {
  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <div className="collapse navbar-collapse">
        <ul className="navbar-nav mr-auto">
          <li className="nav-item mr-2">
            <Link to="/" className="btn">Home</Link>
          </li>
          <li className="nav-item dropdown">
            <button className="btn dropdown-toggle" type="button" id="navbarDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Planes
              </button>
            <div className="dropdown-menu" aria-labelledby="navbarDropdown">
              <Link to="/commercial-planes" className="dropdown-item">Commercial</Link>
              <Link to="/cargo-planes" className="dropdown-item">Cargo</Link>
              <Link to="/private-planes" className="dropdown-item">Private</Link>
            </div>
          </li>
          <li className="nav-item mr-2">
            <Link to="/add-plane" className="btn">Add</Link>
          </li>
        </ul>
        <ul className="navbar-nav ml-auto">
          <li className="nav-item mr-2">
            <Link to="/account" className="btn">Account</Link>
          </li>
          <li className="nav-item mr-2">
            <Link to="/orders" className="btn">Orders</Link>
          </li>
        </ul>
      </div>
    </nav>
  )
}