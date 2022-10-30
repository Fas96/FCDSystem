import {faker} from "@faker-js/faker";
import {Mappable} from "@/map/customMap";
export class User extends Mappable{
    name: string;
    location: {
        lat: number;
        lng: number;
    }
    constructor(){
        super();
        this.name = faker.name.firstName();
        this.location = {
            lat: parseFloat(faker.address.latitude()),
            lng: parseFloat(faker.address.longitude())
        }
    }
    // constructor(){
    //     this.name = faker.name.firstName();
    //     this.location = {
    //         lat: parseFloat(faker.address.latitude()),
    //         lng: parseFloat(faker.address.longitude())
    //     }
    // }
}