package com.yosuer.peliculaloader.service;

import com.yosuer.peliculaloader.domain.PeliculaImdb;

public interface PeliculaImdbService {

    PeliculaImdb getByTitle(String title);

}
