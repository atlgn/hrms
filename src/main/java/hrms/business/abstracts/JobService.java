package hrms.business.abstracts;

import java.util.List;

import hrms.results.DataResult;
import hrms.results.Result;
import hrms.entities.concretes.Job;

public interface JobService {
 DataResult<List<Job>> getAll();
 Result add(Job job);
}
