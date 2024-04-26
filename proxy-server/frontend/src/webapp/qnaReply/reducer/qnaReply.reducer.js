import {createAsyncThunk, createSlice} from "@reduxjs/toolkit";
import {QnaReplyService} from "webapp/qnaReply/index";

export const getQnaReplyList = createAsyncThunk('qreplies/list/reNo', async (reNo) => {
    const res = await QnaReplyService.list(reNo);
    return res.data;
})

export const getQnaReplyRegister = createAsyncThunk("qreplies/register", async (input) => {
    const res = await QnaReplyService.register(input);
    return res.data;

})

export const getQnaReplyModify = createAsyncThunk("qreplies/modify/reNo", async (qnaReply) => {
    const res = await QnaReplyService.modify(qnaReply);
    return res.data
})

export const getQnaReplyRemove = createAsyncThunk("qreplies/remove/reNo", async (reNo) => {
    const res = await QnaReplyService.remove(reNo)
    return res.data;
})

const isRejectAction = (action) => action.type.endsWith('rejected')

const qnaReplySlice = createSlice({
    name: 'qnaReplies',
    initialState: {
        msg: '',
        reply: []
    },

    reducers: {},
    extraReducers: (builder => {
        builder.addCase(getQnaReplyList.fulfilled, (state, {payload}) => {
            state.reply = payload;
        })
            .addCase(getQnaReplyRegister.fulfilled, (state, {payload}) => {
                const msg = '' + payload + '번 등록'
                return {...state, msg}
            })
            .addCase(getQnaReplyModify.fulfilled, (state, {payload}) => {
                state.reply = []
                console.log(payload)
            })
            .addCase(getQnaReplyRemove.fulfilled, (state, {payload}) => {
                state.reNo = payload
            })
            .addMatcher(isRejectAction, () => {
            })
            .addDefaultCase((state, payload) => {
            })
    })
})

const {actions, reducer} = qnaReplySlice
export const currentQnaReply = (state) => state.qreplies.qnaReply
export default reducer;