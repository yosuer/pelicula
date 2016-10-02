package com.yosuer.peliculaloader.repository;

import com.yosuer.peliculaloader.repository.PeliculaImdbRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yosuer.peliculaloader.domain.PeliculaImdb;
import java.io.IOException;
import java.net.URI;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import org.springframework.web.util.UriComponentsBuilder;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockRestServiceServer
public class PeliculaImdbRepositoryTest {

    @Autowired
    private PeliculaImdbRepository peliculaImdbRepository;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${com.yosuer.pelicula-loader.apiomdb.url}")
    private String urlApiOmdb;

    @Test
    public void getByTitle_WithNonExistentTitle_returnNull() {
        String jsonPeliculaNoEncontrada = "{\"Response\": \"False\",\"Error\": \"Movie not found!\"}";

        URI uriApiOmdb = UriComponentsBuilder
                .fromHttpUrl(urlApiOmdb)
                .queryParam("t", "un titulo")
                .build().toUri();

        mockRestServiceServer
                .expect(requestTo(uriApiOmdb))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonPeliculaNoEncontrada, MediaType.APPLICATION_JSON));

        PeliculaImdb peliculaImdb = peliculaImdbRepository.getByTitle("un titulo");

        mockRestServiceServer.verify();
        assertNull(peliculaImdb);
    }

    @Test
    public void getByTitle_WithExistentTitle_returnPeliculaImdb() throws IOException {
        String jsonPeliculaExpected = "{\"Title\":\"un titulo\"}";       
        PeliculaImdb peliculaImdbExpected = objectMapper.readValue(jsonPeliculaExpected, PeliculaImdb.class);

        URI uriApiOmdb = UriComponentsBuilder
                .fromHttpUrl(urlApiOmdb)
                .queryParam("t", "un titulo")
                .build().toUri();

        mockRestServiceServer
                .expect(requestTo(uriApiOmdb))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(jsonPeliculaExpected, MediaType.APPLICATION_JSON));

        PeliculaImdb peliculaImdb = peliculaImdbRepository.getByTitle("un titulo");

        mockRestServiceServer.verify();
        assertThat(peliculaImdb).isEqualToComparingFieldByField(peliculaImdbExpected);
    }
}
