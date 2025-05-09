package com.brainventory_mgmt.infrastructure.services.impl;

import com.brainventory_mgmt.infrastructure.dto.room.RoomDTO;
import com.brainventory_mgmt.infrastructure.dto.room.RoomReferenceDTO;
import com.brainventory_mgmt.infrastructure.dto.room.RoomRequestDTO;
import com.brainventory_mgmt.infrastructure.models.room.RoomEntity;
import com.brainventory_mgmt.infrastructure.repository.IRoomRepository;
import com.brainventory_mgmt.infrastructure.services.intefaces.IRoomService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements IRoomService {
    private final IRoomRepository roomRepository;
    private final ModelMapper modelMapper;

    @Override
    public RoomRequestDTO saveRoom(RoomRequestDTO roomRequestDTO, MultipartFile image) {
        try {
            RoomEntity room = modelMapper.map(roomRequestDTO, RoomEntity.class);

            if (image != null && !image.isEmpty()) {
                String folderPath = "C:/Users/lopez/Documents/UAEH_LCA/9_Noveno_Semestre/Proyectos_Computacionales/brainventory-mgmt/images/infrastructure/rooms/";
                String filename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                Path path = Paths.get(folderPath + filename);
                Files.createDirectories(path.getParent());
                Files.write(path, image.getBytes());
                room.setImage("/images/infrastructure/rooms/" + filename);
            }

            RoomEntity savedRoom = roomRepository.save(room);

            return modelMapper.map(savedRoom, RoomRequestDTO.class);
        } catch (Exception e){
            throw  new UnsupportedOperationException("Error saving the room!");
        }
    }

    @Override
    public List<RoomDTO> findAll() {
        return roomRepository.findAll()
                .stream()
                .map(room -> modelMapper.map(room, RoomDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDTO findById(Long id) {
        RoomEntity room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        return modelMapper.map(room, RoomDTO.class);
    }

    @Override
    public RoomRequestDTO updateRoom(RoomRequestDTO roomRequestDTO, MultipartFile image, Long id) {
        RoomEntity existingRoom = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        RoomEntity updatedRoom = modelMapper.map(roomRequestDTO, RoomEntity.class);
        updatedRoom.setId(id);

        if (image != null && !image.isEmpty()) {
            try {
                String folderPath = "C:/Users/lopez/Documents/UAEH_LCA/9_Noveno_Semestre/Proyectos_Computacionales/brainventory-mgmt/images/infrastructure/rooms/";
                String filename = System.currentTimeMillis() + "_" + image.getOriginalFilename();
                Path path = Paths.get(folderPath + filename);
                Files.createDirectories(path.getParent());
                Files.write(path, image.getBytes());
                updatedRoom.setImage("/images/infrastructure/rooms/" + filename);

                if (existingRoom.getImage() != null && !existingRoom.getImage().isBlank()) {
                    String basePath = "C:/Users/lopez/Documents/UAEH_LCA/9_Noveno_Semestre/Proyectos_Computacionales/brainventory-mgmt";
                    Path oldImagePath = Paths.get(basePath + existingRoom.getImage());
                    Files.deleteIfExists(oldImagePath);
                }
            } catch (Exception e) {
                throw new RuntimeException("Error updating room image: " + e.getMessage());
            }
        } else {
            updatedRoom.setImage(existingRoom.getImage());
        }

        RoomEntity savedRoom = roomRepository.save(updatedRoom);

        return modelMapper.map(savedRoom, RoomRequestDTO.class);
    }

    @Override
    public void deleteRoom(Long id) {
        RoomEntity room = roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        if (room.getImage() != null && !room.getImage().isBlank()) {
            String basePath = "C:/Users/lopez/Documents/UAEH_LCA/9_Noveno_Semestre/Proyectos_Computacionales/brainventory-mgmt";
            Path imagePath = Paths.get(basePath + room.getImage());
            try {
                Files.deleteIfExists(imagePath);
            } catch (Exception e) {
                throw new RuntimeException("Error deleting room image: " + e.getMessage());
            }
        }

        roomRepository.deleteById(id);
    }

    @Override
    public List<RoomReferenceDTO> findAllRooms(){
        return roomRepository.findAll()
                .stream()
                .map(room -> modelMapper.map(room, RoomReferenceDTO.class))
                .collect(Collectors.toList());
    }
}
