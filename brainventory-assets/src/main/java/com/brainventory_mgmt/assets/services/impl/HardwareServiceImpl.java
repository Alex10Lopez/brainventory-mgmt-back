package com.brainventory_mgmt.assets.services.impl;

import com.brainventory_mgmt.assets.dto.hardware.brand.BrandDTO;
import com.brainventory_mgmt.assets.dto.hardware.line.LineDTO;
import com.brainventory_mgmt.assets.dto.hardware.name.NameDTO;
import com.brainventory_mgmt.assets.dto.hardware.serie.SerieDTO;
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
    public List<NameDTO> findAllITDeviceNames() {
        return hardwareNameRepository.findAllITDeviceNames()
                .stream()
                .map(itDeviceName -> modelMapper.map(itDeviceName, NameDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<NameDTO> findAllIODeviceNames() {
        return hardwareNameRepository.findAllIODeviceNames()
                .stream()
                .map(ioDeviceName -> modelMapper.map(ioDeviceName, NameDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BrandDTO> findAllHardwareBrands() {
        return hardwareBrandRepository.findAll()
                .stream()
                .map(brandName -> modelMapper.map(brandName, BrandDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<LineDTO> findAllITDeviceLines() {
        return hardwareLineRepository.findAllITDeviceLines()
                .stream()
                .map(itDeviceLine -> modelMapper.map(itDeviceLine, LineDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<LineDTO> findAllIODeviceLines() {
        return hardwareLineRepository.findAllIODeviceLines()
                .stream()
                .map(ioDeviceLine -> modelMapper.map(ioDeviceLine, LineDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<SerieDTO> findAllHardwareSeries() {
        return hardwareSeriesRepository.findAll()
                .stream()
                .map(serieName -> modelMapper.map(serieName, SerieDTO.class))
                .collect(Collectors.toList());
    }
}
