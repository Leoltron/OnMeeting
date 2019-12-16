import React, {useState} from "react";
import "./Auth.less"
import { signUp, signIn } from "../../httpClient"

const Auth: React.FC = () => {
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
        } catch (e) {
            setError(e.message)
        }
    };
    const onSignIn = async () => {
        try {
            await signIn(username, password);
        } catch (e) {
            setError(e.message)
        }
    };

    return (
        <div className={"App-auth"}>
            <input className="Auth-input" type="text" name="username" placeholder="Username" value={username} onChange={onUsernameChange} />
            <input className="Auth-input" type="password" name="password" placeholder="Password" value={password} onChange={onPasswordChange} />
            <div className={"Auth-controls"}>
                <button onClick={onSignUp}>Sign Up</button>
                <button onClick={onSignIn}>Sign In</button>
            </div>
            {error ? <div className={"Error"}>{error}</div> : null}
        </div>
    );
};

export default Auth
