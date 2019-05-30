package com.example.tickets;

import com.example.tickets.domain.Client;
import com.example.tickets.repos.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class TicketsController {
    @Autowired
    private ClientRepo clientRepo;

    @GetMapping("/tickets")
    public String tickets(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "tickets";
    }

    @GetMapping("/clients")
    public String clients( Map<String, Object> model) {
        Iterable<Client> clients = clientRepo.findAll();

        model.put("clients", clients);
        return "clients";
    }

    @PostMapping("/clients")
    public String add(@RequestParam String login,
                      @RequestParam String password,
                      @RequestParam String full_name,
                      @RequestParam String sex,
                      Map<String, Object> model) {
        Client client = new Client(login, password, full_name, sex);

        clientRepo.save(client);

        Iterable<Client> clients = clientRepo.findAll();

        model.put("clients", clients);

        return "clients";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        return "main";
    }

}
