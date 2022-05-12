import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TypeproductComponent } from './typeproduct.component';

describe('TypeproductComponent', () => {
  let component: TypeproductComponent;
  let fixture: ComponentFixture<TypeproductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TypeproductComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TypeproductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
