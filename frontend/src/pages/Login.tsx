import React, { useState } from "react";
import { loginUser } from "../api/userApi";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
const Login: React.FC = () => {
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");

  const navigate = useNavigate();
const { login } = useAuth();

  const handleLogin = async () => {
  try {
    const res = await loginUser({ email, password });

    await login(res.token); // ✅ THIS IS THE FIX

    alert("Login successful");

    navigate("/");

  } catch (error) {
    console.error(error);
    alert("Invalid email or password");
  }
};

  return (
    <div
      className="d-flex justify-content-center align-items-center"
      style={{ height: "80vh" }}
    >
      <div
        className="card shadow p-4"
        style={{ width: "400px", borderRadius: "12px" }}
      >
        <h3 className="text-center mb-4 fw-bold">Log in</h3>

        {/* Email */}
        <div className="mb-3">
          <input
            type="email"
            className="form-control"
            placeholder="Email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>

        {/* Password */}
        <div className="mb-3">
          <input
            type="password"
            className="form-control"
            placeholder="Password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>

        {/* Button */}
        <button
          className="btn btn-danger w-100 fw-bold"
          onClick={handleLogin}
        >
          Continue
        </button>

        {/* Divider */}
        <div className="text-center my-3 text-muted">or</div>

        {/* Register link */}
        <p className="text-center mb-0">
          Don’t have an account?{" "}
          <span
            className="text-danger fw-bold"
            style={{ cursor: "pointer" }}
            onClick={() => navigate("/register")}
          >
            Sign up
          </span>
        </p>
      </div>
    </div>
  );
};

export default Login;