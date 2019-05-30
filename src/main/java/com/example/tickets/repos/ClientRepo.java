package com.example.tickets.repos;

import org.springframework.data.repository.CrudRepository;
import com.example.tickets.domain.Client;

public interface ClientRepo extends CrudRepository<Client, Integer> {
}
