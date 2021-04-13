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

const INITIALIZE_PRODUCTS = "main/INITIALIZE_PRODUCTS";
const REFRESH_AUCTION = "main/REFRESH_AUCTION";

// 상품 목록
const [
  LOAD_PRODUCTS,
  LOAD_PRODUCTS_SUCCESS,
  LOAD_PRODUCTS_FAILURE,
] = createActionTypes("main/LOAD_PRODUCTS");

export const loadProducts = createAsyncAction(
  LOAD_PRODUCTS,
  LOAD_PRODUCTS_SUCCESS,
  LOAD_PRODUCTS_FAILURE
)<any, any, AxiosError>();

// 카테고리 목록
const [
  LOAD_CATEGORIES,
  LOAD_CATEGORIES_SUCCESS,
  LOAD_CATEGORIES_FAILURE,
] = createActionTypes("main/LOAD_CATEGORIES");

export const loadCategories = createAsyncAction(
  LOAD_CATEGORIES,
  LOAD_CATEGORIES_SUCCESS,
  LOAD_CATEGORIES_FAILURE
)<void, any, AxiosError>();

const [
  LIKE,
  LIKE_SUCCESS,
  LIKE_FAILURE,
] = createActionTypes("main/LIKE");

export const like = createAsyncAction(
  LIKE,
  LIKE_SUCCESS,
  LIKE_FAILURE,
)<any, any, AxiosError>();


const [
  UN_LIKE,
  UN_LIKE_SUCCESS,
  UN_LIKE_FAILURE,
] = createActionTypes("main/UN_LIKE");

export const unLike = createAsyncAction(
  UN_LIKE,
  UN_LIKE_SUCCESS,
  UN_LIKE_FAILURE,
)<any, any, AxiosError>();

export const initializeProducts = createAction(
  INITIALIZE_PRODUCTS,
  ()=>{}
)();




const loadProductsSaga = createAsyncSaga(LOAD_PRODUCTS, productsAPI.loadProducts);
const loadCategoriesSaga = createAsyncSaga(LOAD_CATEGORIES, categoriesAPI.loadCategories);

const likeSaga = createAsyncSaga(LIKE, likeAPI.like);
const unLikeSaga = createAsyncSaga(UN_LIKE, likeAPI.unlike);



export function* mainSaga() {
  // yield fork(listenData);
  
  yield takeLatest(LOAD_PRODUCTS, loadProductsSaga);
  yield takeLatest(LOAD_CATEGORIES, loadCategoriesSaga);
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
  categories: null,
  products: Products[] | null,
  pageable: any;
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
  categories: null,
  products: null,
  pageable: null,
  product: null,
  dashboard: {},
  comments: [],
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


const main = createReducer<CommentsState, any>(initialState, {
  [INITIALIZE_PRODUCTS]: (state) => ({
    ...state,
    products: null,
  }),
  [REFRESH_AUCTION]: (state, { payload: { id, price } }) => {
    const idx = state.products?.findIndex((product: any) => product.id === id);
    if (idx === -1) {
      return state;
    }
    // 결과값
    return produce(state, (draft) => {
      // draft.products[idx].price = price;
      // draft.product.price = price;
      // draft.product.numOfAuctions += 1;
    });
  },
  [LOAD_CATEGORIES_SUCCESS]: (state, { payload: categories }) => ({
    ...state,
    categories,
  }),
  [LOAD_PRODUCTS_SUCCESS]: (state, { payload }) => {
    
    console.log(payload);
    
    return {
      ...state,
      products: state.products
        ? state.products.concat(payload.content)
        : payload.content,
      pageable: {
        ...payload.pageable,
        total : Math.ceil(parseInt(payload.total) / parseInt(payload.pageable.size)),
      },
    }
  },
  [LIKE]: (state, { payload: id }) => {
    const idx = state.products
      ? state.products.findIndex((product) => product.id === id)
      : -1;
    return produce(state, (draft) => {
      if (draft.products) {
        draft.products[idx].numOfLike += 1;
        draft.products[idx].isLike = true;
      }
    });
  },
  [UN_LIKE]: (state, { payload: id }) => {
    const idx = state.products
      ? state.products.findIndex((product) => product.id === id)
      : -1;
    return produce(state, (draft) => {
      if (draft.products) {
        draft.products[idx].numOfLike -= 1;
        draft.products[idx].isLike = false;
      }
    });
  },
  // 응답에 대한 비교코드 필요
  [LIKE_SUCCESS]: (state, { payload: numOfLike }) => state,
  [UN_LIKE_SUCCESS]: (state, { payload: numOfLike }) => state,
});

export default main;

