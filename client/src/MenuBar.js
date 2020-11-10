import React from 'react';
import {
  BrowserRouter as Router,
  Route,
  Link
} from 'react-router-dom';

class MenuBar extends React.Component {
  render() {
    return (
      <>
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
          <div className="collapse navbar-collapse">
            <ul>
              <li className="nav-item">
                <Link to="/">Home</Link>
              </li>
              <li className="nav-itemdropdown">
                <a className="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  Planes
                </a>
                <div className="dropdown-menu" aria-labelledby="navbarDropdown">
                <Link to="/planes">Commercial</Link>
                <Link to="/planes">Cargo</Link>
                <Link to="/planes">Private</Link>
                </div>
                
              </li>
              <li className="nav-item">
                <Link to="/add-plane">Add</Link>
              </li>
              <li className="nav-item">
                <Link to="/account">Account</Link>
              </li>
              <li className="nav-item">
                <Link to="/orders">Orders</Link>
              </li>
            </ul>
          </div>
        </nav>
      </>
    )
  }
}

export default MenuBar;
