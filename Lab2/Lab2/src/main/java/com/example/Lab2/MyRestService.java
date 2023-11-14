package com.example.Lab2;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MyRestService{
    public List<Car> cars = new ArrayList<>();
    private CarRepository carRepository;
    Car c1 = new Car("Bmw","v",2009);
    Car c2 = new Car("Audi","A3",2004);
    Car c3 = new Car("Toyota","Yaris",2001);

    public MyRestService(CarRepository carRepository){
        this.carRepository = carRepository;
        this.carRepository.save(new Car("a","v",2009));
        this.carRepository.save(new Car("b","v",2006));
        this.carRepository.save(new Car("c","s",2004));
        this.carRepository.save(new Car("dua","d",2001));
        this.cars.add(c1);
        this.cars.add(c2);
        this.cars.add(c3);
    }

    public Car getCarRepository(String marka) {
        return this.carRepository.findByMarka(marka);
    }
    public void addCarRepository(Car car){
        this.carRepository.save(car);
    }
    public List<Car> wypisz(){
        return cars;
    }
    void c(Car car){
        cars.add(car);
    }
    void usun(int id){
        Optional<Car> CarRepo = Optional.ofNullable(this.carRepository.findById(id));
        if(CarRepo.isPresent()){
            cars.remove(id);
        }else{
            System.out.println("Nie ma takiego samochodu");
            throw new CarExceptionHandler.CarNotFoundException();
        }
    }
    public Optional<Car> findByID(long id){
        Optional<Car> CarRepo = Optional.ofNullable(this.carRepository.findById(id));
        if(CarRepo.isPresent()){
            return CarRepo;
        }else{
            throw new CarExceptionHandler.CarNotFoundException();
        }
    }


}
