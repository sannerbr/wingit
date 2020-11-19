import { useState, useEffect } from 'react';
import PlaneCard from "./PlaneCard";


export default function CargoPlaneView() {
    useEffect(() => {
        getCargoPlanes();
    }, []);

    const [planes, setPlanes] = useState([]);

    const getCargoPlanes = () => {
        fetch(`http://localhost:8080/api/planes/cargo`)
            .then(response => response.json())
            .then(data => setPlanes(data));
    }

     // useEffect(getCargoPlanes, []);

     

    return ( 
        <>
            {
                planes.length === 0 &&
                <h2 className="text-white my-5">There are currently no available cargo planes for sale</h2>
            }
            {planes.length > 0 && planes.map(plane => (
                <div className="row justify-content-md-center my-5">
                    <PlaneCard
                        key={plane.plane_id}
                        plane={plane}
                        rerenderData={getCargoPlanes}
                    />
                </div>
            ))}
        </>
    )
}