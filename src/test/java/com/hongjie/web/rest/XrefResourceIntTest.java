package com.hongjie.web.rest;

import com.hongjie.Application;
import com.hongjie.domain.Xref;
import com.hongjie.repository.XrefRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * Test class for the XrefResource REST controller.
 *
 * @see XrefResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class XrefResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";
    private static final String DEFAULT_OVALUE = "AAAAA";
    private static final String UPDATED_OVALUE = "BBBBB";

    @Inject
    private XrefRepository xrefRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restXrefMockMvc;

    private Xref xref;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        XrefResource xrefResource = new XrefResource();
        ReflectionTestUtils.setField(xrefResource, "xrefRepository", xrefRepository);
        this.restXrefMockMvc = MockMvcBuilders.standaloneSetup(xrefResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        xref = new Xref();
        xref.setName(DEFAULT_NAME);
        xref.setOvalue(DEFAULT_OVALUE);
    }

    @Test
    @Transactional
    public void createXref() throws Exception {
        int databaseSizeBeforeCreate = xrefRepository.findAll().size();

        // Create the Xref

        restXrefMockMvc.perform(post("/api/xrefs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(xref)))
                .andExpect(status().isCreated());

        // Validate the Xref in the database
        List<Xref> xrefs = xrefRepository.findAll();
        assertThat(xrefs).hasSize(databaseSizeBeforeCreate + 1);
        Xref testXref = xrefs.get(xrefs.size() - 1);
        assertThat(testXref.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testXref.getOvalue()).isEqualTo(DEFAULT_OVALUE);
    }

    @Test
    @Transactional
    public void getAllXrefs() throws Exception {
        // Initialize the database
        xrefRepository.saveAndFlush(xref);

        // Get all the xrefs
        restXrefMockMvc.perform(get("/api/xrefs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(xref.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].ovalue").value(hasItem(DEFAULT_OVALUE.toString())));
    }

    @Test
    @Transactional
    public void getXref() throws Exception {
        // Initialize the database
        xrefRepository.saveAndFlush(xref);

        // Get the xref
        restXrefMockMvc.perform(get("/api/xrefs/{id}", xref.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(xref.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.ovalue").value(DEFAULT_OVALUE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingXref() throws Exception {
        // Get the xref
        restXrefMockMvc.perform(get("/api/xrefs/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateXref() throws Exception {
        // Initialize the database
        xrefRepository.saveAndFlush(xref);

		int databaseSizeBeforeUpdate = xrefRepository.findAll().size();

        // Update the xref
        xref.setName(UPDATED_NAME);
        xref.setOvalue(UPDATED_OVALUE);

        restXrefMockMvc.perform(put("/api/xrefs")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(xref)))
                .andExpect(status().isOk());

        // Validate the Xref in the database
        List<Xref> xrefs = xrefRepository.findAll();
        assertThat(xrefs).hasSize(databaseSizeBeforeUpdate);
        Xref testXref = xrefs.get(xrefs.size() - 1);
        assertThat(testXref.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testXref.getOvalue()).isEqualTo(UPDATED_OVALUE);
    }

    @Test
    @Transactional
    public void deleteXref() throws Exception {
        // Initialize the database
        xrefRepository.saveAndFlush(xref);

		int databaseSizeBeforeDelete = xrefRepository.findAll().size();

        // Get the xref
        restXrefMockMvc.perform(delete("/api/xrefs/{id}", xref.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Xref> xrefs = xrefRepository.findAll();
        assertThat(xrefs).hasSize(databaseSizeBeforeDelete - 1);
    }
}
