import React, { useState } from "react";
import './Gallery.css';
import slide_image_1 from '../assets/img.jpeg';
import slide_image_2 from '../assets/img_2.jpeg';
import slide_image_3 from '../assets/img_3.jpeg';
import slide_image_4 from '../assets/img_4.jpeg';
import slide_image_5 from '../assets/img_5.jpeg';
import slide_image_6 from '../assets/img6.jpeg';
import { IoClose } from "react-icons/io5";
import {title} from './Service.css';

const Gallery=()=>{
    let data = [
       {id: 1,
        imgSrc: slide_image_1,
       },

       {id: 2,
        imgSrc: slide_image_2,
       },

       {id: 3,
        imgSrc: slide_image_3,
       },

       {id: 4,
        imgSrc: slide_image_4,
       },


       {id: 5,
        imgSrc: slide_image_5,
       },

       {id: 6,
        imgSrc: slide_image_6,
       },
    ]

const [model,setModel] = useState(false);
const [tempimgSrc,setTempImgSrc] = useState('');

const getImg = (imgSrc) =>{
    setTempImgSrc(imgSrc);
   setModel(true);
}


    return(
        <>
        <p className="title">Gallery:</p>
        <div className={model? 'model open':'model'}>
<img src={tempimgSrc} />
<IoClose onClick={()=> setModel(false)}/>
        </div>
        <div className="gallery">
{
    data.map((item,index)=>{
        return(
            <div className="pics" key={index} onClick={()=>getImg(item.imgSrc)}>
                <img src={item.imgSrc} style={{width:'100%'}}/>
                </div>
        )

    })
}
        </div>
        </>
    );
}

export default Gallery;