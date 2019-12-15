import {PrincipalData} from "../../models/PrincipalData";
import {CardViewModel} from "../../models/CardViewModel";
import {CardAddOrEditModel} from "../../models/CardAddOrEditModel";
import {TagViewModel} from "../../models/tagViewModel";
import {UserModel} from "../../models/userModel";

const BASE_URL = 'https://on-meeting.herokuapp.com';

const createPostRequestInit = () => <RequestInit>{
    method: 'POST',
    credentials: 'same-origin',
    headers: {
        'Content-Type': 'application/json'
    }
};
const createPatchRequestInit = () => <RequestInit>{
    method: 'PATCH',
    credentials: 'same-origin',
    headers: {
        'Content-Type': 'application/json'
    }
};
const createGetRequestInit = () => <RequestInit>{
    method: 'GET',
    credentials: 'same-origin',
};
const createDeleteRequestInit = () => <RequestInit>{
    method: 'DELETE',
    credentials: 'same-origin',
};

export class AuthHttpClient {
    async signUp(username: string, password: string) {

        let response = await window.fetch(`${BASE_URL}/register?username=${username}&password=${password}`,
            createPostRequestInit());
        if (!response.ok) {
            throw new Error(await response.text())
        }
    }


    async signIn(username: string, password: string) {

        let response = await window.fetch(`${BASE_URL}/login?username=${username}&password=${password}`,
            createPostRequestInit());
        if (!response.ok) {
            throw new Error(await response.text())
        }
    }


    async getPrincipal(): Promise<PrincipalData> {
        let response = await window.fetch(`${BASE_URL}/principal`,
            createGetRequestInit());
        if (!response.ok) {
            throw new Error(await response.text())
        }
        return await response.json() as PrincipalData;
    }

}

export class CardsHttpClient {

    async getParticipatingCards(): Promise<CardViewModel[]> {
        let response = await window.fetch(`${BASE_URL}/api/card/getParticipating`,
            createGetRequestInit());
        if (!response.ok) {
            throw new Error(await response.text())
        }
        return await response.json() as CardViewModel[]

    }


    async addCard(card: CardAddOrEditModel): Promise<CardViewModel> {
        let request = createPostRequestInit();
        request.body = JSON.stringify(card);
        let response = await window.fetch(`${BASE_URL}/api/card/add`,
            request);
        if (!response.ok) {
            throw new Error(await response.text());
        }
        return await response.json() as CardViewModel
    }


    async editCard(card: CardAddOrEditModel, id: number): Promise<CardViewModel> {
        let requestInit = createPatchRequestInit();
        requestInit.body = JSON.stringify(card);
        let response = await window.fetch(`${BASE_URL}/api/card/${id}/edit`,
            requestInit)
        if (!response.ok) {
            throw new Error(await response.text());
        }
        return await response.json() as CardViewModel
    }


    async deleteCard(id: number) {
        let response = await window.fetch(`${BASE_URL}/api/card/${id}/delete`,
            createDeleteRequestInit())
        if (!response.ok) {
            throw new Error(await response.text());
        }
    }
}

export class UsersHttpClient {
    async getAllUsers(): Promise<UserModel[]> {
        let response = await window.fetch(`${BASE_URL}/api/user/getAll`,
            createGetRequestInit())
        if (!response.ok) {
            throw new Error(await response.text());
        }
        return await response.json() as UserModel[]
    }
}


export class TagsHttpClient {
    async getAllTags(): Promise<TagViewModel[]> {
        let response = await window.fetch(`${BASE_URL}/api/tag/getAll`,
            createGetRequestInit())
        if (!response.ok) {
            throw new Error(await response.text());
        }
        return await response.json() as TagViewModel[]
    }


    async addTag(tag: TagViewModel): Promise<TagViewModel> {
        let requestInit = createPostRequestInit();
        requestInit.body = JSON.stringify(tag);
        let response = await window.fetch(`${BASE_URL}/api/tag/add`,
            requestInit)
        if (!response.ok) {
            throw new Error(await response.text());
        }
        return await response.json() as TagViewModel
    }


    async editTag(tag: TagViewModel, id: number): Promise<TagViewModel> {
        let requestInit = createPatchRequestInit();
        requestInit.body = JSON.stringify(tag);
        let response = await window.fetch(`${BASE_URL}/api/tag/edit?id=${id}`,
            requestInit)
        if (!response.ok) {
            throw new Error(await response.text());
        }
        return await response.json() as TagViewModel
    }


    async deleteTag(id: number) {

        let response = await window.fetch(`${BASE_URL}/api/tag/delete?id=${id}`,
            createDeleteRequestInit())
        if (!response.ok) {
            throw new Error(await response.text());
        }
    }

}
