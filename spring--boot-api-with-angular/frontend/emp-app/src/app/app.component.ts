import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Daum, Users} from "./Users";
import {Employee} from "./Employee";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  title = 'emp-app';
  employee:Employee[]=[];

  constructor( private http:HttpClient) {
  }

  public onClick() {
    console.log("hello")
  }

  ngOnInit(): void {
    this.http.get<Employee[]>("http://localhost:8080/api/v1/employee").subscribe(value => {
      this.employee=value;
    });
  }
}
