package hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.EmployerService;
import hrms.business.abstracts.EmployerValidationService;
import hrms.business.abstracts.UserActivationService;
import hrms.results.DataResult;
import hrms.results.ErrorResult;
import hrms.results.Result;
import hrms.results.SuccessDataResult;
import hrms.results.SuccessResult;
import hrms.dataAccess.abstracts.EmployerDao;
import hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService {

	private final UserActivationService userActivationService;
	private final EmployerDao employerDao;
	private EmployerValidationService employerValidationService;
	
	@Autowired
	public EmployerManager(UserActivationService userActivationService, EmployerDao employerDao) {
		super();
		this.userActivationService = userActivationService;
		this.employerDao = employerDao;
	}


	@Override
	public Result register(Employer employer) throws Exception {
	
		List<Employer> employers = this.employerDao.findAll();
		
		if (checkIfUserExistsBefore(employers, employer).isSuccess()) {
			
			if (this.userActivationService.activate()) {
				this.employerValidationService.validate(employer);
				this.employerDao.save(employer);
				return new SuccessResult("Kayıt işlemi başarıyla gerçekleşti.");
			}
		}
		
		else {
			return new ErrorResult("Bu email mevcut.");
		}
				
		
		return new ErrorResult("Lütfen hesabınızı mailinize gönderilen link yardımı ile aktive ediniz");
		
	}	
	
	 private Result checkIfUserExistsBefore(List<Employer> employers, Employer checkEmployer){
	        for (Employer employer: employers) {
	            if (employer.getEmail().equals(checkEmployer.getEmail())){
	                return new ErrorResult("Bu email mevcut.");
	            }	          
	        }
	        return new SuccessResult();
	    }


	@Override
	public DataResult<List<Employer>> getAll() {
		
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll(),"Başarıyla listelendi.");
	}

}
