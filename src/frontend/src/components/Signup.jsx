import axios from 'axios';
import {useState} from 'react'
import {Link, useNavigate} from 'react-router-dom'
import BASE_URL from '../data/base_url'

function Signup() {

    const [username, setUsername] = useState("")
    const [usernameError, setUsernameError] = useState("")

    const [password, setPassword] = useState("")
    const [passwordError, setPasswordError] = useState("")

    const [signupError, setSignupError] = useState("")

    let navigate = useNavigate();

    function signup(event) {
        event.preventDefault()
        axios.post(`${BASE_URL}/auth/register`, {username, password})
            .then((res) => {
                navigate("../login", {replace: true})
            })
            .catch((err) => {
                console.log(err.response)
                if (err.response.status == "400") {
                    if (err.response.data["username"] || err.response.data["password"]) {
                        setUsernameError(err.response.data["username"])
                        setPasswordError(err.response.data["password"])
                        setSignupError("")
                    } else {
                        setUsernameError("")
                        setPasswordError("")
                        setSignupError(err.response.data)
                    }
                } else if (err.response.status == "404") {
                    setUsernameError("")
                    setPasswordError("")
                    setSignupError(err.response.data)
                } else if (err.response.status == "401") {
                    setUsernameError("")
                    setPasswordError("")
                    setSignupError("Password is not correct.")
                }
            })
    }

    return (
        <>
            <div className='container p-5'>
                <h3 className='text-center'>Sign-up</h3>
                <p className='text-center'>Do you have an account ? <Link to="/login"
                                                                          className='text-decoration-none text-info'> Login... </Link>
                </p>
                <form>
                    <h5 className='text-center my-3 mt-5 text-danger'>{signupError}</h5>
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
                    <button onClick={signup} type="submit" className="btn btn-dark">Sign-up</button>
                </form>
            </div>
        </>
    )
}

export default Signup
