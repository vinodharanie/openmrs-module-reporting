/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.reporting.query.obs.evaluator;

import org.openmrs.annotation.Handler;
import org.openmrs.api.context.Context;
import org.openmrs.module.reporting.evaluation.EvaluationContext;
import org.openmrs.module.reporting.evaluation.EvaluationException;
import org.openmrs.module.reporting.query.obs.ObsQueryResult;
import org.openmrs.module.reporting.query.obs.definition.MappedParametersObsQuery;
import org.openmrs.module.reporting.query.obs.definition.ObsQuery;
import org.openmrs.module.reporting.query.obs.service.ObsQueryService;

@Handler(supports = MappedParametersObsQuery.class)
public class MappedParametersObsQueryEvaluator implements ObsQueryEvaluator {

    @Override
    public ObsQueryResult evaluate(ObsQuery cohortDefinition, EvaluationContext context) throws EvaluationException {
        MappedParametersObsQuery q = (MappedParametersObsQuery) cohortDefinition;
        ObsQueryService service = Context.getService(ObsQueryService.class);
        ObsQueryResult evaluated = service.evaluate(q.getWrapped(), context);
        evaluated.setDefinition(q);
        return evaluated;
    }


}
