import { Observable } from "rxjs";

export interface ByCustomerSearcher<T> {

  getAllByCustomerId(customerId:string | number):Observable<T[]>;

}
