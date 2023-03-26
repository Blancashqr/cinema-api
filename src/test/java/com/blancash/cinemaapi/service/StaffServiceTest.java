package com.blancash.cinemaapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.blancash.cinemaapi.models.Cinema;
import com.blancash.cinemaapi.models.Staff;
import com.blancash.cinemaapi.repos.CinemaRepo;
import com.blancash.cinemaapi.repos.MovieRepo;
import com.blancash.cinemaapi.repos.StaffRepo;
import com.blancash.cinemaapi.service.StaffService;
import com.blancash.cinemaapi.service.exception.CinemaNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class StaffServiceTest {

  @Mock
  StaffRepo staffRepo;

  @Mock
  CinemaRepo cinemaRepo;

  StaffService staffService;

  @BeforeEach
  void setup() {
    staffRepo = mock(StaffRepo.class);
    cinemaRepo = mock(CinemaRepo.class);
    staffService = new StaffService(staffRepo, cinemaRepo);
  }

  @Test
  void checkGetAllStaff() {

    Staff staff = new Staff(1, "Lucia", 25000, new Cinema());
    List<Staff> staffList = List.of(staff);

    doReturn(staffList).when(staffRepo).findAll();

    staffService.getAllStaff();

    verify(staffRepo).findAll();
  }

  @Test
  void checkGetStaffById() {

    int id = 1;
    Staff staff = new Staff(1, "Lucia", 25000, new Cinema());

    doReturn(staff).when(staffRepo).findStaffById(id);

    Staff result = staffService.getStaffById(id);

    assertEquals(1, result.getId());

    verify(staffRepo).findStaffById(id);

  }

  @Test
  void checkCreateNewStaffCinemaFound() throws CinemaNotFoundException {

    int cinemaId = 1;
    String staffName = "Blanca";
    int staffSalary = 30000;

    Cinema cinema = new Cinema(1, "metromar", new HashSet<>(), new ArrayList<>());
    Staff staff = new Staff(1,staffName, staffSalary, cinema);

    doReturn(cinema).when(cinemaRepo).findCinemaById(anyInt());
    doReturn(staff).when(staffRepo).save(any());

    Staff result = staffService.createNewStaff(cinemaId, staffName, staffSalary);

    assertEquals(1, cinema.getStaffList().get(0).getId());
    assertEquals("Blanca", result.getName());
    assertEquals(1, result.getCinema().getId());

  }

}
