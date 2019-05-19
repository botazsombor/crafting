import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CraftingAreaPage } from './crafting-area.page';

describe('CraftingAreaPage', () => {
  let component: CraftingAreaPage;
  let fixture: ComponentFixture<CraftingAreaPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CraftingAreaPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CraftingAreaPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
