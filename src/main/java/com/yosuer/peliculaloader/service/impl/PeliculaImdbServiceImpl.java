package com.yosuer.peliculaloader.service.impl;

import com.yosuer.peliculaloader.repository.PeliculaImdbRepository;
import com.yosuer.peliculaloader.service.PeliculaImdbService;
import com.yosuer.peliculaloader.domain.PeliculaImdb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaImdbServiceImpl implements PeliculaImdbService {

    @Autowired
    private PeliculaImdbRepository peliculaImdbRepository;

    @Override
    public PeliculaImdb getByTitle(String title) {
        
        return peliculaImdbRepository.getByTitle(title);
    }

}
