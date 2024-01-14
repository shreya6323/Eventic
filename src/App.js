import React from 'react';
import Navbar from './components/Navbar';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import UpcomingEvents from './components/SwiperClass';
import Gallery from './components/Gallery';
import House from './components/House';
import Service from './components/Service';
import Footer from './components/Footer';

function App() {
  return (
    <Router>
      <div>
    
        <Routes>
          <Route path="/" element={<House />} />
          <Route path="/gallery" element={<Gallery />} />
          <Route path="/UpcomingEvents" element={<UpcomingEvents />} />
          <Route path="/service" element={<Service />} />
          <Route path="/contact" element={<Footer />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
