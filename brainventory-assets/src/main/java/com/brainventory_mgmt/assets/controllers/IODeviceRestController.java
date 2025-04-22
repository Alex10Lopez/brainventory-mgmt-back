package com.brainventory_mgmt.assets.controllers;

import com.brainventory_mgmt.assets.dto.ioDevice.IODeviceDTO;
import com.brainventory_mgmt.assets.dto.ioDevice.IODeviceListDTO;
import com.brainventory_mgmt.assets.dto.ioDevice.IODeviceRequestDTO;
import com.brainventory_mgmt.assets.services.interfaces.IIODeviceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/io-device")
@RequiredArgsConstructor
public class IODeviceRestController {
    private final IIODeviceService ioDeviceService;

    @PostMapping
    public ResponseEntity<IODeviceRequestDTO> saveIODevice(@RequestBody @Valid IODeviceRequestDTO ioDeviceRequestDTO){
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
    public ResponseEntity<IODeviceRequestDTO> updateIODevice(@RequestBody @Valid IODeviceRequestDTO ioDeviceRequestDTO, @PathVariable Long id){
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
