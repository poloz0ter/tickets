package com.example.tickets.controller;

import com.example.tickets.domain.Client;
import com.example.tickets.domain.Ticket;
import com.example.tickets.repos.ClientRepo;
import com.example.tickets.repos.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class TicketsController {
    @Autowired
    private TicketRepo ticketRepo;
    private ClientRepo clientRepo;

    @GetMapping("/tickets")
    public String tickets( Map<String, Object> model) {
        Iterable<Ticket> tickets = ticketRepo.findAll();

        model.put("tickets", tickets);
        return "tickets";
    }

    @PostMapping("/tickets")
    public String add(@RequestParam String price,
                      @RequestParam String date,
                      @RequestParam String client_id,
                      Map<String, Object> model) {
        int intId = Integer.parseInt(client_id);
        Optional<Client> optClient = clientRepo.findById(intId);

        if (optClient.isPresent()) {
            Client client = optClient.get();
            Ticket ticket = new Ticket(price, date);
            ticket.setClient_id(client.getId());
            ticketRepo.save(ticket);
            model.put("ticket", ticket);
        } else {
            System.out.println("Client not found");
        }

        return "redirect:/tickets";
    }
}
