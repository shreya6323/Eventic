import React from 'react';

import '../App.css';

import Footer from './Footer';
import Service from './Service';
import SwiperClass from './SwiperClass';
import Gallery from './Gallery';
import Navbar from './Navbar';
import Home from './Home';

function House(){
    return(
     <>
     <Navbar />
   <Home />
   <SwiperClass />
     <Service />
     <Footer />
    </>

    );
}

export default House;