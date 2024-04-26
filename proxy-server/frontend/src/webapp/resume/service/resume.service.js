import axios from 'axios';

const userInfo = typeof window !== `undefined` ? JSON.parse(localStorage.getItem('artist')) : null;


const resumeList = (page) => {
    return axios({
        url: `http://13.209.194.227:8080/resume/list_page?page=` + page,
        method:'get',
        headers:{
            Authorization: 'JWT fefege..'
        }
    })
};
const resumeRegister = (data) => {
    return axios({
        url: `http://13.209.194.227:8080/resume/register`,
        method: 'post',
        data : data,
        headers:{
            Authorization:  `Bearer ${userInfo.token}`
        }
    })
};
const resumeModify = (data) => {
    return axios({
        url: `http://13.209.194.227:8080/resume/edit`,
        method: 'put',
        data: data,
        headers:{
            Authorization: 'JWT fefege...'
        }
    })
};
const resumeRead = (resumeId) => {
    return axios({
        url : `http://13.209.194.227:8080/resume/read/` + resumeId,
        method: 'get',
        headers:{
            Authorization: 'JWT fefege...'
        }
    })
};

const resumeDelete = (resume) =>{
    return axios({
        url: `http://13.209.194.227:8080/resume/delete`,
        method: 'delete',
        data: {...resume},
        headers:{
            Authorization: 'JWT fefege...'
        }
    })
};
const resumeSearch = (param) => {
    const str = "page=" + (!param.page ? 1 : param.page) +"&type="+ (encodeURIComponent(param.type)) +"&keyword=" + (param.keyword)
    return axios({
        url: `http://13.209.194.227:8080/resume/search?` + str,
        method: 'get',
        headers: {
            Authorization: 'JWT fefege...'
        }
    })
}

const countResume = (artistId) =>{
    return axios({
        url: `http://13.209.194.227:8080/resume/count/${artistId}`,
        method: 'get',
        headers:{
            Authorization: 'JWT fefege...'
        }
    })
};

const categoryList = () =>{
    return axios({
        url: `http://13.209.194.227:8080/category/findall`,
        method: 'get',
        headers:{
            Authorization: 'JWT fefege...'
        }
    })
};

const uploadFile = (formData) =>{
    return axios({
        url: `http://13.209.194.227:8080/resume_file/upload_file`,
        method: 'post',
        data: formData,
        headers:{
            "Content-Type": "multipart/form-data",
            Authorization: 'JWT fefege...'
        }
    })
};

export default {resumeList, resumeRegister, resumeModify, resumeRead, resumeDelete , resumeSearch, countResume, categoryList, uploadFile}