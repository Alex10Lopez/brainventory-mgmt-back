package com.brainventory_mgmt.assets.services.impl;

import com.brainventory_mgmt.assets.dto.hardware.hardwareBrand.HardwareBrandDTO;
import com.brainventory_mgmt.assets.dto.hardware.hardwareLine.HardwareLineDTO;
import com.brainventory_mgmt.assets.dto.hardware.hardwareName.HardwareNameDTO;
import com.brainventory_mgmt.assets.dto.hardware.hardwareSerie.HardwareSerieDTO;
import com.brainventory_mgmt.assets.repository.IHardwareBrandRepository;
import com.brainventory_mgmt.assets.repository.IHardwareLineRepository;
import com.brainventory_mgmt.assets.repository.IHardwareNameRepository;
import com.brainventory_mgmt.assets.repository.IHardwareSeriesRepository;
import com.brainventory_mgmt.assets.services.interfaces.IHardwareService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HardwareServiceImpl implements IHardwareService {
    private final IHardwareNameRepository hardwareNameRepository;
    private final IHardwareBrandRepository hardwareBrandRepository;
    private final IHardwareLineRepository hardwareLineRepository;
    private final IHardwareSeriesRepository hardwareSeriesRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<HardwareNameDTO> findAllITDeviceNames() {
        return hardwareNameRepository.findAllITDeviceNames()
                .stream()
                .map(itDeviceName -> modelMapper.map(itDeviceName, HardwareNameDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<HardwareNameDTO> findAllIODeviceNames() {
        return hardwareNameRepository.findAllIODeviceNames()
                .stream()
                .map(ioDeviceName -> modelMapper.map(ioDeviceName, HardwareNameDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<HardwareBrandDTO> findAllHardwareBrands() {
        return hardwareBrandRepository.findAll()
                .stream()
                .map(brandName -> modelMapper.map(brandName, HardwareBrandDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<HardwareLineDTO> findAllITDeviceLines() {
        return hardwareLineRepository.findAllITDeviceLines()
                .stream()
                .map(itDeviceLine -> modelMapper.map(itDeviceLine, HardwareLineDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<HardwareLineDTO> findAllIODeviceLines() {
        return hardwareLineRepository.findAllIODeviceLines()
                .stream()
                .map(ioDeviceLine -> modelMapper.map(ioDeviceLine, HardwareLineDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<HardwareSerieDTO> findAllHardwareSeries() {
        return hardwareSeriesRepository.findAll()
                .stream()
                .map(serieName -> modelMapper.map(serieName, HardwareSerieDTO.class))
                .collect(Collectors.toList());
    }
}
