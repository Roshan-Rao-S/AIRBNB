import { useEffect, useState } from "react";
import { addProperty, getHostProperties, deleteProperty } from "../api/propertyApi";
import ToastMessage from "../components/ToastMessage";

const HostDashboard = () => {
  const [form, setForm] = useState({
    title: "",
    location: "",
    price: "",
    imageUrl: "",
  });

  const [properties, setProperties] = useState<any[]>([]);
  const [loading, setLoading] = useState(true);

  const [toast, setToast] = useState({
    show: false,
    message: "",
    type: "success" as "success" | "error",
  });

  // 🔥 LOAD HOST PROPERTIES
  const loadProperties = async () => {
    try {
      const data = await getHostProperties();
      setProperties(data);
    } catch {
      setToast({
        show: true,
        message: "Failed to load properties ❌",
        type: "error",
      });
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadProperties();
  }, []);

  // ➕ ADD PROPERTY
  const handleAdd = async () => {
    try {
      await addProperty({
        ...form,
        price: Number(form.price),
      });

      setToast({
        show: true,
        message: "Property added ✅",
        type: "success",
      });

      setForm({
        title: "",
        location: "",
        price: "",
        imageUrl: "",
      });

      loadProperties();
    } catch {
      setToast({
        show: true,
        message: "Add failed ❌",
        type: "error",
      });
    }
  };

  // ❌ DELETE PROPERTY
  const handleDelete = async (id: number) => {
    try {
      await deleteProperty(id);

      setToast({
        show: true,
        message: "Deleted successfully ✅",
        type: "success",
      });

      loadProperties();
    } catch {
      setToast({
        show: true,
        message: "Delete failed ❌",
        type: "error",
      });
    }
  };

  return (
    <div className="container mt-4">
      <h3>Host Dashboard</h3>

      {/* FORM */}
      <div className="card p-3 mb-4">
        <h5>Add Property</h5>

        <input
          className="form-control mb-2"
          placeholder="Title"
          value={form.title}
          onChange={(e) => setForm({ ...form, title: e.target.value })}
        />

        <input
          className="form-control mb-2"
          placeholder="Location"
          value={form.location}
          onChange={(e) => setForm({ ...form, location: e.target.value })}
        />

        <input
          className="form-control mb-2"
          placeholder="Price"
          value={form.price}
          onChange={(e) => setForm({ ...form, price: e.target.value })}
        />

        <input
          className="form-control mb-2"
          placeholder="Image URL (images/xxx.jpeg)"
          value={form.imageUrl}
          onChange={(e) => setForm({ ...form, imageUrl: e.target.value })}
        />

        <button className="btn btn-primary w-100" onClick={handleAdd}>
          Add Property
        </button>
      </div>

      {/* LIST */}
      {loading ? (
        <p>Loading...</p>
      ) : properties.length === 0 ? (
        <p>No properties yet</p>
      ) : (
        <div className="row">
          {properties.map((p) => (
            <div key={p.id} className="col-md-4 mb-3">
              <div className="card">
                <img
                  src={`http://localhost:8080/${p.imageUrl}`}
                  style={{ height: "200px", objectFit: "cover" }}
                />

                <div className="card-body">
                  <h6>{p.title}</h6>
                  <p>{p.location}</p>
                  <p>₹{p.price}</p>

                  <button
                    className="btn btn-danger btn-sm w-100"
                    onClick={() => handleDelete(p.id)}
                  >
                    Delete
                  </button>
                </div>
              </div>
            </div>
          ))}
        </div>
      )}

      <ToastMessage
        show={toast.show}
        message={toast.message}
        type={toast.type}
        onClose={() => setToast({ ...toast, show: false })}
      />
    </div>
  );
};

export default HostDashboard;