import React, { useContext } from 'react';
import {
  BrowserRouter as Router,
  NavLink,
  Route,
  Link
} from 'react-router-dom';

import AuthContext from './AuthContext';

export default function MenuBar() {
  const auth = useContext(AuthContext);

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
          {
            auth.user &&
            <li className="nav-item mr-2">
              <NavLink to="/add-plane" className="btn">Add</NavLink>
            </li>
          }
        </ul>
        <ul className="navbar-nav ml-auto">

          {
            !auth.user &&
            <>
              <li className="nav-item mr-2">
                <NavLink to="/login" className="btn">Login</NavLink>
              </li>
              <li className="nav-item mr-2">
                <NavLink to="/create-account" className="btn">Create Account</NavLink>
              </li>
            </>
          }

          {
            auth.user &&
            <>
              <li className="nav-item mr-2">
                <NavLink to="/account" className="btn">Account</NavLink>
              </li>
              <li className="nav-item mr-2">
                <NavLink to="/orders" className="btn">Orders</NavLink>
              </li>
              <li className="nav-item mr-2">
                <button className="btn" onClick={() => auth.logout()}>Logout</button>
              </li>
            </>
          }
        </ul>
      </div>
    </nav>
  )
}