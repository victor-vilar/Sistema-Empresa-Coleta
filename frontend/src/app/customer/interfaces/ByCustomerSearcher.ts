import { Observable } from "rxjs";


/**
 * Algumas componentes pertencem a algum cliente. Essa interface é utilizada para que seja procurada
 * uma lista de objetos que pertençam a algum cliente.
 */
export interface ByCustomerSearcher<T> {

  getAllByCustomerId(customerId:string | number):Observable<T[]>;

}
