import { Injectable } from '@angular/core';
import { Item } from './item.model';
import { BehaviorSubject } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

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
    },
    {
      id: 3,
      name: 'fire',
      parentA: 1,
      parentB: 2,
      imgUrl: ''
    },
    {
      id: 4,
      name: 'brick',
      parentA: 3,
      parentB: 2,
      imgUrl: ''
    },
    {
      id: 5,
      name: 'house',
      parentA: 4,
      parentB: 4,
      imgUrl: ''
    }
  ];

  private exploredItems: Item[] = [
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
    }
  ];

  itemA: BehaviorSubject<Item>;
  itemB: BehaviorSubject<Item>;
  constructor(private http: HttpClient) {
    this.itemA = new BehaviorSubject<Item>(undefined);
    this.itemB = new BehaviorSubject<Item>(undefined);
  }

  getExploredItems() {
    return [...this.exploredItems];
  }

  tryMerge(itemA: number, itemB: number) {
    const it = this.items.filter(
      x =>
        (x.parentA === itemA && x.parentB === itemB) ||
        (x.parentA === itemB && x.parentB === itemA)
    )[0];

    it && this.exploredItems.indexOf(it) === -1
      ? this.exploredItems.push(it)
      : null;
  }

  toCraftTable(it: Item) {
    if (!this.itemA.getValue()) {
      this.itemA.next(it);
    } else if (!this.itemB.getValue()) {
      this.itemB.next(it);
    }

    if (this.itemA.getValue() && this.itemB.getValue()) {
      this.tryMerge(this.itemA.getValue().id, this.itemB.getValue().id);
    }
  }

  queryData() {
    const link = 'http://35.205.136.122:8080/api/element/all';
    const httpOptions = {
      headers: new HttpHeaders({
        'Access-Control-Allow-Origin': '*'
      })
    };
    this.http.get(link)
    .subscribe(
        data => { // json data
            console.log('Success: ', data);
        },
        error => {
            console.log('Error: ', error);
        });
  }
}
