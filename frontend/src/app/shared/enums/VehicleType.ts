
export const VehicleType ={

  CAMINHAO_COMPACTADOR:"Caminhão Compactador",
  CAMINHAO_POLIGUINDASTE:"Caminhão Poliguindaste",
  CAMINHAO_ROLLON_ROLLOFF:"Caminhão Rollon Rolloff",
  CAMINHAO_BAU:"Caminhão Baú",
  CAMINHAO_TANQUE:"Caminhão Tanque",
  FIORINO_FURGAO:"Fiorino Furgão"

} as const;

export type VehicleTypeType = typeof VehicleType[keyof typeof VehicleType]

