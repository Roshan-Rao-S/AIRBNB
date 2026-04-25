import { useEffect, useState } from "react";
import { createBooking } from "../api/bookingApi";
import { checkAvailability } from "../api/calendarApi";
import API from "../api/api";
import ToastMessage from "./ToastMessage";

interface Availability {
  blockedFrom: string;
  blockedTo: string;
}

const BookingForm = ({ propertyId }: { propertyId: number }) => {
  const [checkIn, setCheckIn] = useState("");
  const [checkOut, setCheckOut] = useState("");
  const [guests, setGuests] = useState(1);

  const [available, setAvailable] = useState<boolean | null>(null);
  const [checking, setChecking] = useState(false);

  const [blockedDates, setBlockedDates] = useState<Availability[]>([]);

  const [toast, setToast] = useState({
    show: false,
    message: "",
    type: "success" as "success" | "error",
  });

  // 🔥 TODAY DATE (disable past)
  const today = new Date().toISOString().split("T")[0];

  // 🔥 LOAD BLOCKED DATES
  useEffect(() => {
    API.get(`/calendar/${propertyId}`)
      .then((res) => setBlockedDates(res.data))
      .catch(() => console.log("No availability data"));
  }, [propertyId]);

  // 🔍 CHECK IF DATE RANGE OVERLAPS
  const isDateBlocked = (from: string, to: string) => {
    const start = new Date(from);
    const end = new Date(to);

    return blockedDates.some((b) => {
      const bStart = new Date(b.blockedFrom);
      const bEnd = new Date(b.blockedTo);

      return start <= bEnd && end >= bStart;
    });
  };

  // 🔍 CHECK AVAILABILITY
  const handleCheck = async () => {
    if (!checkIn || !checkOut) {
      setToast({
        show: true,
        message: "Select dates first ❌",
        type: "error",
      });
      return;
    }

    // ❌ CHECK-OUT BEFORE CHECK-IN
    if (checkOut < checkIn) {
      setToast({
        show: true,
        message: "Invalid date range ❌",
        type: "error",
      });
      return;
    }

    // ❌ BLOCKED RANGE CHECK
    if (isDateBlocked(checkIn, checkOut)) {
      setAvailable(false);
      setToast({
        show: true,
        message: "Dates already booked ❌",
        type: "error",
      });
      return;
    }

    setChecking(true);

    try {
      const result = await checkAvailability({
        propertyId,
        fromDate: checkIn,
        toDate: checkOut,
      });

      setAvailable(result);

      setToast({
        show: true,
        message: result
          ? "Property available ✅"
          : "Not available ❌",
        type: result ? "success" : "error",
      });

    } catch {
      setToast({
        show: true,
        message: "Check failed ❌",
        type: "error",
      });
    } finally {
      setChecking(false);
    }
  };

  // 🏠 BOOK
  const handleBooking = async () => {
    if (!available) {
      setToast({
        show: true,
        message: "Check availability first ❗",
        type: "error",
      });
      return;
    }

    try {
      await createBooking({
        propertyId,
        checkIn,
        checkOut,
        guests,
      });

      setToast({
        show: true,
        message: "Booking confirmed ✅",
        type: "success",
      });

    } catch {
      setToast({
        show: true,
        message: "Booking failed ❌",
        type: "error",
      });
    }
  };

  return (
    <div className="card mt-4 shadow-sm border-0">
      <div className="card-body">

        <h5 className="fw-bold mb-3">Book this property</h5>

        {/* DATE ROW */}
        <div className="row g-3 mb-3">

          <div className="col-md-6">
            <label className="form-label fw-semibold">Check-in</label>
            <input
              type="date"
              className="form-control"
              value={checkIn}
              min={today} // ❌ PAST DISABLED
              onChange={(e) => {
                setCheckIn(e.target.value);
                setCheckOut(""); // reset checkout
              }}
            />
          </div>

          <div className="col-md-6">
            <label className="form-label fw-semibold">Check-out</label>
            <input
              type="date"
              className="form-control"
              value={checkOut}
              min={checkIn || today} // ❌ BEFORE CHECK-IN DISABLED
              onChange={(e) => setCheckOut(e.target.value)}
            />
          </div>

        </div>

        {/* GUESTS */}
        <div className="mb-3">
          <label className="form-label fw-semibold">Guests</label>
          <input
            type="number"
            min="1"
            className="form-control"
            value={guests}
            onChange={(e) => setGuests(Number(e.target.value))}
          />
        </div>

        {/* CHECK BUTTON */}
        <button
          className="btn btn-outline-primary w-100 mb-3"
          onClick={handleCheck}
          disabled={checking}
        >
          {checking ? "Checking..." : "Check Availability"}
        </button>

        {/* STATUS */}
        {available !== null && (
          <div className="text-center mb-3">
            <span
              className={`fw-bold ${
                available ? "text-success" : "text-danger"
              }`}
            >
              {available ? "Available ✅" : "Not Available ❌"}
            </span>
          </div>
        )}

        {/* BOOK BUTTON */}
        <button
          className="btn btn-success w-100 fw-semibold"
          onClick={handleBooking}
        >
          Book Now
        </button>

      </div>

      {/* TOAST */}
      <ToastMessage
        show={toast.show}
        message={toast.message}
        type={toast.type}
        onClose={() => setToast({ ...toast, show: false })}
      />
    </div>
  );
};

export default BookingForm;