import axios from 'axios';
import {setInterceptors} from "@/api/auth/interceptor";
import {getContext} from "@/util/commonUtils";


export function processApiError(error) {
    if (error.response.status != 200) {
        alert('Redirecting to login page');
        location.href = '/login';
    } else {
        alert(error);
    }
}

function createInstance() {
    return axios.create({
        baseURL: getContext(),
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json; charset = utf-8',
        },
    });
}

// axios instance
function createInstanceWithUrl() {
    const instance = axios.create({
        baseURL: getContext(),
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json; charset = utf-8',
        }
    });
    return setInterceptors(instance);
}

// multipart content-type
function createInstanceFormWithUrl() {
    const instance = axios.create({
        baseURL: getContext(),
        headers: {
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'multiparti/form-data;',
        },
    });
    return setInterceptors(instance);
}

export const instance = createInstance();
export const instantsInterceptor = createInstanceWithUrl();
export const instantsInterceptorFormData = createInstanceFormWithUrl();