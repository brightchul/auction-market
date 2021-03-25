/**
 * 메인 페이지
 */
import {
  createAction,
  createAsyncAction,
  ActionType, 
  createReducer
} from 'typesafe-actions';
import produce from 'immer';
import { takeLatest } from 'redux-saga/effects';
import createAsyncSaga, {
  createActionTypes
} from '../lib/createAsyncSaga';
import * as productsAPI from "../lib/api/products";
import * as commentsAPI from "../lib/api/comments";
import { AxiosError } from 'axios';
import { format } from 'node:path';



const INITIALIZE_FORM = "product/INITIALIZE_FORM";
const CHANGE_COMMENT_FIELD = "product/CHANGE_COMMENT_FIELD";
const CHANGE_AUCTION_FIELD = "product/CHANGE_AUCTION_FIELD";
const TOGGLE_COMMENT_MODE = "product/TOGGLE_COMMENT_MODE";

const [
  LOAD_PRODUCT,
  LOAD_PRODUCT_SUCCESS,
  LOAD_PRODUCT_FAILURE,
] = createActionTypes("product/LOAD_PRODUCT");

export const loadProduct = createAsyncAction(
  LOAD_PRODUCT,
  LOAD_PRODUCT_SUCCESS,
  LOAD_PRODUCT_FAILURE,
)<any, any, AxiosError>();


const [
  LOAD_COMMENTS,
  LOAD_COMMENTS_SUCCESS,
  LOAD_COMMENTS_FAILURE,
] = createActionTypes("product/LOAD_COMMENTS");

export const loadComments = createAsyncAction(
  LOAD_COMMENTS,
  LOAD_COMMENTS_SUCCESS,
  LOAD_COMMENTS_FAILURE,
)<any, any, AxiosError>();



const [
  REGISTER_COMMENT,
  REGISTER_COMMENT_SUCCESS,
  REGISTER_COMMENT_FAILURE,
] = createActionTypes("product/REGISTER_COMMENT");

export const registerComment = createAsyncAction(
  REGISTER_COMMENT,
  REGISTER_COMMENT_SUCCESS,
  REGISTER_COMMENT_FAILURE,
)<any, any, AxiosError>();

const [
  UPDATE_COMMENT,
  UPDATE_COMMENT_SUCCESS,
  UPDATE_COMMENT_FAILURE,
] = createActionTypes("product/UPDATE_COMMENT");

export const updateComment = createAsyncAction(
  UPDATE_COMMENT,
  UPDATE_COMMENT_SUCCESS,
  UPDATE_COMMENT_FAILURE,
)<any, any, AxiosError>();



const [
  DELETE_COMMENT,
  DELETE_COMMENT_SUCCESS,
  DELETE_COMMENT_FAILURE,
] = createActionTypes("product/DELETE_COMMENT");

export const deleteComment = createAsyncAction(
  DELETE_COMMENT,
  DELETE_COMMENT_SUCCESS,
  DELETE_COMMENT_FAILURE,
)<any, any, AxiosError>();


const [
  ENTER_AUCTION,
  ENTER_AUCTION_SUCCESS,
  ENTER_AUCTION_FAILURE,
] = createActionTypes("product/ENTER_AUCTION");

export const enterAuction = createAsyncAction(
  ENTER_AUCTION,
  ENTER_AUCTION_SUCCESS,
  ENTER_AUCTION_FAILURE,
)<any, any, AxiosError>();


const [
  CANCEL_AUCTION,
  CANCEL_AUCTION_SUCCESS,
  CANCEL_AUCTION_FAILURE,
] = createActionTypes("product/CANCEL_AUCTION");

export const cancelAuction = createAsyncAction(
  CANCEL_AUCTION,
  CANCEL_AUCTION_SUCCESS,
  CANCEL_AUCTION_FAILURE,
)<any, any, AxiosError>();



const [
  LIKE,
  LIKE_SUCCESS,
  LIKE_FAILURE,
] = createActionTypes("product/LIKE");

export const like = createAsyncAction(
  LIKE,
  LIKE_SUCCESS,
  LIKE_FAILURE,
)<any, any, AxiosError>();


