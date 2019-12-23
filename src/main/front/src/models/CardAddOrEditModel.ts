export interface CardAddOrEditModel {
    title: string,
    locationString?: string,
    startDate?: string,
    endDate?: string,
    participantsIds: number[],
    tagIds: number[]
}