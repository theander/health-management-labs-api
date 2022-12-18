package com.healthmanagement.labsapi.repository;

import com.healthmanagement.labsapi.model.Lab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface LabRepository extends JpaRepository<Lab, Long> {
    List<Lab> findLabByUserId(Long userId);
}
