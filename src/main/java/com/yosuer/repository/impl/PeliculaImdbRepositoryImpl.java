package com.yosuer.repository.impl;

import com.yosuer.repository.PeliculaImdbRepository;
import com.yosuer.domain.PeliculaImdb;
import java.net.URI;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Repository
public class PeliculaImdbRepositoryImpl implements PeliculaImdbRepository {

    private static final Logger logger = getLogger(PeliculaImdb.class);
    private static final String PARAM_TITLE = "t";

    @Autowired
    private RestTemplate restTemplate;

    @Value("${com.yosuer.pelicula-loader.apiomdb.url}")
    private String urlOmdb;

    @Override
    public PeliculaImdb getByTitle(String title) {
        PeliculaImdb peliculaImdb = null;
        URI uriOmdbApi = UriComponentsBuilder
                .fromHttpUrl(urlOmdb)
                .queryParam(PARAM_TITLE, title)
                .build()
                .toUri();
        logger.debug("Realizando petición a: {}", uriOmdbApi);
        try {
            peliculaImdb = restTemplate.getForObject(uriOmdbApi, PeliculaImdb.class);
        } catch (HttpMessageNotReadableException e) {
            logger.debug("Pelicula no encontrada, con titulo: {}", title);
        }

        return peliculaImdb;
    }

}
