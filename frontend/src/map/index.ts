import {CustomMap} from "@/map/customMap";
import {User} from "@/map/User";
import {Company} from "@/map/Company";

export const  initMap = () => {
    let m=  new CustomMap('mapDiv');
    m.addMarker(new User())
    m.addMarker(new Company())
}