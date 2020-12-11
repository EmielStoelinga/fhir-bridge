# Copyright (c) 2020 P. Wohlfarth (Appsfactory GmbH), Wladislaw Wagner (Vitasystems GmbH) &
# Dave Petzold (Appsfactory GmbH)
#
# This file is part of Project EHRbase
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.



*** Settings ***
Resource                ${EXECDIR}/robot/_resources/suite_settings.robot

Test Setup              generic.prepare new request session    Prefer=return=representation
...															   Authorization=Basic bXl1c2VyOm15UGFzc3dvcmQ0MzI=

Force Tags              create



*** Variables ***




*** Test Cases ***
001 Create Diagnose Condition
	[Documentation]    1. create new EHR Patient record
	...                2. update example json patient id
    ...                3. post example json to observation endpoint
	...                4. validate the response status
    [Tags]              diagnose-condition    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create diagnose condition    create-condition-default.json
    condition.validate response - 201


002 Create Condition Using Invalid Profile
    [Documentation]     1. create EHR
    ...                 2. trigger condition endpoint using invalid payload
    [Tags]              invalid

    ehr.create new ehr    000_ehr_status.json
    condition.create diagnose condition    create-condition-with-invalid-profile.json
    condition.validate response - 422 (Unprocessable Entity)


003 Create Condition Symptoms-Covid-19 Present
    [Documentation]     1. create EHR
    ...                 2. create condition symptos-covid-19 present
    [Tags]              symptoms-covid-19    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create symptoms-covid-19    create-symptom-covid-19-present.json
    condition.validate response - 201


004 Create Condition Symptoms-Covid-19 Absent
    [Documentation]     1. create EHR
    ...                 2. create condition symptos-covid-19 present
    [Tags]              symptoms-covid-19    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create symptoms-covid-19    create-symptom-covid-19-absent.json
    condition.validate response - 201


005 Create Condition Symptoms-Covid-19 Unknown
    [Documentation]     1. create EHR
    ...                 2. create condition symptos-covid-19 present
    [Tags]              symptoms-covid-19    valid

    ehr.create new ehr    000_ehr_status.json
    condition.create symptoms-covid-19    create-symptom-covid-19-unknown.json
    condition.validate response - 201


006 Create Condition Diabetes Mellitus
	[Documentation]    1. create new EHR Patient record
	...                2. update example json patient id
    ...                3. post example json to observation endpoint
	...                4. validate the response status
    [Tags]             diabetes-mellitus    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create diabetes mellitus    Diabetes Mellitus    create-diabetes-mellitus.json
    condition.validate response - 201


007 Create Condition Diabetes Mellitus Type 1
	[Documentation]    1. create new EHR Patient record
	...                2. update example json patient id
    ...                3. post example json to observation endpoint
	...                4. validate the response status
    [Tags]             diabetes-mellitus    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create diabetes mellitus    Diabetes Mellitus Type 1    create-diabetes-mellitus-type-1.json
    condition.validate response - 201


008 Create Condition Diabetes Mellitus Type 2
	[Documentation]    1. create new EHR Patient record
	...                2. update example json patient id
    ...                3. post example json to observation endpoint
	...                4. validate the response status
    [Tags]             diabetes-mellitus    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create diabetes mellitus    Diabetes Mellitus Type 2    create-diabetes-mellitus-type-2.json
    condition.validate response - 201


009 Create Condition Diabetes Mellitus Type 2 Insulin Treated
	[Documentation]    1. create new EHR Patient record
	...                2. update example json patient id
    ...                3. post example json to observation endpoint
	...                4. validate the response status
    [Tags]             diabetes-mellitus    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create diabetes mellitus    Diabetes Mellitus Type 2 Insulin Treated    create-diabetes-mellitus-type-2-insulin-treated.json
    condition.validate response - 201


010 Create Rheumatological Immunological Diseases (Rheumatism)
	[Documentation]    1. create new EHR Patient record
	...                2. update example json patient id
    ...                3. post example json to observation endpoint
	...                4. validate the response status
    [Tags]             diabetes-mellitus    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create rheumatological immunological diseases    Rheumatological Immunological Diseases (Rheumatism)    create-rheumatological-immunological-diseases-rheumatism.json
    condition.validate response - 201


