import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private apiUrl = '/api/payment/create_payment'; // Thay đổi địa chỉ nếu cần thiết

  constructor(private http: HttpClient) { }

  createPayment(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }
}
