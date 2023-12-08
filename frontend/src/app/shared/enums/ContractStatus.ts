export const ContractStatus = {
  ATIVO:1,
  CANCELADO:2,
  RENOVACAO_PENDENTE:3,
  ENCERRADO:4
}as const;

export type ContractStatusType = typeof ContractStatus[keyof typeof ContractStatus]


export function getContractStatusValues(){
  return Object.keys(ContractStatus).map(value => value);
}
