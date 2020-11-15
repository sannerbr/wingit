import MenuBar from './MenuBar';
import Home from './Home';
import CommercialPlaneView from './CommercialPlaneView';
import CargoPlaneView from './CargoPlaneView';
import PrivatePlaneView from './PrivatePlaneView';
import AddPlane from './AddPlane';
import OrdersView from './OrdersView';
import {
  BrowserRouter as Router,
  Route,
} from 'react-router-dom';
import './App.css';

function App() {
  return (
    <div className="App">
      <Router>
        <MenuBar />
        <Route exact={true} path="/">
          <Home />
        </Route>
        <Route exact={true} path="/commercial-planes">
          <CommercialPlaneView />
        </Route>
        <Route path="/planes/:id">
          {/*Plane page componenet*/}
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
          <p1>account</p1>
        </Route>
        <Route exact={true} path="/orders">
          <OrdersView />
        </Route>
      </Router>
    </div>
  );
}

export default App;
