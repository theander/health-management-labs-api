package com.healthmanagement.labsapi.repository;

import com.healthmanagement.labsapi.model.Lab;
import com.healthmanagement.labsapi.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LabRepository extends JpaRepository<Lab, Long> {
    List<Lab> findAllByStatusEqualsAndUsernameEquals(Status status,String username);
    List<Lab> findAllByStatusEqualsAndNameEquals(Status status,String name);
    List<Lab> findByUsernameEqualsAndStatusEquals(String username,Status status);
}
