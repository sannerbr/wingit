import React, { useContext } from 'react';
import { BrowserRouter as Router, NavLink, Route, Link } from 'react-router-dom';

import './MenuBar.css';

import AuthContext from './AuthContext';

export default function MenuBar() {
  const auth = useContext(AuthContext);

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-secondary" >
      <div className="collapse navbar-collapse">
        <ul className="navbar-nav mr-auto">
          <li className="nav-item mr-2">
            <NavLink to="/" className="btn text-white">Home</NavLink>
          </li>
          <li className="nav-item dropdown">
            <button className="btn dropdown-toggle text-white" type="button" id="navbarDropdown" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              Planes
              </button>
            <div className="dropdown-menu bg-secondary " aria-labelledby="navbarDropdown">
              <Link to="/commercial-planes" id="item" className="dropdown-item text-white">Commercial</Link>
              <Link to="/cargo-planes" id="item" className="dropdown-item text-white">Cargo</Link>
              <Link to="/private-planes" id="item" className="dropdown-item text-white">Private</Link>
            </div>
          </li>
          {
            auth.user && auth.user.hasRole("ROLE_ADMIN") &&
            <>
            <li className="nav-item mr-2">
              <NavLink to="/add-plane" className="btn text-white">Add</NavLink>
            </li>
            <li className="nav-item mr-2">
              <NavLink to="/hidden-planes" className="btn text-white">Discontinued Planes</NavLink>
            </li>
            </>
          }
        </ul>
        <ul className="navbar-nav ml-auto">

          {
            !auth.user &&
            <>
              <li className="nav-item mr-2">
                <NavLink to="/login" className="btn text-white">Login</NavLink>
              </li>
              <li className="nav-item mr-2">
                <NavLink to="/create-account" className="btn text-white">Create Account</NavLink>
              </li>
            </>
          }

          {
            auth.user &&
            <>
              <li className="nav-item mr-2">
                <NavLink to="/account" className="btn text-white">Account</NavLink>
              </li>
              <li className="nav-item mr-2">
                <NavLink to="/orders" className="btn text-white">Orders</NavLink>
              </li>
              <li className="nav-item mr-2">
                <button className="btn text-white" onClick={() => auth.logout()}>Logout</button>
              </li>
            </>
          }
        </ul>
      </div>
    </nav>
  )
}