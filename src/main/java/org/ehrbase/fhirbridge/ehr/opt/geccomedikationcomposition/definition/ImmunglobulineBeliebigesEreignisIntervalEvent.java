package org.ehrbase.fhirbridge.ehr.opt.geccomedikationcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datavalues.DvCodedText;
import java.lang.Long;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.IntervalEventEntity;
import org.ehrbase.client.classgenerator.shareddefinition.MathFunction;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2021-10-11T15:28:13.024616+02:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 1.5.0"
)
@OptionFor("INTERVAL_EVENT")
public class ImmunglobulineBeliebigesEreignisIntervalEvent implements IntervalEventEntity, ImmunglobulineBeliebigesEreignisChoice {
  /**
   * Path: Medikation/Immunglobuline/Beliebiges Ereignis/Arzneimittel-Name
   * Description: Name des Medikaments, des Impfstoffs oder eines anderen therapeutischen / verschreibungsfähigen Mittels.
   * Comment: Es wird dringend empfohlen, das „Arzneimittel“ mit einer Terminologie zu codieren, die nach Möglichkeit Entscheidungsunterstützung auslösen kann. Das Ausmaß der Codierung kann vom einfachen generischen oder Handelsnamen des Medikamentes bis hin zu strukturierten Angaben über die tatsächlich zu verwendende Medikamentenpackung variieren. Die Freitexteingabe sollte nur verwendet werden, wenn keine geeignete Terminologie verfügbar ist.
   */
  @Path("/data[at0003]/items[at0006]/value")
  private DvCodedText arzneimittelName;

  /**
   * Path: Medikation/Immunglobuline/Beliebiges Ereignis/Tree/Arzneimittel-Name/null_flavour
   */
  @Path("/data[at0003]/items[at0006]/null_flavour|defining_code")
  private NullFlavour arzneimittelNameNullFlavourDefiningCode;

  /**
   * Path: Medikation/Immunglobuline/Beliebiges Ereignis/Herstellungsdetails
   * Description: Strukturierte Angaben zur Gesamtzubereitung einschließlich Wirkstärke, Verabreichungsform und Inhaltsstoffe.
   * Comment: Verwenden Sie diesen SLOT, in dem die detaillierte Beschreibung des angeordneten Arzneimittels explizit angegeben werden muss. Zum Beispiel: Form, Wirkstärke, Verdünnungsmittel oder Mischung von Wirkstoffen.
   */
  @Path("/data[at0003]/items[at0007]")
  private List<Cluster> herstellungsdetails;

  /**
   * Path: Medikation/Immunglobuline/Beliebiges Ereignis/Status
   * Description: Der Status der Anwendung des Medikaments.
   * Comment: Zum Beispiel: Das Medikament wird immer noch aktiv eingenommen oder eine Antibiotikatherapie wurde abgeschlossen.
   */
  @Path("/data[at0003]/items[at0008]/value|defining_code")
  private StatusDefiningCode2 statusDefiningCode;

  /**
   * Path: Medikation/Immunglobuline/Beliebiges Ereignis/Tree/Status/null_flavour
   */
  @Path("/data[at0003]/items[at0008]/null_flavour|defining_code")
  private NullFlavour statusNullFlavourDefiningCode;

  /**
   * Path: Medikation/Immunglobuline/Beliebiges Ereignis/Strukturierte Dosis- und Zeitangaben
   * Description: Details zu strukturierten Dosis- und Zeitangaben.
   */
  @Path("/data[at0003]/items[at0022]")
  private List<Cluster> strukturierteDosisUndZeitangaben;

  /**
   * Path: Medikation/Immunglobuline/Beliebiges Ereignis/Grund
   * Description: Der klinische Grund für die Anwendung des Medikaments.
   * Comment: Zum Beispiel: "Angina". Die Codierung der klinischen Indikation mit einer Terminologie wird nach Möglichkeit bevorzugt. Dieses Datenelement kann mehrfach vorkommen.
   */
  @Path("/data[at0003]/items[at0023 and name/value='Grund']/value")
  private DvCodedText grund;

