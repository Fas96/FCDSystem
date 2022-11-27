import {instantsInterceptor} from "@/api/auth/axiosInstance";
import {getContext} from "@/util/commonUtils";

interface UserProps {
    name: string;
    age: number;
}

type Callback = () => void;

export class User{
    private events:  { [key: string]: Callback[] } = {};

    constructor(private data:UserProps) {}
    get (propName: string): (number | string) {
        return this.data[propName];
    }
    set(update: UserProps): void {
        Object.assign(this.data, update);
    }

    on(eventName: string, callback: Callback): void {
        const handler = this.events[eventName] || [];
        handler.push(callback);
        this.events[eventName] = handler;
    };
    trigger(eventName: string): void {
        const handler = this.events[eventName];
        if (!handler || handler.length === 0) {
            return;
        }
        handler.forEach(callback => {
            callback();
        });
    }

    fetch(): void {
        instantsInterceptor.get(`${getContext()}/users/1`)
            .then((response) => {
                this.set(response.data);
            });
    }
    save(): void {
        const id = this.get('id');
        if (id) {
            instantsInterceptor.put(`${getContext()}/users/${id}`, this.data);
        } else {
            instantsInterceptor.post(`${getContext()}/users`, this.data);
        }
    }




}