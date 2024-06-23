package com.ainella.petclinic.controllers;

import com.ainella.petclinic.models.Owner;
import com.ainella.petclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping()
    public String signupForm(Model model) {
        model.addAttribute("owner", new Owner());
        return "signup";
    }

    @PostMapping()
    public String createOwner(Model model, @ModelAttribute Owner owner) {
        ownerService.createOwner(owner);
        return "redirect:/owner/pets";
    }

}
