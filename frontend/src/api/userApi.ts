import API from "./api";

export interface LoginRequest {
  email: string;
  password: string;
}

export interface RegisterRequest {
  name: string;
  email: string;
  password: string;
}

export interface AuthResponse {
  token: string;
}

export interface User {
  id: number;
  name: string;
  email: string;
  role: string;
  verified: boolean;
}

// 🔐 LOGIN
export const loginUser = async (data: LoginRequest) => {
  const res = await API.post<AuthResponse>("/users/login", data);
  return res.data;
};

// 📝 REGISTER
export const registerUser = async (data: RegisterRequest) => {
  const res = await API.post("/users/register", data);
  return res.data;
};

// 👤 PROFILE
export const getProfile = async (): Promise<User> => {
  const res = await API.get("/users/profile");
  return res.data;
};