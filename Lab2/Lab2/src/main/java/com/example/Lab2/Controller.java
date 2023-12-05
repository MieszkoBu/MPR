package com.example.Lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@org.springframework.stereotype.Controller
public class Controller {
    private final MyRestService service;
    @Autowired
    public Controller(MyRestService service) {
        this.service = service;
    }
    @GetMapping(value = "/index")
    public String getIndexValue(Model model){
        model.addAttribute("Car",service.wypisz());
        return "index";
    }
    @GetMapping(value = "/addCar")
    public String getAddview(Model model){
        model.addAttribute("Car",new Car());
        return "addCar";
    }
    @PostMapping(value = "/addCar")
    public String addCar(Car car, Model model){
       if(car.getRok_produkcji()<0){
           model.addAttribute("errorMessage","error");
           return "addCar";
       }
       service.addCarRepository(car);
        return "redirect:/index";
    }
    @GetMapping(value = "/editCar")
    public String getEditValue(Model model){

        return "addCar";
    }
}
