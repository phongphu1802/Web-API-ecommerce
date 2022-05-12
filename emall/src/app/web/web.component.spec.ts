import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { WebComponent } from './web.component';

describe('WebComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule
      ],
        declarations: [ WebComponent ]
      })
    .compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(WebComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'emall'`, () => {
    const fixture = TestBed.createComponent(WebComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('emall');
  });

  it('should render title', () => {
    const fixture = TestBed.createComponent(WebComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('.content span')?.textContent).toContain('emall app is running!');
  });
});
