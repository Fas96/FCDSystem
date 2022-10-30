import {displayLocation} from "@/util/commonUtils";


export  class Mappable {
    location :  {
     lat :  number ;
     lng :  number ;
      };
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
    color :  string  =  'red' ;
}

export class CustomMap{
    private googleMap: google.maps.Map;
    constructor(divId: string){
      this.googleMap=  new google.maps.Map(document.getElementById(divId), {
            zoom: 1,
            center: {
                lat: 0,
                lng: 0
            }
        })
    }

    addMarker(mappable: Mappable): void{
        const marker = new google.maps.Marker({
            map: this.googleMap,
            position: {
                lat: mappable.location.lat,
                lng: mappable.location.lng
            }
        })
        marker.addListener('click', () => {
            const infoWindow = new google.maps.InfoWindow({
                content: mappable.markerContent()
            })

            infoWindow.open(this.googleMap, marker)

            this.googleMap.setZoom(2);
            this.googleMap.setCenter(marker.getPosition() as google.maps.LatLng);

        })
    }





}
