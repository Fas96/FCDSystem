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