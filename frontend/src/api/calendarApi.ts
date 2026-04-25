import API from "./api";

export interface AvailabilityRequest {
  propertyId: number;
  fromDate: string;
  toDate: string;
}

// ✅ CHECK AVAILABILITY
export const checkAvailability = async (data: AvailabilityRequest) => {
  const res = await API.post("/calendar/check", data);
  return res.data; // true / false
};