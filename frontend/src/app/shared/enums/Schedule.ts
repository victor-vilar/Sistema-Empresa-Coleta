export const Schedule = {

  DIARIO          : 'DIÁRIO',
  SEMANAL         : 'SEMANAL',
  QUINZENAL       : 'QUINZENAL',
  MENSAL          : 'MENSAL',
  SOB_SOLICITACAO : 'SOB SOLICITAÇÃO'

}as const;


export type ScheduleType = typeof Schedule[keyof typeof  Schedule];

export function getScheduleValues(){
  return Object.keys(Schedule).map(value => value);
}
