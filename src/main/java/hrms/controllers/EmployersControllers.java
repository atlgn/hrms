package hrms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.EmployerService;
import hrms.entities.concretes.Employee;
import hrms.entities.concretes.Employer;

@RestController
@RequestMapping("/api/employers")
public class EmployersControllers {

	private final EmployerService employerService;
	
	@Autowired
	public EmployersControllers(EmployerService employerService) {
		super();
		this.employerService = employerService;
	}

	@GetMapping("/getall")
	public List<Employer> getAll(){
		return this.employerService.getAll().getData();
	}
	
	@PostMapping("/add")
	public void add(@RequestBody Employer employer) throws Exception{
		this.employerService.register(employer);
	
}
}
