import { useState } from "react";
import { addReview } from "../api/reviewApi";

const ReviewForm = ({ propertyId, onSuccess }: any) => {
  const [rating, setRating] = useState(5);
  const [comment, setComment] = useState("");

  const handleSubmit = async () => {
    try {
      await addReview({
        propertyId,
        rating,
        comment,
      });

      alert("Review added");
      setComment("");
      onSuccess(); // reload reviews
    } catch {
      alert("Error adding review");
    }
  };

  return (
    <div className="mt-4">
      <h5>Add Review</h5>

      <input
        type="number"
        min="1"
        max="5"
        value={rating}
        onChange={(e) => setRating(Number(e.target.value))}
        className="form-control mb-2"
      />

      <textarea
        placeholder="Write your review"
        className="form-control mb-2"
        value={comment}
        onChange={(e) => setComment(e.target.value)}
      />

      <button className="btn btn-danger" onClick={handleSubmit}>
        Submit
      </button>
    </div>
  );
};

export default ReviewForm;