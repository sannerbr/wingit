import MenuBar from './MenuBar';
import Home from './Home';
import {
  BrowserRouter as Router,
  Route,
  Link
} from 'react-router-dom';
import './App.css';

function App() {
  return (
    <div className="App">

      <Router>
        <MenuBar/>
        <Route exact={true} path="/">
          <Home/>
        </Route>
        <Route exact={true} path="/commercial-planes">
          <p1>Commercial Plane</p1>
        </Route>
        <Route exact={true} path="/cargo-planes">
          <p1>Cargo plane</p1>
        </Route>
        <Route exact={true} path="/private-planes">
          <p1>Private plane</p1>
        </Route>
        <Route exact={true} path="/add-plane">
          <p1>Add Plane</p1>
        </Route>
        <Route exact={true} path="/account">
          <p1>account</p1>
        </Route>
        <Route exact={true} path="/orders">
          <p1>order</p1>
        </Route>
      </Router>

      <header className="App-header">
        <p>placeholder</p>

      </header>
    </div>
  );
}

export default App;
