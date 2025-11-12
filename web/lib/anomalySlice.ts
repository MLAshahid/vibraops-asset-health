import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

// Fetch anomalies
export const fetchAnomalies = createAsyncThunk('anomalies/fetch', async () => {
  const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/api/anomalies`, {
    headers: {
      Authorization: 'Basic ' + btoa('admin:admin123'),
    },
  });
  return (await res.json()) as any[];
});

interface AnomalyState {
  items: any[];
  live: any[];
  loading: boolean;
}

const initialState: AnomalyState = {
  items: [],
  live: [],
  loading: false,
};

const anomalySlice = createSlice({
  name: 'anomalies',
  initialState,
  reducers: {
    pushLive: (state, action) => {
      state.live.unshift(action.payload);
    },
  },
  extraReducers: builder => {
    builder
      .addCase(fetchAnomalies.pending, state => {
        state.loading = true;
      })
      .addCase(fetchAnomalies.fulfilled, (state, action) => {
        state.items = action.payload;
        state.loading = false;
      });
  },
});

export const { pushLive } = anomalySlice.actions;
export default anomalySlice.reducer;