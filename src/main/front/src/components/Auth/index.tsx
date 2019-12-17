import React, {useState} from "react";
import "./Auth.less"
import Button from '@material-ui/core/Button';
import TextField from "@material-ui/core/TextField";
import {signUp, signIn} from "../../httpClient"
import { useHistory } from 'react-router-dom'

const Auth: React.FC = () => {
    let history = useHistory();
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState(null);
    const onUsernameChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { target: { value } } = event;
        setUsername(value);
    };
    const onPasswordChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        const { target: { value } } = event;
        setPassword(value);
    };
    const onSignUp = async () => {
        try {
            await signUp(username, password);
            await signIn(username, password);
            history.push('/board');
        } catch (e) {
            setError(e.message)
        }
    };
    const onSignIn = async () => {
        try {
            await signIn(username, password);
            history.push('/board');
        } catch (e) {
            setError(e.message)
        }
    };

    return (
        <div className="App-auth">
            <TextField id="standard-basic" label="Username" value={username} onChange={onUsernameChange} />
            <TextField id="standard-basic" label="Password" value={password} onChange={onPasswordChange} type="password" />
            <div className="Auth-controls">
                <Button variant="outlined" color="primary" onClick={onSignUp} disabled={password.length === 0 || username.length === 0}>
                    Sign Up
                </Button>
                <Button variant="contained" color="primary" onClick={onSignIn} disabled={password.length === 0 || username.length === 0}>
                    Sign In
                </Button>
            </div>
            {error ? <div className={"Error"}>{error}</div> : null}
        </div>
    );
};

export default Auth
