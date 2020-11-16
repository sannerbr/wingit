import { useState, useEffect } from 'react';
import PlaneCard from "./PlaneCard";


export default function CargoPlaneView() {
    const [planes, setPlanes] = useState([]);

    const getCargoPlanes = () => {
        fetch(`http://localhost:8080/api/planes/cargo`)
            .then(response => response.json())
            .then(data => setPlanes(data));
    }

     useEffect(getCargoPlanes, []);

    return ( 
        <>
            {planes.length > 0 && planes.map(plane => (
                <div className="row justify-content-md-center my-5">
                    <PlaneCard
                        key={plane.plane_id}
                        plane={plane}
                    />
                </div>
            ))}
        </>
    )
}