import { useState } from 'react';


function GetAllManufacturers() {
  const [manufacturers, setManufacturers] = useState([]);

  fetch('http://localhost:8080/api/manufacturers')
    .then(response => response.json())
    .then(data => setManufacturers(data));

  return manufacturers;
}

function GetTypes() {
  const [types, setTypes] = useState([]);

  fetch('http://localhost:8080/api/types')
    .then(response => response.json())
    .then(data => setTypes(data));

  return types;
}

function GetSizes() {
  const [sizes, setSizes] = useState([]);

  fetch('http://localhost:8080/api/sizes')
    .then(response => response.json())
    .then(data => setSizes(data));

  return sizes;
}


export {
  GetAllManufacturers,
  GetTypes,
  GetSizes,
}