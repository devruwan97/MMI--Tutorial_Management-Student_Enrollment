package com.mathsmastery.platform.service;

import com.mathsmastery.platform.model.Sibling;
import com.mathsmastery.platform.model.SiblingRequest;
import com.mathsmastery.platform.model.User;
import com.mathsmastery.platform.repository.SiblingRepository;
import com.mathsmastery.platform.repository.SiblingRequestRepository;
import com.mathsmastery.platform.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiblingService {

    private final SiblingRepository siblingRepository;
    private final SiblingRequestRepository requestRepository;
    private final NotificationService notificationService;
    private final UserRepository userRepository;

    public SiblingService(
            SiblingRepository siblingRepository,
            SiblingRequestRepository requestRepository,
            NotificationService notificationService,
            UserRepository userRepository
    ) {
        this.siblingRepository = siblingRepository;
        this.requestRepository = requestRepository;
        this.notificationService = notificationService;
        this.userRepository = userRepository;
    }

    public SiblingRequest createRequest(SiblingRequest request) {

        request.setStatus("PENDING");
        SiblingRequest savedRequest = requestRepository.save(request);

        List<User> admins = userRepository.findByRole(User.Role.admin);

        for (User admin : admins) {
            notificationService.createNotification(
                    savedRequest.getRequesterStudentId(),
                    "New sibling request submitted by Student ID: "
                            + savedRequest.getRequesterStudentId()
            );
        }

        return savedRequest;
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

        notificationService.createNotification(
                req.getRequesterStudentId(),
                "Your sibling request has been APPROVED."
        );
    }

    public void rejectRequest(Long requestId) {

        SiblingRequest req = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        req.setStatus("REJECTED");
        requestRepository.save(req);

        notificationService.createNotification(
                req.getRequesterStudentId(),
                "Your sibling request has been REJECTED."
        );
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