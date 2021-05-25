package hrms.business.serviceAdapters;

import hrms.business.abstracts.UserCheckService;
import hrms.entities.concretes.Employee;


public class MernisServiceFakeAdapter implements UserCheckService {


	@Override
	public boolean IfUserRealPerson(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
