package com.facilityflow.organization;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;

    public OrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Transactional
    public OrganizationResponse createOrganization(CreateOrganizationRequest request) {

        Organization organization = new Organization();
        organization.setName(request.getName());
        organization.setHqLocationAddress(request.getHqLocationAddress());
        organization.setHqLocationCity(request.getHqLocationCity());
        organization.setHqLocationState(request.getHqLocationState());
        organization.setHqLocationZip(request.getHqLocationZip());
        organization.setHqLocationCountry(request.getHqLocationCountry());
        organization.setWebsite(request.getWebsite());
        organization.setPhoneNumber(request.getPhoneNumber());
        organization.setEmail(request.getEmail());
        organization.setFaxNumber(request.getFaxNumber());

        return toOrganizationResponse(organizationRepository.save(organization));
    }

    private OrganizationResponse toOrganizationResponse(Organization organization) {
        OrganizationResponse response = new OrganizationResponse();

        response.setId(organization.getId());
        response.setName(organization.getName());
        response.setHqLocationAddress(organization.getHqLocationAddress());
        response.setHqLocationCity(organization.getHqLocationCity());
        response.setHqLocationState(organization.getHqLocationState());
        response.setHqLocationZip(organization.getHqLocationZip());
        response.setHqLocationCountry(organization.getHqLocationCountry());
        response.setWebsite(organization.getWebsite());
        response.setActive(organization.isActive());
        response.setPhoneNumber(organization.getPhoneNumber());
        response.setEmail(organization.getEmail());
        response.setFaxNumber(organization.getFaxNumber());
        return response;
    }

    @Transactional(readOnly = true)
    public OrganizationResponse getOrganizationById(Long id) {
        Organization organization = organizationRepository.findById(id)
                .orElseThrow(() -> new OrganizationNotFoundException(id));
        return toOrganizationResponse(organization);
    }
}
