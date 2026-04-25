package com.mathsmastery.platform.service;

import com.mathsmastery.platform.model.Sibling;
import com.mathsmastery.platform.model.SiblingGroup;
import com.mathsmastery.platform.repository.SiblingGroupRepository;
import com.mathsmastery.platform.repository.SiblingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiblingService {

    private final SiblingRepository siblingRepository;
    private final SiblingGroupRepository siblingGroupRepository;

    public SiblingService(
            SiblingRepository siblingRepository,
            SiblingGroupRepository siblingGroupRepository
    ) {
        this.siblingRepository = siblingRepository;
        this.siblingGroupRepository = siblingGroupRepository;
    }

    public SiblingGroup createGroup(SiblingGroup group) {
        return siblingGroupRepository.save(group);
    }

    public Sibling addSibling(Sibling sibling) {
        return siblingRepository.save(sibling);
    }

    public List<SiblingGroup> getAllGroups() {
        return siblingGroupRepository.findAll();
    }

    public List<Sibling> getAllSiblings() {
        return siblingRepository.findAll();
    }

    public List<Sibling> getByStudent(Long studentId) {
        return siblingRepository.findByStudentId(studentId);
    }
}
