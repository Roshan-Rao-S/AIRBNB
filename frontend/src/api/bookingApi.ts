import API from "./api";

export interface BookingRequest {
  propertyId: number;
  checkIn: string;
  checkOut: string;
  guests: number;
}

// ✅ CREATE
export const createBooking = async (data: BookingRequest) => {
  const res = await API.post("/bookings", data);
  return res.data;
};

// ✅ GET USER BOOKINGS
export const getMyBookings = async () => {
  const res = await API.get("/bookings");
  return res.data;
};

// ✅ CANCEL
export const cancelBooking = async (id: number) => {
  const res = await API.put(`/bookings/${id}/cancel`);
  return res.data;
};