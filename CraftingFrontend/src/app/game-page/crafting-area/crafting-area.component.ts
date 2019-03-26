import { Component, OnInit, Input } from '@angular/core';
import { ItemService } from 'src/app/engine/item.service';
import { Item } from 'src/app/engine/item.model';

@Component({
  selector: 'app-crafting-area',
  templateUrl: './crafting-area.component.html',
  styleUrls: ['./crafting-area.component.scss']
})
export class CraftingAreaComponent implements OnInit {
  itemA: Item;
  itemB: Item;
  constructor(private itemService: ItemService) {
    itemService.itemA.asObservable().subscribe(
      item => this.itemA = item
    );

    itemService.itemB.asObservable().subscribe(
      item => this.itemB = item
    );
  }

  ngOnInit() {}

  onRemoveItem(it: number){
    if(it===0){
      this.itemService.itemA.next(undefined);
    }
    else{
      this.itemService.itemB.next(undefined);
    }
  }
}
