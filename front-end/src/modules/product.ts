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
import { takeLatest, fork, call, take, cancelled, put } from "redux-saga/effects";
import createAsyncSaga, {
  createActionTypes
} from '../lib/createAsyncSaga';
import { createSocketChannel } from '../lib/createSocketChannel';
import { Client, Message } from '@stomp/stompjs';

import * as categoriesAPI from "../lib/api/categories";
import * as productsAPI from "../lib/api/products";
import * as commentsAPI from "../lib/api/comments";
import * as likeAPI from "../lib/api/like";
import * as auctionsAPI from "../lib/api/auctions";
import { AxiosError } from 'axios';






const INITIALIZE = "product/INITIALIZE";
const CHANGE_COMMENT_FIELD = "product/CHANGE_COMMENT_FIELD";
const CHANGE_AUCTION_FIELD = "product/CHANGE_AUCTION_FIELD";
const TOGGLE_COMMENT_MODE = "product/TOGGLE_COMMENT_MODE";
const REFRESH_AUCTION = "product/REFRESH_AUCTION";






const refreshAuction = createAction(
  REFRESH_AUCTION,
  ({id, price})=>({id, price})
)();


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

export const enterAuctions = createAsyncAction(
  ENTER_AUCTION,
  ENTER_AUCTION_SUCCESS,
  ENTER_AUCTION_FAILURE,
)<any, any, AxiosError>();


const [
  CANCEL_AUCTION,
  CANCEL_AUCTION_SUCCESS,
  CANCEL_AUCTION_FAILURE,
] = createActionTypes("product/CANCEL_AUCTION");

