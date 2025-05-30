package com.brainventory_mgmt.infrastructure.controllers;

import com.brainventory_mgmt.infrastructure.dto.building.BuildingDTO;
import com.brainventory_mgmt.infrastructure.dto.building.BuildingListDTO;
import com.brainventory_mgmt.infrastructure.dto.building.BuildingReferenceDTO;
import com.brainventory_mgmt.infrastructure.dto.building.BuildingRequestDTO;
import com.brainventory_mgmt.infrastructure.services.intefaces.IBuildingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/building")
@RequiredArgsConstructor
public class BuildingRestController {
    private final IBuildingService buildingService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<BuildingRequestDTO> saveBuilding(@RequestPart("building") @Valid BuildingRequestDTO buildingRequestDTO,
                                                    @RequestPart(value = "image", required = false) MultipartFile image){
        return new ResponseEntity<>(buildingService.saveBuilding(buildingRequestDTO, image), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BuildingListDTO>> findAll(){
        return new ResponseEntity<>(buildingService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuildingDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(buildingService.findById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<BuildingRequestDTO> updateBuilding(@RequestPart("building") @Valid BuildingRequestDTO buildingRequestDTO,
                                                      @RequestPart(value = "image", required = false) MultipartFile image,
                                                      @PathVariable Long id){
        return new ResponseEntity<>(buildingService.updateBuilding(buildingRequestDTO, image, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuilding(@PathVariable Long id){
        try {
            buildingService.deleteBuilding(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/references")
    public ResponseEntity<List<BuildingReferenceDTO>> findAllBuildings(){
        return new ResponseEntity<>(buildingService.findAllBuildings(), HttpStatus.OK);
    }
}
