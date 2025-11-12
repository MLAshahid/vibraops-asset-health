import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';

// Fetch all devices
export const fetchDevices = createAsyncThunk('devices/fetch', async () => {
  const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/api/devices`, {
    headers: {
      Authorization: 'Basic ' + btoa('admin:admin123'),
    },
  });
  return (await res.json()) as any[];
});

// Create a device
export const createDevice = createAsyncThunk('devices/create', async (device: any) => {
  const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/api/devices`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      Authorization: 'Basic ' + btoa('admin:admin123'),
    },
    body: JSON.stringify(device),
  });
  return await res.json();
});

interface DevicesState {
  items: any[];
  loading: boolean;
}

const initialState: DevicesState = {
  items: [],
  loading: false,
};

const slice = createSlice({
  name: 'devices',
  initialState,
  reducers: {},
  extraReducers: builder => {
    builder
      .addCase(fetchDevices.pending, state => {
        state.loading = true;
      })
      .addCase(fetchDevices.fulfilled, (state, action) => {
        state.items = action.payload;
        state.loading = false;
      })
      .addCase(createDevice.fulfilled, (state, action) => {
        state.items.push(action.payload);
      });
  },
});

export default slice.reducer;