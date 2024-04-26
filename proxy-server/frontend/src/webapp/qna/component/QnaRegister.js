import React, {useState} from 'react'
import {useDispatch, useSelector} from "react-redux";
import {getReviewRegister} from "../../review/reducer/review.reducer";
import {Link, useHistory} from "react-router-dom";
import {getQnaRegister} from "../reducer/qna.reducer";

const QnaRegister = () => {
    const loginValue = JSON.parse(localStorage.getItem('artist'))

    const qnas = useSelector(state => {
        return state.qnas.pageResult.dtoList;
    })

    const [input, setInput] = useState({
        title: '',
        content: '',
        writerId: loginValue?.artistId,
        writerName: loginValue?.name
    })

    const dispatch = useDispatch()
    const history = useHistory()

    const handleSubmit = async (e) => {
        e.preventDefault()
        e.stopPropagation()
        console.log(e.target.name, e.target.value)

        setInput({
            ...input,
            [e.target.name]: e.target.value
        })
    }

    const handleClick=(e)=>{
        e.stopPropagation()
        e.preventDefault()
        const data ={...input}
        console.log(data)
        dispatch(getQnaRegister(data))
    }


    return (
        <section className="white-bg">
            <div className="container" style={{marginTop: "-100px", marginBottom: "auto"}}>
                <div id="respond" className="comment-respond">

                    <h1 className="section-title text-center">Q&A Register</h1>
                    <hr className="center_line default-bg" style={{marginBottom: "50px"}}/>

                    <div className="row-form row">
                        <div className="col-form col-md-2">

                            <input
                                style={{color: "black", marginBottom: "30px", border: "1px solid #9e9e9eb5"}}
                                type="text"
                                name="artistId"
                                placeholder="artistId *"
                                value={loginValue?.artistId}
                                onChange={(e) => handleSubmit(e)}/>
                        </div>
                    </div>

                    <div className="row-form row">
                        <div className="col-form col-md-2">

                            <input
                                style={{color: "black", marginBottom: "30px", border: "1px solid #9e9e9eb5"}}
                                type="text"
                                name="writerName"
                                placeholder="writerName *"
                                value={loginValue?.name}
                                onChange={(e) => handleSubmit(e)}
                            />

                        </div>
                    </div>


                    <div className="row-form row">
                        <div className="col-form col-md-5">

                   <textarea
                       style={{color: "black", marginBottom: "30px", border: "1px solid #9e9e9eb5"}}
                       name="title"
                       placeholder="제목을 입력해주세요 *"
                       value={input.title}
                       onChange={(e) => handleSubmit(e)}>
                    </textarea>

                        </div>
                    </div>

                    <div className="row-form row">
                        <div className="col-form col-md-12">

                  <textarea
                      style={{color: "black", marginBottom: "30px", border: "1px solid #9e9e9eb5"}}
                      name="content"
                      id="content"
                      rows="10"
                      placeholder="내용을 입력해주세요 *"
                      value={input.content}
                      onChange={(e) => handleSubmit(e)}
                  ></textarea>

                        </div>
                    </div>

                </div>

                <button className="btn btn-success btn-md btn-default remove-margin pull-right"
                        onClick={(e)=>!loginValue ? alert("로그인을 해주세요", history.push("/artist/artist-signin")) : handleClick(e)}>Register
                </button>
                <Link to="/qnas/qna-list">
                    <button className="btn btn-color btn-md btn-default remove-margin"
                            style={{marginLeft: "10px"}}>Cancel
                    </button>
                </Link>

            </div>
        </section>
    )
}

export default QnaRegister