package com.brainventory_mgmt.assets.controllers;

import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceDTO;
import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceListDTO;
import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceReferenceDTO;
import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceRequestDTO;
import com.brainventory_mgmt.assets.services.impl.ITDeviceServiceImpl;
import com.brainventory_mgmt.assets.services.interfaces.IITDeviceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/it-device")
@RequiredArgsConstructor
public class ITDeviceRestController {
    private final IITDeviceService itDeviceService;

    @PostMapping
    public ResponseEntity<ITDeviceRequestDTO> saveITDevice(@RequestBody @Valid ITDeviceRequestDTO itDeviceRequestDTO){
        return new ResponseEntity<>(itDeviceService.saveITDevice(itDeviceRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ITDeviceListDTO>> findAll(){
        return new ResponseEntity<>(itDeviceService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ITDeviceDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(itDeviceService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ITDeviceRequestDTO> updateITDevice(@RequestBody @Valid ITDeviceRequestDTO itDeviceRequestDTO, @PathVariable Long id){
        return new ResponseEntity<>(itDeviceService.updateITDevice(itDeviceRequestDTO, id), HttpStatus.OK);
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
