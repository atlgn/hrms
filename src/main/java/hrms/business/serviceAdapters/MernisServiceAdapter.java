package hrms.business.serviceAdapters;



import java.rmi.RemoteException;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.UserCheckService;
import hrms.entities.concretes.Employee;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class MernisServiceAdapter implements UserCheckService{

	
	@Override
	public boolean IfUserRealPerson(Employee employee) {
		KPSPublicSoapProxy proxy =new KPSPublicSoapProxy();
		boolean result = false;
		
		try {
			result = proxy.TCKimlikNoDogrula(Long.parseLong(employee.getIdentityNumber()), employee.getFirstName().toUpperCase() , employee.getLastName().toUpperCase(),employee.getDateOfBirth().getYear());
			
		} catch (NumberFormatException | RemoteException e) {
			System.out.println("Not a valid user");
		}
		
		return result;
	}
	
	
	
}
