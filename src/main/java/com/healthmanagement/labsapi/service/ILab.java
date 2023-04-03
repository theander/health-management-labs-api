package com.healthmanagement.labsapi.service;

import com.healthmanagement.labsapi.model.Lab;

import java.util.List;

public interface ILab {
    Lab createLab(Lab lab);

    Lab finishLab(Long id);

    List<Lab> getLabsByUserId(Long userId);
    Lab getLabsById(Long userId);

    List<Lab> getLabs();
    Lab findById(Long id);
}
