package com.rentalhive.stockManagement.services;

import com.rentalhive.stockManagement.entities.Status;

import java.util.List;

public interface StatusService {

    public List<Status> getAllStatus();

    public Status addStatus(Status status);

    public Status updateStatus(Status status);

    public void deleteStatus(Status status);
}
