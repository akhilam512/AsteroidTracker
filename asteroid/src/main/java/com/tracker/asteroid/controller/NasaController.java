package com.tracker.asteroid.controller;

import com.tracker.asteroid.model.Asteroid;
import com.tracker.asteroid.model.AsteroidDetail;
import com.tracker.asteroid.service.NasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
public class NasaController {

    @Autowired
    NasaService nasaService;

    @GetMapping("/asteroids")
    public List<Asteroid> getNearestAsteroids(@RequestParam(value = "start_date") String startDate) throws IOException, ParseException {
        return nasaService.getAsteroids(startDate);
    }

    @GetMapping("/asteroids/{id}")
    public AsteroidDetail getAsteroidDetailById(@PathVariable("id") String id) throws IOException, ParseException {
        return nasaService.getAsteroidById(id);
    }

}
