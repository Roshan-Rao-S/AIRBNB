import API from "./api";
import { Booking } from "../types/models";

export interface BookingRequest {
  propertyId: number;
  checkIn: string;
  checkOut: string;
  guests: number;
}

// ✅ CREATE
export const createBooking = async (data: BookingRequest): Promise<Booking> => {
  const res = await API.post<Booking>("/bookings", data);
  return res.data;
};

// ✅ GET USER BOOKINGS
export const getMyBookings = async (): Promise<Booking[]> => {
  const res = await API.get<Booking[]>("/bookings");
  return res.data;
};

// ✅ CANCEL
export const cancelBooking = async (id: number) => {
  const res = await API.put(`/bookings/${id}/cancel`);
  return res.data;
};