const [
  UN_LIKE,
  UN_LIKE_SUCCESS,
  UN_LIKE_FAILURE,
] = createActionTypes("product/UN_LIKE");

export const unLike = createAsyncAction(
  UN_LIKE,
  UN_LIKE_SUCCESS,
  UN_LIKE_FAILURE,
)<any, any, AxiosError>();



export const changeCommentField = createAction(
  CHANGE_COMMENT_FIELD,
  ({ key, value }: { key: string; value: any }) => ({
    key,
    value,
  })
)();

export const toggleCommentMode = createAction(
  TOGGLE_COMMENT_MODE,
  (id)=>(id)
)()


const loadProductSaga = createAsyncSaga(LOAD_PRODUCT, productsAPI.getProduct);
const loadCommentsSaga = createAsyncSaga(LOAD_COMMENTS, commentsAPI.loadComments);
const registerCommentSaga = createAsyncSaga(REGISTER_COMMENT, commentsAPI.saveComment);
const updateCommentSaga = createAsyncSaga(UPDATE_COMMENT, commentsAPI.updateComment);
const deleteCommentSaga = createAsyncSaga(DELETE_COMMENT, commentsAPI.deleteComment);


// const [
//   REGISTER_PRODUCT,
//   REGISTER_PRODUCT_SUCCESS,
//   REGISTER_PRODUCT_FAILURE,
// ] = createActionTypes("product/REGISTER_PRODUCT");

// export const registerProduct = createAsyncAction(
//   REGISTER_PRODUCT,
//   REGISTER_PRODUCT_SUCCESS,
//   REGISTER_PRODUCT_FAILURE,
// )<any, any, AxiosError>();

export function* productSaga() {
  yield takeLatest(LOAD_PRODUCT, loadProductSaga);
  yield takeLatest(LOAD_COMMENTS, loadCommentsSaga);
  yield takeLatest(REGISTER_COMMENT, registerCommentSaga);
  yield takeLatest(UPDATE_COMMENT, updateCommentSaga);
  yield takeLatest(DELETE_COMMENT, deleteCommentSaga);
}


interface CommentsState {
  product: any;
  dashboard: any;
  comments: any;
  auctions: any;
  form: {
    [key:string] : any;
    
  };
  error: any;
}

const initialState: CommentsState = {
  product: null,
  dashboard: {

  },
  comments: [],
  auctions: [],
  form: {
    auction:{
      price: 0,
    },
    comments: {
      content: '',
      update: '',
    },
  },
  error: "",
};


const product = createReducer<CommentsState, any>(initialState, {
  [INITIALIZE_FORM]: (state) => ({
    ...state,
  }),
  [CHANGE_COMMENT_FIELD]: (state, { payload : {key, value}}) => (
    produce(state, (draft) => {
      draft.form.comments[key] = value
    })
  ),
  [TOGGLE_COMMENT_MODE]: (state, { payload: id }) => {
    const idx = state.comments.findIndex((comment: any)=>comment.id === id);
    return produce(state, (draft) => {
      draft.form.comments.update = draft.comments[idx].content;
      draft.comments[idx].isUpdateMode = !draft.comments[idx].isUpdateMode;
    });
  },
  [CHANGE_AUCTION_FIELD]: (state) => ({
    ...state,
  }),
  [LOAD_PRODUCT_SUCCESS]: (state, {payload: product}) => ({
    ...state,
    product
  }),
  [LOAD_COMMENTS_SUCCESS] : (state, {payload: comments}) => ({
    ...state,
    comments
  }),
  [REGISTER_COMMENT_SUCCESS]: (state, {payload: comments}) => ({
    ...state,
  }),
  [UPDATE_COMMENT_SUCCESS]:  (state) => ({
    ...state,
  }),
  [DELETE_COMMENT_SUCCESS]:  (state) => ({
    ...state,
  }),
  [LIKE_SUCCESS]: (state) => ({
    ...state,
  }),
  [UN_LIKE_SUCCESS]: (state) => ({
    ...state,
  }),
});

export default product;

