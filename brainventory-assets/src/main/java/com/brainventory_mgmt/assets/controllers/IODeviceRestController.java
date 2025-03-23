package com.brainventory_mgmt.assets.controllers;

import com.brainventory_mgmt.assets.dto.ioDevice.IODeviceDTO;
import com.brainventory_mgmt.assets.dto.ioDevice.IODeviceListDTO;
import com.brainventory_mgmt.assets.dto.ioDevice.IODeviceRequestDTO;
import com.brainventory_mgmt.assets.services.interfaces.IIODeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/io-device")
public class IODeviceRestController {
    private final IIODeviceService ioDeviceService;

    public IODeviceRestController(IIODeviceService ioDeviceService){
        this.ioDeviceService = ioDeviceService;
    }

    @PostMapping
    public ResponseEntity<IODeviceRequestDTO> saveIODevice(@RequestBody IODeviceRequestDTO ioDeviceRequestDTO){
        return new ResponseEntity<>(ioDeviceService.saveIODevice(ioDeviceRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<IODeviceListDTO>> findAll(){
        return new ResponseEntity<>(ioDeviceService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IODeviceDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(ioDeviceService.findById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IODeviceRequestDTO> updateIODevice(@RequestBody IODeviceRequestDTO ioDeviceRequestDTO, @PathVariable Long id){
        return new ResponseEntity<>(ioDeviceService.updateIODevice(ioDeviceRequestDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIODevice(@PathVariable Long id){
        try{
            ioDeviceService.deleteIODevice(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
