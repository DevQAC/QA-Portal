package com.qa.portal.cv.services;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import java.util.List;

import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.persistence.repository.CvVersionRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FindCvOperationTest {

    @InjectMocks
    private GetCurrentCvVersionOperation gccvo;

    @Mock
    private CvVersionRepository repo;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        // userOne = new gccvo("Chad", "Thunder");
    }

    @Test
    public void findAllAccounts_Test() {
        List<CvVersion> refData;
        refData = gccvo.getAll();

        verify(repo).findAll();
    }

    // check CV object has given a verison number
    @Test
    public void FindVersionNumber_Test() {
        List<CvVersion> refData;
        refData = gccvo.getAll();

    }

}