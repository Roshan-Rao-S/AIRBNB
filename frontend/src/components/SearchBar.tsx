const SearchBar = () => {
  return (
    <div className="d-flex justify-content-center mt-4">
      <div
        className="d-flex align-items-center shadow-sm px-3 py-2 bg-white"
        style={{
          borderRadius: "40px",
          width: "600px",
          gap: "10px",
        }}
      >
        {/* Where */}
        <div className="flex-fill text-center border-end">
          <small className="fw-bold d-block">Where</small>
          <input
            type="text"
            placeholder="Search destinations"
            className="border-0 text-center"
            style={{ outline: "none" }}
          />
        </div>

        {/* When */}
        <div className="flex-fill text-center border-end">
          <small className="fw-bold d-block">When</small>
          <input
            type="text"
            placeholder="Add dates"
            className="border-0 text-center"
            style={{ outline: "none" }}
          />
        </div>

        {/* Guests */}
        <div className="flex-fill text-center">
          <small className="fw-bold d-block">Who</small>
          <input
            type="text"
            placeholder="Add guests"
            className="border-0 text-center"
            style={{ outline: "none" }}
          />
        </div>

        {/* Search Button */}
        <button
          className="btn btn-danger rounded-circle"
          style={{ width: "45px", height: "45px" }}
        >
          🔍
        </button>
      </div>
    </div>
  );
};

export default SearchBar;