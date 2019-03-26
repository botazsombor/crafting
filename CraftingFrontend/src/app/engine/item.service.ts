import { Injectable } from '@angular/core';
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
  constructor() {}

  getExploredItems() {
    return [...this.items];
  }

  checkMerge(itemA: number, itemB: number) {
    return this.items.filter(
      x =>
        (x.parentA === itemA && x.parentB === itemB) ||
        (x.parentA === itemB && x.parentB === itemA)
    )[0];
  }
}
