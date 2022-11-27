import React from 'react';
import {LoginPage} from "@/authentication/LoginPage";
import {initMap} from "@/map";

import {LinkedListCollection, Node} from "@/util/LinkedListCollection";
window.onload = function () {
    let loginPage=document.getElementById("loginPage");
    let mapPage=document.getElementById("mapPage");

    if(loginPage){
      LoginPage.initLogin();
    }
    if(mapPage){
        initMap();
    }

    let node = new Node(22);
    node.next = new Node(3);
    node.next.next = new Node(4);
    node.next.next.next = new Node(3);
    let linkedListCollection  = new LinkedListCollection(node);

    linkedListCollection.print();

}
