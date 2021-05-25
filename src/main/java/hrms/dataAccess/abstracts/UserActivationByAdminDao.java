package hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import hrms.entities.concretes.UserActivationsByAdmin;

public interface UserActivationByAdminDao extends JpaRepository<UserActivationsByAdmin,Integer> {

}
