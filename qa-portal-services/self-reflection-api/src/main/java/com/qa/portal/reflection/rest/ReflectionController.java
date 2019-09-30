package com.qa.portal.reflection.rest;

import com.qa.portal.common.dto.QaUserDto;
import com.qa.portal.common.security.QaSecurityContext;
import com.qa.portal.reflection.dto.CohortSummaryDto;
import com.qa.portal.reflection.dto.ReflectionDto;
import com.qa.portal.reflection.service.ReflectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/reflection")
public class ReflectionController {

	private final Logger LOGGER = LoggerFactory.getLogger(ReflectionController.class);

	private ReflectionService service;

	private QaSecurityContext context;

	@Autowired
	public ReflectionController(ReflectionService service, QaSecurityContext context) {
		this.service = service;
		this.context = context;
	}

    @GetMapping("/summary")
    public ResponseEntity<List<CohortSummaryDto>> getCohortSummaryDto() {
        return ResponseEntity.ok(this.service.getCohortSummaryDto());
    }

    @GetMapping("/trainee")
    public ResponseEntity<List<ReflectionDto>> getSelfReflectionsForTrainee() {
	    LOGGER.info("In get Self reflections for trainee");
        return ResponseEntity.ok(this.service.getSelfReflectionsForTrainee(context.getUserName()));
    }

    @GetMapping("trainee/{id}")
    public ResponseEntity<List<ReflectionDto>> getSelfReflectionsForTrainee(@PathVariable Integer id) {
        return ResponseEntity.ok(this.service.getSelfReflectionsForTrainee(id));
    }

    @GetMapping("trainee/username/{userName}")
    public ResponseEntity<List<ReflectionDto>> getSelfReflectionsByTraineeUserName(@PathVariable String userName) {
        return ResponseEntity.ok(this.service.getSelfReflectionsForTrainee(userName));
    }

    @GetMapping("/trainer/current")
    public ResponseEntity<Set<ReflectionDto>> getSelfReflectionsForTrainer() {
        return ResponseEntity.ok(this.service.getSelfReflectionsForTrainer(context.getUserName()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ReflectionDto> getSelfReflection(@PathVariable Integer id) {
        return ResponseEntity.ok(this.service.getSelfReflection(id));
    }

    @GetMapping
    public ResponseEntity<ReflectionDto> getSelfReflectionByDate(@RequestBody LocalDate date) {
        return ResponseEntity.ok(this.service.getSelfReflection(context.getUserName(), date));
    }

	@PostMapping
	public ResponseEntity<ReflectionDto> createSelfReflection(@RequestBody ReflectionDto reflection) {
    	return ResponseEntity.ok(this.service.createSelfReflection(reflection, context.getUserName()));
	}

    @GetMapping("/trainee/status/{status}")
    public ResponseEntity<ReflectionDto> getSelfReflectionByStatus(@PathVariable String status) {
        return ResponseEntity.ok(this.service.getSelfReflection(context.getUserName(), status));
    }

	@PutMapping()
	public ResponseEntity<ReflectionDto> updateSelfReflection(@RequestBody ReflectionDto reflection) {
		return ResponseEntity.ok(this.service.updateSelfReflection(reflection, context.getUserName()));
	}

    @GetMapping("/cohort/trainees/review/{id}")
    public ResponseEntity<List<QaUserDto>> getTraineesToReviewForCohort(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.service.getTraineesToReviewForCohort(id));
    }
}
