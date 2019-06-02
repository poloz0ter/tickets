package com.example.tickets.controller;

import com.example.tickets.domain.Client;
import com.example.tickets.domain.Ticket;
import com.example.tickets.repos.ClientRepo;
import com.example.tickets.repos.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class TicketsController {
    @Autowired
    private TicketRepo ticketRepo;
    @Autowired
    private ClientRepo clientRepo;

    @GetMapping("/tickets")
    public String tickets( Map<String, Object> model) {
        Iterable<Ticket> tickets = ticketRepo.findAll();
        model.put("tickets", tickets);

        Iterable<Client> clients = clientRepo.findAll();
        model.put("clients", clients);

        return "tickets";
    }

    @PostMapping("/tickets")
    public String add(@RequestParam String price,
                      @RequestParam String date,
                      @RequestParam Integer client_id,
                      Map<String, Object> model) {
        Client client = clientRepo.findById(client_id).get();
        Ticket ticket = new Ticket(price, date, client);

        ticketRepo.save(ticket);
        model.put("ticket", ticket);

        return "redirect:/tickets";
    }
}
