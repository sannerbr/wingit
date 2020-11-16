import React, { useContext, useState } from 'react';
import { Link, useHistory } from 'react-router-dom'

import AuthContext from './AuthContext';
import Errors from './Errors';

export default function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [errors, setErrors] = useState([]);

  const auth = useContext(AuthContext);
  const history = useHistory();

  const handleUsernameChange = (event) => {
    setUsername(event.target.value);
  }

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  }

  const loginSubmitHandler = async (event) => {
    event.preventDefault();

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
  };


  return (
    <>
      <Errors errors={errors} />
      <div className="row justify-content-center">
        <form onSubmit={loginSubmitHandler}>
          <div className="row">
            <label htmlFor="username">Username</label>
            <input id="username" type="text" value={username} onChange={handleUsernameChange} placeholder="username" />
          </div>
          <div className="row">
            <label htmlFor="password">Password</label>
            <input id="password" type="password" value={password} onChange={handlePasswordChange} placeholder="password" />
          </div>
          <div className="row">
            <button className="btn btn-primary" type="submit">Login</button>
            <button className="btn btn-secondary">Create account</button>
          </div>
        </form>
      </div>
    </>
  )
}

//Change create account button to link to register page