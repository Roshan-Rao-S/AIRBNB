import API from "./api";

export interface Review {
  id: number;
  propertyId: number;
  rating: number;
  comment: string;
}

// ✅ Get reviews
export const getReviews = async (propertyId: string) => {
  const res = await API.get(`/reviews/${propertyId}`);
  return res.data;
};

// ✅ Add review (protected)
export const addReview = async (data: any) => {
  const res = await API.post("/reviews", data);
  return res.data;
};