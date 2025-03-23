package com.brainventory_mgmt.assets.controllers;

import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceDTO;
import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceListDTO;
import com.brainventory_mgmt.assets.dto.itDevice.ITDeviceRequestDTO;
import com.brainventory_mgmt.assets.services.impl.ITDeviceServiceImpl;
import com.brainventory_mgmt.assets.services.interfaces.IITDeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/it-device")
public class ITDeviceRestController {
    private final IITDeviceService itDeviceService;

    public ITDeviceRestController(IITDeviceService itDeviceService){
        this.itDeviceService = itDeviceService;
    }

    @PostMapping
    public ResponseEntity<ITDeviceRequestDTO> saveITDevice(@RequestBody ITDeviceRequestDTO itDeviceRequestDTO){
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
    public ResponseEntity<ITDeviceRequestDTO> updateITDevice(@RequestBody ITDeviceRequestDTO itDeviceRequestDTO, @PathVariable Long id){
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
}
