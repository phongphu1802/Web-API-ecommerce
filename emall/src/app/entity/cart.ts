import { Product } from "../entity/product";
import { UserDetail } from "./user-detail";

export class Cart {
    cartid!: number;
    product!: Product;
    userDetail!: UserDetail;
    quantity!: number;
}