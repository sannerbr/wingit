import { useState, useEffect } from 'react';
import { useHistory } from 'react-router-dom';
import { GetAllManufacturers, GetTypes, GetSizes } from './FormDataFetch';
import Errors from './Errors';

export default function AddPlane() {
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


  const [allManufacturers, setAllManufacturers] = useState([]);
  const [types, setTypes] = useState([]);
  const [sizes, setSizes] = useState([]);

  const history = useHistory();


  //When Manufacturer Select value is changed the list of models is updated
  // Set select menu value to 0 and if event.target.value === 0 then set models to [] 
  const handleManufacturerChange = (event) => {
    setManufacturer(event.target.value);
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
  // Commented out until controller built
  // get all manufacturers, types, and sizes
  // useEffect(setAllManufacturers(GetAllManufacturers()), []);
  // useEffect(setTypes(GetTypes()), []);
  // useEffect(setSizes(GetSizes()), []);


  const addSubmitHandler = (event) => {
    event.preventDefault()

    let modelObj = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: {
        name: modelName
      }
    }

    fetch('http://localhost:8080/api/add-model', modelObj)
      .then(response => {
        if (response.status === 201) {
          response.json()
            .then(data => {
              setModel(data);
              addPlane();
            })
        } else if (response.status === 400) {
          response.json()
            .then(data => setErrors(data));
        }
      })
  }

  const addPlane = () => {
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
        seatingCapacity,
        length,
        height,
        wingspan,
        range,
        description,
      })
    }

    fetch('http://localhost:8080/add-plane', obj)
      .then(response => {
        if (response.status === 201) {
          history.push('/');
        } else if (response.status === 400) {
          response.json()
            .then(data => {
              setErrors(data);
            });
        }
      })
  }

  // className="form-control" add classname to all inputs and size

  return (
    <>
      <Errors errors={errors} />
      <div className="row justify-content-md-center">
        <div className="col-md-11">
          <form class="form-group" onSubmit={addSubmitHandler}>
            <div className="form-group">
              <div className="row">
                <div className="col">
                  <label for="manufacturer">Manufacturer</label>
                  <select className="form-control" id="manufacturer" value={manufacturer} onChange={handleManufacturerChange}>
                    <option selected>Select Manufacturer</option>
                    {allManufacturers.map(m => (
                      <option key={m.manufacturerId} value={m}>{`${m.name}`}</option>
                    ))}
                  </select>
                </div>

                <div className="col">
                  <label for="model">Model Name</label>
                  <input className="form-control" type="text" id="model" value={modelName} onChange={handleModelChange} />
                </div>
              </div>
            </div>

            <div className="form-group">
              <div className="row">
                <div className="col">
                  <label for="type">Plane Type</label>
                  <select className="form-control" id="type" value={type} onChange={handlePlaneTypeChange}>
                    <option selected>Plane Type</option>
                    {
                      types.map(t => (
                        <option key={t.typeId} value={t}>{`${t.name}`}</option>
                      ))
                    }
                  </select>
                </div>
                <div className="col">
                  <label for="size">Plane Size</label>
                  <select className="form-control" id="size" value={size} onChange={handlePlaneSizeChange}>
                    <option selected>Plane Size</option>
                    {
                      sizes.map(s => (
                        <option key={s.sizeId} value={s}>{`${s.size}`}</option>
                      ))
                    }
                  </select>
                </div>
              </div>
            </div>

            <div className="form-group">
              <div className="row">
                <div className="col">
                  <label for="price">Price</label>
                  <input className="form-control" id="price" type="number" value={price} min="0" step='0.01' onChange={handlePriceChange} />
                </div>
                <div className="col">
                  <label for="quantity">Quantity</label>
                  <input className="form-control" id="quantity" type="number" value={quantity} min="1" onChange={handleQuantityChange} />
                </div>
              </div>
            </div>

            <div className="form-group">
              <div className="row">
                <div className="col">
                  <label for="wingspan">Wingspan (ft.)</label>
                  <input className="form-control" type="number" id="wingspan" value={wingspan} min="1" onChange={handleWingspanChange} />
                </div>

                <div className="col">
                  <label for="length">Length (ft.)</label>
                  <input className="form-control" type="number" id="length" value={length} min="1" onChange={handleLengthChange} />
                </div>

                <div className="col">
                  <label for="height">Height (ft.)</label>
                  <input className="form-control" type="number" id="height" value={height} min="1" onChange={handleHeightChange} />
                </div>
              </div>
            </div>

            <div className="form-group">
              <div className="row">
                <div className="col">
                  <label for="seatingCapacity">Seating Capacity</label>
                  <input className="form-control" type="number" id="seatingCapacity" value={seatingCapacity} min="0" onChange={handleSeatingCapacityChange} />
                </div>
                <div className="col">
                  <label for="range">Range (miles)</label>
                  <input className="form-control" type="number" id="range" value={range} min="1" onChange={handleRangeChange} />
                </div>
              </div>
            </div>

            <div className="form-group">
              <label for="description">Description</label>
              <input className="form-control" type="text" id="description" value={description} min="1" onChange={handleDescriptionChange} />
            </div>

            <button className="btn btn-primary mr-1" type="submit">Submit</button>
            <button className="btn btn-secondary ml-1" type="button" onClick={handleCancel}>Cancel</button>
          </form>
        </div>
      </div>
    </>
  )
}

