import { useState, useEffect, useContext } from 'react';
import { useHistory } from 'react-router-dom';
import Errors from './Errors';
import AuthContext from "./AuthContext";

export default function AddPlane() {
  const auth = useContext(AuthContext);

  const [manufacturer, setManufacturer] = useState({});
  const [model, setModel] = useState({});
  const [type, setType] = useState({});
  const [size, setSize] = useState({});
  const [price, setPrice] = useState(0.00);
  const [quantity, setQunatity] = useState(0);
  const [seatingCapacity, setSeatingCapacity] = useState(0);
  const [height, setHeight] = useState(0);
  const [length, setLength] = useState(0);
  const [wingspan, setWingspan] = useState(0);
  const [range, setRange] = useState(0);
  const [description, setDescription] = useState('');
  const [errors, setErrors] = useState([]);

  const [modelName, setModelName] = useState('');
  const [manufacturerName, setManufacturerName] = useState('');


  const [allManufacturers, setAllManufacturers] = useState([]);
  const [types, setTypes] = useState([]);
  const [sizes, setSizes] = useState([]);

  const history = useHistory();


  const handleManufacturerChange = (event) => {
    setManufacturerName(event.target.value);
    setManufacturer(manufacturerFromName(event.target.value));
  }

  const handleModelChange = (event) => {
    setModelName(event.target.value);
  }

  const handlePlaneTypeChange = (event) => {
    setType(event.target.value);
  }

  const handlePlaneSizeChange = (event) => {
    setSize(event.target.value);
  }

  const handlePriceChange = (event) => {
    setPrice(event.target.value);
  }

  const handleQuantityChange = (event) => {
    setQunatity(event.target.value);
  }

  const handleWingspanChange = (event) => {
    setWingspan(event.target.value);
  }

  const handleLengthChange = (event) => {
    setLength(event.target.value);
  }

  const handleHeightChange = (event) => {
    setHeight(event.target.value);
  }

  const handleSeatingCapacityChange = (event) => {
    setSeatingCapacity(event.target.value);
  }

  const handleRangeChange = (event) => {
    setRange(event.target.value);
  }

  const handleDescriptionChange = (event) => {
    setDescription(event.target.value);
  }

  const handleCancel = (event) => {
    history.push("/");
  }

  function manufacturerFromName(name) {
    for (let i = 0; i < allManufacturers.length; i++) {
      if (allManufacturers[i].name === name) {
        return allManufacturers[i];
      }
    }
    return null;
  }


  const getManufacturers = () => {
    let obj = {
      method: 'GET',
      headers: {
        token: 'Bearer ' + auth.user.token
      }
    }

    fetch('http://localhost:8080/api/manufacturers', obj)
      .then(response => response.json())
      .then(data => setAllManufacturers(data));
  }

  const getSizes = () => {
    let obj = {
      method: 'GET',
      headers: {
        token: 'Bearer ' + auth.user.token
      }
    }
    fetch('http://localhost:8080/api/sizes', obj)
      .then(response => response.json())
      .then(data => setSizes(data));
  }

  const getTypes = () => {
    let obj = {
      method: 'GET',
      headers: {
        token: 'Bearer ' + auth.user.token
      }
    }
    fetch('http://localhost:8080/api/types', obj)
      .then(response => response.json())
      .then(data => setTypes(data));
  }


  useEffect(getTypes, [auth.user.token]);
  useEffect(getSizes, [auth.user.token]);
  useEffect(getManufacturers, [auth.user.token]);

  const addSubmitHandler = (event) => {
    event.preventDefault()
    let modelObj = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        token: 'Bearer ' + auth.user.token
      },
      body: JSON.stringify({
        model_id: 0,
        name: modelName,
        manufacturer: manufacturer,
      })
    }

    fetch('http://localhost:8080/api/models', modelObj)
      .then(response => {
        if (response.status === 201) {
          response.json()
            .then(data => {
              setModel(data);
              addPlane(data);
            })
        } else if (response.status === 400) {
          response.json()
            .then(data => setErrors(data));
        }
      })
  }

  const addPlane = (model) => {
    let obj = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({
        model,
        type,
        size,
        price,
        quantity,
        seating_capacity: seatingCapacity,
        length,
        height,
        wingspan,
        range,
        description,
      })
    }

    fetch('http://localhost:8080/api/planes', obj)
      .then(response => {
        if (response.status === 201) {
          alert("Plane Added");
          history.push('/');
        } else if (response.status === 400) {
          response.json()
            .then(data => {
              setErrors(data);
            });
        }
      })
  }

  return (
    <>
      <Errors errors={errors} />
      <div className="row justify-content-md-center">
        <div className="col-md-11">
          <form className="form-group" onSubmit={addSubmitHandler}>
            <div className="form-group">
              <div className="row">
                <div className="col">
                  <label htmlFor="manufacturer">Manufacturer</label>
                  <select className="form-control" id="manufacturer" value={manufacturerName} onChange={handleManufacturerChange}>
                    <option defaultValue>Select Manufacturer</option>
                    {
                      allManufacturers.map(m => (
                        <>
                          <option key={m.name} value={m.name}>{`${m.name}`}</option>
                        </>
                      ))
                    }
                  </select>
                </div>

                <div className="col">
                  <label htmlFor="model">Model Name</label>
                  <input className="form-control" type="text" id="model" value={modelName} onChange={handleModelChange} />
                </div>
              </div>
            </div>

            <div className="form-group">
              <div className="row">
                <div className="col">
                  <label htmlFor="type">Plane Type</label>
                  <select className="form-control" id="type" value={type} onChange={handlePlaneTypeChange}>
                    <option defaultValue>Plane Type</option>
                    {
                      types.map(t => (
                        <option key={t} value={t}>{`${t}`}</option>
                      ))
                    }
                  </select>
                </div>
                <div className="col">
                  <label htmlFor="size">Plane Size</label>
                  <select className="form-control" id="size" value={size} onChange={handlePlaneSizeChange}>
                    <option defaultValue>Plane Size</option>
                    {
                      sizes.map(s => (
                        <option key={s} value={s}>{`${s}`}</option>
                      ))
                    }
                  </select>
                </div>
              </div>
            </div>

            <div className="form-group">
              <div className="row">
                <div className="col">
                  <label htmlFor="price">Price</label>
                  <input className="form-control" id="price" type="number" value={price} min="0" step='0.01' onChange={handlePriceChange} />
                </div>
                <div className="col">
                  <label htmlFor="quantity">Quantity</label>
                  <input className="form-control" id="quantity" type="number" value={quantity} min="1" onChange={handleQuantityChange} />
                </div>
              </div>
            </div>

            <div className="form-group">
              <div className="row">
                <div className="col">
                  <label htmlFor="wingspan">Wingspan (ft.)</label>
                  <input className="form-control" type="number" id="wingspan" value={wingspan} min="1" onChange={handleWingspanChange} />
                </div>

                <div className="col">
                  <label htmlFor="length">Length (ft.)</label>
                  <input className="form-control" type="number" id="length" value={length} min="1" onChange={handleLengthChange} />
                </div>

                <div className="col">
                  <label htmlFor="height">Height (ft.)</label>
                  <input className="form-control" type="number" id="height" value={height} min="1" onChange={handleHeightChange} />
                </div>
              </div>
            </div>

            <div className="form-group">
              <div className="row">
                <div className="col">
                  <label htmlFor="seatingCapacity">Seating Capacity</label>
                  <input className="form-control" type="number" id="seatingCapacity" value={seatingCapacity} min="0" onChange={handleSeatingCapacityChange} />
                </div>
                <div className="col">
                  <label htmlFor="range">Range (miles)</label>
                  <input className="form-control" type="number" id="range" value={range} min="1" onChange={handleRangeChange} />
                </div>
              </div>
            </div>

            <div className="form-group">
              <label htmlFor="description">Description</label>
              <textarea className="form-control" type="text" id="description" value={description} min="1" onChange={handleDescriptionChange} />
            </div>

            <button className="btn btn-primary mr-1" type="submit">Submit</button>
            <button className="btn btn-secondary ml-1" type="button" onClick={handleCancel}>Cancel</button>
          </form>
        </div>
      </div>
    </>
  )
}

