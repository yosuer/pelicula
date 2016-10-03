package com.yosuer.peliculaloader.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yosuer.peliculaloader.PeliculaLoaderApplicationTests;
import com.yosuer.peliculaloader.domain.PeliculaImdb;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import org.apache.commons.io.IOUtils;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import org.springframework.web.util.UriComponentsBuilder;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@AutoConfigureMockRestServiceServer
public class PeliculaImdbRepositoryTest extends PeliculaLoaderApplicationTests{

    @Autowired
    private PeliculaImdbRepository peliculaImdbRepository;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private ObjectMapper objectMapper;

    @Value("${com.yosuer.pelicula-loader.apiomdb.url}")
    private String urlApiOmdb;

    @Test
    public void getByTitle_WithNonExistentTitle_returnWithTitleNull() throws IOException {
        String jsonPeliculaNoEncontrada = IOUtils.
                toString(new FileInputStream("src/test/resources/responseImdbAPI/NotFound.json"), "UTF-8");

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
        assertNull(peliculaImdb.getTitle());
    }

    @Test
    public void getByTitle_WithExistentTitle_returnPeliculaImdb() throws IOException {
        String jsonPeliculaExpected = IOUtils.
                toString(new FileInputStream("src/test/resources/responseImdbAPI/Found.json"), "UTF-8");

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
