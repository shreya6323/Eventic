import React from 'react';
import { Swiper } from 'swiper/react';
import 'swiper/swiper-bundle.css'; // Import the bundle, adjust the path according to your setup
import { SwiperSlide } from 'swiper/react'; // Import SwiperSlide from 'swiper/react'
import './SwiperClass.css';
import slide_image_1 from '../assets/img.jpeg';
import slide_image_2 from '../assets/img_2.jpeg';
import slide_image_3 from '../assets/img_3.jpeg';
import slide_image_4 from '../assets/img_4.jpeg';
import slide_image_5 from '../assets/img_5.jpeg';
import slide_image_6 from '../assets/img6.jpeg';
import { IonIcon } from '@ionic/react';
import { ticketOutline } from 'ionicons/icons';


// Install Swiper modules
import {EffectCoverflow,Pagination,Navigation} from 'swiper/modules';

function SwiperClass() {
  return (
  
   <>
  
                   
  <p className="heading">Upcoming Events...</p>
     <Swiper
    
     effect={'coverflow'}
     grabCursor={true}
     centeredSlides={true}
     
     loop={true}
     spaceBetween={-950}
     slidesPerview={5}
     coverflowEffect={{
        rotate:0,
        stretch:0,
        depth:400,
        modifier:4.5,
     }}
    
     pagination={{el:'.swiper-pagination',clickable:true}}
     navigation={
        {
            nextEl:'swiper-button-next',
            prevEl:'swiper-button-prev',
            clickable:true,
        }
     }
     modules={[EffectCoverflow,Pagination,Navigation]}

     className='swiper_container' 
      >

     <SwiperSlide>
      <img src={slide_image_1} alt="slide_image"/>
     </SwiperSlide>

     <SwiperSlide>
      <img src={slide_image_2} alt="slide_image"/>
     </SwiperSlide>

     <SwiperSlide>
      <img src={slide_image_3} alt="slide_image"/>
     </SwiperSlide>

     <SwiperSlide>
      <img src={slide_image_4} alt="slide_image"/>
     </SwiperSlide>

     <SwiperSlide>
      <img src={slide_image_5} alt="slide_image"/>
     </SwiperSlide>

     <SwiperSlide>
      <img src={slide_image_6} alt="slide_image"/>
     </SwiperSlide>
     <div className='slider-controler'>
        </div>
     </Swiper>
      </>
  );
}

export default SwiperClass;
