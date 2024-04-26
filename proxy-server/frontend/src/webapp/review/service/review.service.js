import axios from 'axios'
import { useRef } from 'react';

const userInfo = typeof window !== `undefined` ? JSON.parse(localStorage.getItem('artist')) : null;

const register=(fd)=>{

    return axios({
        url: `http://13.209.194.227:8080/reviews/register`,
        method:'post',
        data: fd,
        headers:{
            Authorization: `Bearer ${userInfo.token}`,
            'Content-Type': 'multipart/form-data'
        }
    })
}

const list=(pageResult)=>{

    const str = "page=" + (!pageResult.page?1:pageResult.page) +"&type="+ (pageResult.type) +"&keyword=" + (pageResult.keyword)

    return axios({
        url : `http://13.209.194.227:8080/reviews/list/pages?`+str,
        method : 'get',
        headers:{
            Authorization : 'JWT fefefg...'
        }
    })
    
}

const read=(reviewId)=>{
    return axios({
        url : `http://13.209.194.227:8080/reviews/read/${reviewId}`,
        method:'get',
        headers:{
            Authorization : 'JWT fefefg...'
        }
    })
}

const modify=(review)=>{

    return axios({
        url : `http://13.209.194.227:8080/reviews/modify/`+review.reviewId,
        method : "put",
        data : review,
        headers:{
            Authorization: `Bearer ${userInfo.token}`,
            'Content-Type': 'multipart/form-data'
        }
    })

}

const deletes=(reviewId)=>{

    return axios({
        url : `http://13.209.194.227:8080/reviews/remove/${reviewId}`,
        method : 'delete',
        data : {...reviewId},
        headers :{
            Authorization : 'JWT fefefg...'
        }
    })

}

export default{register, list, read, modify, deletes}