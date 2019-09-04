package com.qa.portal.cv.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Map;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.portal.cv.domain.CvSearchCriteria;
import com.qa.portal.cv.domain.CvVersion;
import com.qa.portal.cv.persistence.repository.CvVersionRepository;
import com.qa.portal.cv.services.CvSearchOperation;

import antlr.collections.List;




@RunWith(MockitoJUnitRunner.class)


public class CvSearchTester {
	
	@InjectMocks
	private CvSearchOperation cvo;

	@Mock
	private CvVersionRepository cvRepo;

//	   public static void main(String[] args){
//		   CvSearchTester tester = new CvSearchTester();
//	      tester.setUp();
//	      System.out.println(tester.testCvValue()?"pass":"fail");
//	   }
	   		
	   @Test
	   public void testCvValue(){
	
		   ArrayList<CvSearchCriteria> cvs = new ArrayList<CvSearchCriteria>();
		   CvSearchCriteria jake = new CvSearchCriteria("Jake Fellows","Complete", "June", "Java");
		   cvs.add(jake);

		   CvSearchOperation cvServiceMock = mock(CvSearchOperation.class);
		   
		   when(cvServiceMock.findByCriteria(jake)).thenReturn((java.util.List<CvVersion>) jake);
   		   assertEquals(jake, cvServiceMock);
		   
		   verify(cvo).findByCriteria(jake);
			   
		   }

		  
	   }
