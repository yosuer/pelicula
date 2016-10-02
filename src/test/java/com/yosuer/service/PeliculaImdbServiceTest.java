package com.yosuer.service;

import com.yosuer.repository.PeliculaImdbRepository;
import com.yosuer.domain.PeliculaImdb;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PeliculaImdbServiceTest {

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
        PeliculaImdb peliculaImdbMock = new PeliculaImdb();
        peliculaImdbMock.setTitle(title);
        when(peliculaImdbRepository.getByTitle(title)).thenReturn(peliculaImdbMock);

        PeliculaImdb peliculaImdb = peliculaImdbService.getByTitle(title);

        assertEquals(title, peliculaImdb.getTitle());
    }
}
