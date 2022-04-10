import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LojasPageComponent } from './lojas-page.component';

describe('LojasPageComponent', () => {
  let component: LojasPageComponent;
  let fixture: ComponentFixture<LojasPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LojasPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LojasPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
