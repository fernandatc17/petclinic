package com.tecsup.petclinic.webs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import com.tecsup.petclinic.domain.OwnerTO;
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
public class OwnerControllerTest {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testFindAllOwners() throws Exception {
        int ID_FIRST_RECORD = 1;

        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$[0].id", is(ID_FIRST_RECORD)));
    }

    @Test
    public void testFindOwnerOK() throws Exception {
        String FIRST_NAME = "Jeff";
        String LAST_NAME = "Black";
        int ID = 7;

        mockMvc.perform(get("/owners/" + ID))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(ID)))
                .andExpect(jsonPath("$.firstName", is(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", is(LAST_NAME)));
    }

    @Test
    public void testFindOwnerKO() throws Exception {
        mockMvc.perform(get("/owners/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateOwner() throws Exception {
        String FIRST_NAME = "Jane";
        String LAST_NAME = "Doe";
        String ADDRESS = "123 Main St";
        String CITY = "Springfield";
        String TELEPHONE = "123456789";

        OwnerTO newOwnerTO = new OwnerTO();
        newOwnerTO.setFirstName(FIRST_NAME);
        newOwnerTO.setLastName(LAST_NAME);
        newOwnerTO.setAddress(ADDRESS);
        newOwnerTO.setCity(CITY);
        newOwnerTO.setTelephone(TELEPHONE);

        mockMvc.perform(post("/owners")
                        .content(om.writeValueAsString(newOwnerTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", is(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", is(LAST_NAME)))
                .andExpect(jsonPath("$.address", is(ADDRESS)))
                .andExpect(jsonPath("$.city", is(CITY)))
                .andExpect(jsonPath("$.telephone", is(TELEPHONE)));
    }

    @Test
    public void testDeleteOwner() throws Exception {
        String FIRST_NAME = "DeleteTest";
        OwnerTO newOwnerTO = new OwnerTO();
        newOwnerTO.setFirstName(FIRST_NAME);
        newOwnerTO.setLastName("User");

        ResultActions result = mockMvc.perform(post("/owners")
                        .content(om.writeValueAsString(newOwnerTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        String response = result.andReturn().getResponse().getContentAsString(); //Obtiene el cuerpo de la respuesta HTTP como una cadena de texto
        Integer id = JsonPath.parse(response).read("$.id");

        mockMvc.perform(delete("/owners/" + id))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteOwnerKO() throws Exception {
        mockMvc.perform(delete("/owners/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateOwner() throws Exception {
        String FIRST_NAME = "Initial";
        String LAST_NAME = "User";
        String UPDATED_FIRST_NAME = "Updated";
        String UPDATED_LAST_NAME = "Name";

        OwnerTO newOwnerTO = new OwnerTO();
        newOwnerTO.setFirstName(FIRST_NAME);
        newOwnerTO.setLastName(LAST_NAME);

        ResultActions result = mockMvc.perform(post("/owners")
                        .content(om.writeValueAsString(newOwnerTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        String response = result.andReturn().getResponse().getContentAsString();
        Integer id = JsonPath.parse(response).read("$.id");

        OwnerTO updatedOwnerTO = new OwnerTO();
        updatedOwnerTO.setId(id);
        updatedOwnerTO.setFirstName(UPDATED_FIRST_NAME);
        updatedOwnerTO.setLastName(UPDATED_LAST_NAME);

        mockMvc.perform(put("/owners/" + id)
                        .content(om.writeValueAsString(updatedOwnerTO))
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is(UPDATED_FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", is(UPDATED_LAST_NAME)));
    }
}
