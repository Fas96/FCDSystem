import {faker} from "@faker-js/faker";
import {displayLocation} from "@/util/commonUtils";
import {iMappable} from "@/map/IMappable";

export  class Company implements iMappable{
    name: string;
    catchPhrase: string;
    location: {
        lat: number;
        lng: number;
    }
    constructor(){
        this.name = faker.company.companyName();
        this.catchPhrase = faker.company.catchPhrase();
        this.location = {
            lat: parseFloat(faker.address.latitude()),
            lng: parseFloat(faker.address.longitude())
        }
    }
    markerContent(): Element {
        let mapContent = document.createElement('div');
        mapContent.classList.add('map-content');
        displayLocation(this.location.lat, this.location.lng).then((placeName) => {
            let mapContentName3 = document.createElement('div');
            mapContentName3.classList.add('map-content-name');
            mapContentName3.innerHTML ='Name:'+ placeName;
            mapContent.appendChild(mapContentName3);
        }) ;

        let mapContentName = document.createElement('div');
        mapContentName.classList.add('map-content-name');
        mapContentName.innerHTML = `Lat: ${this.location.lat.toString()}`;

        let mapContentName2 = document.createElement('div');
        mapContentName2.classList.add('map-content-name');
        mapContentName2.innerHTML = `Lng: ${this.location.lng.toString()}`;
        mapContent.appendChild(mapContentName);

        mapContent.appendChild(mapContentName2);
        return mapContent;
    }
    color: string = 'red';
    icon: string = "https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png";

}

