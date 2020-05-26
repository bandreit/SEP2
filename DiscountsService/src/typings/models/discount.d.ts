declare module Models {
  export interface Discount {
    uid: string;
    title: string;
    details?: string;
    imageUrl?: string;

    categoryId: string;
    storeId: string;

    discountPrice: number;
    normalPrice?: number;

    link?: string;
  }
}
