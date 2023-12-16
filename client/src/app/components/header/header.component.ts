import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html'
})
export class HeaderComponent implements OnInit {
  title = 'MegaMart - Online Store';

  constructor() { }

  ngOnInit(): void { }
}
