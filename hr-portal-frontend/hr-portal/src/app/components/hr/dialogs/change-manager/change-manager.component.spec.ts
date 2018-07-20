import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeManagerComponent } from './change-manager.component';

describe('ChangeManagerComponent', () => {
  let component: ChangeManagerComponent;
  let fixture: ComponentFixture<ChangeManagerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChangeManagerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
