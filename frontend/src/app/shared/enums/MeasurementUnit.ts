export const  MeasurementUnit = {
  METRO_CUBICO : 1,
  QUILOS       : 2,
  LITROS       : 3,
  EQUIPAMENTO  : 4
}

export type MeasurementUnitType = typeof MeasurementUnit[keyof typeof MeasurementUnit]


export function getMeasurementUnitValues(){
  return Object.keys(MeasurementUnit).filter(value => value);
}
