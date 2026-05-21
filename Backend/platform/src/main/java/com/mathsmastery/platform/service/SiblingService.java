package com.mathsmastery.platform.service;

import com.mathsmastery.platform.model.Sibling;
import com.mathsmastery.platform.model.SiblingRequest;
import com.mathsmastery.platform.repository.SiblingRepository;
import com.mathsmastery.platform.repository.SiblingRequestRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiblingService {

    private final SiblingRepository siblingRepository;
    private final SiblingRequestRepository requestRepository;

    public SiblingService(
            SiblingRepository siblingRepository,
            SiblingRequestRepository requestRepository
    ) {
        this.siblingRepository = siblingRepository;
        this.requestRepository = requestRepository;
    }

    public SiblingRequest createRequest(SiblingRequest request) {
        request.setStatus("PENDING");
        return requestRepository.save(request);
    }

    public void approveRequest(Long requestId) {

        SiblingRequest req = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        req.setStatus("APPROVED");
        requestRepository.save(req);

        Sibling s1 = new Sibling();
        s1.setStudentId(req.getRequesterStudentId());
        s1.setSiblingId(req.getTargetStudentId());
        s1.setStatus("ACTIVE");

        Sibling s2 = new Sibling();
        s2.setStudentId(req.getTargetStudentId());
        s2.setSiblingId(req.getRequesterStudentId());
        s2.setStatus("ACTIVE");

        siblingRepository.save(s1);
        siblingRepository.save(s2);
    }

    public void rejectRequest(Long requestId) {
        SiblingRequest req = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        req.setStatus("REJECTED");
        requestRepository.save(req);
    }

    public List<Sibling> getByStudent(Long studentId) {
        return siblingRepository.findByStudentId(studentId);
    }

    public List<Sibling> getAllSiblings() {
        return siblingRepository.findAll();
    }

    public List<SiblingRequest> getAllSiblingsRequests() {
        return requestRepository.findAll();
    }

}