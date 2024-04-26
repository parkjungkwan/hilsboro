import React, {useEffect} from 'react'
import {useDispatch, useSelector} from "react-redux";
import {Link, useHistory, useParams} from "react-router-dom";
import {getQnaRead, getQnaRemove} from "../reducer/qna.reducer";
import {getReviewDelete} from "../../review/reducer/review.reducer";
import Icofont from "react-icofont";
import {ReplyList} from "../../reply";

const QnaRead =()=>{
    const QnaObj = useSelector(state=>{
        return state.qnas.params
    })
    const dispatch = useDispatch()
    const params = useParams()

    const fetchQnaRead =()=>{
        dispatch(getQnaRead(params.qnaId))
    }

    useEffect(()=>{
        fetchQnaRead()
    },[])

    const history = useHistory()

    const remove = async(qnaId)=>{
        const loginValue = JSON.parse(localStorage.getItem('artist'))
        const qnaRemove = window.confirm("정말 삭제 하시겠습니까?")
        if (loginValue) {
            if (qnaRemove) {
                alert("삭제가 완료되었습니다.")
                await dispatch(getQnaRemove(qnaId))
                history.push("/qnas/qna-list")
            }
        } else {
            alert("로그인해주세요")
        }
    }

    return(<>
        <div>
            <section className="white-bg">

                <div className="container">

                    <h1 className="text-center" style={{marginTop: "10px"}}>{QnaObj.writerName}님의 Review</h1>
                    <hr className="center_line default-bg"/>


                    <div className="post-info all-padding-20">
                        <h2>{QnaObj.title}</h2><br></br>
                        <blockquote style={{boxShadow: "1px 2px 2px 2px lightgrey"}}>
                            <h5>{QnaObj.content}</h5>
                            <p style={{textAlign: "right"}}>등록 : {QnaObj.regDate}<br></br>수정 : {QnaObj.modDate}
                            </p>
                        </blockquote>
                    </div>

                    <div className="post" style={{marginBottom: "100px"}}>
                        <div className="post-tags pull-right">
                            <Link to={`/qnas/qna-modify/${params.qnaId}`}>
                                <div>Modify</div>
                            </Link>
                        </div>

                        <div className="post-tags pull-left">
                            <Link to="/reviews/review_list"> Review List</Link>

                            <Link to={`/qnas/qna-read/${QnaObj.qnaId}`}>
                                <div onClick={() => remove(params.qnaId)}>Review Remove</div>
                            </Link>
                        </div>

                        <div className="post-tags text-center">
                            < Link className="pull-right" to="/replies/reply_register">
                                <Icofont size="30px" icon="icofont-speech-comments"/>Comments</Link>
                        </div>
                    </div>
                </div>
                {/*<ReplyList qnaId={params.qnaId} changeFlag={changeFlag} flag={flag}></ReplyList>*/}
            </section>
        </div>
    </>)
}

export default QnaRead