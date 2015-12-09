package com.hongjie.web.rest;

import com.hongjie.Application;
import com.hongjie.domain.ProductRelate;
import com.hongjie.repository.ProductRelateRepository;

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
 * Test class for the ProductRelateResource REST controller.
 *
 * @see ProductRelateResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class ProductRelateResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";

    @Inject
    private ProductRelateRepository productRelateRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restProductRelateMockMvc;

    private ProductRelate productRelate;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ProductRelateResource productRelateResource = new ProductRelateResource();
        ReflectionTestUtils.setField(productRelateResource, "productRelateRepository", productRelateRepository);
        this.restProductRelateMockMvc = MockMvcBuilders.standaloneSetup(productRelateResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        productRelate = new ProductRelate();
        productRelate.setName(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createProductRelate() throws Exception {
        int databaseSizeBeforeCreate = productRelateRepository.findAll().size();

        // Create the ProductRelate

        restProductRelateMockMvc.perform(post("/api/productRelates")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(productRelate)))
                .andExpect(status().isCreated());

        // Validate the ProductRelate in the database
        List<ProductRelate> productRelates = productRelateRepository.findAll();
        assertThat(productRelates).hasSize(databaseSizeBeforeCreate + 1);
        ProductRelate testProductRelate = productRelates.get(productRelates.size() - 1);
        assertThat(testProductRelate.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void getAllProductRelates() throws Exception {
        // Initialize the database
        productRelateRepository.saveAndFlush(productRelate);

        // Get all the productRelates
        restProductRelateMockMvc.perform(get("/api/productRelates"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(productRelate.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())));
    }

    @Test
    @Transactional
    public void getProductRelate() throws Exception {
        // Initialize the database
        productRelateRepository.saveAndFlush(productRelate);

        // Get the productRelate
        restProductRelateMockMvc.perform(get("/api/productRelates/{id}", productRelate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(productRelate.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingProductRelate() throws Exception {
        // Get the productRelate
        restProductRelateMockMvc.perform(get("/api/productRelates/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProductRelate() throws Exception {
        // Initialize the database
        productRelateRepository.saveAndFlush(productRelate);

		int databaseSizeBeforeUpdate = productRelateRepository.findAll().size();

        // Update the productRelate
        productRelate.setName(UPDATED_NAME);

        restProductRelateMockMvc.perform(put("/api/productRelates")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(productRelate)))
                .andExpect(status().isOk());

        // Validate the ProductRelate in the database
        List<ProductRelate> productRelates = productRelateRepository.findAll();
        assertThat(productRelates).hasSize(databaseSizeBeforeUpdate);
        ProductRelate testProductRelate = productRelates.get(productRelates.size() - 1);
        assertThat(testProductRelate.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void deleteProductRelate() throws Exception {
        // Initialize the database
        productRelateRepository.saveAndFlush(productRelate);

		int databaseSizeBeforeDelete = productRelateRepository.findAll().size();

        // Get the productRelate
        restProductRelateMockMvc.perform(delete("/api/productRelates/{id}", productRelate.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<ProductRelate> productRelates = productRelateRepository.findAll();
        assertThat(productRelates).hasSize(databaseSizeBeforeDelete - 1);
    }
}
