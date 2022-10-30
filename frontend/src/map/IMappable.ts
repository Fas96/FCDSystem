
export interface   iMappable {
    location :  {
        lat :  number ;
        lng :  number ;
    };

    markerContent(): Element ;
    color :  string  ;
    icon :  string  ;
}