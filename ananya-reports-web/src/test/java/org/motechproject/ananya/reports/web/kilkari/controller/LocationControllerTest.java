package org.motechproject.ananya.reports.web.kilkari.controller;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.internal.matchers.Contains;
import org.motechproject.ananya.reports.kilkari.contract.request.LocationRequest;
import org.motechproject.ananya.reports.kilkari.contract.request.LocationSyncRequest;
import org.motechproject.ananya.reports.kilkari.domain.LocationStatus;
import org.motechproject.ananya.reports.kilkari.domain.dimension.LocationDimension;
import org.motechproject.ananya.reports.kilkari.service.LocationService;
import org.motechproject.ananya.reports.web.kilkari.controller.views.HttpConstants;
import org.motechproject.ananya.reports.web.util.TestUtils;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.motechproject.ananya.reports.web.util.MVCTestUtils.mockMvc;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;

public class LocationControllerTest {

    private LocationController locationController;

    @Mock
    private LocationService locationService;

    @Before
    public void setUp(){
        initMocks(this);
        locationController = new LocationController(locationService);
    }


    @Test
    public void shouldReturnLocationDetailsBasedOnQuery() throws Exception {
        String district = "mydistrict";
        String block="myblock";
        String panchayat="mypanchayat";

        when(locationService.fetchFor(district,block,panchayat)).thenReturn(new LocationDimension(district, block, panchayat, "VALID"));
        mockMvc(locationController)
                .perform(get("/location").param("district", district).param("block", block).param("panchayat", panchayat))
                .andExpect(status().isOk())
                .andExpect(content().type(HttpConstants.JSON_CONTENT_TYPE))
                .andExpect(content().string(new Contains("\"district\":\"mydistrict\"")))
                .andExpect(content().string(new Contains("\"block\":\"myblock\"")))
                .andExpect(content().string(new Contains("\"district\":\"mydistrict\"")));
    }

    @Test
    public void shouldReturn404IfNoneOfTheLocationParametersArePresent() throws Exception {
        String district = "mydistrict";
        String block="myblock";
        String panchayat="mypanchayat";

        when(locationService.fetchFor(district,block,panchayat)).thenReturn(new LocationDimension(district, block, panchayat, "VALID"));
        mockMvc(locationController)
                .perform(get("/location"))
                .andExpect(status().isNotFound())
                .andExpect(content().type(HttpConstants.JSON_CONTENT_TYPE))
                .andExpect(content().string(new Contains("\"status\":\"ERROR\"")))
                .andExpect(content().string(new Contains("\"description\":\"location not found\"")));
    }

    @Test
    public void shouldUpdateLocation() throws Exception {
        LocationSyncRequest expectedLocationRequest = new LocationSyncRequest(new LocationRequest("D1","B1","P1"), new LocationRequest("D1","B1","P1"), LocationStatus.VALID.name(), null);

        mockMvc(locationController)
                .perform(post("/location")
                        .body(TestUtils.toJson(expectedLocationRequest).getBytes())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(locationService).addOrUpdate(expectedLocationRequest);
    }

    @Test
    public void shouldReturn404ResponseIfLocationNotFound() throws Exception {
        String district = "mydistrict";
        String block="myblock";
        String panchayat="mypanchayat";

        when(locationService.fetchFor(district,block,panchayat)).thenReturn(null);
        mockMvc(locationController)
                .perform(get("/location").param("district", district).param("block", block).param("panchayat", panchayat))
                .andExpect(status().isNotFound())
                .andExpect(content().type(HttpConstants.JSON_CONTENT_TYPE))
                .andExpect(content().string(new Contains("\"status\":\"ERROR\"")))
                .andExpect(content().string(new Contains("\"description\":\"location not found\"")));
    }
}
