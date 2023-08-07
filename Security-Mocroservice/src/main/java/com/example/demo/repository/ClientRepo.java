package com.example.demo.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.model.Client;


public interface ClientRepo extends MongoRepository<Client, Integer>{

	Client findByName(String name);

}