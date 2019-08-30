package com.qa.portal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.portal.common.persistence.repository.CvStatusRepository;
import com.qa.portal.common.persistence.repository.QaCohortRepository;
import com.qa.portal.common.persistence.repository.TechnologyRepository;
import com.qa.portal.core.service.ReferenceDataManager;

@SpringBootTest
public class ReferenceDataManagerTests {

    @InjectMocks
    private ReferenceDataManager rdm;

    @Mock
    private QaCohortRepository cohortRepo;
    @Mock
    private CvStatusRepository statusRepo;
    @Mock
    private TechnologyRepository techRepo;



    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


   @Test
    public void testGetReferenceDataForCategories_callsFindAll() {
        Map<String, List<String>> refData;        
        refData = rdm.getReferenceDataForCategories();        
       
        // getReferenceDataForCategories should have called 'findAll' method on all three repositories
        verify(techRepo).findAll();   
        verify(statusRepo).findAll();    
        verify(cohortRepo).findAll();            
    }
   
   @Test
   public void testGetReferenceDataForCategories_includeskeys() {
       Map<String, List<String>> refData;        
       refData = rdm.getReferenceDataForCategories();
       
      // System.out.println(refData);
       
       // should contain cohort
       assertTrue(refData.containsKey("cohort"));     
         
       
   }
   
   @Test
   public void testGetReferenceDataForCategories_wrongkeys() {
       Map<String, List<String>> refData;        
       refData = rdm.getReferenceDataForCategories();  
       
       // should not contain cohorts
       assertFalse(refData.containsKey("cohorts"));      
   
   }
}
