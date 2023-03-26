package com.blancash.cinemaapi.service;

import com.blancash.cinemaapi.models.Cinema;
import com.blancash.cinemaapi.models.Staff;
import com.blancash.cinemaapi.repos.CinemaRepo;
import com.blancash.cinemaapi.repos.MovieRepo;
import com.blancash.cinemaapi.repos.StaffRepo;
import com.blancash.cinemaapi.service.exception.CinemaNotFoundException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StaffService {

  private final StaffRepo staffRepo;
  private final CinemaRepo cinemaRepo;

  @Autowired
  public StaffService(StaffRepo staffRepo, CinemaRepo cinemaRepo) {
    this.staffRepo = staffRepo;
    this.cinemaRepo = cinemaRepo;
  }

  public List<Staff> getAllStaff() {
    return staffRepo.findAll();
  }

  public Staff getStaffById(int id) {
    return staffRepo.findStaffById(id);
  }

  public Staff createNewStaff(int cinemaId, String name, int salary) throws CinemaNotFoundException {
    log.info("Creating new staff member for cinemaId={}...", cinemaId);

    Cinema cinema = cinemaRepo.findCinemaById(cinemaId);

    if (cinema == null) {
      throw new CinemaNotFoundException("Cinema not found.");
    }

    Staff staff = new Staff(name, salary, cinema);

    Staff newStaff = staffRepo.save(staff);

    List<Staff> cinemaStaff = cinema.getStaffList();
    cinemaStaff.add(newStaff);
    log.info("Successfully created new staffId={} for cinemaId={}.", newStaff.getId(), cinemaId);

    return newStaff;

  }

}
