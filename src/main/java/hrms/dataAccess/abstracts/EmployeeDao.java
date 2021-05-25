package hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import hrms.results.Result;
import hrms.entities.concretes.Employee;

public interface EmployeeDao extends JpaRepository<Employee,Integer> {

	
}
