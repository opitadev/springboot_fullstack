package com.project.coches.controller;

import com.project.coches.domain.dto.CarDto;
import com.project.coches.domain.service.useCase.ICarUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cars")
public class CarController {

    private final ICarUseCase iCarUseCase;

    @GetMapping()
    public ResponseEntity<List<CarDto>> getAll() {
        return ResponseEntity.ok(iCarUseCase.getAll());
    }

    @GetMapping(path = "/brand-car/{idBrandCar}")
    public ResponseEntity<List<CarDto>> getByIdCar(@PathVariable Integer idBrandCar) {
        return ResponseEntity.ok(iCarUseCase.getByIdCar(idBrandCar));
    }

    @GetMapping(path = "/price/{priceCar}")
    public ResponseEntity<List<CarDto>> getCarsByPriceLessThan(@PathVariable double priceCar) {
        return ResponseEntity.ok(iCarUseCase.getCarsByPriceLessThan(priceCar));
    }

    @GetMapping(path = "/{idCar}")
    public ResponseEntity<CarDto> getCar(@PathVariable Integer idCar) {
        return ResponseEntity.of(iCarUseCase.getCar(idCar));
    }

    @PostMapping()
    public ResponseEntity<CarDto> save(@RequestBody CarDto carDtoNew) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(iCarUseCase.save(carDtoNew));
    }

    @DeleteMapping(path = "/{cardId}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer cardId) {
        return new ResponseEntity<>(this.iCarUseCase.delete(cardId) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
