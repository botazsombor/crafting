import { Component, OnInit } from '@angular/core';
import { ItemService } from 'src/app/engine/item.service';
import { Item } from 'src/app/engine/item.model';

@Component({
  selector: 'app-explored-items',
  templateUrl: './explored-items.component.html',
  styleUrls: ['./explored-items.component.scss'],
})
export class ExploredItemsComponent implements OnInit {
  exploredItems: Item[];
  constructor(private itemService: ItemService) {
    this.exploredItems = this.itemService.getExploredItems();
    console.log(this.itemService.checkMerge(4,0));
   }

  ngOnInit() {}

}
