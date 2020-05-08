import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { map } from "rxjs/operators";

export class User {
  constructor(public status: string) {}
}

@Injectable({
  providedIn: "root"
})
export class AuthenticationService {
  constructor(private httpClient: HttpClient) {}

  user:User;
  authenticate(username,password) {
    console.log(username+password);
    return this.httpClient
      .post<any>("http://localhost:8088/authenticate", {
        "userName":username,
        "password":password
      }
      ,{  responseType: 'text' as 'json' })
      .pipe(
        map(userData => {
            sessionStorage.setItem("username",username);
          let tokenStr = 'Bearer ' + userData;
          console.log(tokenStr);
          // sessionStorage.setItem("token", tokenStr);
          return userData;
        })
      );
  }

  isUserLoggedIn() {
    let user = sessionStorage.getItem("username");
    return !(user === null);
  }

  logOut() {
    sessionStorage.clear();
  }
}
