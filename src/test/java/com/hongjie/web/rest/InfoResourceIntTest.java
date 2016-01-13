package com.hongjie.web.rest;

import com.hongjie.Application;
import com.hongjie.domain.Info;
import com.hongjie.repository.InfoRepository;

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
 * Test class for the InfoResource REST controller.
 *
 * @see InfoResource
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
public class InfoResourceIntTest {

    private static final String DEFAULT_QR_CODE = "AAAAA";
    private static final String UPDATED_QR_CODE = "BBBBB";
    private static final String DEFAULT_WECHAT_SUBSCRIBE_CODE = "AAAAA";
    private static final String UPDATED_WECHAT_SUBSCRIBE_CODE = "BBBBB";
    private static final String DEFAULT_WECHAT_SERVICE_CODE = "AAAAA";
    private static final String UPDATED_WECHAT_SERVICE_CODE = "BBBBB";
    private static final String DEFAULT_WEBO_URL = "AAAAA";
    private static final String UPDATED_WEBO_URL = "BBBBB";
    private static final String DEFAULT_QQ_URL = "AAAAA";
    private static final String UPDATED_QQ_URL = "BBBBB";
    private static final String DEFAULT_PLACEHOLDER1 = "AAAAA";
    private static final String UPDATED_PLACEHOLDER1 = "BBBBB";
    private static final String DEFAULT_PLACEHOLDER2 = "AAAAA";
    private static final String UPDATED_PLACEHOLDER2 = "BBBBB";

    @Inject
    private InfoRepository infoRepository;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    private MockMvc restInfoMockMvc;

