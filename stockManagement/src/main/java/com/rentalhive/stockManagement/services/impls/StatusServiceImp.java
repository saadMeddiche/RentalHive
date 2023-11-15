package com.rentalhive.stockManagement.services.impls;

import com.rentalhive.stockManagement.entities.Status;
import com.rentalhive.stockManagement.repositories.StatusRepository;
import com.rentalhive.stockManagement.services.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StatusServiceImp implements StatusService {

    private StatusRepository statusRepository;
    public List<Status> getAllStatus() {
        return null;
    }
    public Optional<Status> findById(Long id) {
        return Optional.empty();
    }

    public Status addStatus(Status status) {
        return null;
    }

    public Status updateStatus(Status status) {
        return null;
    }

    public void deleteStatus(Status status) {

    }
}
