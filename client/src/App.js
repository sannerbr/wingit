import React, { useState } from 'react';
import jwt_decode from 'jwt-decode';

import { BrowserRouter as Router, Route } from 'react-router-dom';

import MenuBar from './MenuBar';
import Home from './Home';
import CommercialPlaneView from './CommercialPlaneView';
import CargoPlaneView from './CargoPlaneView';
import PrivatePlaneView from './PrivatePlaneView';
import AddPlane from './AddPlane';
import OrdersView from './OrdersView';
import Login from './Login';
import CreateAccount from './CreateAccount';
import PlanePage from './PlanePage';
import EditPlane from './EditPlane';
import PlanePurchase from './PlanePurchase';
import AccountManagement from "./AccountManagement";

import './App.css';
import AuthContext from './AuthContext';

function App() {
  const [user, setUser] = useState(null);

  const login = (token) => {
    const { userId, sub: username, authorities, email, phone, address, company } = jwt_decode(token);


    const roles = authorities.split(',');

    const user = {
      userId: parseInt(userId, 10),
      username,
      email,
      phone,
      address,
      company,
      roles,
      token,
      hasRole(role) {
        return this.roles.includes(role);
      }
    };

    console.log(user);
    setUser(user);

    return user;
  }

  const logout = () => {
    setUser(null);
  }

  const auth = {
    user,
    login,
    logout
  };

  return (
    <AuthContext.Provider value={auth}>
      <div className="App">
        <Router>
          <MenuBar />
          <Route exact={true} path="/">
            <Home />
          </Route>
          <Route exact={true} path="/commercial-planes">
            <CommercialPlaneView />
          </Route>
          <Route exact={true} path="/planes/:planeId">
            <PlanePage />
          </Route>
          <Route exact={true} path="/edit/planes/:planeId">
            <EditPlane />
          </Route>
          <Route exact={true} path="/cargo-planes">
            <CargoPlaneView />
          </Route>
          <Route exact={true} path="/private-planes">
            <PrivatePlaneView />
          </Route>
          <Route exact={true} path="/add-plane">
            <AddPlane />
          </Route>
          <Route exact={true} path="/account">
            <AccountManagement />
          </Route>
          <Route exact={true} path="/orders">
            <OrdersView />
          </Route>
          <Route exact={true} path="/login">
            <Login />
          </Route>
          <Route exact={true} path="/create-account">
            <CreateAccount />
          </Route>
          <Route exact={true} path="/purchase">
            <PlanePurchase />
          </Route>
        </Router>
      </div>
    </AuthContext.Provider>
  );
}

export default App;
