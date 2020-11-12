import React from 'react';
import {
  BrowserRouter as Router,
  NavLink,
  Route,
  Link
} from 'react-router-dom';

export default function MenuBar() {
  return (
    <nav className="navbar navbar-expand-lg navbar-light bg-light">
      <div className="collapse navbar-collapse">
        <ul className="navbar-nav mr-auto">
          <li className="nav-item mr-2">
            <NavLink to="/" className="btn">Home</NavLink>
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
            <NavLink to="/add-plane" className="btn">Add</NavLink>
          </li>
        </ul>
        <ul className="navbar-nav ml-auto">
          <li className="nav-item mr-2">
            <NavLink to="/account" className="btn">Account</NavLink>
          </li>
          <li className="nav-item mr-2">
            <NavLink to="/orders" className="btn">Orders</NavLink>
          </li>
        </ul>
      </div>
    </nav>
  )
}