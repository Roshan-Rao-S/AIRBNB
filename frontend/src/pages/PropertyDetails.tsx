import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getPropertyById } from "../api/propertyApi";
import { getReviews } from "../api/reviewApi";
import { API_BASE_URL } from "../api/api";
import ReviewForm from "../components/ReviewForm";
import BookingForm from "../components/BookingForm";
import { Property, Review } from "../types/models";

const PropertyDetails = () => {
  const { id } = useParams<{ id: string }>();

  const [property, setProperty] = useState<Property | null>(null);
  const [reviews, setReviews] = useState<Review[]>([]);
  const propertyId = Number(id);

  // ✅ Load reviews
  const loadReviews = () => {
    if (Number.isFinite(propertyId)) {
      getReviews(propertyId).then(setReviews);
    }
  };

  // ✅ Load property + reviews
  useEffect(() => {
  if (!id) return;

  const fetchData = async () => {
    try {
      const propertyData = await getPropertyById(Number(id));
      setProperty(propertyData);

      const reviewData = await getReviews(Number(id));
      setReviews(reviewData);
    } catch (err) {
      console.error("Error loading property/reviews", err);
    }
  };

  fetchData();
}, [id]);

  if (!Number.isFinite(propertyId)) {
    return <h4 className="text-center mt-5">Invalid property</h4>;
  }

  if (!property) return <h4 className="text-center mt-5">Loading...</h4>;

  return (
    <div className="container mt-4">
      <h2>{property.title}</h2>

      <img
        src={`${API_BASE_URL}/${property.imageUrl}`}
        style={{ width: "100%", height: "400px", objectFit: "cover" }}
        alt={property.title}
      />

      <h5 className="mt-3">{property.location}</h5>
      <h4>₹{property.price} / night</h4>

      <p className="mt-3">Beautiful place to stay.</p>

      {/* ⭐ Reviews */}
      <h4 className="mt-4">Reviews</h4>

      {reviews.length === 0 && <p>No reviews yet</p>}

      {reviews.map((r, i) => (
        <div key={i} className="border p-2 mb-2 rounded">
          ⭐ {r.rating}
          <p>{r.comment}</p>
        </div>
      ))}

      {/* 📝 Add Review */}
      <ReviewForm propertyId={propertyId} onSuccess={loadReviews} />
      <BookingForm propertyId={propertyId} />
    </div>
  );
};

export default PropertyDetails;
