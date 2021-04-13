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
import * as authAPI from '../lib/api/auth';
import { AxiosError } from 'axios';


const SET_LOGGED_INFO = "auth/SET_LOGGED_INFO";
export const setLoggedInfo = createAction(SET_LOGGED_INFO, (loggedInfo)=>(loggedInfo))();

const LOGOUT = "auth/LOGOUT";
export const logout = createAction(LOGOUT)();


const [
  LOGIN_KAKAO,
  LOGIN_KAKAO_SUCCESS,
  LOGIN_KAKAO_FAILURE,
] = createActionTypes("auth/LOGIN_KAKAO");

export const loginKakao = createAsyncAction(
  LOGIN_KAKAO,
  LOGIN_KAKAO_SUCCESS,
  LOGIN_KAKAO_FAILURE,
)<any, any, AxiosError>();




const loginKakaoSaga = createAsyncSaga(LOGIN_KAKAO, authAPI.loginKakao);


export function* authSaga() {
  yield takeLatest(LOGIN_KAKAO, loginKakaoSaga);
}




interface AuthState {
  loggedInfo: any;
}

const initialState: AuthState = {
  loggedInfo: null
};


const auth = createReducer<AuthState, any>(initialState, {
  [SET_LOGGED_INFO]: (state, { payload: loggedInfo }) => ({
    ...state,
    loggedInfo
  }),
  [LOGIN_KAKAO_SUCCESS]: (state, payload) => {
    return {
      loggedInfo : payload.meta.data.response
    }
  },
  [LOGOUT] : (state) => ({
    ...state,
    loggedInfo: null
  })
  
});


export default auth;

