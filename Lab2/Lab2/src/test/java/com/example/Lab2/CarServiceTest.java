package com.example.Lab2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void find(){
        String marka = "Toyota";
        Car car = new Car(marka,"g",2003);
        Mockito.when(repository.findByMarka(marka)).thenReturn(car);
        Car result = CarService.getCarRepository(marka);
        assertEquals(car,result);
    }
    @Test
    public void save(){
        String marka = "Audi";
        String model = "A";
        int rok_produkcji = 2001;
        Car car = new Car(marka,model,rok_produkcji);
        ArgumentCaptor<Car> captor = ArgumentCaptor.forClass(Car.class);
        Mockito.when(repository.save(captor.capture())).thenReturn(car);

        CarService.addCarRepository(car);
        Mockito.verify(CarService,Mockito.times(1)).addCarRepository(car);
        Mockito.verify(repository,Mockito.times(5)).save(Mockito.any());
        Car carFromSaveCall = captor.getValue();
        assertEquals(car,carFromSaveCall);
    }
}
