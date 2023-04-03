package com.healthmanagement.labsapi.service;

import com.healthmanagement.labsapi.model.Lab;
import com.healthmanagement.labsapi.model.Status;
import com.healthmanagement.labsapi.repository.LabRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class LabService implements ILab {
    private final LabRepository labRepository;

    @Override
    public Lab createLab(Lab lab) {
        return labRepository.save(lab);
    }

    @Override
    public Lab finishLab(Long id) {
        Lab labById = this.findById(id);
        labById.setStatus(Status.CLOSE);
        return labRepository.save(labById);
    }

    @Override
    public List<Lab> getLabsByUserId(Long userId) {
        return labRepository.findLabByUserId(userId);
    }

    @Override
    public Lab getLabsById(Long userId) {
        Optional<Lab> labRepositoryById = labRepository.findById(userId);
        if(labRepositoryById.isPresent()) {
            return labRepositoryById.get();
        }
        return null;
    }

    @Override
    public List<Lab> getLabs() {
        return labRepository.findAll();
    }

    @Override
    public Lab findById(Long id) {
        Optional<Lab> lab = labRepository.findById(id);
        if (!lab.isPresent()) {
            return null;
        }
        return lab.get();
    }
}
