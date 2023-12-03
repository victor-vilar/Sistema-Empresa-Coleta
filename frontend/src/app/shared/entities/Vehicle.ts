import { VehicleType } from "../enums/VehicleType";

export interface Vehicle {
  id: number;
  vehicleType: VehicleType;
  plate: string;
  renavam: string;
  validCrlvUrl: string;
}
