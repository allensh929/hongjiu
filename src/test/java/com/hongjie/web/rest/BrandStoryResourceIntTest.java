package com.hongjie.web.rest;

import com.hongjie.Application;
import com.hongjie.domain.BrandStory;
import com.hongjie.repository.BrandStoryRepository;

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
 * Test class for the BrandStoryResource REST controller.
 *
 * @see BrandStoryResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class BrandStoryResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAA";
    private static final String UPDATED_NAME = "BBBBB";
    private static final String DEFAULT_TITLE = "AAAAA";
    private static final String UPDATED_TITLE = "BBBBB";
    private static final String DEFAULT_SLIDE1 = "AAAAA";
    private static final String UPDATED_SLIDE1 = "BBBBB";
    private static final String DEFAULT_SLIDE2 = "AAAAA";
    private static final String UPDATED_SLIDE2 = "BBBBB";
    private static final String DEFAULT_SLIDE3 = "AAAAA";
    private static final String UPDATED_SLIDE3 = "BBBBB";
    private static final String DEFAULT_DETAIL_INFO = "AAAAA";
    private static final String UPDATED_DETAIL_INFO = "BBBBB";

    private static final Boolean DEFAULT_ACTIVE = false;
    private static final Boolean UPDATED_ACTIVE = true;
    private static final String DEFAULT_ORDER_TAG = "AAAAA";
    private static final String UPDATED_ORDER_TAG = "BBBBB";
    private static final String DEFAULT_PLACEHOLDER1 = "AAAAA";
    private static final String UPDATED_PLACEHOLDER1 = "BBBBB";
    private static final String DEFAULT_PLACEHOLDER2 = "AAAAA";
    private static final String UPDATED_PLACEHOLDER2 = "BBBBB";

    @Inject
    private BrandStoryRepository brandStoryRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restBrandStoryMockMvc;

    private BrandStory brandStory;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BrandStoryResource brandStoryResource = new BrandStoryResource();
        ReflectionTestUtils.setField(brandStoryResource, "brandStoryRepository", brandStoryRepository);
        this.restBrandStoryMockMvc = MockMvcBuilders.standaloneSetup(brandStoryResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        brandStory = new BrandStory();
        brandStory.setName(DEFAULT_NAME);
        brandStory.setTitle(DEFAULT_TITLE);
        brandStory.setSlide1(DEFAULT_SLIDE1);
        brandStory.setSlide2(DEFAULT_SLIDE2);
        brandStory.setSlide3(DEFAULT_SLIDE3);
        brandStory.setDetailInfo(DEFAULT_DETAIL_INFO);
        brandStory.setActive(DEFAULT_ACTIVE);
        brandStory.setOrderTag(DEFAULT_ORDER_TAG);
        brandStory.setPlaceholder1(DEFAULT_PLACEHOLDER1);
        brandStory.setPlaceholder2(DEFAULT_PLACEHOLDER2);
    }

    @Test
    @Transactional
    public void createBrandStory() throws Exception {
        int databaseSizeBeforeCreate = brandStoryRepository.findAll().size();

        // Create the BrandStory

        restBrandStoryMockMvc.perform(post("/api/brandStorys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(brandStory)))
                .andExpect(status().isCreated());

        // Validate the BrandStory in the database
        List<BrandStory> brandStorys = brandStoryRepository.findAll();
        assertThat(brandStorys).hasSize(databaseSizeBeforeCreate + 1);
        BrandStory testBrandStory = brandStorys.get(brandStorys.size() - 1);
        assertThat(testBrandStory.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testBrandStory.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testBrandStory.getSlide1()).isEqualTo(DEFAULT_SLIDE1);
        assertThat(testBrandStory.getSlide2()).isEqualTo(DEFAULT_SLIDE2);
        assertThat(testBrandStory.getSlide3()).isEqualTo(DEFAULT_SLIDE3);
        assertThat(testBrandStory.getDetailInfo()).isEqualTo(DEFAULT_DETAIL_INFO);
        assertThat(testBrandStory.getActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testBrandStory.getOrderTag()).isEqualTo(DEFAULT_ORDER_TAG);
        assertThat(testBrandStory.getPlaceholder1()).isEqualTo(DEFAULT_PLACEHOLDER1);
        assertThat(testBrandStory.getPlaceholder2()).isEqualTo(DEFAULT_PLACEHOLDER2);
    }

    @Test
    @Transactional
    public void getAllBrandStorys() throws Exception {
        // Initialize the database
        brandStoryRepository.saveAndFlush(brandStory);

        // Get all the brandStorys
        restBrandStoryMockMvc.perform(get("/api/brandStorys"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(brandStory.getId().intValue())))
                .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
                .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
                .andExpect(jsonPath("$.[*].slide1").value(hasItem(DEFAULT_SLIDE1.toString())))
                .andExpect(jsonPath("$.[*].slide2").value(hasItem(DEFAULT_SLIDE2.toString())))
                .andExpect(jsonPath("$.[*].slide3").value(hasItem(DEFAULT_SLIDE3.toString())))
                .andExpect(jsonPath("$.[*].detailInfo").value(hasItem(DEFAULT_DETAIL_INFO.toString())))
                .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.booleanValue())))
                .andExpect(jsonPath("$.[*].orderTag").value(hasItem(DEFAULT_ORDER_TAG.toString())))
                .andExpect(jsonPath("$.[*].placeholder1").value(hasItem(DEFAULT_PLACEHOLDER1.toString())))
                .andExpect(jsonPath("$.[*].placeholder2").value(hasItem(DEFAULT_PLACEHOLDER2.toString())));
    }

    @Test
    @Transactional
    public void getBrandStory() throws Exception {
        // Initialize the database
        brandStoryRepository.saveAndFlush(brandStory);

        // Get the brandStory
        restBrandStoryMockMvc.perform(get("/api/brandStorys/{id}", brandStory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(brandStory.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.slide1").value(DEFAULT_SLIDE1.toString()))
            .andExpect(jsonPath("$.slide2").value(DEFAULT_SLIDE2.toString()))
            .andExpect(jsonPath("$.slide3").value(DEFAULT_SLIDE3.toString()))
            .andExpect(jsonPath("$.detailInfo").value(DEFAULT_DETAIL_INFO.toString()))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.orderTag").value(DEFAULT_ORDER_TAG.toString()))
            .andExpect(jsonPath("$.placeholder1").value(DEFAULT_PLACEHOLDER1.toString()))
            .andExpect(jsonPath("$.placeholder2").value(DEFAULT_PLACEHOLDER2.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingBrandStory() throws Exception {
        // Get the brandStory
        restBrandStoryMockMvc.perform(get("/api/brandStorys/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBrandStory() throws Exception {
        // Initialize the database
        brandStoryRepository.saveAndFlush(brandStory);

		int databaseSizeBeforeUpdate = brandStoryRepository.findAll().size();

        // Update the brandStory
        brandStory.setName(UPDATED_NAME);
        brandStory.setTitle(UPDATED_TITLE);
        brandStory.setSlide1(UPDATED_SLIDE1);
        brandStory.setSlide2(UPDATED_SLIDE2);
        brandStory.setSlide3(UPDATED_SLIDE3);
        brandStory.setDetailInfo(UPDATED_DETAIL_INFO);
        brandStory.setActive(UPDATED_ACTIVE);
        brandStory.setOrderTag(UPDATED_ORDER_TAG);
        brandStory.setPlaceholder1(UPDATED_PLACEHOLDER1);
        brandStory.setPlaceholder2(UPDATED_PLACEHOLDER2);

        restBrandStoryMockMvc.perform(put("/api/brandStorys")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(brandStory)))
                .andExpect(status().isOk());

        // Validate the BrandStory in the database
        List<BrandStory> brandStorys = brandStoryRepository.findAll();
        assertThat(brandStorys).hasSize(databaseSizeBeforeUpdate);
        BrandStory testBrandStory = brandStorys.get(brandStorys.size() - 1);
        assertThat(testBrandStory.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testBrandStory.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testBrandStory.getSlide1()).isEqualTo(UPDATED_SLIDE1);
        assertThat(testBrandStory.getSlide2()).isEqualTo(UPDATED_SLIDE2);
        assertThat(testBrandStory.getSlide3()).isEqualTo(UPDATED_SLIDE3);
        assertThat(testBrandStory.getDetailInfo()).isEqualTo(UPDATED_DETAIL_INFO);
        assertThat(testBrandStory.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testBrandStory.getOrderTag()).isEqualTo(UPDATED_ORDER_TAG);
        assertThat(testBrandStory.getPlaceholder1()).isEqualTo(UPDATED_PLACEHOLDER1);
        assertThat(testBrandStory.getPlaceholder2()).isEqualTo(UPDATED_PLACEHOLDER2);
    }

    @Test
    @Transactional
    public void deleteBrandStory() throws Exception {
        // Initialize the database
        brandStoryRepository.saveAndFlush(brandStory);

		int databaseSizeBeforeDelete = brandStoryRepository.findAll().size();

        // Get the brandStory
        restBrandStoryMockMvc.perform(delete("/api/brandStorys/{id}", brandStory.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<BrandStory> brandStorys = brandStoryRepository.findAll();
        assertThat(brandStorys).hasSize(databaseSizeBeforeDelete - 1);
    }
}
