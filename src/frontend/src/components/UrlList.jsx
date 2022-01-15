import axios from 'axios';
import { Button, Modal } from 'react-bootstrap'
import { useEffect, useState } from 'react'
import { useNavigate } from 'react-router-dom'

function UrlList() {

    const [data, setData] = useState([])
    const [responseAlert, setResponseAlert] = useState("")

    const [url, setUrl] = useState("")
    const [urlError, setUrlError] = useState("")

    let navigate = useNavigate();

    const [show, setShow] = useState(false);

    const handleClose = () => setShow(false);
    const handleShow = () => {
        setUrl("")
        setUrlError("")
        setShow(true)
    };

    useEffect(() => {
        axios.get(`/urls/user/${localStorage.getItem("userId")}/url/list`,
            { headers: { authorization: `${localStorage.getItem("token")}` } })
            .then((res) => {
                setData(res.data)
            })
            .catch((err) => {
                console.log(err)
                if (err.response.status == "401") {
                    navigate("../login", { replace: true })
                } else {
                    navigate("../error", { replace: true })
                }
            })
    }, [data])

    function deleteUrl(id) {
        axios.delete(`/urls/user/${localStorage.getItem("userId")}/url/detail/${id}`,
            { headers: { authorization: `${localStorage.getItem("token")}` } })
            .then((res) => {
                setResponseAlert("#" + id + " " + res.data)
            })
            .catch((err) => {
                if (err.response.status == "401") {
                    navigate("../login", { replace: true })
                } else {
                    navigate("../error", { replace: true })
                }
            })
    }

    function addUrl() {
        axios.post(`/urls/user/${localStorage.getItem("userId")}/url/save`, url,
            { headers: { authorization: `${localStorage.getItem("token")}`, 'Content-Type': 'application/json' } })
            .then((res) => {
                console.log(res.data)
                handleClose()
            })
            .catch((err) => {
                console.log(err.response)
                if (err.response.status == "400") {
                    setUrlError(err.response.data["originUrl"])
                } else if (err.response.status == "401") {
                    navigate("../login", { replace: true })
                } else {
                    navigate("../error", { replace: true })
                }
            })
    }

    return (
        <>

            <div className='container p-5'>
                <div className="mb-4">
                    <Button variant="dark" onClick={handleShow}>
                        Add Url
                    </Button>
                </div>

                <Modal show={show} onHide={handleClose}>
                    <Modal.Header closeButton>
                        <Modal.Title>Url</Modal.Title>
                    </Modal.Header>
                    <Modal.Body>
                        <form>
                            <div className="mb-3">
                                <input onChange={(e) => setUrl(e.target.value)} type="url" className="form-control"
                                    id="exampleInputUrl" aria-describedby="urlHelp" />
                                <div id="urlHelp" className="form-text text-danger">{urlError}</div>
                            </div>
                        </form>
                    </Modal.Body>
                    <Modal.Footer>
                        <Button variant="danger" onClick={handleClose}>
                            Close
                        </Button>
                        <Button variant="dark" onClick={addUrl}>
                            Add
                        </Button>
                    </Modal.Footer>
                </Modal>
                {
                    responseAlert &&
                    <div className="alert alert-warning" role="alert">
                        {responseAlert}
                    </div>
                }
                {
                    data.length > 0 ?
                        <table className="table">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Short Url</th>
                                    <th scope="col">Redirect</th>
                                    <th scope="col">Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    data.map((item) =>
                                        <tr key={item.id}>
                                            <th scope="row">{item.id}</th>
                                            <td>{`${window.location.href.split('/')[0]}//${window.location.href.split('/')[2]}/urls/s/${item.shortUrl}`}</td>
                                            <td>
                                                <a href={`/urls/s/${item.shortUrl}`} target={"_blank"}
                                                    type="button" className="btn btn-warning">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                        fill="currentColor" className="bi bi-arrow-right-short"
                                                        viewBox="0 0 16 16">
                                                        <path fillRule="evenodd"
                                                            d="M4 8a.5.5 0 0 1 .5-.5h5.793L8.146 5.354a.5.5 0 1 1 .708-.708l3 3a.5.5 0 0 1 0 .708l-3 3a.5.5 0 0 1-.708-.708L10.293 8.5H4.5A.5.5 0 0 1 4 8z" />
                                                    </svg>
                                                </a>
                                            </td>
                                            <td>
                                                <button onClick={() => deleteUrl(item.id)} type="button"
                                                    className="btn btn-danger">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                        fill="currentColor" className="bi bi-x" viewBox="0 0 16 16">
                                                        <path
                                                            d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
                                                    </svg>
                                                </button>
                                            </td>
                                        </tr>
                                    )
                                }

                            </tbody>
                        </table>
                        :
                        <h5 className='text-center'>You don't have a shortened url. Create one now.</h5>
                }

            </div>
        </>
    )
}

export default UrlList
