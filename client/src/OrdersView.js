import { useState, useEffect, useContext } from 'react';
import Order from "./Order";
import AuthContext from "./AuthContext"

export default function OrdersView() {
    const [orders, setOrders] = useState([]);

    const auth = useContext(AuthContext);

    const getOrders = () => {
        let obj = {
            method: 'GET',
            headers: {
              token: 'Bearer ' + auth.user.token
            }
          }
        fetch(`http://localhost:8080/api/orders/`, obj)
            .then(response => response.json())
            .then(data => setOrders(data));
    }

    const getOrdersForUser = () => {
        let obj = {
            method: 'GET',
            headers: {
              token: 'Bearer ' + auth.user.token
            }
          }
        fetch(`http://localhost:8080/api/orders/user/${auth.user.username}/`, obj)
        .then(response => response.json())
        .then(data => setOrders(data));
    }

    //

    const getOrdersByRole = () => {
        if(auth.user.hasRole("ROLE_ADMIN")) {
            getOrders();
        } else if (auth.user.hasRole("ROLE_USER")) {
            getOrdersForUser();
        }
    }

    useEffect(getOrdersByRole, [auth.user.token])

    return (
        <div>
            <h1 className="my-3">Orders</h1>
            <br/>
            {orders.map(o => (
                <Order 
                key={o.orderId}
                order={o} />
            ))}
            
        </div>
    )
}