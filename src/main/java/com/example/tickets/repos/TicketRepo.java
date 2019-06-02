package com.example.tickets.repos;

import org.springframework.data.repository.CrudRepository;
import com.example.tickets.domain.Ticket;

public interface TicketRepo extends CrudRepository<Ticket, Integer>{
}
