/**
 * 상품 등록
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
import * as productsAPI from "../lib/api/products";
import { AxiosError } from "axios";

const CHANGE_FIELD = "register/CHANGE_FIELD";
const INITIALIZE_FORM = "register/INITIALIZE_FORM";
const SET_IMAGES = "register/SET_IMAGES";

export const changeField = createAction(
  CHANGE_FIELD,
  ({ key, value }: { key: string; value: any }) => ({
    key,
    value,
  })
)();
export const setImages = createAction(
  SET_IMAGES,
  (images) => (images)
)();


const [
  REGISTER_PRODUCT,
  REGISTER_PRODUCT_SUCCESS,
  REGISTER_PRODUCT_FAILURE,
] = createActionTypes("register/REGISTER_PRODUCT");

export const registerProduct = createAsyncAction(
  REGISTER_PRODUCT,
  REGISTER_PRODUCT_SUCCESS,
  REGISTER_PRODUCT_FAILURE,
)<any, any, AxiosError>();

const [
  LOAD_PRODUCT,
  LOAD_PRODUCT_SUCCESS,
  LOAD_PRODUCT_FAILURE,
] =  createActionTypes("register/LOAD_PRODUCT");

export const loadProduct = createAsyncAction(
  LOAD_PRODUCT,
  LOAD_PRODUCT_SUCCESS,
  LOAD_PRODUCT_FAILURE,
)<any, any, AxiosError>();


const loadProductSaga = createAsyncSaga(LOAD_PRODUCT, productsAPI.getProduct);
const registerProductSaga = createAsyncSaga(REGISTER_PRODUCT, productsAPI.saveProduct);




export function* registerSaga() {
  yield takeLatest(LOAD_PRODUCT, loadProductSaga);
  yield takeLatest(REGISTER_PRODUCT, registerProductSaga);

}

interface RegisterState {
  form: {
    [key:string] : any;
    categories: any;
    images: any;
    title: string;
    content: string;
    startPrice: number;
    startDateTime: string;
    endDateTime: string;
  };
  error: any;
}

const initialState: RegisterState = {
  form: {
    categories: null,
    images: [],
    title: "",
    content: "",
    startPrice: 0,
    startDateTime: "",
    endDateTime: "",
  },
  error: "",
};

type ChangeFieldAction = ActionType<typeof changeField>;

const register = createReducer<RegisterState, any>(initialState, {
  [INITIALIZE_FORM]: (state) => ({
    ...state,
  }),
  [CHANGE_FIELD]: (
    state, 
    { payload: { key, value } } : ChangeFieldAction
  ) => 
    produce(state, (draft) => {
      draft.form[key] = value;
    }),
  [SET_IMAGES]: (state, { payload:images}) => ({
    ...state,
    form : {
      ...state.form,
      images
    }
  }),
  [LOAD_PRODUCT_SUCCESS]: (state, {payload: product}) => ({
    ...state,
    form: product
  }),
  
  
});

export default register;
