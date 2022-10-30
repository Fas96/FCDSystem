import axios from "axios";

export const   getContext=()=> {
    let context = window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
    if (context === "/") {
        return "";
    }
    return context;
}

export const storeDataInSession=(data: FCDSUser)=>{
    sessionStorage.setItem('userId', data.userId);
    sessionStorage.setItem('name', data.name);
    sessionStorage.setItem('role', data.role);
    sessionStorage.setItem('status', data.status);
    sessionStorage.setItem('failCount', data.failCount);
    sessionStorage.setItem('isBlocked', String(data.isBlocked));
}


export  const  displayLocation=async (latitude, longitude): Promise<string> => {

    let url = 'https://maps.googleapis.com/maps/api/geocode/json?latlng=' + latitude + ',' + longitude + '&key=AIzaSyAF3dVDB9988nqO0Qo5RCqbowh03x0GBs4';
    let placeName = "Unknown";
    await axios.get(url).then((response) => {
        if (response.data.status === "OK") {
            let data = response.data;
            debugger;
            placeName= data['plus_code']['compound_code'];
        }

    }).catch((error) => {
        console.log(error);
    })
    return placeName;
};
