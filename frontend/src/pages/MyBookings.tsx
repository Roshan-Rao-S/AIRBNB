import { useEffect, useState } from "react";
import { getMyBookings, cancelBooking } from "../api/bookingApi";
import { getPropertyById, Property } from "../api/propertyApi";
import ToastMessage from "../components/ToastMessage";

interface Booking {
  id: number;
  propertyId: number;
  checkIn: string;
  checkOut: string;
  guests: number;
  status: string;

  property?: Property; // ✅ NEW
}

const MyBookings = () => {
  const [bookings, setBookings] = useState<Booking[]>([]);
  const [loading, setLoading] = useState(true);

  const [toast, setToast] = useState({
    show: false,
    message: "",
    type: "success" as "success" | "error",
  });

  // 🔥 LOAD BOOKINGS + PROPERTY DATA
  const loadBookings = async () => {
    try {
      const data = await getMyBookings();

      const enriched = await Promise.all(
        data.map(async (b: Booking) => {
          try {
            const property = await getPropertyById(b.propertyId);
            return { ...b, property };
          } catch {
            return { ...b, property: undefined };
          }
        })
      );

      setBookings(enriched);

    } catch {
      setToast({
        show: true,
        message: "Failed to load bookings ❌",
        type: "error",
      });
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadBookings();
  }, []);

  const handleCancel = async (id: number) => {
    try {
      await cancelBooking(id);

      setToast({
        show: true,
        message: "Booking cancelled ✅",
        type: "success",
      });

      loadBookings();
    } catch {
      setToast({
        show: true,
        message: "Cancel failed ❌",
        type: "error",
      });
    }
  };

  if (loading) {
    return <h5 className="text-center mt-5">Loading...</h5>;
  }

  if (bookings.length === 0) {
    return (
      <h5 className="text-center mt-5 text-muted">
        No bookings yet
      </h5>
    );
  }

  return (
    <div className="container mt-4">
      <h3 className="mb-4">My Bookings</h3>

      <div className="row">
        {bookings.map((b) => (
          <div key={b.id} className="col-md-4 mb-4">
            <div className="card shadow-sm p-3">

              {/* ✅ PROPERTY UI */}
              {b.property && (
                <>
                  <img
                    src={
                      b.property.imageUrl
                        ? `http://localhost:8080/${b.property.imageUrl}`
                        : "https://via.placeholder.com/300"
                    }
                    alt="property"
                    className="card-img-top mb-2"
                    style={{
                      height: "180px",
                      objectFit: "cover",
                      borderRadius: "8px",
                    }}
                  />

                  <h6 className="fw-bold">
                    {b.property.title}
                  </h6>

                  <p className="text-muted mb-1">
                    {b.property.location}
                  </p>

                  <p className="mb-2">
                    ₹{b.property.price} / night
                  </p>
                </>
              )}

              {/* BOOKING INFO */}
              <p className="mb-1">
                <b>Check-in:</b> {b.checkIn}
              </p>

              <p className="mb-1">
                <b>Check-out:</b> {b.checkOut}
              </p>

              <p className="mb-1">
                <b>Guests:</b> {b.guests}
              </p>

              <p className="mb-2">
                <b>Status:</b>{" "}
                <span
                  className={
                    b.status === "CONFIRMED"
                      ? "text-success"
                      : "text-danger"
                  }
                >
                  {b.status}
                </span>
              </p>

              {b.status === "CONFIRMED" && (
                <button
                  className="btn btn-outline-danger btn-sm"
                  onClick={() => handleCancel(b.id)}
                >
                  Cancel Booking
                </button>
              )}

            </div>
          </div>
        ))}
      </div>

      {/* 🔥 TOAST */}
      <ToastMessage
        show={toast.show}
        message={toast.message}
        type={toast.type}
        onClose={() => setToast({ ...toast, show: false })}
      />
    </div>
  );
};

export default MyBookings;