package hrms.business.abstracts;

import java.util.List;

import hrms.results.DataResult;
import hrms.results.Result;
import hrms.entities.concretes.Employee;

public interface EmployeeService {

    DataResult<List<Employee>> getAll();
	Result register(Employee employee) throws Exception;
}
