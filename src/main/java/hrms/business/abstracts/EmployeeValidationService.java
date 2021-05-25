package hrms.business.abstracts;

import hrms.entities.concretes.Employee;


public interface EmployeeValidationService {
	public void validate(Employee employee) throws Exception;
}
