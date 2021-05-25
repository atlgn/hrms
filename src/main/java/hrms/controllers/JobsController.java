package hrms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hrms.business.abstracts.JobService;
import hrms.entities.concretes.Job;

@RestController
@RequestMapping("/api/jobs")
public class JobsController {

	private final JobService jobService;
	
	@Autowired
	public JobsController(JobService jobService) {
		super();
		this.jobService = jobService;
	}

	@GetMapping("/getall")
	public List<Job> getAll(){
		return this.jobService.getAll().getData();
	}
	
	@PostMapping("/add")
	public void add(@RequestBody Job job){
		this.jobService.add(job);
	}
	
}
