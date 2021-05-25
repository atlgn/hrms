package hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import hrms.entities.concretes.Admin;

public interface AdminDao extends JpaRepository<Admin,Integer> {

}
