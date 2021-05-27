package com.tp2ee.tpspringmvc.web;

import com.tp2ee.tpspringmvc.entities.Patient;
import com.tp2ee.tpspringmvc.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class PatientController {
    @Autowired
    PatientRepository pr;

    @GetMapping(path = "/index")
    public String index() {
        return "index";
    }

    @GetMapping(path = "/patients")
    public String patients(Model model,
                           @RequestParam(name = "page", defaultValue = "0") int page,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword,
                           @RequestParam(name = "size", defaultValue = "5") int size) {
        Page<Patient> pPage = pr.findByNameContains(keyword, PageRequest.of(page, size));
        model.addAttribute("patients", pPage.getContent());
        model.addAttribute("pages", new int[pPage.getTotalPages()]);
        model.addAttribute("size", size);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "patients";
    }

    @GetMapping(path = "/deletePatient")
    public String deletePatient(Long id, String keyword, int size, int page) {
        pr.deleteById(id);
        return "redirect:/patients?page=" + page + "&size=" + size + "&page=" + page + "&keyword=" + keyword;
    }

    @GetMapping(path = "/afficherPatientsMalades")
    public String afficherPatientsMalades(int size, int page) {
        pr.findBySickTrue(PageRequest.of(page, size));
        return "afficherPatientsMalade";
    }

    @GetMapping(path = "/formPatient")
    public String formPatient(Model m) {
        m.addAttribute("patient", new Patient());
        m.addAttribute("mode","new");
        return "formPatient";
    }

    @PostMapping(path = "/savePatient")
    public String savePatient(@Valid Patient p, BindingResult bs) {
        if (bs.hasErrors()) return "formPatient";
        pr.save(p);
        return "confirmation";
    }

    @GetMapping(path = "/editPatient")
    public String editPatient(Model m, Long id) {
        Patient p = pr.findById(id).get();
        m.addAttribute("patient", p);
        m.addAttribute("mode","edit");
        return "formPatient";
    }
}