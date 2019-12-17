import {PrincipalData} from "../models/PrincipalData";
import {CardViewModel} from "../models/CardViewModel";
import {CardAddOrEditModel} from "../models/CardAddOrEditModel";
import {TagViewModel} from "../models/tagViewModel";
import {UserModel} from "../models/userModel";

const BASE_URL = 'http://localhost:8080';

const postRequestInit = {
    method: 'POST',
    credentials: 'same-origin',
    headers: {
        'Content-Type': 'application/json',
           // 'Access-Control-Allow-Origin':'*'
    },
    //mode: 'no-cors'
} as RequestInit;

const patchRequestInit = {
    method: 'PATCH',
    credentials: 'same-origin',
    headers: {
        'Content-Type': 'application/json'
    }
} as RequestInit;

const getRequestInit = {
    method: 'GET',
    credentials: 'same-origin',
} as RequestInit;

const deleteRequestInit = {
    method: 'DELETE',
    credentials: 'same-origin',
} as RequestInit;

export async function signUp(username: string, password: string) {
    let response = await fetch(
        `${BASE_URL}/register?username=${username}&password=${password}`,
        postRequestInit
    );
    console.log(response);

    if (response.status !== 200) {
        throw new Error(await response.text())
    }
}

export async function signIn(username: string, password: string) {
    let response = await fetch(
        `${BASE_URL}/login?username=${username}&password=${password}`,postRequestInit);
        //{mode: 'no-cors', ...postRequestInit});

    //console.log(await response.text());
    if (!response.ok) {
        throw new Error('Wrong username or password')
    }
    console.log((await getPrincipal()).username)
}

export async function getPrincipal(): Promise<PrincipalData> {
    let response = await fetch(`${BASE_URL}/principal`, getRequestInit);
    if (!response.ok) {
        throw new Error(await response.text())
    }
    return await response.json() as PrincipalData;
}

export class CardsHttpClient {

    static async getParticipatingCards(): Promise<CardViewModel[]> {
        let response = await fetch(`${BASE_URL}/api/card/getParticipating`, getRequestInit
        );
        if (!response.ok) {
            throw new Error(await response.text())
        }
        return await response.json() as CardViewModel[]

    }


    static async addCard(card: CardAddOrEditModel): Promise<CardViewModel> {
        let request = postRequestInit;
        request.body = JSON.stringify(card);
        let response = await fetch(`${BASE_URL}/api/card/add`, request);
        if (!response.ok) {
            throw new Error(await response.text());
        }
        return await response.json() as CardViewModel
    }


    static async editCard(card: CardAddOrEditModel, id: number): Promise<CardViewModel> {
        let requestInit = patchRequestInit;
        requestInit.body = JSON.stringify(card);
        let response = await fetch(`${BASE_URL}/api/card/${id}/edit`, requestInit);
        if (!response.ok) {
            throw new Error(await response.text());
        }
        return await response.json() as CardViewModel
    }


    static async deleteCard(id: number) {
        let response = await fetch(`${BASE_URL}/api/card/${id}/delete`, deleteRequestInit
        );
        if (!response.ok) {
            throw new Error(await response.text());
        }
    }
}

export class UsersHttpClient {
    static async getAllUsers(): Promise<UserModel[]> {
        let response = await fetch(`${BASE_URL}/api/user/getAll`, getRequestInit
        );
        if (!response.ok) {
            throw new Error(await response.text());
        }
        return await response.json() as UserModel[]
    }
}


export class TagsHttpClient {
    static async getAllTags(): Promise<TagViewModel[]> {
        let response = await fetch(`${BASE_URL}/api/tag/getAll`, getRequestInit
        );
        if (!response.ok) {
            throw new Error(await response.text());
        }
        return await response.json() as TagViewModel[]
    }


    static async addTag(tag: TagViewModel): Promise<TagViewModel> {
        let requestInit = postRequestInit;
        requestInit.body = JSON.stringify(tag);
        let response = await fetch(`${BASE_URL}/api/tag/add`, requestInit);
        if (!response.ok) {
            throw new Error(await response.text());
        }
        return await response.json() as TagViewModel
    }


    static async editTag(tag: TagViewModel, id: number): Promise<TagViewModel> {
        let requestInit = patchRequestInit;
        requestInit.body = JSON.stringify(tag);
        let response = await fetch(`${BASE_URL}/api/tag/edit?id=${id}`, requestInit);
        if (!response.ok) {
            throw new Error(await response.text());
        }
        return await response.json() as TagViewModel
    }


    static async deleteTag(id: number) {

        let response = await fetch(`${BASE_URL}/api/tag/delete?id=${id}`, deleteRequestInit
        );
        if (!response.ok) {
            throw new Error(await response.text());
        }
    }

}
