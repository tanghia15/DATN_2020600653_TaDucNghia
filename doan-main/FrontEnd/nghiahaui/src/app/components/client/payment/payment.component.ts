import { Component, OnInit } from '@angular/core';
import { PaymentService } from 'src/app/_service/payment.service';
@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  constructor(private paymentService: PaymentService) { }

  ngOnInit(): void {
    this.createPayment();
  }

  createPayment(): void {
    this.paymentService.createPayment().subscribe(
      response => {
        // Xử lý phản hồi từ endpoint, ví dụ hiển thị URL thanh toán
        console.log(response);
        window.location.href = response.URL; // Chuyển hướng đến URL thanh toán
      },
      error => {
        // Xử lý lỗi
        console.error('Error:', error);
      }
    );
  }
}
