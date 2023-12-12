export const Weekday = {

  DOMINGO : 'DOMINGO',
  SEGUNDA : 'SEGUNDA-FEIRA',
  TERCA   : 'TERÇA-FEIRA',
  QUARTA  : 'QUARTA-FEIRA',
  QUINTA  : 'QUINTA-FEIRA',
  SEXTA   : 'SEXTA-FEIRA',
  SABADO  : 'SÁBADO'
} as const;

export type WeekdayType = typeof Weekday[keyof typeof Weekday]


export function getWeekdayValues(){
  return Object.keys(Weekday).map(value =>value);
}

