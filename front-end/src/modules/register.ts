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
import * as followingAPI from "../lib/api/products";
import { AxiosError } from "axios";

const CHANGE_FIELD = "register/CHANGE_FIELD";
const INITIALIZE_FORM = "register/INITIALIZE_FORM";

export function* registerSaga() {}

interface RegisterState {
  form: {
    title: string;
    content: string;
    startPrice: number;
  };
}

const initialState: RegisterState = {
  form: {
    title: "",
    content: "",
    startPrice: 0,
  },
};

const register = createReducer<RegisterState, any>(initialState, {
  [INITIALIZE_FORM]: (state) => ({
    ...state,
  }),
  [CHANGE_FIELD]: (state) => ({
    ...state,
  }),
});

export default register;
