
export default function Order({ order }) {
    return (
        <div className="row justify-content-md-center my-4">
            <div className="card col-sm-8 bg-secondary">
                <div className="row card-header">
                    <div className="col">
                        <strong>
                            {`Order Id: ${order.orderId}`}
                        </strong>
                    </div>
                    <div className="col">
                        <strong>
                            {`Order Date: ${order.orderDate}`}
                        </strong>
                    </div>
                </div>
                <div className="card-body">
                    <table className="table">
                        <thead >
                            <th>Manufacturer</th>
                            <th>Model</th>
                            <th>Quantity</th>
                            <th>Price Per Plane</th>
                            <th>Plane Total</th>
                        </thead>
                        <tbody>
                            {order && order.planes.map(p => (
                                <tr key={p.planeId} className="text-white">
                                    <td>{`${p.model.manufacturer.name}`}</td>
                                    <td>{`${p.model.name}`}</td>
                                    <td>{`${p.quantity}`}</td>
                                    <td>{`$${p.price}`}</td>
                                    <td>{`$${p.price * p.quantity}`}</td>
                                </tr>
                            ))}
                            <tr>
                                <td colSpan="3"></td>
                                <th className="text-center" >Order Total</th>
                                <td className="text-white">{`$${order.totalCost}`}</td>
                            </tr>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>

    )
}

// {`${}`}