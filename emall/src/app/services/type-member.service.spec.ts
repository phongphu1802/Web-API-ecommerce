import { TestBed } from '@angular/core/testing';
import { TypeMemberService } from './type-member.service';

describe('TypeMemberService', () => {
  let service: TypeMemberService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TypeMemberService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
