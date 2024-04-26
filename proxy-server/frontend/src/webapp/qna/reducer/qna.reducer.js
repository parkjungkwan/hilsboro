import {createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import {QnaService} from "webapp/qna/index";

export const getQnaList = createAsyncThunk("qnas/list",
    async (pageResult) => {
        const res = await QnaService.list(pageResult)
        return res.data
    }
)

export const getQnaRegister = createAsyncThunk("qnas/register",
    async (input) => {
        const res = await QnaService.register(input)
        return res
    })

export const getQnaRead = createAsyncThunk('qnas/read',
    async (qnaId) => {
        const res = await QnaService.read(qnaId)
        return res.data
    })

export const getQnaModify = createAsyncThunk("qnas/modify",
    async (qna) => {
        const res = await QnaService.modify(qna)
        return res.data
    })

export const getQnaRemove = createAsyncThunk("qnas/delete",
    async (qnaId) => {
        const res = await QnaService.delete(qnaId)
        return res.data
    })

const isRejectAction = action => (action.type.endsWith('rejected'))

const qnaSlice = createSlice({
    name: 'qnas',
    initialState: {
        msg: '',
        pageResult: {
            dtoList: [],
            page: 1,
            pageList: [],
            start: 1,
            end: 1,
            prev: false,
            next: false,
        },
        type: '',
        keyword: '',
        params: {}
    },

    reducers: {
        changeSearch: (state, action) => {
            state.type = action.payload.type
            state.keyword = action.payload.keyword
        }
    },

    extraReducers: (builder => {
        builder.addCase(getQnaList.fulfilled, (state, {payload}) => {
            state.pageResult = payload
        })
            .addCase(getQnaRegister.fulfilled, (state, {payload}) => {
                return {...state}
            })
            .addCase(getQnaRead.fulfilled, (state, {payload}) => {
                state.params = payload
            })
            .addCase(getQnaModify.fulfilled, (state, {payload}) => {
                state.qnaId = payload
            })
            .addCase(getQnaRemove.fulfilled, (state, {payload}) => {
                state.params = payload
            })
            .addMatcher(isRejectAction, () => {
            })
            .addDefaultCase((state, payload) => {

            })

    })
})

const {actions, reducer} = qnaSlice

export const currentQna = state =>  state.qnas.params
export const {changeSearch} = actions
export default reducer