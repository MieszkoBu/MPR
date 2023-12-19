package com.example.Lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MyRestController {
    private final MyRestService myRestService;
    @GetMapping("/greeting")
    public String greeting(){
        return "Greeting from spring boot";
    }
    @Autowired
    public MyRestController(MyRestService myRestService){
        this.myRestService = myRestService;
    }

    @GetMapping("/cars")
    public List<Car> cars(){
        return myRestService.wypisz();
    }
    @GetMapping("/findbyMarka/{marka}")
    public Optional<Car> getCarByMarka(@PathVariable("marka") String marka){
            return this.myRestService.getCarRepository(marka);
        }
        @GetMapping("/findbyId/{id}")
        public Optional<Car> getCarByID(@PathVariable("id") long id){
            return this.myRestService.findByID(id);
        }
    @PostMapping("/Car/add")
    public void addCar(@RequestBody Car car){
        this.myRestService.addCarRepository(car);
    }
    @PutMapping("/car")
    public void putCar(@RequestBody Car car){
           myRestService.c(car);
           System.out.println("Dodano samochód");
    }
    @DeleteMapping("/ucar/{id}")
    public void deletecar(@PathVariable("id") int id){
        myRestService.usun(id);
    }
    @GetMapping("/car/{rok_produkcji}")
    public List<Car> filterByModel(@PathVariable("rok_produkcji") int rok_produkcji){
            return myRestService.filterByRok_produkcji(rok_produkcji);
    }

}
