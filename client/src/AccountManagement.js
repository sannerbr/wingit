import { useState, useContext, useEffect } from 'react';

import AuthContext from "./AuthContext";
import Errors from './Errors';

export default function AccountManagement() {
  const auth = useContext(AuthContext);
  const [userId, setUserId] = useState(auth.user.userId);
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [address, setAddress] = useState('');
  const [company, setCompany] = useState('');
  const [role, setRole] = useState(auth.user.roles[0].substring(5))
  const [editing, setEditing] = useState(false);
  const [errors, setErrors] = useState([]);

  const cancelEdit = (event) => {
    event.preventDefault();
    setUsername(auth.user.username);
    setEmail(auth.user.email);
    setPhone(auth.user.phone);
    setAddress(auth.user.address);
    setCompany(auth.user.company);
    setEditing(false);
  }

  const getUserInfo = () => {
    fetch(`http://localhost:8080/api/users/id/${userId}`)
      .then(response => response.json())
      .then(data => setUserInfo(data))
  }

  const setUserInfo = (user) => {
    setUsername(user.username);
    setEmail(user.email);
    setPhone(user.phone);
    setAddress(user.address);
    setCompany(user.company);
  }

  useEffect(getUserInfo, [userId]);

  const handleUserEditSubmit = (event) => {
    event.preventDefault();

    let obj = {
      method: 'PUT',
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        userId,
        username,
        email,
        phone,
        address,
        company,
        role
      })
    }
    fetch(`http://localhost:8080/api/users/id/${userId}`, obj)
      .then(response => {
        if(response.status === 204) {
          alert("User Updated")
          getUserInfo();
          setEditing(false);
        } else if (response.status === 400) {
          setErrors(response.json())
        } else {
          setErrors(['Unknown Error Occured'])
        }
      })
  }

  return (
    <div className="row justify-content-center">
      <div className="col-md-8">
        <div className="row justify-content-center my-4">
          <h1 className="text-white">User Info</h1>
        </div>
        {
          auth.user && !editing &&
          <div className="row justify-content-center">
            <div className="col">
              <div className="row justify-content-center">
                <form>
                  <div className="row form-group justify-content-center">
                    <label className="text-white mx-3">Username</label>
                    <input className="form-control" type="text" value={username} readOnly />
                  </div>
                  <div className="row form-group justify-content-center">
                    <label className="text-white">Email</label>
                    <input className="form-control" type="email" value={email} readOnly />
                  </div>
                  <div className="row form-group justify-content-center">
                    <label className="text-white">Phone</label>
                    <input className="form-control" type="text" value={phone} readOnly />
                  </div>
                  <div className="row form-group justify-content-center">
                    <label className="text-white">Address</label>
                    <input className="form-control" type="text" value={address} readOnly />
                  </div>
                  <div className="row form-group justify-content-center">
                    <label className="text-white">Company</label>
                    <input className="form-control" type="text" value={company} readOnly />
                  </div>
                </form>
              </div>
              <div className="row justify-content-center">
                <button className="btn btn-info my-3" onClick={() => { setEditing(true) }}>Edit</button>
              </div>
            </div>
          </div>
        }

        {
          auth.user && editing &&
          <div className="row justify-content-center">
            <div className="col">
              <Errors errors={errors} />
              <div className="row justify-content-center">
                <form onSubmit={handleUserEditSubmit}>
                  <input type="hidden" value={userId} />
                  <div className="row form-group justify-content-center">
                    <label className="mx-3">Username</label>
                    <input className="form-control" type="text" value={username} onChange={(event) => setUsername(event.target.value)} />
                  </div>
                  <div className="row form-group justify-content-center">
                    <label className="mx-3">Email</label>
                    <input className="form-control" type="email" value={email} onChange={(event) => setEmail(event.target.value)} />
                  </div>
                  <div className="row form-group justify-content-center">
                    <label className="mx-3">Phone</label>
                    <input className="form-control" type="text" value={phone} onChange={(event) => setPhone(event.target.value)} />
                  </div>
                  <div className="row form-group justify-content-center">
                    <label className="mx-3">Address</label>
                    <input className="form-control" type="text" value={address} onChange={(event) => setAddress(event.target.value)} />
                  </div>
                  <div className="row form-group justify-content-center">
                    <label className="mx-3">Company</label>
                    <input className="form-control" type="text" value={company} onChange={(event) => setCompany(event.target.value)} />
                  </div>
                  <div className="row form-group justify-content-center my-3">
                    <button className="btn btn-primary" type="submit">Submit</button>
                    <button className="btn btn-secondary mx-2" onClick={cancelEdit}>Cancel</button>
                  </div>

                </form>
              </div>
            </div>
          </div>
        }
      </div>
    </div >
  )
}