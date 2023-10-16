package com.healthmanagement.labsapi.service;

import com.healthmanagement.labsapi.model.Lab;
import com.healthmanagement.labsapi.model.Status;
import com.healthmanagement.labsapi.repository.LabRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Month;
import java.util.*;
import java.util.stream.Stream;

@Slf4j
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
        Lab labById = labRepository.findById(id).get();
        labById.setStatus(Status.CLOSE);
        return labRepository.save(labById);
    }

    @Override
    public Lab getLabsById(Long Id) {
        Optional<Lab> labRepositoryById = labRepository.findById(Id);
        if (labRepositoryById.isPresent()) {
            return labRepositoryById.get();
        }
        return null;
    }

    @Override
    public List<Lab> getLabs(String status, String username, String name) {

        if (username == null && name == null) {
            return labRepository.findByStatusEquals(Status.valueOf(status));
        }

        if (name.isEmpty()) {

            return labRepository.findAllByStatusEqualsAndUsernameEquals(Status.valueOf(status), username);
        }
        return labRepository.findAllByStatusEqualsAndNameEquals(Status.valueOf(status), name);
    }

    @Override
    public Lab findById(Long id) {
        Optional<Lab> lab = labRepository.findById(id);
        if (!lab.isPresent()) {
            return null;
        }
        return lab.get();
    }

    @Override
    public List<Lab> findLabByUsername(String username) {
        List<Lab> lab = labRepository.findByUsernameEqualsAndStatusEquals(username, Status.CLOSE);
        if (lab == null) {
            return null;
        }
        return lab;
    }

    @Override
    public Map<Integer, Integer> countLabsByMonth() {
        Map<Integer,Integer> map = new HashMap<>();
        Stream<Month> stream = Arrays.stream(Month.values());
        stream.forEach(month -> {
            int o = labRepository.countForMonth(month.getValue());
            map.put(month.getValue(),o);
        });
        return map;
    }
}
