import axios from 'axios'

const userInfo = typeof window !== `undefined` ? JSON.parse(localStorage.getItem('artist')) : null;

const register = (qr) => {

    return axios({
        url: 'http://13.209.194.227:8080/qreplies/register',
        method: 'post',
        data: qr,
        headers: {
            Authorization: `Bearer ${userInfo.token}`,
        }
    })
}

const list = (reNo) => {
    return axios({
        url: `http://13.209.194.227:8080/qreplies/list/${reNo}`,
        method: 'get',
        headers: {
            Authorization: 'JWT list'
        }
    })
}

const modify = (qnaReply) => {
    return axios({
        url: 'http://13.209.194.227:8080/qreplies/modify/ + qnaReply.reNo',
        method: 'put',
        data: qnaReply,
        headers: {
            Authorization: `Bearer ${userInfo.token}`,
        }
    })
}

const remove = (reNo) => {
    return axios({
        url: `http://13.209.194.227:8080/qreplies/remove/${reNo}`,
        method: 'delete',
        data: {...reNo},
        headers: {
            Authorization: 'JWT remove'
        }
    })
}

export default {register, list, modify, remove}