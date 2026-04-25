import { useState } from "react";

const UploadImage = () => {
  const [file, setFile] = useState<File | null>(null);
  const [imagePath, setImagePath] = useState("");

  const handleUpload = async () => {
    if (!file) return;

    const formData = new FormData();
    formData.append("file", file);

    const res = await fetch("http://localhost:8080/properties/upload", {
      method: "POST",
      body: formData,
    });

    const data = await res.text();
    setImagePath(data);

    console.log("Saved path:", data);
  };

  return (
    <div className="container mt-4">
      <h3>Upload Image</h3>

      <input
        type="file"
        onChange={(e) => setFile(e.target.files?.[0] || null)}
      />

      <button className="btn btn-primary mt-2" onClick={handleUpload}>
        Upload
      </button>

      {imagePath && (
        <div className="mt-3">
          <p>Saved as: {imagePath}</p>
          <img
            src={`http://localhost:8080/${imagePath}`}
            alt="uploaded"
            style={{ width: "300px" }}
          />
        </div>
      )}
    </div>
  );
};

export default UploadImage;