/*
 * Copyright 2014 Open mHealth
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.openmhealth.data.generator.service;

import org.openmhealth.data.generator.domain.MeasureGroup;
import org.openmhealth.schema.pojos.DataPoint;
import org.openmhealth.schema.pojos.builder.BloodPressureBuilder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Emerson Farrugia
 */
@Service
public class BloodPressureDataPointGenerationServiceImpl extends AbstractDataPointGenerationServiceImpl
        implements DataPointGenerationService {

    @Override
    public Iterable<DataPoint> generateDataPoints(Iterable<MeasureGroup> measureGroups) {

        List<DataPoint> dataPoints = new ArrayList<>();

        for (MeasureGroup measureGroup : measureGroups) {

            BloodPressureBuilder builder = new BloodPressureBuilder();

            builder.setTimeTaken(convert(measureGroup.getEffectiveDateTime()));

            builder.setValues(
                    new BigDecimal(measureGroup.getMeasureValue("systolic")),
                    new BigDecimal(measureGroup.getMeasureValue("diastolic")));

            dataPoints.add(newDataPoint(builder.build(), "withings"));
        }

        return dataPoints;
    }
}
