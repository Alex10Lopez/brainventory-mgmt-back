package com.brainventory_mgmt.human_resources.controllers;

import com.brainventory_mgmt.human_resources.dto.jobRole.JobRoleReferenceDTO;
import com.brainventory_mgmt.human_resources.services.interfaces.IJobRoleService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/job-role")
@RequiredArgsConstructor
public class JobRoleRestController {
    private final IJobRoleService jobRoleService;

    @GetMapping("/references")
    public ResponseEntity<List<JobRoleReferenceDTO>> findAllReferences(){
        return new ResponseEntity<>(jobRoleService.findAllReferences(), HttpStatus.OK);
    }
}
