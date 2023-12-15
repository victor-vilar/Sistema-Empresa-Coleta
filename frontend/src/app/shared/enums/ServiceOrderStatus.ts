export const ServiceOrderStatus = {

    UNDONE    : 'EM ABERTO',
    DONE      : 'FINALIZADO',
    CANCELLED : 'CANCELADO'


} as const

export type ServiceOrderStatusType = typeof ServiceOrderStatus[keyof typeof ServiceOrderStatus]

