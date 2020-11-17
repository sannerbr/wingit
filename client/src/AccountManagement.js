import {useContext} from 'react';

import AuthContext from "./AuthContext";

export default function AccountManagement() {
    const auth = useContext(AuthContext);

    return (
        <p>Account management</p>
    )
}