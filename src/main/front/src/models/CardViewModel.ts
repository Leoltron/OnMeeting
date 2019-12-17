import {UserModel} from "./userModel";
import {TagViewModel} from "./tagViewModel";

export interface CardViewModel {
    title: string,
    username: string,
    cardId: number,
    locationString: string,
    startDate: string,
    endDate: string,
    participants: UserModel[],
    tags: TagViewModel[]
}