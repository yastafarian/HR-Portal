import { TestBed, inject } from '@angular/core/testing';

import { DepartmentDataService } from './department-data.service';

describe('DepartmentDataService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DepartmentDataService]
    });
  });

  it('should be created', inject([DepartmentDataService], (service: DepartmentDataService) => {
    expect(service).toBeTruthy();
  }));
});
