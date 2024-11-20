package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.domain.SpecialtyTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
public class SpecialtyControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindAllSpecialties() throws Exception {
        this.mockMvc.perform(get("/specialties"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
        // Puedes añadir más validaciones aquí, como el tamaño de la lista.
    }

    @Test
    public void testFindSpecialtyOK() throws Exception {
        String SPECIALTY_NAME = "radiology";
        int ID_SPECIALTY = 1;

        mockMvc.perform(get("/specialties/" + ID_SPECIALTY))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(ID_SPECIALTY)))
                .andExpect(jsonPath("$.name", is(SPECIALTY_NAME)));
    }

    @Test
    public void testFindSpecialtyKO() throws Exception {
        mockMvc.perform(get("/specialties/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateSpecialty() throws Exception {
        String SPECIALTY_NAME = "Neurology";
        SpecialtyTO newSpecialtyTO = new SpecialtyTO();
        newSpecialtyTO.setName(SPECIALTY_NAME);
        newSpecialtyTO.setOffice("MainOffice");
        newSpecialtyTO.setHOpen("9");
        newSpecialtyTO.setHClose("17");

        mockMvc.perform(post("/specialties")
                        .content(om.writeValueAsString(newSpecialtyTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(SPECIALTY_NAME)))
                .andExpect(jsonPath("$.office", is("MainOffice")))
                .andExpect(jsonPath("$.hopen", is("9")))
                .andExpect(jsonPath("$.hclose", is("17")));
    }

    @Test
    public void testDeleteSpecialty() throws Exception {
        String SPECIALTY_NAME = "surgery";
        SpecialtyTO newSpecialtyTO = new SpecialtyTO();
        newSpecialtyTO.setName(SPECIALTY_NAME);
        newSpecialtyTO.setOffice("Maryland");
        newSpecialtyTO.setHOpen("8");
        newSpecialtyTO.setHClose("12");

        ResultActions mvcActions = mockMvc.perform(post("/specialties")
                        .content(om.writeValueAsString(newSpecialtyTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        String response = mvcActions.andReturn().getResponse().getContentAsString();
        Integer id = JsonPath.parse(response).read("$.id");

        mockMvc.perform(delete("/specialties/" + id))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteSpecialtyKO() throws Exception {
        mockMvc.perform(delete("/specialties/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateSpecialty() throws Exception {
        String SPECIALTY_NAME = "dentistry";
        SpecialtyTO newSpecialtyTO = new SpecialtyTO();
        newSpecialtyTO.setName(SPECIALTY_NAME);
        newSpecialtyTO.setOffice("Terranova");
        newSpecialtyTO.setHOpen("9");
        newSpecialtyTO.setHClose("19");

        ResultActions mvcActions = mockMvc.perform(post("/specialties")
                        .content(om.writeValueAsString(newSpecialtyTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());

        String response = mvcActions.andReturn().getResponse().getContentAsString();
        Integer id = JsonPath.parse(response).read("$.id");

        // Update
        newSpecialtyTO.setId(id);
        newSpecialtyTO.setName("Updated dentistry");

        mockMvc.perform(put("/specialties/" + id)
                        .content(om.writeValueAsString(newSpecialtyTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        // Validate Update
        mockMvc.perform(get("/specialties/" + id))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(id)))
                .andExpect(jsonPath("$.name", is("Updated dentistry")));
    }
}
