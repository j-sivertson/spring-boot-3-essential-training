package com.frankmoley.lil.roomwebapp.web.api;

import com.frankmoley.lil.roomwebapp.service.StaffService;
import com.frankmoley.lil.roomwebapp.web.model.Room;
import com.frankmoley.lil.roomwebapp.web.model.Staff;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/staff")
public class StaffApiController {

    private final StaffService staffService;

    public StaffApiController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public List<Staff> getAll() {
        return this.staffService.getAllStaff();
    }

    @GetMapping("/{id}")
    public Staff getRoom(@PathVariable(name="id") UUID id) {
        return this.staffService.getStaffById(id);
    }

    @PostMapping
    public Staff addRoom(@RequestBody Staff staff){
        return this.staffService.addStaff(staff);
    }

    @PutMapping("{id}")
    public Staff updateRoom(@PathVariable(name="id") UUID id, @RequestBody Staff staff) {
        return this.staffService.updateStaff(staff);
    }

    @DeleteMapping("{id}")
    public void deleteRoom(@PathVariable(name="id") UUID id){
        this.staffService.deleteStaff(id);
    }
}
