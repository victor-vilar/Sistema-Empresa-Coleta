import { VehicleTypeType } from "../enums/VehicleType";

export interface Vehicle {
  id: number;
  vehicleType: VehicleTypeType;
  plate: string;
  renavam: string;
  validCrlvUrl: string;
}
