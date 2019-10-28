package us.flexion.convertunits.model;

public class Problem {
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
    return String.format("Problem[inputValue=%f, inputUnit=%s, targetUnit=%s, studentResponse=%f, problemOutput=%s]", inputValue, inputUnit, targetUnit, studentResponse,
        problemOutput);
  }
}
