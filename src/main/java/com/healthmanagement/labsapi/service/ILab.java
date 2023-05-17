package com.healthmanagement.labsapi.service;

import com.healthmanagement.labsapi.model.Lab;

import java.util.List;

public interface ILab {
    Lab createLab(Lab lab);
    Lab finishLab(Long id);
    Lab getLabsById(Long userId);
    List<Lab> getLabs(String status,String username, String name);
    Lab findById(Long id);
    List<Lab> findLabByUsername(String username);
}
