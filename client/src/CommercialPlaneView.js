import { useState, useEffect } from 'react';
import PlaneCard from "./PlaneCard";


export default function CommercialPlaneView() {
    const [planes, setPlanes] = useState([]);

    const getCommercialPlanes = () => {
        fetch(`http://localhost:8080/api/planes/commercial`)
            .then(response => response.json())
            .then(data => setPlanes(data));
    }

    useEffect(getCommercialPlanes, []);

    return (
        <>
            {
                planes.length === 0 && 
                <h2 className="text-white my-5">There are currently no commercial planes for sale</h2>
            }

            {planes.length > 0 && planes.map(plane => (
                <div className="row justify-content-md-center my-5">
                    <PlaneCard
                        key={plane.planeId}
                        plane={plane}
                        rerenderData={getCommercialPlanes}
                    />
                </div>
            ))}
        </>
    )
}
