import { Injectable } from '@angular/core';
import { Item } from './item.model';
import { BehaviorSubject } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class ItemService {
  private hd : HttpHeaders;

  exploredItems: BehaviorSubject<Item[]>;

  expI: Item[] = [];

  itemA: BehaviorSubject<Item>;
  itemB: BehaviorSubject<Item>;
  constructor(private http: HttpClient, private authService: AuthService) {
    this.hd = authService.getHeader();
    this.itemA = new BehaviorSubject<Item>(undefined);
    this.itemB = new BehaviorSubject<Item>(undefined);
    this.exploredItems = new BehaviorSubject<Item[]>(undefined);
    this.queryData();
  }

  tryMerge(itemA: Item, itemB: Item) {
    const link = 'http://35.205.82.160:8080/api/element/fusion';
    const bd = [itemA, itemB];
    console.log(bd);
    this.http.post(link, { headers: this.hd, body: bd}).subscribe(
      data => {
        this.exploredItems.next(this.exploredItems.value.push(data));
      },
      error => {
        console.log('Error: ', error);
      }
    );
  }

  toCraftTable(it: Item) {
    if (!this.itemA.getValue()) {
      this.itemA.next(it);
    } else if (!this.itemB.getValue()) {
      this.itemB.next(it);
    }

    if (this.itemA.getValue() && this.itemB.getValue()) {
      this.tryMerge(this.itemA.getValue(), this.itemB.getValue());
    }
  }

  queryData() {
    const link = 'http://35.205.82.160:8080/api/user/elements';

    this.http.get(link, { headers: this.hd }).subscribe(
      data => {
        console.log(data);
        this.expI = data as Item[];
        this.exploredItems.next(this.expI);
      },
      error => {
        console.log('Error: ', error);
      }
    );
  }
}

/*

/api/user/elements -- A bejelentkezett user elemeit adja vissza listaként
- /api/user/insertelement -- A bejelentkezett user elemei közé szúr be egy újat RequestBody : Element
- /api/element/basic -- Visszaadja egy listában az összes basic element (aminek nincsenek szülői / firstParent = null és secondParent = null)
- /api/element/all -- Visszaadja egy listában az összes elemet
- /api/element/fusion  -- Megvizsgálja, hogy két elemnek van-e gyerekeleme, ha van akkor azzal tér vissza, ha nincs akkor notfound().buld() Requestbody: JSONArray két elemmel, sorrend nemszámít*/
