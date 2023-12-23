package com.example.Lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        return myRestService.cars();
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
    @PutMapping("/car/edit/{id}")
    public ResponseEntity<String> editCar(@PathVariable("id") long id, @RequestBody Car updatedCar) {
        Optional<Car> existingCar = myRestService.findByID(id);
        if (existingCar.isPresent()) {
            Car carToUpdate = existingCar.get();
            carToUpdate.setMarka(updatedCar.getMarka());
            carToUpdate.setModel(updatedCar.getModel());
            carToUpdate.setRok_produkcji(updatedCar.getRok_produkcji());

            myRestService.save(carToUpdate);
            return ResponseEntity.ok("Edytowano samochód o ID: " + id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Samochód o ID: " + id + " nie istnieje");
        }
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
