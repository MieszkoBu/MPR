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
    Car c3 = new Car("Toyota","Yaris",2009);


    public MyRestService(CarRepository carRepository){
        this.carRepository = carRepository;
        this.carRepository.save(new Car("a","v",2009));
        this.carRepository.save(new Car("b","v",2003));
        this.carRepository.save(new Car("c","s",2004));
        this.carRepository.save(new Car("dua","d",2009));
        this.carRepository.save(new Car("dua","df",2008));

        this.cars.add(c1);
        this.cars.add(c2);
        this.cars.add(c3);

    }

    public Optional<Car> getCarRepository(String marka) {
        Optional<Car> CarRepo = Optional.ofNullable(this.carRepository.findByMarka(marka));
        if(CarRepo.isPresent()){
            return CarRepo;
        }else{
            System.out.println("Nie ma takiego samochodu");
            throw new CarExceptionHandler.CarNotFoundException();
        }
    }
    public void addCarRepository(Car car){
        this.carRepository.save(car);
    }
    public List<Car> wypisz(){
        List<Car> car = new ArrayList<>();
        car.addAll(carRepository.findAll());
        return car;
    }
    void c(Car car){
        this.carRepository.save(car);
    }
    void usun(long id){
        Optional<Car> CarRepo = Optional.ofNullable(this.carRepository.findById(id));
        if(CarRepo.isPresent()){
            this.carRepository.deleteById(id);
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
            System.out.println("Nie ma takiego samochodu");
            throw new CarExceptionHandler.CarNotFoundException();
        }
    }
    public List<Car> filterByRok_produkcji(int rok_produkcji){
        List<Car> C = new ArrayList<>();
        for (Car c: cars) {
            if(c.getRok_produkcji()==rok_produkcji){
                C.add(c);
            }
        }
        return C;
    }


}
