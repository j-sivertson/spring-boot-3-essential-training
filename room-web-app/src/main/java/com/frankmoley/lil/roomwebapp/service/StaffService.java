package com.frankmoley.lil.roomwebapp.service;

import com.frankmoley.lil.roomwebapp.data.entity.Position;
import com.frankmoley.lil.roomwebapp.data.entity.StaffMember;
import com.frankmoley.lil.roomwebapp.data.repository.StaffRepository;
import com.frankmoley.lil.roomwebapp.web.model.Staff;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> getAllStaff() {
        List<StaffMember> staffMembers = this.staffRepository.findAll();
        List<Staff> staffList = new ArrayList<>(staffMembers.size());
        staffMembers.forEach(e-> staffList.add(getStaffFromStaffMembers(e)));
        return staffList;
    }

    public Staff getStaffById(UUID id){
        Optional<StaffMember> entity = this.staffRepository.findById(id);
        if(entity.isEmpty()){
            return null;
        }else{
            return this.getStaffFromStaffMembers(entity.get());
        }
    }

    public Staff addStaff(Staff staff){
        StaffMember entity = this.getStaffEntityFromStaff(staff);
        entity = this.staffRepository.save(entity);
        return this.getStaffFromStaffMembers(entity);
    }

    public Staff updateStaff(Staff staff){
        StaffMember entity = this.getStaffEntityFromStaff(staff);
        entity = this.staffRepository.save(entity);
        return this.getStaffFromStaffMembers(entity);
    }

    public void deleteStaff(UUID id){
        this.staffRepository.deleteById(id);
    }

    private Staff getStaffFromStaffMembers(StaffMember staffMember){
        return new Staff(staffMember.getEmployeeId(), staffMember.getFirstName(), staffMember.getLastName(), staffMember.getPosition().toString());
    }

    private StaffMember getStaffEntityFromStaff(Staff staff){
        return new StaffMember(staff.getId(), staff.getFirstName(), staff.getLastName(), Position.valueOf(staff.getPosition()));
    }
}
