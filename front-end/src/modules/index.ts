import { combineReducers  } from 'redux';
import { all } from 'redux-saga/effects';

import loading from './loading';

import main, { mainSaga } from './main';
import register, { registerSaga } from './register';


const rootReducer = combineReducers({
  loading,
  // base,
  main,
  register
});

export function* rootSaga() {
  yield all([
    mainSaga(),
    registerSaga(),
  ]);
};

export default rootReducer;

export type RootState = ReturnType<typeof rootReducer>;
