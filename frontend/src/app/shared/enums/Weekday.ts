export const Weekday = {

  DOMINGO : 1,
  SEGUNDA : 2,
  TERCA   : 3,
  QUARTA  : 4,
  QUINTA  : 5,
  SEXTA   : 6,
  SABADO  : 7
} as const;

export type WeekdayType = typeof Weekday[keyof typeof Weekday]


export function getWeekdayValues(){
  return Object.keys(Weekday).map(value =>value);
}

