import axios from 'axios';
import {useState} from 'react'
import {Link, useNavigate} from 'react-router-dom'

function Login() {

    const [username, setUsername] = useState("")
    const [usernameError, setUsernameError] = useState("")

    const [password, setPassword] = useState("")
    const [passwordError, setPasswordError] = useState("")

    const [loginError, setLoginError] = useState("")

    let navigate = useNavigate();

    function login(event) {
        event.preventDefault()
        axios.post(`/auth/login`, {username, password})
            .then((res) => {
                localStorage.setItem("token", res.data.token)
                localStorage.setItem("userId", res.data.id)
                localStorage.setItem("username", username)
                navigate("../", {replace: true})
            })
            .catch((err) => {
                if (err.response.status == "400") {
                    setUsernameError(err.response.data["username"])
                    setPasswordError(err.response.data["password"])
                    setLoginError("")
                } else if (err.response.status == "404") {
                    setUsernameError("")
                    setPasswordError("")
                    setLoginError(err.response.data)
                } else if (err.response.status == "401") {
                    setUsernameError("")
                    setPasswordError("")
                    setLoginError("Password is not correct.")
                }
            })
    }

    return (
        <>
            <div className='container p-5'>
                <h3 className='text-center'>Login</h3>
                <p className='text-center'>Don't you have an account ? <Link to="/sign-up"
                                                                             className='text-decoration-none text-info'> Sign-up... </Link>
                </p>
                <form>
                    <h5 className='text-center my-3 mt-5 text-danger'>{loginError}</h5>
                    <div className="mb-3">
                        <label htmlFor="InputUsername" className="form-label">Username</label>
                        <input onChange={(e) => setUsername(e.target.value)} type="text" className="form-control"
                               id="InputUsername" aria-describedby="usernameHelp"/>
                        <div id="usernameHelp" className="form-text text-danger">{usernameError}</div>
                    </div>
                    <div className="mb-3">
                        <label htmlFor="InputPassword" className="form-label">Password</label>
                        <input onChange={(e) => setPassword(e.target.value)} type="password" className="form-control"
                               id="InputPassword"/>
                        <div id="passwordHelp" className="form-text text-danger">{passwordError}</div>
                    </div>
                    <button onClick={login} type="submit" className="btn btn-dark">Login</button>
                </form>
            </div>
        </>
    )
}

export default Login
