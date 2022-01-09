import {Link, useNavigate} from 'react-router-dom';

function Nav() {

    let navigate = useNavigate();

    function logout() {
        localStorage.removeItem("token")
        localStorage.removeItem("userId")
        localStorage.removeItem("username")
        navigate("../", {replace: true})
    }

    return (
        <>
            <div>
                <header
                    className="container d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
                    <Link to="/"
                          className="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none fw-bold">
                        Shortened Url
                    </Link>

                    {
                        localStorage.getItem("username") == null ?
                            <ul className="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
                                <li><Link to="/" className="nav-link px-2 link-dark">Home</Link></li>
                                <li><Link to="/about" className="nav-link px-2 link-dark">About</Link></li>
                            </ul>
                            :
                            <ul className="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
                                <li><Link to="/" className="nav-link px-2 link-dark">Home</Link></li>
                                <li><Link to="/url-list" className="nav-link px-2 link-dark">Url List</Link></li>
                                <li><Link to="/about" className="nav-link px-2 link-dark">About</Link></li>
                            </ul>

                    }

                    {
                        localStorage.getItem("username") == null ?
                            <div className="col-md-3 text-end">
                                <Link to="/login" role="button" className="btn btn-outline-dark me-2">Login</Link>
                                <Link to="/sign-up" role="button" className="btn btn-dark">Sign-up</Link>
                            </div>
                            :
                            <div className="col-md-3 text-end">
                                <Link to="/url-list" role="button"
                                      className="btn btn-outline-dark me-2">Welcome {localStorage.getItem("username")}</Link>
                                <button onClick={logout} role="button" className="btn btn-outline-danger me-2">Logout
                                </button>
                            </div>

                    }
                </header>
            </div>
        </>
    )
}

export default Nav;