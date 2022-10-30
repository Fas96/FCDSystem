import React from 'react';
import {LoginPage} from "@/authentication/LoginPage";
import {CustomMap} from "@/map/customMap";
import {User} from "@/map/User";
import {Company} from "@/map/Company";
window.onload = function () {
    let loginPage=document.getElementById("loginPage");
    let mapPage=document.getElementById("mapPage");

    if(loginPage){
      LoginPage.initLogin();
    }
    if(mapPage){
       let m=  new CustomMap('mapDiv');
       m.addMarker(new User())
       m.addMarker(new Company())
    }

}
