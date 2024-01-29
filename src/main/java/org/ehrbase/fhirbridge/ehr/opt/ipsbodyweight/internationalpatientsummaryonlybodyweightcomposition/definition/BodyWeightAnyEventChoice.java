package org.ehrbase.fhirbridge.ehr.opt.ipsbodyweight.internationalpatientsummaryonlybodyweightcomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import com.nedap.archie.rm.datastructures.ItemTree;
import java.lang.Double;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import javax.annotation.processing.Generated;
import org.ehrbase.client.classgenerator.shareddefinition.NullFlavour;

@Generated(
    value = "org.ehrbase.openehr.sdk.generator.ClassGenerator",
    date = "2024-01-26T15:43:54.030141+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: 2.6.0"
)
public interface BodyWeightAnyEventChoice {
  TemporalAccessor getTimeValue();

  void setTimeValue(TemporalAccessor timeValue);

  NullFlavour getWeightNullFlavourDefiningCode();

  void setWeightNullFlavourDefiningCode(NullFlavour weightNullFlavourDefiningCode);

  ItemTree getStateStructure();

  void setStateStructure(ItemTree stateStructure);

  String getWeightUnits();

  void setWeightUnits(String weightUnits);

  Double getWeightMagnitude();

  void setWeightMagnitude(Double weightMagnitude);

  FeederAudit getFeederAudit();

  void setFeederAudit(FeederAudit feederAudit);
}
