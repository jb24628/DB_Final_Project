package com.example.librarydb.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.librarydb.models.Checkedout;
/**
 * Lets the application communicate with MySQL
 * @author JB
 *
 */
@Repository
public interface CheckedoutRepository extends CrudRepository<Checkedout, Integer>{

}
