package org.openmrs.module.reporting.cohort.definition.evaluator;


import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.Cohort;
import org.openmrs.Concept;
import org.openmrs.Location;
import org.openmrs.api.PatientSetService.TimeModifier;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.cohort.definition.CohortDefinition;
import org.openmrs.module.reporting.cohort.definition.TextObsCohortDefinition;
import org.openmrs.module.reporting.cohort.definition.service.CohortDefinitionService;
import org.openmrs.module.reporting.common.DateUtil;
import org.openmrs.module.reporting.common.SetComparator;
import org.openmrs.module.reporting.evaluation.EvaluationContext;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.openmrs.test.Verifies;

public class TextObsCohortDefinitionEvaluatorTest extends BaseModuleContextSensitiveTest {
	
	@Before
	public void setup() throws Exception {
		executeDataSet("org/openmrs/module/reporting/include/ReportTestDataset.xml");
	}

	/**
	 * @see {@link TextObsCohortDefinitionEvaluator#evaluate(CohortDefinition,EvaluationContext)}
	 */
	@Test
	@Verifies(value = "should test any with many properties specified", method = "evaluate(CohortDefinition,EvaluationContext)")
	public void evaluate_shouldTestAnyWithManyPropertiesSpecified() throws Exception {
		//<obs obs_id="12" person_id="7" concept_id="19" encounter_id="4" obs_datetime="2008-08-15 00:00:00.0" location_id="1" value_text="PB and J" comments="" creator="1" date_created="2008-08-19 12:32:03.0" voided="false" uuid="e26cea2c-1b9f-4afe-b211-f3ef6c88af6f"/>
		TextObsCohortDefinition cd = new TextObsCohortDefinition();
		cd.setTimeModifier(TimeModifier.ANY);
		cd.setQuestion(new Concept(19)); // favorite food, in the reporting test dataset
		cd.setOperator(SetComparator.IN);
		cd.setValueList(Collections.singletonList("PB and J"));
		cd.setOnOrAfter(DateUtil.getDateTime(2008, 8, 14));
		cd.setOnOrBefore(DateUtil.getDateTime(2008, 8, 16));
		cd.setLocationList(Collections.singletonList(new Location(1)));
		Cohort cohort = Context.getService(CohortDefinitionService.class).evaluate(cd, null);
		Assert.assertEquals(1, cohort.size());
		Assert.assertTrue(cohort.contains(7));
	}
	
	/**
	 * @see {@link TextObsCohortDefinitionEvaluator#evaluate(CohortDefinition,EvaluationContext)}
	 */
	@Test
	@Verifies(value = "should test last with many properties specified", method = "evaluate(CohortDefinition,EvaluationContext)")
	public void evaluate_shouldTestLastWithManyPropertiesSpecified() throws Exception {
		TextObsCohortDefinition cd = new TextObsCohortDefinition();
		cd.setTimeModifier(TimeModifier.LAST);
		cd.setQuestion(new Concept(19)); // favorite food, in the reporting test dataset
		cd.setOperator(SetComparator.IN);
		cd.setValueList(Collections.singletonList("PB and J"));
		Cohort cohort = Context.getService(CohortDefinitionService.class).evaluate(cd, null);
		Assert.assertEquals(1, cohort.size());
		Assert.assertTrue(cohort.contains(7));
	}
}