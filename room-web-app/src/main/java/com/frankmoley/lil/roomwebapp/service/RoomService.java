package com.frankmoley.lil.roomwebapp.service;

import com.frankmoley.lil.roomwebapp.data.entity.RoomEntity;
import com.frankmoley.lil.roomwebapp.data.repository.RoomRepository;
import com.frankmoley.lil.roomwebapp.web.model.Room;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        List<RoomEntity> entities = this.roomRepository.findAll();
        List<Room> rooms = new ArrayList<>(entities.size());
        entities.forEach(e -> rooms.add(getRoomFromEntity(e)));
        return rooms;
    }

    public Room getRoomId(UUID id) {
        Optional<RoomEntity> entity = this.roomRepository.findById(id);
        if(entity.isEmpty()) {
            return null;
        }
        else {
            return this.getRoomFromEntity(entity.get());
        }
    }

    public Room addRoom(Room room) {
        RoomEntity entity = this.getRoomEntityFromRoom(room);
        entity = this.roomRepository.save(entity);
        return this.getRoomFromEntity(entity);
    }

    public Room updateRoom(Room room) {
        RoomEntity entity = this.getRoomEntityFromRoom(room);
        entity = this.roomRepository.save(entity);
        return this.getRoomFromEntity(entity);
    }

    public void deleteRoom(UUID id){
        this.roomRepository.deleteById(id);
    }

    private Room getRoomFromEntity(RoomEntity roomEntity) {
        return new Room(roomEntity.getRoomId(), roomEntity.getName(), roomEntity.getNumber(), roomEntity.getBedInfo());
    }

    private RoomEntity getRoomEntityFromRoom(Room room) {
        return new RoomEntity(room.getId(), room.getName(), room.getNumber(), room.getInfo());
    }
}