    private Info info;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        InfoResource infoResource = new InfoResource();
        ReflectionTestUtils.setField(infoResource, "infoRepository", infoRepository);
        this.restInfoMockMvc = MockMvcBuilders.standaloneSetup(infoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    @Before
    public void initTest() {
        info = new Info();
        info.setQrCode(DEFAULT_QR_CODE);
        info.setWechatSubscribeCode(DEFAULT_WECHAT_SUBSCRIBE_CODE);
        info.setWechatServiceCode(DEFAULT_WECHAT_SERVICE_CODE);
        info.setWeiboUrl(DEFAULT_WEBO_URL);
        info.setQqUrl(DEFAULT_QQ_URL);
        info.setPlaceholder1(DEFAULT_PLACEHOLDER1);
        info.setPlaceholder2(DEFAULT_PLACEHOLDER2);
    }

    @Test
    @Transactional
    public void createInfo() throws Exception {
        int databaseSizeBeforeCreate = infoRepository.findAll().size();

        // Create the Info

        restInfoMockMvc.perform(post("/api/infos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(info)))
                .andExpect(status().isCreated());

        // Validate the Info in the database
        List<Info> infos = infoRepository.findAll();
        assertThat(infos).hasSize(databaseSizeBeforeCreate + 1);
        Info testInfo = infos.get(infos.size() - 1);
        assertThat(testInfo.getQrCode()).isEqualTo(DEFAULT_QR_CODE);
        assertThat(testInfo.getWechatSubscribeCode()).isEqualTo(DEFAULT_WECHAT_SUBSCRIBE_CODE);
        assertThat(testInfo.getWechatServiceCode()).isEqualTo(DEFAULT_WECHAT_SERVICE_CODE);
        assertThat(testInfo.getWeiboUrl()).isEqualTo(DEFAULT_WEBO_URL);
        assertThat(testInfo.getQqUrl()).isEqualTo(DEFAULT_QQ_URL);
        assertThat(testInfo.getPlaceholder1()).isEqualTo(DEFAULT_PLACEHOLDER1);
        assertThat(testInfo.getPlaceholder2()).isEqualTo(DEFAULT_PLACEHOLDER2);
    }

    @Test
    @Transactional
    public void getAllInfos() throws Exception {
        // Initialize the database
        infoRepository.saveAndFlush(info);

        // Get all the infos
        restInfoMockMvc.perform(get("/api/infos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[*].id").value(hasItem(info.getId().intValue())))
                .andExpect(jsonPath("$.[*].qrCode").value(hasItem(DEFAULT_QR_CODE.toString())))
                .andExpect(jsonPath("$.[*].wechatSubscribeCode").value(hasItem(DEFAULT_WECHAT_SUBSCRIBE_CODE.toString())))
                .andExpect(jsonPath("$.[*].wechatServiceCode").value(hasItem(DEFAULT_WECHAT_SERVICE_CODE.toString())))
                .andExpect(jsonPath("$.[*].weiboUrl").value(hasItem(DEFAULT_WEBO_URL.toString())))
                .andExpect(jsonPath("$.[*].qqUrl").value(hasItem(DEFAULT_QQ_URL.toString())))
                .andExpect(jsonPath("$.[*].placeholder1").value(hasItem(DEFAULT_PLACEHOLDER1.toString())))
                .andExpect(jsonPath("$.[*].placeholder2").value(hasItem(DEFAULT_PLACEHOLDER2.toString())));
    }

    @Test
    @Transactional
    public void getInfo() throws Exception {
        // Initialize the database
        infoRepository.saveAndFlush(info);

        // Get the info
        restInfoMockMvc.perform(get("/api/infos/{id}", info.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.id").value(info.getId().intValue()))
            .andExpect(jsonPath("$.qrCode").value(DEFAULT_QR_CODE.toString()))
            .andExpect(jsonPath("$.wechatSubscribeCode").value(DEFAULT_WECHAT_SUBSCRIBE_CODE.toString()))
            .andExpect(jsonPath("$.wechatServiceCode").value(DEFAULT_WECHAT_SERVICE_CODE.toString()))
            .andExpect(jsonPath("$.weiboUrl").value(DEFAULT_WEBO_URL.toString()))
            .andExpect(jsonPath("$.qqUrl").value(DEFAULT_QQ_URL.toString()))
            .andExpect(jsonPath("$.placeholder1").value(DEFAULT_PLACEHOLDER1.toString()))
            .andExpect(jsonPath("$.placeholder2").value(DEFAULT_PLACEHOLDER2.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingInfo() throws Exception {
        // Get the info
        restInfoMockMvc.perform(get("/api/infos/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateInfo() throws Exception {
        // Initialize the database
        infoRepository.saveAndFlush(info);

		int databaseSizeBeforeUpdate = infoRepository.findAll().size();

        // Update the info
        info.setQrCode(UPDATED_QR_CODE);
        info.setWechatSubscribeCode(UPDATED_WECHAT_SUBSCRIBE_CODE);
        info.setWechatServiceCode(UPDATED_WECHAT_SERVICE_CODE);
        info.setWeiboUrl(UPDATED_WEBO_URL);
        info.setQqUrl(UPDATED_QQ_URL);
        info.setPlaceholder1(UPDATED_PLACEHOLDER1);
        info.setPlaceholder2(UPDATED_PLACEHOLDER2);

        restInfoMockMvc.perform(put("/api/infos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(info)))
                .andExpect(status().isOk());

        // Validate the Info in the database
        List<Info> infos = infoRepository.findAll();
        assertThat(infos).hasSize(databaseSizeBeforeUpdate);
        Info testInfo = infos.get(infos.size() - 1);
        assertThat(testInfo.getQrCode()).isEqualTo(UPDATED_QR_CODE);
        assertThat(testInfo.getWechatSubscribeCode()).isEqualTo(UPDATED_WECHAT_SUBSCRIBE_CODE);
        assertThat(testInfo.getWechatServiceCode()).isEqualTo(UPDATED_WECHAT_SERVICE_CODE);
        assertThat(testInfo.getWeiboUrl()).isEqualTo(UPDATED_WEBO_URL);
        assertThat(testInfo.getQqUrl()).isEqualTo(UPDATED_QQ_URL);
        assertThat(testInfo.getPlaceholder1()).isEqualTo(UPDATED_PLACEHOLDER1);
        assertThat(testInfo.getPlaceholder2()).isEqualTo(UPDATED_PLACEHOLDER2);
    }

    @Test
    @Transactional
    public void deleteInfo() throws Exception {
        // Initialize the database
        infoRepository.saveAndFlush(info);

		int databaseSizeBeforeDelete = infoRepository.findAll().size();

        // Get the info
        restInfoMockMvc.perform(delete("/api/infos/{id}", info.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Info> infos = infoRepository.findAll();
        assertThat(infos).hasSize(databaseSizeBeforeDelete - 1);
    }
}
