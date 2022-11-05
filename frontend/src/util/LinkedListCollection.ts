import { Sorter} from "@/util/Sorter";

export class LinkedListCollection extends Sorter{
     head: Node | null = null;
     constructor(public data: Node) {
         super();}

    get length(): number {
        let length = 0;
        let node: Node | null = this.head;
        while (node) {
            length++;
            node = node.next;
        }
        return length;
    }
    add(data: number): void {
        const node = new Node(data);
        if (!this.head) {
            this.head = node;
            return;
        }
        let tail = this.head;
        while (tail.next) {
            tail = tail.next;
        }
        tail.next = node;
    }

    get tail(): Node {
        if (!this.head) {
            throw new Error('List is empty');
        }
        let node: Node | null = this.head;
        while (node.next) {
            node = node.next;
        }
        return node;
    }

    get at(): Node {
        if (!this.head) {
            throw new Error('List is empty');
        }
        let node: Node | null = this.head;
        while (node.next) {
            node = node.next;
        }
        return node;
    }
    print(): void {
        if (!this.head) {
            return;
        }
        let node: Node | null = this.head;
        while (node) {
            console.log(node.data);
            node = node.next;
        }
    }


    compare(leftIndex: number, rightIndex: number): boolean {
        let leftNode: Node | null = this.head;
        let rightNode: Node | null = this.head;

        for (let i = 0; i < leftIndex; i++) {
            leftNode = leftNode.next;
        }
        for (let i = 0; i < rightIndex; i++) {
            rightNode = rightNode.next;
        }
        return leftNode.data > rightNode.data;
    }

    swap(leftIndex: number, rightIndex: number): void {
        let leftNode: Node | null = this.head;
        let rightNode: Node | null = this.head;

        for (let i = 0; i < leftIndex; i++) {
            leftNode = leftNode.next;
        }
        for (let i = 0; i < rightIndex; i++) {
            rightNode = rightNode.next;
        }
        const leftHand = leftNode.data;
        leftNode.data = rightNode.data;
        rightNode.data = leftHand;
    }
}
export  class Node {
    next: Node | null = null;
    constructor(public data: number) {}
    length: number = 0;
}