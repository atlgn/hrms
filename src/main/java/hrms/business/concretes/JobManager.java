package hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hrms.business.abstracts.JobService;
import hrms.results.DataResult;
import hrms.results.Result;
import hrms.results.SuccessDataResult;
import hrms.results.SuccessResult;
import hrms.dataAccess.abstracts.JobDao;
import hrms.entities.concretes.Job;

@Service
public class JobManager implements JobService {

	private JobDao jobDao;
	
	@Autowired
	public JobManager(JobDao jobDao) {
		super();
		this.jobDao = jobDao;
	}
	
	@Override
	public DataResult<List<Job>> getAll() {
		return new SuccessDataResult<List<Job>>(this.jobDao.findAll(),"Başarıyla listelendi.");
	}

	@Override
	public Result add(Job job) {
	
		this.jobDao.save(job);
		return new SuccessResult("Başarıyla eklendi.");
	}

}
