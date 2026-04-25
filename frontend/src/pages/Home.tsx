import { useEffect, useState } from "react";
import { getAllProperties } from "../api/propertyApi";
import SearchBar from "../components/SearchBar";
import { useNavigate } from "react-router-dom";

interface Property {
  id: number;
  title: string;
  location: string;
  price: number;
  imageUrl: string;
  rating: number;
  reviewCount: number;
}

const Home = () => {
  const [properties, setProperties] = useState<Property[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string>("");

  const navigate = useNavigate();

  useEffect(() => {
    getAllProperties()
      .then((data) => {
        setProperties(data);
        setLoading(false);
      })
      .catch((err) => {
        console.error(err);
        setError("Failed to load properties");
        setLoading(false);
      });
  }, []);

  if (loading) return <h5 className="text-center mt-5">Loading...</h5>;
  if (error) return <h5 className="text-center text-danger mt-5">{error}</h5>;

  return (
    <div className="container mt-3">
      <SearchBar />

      <div className="mt-5">
        <h4 className="mb-4">Popular homes</h4>

        <div className="row">
          {properties.map((p) => (
            <div
              className="col-md-3 mb-4"
              key={p.id}
              style={{ cursor: "pointer" }}
              onClick={() => navigate(`/property/${p.id}`)}
            >
              <div className="card shadow-sm border-0">

                <img
                  src={
                    p.imageUrl
                      ? `${process.env.REACT_APP_API_URL}/${p.imageUrl}`
                      : "https://via.placeholder.com/300"
                  }
                  alt={p.title}
                  className="card-img-top"
                  style={{ height: "200px", objectFit: "cover" }}
                />

                <div className="card-body">
                  <h6 className="fw-bold">{p.title}</h6>
                  <p className="text-muted mb-1">{p.location}</p>
                  <p className="mb-1">₹{p.price} / night</p>

                  <small>
                    ⭐ {p.rating ? p.rating.toFixed(1) : "New"} ({p.reviewCount})
                  </small>
                </div>

              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default Home;