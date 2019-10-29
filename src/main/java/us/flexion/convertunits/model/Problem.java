package us.flexion.convertunits.model;

import java.util.HashMap;
import java.util.Map;

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

  private UnitTypes unitType;

  public UnitTypes getUnitType() {
    return unitType;
  }

  public void setUnitType(UnitTypes unitType) {
    this.unitType = unitType;
  }

  private Double inputValue;

  public Double getInputValue() {
    return inputValue;
  }

  public void setInputValue(Double inputValue) {
    this.inputValue = inputValue;
  }

  // 'inputUnit' & 'targetUnit' are the Canonical Name of a child class of AUnit.

  private String inputUnit;

  public String getInputUnit() {
    return inputUnit;
  }

  public void setInputUnit(String inputUnit) {
    this.inputUnit = inputUnit;
  }

  private String targetUnit;

  public String getTargetUnit() {
    return targetUnit;
  }

  public void setTargetUnit(String targetUnit) {
    this.targetUnit = targetUnit;
  }

  private Double studentResponse;

  public Double getStudentResponse() {
    return studentResponse;
  }

  public void setStudentResponse(Double studentResponse) {
    this.studentResponse = studentResponse;
  }

  private String problemOutput;

  public String getProblemOutput() {
    return problemOutput;
  }

  public void setProblemOutput(String problemOutput) {
    this.problemOutput = problemOutput;
  }

  @Override
  public String toString() {
    return String.format("Problem[unitType=%s inputValue=%f, inputUnit=%s, targetUnit=%s, studentResponse=%f, problemOutput=%s]", unitType, inputValue, inputUnit, targetUnit,
        studentResponse, problemOutput);
  }

  // Returns whether all fields (other than 'problemOutput') in this class have been set - i.e. whether 'problemOutput' can be calculated.
  public boolean allDataProvided() {
    if (getUnitType() == null || getInputValue() == null || isNullOrEmpty(getInputUnit()) || isNullOrEmpty(getTargetUnit()) || getStudentResponse() == null) {
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
