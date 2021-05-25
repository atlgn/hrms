package hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import hrms.entities.concretes.UserActivation;

public interface UserActivationDao extends JpaRepository<UserActivation,Integer> {

}
