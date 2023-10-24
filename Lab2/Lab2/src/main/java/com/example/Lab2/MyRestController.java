package com.example.Lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/Car/{marka}")
    public Car getCarByMarka(
                @PathVariable("marka") String marka
        ){
            return this.myRestService.getCarRepository(marka);
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
    @DeleteMapping("/ucar")
    public void deletecar(@PathVariable int id){
        myRestService.usun(id);
    }


}
