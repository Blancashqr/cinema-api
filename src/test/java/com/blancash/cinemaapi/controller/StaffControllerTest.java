package com.blancash.cinemaapi.controller;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.blancash.cinemaapi.models.Cinema;
import com.blancash.cinemaapi.models.Staff;
import com.blancash.cinemaapi.service.StaffService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class StaffControllerTest {

  @Mock
  StaffService staffService;

  StaffController staffController;

  @BeforeEach
  void setup() {
    staffService = mock(StaffService.class);
    staffController = new StaffController(staffService);
  }

  @Test
  void checkGetAllStaff() {

    Staff staff = new Staff(1, "Lucia", 25000, new Cinema());
    List<Staff> staffList = List.of(staff);

    doReturn(staffList).when(staffService).getAllStaff();

    staffController.getAllStaff();

    verify(staffService).getAllStaff();

  }

  @Test
  void checkGetStaffById() {

    int id = 1;
    Staff staff = new Staff(1, "Lucia", 25000, new Cinema());

    doReturn(staff).when(staffService).getStaffById(id);

    staffController.getStaffById(id);

    verify(staffService).getStaffById(id);

  }




}
