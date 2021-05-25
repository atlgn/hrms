package hrms.business.abstracts;

import java.util.List;

import hrms.results.DataResult;
import hrms.results.Result;
import hrms.entities.concretes.Employer;

public interface EmployerService {

	 DataResult<List<Employer>> getAll();
	Result register(Employer employer) throws Exception;
	
}
