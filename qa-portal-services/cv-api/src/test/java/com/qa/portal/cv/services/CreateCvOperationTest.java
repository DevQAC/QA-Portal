package com.qa.portal.cv.services;

import static org.mockito.Mockito.verify;

import java.util.List;

import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.persistence.repository.CvVersionRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreateCvOperationTest {

    @InjectMocks
    private GetCurrentCvVersionOperation gccvo;
    
    @Mock
    private CvVersionRepository repo;
    
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllAccounts_Test() {
        List<CvVersion> refData;
        refData = gccvo.getAll();

        verify(repo).findAll();
    }

}