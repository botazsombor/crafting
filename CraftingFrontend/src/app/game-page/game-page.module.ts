import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Routes, RouterModule } from '@angular/router';

import { IonicModule } from '@ionic/angular';

import { GamePagePage } from './game-page.page';
import { ExploredItemsComponent } from './explored-items/explored-items.component';
import { CraftingAreaComponent } from './crafting-area/crafting-area.component';


const routes: Routes = [
  {
    path: '',
    component: GamePagePage
  }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    RouterModule.forChild(routes)
  ],
  declarations: [GamePagePage, ExploredItemsComponent, CraftingAreaComponent]
})
export class GamePagePageModule {}
