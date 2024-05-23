// order-detail.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OrderService } from 'src/app/_service/order.service';
import { OrderDetail } from 'src/app/_class/order-detail';
import { Location } from '@angular/common';
import { OrderDetailService } from 'src/app/_service/order-detail.service';

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.scss']
})
export class OrderDetailComponent implements OnInit {
  orderId: number = 0;
  orderDetail!: OrderDetail;

  constructor(
    private route: ActivatedRoute,
    private orderService: OrderService,
    private orderDetailService: OrderDetailService,
    private location: Location
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.orderId = +params['id'];
      this.getOrderDetail();
    });
  }

  getOrderDetail(): void {
    this.orderService.getOrderById(this.orderId).subscribe({
      next: (order: any) => {
        // Lấy thông tin order detail khi nhận được thông tin về order
        this.orderService.getOrderById(order.id).subscribe({
          next: (orderDetail: OrderDetail) => {
            this.orderDetail = orderDetail;
          },
          error: (err: any) => {
            console.log(err);
          }
        });
      },
      error: (err: any) => {
        console.log(err);
      }
    });
  }
  
  goBack(): void {
    this.location.back();
  }
}
