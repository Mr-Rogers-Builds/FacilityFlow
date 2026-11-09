package com.facilityflow.organization;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrganizationServiceTest {
    @Mock
    private OrganizationRepository organizationRepository;

    @InjectMocks
    private OrganizationService organizationService;

    @Test
    void getOrganizationById_returnsOrganizationWhenFound() {
        Organization organization = new Organization();

        organization.setName("Test Organization");
        organization.setHqLocationAddress("100 test street");
        organization.setHqLocationCity("Statesboro");
        organization.setHqLocationState("Georgia");
        organization.setHqLocationZip("30458");
        organization.setHqLocationCountry("United States");
        organization.setWebsite("http://example.com");
        organization.setEmail("test@example.com");
        organization.setPhoneNumber("123-456-7890");
        organization.setFaxNumber(null);

        when(organizationRepository.findById(1L)).thenReturn(Optional.of(organization));

        OrganizationResponse response = organizationService.getOrganizationById(1L);

        assertEquals("Test Organization", response.getName());
        assertEquals("100 test street", response.getHqLocationAddress());
        assertEquals("Statesboro", response.getHqLocationCity());
        assertEquals("Georgia", response.getHqLocationState());
        assertEquals("30458", response.getHqLocationZip());
        assertEquals("United States", response.getHqLocationCountry());
        assertTrue(response.isActive());
        assertEquals("http://example.com", response.getWebsite());
        assertEquals("test@example.com", response.getEmail());
        assertEquals("123-456-7890", response.getPhoneNumber());
        assertNull(response.getFaxNumber());

    }

    @Test
    void getOrganizationById_throwsExceptionWhenNotFound() {
        when(organizationRepository.findById(999L)).thenReturn(Optional.empty());

        OrganizationNotFoundException exception = assertThrows(OrganizationNotFoundException.class,
                () -> organizationService.getOrganizationById(999L));
        assertEquals("Organization not found with id: 999", exception.getMessage());
    }

    @Test
    void createOrganization_savesAndReturnsOrganization() {
        CreateOrganizationRequest request = new CreateOrganizationRequest();
        request.setName("New Organization");
        request.setHqLocationAddress("200 new street");
        request.setHqLocationCity("Savannah");
        request.setHqLocationState("Georgia");
        request.setHqLocationZip("31401");
        request.setHqLocationCountry("United States");
        request.setWebsite("http://newexample.com");
        request.setEmail("new@example.com");
        request.setPhoneNumber("987-654-3210");
        request.setFaxNumber(null);

        when(organizationRepository.save(any(Organization.class))).thenAnswer(invocation -> invocation.getArgument(0));

        OrganizationResponse response = organizationService.createOrganization(request);

        assertEquals("New Organization", response.getName());
        assertEquals("200 new street", response.getHqLocationAddress());
        assertEquals("Savannah", response.getHqLocationCity());
        assertEquals("Georgia", response.getHqLocationState());
        assertEquals("31401", response.getHqLocationZip());
        assertEquals("United States", response.getHqLocationCountry());
        assertTrue(response.isActive());
        assertEquals("http://newexample.com", response.getWebsite());
        assertEquals("new@example.com", response.getEmail());
        assertEquals("987-654-3210", response.getPhoneNumber());
        assertNull(response.getFaxNumber());

        ArgumentCaptor<Organization> organizationCaptor = ArgumentCaptor.forClass(Organization.class);
        verify(organizationRepository).save(organizationCaptor.capture());
        Organization savedOrganization = organizationCaptor.getValue();
        assertEquals("New Organization", savedOrganization.getName());
        assertEquals("200 new street", savedOrganization.getHqLocationAddress());
        assertEquals("Savannah", savedOrganization.getHqLocationCity());
        assertEquals("Georgia", savedOrganization.getHqLocationState());
        assertEquals("31401", savedOrganization.getHqLocationZip());
        assertEquals("United States", savedOrganization.getHqLocationCountry());
        assertTrue(savedOrganization.isActive());
        assertEquals("http://newexample.com", savedOrganization.getWebsite());
        assertEquals("new@example.com", savedOrganization.getEmail());
        assertEquals("987-654-3210", savedOrganization.getPhoneNumber());
        assertNull(savedOrganization.getFaxNumber());
    }
}
