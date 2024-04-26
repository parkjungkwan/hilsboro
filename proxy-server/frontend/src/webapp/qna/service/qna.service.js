import axios from 'axios';

const userInfo = typeof window !== `undefined` ? JSON.parse(localStorage.getItem('artist')) : null;

const register = (qd) => {
    return axios({
        url: `http://13.209.194.227:8080/qnas/register`,
        method: 'post',
        data: qd,
        headers: {
            Authorization: `Bearer ${userInfo.token}`,
        }
    })
}

const list = (pageResult) => {
    const str = "page=" + (!pageResult.page ? 1 : pageResult.page) + "&type=" + (pageResult.type) + "&keyword=" + (pageResult.keyword)

    return axios({
        url: `http://13.209.194.227:8080/qnas/list/pages?` + str,
        method: 'get',
        headers: {
            Authorization: "JWT list"
        }
    })
}

const read = (qnaId) => {
    return axios({
        url: `http://13.209.194.227:8080/qnas/read/${qnaId}`,
        method: 'get',
        headers: {
            Authorization: "JWT read"
        }
    })
}

const modify = (qna) => {
    return axios({
        url: `http://13.209.194.227:8080/qnas/modify/` + qna.qnaId,
        method: 'put',
        data: qna,
        headers: {
            Authorization: `Bearer ${userInfo.token}`
        }
    })
}

const remove = (qnaId) => {
    return axios({
        url: `http://13.209.194.227:8080/qnas/remove/${qnaId}`,
        method: 'delete',
        data: {...qnaId},
        headers: {
            Authorization: 'JWT remove'
        }
    })
}

export default {register, list, read, modify, remove}
