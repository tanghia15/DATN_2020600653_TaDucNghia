import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/_service/order.service';
import { StorageService } from 'src/app/_service/storage.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-my-order',
  templateUrl: './my-order.component.html',
  styleUrls: ['./my-order.component.css']
})
export class MyOrderComponent implements OnInit {

  listOrder:any;
  username: any;
  constructor(private orderService: OrderService,private storageService: StorageService,private router: Router){}

  ngOnInit(): void {
    this.username = this.storageService.getUser().username;
    this.getListOrder();
  }

  getListOrder(){
    this.orderService.getListOrderByUser(this.username).subscribe({
      next: res=>{
        this.listOrder = res;
        console.log(this.listOrder);
      },error: err =>{
        console.log(err);
      }
    })
  }
  viewOrderDetail(orderId: number): void {
    this.router.navigate(['/order', orderId]);
  }
}
