import moment from "moment";
import Discount from "../models/Discount";

class DiscountService {
  public create(args: Models.Discount | Models.Discount[]) {
    const param = args instanceof Array ? args : [args];

    return Discount.insertMany(param, (error, doc) => {
      return doc;
    });
  }

  public update(id: string, input: Models.Discount) {
    return Discount.findOneAndUpdate(
      { _id: id },
      {
        ...input
      },
      {
        new: true
      },
      (error, doc) => {
        console.log({ error });
        return doc;
      }
    );
  }

  public delete(id: string) {
    return Discount.findOneAndDelete({ _id: id }, (error, doc) => {
      if (error) {
        console.log({ error });
        return false;
      }

      return true;
    });
  }

  public deleteExpired(days: number) {
    const olderThan = moment()
      .subtract(days, "days")
      .toDate();

    return Discount.deleteMany({ createdAt: { $lte: olderThan } }).catch(
      error => {
        console.log({ error });
      }
    );
  }

  public get({ ...args }) {
    return Discount.findOne({ ...args }, (error, data) => {
      if (error) {
        console.log({ error });
      }

      return data;
    });
  }

  public list({ limit, skip, filter }) {
    const find = filter
      ? {
          $or: [
            { title: new RegExp(filter, "gi") },
            { employer: new RegExp(filter, "gi") },
            { location: new RegExp(filter, "gi") }
          ]
        }
      : {};

    return Discount.find(find)
      .limit(limit)
      .skip(skip)
      .catch(error => {
        console.log({ error });
      });
  }
}

export default DiscountService;
