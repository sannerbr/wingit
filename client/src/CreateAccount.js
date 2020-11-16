import { useState } from 'react';
import { useHistory } from 'react-router-dom';

export default function CreateAccount() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [address, setAddress] = useState('');
  const [company, setCompany] = useState(null);
  const [orders, setOrders] = useState(null);

  const history = useHistory();


  const submitHandler = (event) => {
    event.preventDefault();

  }

  return (
    <div className="row justify-content-center">
      <div className="col-md-8">
        <form onSubmit={submitHandler}>
          <div className="row justify-content-center my-3">
            <label htmlFor="username">Username</label>
            <input className="form-control" type="text" id="username" value={username} onChange={(event) => setUsername(event.target.value)} placeholder="Username" required/>
          </div>
          <div className="row justify-content-center my-3">
            <label htmlFor="password">Password</label>
            <input className="form-control" type="password" id="password" value={password} onChange={(event) => setPassword(event.target.value)} placeholder="Password" required/>
          </div>
          <div className="row justify-content-center my-3">
            <label htmlFor="email">Email</label>
            <input className="form-control" type="email" id="email" value={email} onChange={(event) => setEmail(event.target.value)} placeholder="Email" required/>
          </div>
          <div className="row justify-content-center my-3">
            <label htmlFor="phone">Phone</label>
            <input className="form-control" type="text" id="phone" value={phone} onChange={(event) => setPhone(event.target.value)} placeholder="Phone" required/>
          </div>
          <div className="row justify-content-center my-3">
            <label htmlFor="address">Address</label>
            <input className="form-control" type="text" id="address" value={address} onChange={(event) => setAddress(event.target.value)} placeholder="Address" required/>
          </div>
          <div className="row justify-content-center my-3">
            <label htmlFor="company">Company</label>
            <input className="form-control" type="text" id="company" value={company} onChange={(event) => setCompany(event.target.value)} placeholder="Company" />
          </div>
          <button className="btn btn-primary mx-2" type="submit">Submit</button>
          <button className="btn btn-secondary mx-2" onClick={() => history.push("/")}>Cancel</button>
        </form>
      </div>
    </div>

  )
}