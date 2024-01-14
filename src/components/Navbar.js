import React, { useState } from 'react';
import './Navbar.css';
import { Link } from 'react-router-dom';
import '@fortawesome/fontawesome-free/css/all.min.css';
import { IonIcon } from '@ionic/react';
import SearchBar from './SearchBar';

import { ticketOutline } from 'ionicons/icons';

function Navbar() {
    const [click, setClick] = useState(false); // Fix: Use array destructuring for useState
  
    const handleClick = () => setClick(!click);
    const closeMobileMenu = ()=> setClick(false);

   
    return (
        <nav className='navbar'>
            <div className='navbar-container'>
            <span className='icon'>
                <Link to="/">
            
      {/* Optionally, you can set size and color */}
      <IonIcon icon={ticketOutline} size="large" color="primary" />
                    <span className="logo">Eventic</span>
                </Link>
                </span>  
                
                <div className='menu-icon' onClick={handleClick}>
                    <i className={click ? 'fas fa-times' : 'fas fa-bars'}/>
                </div>
                <ul className={click ? 'nav-menu active' : 'nav-menu'}>
                    <li className='nav-item'>
                        <Link to='/' className='nav-links' onClick={closeMobileMenu}>
                            Home 
                        </Link>
                        </li>
                      
                        <li className='nav-item'>
                        <Link to='/UpcomingEvents' className='nav-links' onClick={closeMobileMenu}>
                            Events 
                        </Link>
                        </li>
                        <li className='nav-item'>
                        <Link to='/gallery' className='nav-links' onClick={closeMobileMenu}>
                            Gallery 
                        </Link>
                        </li>

                        <li className='nav-item'>
                        <Link to='/service' className='nav-links' onClick={closeMobileMenu}>
                            Service
                        </Link>
                        </li>

                        <li className='nav-item'>
                        <Link to='/contact' className='nav-links' onClick={closeMobileMenu}>
                            Contact
                        </Link>
                        </li>

                     
                       
          
        <li>
        <SearchBar />
        </li>
        <li>
        <Link to="/">
        <button className='login-button'>Login</button>
      </Link></li>
    
     
      </ul>
   </div>
        </nav>
    );
}

export default Navbar;
