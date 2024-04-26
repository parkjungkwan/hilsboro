import axios from 'axios';

const userInfo = typeof window !== `undefined` ? JSON.parse(localStorage.getItem('artist')) : null;

const register = (fd) => {

    return axios({
        url : `http://13.209.194.227:8080/replies/register`,
        method : 'post',
        data : fd,
        headers:{
            Authorization : `Bearer ${userInfo.token}`,
            'Content-Type': 'multipart/form-data'
        }
    })

};

const list = (reviewId) => {
    return axios({
        url : `http://13.209.194.227:8080/replies/list/${reviewId}`,
        method : 'get',
        headers:{
            Authorization: 'JWT fefege..'
        }
    })
};

const modify = (reply) => {
    return axios({
        url : 'http://13.209.194.227:8080/replies/modify/'+reply.rno,
        method : 'put',
        data : reply,
        headers :{
             Authorization : `Bearer ${userInfo.token}`,
            'Content-Type': 'multipart/form-data',
        }
    })
};

const deletes = (rno) => {
    return axios({
        url : `http://13.209.194.227:8080/replies/remove/${rno}`,
        method: 'delete',
        data : {...rno},
        headers:{
            Authorization: 'JWT fefege..'
        }
    })
};

export default { register, list, modify, deletes };