export const cancelAuctions = createAsyncAction(
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



export const initialize = createAction(
  INITIALIZE,
  ()=>{}  
)();

export const changeCommentField = createAction(
  CHANGE_COMMENT_FIELD,
  ({ key, value }: { key: string; value: any }) => ({
    key,
    value,
  })
)();

export const changeAuctionField = createAction(
  CHANGE_AUCTION_FIELD,
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



const enterAuctionSaga = createAsyncSaga(ENTER_AUCTION, auctionsAPI.enterAuctions);
const cancelAuctionSaga = createAsyncSaga(CANCEL_AUCTION, auctionsAPI.cancelProduct);

const likeSaga = createAsyncSaga(LIKE, likeAPI.like);
const unLikeSaga = createAsyncSaga(UN_LIKE, likeAPI.unlike);






function createDataSocket() {
  return new Promise((resolve, reject) => {
    const client = new Client({
      brokerURL: 'ws://localhost/ws',
      // connectHeaders: {
      //   login: 'user',
      //   passcode: 'password',
      // },
      debug: function (str) {
        console.log(str);
      },
      reconnectDelay: 5000,
      heartbeatIncoming: 4000,
      heartbeatOutgoing: 4000,
    });
    
    client.onConnect = function (frame) {
      // Do something, all subscribes must be done is this callback
      // This is needed because this will be executed after a (re)connect
      console.log('connect1', frame);
      resolve(client);
    };
    
    client.onStompError = function (frame) {
      // Will be invoked in case of error encountered at Broker
      // Bad login/passcode typically will cause an error
      // Complaint brokers will set `message` header with a brief message. Body may contain details.
      // Compliant brokers will terminate the connection after any error
      console.log('Broker reported error: ' + frame.headers['message']);
      console.log('Additional details: ' + frame.body);
      resolve(client);
    };
    client.activate();

  });
}



function* listenAuction() { 
  let socket;
  let socketChannel;
  
  try{
    // 소캣 생성
    socket        = yield call(createDataSocket);
    // 채널 생성
    socketChannel = yield call(createSocketChannel, '/topic/auctions', socket);
    
    // yield fork(writeSocket, socket); 


    while(true) {
      // 새로운 채팅
      const result = yield take(socketChannel);
      const auction = JSON.parse(result.body);
      console.log('receviced from server : ',auction);
      yield put(refreshAuction({
        id: auction.productsId,
        price: auction.price,
      }));
    }

  } catch (error) {
    console.log(error);
  } finally {
    if (yield cancelled()) {
      // close the channel
      socketChannel.close();

      // // close the WebSocket connection
      socket.deactivate();
    } else {
      // yield dispatch(LiveDataActions.connectionError('WebSocket disconnected'));
    }
  }
}


function* listenState() { 
  let socket;
  let socketChannel;
  
  try{
    // 소캣 생성
    socket        = yield call(createDataSocket);
    // 채널 생성
    socketChannel = yield call(createSocketChannel, '/topic/state', socket);
    
    // yield fork(writeSocket, socket); 


    while(true) {
      // 새로운 채팅
      const result = yield take(socketChannel);
      const auction = JSON.parse(result.body);
      console.log('receviced from server : ',auction);
      yield put(refreshAuction({
        id: auction.productsId,
        price: auction.price,
      }));
    }

  } catch (error) {
    console.log(error);
  } finally {
    if (yield cancelled()) {
      // close the channel
      socketChannel.close();

      // // close the WebSocket connection
      socket.deactivate();
    } else {
      // yield dispatch(LiveDataActions.connectionError('WebSocket disconnected'));
    }
  }
}



export function* productSaga() {
  yield fork(listenAuction);
  yield fork(listenState);
  


  yield takeLatest(LOAD_PRODUCT, loadProductSaga);
  yield takeLatest(LOAD_COMMENTS, loadCommentsSaga);

  yield takeLatest(REGISTER_COMMENT, registerCommentSaga);
  yield takeLatest(UPDATE_COMMENT, updateCommentSaga);
  yield takeLatest(DELETE_COMMENT, deleteCommentSaga);

  yield takeLatest(ENTER_AUCTION, enterAuctionSaga);
  yield takeLatest(CANCEL_AUCTION, cancelAuctionSaga);

  yield takeLatest(LIKE, likeSaga);
  yield takeLatest(UN_LIKE, unLikeSaga);
}


interface Products {
  id: number;
  title: string;
  content: string;
  startPrice: number;
  startDateTime: string;
  endDateTime: string;

  auctions: any;
  images: any;
  numOfLike: number;
  isLike: boolean;

  numOfAuctions: number;
  numOfParticipant: number;
  price: number;

  categories: any;

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
  product: undefined,
  dashboard: {
  },
  comments: undefined,
  auctions: [],
  form: {
    auctions:{
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
  [INITIALIZE]: (state) => ({
    ...state,
    ...initialState,
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
  [CHANGE_AUCTION_FIELD]: (state, { payload : {key, value}}) => (
    produce(state, (draft) => {
      draft.form.auctions[key] = value
    })
  ),
  // [REFRESH_AUCTION]: (state, { payload: { id, price}})=> {
  //   const idx = state.products?.findIndex((product: any) => product.id === id);
  //   if (idx === -1) {
  //     return state;
  //   }
  //   // 결과값 
  //   return produce(state, (draft) => {
  //     draft.products[idx]!.price = price;
  //     draft.product.price = price;
  //     draft.product.numOfAuctions += 1;
  //   });
  // },
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
  [LIKE]: (state) => 
    produce(state, (draft) => {
      draft.product.numOfLike += 1;
      draft.product.isLike = true;
    }),
  [UN_LIKE]: (state) => 
    produce(state, (draft) => {
      draft.product.numOfLike -= 1;
      draft.product.isLike = false;
    }),
  // 응답에 대한 비교코드 필요
  [LIKE_SUCCESS]: (state, { payload: numOfLike}) => 
    state,
  [UN_LIKE_SUCCESS]: (state, { payload: numOfLike}) => 
    state,
  [ENTER_AUCTION_SUCCESS]: (state, { payload }) => 
    state,
  [CANCEL_AUCTION_SUCCESS]: (state, { payload}) => 
    state,
});

export default product;

