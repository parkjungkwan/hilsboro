import React, {useEffect, useState} from 'react'
import {useDispatch, useSelector} from "react-redux";
import {currentQna, getQnaRead} from "../../qna/reducer/qna.reducer";
import {Link, useHistory} from "react-router-dom";
import {getQnaReplyRegister} from "../reducer/qnaReply.reducer";

const QnaReplyRegister = () => {
    const qnaObj = useSelector(currentQna)
    const loginValue = JSON.parse(localStorage.getItem('artist'))
    const [input, setInput] = useState({
        text: '',
        replyer: !loginValue ? '' : loginValue.name,
        qnaId: qnaObj.qnaId
    })

    const dispatch = useDispatch()

    const history = useHistory()

    const fetchRead = () => {
        dispatch(getQnaRead(qnaObj.qnaId))
    }

    const handleSubmit = async (e) => {
        e.preventDefault()
        e.stopPropagation()
        console.log(e.target.name, e.target.value)

        setInput({
            ...input,
            [e.target.name]: e.target.value
        })
    }

    const handleClick = async (e) => {
        e.preventDefault()
        e.stopPropagation()
        const data = {...input}
        console.log(data)
        dispatch(getQnaReplyRegister(data))
        history.replace(`/qnas/qna-read/${input.qnaId}`)
    }

    useEffect(() => {
        fetchRead()
    }, [])

    return (<>

        <section className="white-bg" style={{marginTop: "-100px", marginBottom: "auto"}}>
            <div className="container">
                <div id="respond" className="comment-respond">

                    <h1 className="section-title text-center">Q&A Reply Register</h1>
                    <hr className="center_line default-bg" style={{marginBottom: "70px"}}/>

                    <div className="row-form row">
                        <div className="col-form col-md-2">
                            <input
                                style={{color: "black", marginBottom: "30px", border: "1px solid #9e9e9eb5"}}
                                type="text"
                                name="qnaId"
                                placeholder="reviewId *"
                                value={input?.qnaId}
                                onChange={(e) => handleSubmit(e)}
                            />
                        </div>
                    </div>

                    <div className="row-form row">
                        <div className="col-form col-md-3">
                            <input
                                style={{color: "black", marginBottom: "30px", border: "1px solid #9e9e9eb5"}}
                                type="text"
                                name="replyer"
                                placeholder="이름을 입력해주세요 *"
                                value={loginValue?.name}
                                onChange={(e) => handleSubmit(e)}
                            />
                        </div>
                    </div>

                    <div className="row-form row">
                        <div className="col-form col-md-12">
                <textarea
                    style={{color: "black", marginBottom: "30px", border: "1px solid #9e9e9eb5"}}
                    name="text"
                    rows="10"
                    placeholder="댓글을 입력해주세요 *"
                    value={input?.text}
                    onChange={(e) => handleSubmit(e)}
                ></textarea>

                        </div>
                    </div>
                    <div>
                        <button className="btn btn-success btn-md btn-default remove-margin pull-right"
                                onClick={handleClick}>Register
                        </button>
                        <Link to={`/qnas/qna-read/${qnaObj.qnaId}`}>
                            <button className="btn btn-color btn-md btn-default"
                            >Cancel
                            </button>
                        </Link>
                    </div>

                </div>
            </div>
        </section>
    </>)
}

export default QnaReplyRegister