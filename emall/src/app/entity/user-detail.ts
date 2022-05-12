import { TypeMember } from "./type-member";

export class UserDetail {
    id!: string;
    phone!: string;
    firstname!: string;
    lastname!: string;
    address!: string;
    birthday!: Date;
    gmail!: string;
    typeMember!: TypeMember;
    state!: number;
}
