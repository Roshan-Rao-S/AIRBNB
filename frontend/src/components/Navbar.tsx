import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

const Navbar = () => {
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate("/login");
  };

  return (
    <nav className="navbar navbar-expand-lg bg-white shadow-sm px-4 sticky-top">

      {/* Logo */}
      <Link className="navbar-brand fw-bold text-danger" to="/">
        Airbnb
      </Link>

      {/* 🍔 Burger */}
      <button
        className="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNav"
      >
        <span className="navbar-toggler-icon"></span>
      </button>

      {/* Collapsible */}
      <div className="collapse navbar-collapse" id="navbarNav">
        <div className="ms-auto d-flex flex-column flex-lg-row align-items-start align-items-lg-center gap-3 mt-3 mt-lg-0">

          {/* ❌ NOT LOGGED IN */}
          {!user ? (
            <>
              <Link className="nav-link" to="/">Home</Link>
              <Link className="nav-link" to="/login">Login</Link>
              <Link className="btn btn-danger text-white px-3" to="/register">
                Sign Up
              </Link>
            </>
          ) : (
            <>
              {/* ✅ LOGGED IN */}
              <span className="fw-bold">Hi, {user.name}</span>

              <Link className="nav-link" to="/bookings">
                My Bookings
              </Link>

              {/* 🔥 THIS IS THE FIX */}
              {user.role === "HOST" && (
                <Link className="nav-link" to="/host">
                  Host Dashboard
                </Link>
              )}

              <button
                className="btn btn-outline-danger"
                onClick={handleLogout}
              >
                Logout
              </button>
            </>
          )}

        </div>
      </div>
    </nav>
  );
};

export default Navbar;