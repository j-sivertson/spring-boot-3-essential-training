package com.frankmoley.lil.roomwebapp.web.api;

import com.frankmoley.lil.roomwebapp.service.RoomService;
import com.frankmoley.lil.roomwebapp.web.model.Room;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/rooms")
public class RoomApiController {

    private final RoomService roomService;

    public RoomApiController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAll() {
        return this.roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable(name="id") UUID id) {
        return this.roomService.getRoomById(id);
    }

    @PostMapping
    public Room addRoom(@RequestBody Room room){
        return this.roomService.addRoom(room);
    }

    @PutMapping("{id}")
    public Room updateRoom(@PathVariable(name="id") UUID id, @RequestBody Room room) {
        return this.roomService.updateRoom(room);
    }

    @DeleteMapping("{id}")
    public void deleteRoom(@PathVariable(name="id") UUID id){
        this.roomService.deleteRoom(id);
    }




}
