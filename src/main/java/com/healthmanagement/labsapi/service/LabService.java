package com.healthmanagement.labsapi.service;

import com.healthmanagement.labsapi.model.Lab;
import com.healthmanagement.labsapi.repository.LabRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Service
@RequiredArgsConstructor
public class LabService implements ILab{
    private final LabRepository labRepository;
    @Override
    public Lab createLab(Lab lab) {
        return labRepository.save(lab);
    }

    @Override
    public Lab updateLab(Lab lab) {
        return labRepository.save(lab);
    }

    @Override
    public List<Lab> getLabs(Long userId) {
        return labRepository.findLabByUserId(userId);
    }
}
