import React from "react";
import { BrowserRouter as Router, Switch, Route, useHistory } from "react-router-dom";
import Auth from "../Auth";
import Board from "../Board";
import {getPrincipal} from "../../httpClient";
import report from '../../utils/report'

const Layout: React.FC = () => {
    return (
        <Router>
            <Switch>
                <Route exact path="/" >
                    <WelcomePage />
                </Route>
                <Route exact path="/login">
                    <Auth />
                </Route>
                <Route path="/board">
                    <Board/>
                </Route>
                <Route path="/login" />
            </Switch>
        </Router>
    )
};

const WelcomePage: React.FC = () => {
    const history = useHistory();
    getPrincipal()
        .then(p => history.replace(p.authorized ? "/board" : "/login"))
        .catch(report);
    return null;
};



export default Layout;