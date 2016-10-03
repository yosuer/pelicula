package com.yosuer.peliculaloader.controller.rest;

import com.yosuer.peliculaloader.domain.PeliculaImdb;
import com.yosuer.peliculaloader.service.PeliculaImdbService;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/peliculaImdb")
public class PeliculaImdbRestController {

    @Autowired
    private PeliculaImdbService peliculaImdbService;
    private static final Logger logger = getLogger(PeliculaImdbRestController.class);

    @GetMapping
    public PeliculaImdb obtenerPeliculaImdbPorTitulo(@RequestParam String titulo) {
        PeliculaImdb peliculaImdb = peliculaImdbService.getByTitle(titulo);
        logger.debug("Devolviendo pelicula : {}", peliculaImdb);
        return peliculaImdb;
    }

}
