import { TypeProduct } from "./type-product";

export class Product {
    productid!: string;
    typeproduct!: TypeProduct;
    productname!: string;
    images!: Array<String>;
    info!: String;
    price!:BigInteger;
    quantity!:Number;
    percent_discount!:Number;
    special_from_time!:Date;
    special_to_time!:Date;
    likes!:Number;
    views!:Number;
    state!:Number;
}