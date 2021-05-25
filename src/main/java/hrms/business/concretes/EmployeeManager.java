package hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.EmployeeService;
import hrms.business.abstracts.EmployeeValidationService;
import hrms.business.abstracts.UserActivationService;
import hrms.business.abstracts.UserCheckService;
import hrms.results.DataResult;
import hrms.results.ErrorResult;
import hrms.results.Result;
import hrms.results.SuccessDataResult;
import hrms.results.SuccessResult;
import hrms.dataAccess.abstracts.EmployeeDao;
import hrms.entities.concretes.Employee;

@Service
public class EmployeeManager implements EmployeeService {

	@Autowired
	private final UserActivationService userActivationService;
	@Autowired
	private final UserCheckService userCheckService;
	@Autowired
	private final EmployeeDao employeeDao;
	@Autowired
	private final EmployeeValidationService employeeValidationService;
	
	
	
	
	public EmployeeManager(UserActivationService userActivationService, UserCheckService userCheckService, EmployeeDao employeeDao,
			EmployeeValidationService employeeValidationService) {
		super();
		this.userActivationService = userActivationService;
		this.userCheckService = userCheckService;
		this.employeeDao = employeeDao;
		this.employeeValidationService=employeeValidationService;
	}

	@Override
	public Result register(Employee employee) throws Exception {
	
		List<Employee> employees = this.employeeDao.findAll();
		if (this.userCheckService.IfUserRealPerson(employee)) {
			
			if (this.userActivationService.activate()) {
				
				checkIfUserExistsBefore(employees, employee);
				if (checkIfUserExistsBefore(employees, employee).isSuccess()) {
					
					employeeValidationService.validate(employee);
					this.employeeDao.save(employee);
					return new SuccessResult("Kayıt olma işlemi başarıyla gerçekleşti.");
				}										
				
			}	
			
			return new ErrorResult("Lütfen mail adresine gönderilen aktivasyon kodu ile aktivasyon işlemini gerçekleştiriniz.");
		}
		
		return new ErrorResult("Lütfen kullanıcı bilgilerini doğru şekilde giriniz.");
	}	
	
	  private Result checkIfUserExistsBefore(List<Employee> employees, Employee checkEmployee){
	        for (Employee employee: employees) {
	            if (employee.getEmail().equals(checkEmployee.getEmail())){
	                return new ErrorResult("Bu email mevcut.");
	            }
	            if (employee.getIdentityNumber().equals(checkEmployee.getIdentityNumber())){
	                return new ErrorResult("Bu kimlik numarası zaten mevcut.");
	            }
	        }
	        return new SuccessResult();
	    }

	@Override
	public DataResult<List<Employee>> getAll() {
		 return new SuccessDataResult<>(this.employeeDao.findAll(),"Başarıyla Listelendi");
	}

}






