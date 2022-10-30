import {getContext} from "@/util/commonUtils";
import axios from "axios";


export const postUserDetailsForAuthentication=(data: { password: File | string; userId: File | string })=>{
   return  axios.post<FCDSUser>(getContext() + '/api/login/authUser', JSON.stringify(data), {headers: {'Content-Type': 'application/json'}
    });

}