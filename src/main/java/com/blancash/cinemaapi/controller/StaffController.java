package com.blancash.cinemaapi.controller;

import com.blancash.cinemaapi.models.Staff;
import com.blancash.cinemaapi.service.StaffService;
import com.blancash.cinemaapi.service.exception.CinemaNotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StaffController {

  private final StaffService staffService;

  @Autowired
  public StaffController(StaffService staffService) {
    this.staffService = staffService;
  }

  @GetMapping("/staff")
  public List<Staff> getAllStaff() {
    return staffService.getAllStaff();
  }

  @GetMapping("/staff/{id}")
  public Staff getStaffById(@PathVariable int id) {
    return staffService.getStaffById(id);
  }

  @PostMapping("/new/staff/{cinemaId}")
  public Staff createNewStaff(@PathVariable int cinemaId, @RequestParam String staffName, @RequestParam int staffSalary)
      throws CinemaNotFoundException {
    return staffService.createNewStaff(cinemaId, staffName, staffSalary);
  }

}


