package com.brainventory_mgmt.assets.controllers;

import com.brainventory_mgmt.assets.dto.hardware.hardwareBrand.HardwareBrandDTO;
import com.brainventory_mgmt.assets.dto.hardware.hardwareLine.HardwareLineDTO;
import com.brainventory_mgmt.assets.dto.hardware.hardwareName.HardwareNameDTO;
import com.brainventory_mgmt.assets.dto.hardware.hardwareSerie.HardwareSerieDTO;
import com.brainventory_mgmt.assets.services.interfaces.IHardwareService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/hardware")
@RequiredArgsConstructor
public class HardwareRestController {
    private final IHardwareService hardwareService;

    @GetMapping("/it-devices")
    public ResponseEntity<List<HardwareNameDTO>> findAllITDeviceNames(){
        return new ResponseEntity<>(hardwareService.findAllITDeviceNames(), HttpStatus.OK);
    }

    @GetMapping("/io-devices")
    public ResponseEntity<List<HardwareNameDTO>> findAllIODeviceNames(){
        return new ResponseEntity<>(hardwareService.findAllIODeviceNames(), HttpStatus.OK);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<HardwareBrandDTO>> findAllHardwareBrands(){
        return new ResponseEntity<>(hardwareService.findAllHardwareBrands(), HttpStatus.OK);
    }

    @GetMapping("/it-lines")
    public ResponseEntity<List<HardwareLineDTO>> findAllITDeviceLines(){
        return new ResponseEntity<>(hardwareService.findAllITDeviceLines(), HttpStatus.OK);
    }

    @GetMapping("/io-lines")
    public ResponseEntity<List<HardwareLineDTO>> findAllIODeviceLines(){
        return new ResponseEntity<>(hardwareService.findAllIODeviceLines(), HttpStatus.OK);
    }

    @GetMapping("/series")
    public ResponseEntity<List<HardwareSerieDTO>> findAllHardwareSeries(){
        return new ResponseEntity<>(hardwareService.findAllHardwareSeries(), HttpStatus.OK);
    }
}
