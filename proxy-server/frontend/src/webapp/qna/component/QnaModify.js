import React, {useEffect, useState} from 'react'
import {currentQna, getQnaModify} from "../reducer/qna.reducer";
import {useDispatch, useSelector} from "react-redux";
import {Link, useHistory} from "react-router-dom";
import {getReviewModify} from "../../review/reducer/review.reducer";

const QnaModify = () => {
    const loginValue = JSON.parse(localStorage.getItem('artist'))
    const [title, setTitle] = useState('')
    const [content, setContent] = useState('')

    const qnaObj = useSelector(currentQna)

    const dispatch = useDispatch()
    const history = useHistory()

    useEffect(() => {
        setTitle(qnaObj.title)
        setContent(qnaObj.content)
    }, [qnaObj])

    const handleChangeTitle = (e) => {
        setTitle(e.target.value)
    }

    const handleChangeContent = (e) => {
        setContent(e.target.value)
    }

    const qnaModify = async (e) => {
        let modifyResult = window.confirm("Q&A를 수정하시겠습니까?")

        const obj = {
            qnaId: qnaObj.qnaId,
            title: title,
            content: content,
            writerId: qnaObj.writerId
        }

        if (modifyResult) {
            await dispatch(getQnaModify(obj))
            alert("리뷰 수정 완료!")
            history.push(`/qnas/qna-read/${qnaObj.qnaId}`)
        }
    }


    return (<>
        <div className="white-bg">
            <div className="container">
                <div id="respond" className="comment-respond">
                    <h1 className="section-title text-center">Q&A Modify</h1>
                    <hr className="center_line default-bg" style={{marginBottom: "50px"}}/>

                    <div className="row-form row">
                        <div className="col-form col-md-2">
                            <label> * writerName </label>
                            <textarea style={{color: "black", marginBottom: "-45px", border: "1px solid #9e9e9eb5"}}
                                      value={qnaObj.writerName} name="writerName" readOnly></textarea>
                        </div>
                    </div>
                </div>

                <div className="row-form row">
                    <div className="col-form col-md-5">
                        <label> * Title </label>
                        <textarea name="title"
                                  style={{color: "black", marginBottom: "30px", border: "1px solid #9e9e9eb5"}}
                                  className="md-textarea" id="title" rows="2"
                                  placeholder="제목을 수정하세요"
                                  value={title}
                                  onChange={(e) => handleChangeTitle(e)}
                        ></textarea>
                    </div>
                </div>

                <div className="row-form row">
                    <div className="col-form col-md-12">
                        <label> * Content </label>
                        <textarea
                            name="content"
                            style={{color: "black", marginBottom: "30px", border: "1px solid #9e9e9eb5"}}
                            rows="10"
                            placeholder="내용을 수정하세요 *"
                            value={content}
                            onChange={(e) => handleChangeContent(e)}
                        ></textarea>
                    </div>
                </div>

                <div style={{marginTop: "50px"}}>

                    <button className="btn btn-success btn-md btn-default remove-margin pull-right"
                            onClick={(e) => !loginValue ? alert("로그인을 해주세요", history.push(`/qnas/qna-read/${qnaObj.reviewId}`)) : qnaModify(e)}>Modify
                    </button>
                    < Link to={`/qnas/qna-read/${qnaObj.qnaId}`}>
                        <button className="btn btn-color btn-md btn-default remove-margin">Cancel</button>
                    </Link>
                </div>

            </div>
        </div>
    </>)
}

export default QnaModify;