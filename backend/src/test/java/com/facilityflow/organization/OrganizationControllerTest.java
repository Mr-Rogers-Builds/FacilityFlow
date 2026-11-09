package com.facilityflow.organization;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(OrganizationController.class)
class OrganizationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private OrganizationService organizationService;

    @Test
    void testCreateOrganization() {

    }

    @Test
    void testGetOrganizationById() throws Exception {

        when(organizationService.getOrganizationById(999L))
                .thenThrow(new OrganizationNotFoundException(999L));

        mockMvc.perform(get("/api/organizations/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Organization not found with id: 999"));

    }

}
