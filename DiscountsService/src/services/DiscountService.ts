import moment from "moment";

class DiscountService {
  public create(args: Models.Discount | Models.Discount[]) {
    const param = args instanceof Array ? args : [args];
    // console.log("CREEAATE", { param });
    // return Discount.insertMany(param, (error, doc) => {
    //   return doc;
    // });
  }

  public delete(id: string) {
    // console.log("delete", id);
    // return Discount.findOneAndDelete({ _id: id }, (error, doc) => {
    //   if (error) {
    //     console.log({ error });
    //     return false;
    //   }
    //   return true;
    // });
  }

  public deleteExpired(days: number) {
    const olderThan = moment().subtract(days, "days").toDate();
    // console.log("delete expired");
    // return Discount.deleteMany({ createdAt: { $lte: olderThan } }).catch(
    //   (error) => {
    //     console.log({ error });
    //   }
    // );
  }
}

export default DiscountService;
