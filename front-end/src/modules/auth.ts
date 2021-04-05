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
  result: any;
}

const initialState: AuthState = {
  result: null
};


const auth = createReducer<AuthState, any>(initialState, {
  [LOGIN_KAKAO_SUCCESS]: (state, payload) => {
    return {
      result : payload.meta.data
    }
  }
});


export default auth;