  /**
   * Path: Medikation/Immunglobuline/Beliebiges Ereignis/Tree/Grund/null_flavour
   */
  @Path("/data[at0003]/items[at0023 and name/value='Grund']/null_flavour|defining_code")
  private NullFlavour grundNullFlavourDefiningCode;

  /**
   * Path: Medikation/Immunglobuline/Beliebiges Ereignis/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Path: Medikation/Immunglobuline/Beliebiges Ereignis/time
   */
  @Path("/time|value")
  private TemporalAccessor timeValue;

  /**
   * Path: Medikation/Immunglobuline/Beliebiges Ereignis/width
   */
  @Path("/width|value")
  private TemporalAmount widthValue;

  /**
   * Path: Medikation/Immunglobuline/Beliebiges Ereignis/math_function
   */
  @Path("/math_function|defining_code")
  private MathFunction mathFunctionDefiningCode;

  /**
   * Path: Medikation/Immunglobuline/Beliebiges Ereignis/sample_count
   */
  @Path("/sample_count")
  private Long sampleCount;

  public void setArzneimittelName(DvCodedText arzneimittelName) {
     this.arzneimittelName = arzneimittelName;
  }

  public DvCodedText getArzneimittelName() {
     return this.arzneimittelName ;
  }

  public void setArzneimittelNameNullFlavourDefiningCode(
      NullFlavour arzneimittelNameNullFlavourDefiningCode) {
     this.arzneimittelNameNullFlavourDefiningCode = arzneimittelNameNullFlavourDefiningCode;
  }

  public NullFlavour getArzneimittelNameNullFlavourDefiningCode() {
     return this.arzneimittelNameNullFlavourDefiningCode ;
  }

  public void setHerstellungsdetails(List<Cluster> herstellungsdetails) {
     this.herstellungsdetails = herstellungsdetails;
  }

  public List<Cluster> getHerstellungsdetails() {
     return this.herstellungsdetails ;
  }

  public void setStatusDefiningCode(StatusDefiningCode2 statusDefiningCode) {
     this.statusDefiningCode = statusDefiningCode;
  }

  public StatusDefiningCode2 getStatusDefiningCode() {
     return this.statusDefiningCode ;
  }

  public void setStatusNullFlavourDefiningCode(NullFlavour statusNullFlavourDefiningCode) {
     this.statusNullFlavourDefiningCode = statusNullFlavourDefiningCode;
  }

  public NullFlavour getStatusNullFlavourDefiningCode() {
     return this.statusNullFlavourDefiningCode ;
  }

  public void setStrukturierteDosisUndZeitangaben(List<Cluster> strukturierteDosisUndZeitangaben) {
     this.strukturierteDosisUndZeitangaben = strukturierteDosisUndZeitangaben;
  }

  public List<Cluster> getStrukturierteDosisUndZeitangaben() {
     return this.strukturierteDosisUndZeitangaben ;
  }

  public void setGrund(DvCodedText grund) {
     this.grund = grund;
  }

  public DvCodedText getGrund() {
     return this.grund ;
  }

  public void setGrundNullFlavourDefiningCode(NullFlavour grundNullFlavourDefiningCode) {
     this.grundNullFlavourDefiningCode = grundNullFlavourDefiningCode;
  }

  public NullFlavour getGrundNullFlavourDefiningCode() {
     return this.grundNullFlavourDefiningCode ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setWidthValue(TemporalAmount widthValue) {
     this.widthValue = widthValue;
  }

  public TemporalAmount getWidthValue() {
     return this.widthValue ;
  }

  public void setMathFunctionDefiningCode(MathFunction mathFunctionDefiningCode) {
     this.mathFunctionDefiningCode = mathFunctionDefiningCode;
  }

  public MathFunction getMathFunctionDefiningCode() {
     return this.mathFunctionDefiningCode ;
  }

  public void setSampleCount(Long sampleCount) {
     this.sampleCount = sampleCount;
  }

  public Long getSampleCount() {
     return this.sampleCount ;
  }
}
