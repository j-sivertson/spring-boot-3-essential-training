package com.frankmoley.lil.roomwebapp.web.controller;

import com.frankmoley.lil.roomwebapp.data.entity.StaffMember;
import com.frankmoley.lil.roomwebapp.data.repository.StaffRepository;
import com.frankmoley.lil.roomwebapp.web.model.Room;
import com.frankmoley.lil.roomwebapp.web.model.Staff;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {

    private final StaffRepository staffRepository;

    public StaffController(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public StaffRepository getRoomRepository() {
        return staffRepository;
    }

    @GetMapping
    public String getRoomsPage(Model model){
        List<StaffMember> staffMembers = this.staffRepository.findAll();
        List<Staff> members = new ArrayList<>(staffMembers.size());
        staffMembers.forEach(e -> members.add(new Staff(e.getEmployeeId(), e.getFirstName(), e.getLastName(), e.getPosition() )));
        model.addAttribute("staffMembers", members);
        return "staffMembers";
    }
}
