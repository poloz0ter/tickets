package com.example.tickets.controller;

import com.example.tickets.domain.Client;
import com.example.tickets.repos.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Optional;

@Controller
public class ClientsController {
    @Autowired
    private ClientRepo clientRepo;

    @GetMapping("/clients")
    public String clients( Map<String, Object> model) {
        Iterable<Client> clients = clientRepo.findAll();

        model.put("clients", clients);
        return "clients";
    }

    @PostMapping("/clients")
    public String add(@RequestParam(required = false) String login,
                      @RequestParam(required = false) String password,
                      @RequestParam(required = false) String full_name,
                      @RequestParam(required = false) String sex,
                      Map<String, Object> model) {
        Client client = new Client(login, password, full_name, sex);

        clientRepo.save(client);
        model.put("client", client);
  
        return "redirect:/clients";
    }

    @GetMapping("/clients/edit")
    public String editForm(@RequestParam(required = false) String id) {
        int intId = Integer.parseInt(id);
        Optional<Client> optClient = clientRepo.findById(intId);
        if (optClient.isPresent()) {
            return "edit";
        } else {
            return "redirect:/clients";
        }
    }

    @PostMapping("/clients/edit")
    public String edit(@RequestParam(required = false) String id,
                       @RequestParam(required = false) String login,
                       @RequestParam(required = false) String password,
                       @RequestParam(required = false) String full_name,
                       @RequestParam(required = false) String sex,
                       Map<String, Object> model) {
        int intId = Integer.parseInt(id);
        Optional<Client> optClient = clientRepo.findById(intId);
        Client client = optClient.get();
        client.setLogin(full_name);
        client.setPassword(password);
        client.setFull_name(full_name);
        client.setSex(sex);

        clientRepo.save(client);
        model.put("client", client);

        return "redirect:/clients";
    }

    @GetMapping("/clients/delete")
    public String delete(@RequestParam(required = false) String id, Map<String, Object> model) {
        int intId = Integer.parseInt(id);
        clientRepo.deleteById(intId);

        return "redirect:/clients";
    }

    @GetMapping("/clients/show")
    public String show(@RequestParam(required = false) String id, Map<String, Object> model) {
        int intId = Integer.parseInt(id);
        Optional<Client> optClient = clientRepo.findById(intId);
        Client client = optClient.get();
        model.put("client", client);


        return "show";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        return "main";
    }

}
