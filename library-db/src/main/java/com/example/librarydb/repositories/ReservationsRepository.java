package com.example.librarydb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.librarydb.models.Reservations;
/**
 * Lets the application communicate with MySQL
 * @author JB
 *
 */
@Repository
public interface ReservationsRepository extends CrudRepository<Reservations, Integer>{

}
