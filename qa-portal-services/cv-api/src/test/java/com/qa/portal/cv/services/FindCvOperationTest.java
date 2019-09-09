package com.qa.portal.cv.services;

import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.persistence.repository.CvVersionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
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
