import { Injectable, Output } from '@angular/core';
import { Item } from './item.model';


@Injectable({
  providedIn: 'root'
})
export class ItemService {
  private items: Item[] = [
    {
      id: 0,
      name: 'water',
      parentA: null,
      parentB: null,
      imgUrl: ''
    },
    {
      id: 1,
      name: 'dirt',
      parentA: null,
      parentB: null,
      imgUrl: ''
    },
    {
      id: 2,
      name: 'mud',
      parentA: 0,
      parentB: 1,
      imgUrl: ''
    }
  ];

  itemA: Item;
  itemB: Item;
  constructor() {}

  getExploredItems() {
    return [...this.items];
  }

  tryMerge(itemA: number, itemB: number) {
    return this.items.filter(
      x =>
        (x.parentA === itemA && x.parentB === itemB) ||
        (x.parentA === itemB && x.parentB === itemA)
    )[0];
  }

  toCraftTable(it: Item){
    if(!this.itemA){
      this.itemA = it;
    }
    else if (!this.itemB){
      this.itemB = it;
    }
  }

  removeItem(it: Item){
    if(this.itemA.id === it.id){
      this.itemA = undefined;
      return;
    }
    this.itemB = undefined;
  }

  getItemA(){
    return this.itemA;
  }
}
