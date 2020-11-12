import MenuBar from './MenuBar';
import Home from './Home';
import CommercialPlaneView from './CommercialPlaneView';
import CargoPlaneView from './CargoPlaneView';
import PrivatePlaneView from './PrivatePlaneView';
import AddPlane from './AddPlane';
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
        <Route path="/commercial-planes/:id">
          {/* Commercial plane componenet*/}
        </Route>
        <Route exact={true} path="/cargo-planes">
          <CargoPlaneView />
        </Route>
        <Route path="/cargo-planes/:id">
          {/* Cargo plane componenet*/}
        </Route>
        <Route exact={true} path="/private-planes">
          <PrivatePlaneView />
        </Route>
        <Route path="/private-planes/:id">
          {/* Private plane componenet*/}  
        </Route>
        <Route exact={true} path="/add-plane">
          <AddPlane />
        </Route>
        <Route exact={true} path="/account">
          <p1>account</p1>
        </Route>
        <Route exact={true} path="/orders">
          <p1>order</p1>
        </Route>
      </Router>
    </div>
  );
}

export default App;
