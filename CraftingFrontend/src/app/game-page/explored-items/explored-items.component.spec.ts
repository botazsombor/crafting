import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExploredItemsPage } from './explored-items.page';

describe('ExploredItemsPage', () => {
  let component: ExploredItemsPage;
  let fixture: ComponentFixture<ExploredItemsPage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExploredItemsPage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExploredItemsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
