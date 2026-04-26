import API from "./api";
import { AddPropertyRequest, Property } from "../types/models";

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
export const addProperty = async (data: AddPropertyRequest): Promise<Property> => {
  const res = await API.post<Property>("/properties", data);
  return res.data;
};

export const getHostProperties = async (): Promise<Property[]> => {
  const res = await API.get<Property[]>("/properties/host");
  return res.data;
};

export const deleteProperty = async (id: number) => {
  const res = await API.delete(`/properties/${id}`);
  return res.data;
};
