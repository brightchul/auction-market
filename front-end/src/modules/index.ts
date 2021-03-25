import { combineReducers  } from 'redux';
import { all } from 'redux-saga/effects';

import loading from './loading';

import main, { mainSaga } from './main';
import register, { registerSaga } from './register';
import product, { productSaga } from './product';


const rootReducer = combineReducers({
  loading,
  // base,
  main,
  register,
  product,
});

export function* rootSaga() {
  yield all([
    mainSaga(),
    registerSaga(),
    productSaga(),
  ]);
};

export default rootReducer;

export type RootState = ReturnType<typeof rootReducer>;
