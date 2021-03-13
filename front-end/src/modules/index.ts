import { combineReducers  } from 'redux';
import { all } from 'redux-saga/effects';

import loading from './loading';

import main, { mainSaga } from './main';

const rootReducer = combineReducers({
  loading,
  // base,
  main,
});

export function* rootSaga() {
  yield all([
    mainSaga(),
  ]);
};

export default rootReducer;

export type RootState = ReturnType<typeof rootReducer>;
