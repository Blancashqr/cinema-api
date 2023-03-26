package com.blancash.cinemaapi.repos;

import com.blancash.cinemaapi.models.Staff;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepo extends CrudRepository<Staff, Long> {

  List<Staff> findAll();

  Staff findStaffById(int id);

}
