/**
 * 메인 페이지
 */
import {
  createAction,
  createAsyncAction,
  ActionType,
  createReducer,
} from "typesafe-actions";
import produce from "immer";
import { takeLatest } from "redux-saga/effects";
import createAsyncSaga, { createActionTypes } from "../lib/createAsyncSaga";
import * as productsApi from "../lib/api/products";
import * as categoriesAPI from "../lib/api/categories";

import { AxiosError } from "axios";

const CHANGE_FIELD = "main/CHANGE_FIELD";
const INITIALIZE_FORM = "main/INITIALIZE_FORM";

// 상품 목록
const [
  LOAD_PRODUCTS,
  LOAD_PRODUCTS_SUCCESS,
  LOAD_PRODUCTS_FAILURE,
] = createActionTypes("main/LOAD_PRODUCCTS");

export const loadProducts = createAsyncAction(
  LOAD_PRODUCTS,
  LOAD_PRODUCTS_SUCCESS,
  LOAD_PRODUCTS_FAILURE
)<void, any, AxiosError>();

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

const loadProductsSaga = createAsyncSaga(LOAD_PRODUCTS, productsApi.loadProducts);
const loadCategoriesSaga = createAsyncSaga(LOAD_CATEGORIES, categoriesAPI.loadCategories);

export function* mainSaga() {
  yield takeLatest(LOAD_PRODUCTS, loadProductsSaga);
  yield takeLatest(LOAD_CATEGORIES, loadCategoriesSaga);
}

interface MainState {
  categories: any;
  products: any;
  error: any;
}

const initialState: MainState = {
  categories: [],
  products: [],
  error: null,
};

const main = createReducer<MainState, any>(initialState, {
  [INITIALIZE_FORM]: (state) => ({
    ...state,
  }),
  [CHANGE_FIELD]: (state) => ({
    ...state,
  }),
  [LOAD_CATEGORIES_SUCCESS]: (state, { payload: categories }) => ({
    ...state,
    categories
  }),
  [LOAD_PRODUCTS_SUCCESS]: (state, { payload: products }) => ({
    ...state,
    products
  }),
  [LOAD_PRODUCTS_FAILURE]: (state, { payload: products }) => ({
    ...state,
  }),
});

export default main;
