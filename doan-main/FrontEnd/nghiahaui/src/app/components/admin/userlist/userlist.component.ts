import { MessageService } from "primeng/api";
import { Component, OnInit } from "@angular/core";
import { UserService } from "src/app/_service/user.service";
import { StorageService } from "src/app/_service/storage.service";
@Component({
    selector: 'app-userlist',
    templateUrl: './userlist.component.html',
    styleUrls: ['./userlist.component.scss'],
    providers: [MessageService]
  
})
export class UserListComponent implements OnInit {
    listUser:any;
    
    constructor(private userService: UserService,private storageService: StorageService){}
  
    ngOnInit(): void {
      this.getListUser();
    }
    getListUser(){
        this.userService.getListUser().subscribe({
          next: res =>{
            this.listUser = res;
            console.log(res);
          },error: err =>{
            console.log(err);
          }
        })
      }
}