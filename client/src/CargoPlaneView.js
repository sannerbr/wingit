import { useState, useEffect } from 'react';
import PlaneCard from "./PlaneCard";


export default function CommercialPlane() {
    const [planes, setPlanes] = useState([]);

    const getCargoPlanes = () => {
        fetch(`http://localhost:8080/api/planes/cargo`)
            .then(response => response.json())
            .then(data => setPlanes(data));
    }

    //Commented out until controller set up
     useEffect(getCargoPlanes, []);

    return ( 
        <>
            {console.log(planes)}
            {planes.length > 0 && planes.map(plane => (
                <div className="row justify-content-md-center my-5">
                    <PlaneCard
                        key={planes.planeId}
                        plane={plane}
                    />
                </div>
            ))}
        </>
    )
}