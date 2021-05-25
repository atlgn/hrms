package hrms.business.concretes;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import hrms.business.abstracts.EmployeeValidationService;
import hrms.business.consts.Messages;
import hrms.results.ErrorResult;
import hrms.results.Result;
import hrms.results.SuccessResult;
import hrms.tools.RunTools;
import hrms.entities.concretes.Employee;

@Service
public class EmployeeValidationManager implements EmployeeValidationService {

	public EmployeeValidationManager() 
	{
		
	}
	
	@Override
	public void validate(Employee employee) throws Exception {
		// TODO Auto-generated method stub

		Result[] results = RunTools.runValidationRules(this.checkFirstNameNotNull(employee.getFirstName()),
				this.checkLastNameNotNull(employee.getLastName()), this.checkEmailNotNull(employee.getEmail()),
				this.checkPasswordNotNull(employee.getPassword()), this.checkPasswordMinLength(employee.getPassword()),
				this.checkEmailIsValid(employee.getEmail()), this.checkFirstNameMinLength(employee.getFirstName()),
				this.checkLastNameMinLength(employee.getLastName()));
		for (Result result : results) {
			if (!result.isSuccess()) {
				throw new Exception(result.getMessage());
			}
		}
	}
		
		private Result checkFirstNameNotNull(String firstName) {
			if (firstName.isEmpty() || firstName == null) {
				return new ErrorResult(Messages.firstNameCanNotBeNullOrEmpty);
			}
			return new SuccessResult();
		}

		private Result checkLastNameNotNull(String lastName) {
			if (lastName.isEmpty() || lastName == null) {
				return new ErrorResult(Messages.lastNameCanNotBeNullOrEmpty);
			}
			return new SuccessResult();
		}

		private Result checkEmailNotNull(String email) {
			if (email.isEmpty() || email == null) {
				return new ErrorResult(Messages.emailCanNotBeNullOrEmpty);
			}
			return new SuccessResult();
		}

		private Result checkPasswordNotNull(String password) {
			if (password.isEmpty() || password == null) {
				return new ErrorResult(Messages.passwordCanNotBeNullOrEmpty);
			}
			return new SuccessResult();
		}

		private Result checkPasswordMinLength(String password) {
			if (password.length() < 6) {
				return new ErrorResult(Messages.passwordLeastCharacter.replace("`n`", "6"));
			}
			return new SuccessResult();
		}

		private Result checkEmailIsValid(String email) {
			String regexString = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
			if (!Pattern.compile(regexString).matcher(email).matches()) {
				return new ErrorResult(Messages.emailNotValid);
			}
			return new SuccessResult();
		}

		private Result checkFirstNameMinLength(String firstName) {
			if (firstName.length() < 2) {
				return new ErrorResult(Messages.firstNameLeastCharacter);
			}
			return new SuccessResult();
		}

		private Result checkLastNameMinLength(String lastName) {
			if (lastName.length() < 2) {
				return new ErrorResult(Messages.lastNameLeastCharacter);
			}
			return new SuccessResult();
		}

}