011 Create Rheumatological Immunological Diseases (Rheumatoid Arthritis)
	[Documentation]    1. create new EHR Patient record
	...                2. update example json patient id
    ...                3. post example json to observation endpoint
	...                4. validate the response status
    [Tags]             diabetes-mellitus    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create rheumatological immunological diseases    Rheumatological Immunological Diseases (Rheumatoid Arthritis)    create-rheumatological-immunological-diseases-rheumatoid-arthritis.json
    condition.validate response - 201


012 Create Chronic Lung Diseases
	[Documentation]    1. create new EHR Patient record
	...                2. update example json patient id
    ...                3. post example json to observation endpoint
	...                4. validate the response status
    [Tags]             chronic-lung-diseases    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic lung disease    create-chronic-lung-disease.json
    condition.validate response - 201


013 Create Chronic Obstructive Lung Disease
	[Documentation]    1. create new EHR Patient record
	...                2. update example json patient id
    ...                3. post example json to observation endpoint
	...                4. validate the response status
    [Tags]             chronic-lung-diseases    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic obstructive lung disease    create-chronic-obstructive-lung-disease.json
    condition.validate response - 201


014 Create Chronic Lung Disease (Fibrosis of Lung)
	[Documentation]    1. create new EHR Patient record
	...                2. update example json patient id
    ...                3. post example json to observation endpoint
	...                4. validate the response status
    [Tags]             chronic-lung-diseases    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic Lung Disease (Fibrosis of Lung)    create-chronic-lung-disease-fibrosis-of-lung.json
    condition.validate response - 201


015 Create Chronic Lung Disease (Asthma)
	[Documentation]    1. create new EHR Patient record
	...                2. update example json patient id
    ...                3. post example json to observation endpoint
	...                4. validate the response status
    [Tags]             chronic-lung-diseases    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic Lung Disease (Asthma)    create-chronic-lung-disease-asthma.json
    condition.validate response - 201


016 Create Chronic Lung Disease (Cystic Fibrosis)
	[Documentation]    1. create new EHR Patient record
	...                2. update example json patient id
    ...                3. post example json to observation endpoint
	...                4. validate the response status
    [Tags]             chronic-lung-diseases    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic Lung Disease (Cystic Fibrosis)    create-chronic-lung-disease-cystic-fibrosis.json
    condition.validate response - 201


017 Create Chronic Lung Disease (Extreme Obesity with Alveolar Hypoventilation)
	[Documentation]    1. create new EHR Patient record
	...                2. update example json patient id
    ...                3. post example json to observation endpoint
	...                4. validate the response status
    [Tags]             chronic-lung-diseases    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic Lung Disease (Extreme Obesity with Alveolar Hypoventilation)    create-chronic-lung-disease-with-alveolar-hypoventilation.json
    condition.validate response - 201


018 Create Chronic Lung Disease (Pulmonary hypertension)
	[Documentation]    1. create new EHR Patient record
	...                2. update example json patient id
    ...                3. post example json to observation endpoint
	...                4. validate the response status
    [Tags]             chronic-lung-diseases    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic Lung Disease (Pulmonary hypertension)    create-chronic-lung-disease-pulmonary-hypertension.json
    condition.validate response - 201


019 Create Chronic Lung Disease (Sleep Apnea)
	[Documentation]    1. create new EHR Patient record
	...                2. update example json patient id
    ...                3. post example json to observation endpoint
	...                4. validate the response status
    [Tags]             chronic-lung-diseases    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic Lung Disease (Sleep Apnea)    create-chronic-lung-disease-sleep-apnea.json
    condition.validate response - 201


020 Create Chronic Lung Disease (Obstructive Sleep Apnea Syndrome)
	[Documentation]    1. create new EHR Patient record
	...                2. update example json patient id
    ...                3. post example json to observation endpoint
	...                4. validate the response status
    [Tags]             chronic-lung-diseases    valid    not-ready

    ehr.create new ehr    000_ehr_status.json
    condition.create chronic lung diseases    Chronic Lung Disease (Obstructive Sleep Apnea Syndrome)    create-chronic-lung-disease-obstructive-sleep-apnea.json
    condition.validate response - 201
