class ArrayOfAnything<T> {
    constructor(public collection: T[]) {}
    get(index: number): T {
        return this.collection[index];
    }
    set (index: number, value: T): void {
        this.collection[index] = value;
    }
    get length(): number {
        return this.collection.length;
    }
}

new ArrayOfAnything<string>(['a', 'b', 'c']);

interface  Printable {
    print(): void;
}
function printHousesOrCars<T extends Printable>(arr: T[]): void {
    for (let i = 0; i < arr.length; i++) {
        arr[i].print();
    }
}

