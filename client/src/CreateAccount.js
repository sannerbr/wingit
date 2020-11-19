import { useState, useContext } from 'react';
import { useHistory } from 'react-router-dom';
import AuthContext from './AuthContext';
import Errors from './Errors';

export default function CreateAccount() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [address, setAddress] = useState('');
  const [company, setCompany] = useState(null);
  const [orders, setOrders] = useState(null);

  const [errors, setErrors] = useState([]);

  const auth = useContext(AuthContext);

  const history = useHistory();


  const submitHandler = (event) => {
    event.preventDefault();

    let obj = {
      method: 'POST',
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        username,
        password,
        email,
        phone,
        address,
        company
      })
    }

    fetch('http://localhost:8080/api/create-account', obj)
      .then(response => {
        if(response.status === 201) {
          alert("User Created");
          login();
        } else if(response.status === 400) {
          setErrors(response.json())
        } else {
          setErrors(['Unknown Error'])
        }

      })
  }

const login = async () => {
  let obj = {
    method: 'POST',
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      username,
      password
    })
  }

  const response = await fetch('http://localhost:8080/api/authenticate', obj);

  if (response.status === 200) {
    const { jwt_token } = await response.json();
    auth.login(jwt_token);
    history.push("/");
  } else if (response.status === 403) {
    setErrors(['Login Failed']);
  } else {
    setErrors(['Unknown Error'])
  }
}

  

  return (
    <div className="row justify-content-center">
      <div className="col-md-8">
        <Errors errors={errors} />
        <form onSubmit={submitHandler}>
          <div className="row justify-content-center my-3">
            <label className=" text-white" htmlFor="username">Username</label>
            <input className="form-control" type="text" id="username" value={username} onChange={(event) => setUsername(event.target.value)} placeholder="Username" required/>
          </div>
          <div className="row justify-content-center my-3">
            <label className=" text-white" htmlFor="password">Password</label>
            <input className="form-control" type="password" id="password" value={password} onChange={(event) => setPassword(event.target.value)} placeholder="Password" required/>
          </div>
          <div className="row justify-content-center my-3">
            <label className=" text-white" htmlFor="email">Email</label>
            <input className="form-control" type="email" id="email" value={email} onChange={(event) => setEmail(event.target.value)} placeholder="Email" required/>
          </div>
          <div className="row justify-content-center my-3">
            <label className=" text-white" htmlFor="phone">Phone</label>
            <input className="form-control" type="text" id="phone" value={phone} onChange={(event) => setPhone(event.target.value)} placeholder="Phone" required/>
          </div>
          <div className="row justify-content-center my-3">
            <label className=" text-white" htmlFor="address">Address</label>
            <input className="form-control" type="text" id="address" value={address} onChange={(event) => setAddress(event.target.value)} placeholder="Address" required/>
          </div>
          <div className="row justify-content-center my-3">
            <label className=" text-white" htmlFor="company">Company</label>
            <input className="form-control" type="text" id="company" value={company} onChange={(event) => setCompany(event.target.value)} placeholder="Company" />
          </div>
          <button className="btn btn-info mx-2" type="submit">Submit</button>
          <button className="btn btn-secondary mx-2" onClick={() => history.push("/")}>Cancel</button>
        </form>
      </div>
    </div>

  )
}