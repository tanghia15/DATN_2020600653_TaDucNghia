import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { OrderDetail } from '../_class/order-detail';

const ORDER_DETAIL_API = "http://localhost:8080/api/order_detail/";
const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class OrderDetailService {

  constructor(private http: HttpClient) { }

  getOrderDetail(orderId: number): Observable<OrderDetail> {
    return this.http.get<OrderDetail>(`${ORDER_DETAIL_API}${orderId}`, httpOptions);
  }
}
