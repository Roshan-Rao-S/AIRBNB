import API from "./api";
import { Review } from "../types/models";

// ✅ Get reviews
export const getReviews = async (propertyId: number): Promise<Review[]> => {
  const res = await API.get<Review[]>(`/reviews/${propertyId}`);
  return res.data;
};

// ✅ Add review (protected)
export const addReview = async (data: Review): Promise<Review> => {
  const res = await API.post<Review>("/reviews", data);
  return res.data;
};
