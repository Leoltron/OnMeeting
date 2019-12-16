import React from "react";
import { BrowserRouter as Router, Switch, Route, Redirect } from "react-router-dom";
import Auth from "../Auth";
import Board from "../Board";

const Layout: React.FC = () => {
    return (
        <Router>
            <Switch>
                <Route exact path="/" >
                    {authorized() ? <Redirect to="/board" /> : <Redirect to="/login" />}
                </Route>
                <Route exact path="/login">
                    <Auth />
                </Route>
                <Route path="/board">
                    {authorized() ? <Board/> : <Redirect to="/"/>}
                </Route>
                <Route path="/login" />
            </Switch>
        </Router>
    )
};

const authorized = (): boolean =>  false;

export default Layout;