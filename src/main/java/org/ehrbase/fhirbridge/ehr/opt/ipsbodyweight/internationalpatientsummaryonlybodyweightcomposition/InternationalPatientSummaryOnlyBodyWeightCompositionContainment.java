package org.ehrbase.fhirbridge.ehr.opt.ipsbodyweight.internationalpatientsummaryonlybodyweightcomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import org.ehrbase.fhirbridge.ehr.opt.ipsbodyweight.internationalpatientsummaryonlybodyweightcomposition.definition.BodyWeightObservation;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;

public class InternationalPatientSummaryOnlyBodyWeightCompositionContainment extends Containment {
  public SelectAqlField<InternationalPatientSummaryOnlyBodyWeightComposition> INTERNATIONAL_PATIENT_SUMMARY_ONLY_BODY_WEIGHT_COMPOSITION = new AqlFieldImp<InternationalPatientSummaryOnlyBodyWeightComposition>(InternationalPatientSummaryOnlyBodyWeightComposition.class, "", "InternationalPatientSummaryOnlyBodyWeightComposition", InternationalPatientSummaryOnlyBodyWeightComposition.class, this);

  public SelectAqlField<Category> CATEGORY_DEFINING_CODE = new AqlFieldImp<Category>(InternationalPatientSummaryOnlyBodyWeightComposition.class, "/category|defining_code", "categoryDefiningCode", Category.class, this);

  public ListSelectAqlField<Cluster> EXTENSION = new ListAqlFieldImp<Cluster>(InternationalPatientSummaryOnlyBodyWeightComposition.class, "/context/other_context[at0001]/items[at0002]", "extension", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> START_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(InternationalPatientSummaryOnlyBodyWeightComposition.class, "/context/start_time|value", "startTimeValue", TemporalAccessor.class, this);

  public ListSelectAqlField<Participation> PARTICIPATIONS = new ListAqlFieldImp<Participation>(InternationalPatientSummaryOnlyBodyWeightComposition.class, "/context/participations", "participations", Participation.class, this);

  public SelectAqlField<TemporalAccessor> END_TIME_VALUE = new AqlFieldImp<TemporalAccessor>(InternationalPatientSummaryOnlyBodyWeightComposition.class, "/context/end_time|value", "endTimeValue", TemporalAccessor.class, this);

  public SelectAqlField<String> LOCATION = new AqlFieldImp<String>(InternationalPatientSummaryOnlyBodyWeightComposition.class, "/context/location", "location", String.class, this);

  public SelectAqlField<PartyIdentified> HEALTH_CARE_FACILITY = new AqlFieldImp<PartyIdentified>(InternationalPatientSummaryOnlyBodyWeightComposition.class, "/context/health_care_facility", "healthCareFacility", PartyIdentified.class, this);

  public SelectAqlField<Setting> SETTING_DEFINING_CODE = new AqlFieldImp<Setting>(InternationalPatientSummaryOnlyBodyWeightComposition.class, "/context/setting|defining_code", "settingDefiningCode", Setting.class, this);

  public SelectAqlField<BodyWeightObservation> BODY_WEIGHT = new AqlFieldImp<BodyWeightObservation>(InternationalPatientSummaryOnlyBodyWeightComposition.class, "/content[openEHR-EHR-SECTION.adhoc.v1]/items[openEHR-EHR-OBSERVATION.body_weight.v2]", "bodyWeight", BodyWeightObservation.class, this);

  public SelectAqlField<PartyProxy> COMPOSER = new AqlFieldImp<PartyProxy>(InternationalPatientSummaryOnlyBodyWeightComposition.class, "/composer", "composer", PartyProxy.class, this);

  public SelectAqlField<Language> LANGUAGE = new AqlFieldImp<Language>(InternationalPatientSummaryOnlyBodyWeightComposition.class, "/language", "language", Language.class, this);

  public SelectAqlField<FeederAudit> FEEDER_AUDIT = new AqlFieldImp<FeederAudit>(InternationalPatientSummaryOnlyBodyWeightComposition.class, "/feeder_audit", "feederAudit", FeederAudit.class, this);

  public SelectAqlField<Territory> TERRITORY = new AqlFieldImp<Territory>(InternationalPatientSummaryOnlyBodyWeightComposition.class, "/territory", "territory", Territory.class, this);

  private InternationalPatientSummaryOnlyBodyWeightCompositionContainment() {
    super("openEHR-EHR-COMPOSITION.health_summary.v1");
  }

  public static InternationalPatientSummaryOnlyBodyWeightCompositionContainment getInstance() {
    return new InternationalPatientSummaryOnlyBodyWeightCompositionContainment();
  }
}
