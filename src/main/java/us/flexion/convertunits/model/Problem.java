package us.flexion.convertunits.model;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder // Reference info: https://projectlombok.org/features/Builder and https://www.baeldung.com/lombok-builder
@AllArgsConstructor
@ToString
public class Problem {
  public enum UnitTypes {
    TEMPERATURE("Temperature"), VOLUME("Volume");

    public final String label;

    private UnitTypes(String label) {
      this.label = label;
    }

    @Override
    public String toString() {
      return label;
    }

    public static Map<String, String> getEnumAsMap() {
      Map<String, String> unitTypesMap = new HashMap<String, String>();
      for (UnitTypes unitType : UnitTypes.values()) {
        unitTypesMap.put(unitType.name(), unitType.label);
      }
      return unitTypesMap;
    }
  }

  @Getter
  @Setter
  private UnitTypes unitType;

  @Getter
  @Setter
  private Double inputValue;

  // 'inputUnit' & 'targetUnit' are the Canonical Name of a child class of AUnit.

  @Getter
  @Setter
  private String inputUnit;

  @Getter
  @Setter
  private String targetUnit;

  @Getter
  @Setter
  private Double studentResponse;

  @Getter
  @Setter
  private String problemOutput;

  // Returns whether all the fields have been set that are needed to calculate 'problemOutput'.
  public boolean allDataProvided() {
    if (getInputValue() == null || isNullOrEmpty(getInputUnit()) || isNullOrEmpty(getTargetUnit()) || getStudentResponse() == null) {
      return false;
    }
    return true;
  }

  private boolean isNullOrEmpty(String str) {
    if (str != null && !str.isEmpty()) {
      return false;
    }
    return true;
  }
}
