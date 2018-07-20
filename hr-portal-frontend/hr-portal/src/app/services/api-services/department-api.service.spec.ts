import { TestBed, inject } from '@angular/core/testing';

import { DepartmentApiService } from './department-api.service';

describe('DepartmentApiService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DepartmentApiService]
    });
  });

  it('should be created', inject([DepartmentApiService], (service: DepartmentApiService) => {
    expect(service).toBeTruthy();
  }));
});
