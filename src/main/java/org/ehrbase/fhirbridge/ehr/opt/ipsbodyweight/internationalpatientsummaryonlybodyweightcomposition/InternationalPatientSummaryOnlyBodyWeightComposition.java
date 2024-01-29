package org.ehrbase.fhirbridge.ehr.opt.ipsbodyweight.internationalpatientsummaryonlybodyweightcomposition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.Participation;
import com.nedap.archie.rm.generic.PartyIdentified;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.fhirbridge.ehr.opt.ipsbodyweight.internationalpatientsummaryonlybodyweightcomposition.definition.BodyWeightObservation;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Id;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.annotations.Template;
import org.ehrbase.client.classgenerator.interfaces.CompositionEntity;
import org.ehrbase.client.classgenerator.shareddefinition.Category;
import org.ehrbase.client.classgenerator.shareddefinition.Language;
import org.ehrbase.client.classgenerator.shareddefinition.Setting;
import org.ehrbase.client.classgenerator.shareddefinition.Territory;
import org.ehrbase.client.openehrclient.VersionUid;

@Entity
@Archetype("openEHR-EHR-COMPOSITION.health_summary.v1")
@Generated(
    value = "org.ehrbase.openehr.sdk.generator.ClassGenerator",
    date = "2024-01-26T15:43:54.003062300+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 2.6.0"
)
@Template("International Patient Summary - only body weight")
public class InternationalPatientSummaryOnlyBodyWeightComposition implements CompositionEntity {
  /**
   * Path: International Patient Summary/category
   */
  @Path("/category|defining_code")
  private Category categoryDefiningCode;

  /**
   * Path: International Patient Summary/context/Extension
   * Description: Additional information required to capture local content or to align with other reference models/formalisms.
   * Comment: For example: local information requirements or additional metadata to align with FHIR or CIMI equivalents.
   */
  @Path("/context/other_context[at0001]/items[at0002]")
  private List<Cluster> extension;

  /**
   * Path: International Patient Summary/context/start_time
   */
  @Path("/context/start_time|value")
  private TemporalAccessor startTimeValue;

  /**
   * Path: International Patient Summary/context/participations
   */
  @Path("/context/participations")
  private List<Participation> participations;

  /**
   * Path: International Patient Summary/context/end_time
   */
  @Path("/context/end_time|value")
  private TemporalAccessor endTimeValue;

  /**
   * Path: International Patient Summary/context/location
   */
  @Path("/context/location")
  private String location;

  /**
   * Path: International Patient Summary/context/health_care_facility
   */
  @Path("/context/health_care_facility")
  private PartyIdentified healthCareFacility;

  /**
   * Path: International Patient Summary/context/setting
   */
  @Path("/context/setting|defining_code")
  private Setting settingDefiningCode;

  /**
   * Path: International Patient Summary/Vital Signs/Body weight
   * Description: Measurement of the body weight of an individual.
   */
  @Path("/content[openEHR-EHR-SECTION.adhoc.v1 and name/value='Vital Signs']/items[openEHR-EHR-OBSERVATION.body_weight.v2]")
  private BodyWeightObservation bodyWeight;

  /**
   * Path: International Patient Summary/composer
   */
  @Path("/composer")
  private PartyProxy composer;

  /**
   * Path: International Patient Summary/language
   */
  @Path("/language")
  private Language language;

  /**
   * Path: International Patient Summary/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: International Patient Summary/territory
   */
  @Path("/territory")
  private Territory territory;

  @Id
  private VersionUid versionUid;

  public void setCategoryDefiningCode(Category categoryDefiningCode) {
     this.categoryDefiningCode = categoryDefiningCode;
  }

  public Category getCategoryDefiningCode() {
     return this.categoryDefiningCode ;
  }

  public void setExtension(List<Cluster> extension) {
     this.extension = extension;
  }

  public List<Cluster> getExtension() {
     return this.extension ;
  }

  public void setStartTimeValue(TemporalAccessor startTimeValue) {
     this.startTimeValue = startTimeValue;
  }

  public TemporalAccessor getStartTimeValue() {
     return this.startTimeValue ;
  }

  public void setParticipations(List<Participation> participations) {
     this.participations = participations;
  }

  public List<Participation> getParticipations() {
     return this.participations ;
  }

  public void setEndTimeValue(TemporalAccessor endTimeValue) {
     this.endTimeValue = endTimeValue;
  }

  public TemporalAccessor getEndTimeValue() {
     return this.endTimeValue ;
  }

  public void setLocation(String location) {
     this.location = location;
  }

  public String getLocation() {
     return this.location ;
  }

  public void setHealthCareFacility(PartyIdentified healthCareFacility) {
     this.healthCareFacility = healthCareFacility;
  }

  public PartyIdentified getHealthCareFacility() {
     return this.healthCareFacility ;
  }

  public void setSettingDefiningCode(Setting settingDefiningCode) {
     this.settingDefiningCode = settingDefiningCode;
  }

  public Setting getSettingDefiningCode() {
     return this.settingDefiningCode ;
  }

  public void setBodyWeight(BodyWeightObservation bodyWeight) {
     this.bodyWeight = bodyWeight;
  }

  public BodyWeightObservation getBodyWeight() {
     return this.bodyWeight ;
  }

  public void setComposer(PartyProxy composer) {
     this.composer = composer;
  }

  public PartyProxy getComposer() {
     return this.composer ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setTerritory(Territory territory) {
     this.territory = territory;
  }

  public Territory getTerritory() {
     return this.territory ;
  }

  public VersionUid getVersionUid() {
     return this.versionUid ;
  }

  public void setVersionUid(VersionUid versionUid) {
     this.versionUid = versionUid;
  }
}
