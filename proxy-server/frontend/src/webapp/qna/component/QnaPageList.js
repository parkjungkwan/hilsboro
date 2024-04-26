import React from 'react'
import {useDispatch, useSelector} from "react-redux";
import Icofont from "react-icofont";
import {getQnaList} from "webapp/qna/reducer/qna.reducer";

const QnaPageList = () => {
    const {pageList, page, start, end, prev, next} = useSelector(state => state.qnas.pageResult)
    const searchType = useSelector(state => state.qnas.type)
    const searchKeyword = useSelector(state => state.qnas.keyword)

    const dispatch = useDispatch()

    const movePage = (page) => {

        const param = {type: searchType, keyword: searchKeyword, page: page}

        dispatch(getQnaList(param))
    }

    const list = pageList.map(i => <button className="btn" key={i} onClick={() => movePage(i)}>{i}</button>)

    return (
        <div style={{marginBottom: "100px"}}>
            <div className="my-auto mx-auto text-center">
                <div className="row mt-100">
                    <div className="col-md-12">
                        <div className="text-center">
                            <div className="pagination dark-color">
                            </div>
                        </div>
                    </div>
                </div>
                {prev ? <button className="btn" onClick={() => movePage(start - 1)}>
                    <Icofont icon="long-arrow-left" className="mr-5 xs-display-none"/> prev
                </button> : <></>}
                {list}
                {next ? <button className="btn " onClick={() => movePage(end + 1)}>
                    next <Icofont icon="long-arrow-right" className="ml-5 xs-display-none"/>
                </button> : <></>}
            </div>
        </div>
    )
}

export default QnaPageList