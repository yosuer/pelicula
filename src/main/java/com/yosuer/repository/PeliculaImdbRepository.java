package com.yosuer.repository;

import com.yosuer.domain.PeliculaImdb;

public interface PeliculaImdbRepository {

    PeliculaImdb getByTitle(String title);
}
