import mongoose from "mongoose";

const schema = new mongoose.Schema(
  {
    uid: { type: String, required: true, unique: true },
    title: { type: String, required: true },
    details: { type: String, required: false },
    imageUrl: { type: String, required: false },

    categoryId: { type: String, required: true },
    storeId: { type: String, required: true },

    discountPrice: { type: Number, required: false },
    normalPrice: { type: Number, required: false },

    link: { type: String, required: false }
  },
  { timestamps: { createdAt: "createdAt", updatedAt: "updatedAt" } }
);

export default mongoose.model("Discounts", schema);
