export const ContractStatus = {
  ATIVO: 'ATIVO',
  CANCELADO: 'CANCELADO',
  RENOVACAO_PENDENTE:'RENOVAÇÃO PENDENTE',
  ENCERRADO:'ENCERRADO'
}as const;

export type ContractStatusType = typeof ContractStatus[keyof typeof ContractStatus]


export function getContractStatusValues(){
  return Object.keys(ContractStatus).map(value => value);
}
