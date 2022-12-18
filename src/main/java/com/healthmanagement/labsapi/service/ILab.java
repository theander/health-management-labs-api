package com.healthmanagement.labsapi.service;

import com.healthmanagement.labsapi.model.Lab;

import java.util.List;

public interface ILab {
Lab createLab(Lab lab);
Lab updateLab(Lab lab);
List<Lab> getLabs(Long userId);
}
