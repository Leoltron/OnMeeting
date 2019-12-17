import React from "react";
import {useHistory} from "react-router-dom";
import {getPrincipal} from "../../httpClient";
import report from "../../utils/report";
import Logout from "../LogoutButton";

const Board: React.FC = () => {
    const history = useHistory();
    const checkUserAuthorized = () => {
        getPrincipal()
            .then(p => {
                if (!p.authorized) history.push("/")
            })
            .catch(report);
    };
    checkUserAuthorized();


    return <Logout />
};

export default Board;