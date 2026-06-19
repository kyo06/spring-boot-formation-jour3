package org.formation.projet3.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.formation.projet3.dto.PersonneDto;
import org.formation.projet3.services.IPersonneService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonneControllerTest {

    // MockMvc c'est votre client API REST
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IPersonneService personneService;

    // Objet permettant de sérialiser / déserialiser un objet en json
    // Besoin pour POST et PUT
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testGetPersonnes() throws Exception {

        PersonneDto p = new PersonneDto();
        p.setId(1);
        p.setNom("Dupont");
        p.setAge(30);

        when(personneService.findAll())
                .thenReturn(List.of(p));

        mockMvc.perform(get("/personnes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nom").value("Dupont"))
                .andExpect(jsonPath("$[0].age").value(30));

    }

    @Test
    void testSearchByCriteria() throws Exception {

        PersonneDto p = new PersonneDto();
        p.setId(1);
        p.setNom("Dupont");
        p.setAge(30);

        when(personneService.searchAllByCriteria("Dupont", 30))
                .thenReturn(List.of(p));

        mockMvc.perform(
                        get("/personnes")
                                .param("nom", "Dupont")
                                .param("age", "30")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nom").value("Dupont"));
    }

    @Test
    void testGetPersonne() throws Exception {

        PersonneDto p = new PersonneDto();
        p.setId(1);
        p.setNom("Dupont");
        p.setAge(30);

        when(personneService.findById(1))
                .thenReturn(p);

        mockMvc.perform(get("/personnes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Dupont"));
    }

    @Test
    void testDeletePersonne() throws Exception {
        when(personneService.deleteById(1)).thenReturn(true);

        mockMvc.perform(delete("/personnes/1"))
                .andExpect(status().isNoContent());

        verify(personneService).deleteById(1);
    }

    @Test
    void testAddPersonne() throws Exception {
        PersonneDto p = new PersonneDto();
        p.setId(1);
        p.setNom("Martin");
        p.setAge(25);

        when(personneService.add(any(PersonneDto.class)))
                .thenReturn(p);

        mockMvc.perform(post("/personnes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p))) //body request
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.nom").value("Martin"));
    }

    @Test
    void testUpdatePersonne() throws Exception {

        PersonneDto p = new PersonneDto();
        p.setId(1);
        p.setNom("Durand");
        p.setAge(40);

        when(personneService.update(any(PersonneDto.class)))
                .thenReturn(p);

        mockMvc.perform(put("/personnes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(p)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Durand"))
                .andExpect(jsonPath("$.age").value(40));
    }

    @Test
    void testGetPersonneByIdNotFound() throws Exception {

        when(personneService.findById(999))
                .thenReturn(null);

        mockMvc.perform(get("/personnes/999"))
                .andExpect(status().isNotFound());
    }
}