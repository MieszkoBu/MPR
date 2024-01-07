package com.example.Lab2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarServiceTest {
    @Mock
    private CarRepository repository;
    private AutoCloseable openMocks;
    private MyRestService CarService;
    @BeforeEach
    public void init(){
        openMocks = MockitoAnnotations.openMocks(this);
        CarService = Mockito.spy(new MyRestService(repository));
    }
    @AfterEach
    public void tearDown() throws Exception{
        openMocks.close();
    }
    @Test
    public void findCarByMarka() {
        String marka = "Toyota";
        Car car = new Car(marka, "g", 2003);
        Mockito.when(repository.findByMarka(marka)).thenReturn(car);
        Optional<Car> result = CarService.getCarRepository(marka);
        assertEquals(car, result.orElse(null));
    }

    @Test
    public void saveCar() {
        String marka = "Audi";
        String model = "A";
        int rok_produkcji = 2001;
        Car car = new Car(marka, model, rok_produkcji);
         CarService.save(car);
        Mockito.verify(repository, Mockito.times(6)).save(Mockito.any());
    }
    @Test
    public void CarAddThrowsExceptionWhenCarIsNotPresent() {
        Car car = new Car("a", "v", 2009);
        car.setId(6);
        Mockito.when(repository.findById(6L)).thenReturn(Optional.empty());

        assertThrows(CarExceptionHandler.CarNotFoundException.class, () -> {
            CarService.edit(car);
        });
    }

    @Test
    public void CarAddThrowsExceptionWhenCarIsPresent() {
        Car car = new Car("BMW", "A", 2001);
        car.setId(2);
        Mockito.when(repository.findById(2L)).thenReturn(Optional.of(car));

        assertThrows(CarExceptionHandler.CarAlreadyExistExeption.class, () -> {
            CarService.save(car);
        });
    }
}
