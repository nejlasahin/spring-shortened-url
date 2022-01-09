import React from 'react';
import Login from './components/Login';
import Footer from './layouts/Footer';
import Nav from './layouts/Nav';
import { Routes, Route } from 'react-router-dom';
import Signup from './components/Signup';
import Error from './components/Error';
import UrlList from './components/UrlList';
import Home from './components/Home';
import About from './components/About';

function App() {
  return (
    <>
      <Nav />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/url-list" element={<UrlList />} />
        <Route path="/login" element={<Login />} />
        <Route path="/sign-up" element={<Signup />} />
        <Route path="/about" element={<About />} />
        <Route path="*" element={<Error />} />
      </Routes>
      <Footer />
    </>
  );
}

export default App;
