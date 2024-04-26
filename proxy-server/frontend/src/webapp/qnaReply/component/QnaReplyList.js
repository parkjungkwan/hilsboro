import React, {useEffect} from 'react'
import {useDispatch, useSelector} from "react-redux";
import {currentQna, getQnaRead} from "../../qna/reducer/qna.reducer";
import {Link, useParams} from "react-router-dom";
import {getQnaReplyList, getQnaReplyRemove} from "../reducer/qnaReply.reducer";
import Icofont from "react-icofont";
import {ReplyModify} from "../../reply";

const QnaReplyList = ({qnaId, changeFlag, flag}) => {

    const params = useParams()
    const dispatch = useDispatch()

    const qnaReplies = useSelector(state => {
        return state.qreplies.qnaReply
    })

    const qnaObj = useSelector(currentQna)

    const fetchRead = () => {
        dispatch(getQnaRead(params.qnaId))
    }

    const remove = async (reNo) => {
        let removeResult = window.confirm("정말 삭제하시겠습니까?")
        if (removeResult) {
            await dispatch(getQnaReplyRemove(reNo))
            changeFlag()
            fetchRead()
        }
    }

    useEffect(() => {
        dispatch(getQnaReplyList(qnaId))
    }, [flag])


    return (<>

        {qnaReplies.length > 0 ?
            qnaReplies.map((reply, rno) => {
                return (
                    <ul className=" container comment-box">
                        <li className="post-comment" key={reply.rno}>
                            <div className="comment-content" style={{border: "1px solid #9e9e9e85"}}>
                                <div className="post-body">
                                    <div className="comment-header">
                                        <div className="post-tags pull-left">
                                            <Link onClick={() => remove(reply.reNo)}>Remove</Link>
                                        </div>
                                        <div className="post-tags" style={{marginLeft: "1020px"}}>
                                            <Link onClick={() => handleOpen(reply)}>Modify</Link>
                                        </div>
                                    </div>
                                    <div className="post-message">
                                        <p className="line-height-26 font-15px">
                                            <div className="font-700 mb-0">
                                                <h5><Icofont icon="icofont-user-alt-5"
                                                             className="font-20px mt-20"/>&nbsp;&nbsp;{reply.replyer}
                                                </h5>
                                            </div>
                                            <Icofont icon="quote-left"
                                                     className="font-20px default-color mt-20"/> &nbsp;
                                            <span>{reply.text}</span>
                                        </p>
                                    </div>
                                    <b className="comment-footer">
                                        <h5>등록 : {reply.regDate}</h5>
                                        <h5>수정 : {reply.modDate}</h5>
                                    </b>
                                </div>
                            </div>
                        </li>
                        <ReplyModify open={show} handleClose={() => handleClose()}></ReplyModify>
                    </ul>
                )
            })

            : <div className="text-center"><Icofont icon="icofont-comment"
                                                    className="font-20px icofont-speech-comments"/>&nbsp;첫 번째 댓글을 입력해주세요
            </div>}
    </>)
}

export default QnaReplyList

