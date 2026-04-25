import { useEffect } from "react";

interface Props {
  message: string;
  type: "success" | "error";
  show: boolean;
  onClose: () => void;
}

const ToastMessage = ({ message, type, show, onClose }: Props) => {
  useEffect(() => {
    if (show) {
      const timer = setTimeout(onClose, 3000);
      return () => clearTimeout(timer);
    }
  }, [show]);

  if (!show) return null;

  return (
    <div
      className="position-fixed top-0 end-0 p-3"
      style={{ zIndex: 9999 }}
    >
      <div
        className={`toast show text-white ${
          type === "success" ? "bg-success" : "bg-danger"
        }`}
      >
        <div className="toast-body d-flex justify-content-between align-items-center">
          {message}
          <button className="btn-close btn-close-white" onClick={onClose}></button>
        </div>
      </div>
    </div>
  );
};

export default ToastMessage;