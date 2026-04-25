import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { getPropertyById } from "../api/propertyApi";
import { getReviews } from "../api/reviewApi";
import ReviewForm from "../components/ReviewForm";
import BookingForm from "../components/BookingForm";
interface Review {
  rating: number;
  comment: string;
}

const PropertyDetails = () => {
  const { id } = useParams<{ id: string }>();

  const [property, setProperty] = useState<any>(null);
  const [reviews, setReviews] = useState<Review[]>([]);

  // ✅ Load reviews
  const loadReviews = () => {
    if (id) {
      getReviews(id).then(setReviews);
    }
  };

  // ✅ Load property + reviews
  useEffect(() => {
  if (!id) return;

  const fetchData = async () => {
    try {
      const propertyData = await getPropertyById(Number(id));
      setProperty(propertyData);

      const reviewData = await getReviews(id);
      setReviews(reviewData);
    } catch (err) {
      console.error("Error loading property/reviews", err);
    }
  };

  fetchData();
}, [id]);

  if (!property) return <h4 className="text-center mt-5">Loading...</h4>;

  return (
    <div className="container mt-4">
      <h2>{property.title}</h2>

      <img
        src={`${process.env.REACT_APP_API_URL}/${property.imageUrl}`}
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
      <ReviewForm propertyId={id} onSuccess={loadReviews} />
      <BookingForm propertyId={Number(id)} />
    </div>
  );
};

export default PropertyDetails;