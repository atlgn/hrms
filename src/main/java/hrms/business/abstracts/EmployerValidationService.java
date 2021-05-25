package hrms.business.abstracts;

import hrms.entities.concretes.Employer;

public interface EmployerValidationService {
	public void validate(Employer employer) throws Exception;
}
