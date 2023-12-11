export const ServiceOrderStatus = {

    UNDONE :  0,
    DONE : 1,
    CANCELLED : 2


} as const

export type ServiceOrderStatusType = typeof ServiceOrderStatus[keyof typeof ServiceOrderStatus]

