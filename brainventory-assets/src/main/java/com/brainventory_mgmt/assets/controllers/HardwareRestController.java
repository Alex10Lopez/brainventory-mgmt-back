package com.brainventory_mgmt.assets.controllers;

import com.brainventory_mgmt.assets.dto.hardware.brand.BrandDTO;
import com.brainventory_mgmt.assets.dto.hardware.line.LineDTO;
import com.brainventory_mgmt.assets.dto.hardware.name.NameDTO;
import com.brainventory_mgmt.assets.dto.hardware.serie.SerieDTO;
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
    public ResponseEntity<List<NameDTO>> findAllITDeviceNames(){
        return new ResponseEntity<>(hardwareService.findAllITDeviceNames(), HttpStatus.OK);
    }

    @GetMapping("/io-devices")
    public ResponseEntity<List<NameDTO>> findAllIODeviceNames(){
        return new ResponseEntity<>(hardwareService.findAllIODeviceNames(), HttpStatus.OK);
    }

    @GetMapping("/brands")
    public ResponseEntity<List<BrandDTO>> findAllHardwareBrands(){
        return new ResponseEntity<>(hardwareService.findAllHardwareBrands(), HttpStatus.OK);
    }

    @GetMapping("/it-lines")
    public ResponseEntity<List<LineDTO>> findAllITDeviceLines(){
        return new ResponseEntity<>(hardwareService.findAllITDeviceLines(), HttpStatus.OK);
    }

    @GetMapping("/io-lines")
    public ResponseEntity<List<LineDTO>> findAllIODeviceLines(){
        return new ResponseEntity<>(hardwareService.findAllIODeviceLines(), HttpStatus.OK);
    }

    @GetMapping("/series")
    public ResponseEntity<List<SerieDTO>> findAllHardwareSeries(){
        return new ResponseEntity<>(hardwareService.findAllHardwareSeries(), HttpStatus.OK);
    }
}
