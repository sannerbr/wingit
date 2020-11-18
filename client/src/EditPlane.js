import { useParams, useHistory } from 'react-router-dom';
import { useState, useEffect, useContext } from 'react';
import Errors from "./Errors"
import AuthContext from "./AuthContext";

export default function EditPlane() {
  const auth = useContext(AuthContext)

  let { planeId } = useParams();
  const [manufacturer, setManufacturer] = useState({});
  const [model, setModel] = useState({});
  const [type, setType] = useState({});
  const [size, setSize] = useState({});
  const [price, setPrice] = useState(0.00);
  const [quantity, setQuantity] = useState(0);
  const [seatingCapacity, setSeatingCapacity] = useState(0);
  const [height, setHeight] = useState(0);
  const [length, setLength] = useState(0);
  const [wingspan, setWingspan] = useState(0);
  const [range, setRange] = useState(0);
  const [description, setDescription] = useState('');
  const [errors, setErrors] = useState([]);

  const [types, setTypes] = useState([]); 
  const [sizes, setSizes] = useState([]);

  const history = useHistory();

  const getPlaneInfo = () => {
    fetch(`http://localhost:8080/api/planes/id/${planeId}`)
      .then(response => response.json())
      .then(data => {setFormData(data)})
  }

  useEffect(getPlaneInfo, [planeId]);
  

  const setFormData = (plane) => {
    setManufacturer(plane.model.manufacturer);
    setModel(plane.model);
    setType(plane.type);
    setSize(plane.size);
    setPrice(plane.price);
    setQuantity(plane.quantity);
    setSeatingCapacity(plane.seating_capacity);
    setRange(plane.range);
    setHeight(plane.height);
    setWingspan(plane.wingspan);
    setLength(plane.length);
    setDescription(plane.description);
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


  useEffect( getTypes, [auth.user.token]);
  useEffect( getSizes, [auth.user.token]);

  

  const editSubmitHandler = (event) => {
    event.preventDefault();

    let obj = {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        token: 'Bearer ' + auth.user.token
      },
      body: JSON.stringify({
        plane_id: planeId,
        model,
        type, 
        size,
        price,
        quantity,
        seating_capacity: seatingCapacity,
        range, 
        height,
        wingspan,
        length,
        description
      })
    }
    

    fetch(`http://localhost:8080/api/planes/id/${planeId}`, obj)
      .then(response => {
        if(response.status === 204) {
          alert("Plane Updated")
          history.push("/")
        } else if (response.status === 400) {
          response.json()
            .then(
              data => setErrors(data)
            )
        } else {
          setErrors(['Unknown Error']);
        }
      })
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
    setQuantity(event.target.value);
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

  return (
    <>
      <Errors errors={errors} />
      <div className="row justify-content-md-center">
        <div className="col-md-11">
          <form className="form-group" onSubmit={editSubmitHandler}>
            <div className="form-group">
              <div className="row">
                <div className="col">
                  <label htmlFor="manufacturer">Manufacturer</label>
                  <input className="form-control" type="text" id="manufacturer" value={manufacturer.name} readOnly />
                </div>
                <div className="col">
                  <label htmlFor="model">Model Name</label>
                  <input className="form-control" type="text" id="model" value={model.name} readOnly/>
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