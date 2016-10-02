package com.yosuer.peliculaloader.repository;

import com.yosuer.peliculaloader.domain.PeliculaImdb;

public interface PeliculaImdbRepository {

    PeliculaImdb getByTitle(String title);
}
