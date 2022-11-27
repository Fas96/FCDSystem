import {getContext} from "@/util/commonUtils";
import {instantsInterceptor} from "@/api/auth/axiosInstance";


export const postUserDetailsForAuthentication=(data: { password: File | string; userId: File | string })=>{
    return instantsInterceptor.post(getContext() + '/api/login/authUser', data);
}