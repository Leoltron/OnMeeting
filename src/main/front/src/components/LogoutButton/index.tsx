import React from "react";
import {useHistory} from "react-router";
import {logout} from "../../httpClient";
import {Button} from "@material-ui/core";

const Logout: React.FC = () => {
    const history = useHistory();
    const onLogout = async () => {
        await logout();
        history.push("/");
    };
    return (
        <Button variant="outlined" onClick={onLogout}>Logout</Button>
    )
};

export default Logout;