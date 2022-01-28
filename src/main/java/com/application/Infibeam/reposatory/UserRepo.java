package com.application.Infibeam.reposatory;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.print.attribute.standard.DateTimeAtCompleted;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.application.Infibeam.model.Users;

@Transactional
@Repository
public interface UserRepo extends JpaRepository<Users, Integer>{
	
	 Optional<Users> findByUserName(String userName);
	 Boolean existsByUserName(String userName);
	 Boolean existsByEmailId(String emailId);
	 
	 @Modifying
	 @Query("update Users u set u.address=?2,u.mobile=?3,u.updatedAt=?4 where u.userId=?1")
	 public void updateUser(int id,String address,String mobile,Date d);
	 
	 @Modifying
	 @Query("update Users u set u.password=?2 where u.userId=?1")
	 public void changePassword(int id,String password);
	 
	 
	 @Query("select u from Users u inner join u.roles r where r.id=6")
	 public List<Users> getAllUser();
}
