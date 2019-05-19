import { Component, OnInit } from '@angular/core';
import { AuthService } from '../engine/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  constructor(private auth: AuthService ) { }

  ngOnInit() {
  }

  login(form){
    console.log(form.value);
    this.auth.login(form.value.username, form.value.password);
  }

}
