package com.qa.portal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
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
public class ReferenceDataTests {

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
   // getReferenceDataForCategories should have called 'findAll' method on all three repositories
    public void testGetReferenceDataForCategories_callsFindAll() {
        Map<String, List<String>> refData;        
        refData = rdm.getReferenceDataForCategories();        
       
        verify(techRepo).findAll();   
        verify(statusRepo).findAll();    
        verify(cohortRepo).findAll();            
    }
   
   @Test
// The returned map should contain cohort
   public void testGetReferenceDataForCategories_includeskeys() {
       Map<String, List<String>> refData;        
       refData = rdm.getReferenceDataForCategories();    
       
       assertTrue(refData.containsKey("cohort"));  
       assertTrue(refData.containsKey("technology"));
   }
   
   @Test
   // the returned map should not contain cohorts (just for trivial example of assertFalse)
   public void testGetReferenceDataForCategories_wrongkeys() {
       Map<String, List<String>> refData;        
       refData = rdm.getReferenceDataForCategories();         
      
       assertFalse(refData.containsKey("cohorts"));     

   }
   @Test
   // the returned map should not contain cohorts (just for trivial example of assertFalse)
   public void testGetReferenceDataForCategories_ListHasValues() {
       Map<String, List<String>> refData;        
       refData = rdm.getReferenceDataForCategories(); 
     
       assertTrue("List has values", refData.values().size() > 0);     
       assertTrue("List has keys", refData.keySet().size() > 0);
     
   }
 
}

