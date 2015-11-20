package com.hongjie.web.rest;

import com.hongjie.Application;
import com.hongjie.domain.Dictionary;
import com.hongjie.repository.DictionaryRepository;

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
 * Test class for the DictionaryResource REST controller.
 *
 * @see DictionaryResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class DictionaryResourceIntTest {

    private static final String DEFAULT_IDENTIFER = "AAAAA";
    private static final String UPDATED_IDENTIFER = "BBBBB";
    private static final String DEFAULT_OVALUE = "AAAAA";
    private static final String UPDATED_OVALUE = "BBBBB";
    private static final String DEFAULT_DESCRIPTION = "AAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBB";

    @Inject
    private DictionaryRepository dictionaryRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restDictionaryMockMvc;

    private Dictionary dictionary;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        DictionaryResource dictionaryResource = new DictionaryResource();
        ReflectionTestUtils.setField(dictionaryResource, "dictionaryRepository", dictionaryRepository);
        this.restDictionaryMockMvc = MockMvcBuilders.standaloneSetup(dictionaryResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        dictionary = new Dictionary();
        dictionary.setIdentifer(DEFAULT_IDENTIFER);
        dictionary.setOvalue(DEFAULT_OVALUE);
        dictionary.setDescription(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createDictionary() throws Exception {
        int databaseSizeBeforeCreate = dictionaryRepository.findAll().size();

        // Create the Dictionary

        restDictionaryMockMvc.perform(post("/api/dictionarys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dictionary)))
                .andExpect(status().isCreated());

        // Validate the Dictionary in the database
        List<Dictionary> dictionarys = dictionaryRepository.findAll();
        assertThat(dictionarys).hasSize(databaseSizeBeforeCreate + 1);
        Dictionary testDictionary = dictionarys.get(dictionarys.size() - 1);
        assertThat(testDictionary.getIdentifer()).isEqualTo(DEFAULT_IDENTIFER);
        assertThat(testDictionary.getOvalue()).isEqualTo(DEFAULT_OVALUE);
        assertThat(testDictionary.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllDictionarys() throws Exception {
        // Initialize the database
        dictionaryRepository.saveAndFlush(dictionary);

        // Get all the dictionarys
        restDictionaryMockMvc.perform(get("/api/dictionarys"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(dictionary.getId().intValue())))
                .andExpect(jsonPath("$.[*].identifer").value(hasItem(DEFAULT_IDENTIFER.toString())))
                .andExpect(jsonPath("$.[*].ovalue").value(hasItem(DEFAULT_OVALUE.toString())))
                .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())));
    }

    @Test
    @Transactional
    public void getDictionary() throws Exception {
        // Initialize the database
        dictionaryRepository.saveAndFlush(dictionary);

        // Get the dictionary
        restDictionaryMockMvc.perform(get("/api/dictionarys/{id}", dictionary.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(dictionary.getId().intValue()))
            .andExpect(jsonPath("$.identifer").value(DEFAULT_IDENTIFER.toString()))
            .andExpect(jsonPath("$.ovalue").value(DEFAULT_OVALUE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDictionary() throws Exception {
        // Get the dictionary
        restDictionaryMockMvc.perform(get("/api/dictionarys/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDictionary() throws Exception {
        // Initialize the database
        dictionaryRepository.saveAndFlush(dictionary);

		int databaseSizeBeforeUpdate = dictionaryRepository.findAll().size();

        // Update the dictionary
        dictionary.setIdentifer(UPDATED_IDENTIFER);
        dictionary.setOvalue(UPDATED_OVALUE);
        dictionary.setDescription(UPDATED_DESCRIPTION);

        restDictionaryMockMvc.perform(put("/api/dictionarys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dictionary)))
                .andExpect(status().isOk());

        // Validate the Dictionary in the database
        List<Dictionary> dictionarys = dictionaryRepository.findAll();
        assertThat(dictionarys).hasSize(databaseSizeBeforeUpdate);
        Dictionary testDictionary = dictionarys.get(dictionarys.size() - 1);
        assertThat(testDictionary.getIdentifer()).isEqualTo(UPDATED_IDENTIFER);
        assertThat(testDictionary.getOvalue()).isEqualTo(UPDATED_OVALUE);
        assertThat(testDictionary.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void deleteDictionary() throws Exception {
        // Initialize the database
        dictionaryRepository.saveAndFlush(dictionary);

		int databaseSizeBeforeDelete = dictionaryRepository.findAll().size();

        // Get the dictionary
        restDictionaryMockMvc.perform(delete("/api/dictionarys/{id}", dictionary.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Dictionary> dictionarys = dictionaryRepository.findAll();
        assertThat(dictionarys).hasSize(databaseSizeBeforeDelete - 1);
    }
}
