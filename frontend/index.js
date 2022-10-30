import React from 'react';
import {LoginPage} from "@/authentication/LoginPage";
import {initMap} from "@/map";
window.onload = function () {
    let loginPage=document.getElementById("loginPage");
    let mapPage=document.getElementById("mapPage");

    if(loginPage){
      LoginPage.initLogin();
    }
    if(mapPage){
        initMap();
    }

}
