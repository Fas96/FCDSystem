import {iMappable} from "@/map/IMappable";



export class CustomMap{
    private readonly googleMap: google.maps.Map;
    constructor(divId: string){
      this.googleMap=  new google.maps.Map(document.getElementById(divId), {
            zoom: 1,
            center: {
                lat: 0,
                lng: 0
            }
        })
    }
    addMarker(mappable: iMappable): void{
        const marker = new google.maps.Marker({
            map: this.googleMap,
            position: {
                lat: mappable.location.lat,
                lng: mappable.location.lng
            },
            icon:mappable.icon,
        })
        marker.setTitle("mappable.color");

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
