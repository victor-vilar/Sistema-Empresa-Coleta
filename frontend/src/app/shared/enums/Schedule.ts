export const Schedule = {

  DIARIO          : 1,
  SEMANAL         : 2,
  QUINZENAL       : 3,
  MENSAL          : 4,
  SOB_SOLICITACAO : 5

}as const;


export type ScheduleType = typeof Schedule[keyof typeof  Schedule];

export function getScheduleValues(){
  return Object.keys(Schedule).map(value => value);
}
