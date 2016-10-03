package com.yosuer.peliculaloader.service;

import com.yosuer.peliculaloader.PeliculaLoaderApplicationTests;
import com.yosuer.peliculaloader.repository.PeliculaImdbRepository;
import com.yosuer.peliculaloader.domain.PeliculaImdb;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

public class PeliculaImdbServiceTest extends PeliculaLoaderApplicationTests {

    @Autowired
    private PeliculaImdbService peliculaImdbService;

    @MockBean
    private PeliculaImdbRepository peliculaImdbRepository;

    @Test
    public void getByTitle_withNonExistentTitle_returnNull() {
        String title = "un titulo";
        when(peliculaImdbRepository.getByTitle(title)).thenReturn(null);

        PeliculaImdb peliculaImdb = peliculaImdbService.getByTitle(title);

        assertNull(peliculaImdb);
    }

    @Test
    public void getByTitle_withExistentTitle_returnPeliculaImdb() {
        String title = "un titulo";
        PeliculaImdb peliculaImdbMock = buildPeliculaImdbMock(title);
        when(peliculaImdbRepository.getByTitle(title)).thenReturn(peliculaImdbMock);

        PeliculaImdb peliculaImdb = peliculaImdbService.getByTitle(title);

        assertEquals(title, peliculaImdb.getTitle());
    }

    private PeliculaImdb buildPeliculaImdbMock(String title) {
        PeliculaImdb peliculaImdbMock = new PeliculaImdb();
        peliculaImdbMock.setTitle(title);
        peliculaImdbMock.setYear("1990");
        peliculaImdbMock.setCountry("AlgunPais");
        peliculaImdbMock.setGenre("Terror");
        peliculaImdbMock.setPoster("urlDeLaImagen");
        peliculaImdbMock.setPlot("Alguna descripcion");

        return peliculaImdbMock;
    }
}
