import { useParams, useHistory } from 'react-router-dom';
import { useState, useEffect, useContext } from 'react';
import Errors from "./Errors"
import AuthContext from './AuthContext';

export default function PlanePurchase() {
    const auth = useContext(AuthContext);
    let { planeId } = useParams();
    const [userId, setUserId] = useState(auth.user.userId)
    const [manufacturer, setManufacturer] = useState({});
    const [model, setModel] = useState({});
    const [type, setType] = useState({});
    const [price, setPrice] = useState(0.00);
    const [quantity, setQuantity] = useState(0);

    const [plane, setPlane] = useState({});

    const [errors, setErrors] = useState([]);


    const history = useHistory();

    const getPlaneInfo = () => {
        fetch(`http://localhost:8080/api/planes/id/${planeId}`)
            .then(response => response.json())
            .then(data => { setFormData(data) })
    }

    useEffect(getPlaneInfo, [planeId]);


    const setFormData = (plane) => {
        setModel(plane.model);
        setManufacturer(plane.model.manufacturer);
        setType(plane.type);
        setPrice(plane.price);
        setPlane(plane)
    }

    const handleQuantityChange = (event) => {
        setQuantity(event.target.value)
    }

    const handleCancel = (event) => {
        history.push("/");
    }

    const editSubmitHandler = (event) => {
        event.preventDefault();

        var today = new Date();

        var date = today.getFullYear()+'-'+(today.getMonth()+1)+'-'+today.getDate();

        let obj = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                userId,
                orderDate: date,
                totalCost: quantity * price,
                planes: [plane]
            })
        }


        fetch("http://localhost:8080/api/orders", obj)
            .then(response => {
                if (response.status === 201) {
                    alert("Order Submitted")
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

    return (
        <>
            <Errors errors={errors} />
            <div className="row justify-content-md-center">
                <div className="col-md-6">
                    <p>Purchase Review</p>
                    <form className="form-group" onSubmit={editSubmitHandler}>
                        <div className="col">
                            <label htmlFor="manufacturer">Manufacturer</label>
                            <input className="form-control" type="text" id="manufacturer" value={manufacturer.name} readOnly />
                        </div>
                        <div className="col">
                            <label htmlFor="model">Model Name</label>
                            <input className="form-control" type="text" id="model" value={model.name} readOnly />
                        </div>
                        <div className="col">
                            <label htmlFor="type">Type</label>
                            <input className="form-control" type="text" id="type" value={type} readOnly />
                        </div>
                        <div className="col">
                            <label htmlFor="price">Price Per Plane</label>
                            <input className="form-control" type="text" id="price" value={`$${price}`} readOnly />
                        </div>
                        <div className="col">
                            <label htmlFor="quantity">Quantity</label>
                            <input className="form-control" type="number" id="quantity" value={quantity} onChange={handleQuantityChange} />
                        </div>
                        <div className="col">
                            <label htmlFor="total">Total</label>
                            <input className="form-control" type="text" id="total" value={`$${quantity * price}`} readOnly />
                        </div>
                        <button className="btn btn-primary mr-1" type="submit">Submit Order</button>
                        <button className="btn btn-secondary ml-1" type="button" onClick={handleCancel}>Cancel Order</button>
                    </form>
                </div>
            </div>
        </>
    )
}