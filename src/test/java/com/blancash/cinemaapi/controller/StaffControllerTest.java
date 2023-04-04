package com.blancash.cinemaapi.controller;

import com.blancash.cinemaapi.models.Cinema;
import com.blancash.cinemaapi.models.Staff;
import com.blancash.cinemaapi.service.StaffService;
import com.blancash.cinemaapi.service.exception.CinemaNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

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

    @Test
    void checkCreateNewStaff() throws CinemaNotFoundException {

        int id = 1;
        String staffName = "Ana";
        int staffSalary = 20000;

        Staff staff = new Staff();

        doReturn(staff).when(staffService).createNewStaff(anyInt(), anyString(), anyInt());

        staffController.createNewStaff(id, staffName, staffSalary);

        verify(staffService).createNewStaff(id, staffName, staffSalary);
    }

    @Test
    void checkCreateNewStaffCinemaNotFound() throws CinemaNotFoundException {

        int id = 1;
        String staffName = "Ana";
        int staffSalary = 20000;

        doThrow(new CinemaNotFoundException("Cinema not found.")).when(staffService).createNewStaff(anyInt(), anyString(), anyInt());

        CinemaNotFoundException thrown = Assertions.assertThrows(CinemaNotFoundException.class,
                () -> staffController.createNewStaff(id, staffName, staffSalary));

        assertEquals("Cinema not found.", thrown.getMessage());

    }


}
