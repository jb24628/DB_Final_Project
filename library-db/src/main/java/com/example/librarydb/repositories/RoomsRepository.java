package com.example.librarydb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.librarydb.models.Rooms;
/**
 * Lets the application communicate with MySQL
 * @author JB
 *
 */
@Repository
public interface RoomsRepository extends CrudRepository<Rooms, Integer>{

}
