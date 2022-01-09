import shortUrl from '../assets/short-url.png'

function Home() {
    return (
        <>
            <div className="container">
                <div className="row">
                    <div className="col-md-6 my-auto">
                        <h3>Here you have full control over your links.</h3>
                        <p className='text-muted'>Shortened URL allows to reduce long links from Instagram, Facebook, YouTube, Twitter, Linked In and top sites on the Internet, just paste the long URL and click the Shorten URL button. On the next screen, copy the shortened URL and share it on websites, chat and e-mail.</p>
                    </div>
                    <div className="col-md-6">
                        <img className="img-fluid" src={shortUrl} alt="short-url" />
                    </div>
                </div>
            </div>
        </>
    )
}

export default Home
