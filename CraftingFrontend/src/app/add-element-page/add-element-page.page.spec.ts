import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddElementPagePage } from './add-element-page.page';

describe('AddElementPagePage', () => {
  let component: AddElementPagePage;
  let fixture: ComponentFixture<AddElementPagePage>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddElementPagePage ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA],
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddElementPagePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
