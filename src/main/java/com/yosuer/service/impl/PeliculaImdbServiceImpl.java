package com.yosuer.service.impl;

import com.yosuer.repository.PeliculaImdbRepository;
import com.yosuer.service.PeliculaImdbService;
import com.yosuer.domain.PeliculaImdb;
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
