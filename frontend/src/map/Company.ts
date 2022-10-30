import {faker} from "@faker-js/faker";
import {Mappable} from "@/map/customMap";

export  class Company extends Mappable{
    name: string;
    catchPhrase: string;
    location: {
        lat: number;
        lng: number;
    }
    constructor(){
        super();
        this.name = faker.company.companyName();
        this.catchPhrase = faker.company.catchPhrase();
        this.location = {
            lat: parseFloat(faker.address.latitude()),
            lng: parseFloat(faker.address.longitude())
        }
    }

}