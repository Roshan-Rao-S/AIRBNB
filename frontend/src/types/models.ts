export interface Property {
  id: number;
  title: string;
  location: string;
  price: number;
  imageUrl: string;
  rating: number;
  reviewCount: number;
}

export interface AddPropertyRequest {
  title: string;
  location: string;
  price: number;
  imageUrl: string;
}

export interface Review {
  id?: number;
  propertyId: number;
  rating: number;
  comment: string;
}

export interface Booking {
  id: number;
  propertyId: number;
  checkIn: string;
  checkOut: string;
  guests: number;
  status: string;
}
