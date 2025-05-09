package com.brainventory_mgmt.assets.controllers;

import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceDTO;
import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceListDTO;
import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceReferenceDTO;
import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceRequestDTO;
import com.brainventory_mgmt.assets.services.interfaces.IITDeviceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/it-device")
@RequiredArgsConstructor
public class ITDeviceRestController {
    private final IITDeviceService itDeviceService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<ITDeviceRequestDTO> saveITDevice(@RequestPart("itDevice") @Valid ITDeviceRequestDTO itDeviceRequestDTO,
                                                           @RequestPart(value = "image", required = false) MultipartFile image){
        return new ResponseEntity<>(itDeviceService.saveITDevice(itDeviceRequestDTO, image), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ITDeviceListDTO>> findAll(){
        return new ResponseEntity<>(itDeviceService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ITDeviceDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(itDeviceService.findById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", consumes = {"multipart/form-data"})
    public ResponseEntity<ITDeviceRequestDTO> updateITDevice(@RequestPart("itDevice") @Valid ITDeviceRequestDTO itDeviceRequestDTO,
                                                             @RequestPart(value = "image", required = false) MultipartFile image,
                                                             @PathVariable Long id){
        return new ResponseEntity<>(itDeviceService.updateITDevice(itDeviceRequestDTO, image, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteITDevice(@PathVariable Long id){
        try{
            itDeviceService.deleteITDevice(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/references")
    public ResponseEntity<List<ITDeviceReferenceDTO>> findAllITDevice(){
        return new ResponseEntity<>(itDeviceService.findAllITDevices(), HttpStatus.OK);
    }
}
