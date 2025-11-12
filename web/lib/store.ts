import { configureStore } from '@reduxjs/toolkit';
import devicesReducer from './devicesSlice';
import anomalyReducer from './anomalySlice';
import { TypedUseSelectorHook, useDispatch, useSelector } from 'react-redux';

// Root Redux store with two slices: devices and anomalies.
export const store = configureStore({
  reducer: {
    devices: devicesReducer,
    anomalies: anomalyReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;

export const useAppDispatch: () => AppDispatch = useDispatch;
export const useAppSelector: TypedUseSelectorHook<RootState> = useSelector;