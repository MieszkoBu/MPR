package com.example.Lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ThymeleafController {

    private final MyRestService service;

    @Autowired
    public ThymeleafController(MyRestService service) {
        this.service = service;
    }

    @GetMapping(value = "/index")
    public String getIndexValue(Model model) {
        model.addAttribute("Car", service.findAll());
        return "index";
    }

    @GetMapping(value = "/addCar")
    public String getAddview(Model model) {
        model.addAttribute("Car", new Car());
        return "addCar";
    }

    @PostMapping(value = "/addCar")
    public String addCar(Car car, Model model) {
        if (car.getRok_produkcji() < 0) {
            model.addAttribute("errorMessage", "error");
            return "addCar";
        }
        service.saveCar(car);
        return "redirect:/index";
    }

    @GetMapping(value = "/editCar/{id}")
    public String getEditValue(@PathVariable("id") long id, Model model) {
        try {
            Optional<Car> carToEdit = service.findByID(id);
            model.addAttribute("Car", carToEdit.orElse(new Car()));
            return "editCar";
        } catch (CarExceptionHandler.CarNotFoundException e) {
            model.addAttribute("errorMessage", "Car not found");
            return "error";
        }
    }

    @RequestMapping(value = "/editCar/{id}", method = RequestMethod.POST, params = "_method=PUT")
    public String editCar(@PathVariable("id") long id, Car car, Model model) {
        try {
            if (car.getRok_produkcji() < 0) {
                model.addAttribute("errorMessage", "error");
                model.addAttribute("Car", car);
                return "editCar";
            }
            service.editCar(id, car);
            return "redirect:/index";
        } catch (CarExceptionHandler.CarNotFoundException e) {
            model.addAttribute("errorMessage", "Car not found");
            return "error";
        }
    }
    @GetMapping("/deleteCar/{id}")
    public String getDeleteValue(@PathVariable("id") long id, Model model) {
        try {
            Optional<Car> carToDelete = service.findByID(id);
            model.addAttribute("Car", carToDelete.orElse(new Car()));
            return "deleteCar";
        } catch (CarExceptionHandler.CarNotFoundException e) {
            model.addAttribute("errorMessage", "Car not found");
            return "error";
        }
    }
    @RequestMapping(value = "/deleteCar/{id}", method = RequestMethod.POST, params = "_method=DELETE")
    public String deleteCar(@PathVariable("id") long id, Model model) {
        try {
            service.deleteCar(id);
            return "redirect:/index";
        } catch (CarExceptionHandler.CarNotFoundException e) {
            model.addAttribute("errorMessage", "Car not found");
            return "error";
        }
    }
}
