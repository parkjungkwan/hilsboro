import React, {useEffect} from 'react'
import {useDispatch, useSelector} from "react-redux";
import {Link} from "react-router-dom";
import Footer from "webapp/common/Footer/FooterOne";
import {getQnaList} from "webapp/qna/reducer/qna.reducer";
import QnaSearch from "./QnaSearch";
import QnaPageList from "webapp/qna/component/QnaPageList";


const QnaList = () => {
    const pageResult = useSelector(state => state.qnas.pageResult)

    const page = pageResult.page

    const dispatch = useDispatch()

    const qnas = useSelector(state => {
        return state.qnas.pageResult.dtoList;
    })

    useEffect((e) => {
        dispatch(getQnaList(page))
    }, [])

    return (
        <>
            <section className="white-bg">
                <div className="col-md-12">
                    <div className="section-title text-center">
                        <h1>Philo_Arte에 궁금한 사항을 문의하세요 </h1>
                    </div>
                </div>
                <hr className="center_line default-bg"/>

                <div className="container" style={{marginTop: "75px"}}>
                    <div className="post-tags pull-left">
                        < Link to="/">Home</ Link>
                    </div>
                    <div className="post-tags pull-right">
                        < Link to="/qnas/qna-register">Register</ Link>
                    </div>
                    <br></br><QnaSearch></QnaSearch>
                </div>

                <div className="row">
                    <div className="row-md-12">
                        <div className="container display-flex" style={{flexWrap: "wrap", justifyContent: "center"}}>
                            {qnas.map((qna, qnaId) => {
                                return (
                                    <div className="mt-10">
                                        <div className="row-md-10 pricing-table row-sm-5">
                                            <div className="pricing-box" style={{
                                                margin: "3vh",
                                                width: "18vw",
                                                boxShadow: "3px 3px 3px 3px lightgrey",
                                                border: "1px solid"
                                            }}>
                                                <h3 className="dark-color mb-0" style={{
                                                    marginBottom: "50px",
                                                    textOverflow: "ellipsis",
                                                    overflow: "hidden",
                                                    whiteSpace: "nowrap"
                                                }}>{qna.title}</h3>
                                                <h5 className="dark-color mb-0">By&nbsp;{qna.writerName}</h5>
                                                <h5 className="dark-color"
                                                    style={{marginTop: "50px"}}>{qna.regDate}</h5>
                                            </div>
                                            <br></br>
                                            <div className="pricing-box-bottom text-center">
                                                <Link to={`/qnas/qna-read/${qna.qnaId}`}
                                                      className="btn btn-lg btn-square btn-color">더 보기</Link>
                                            </div>
                                        </div>
                                    </div>
                                )
                            })}

                        </div>
                    </div>
                    <QnaPageList></QnaPageList>
                </div>
            </section>
            <Footer></Footer>
        </>
    )

}

export default QnaList