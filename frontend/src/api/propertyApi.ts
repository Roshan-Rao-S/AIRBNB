import API from "./api";

export interface Property {
  id: number;
  title: string;
  location: string;
  price: number;
  imageUrl: string;
  rating: number;
  reviewCount: number;
}

// ✅ GET ALL
export const getAllProperties = async (): Promise<Property[]> => {
  const res = await API.get("/properties");
  return res.data;
};

// ✅ GET BY ID
export const getPropertyById = async (id: number): Promise<Property> => {
  const res = await API.get(`/properties/${id}`);
  return res.data;
};

// 🔐 ADD PROPERTY
export const addProperty = async (data: any) => {
  const res = await API.post("/properties", data);
  return res.data;
};

export const getHostProperties = async () => {
  const res = await API.get("/properties/host");
  return res.data;
};

export const deleteProperty = async (id: number) => {
  const res = await API.delete(`/properties/${id}`);
  return res.data;
};