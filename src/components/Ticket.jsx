import React from 'react';
import './Ticket.css'; // Import CSS file for styling
import { IonIcon } from '@ionic/react';
import { ticketOutline } from 'ionicons/icons';
// import qrcode from'../assets/qrcode.png';
import { useLocation } from 'react-router-dom';
import lottie from 'lottie-web';
import animationData from '../assets/confetti.json'; // Replace with the path to your Lottie animation JSON file

const Ticket = () => {

  const location = useLocation();

  // Access data from location.state
  const { data } = location.state;

  return (

    <div className="ticket">
<div className='eventic-container'>
    <span className='eventic-logo-name'>Eventic</span>
    </div>
      <div className="ticket-header">{data.user.userName}</div>
      <div className="ticket-details">
        <div className="detail-row">
          <span className="detail-label">&#127881;</span>
          <span className="detail-value">{data.event.eventname}</span>
        </div>
        <div className="detail-row">
          <span className="detail-label">&#128197;</span>
          <span className="detail-value">{data.event.eventDate} {data.event.eventTime}</span>
        </div>
        <div className="detail-row">
          <span className="detail-label">&#128205;</span>
          <span className="detail-value">{data.event.location}</span>
        </div>
        
        <div className="detail-row">
          <span className="detail-label">&#128176;</span>
          <span className="detail-value">Rs {data.event.price}</span>
        </div>
      
      </div>
      <div className="qr-code-container">
        <div className="qr-code"><img src={`data:image/png;base64,${data.qrcode}`} alt="qrcode"/></div>
      </div>
     
      
    </div>

   
   
  );
};

export default Ticket;
// import React, { useEffect } from 'react';
// import './Ticket.css'; // Import CSS file for styling
// import './SuccessfulBooking.css';
// import { IonIcon } from '@ionic/react';
// import { ticketOutline } from 'ionicons/icons';
// import qrcode from '../assets/qrcode.png';
// import { useLocation } from 'react-router-dom';
// import lottie from 'lottie-web';
// import animationData from '../assets/confetti.json'; // Replace with the path to your Lottie animation JSON file


// const Ticket = () => {
//   useEffect(() => {
//     // Create a lottie animation instance for left confetti
//     const animationLeft = lottie.loadAnimation({
//       container: document.getElementById('confetti-container-left'), // Replace with the ID of the left container element
//       renderer: 'svg', // Use 'svg' for better compatibility
//       loop: true,
//       autoplay: true,
//       animationData: animationData,
//     });

//     // Create a lottie animation instance for right confetti
//     const animationRight = lottie.loadAnimation({
//       container: document.getElementById('confetti-container-right'), // Replace with the ID of the right container element
//       renderer: 'svg', // Use 'svg' for better compatibility
//       loop: true,
//       autoplay: true,
//       animationData: animationData,
//     });

//     return () => {
//       animationLeft.destroy();
//       animationRight.destroy();
//     };
//   }, []);

//   const location = useLocation();

//   // Access data from location.state
//   const { data } = location.state;

//   return (
//     <div>
//       <div className="confetti-container" id="confetti-container-left" style={{ position: 'absolute', top: 0, left: 0, width: '50%', height: '100%', zIndex: 2 }} />
//       <div className="container">
//         <div className="ticket">
//           <span className='logo'>
//             <IonIcon icon={ticketOutline} size="large" className='eventic_logo' />
//             <span className="logo">Eventic</span>
//           </span>
//           <div className="ticket-header">{data.user.userName}</div>
//           <div className="ticket-details">
//             <div className="detail-row">
//               <span className="detail-label">&#127881;</span>
//               <span className="detail-value">{data.event.eventname}</span>
//             </div>
//             <div className="detail-row">
//               <span className="detail-label">&#128197;</span>
//               <span className="detail-value">{data.event.eventDate} {data.event.eventTime}</span>
//             </div>
//             <div className="detail-row">
//               <span className="detail-label">&#128205;</span>
//               <span className="detail-value">{data.event.location}</span>
//             </div>
//             <div className="detail-row">
//               <span className="detail-label">&#128176;</span>
//               <span className="detail-value">Rs {data.event.price}</span>
//             </div>
//           </div>
//           <div className="qr-code-container">
//             <div className="qr-code"><img src={qrcode} alt="qrcode" /></div>
//           </div>
//         </div>
//         <button className="button">Download</button>
//       </div>
//       <div className="confetti-container" id="confetti-container-right" style={{ position: 'absolute', top: 0, right: 0, width: '50%', height: '100%', zIndex: 2 }} />
//     </div>
//   );
// };

// export default Ticket;
