import { combineReducers  } from 'redux';
import { all } from 'redux-saga/effects';

import loading from './loading';
import auth, { authSaga} from './auth';
import main, { mainSaga} from './main';
import register, { registerSaga } from './register';
import product, { productSaga } from './product';


const rootReducer = combineReducers({
  loading,
  auth,
  // base,
  main,
  register,
  product,
});

export function* rootSaga() {
  yield all([
    authSaga(),
    mainSaga(),
    registerSaga(),
    productSaga(),
  ]);
};

export default rootReducer;

export type RootState = ReturnType<typeof rootReducer>;
