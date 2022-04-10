import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransacoesPageComponent } from './transacoes-page.component';

describe('TransacoesPageComponent', () => {
  let component: TransacoesPageComponent;
  let fixture: ComponentFixture<TransacoesPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TransacoesPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TransacoesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
