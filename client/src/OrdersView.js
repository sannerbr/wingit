import { useState, useEffect } from 'react';
import Order from "./Order";


export default function OrdersView() {
    const [orders, setOrders] = useState([]);

    const getOrders = () => {
        fetch(`http://localhost:8080/api/orders`)
            .then(response => response.json())
            .then(data => setOrders(data));
    }

    // Commented out until controller built
    useEffect(getOrders, [])

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