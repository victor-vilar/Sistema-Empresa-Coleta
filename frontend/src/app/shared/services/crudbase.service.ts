import { Injectable, OnInit, inject } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { BehaviorSubject, Observable, of, Subject, switchMap } from "rxjs";
import { environment } from 'src/environments/environment';

@Injectable(
  {providedIn: 'root',}
)
export abstract class CrudBaseService<T>{


  private refreshRequired = new Subject<any>();
  protected readonly BASE_URL:string = environment.LOCAL_API_URL;
  list:T[] = [];
  rota:string;
  protected http:HttpClient = inject(HttpClient);
  constructor(){ }

  /**
 * return this subject as observable
 * The components can subscribe to this observable to
 * get the updates comming from the send method
 * @returns this subject
 */
  refreshAllData(){
    return this.refreshRequired.asObservable();
  }

  /**
   * method to make this subject emmit an value;
   *
   * @param object the object that the components wants to receive
   */
  send(object:any){
      this.refreshRequired.next(object);
  }

  /**
   * I dont remeber why a made this method and i dont know if a component is using it anymore :D
   */
  sendNull(){
    this.refreshRequired.next(this.list);
  }

  /**
   * save T in api
   * @param type T object
   * @returns post response as an observable
   */
  save(type:T):Observable<T>{
    return  this.http.post<T>(this.BASE_URL + this.rota,type,{withCredentials:true});
  }

  /**
   * get all T from api, updated this list and send the object to all subscribed objects
   */
  getAll(){
    this.http.get<T[]>(this.BASE_URL + this.rota,{withCredentials:true})
    .subscribe(value => {
      this.list = value;
      this.send(this.list)
    });
  };

  /**
   * get a T from api with the passed id
   * @param id of the T
   * @returns T
   */
  getById(id:number | string):Observable<T>{
    return this.http.get<T>(this.BASE_URL + this.rota + '/' + id,{withCredentials:true});
  };

  /**
   *  update a T object
   * @param type of the T
   * @returns updated T
   */
  update(type:T):Observable<T>{
    return this.http.put<T>(this.BASE_URL + this.rota, type,{withCredentials:true});
  };


  /**
   *  delete T object from api
   * @param id of the T
   * @returns Observable T
   */
  delete(id:number | string):Observable<T>{
    return this.http.delete<T>(this.BASE_URL + this.rota + '/' + id,{withCredentials:true});
  };

  /**
   * get the total of T object from api
   * @returns Observable of number
   */
  getCount():Observable<number>{
    return this.http.get<number>(this.BASE_URL + this.rota + "/count",{withCredentials:true});
  }


}
