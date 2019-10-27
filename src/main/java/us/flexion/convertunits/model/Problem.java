package us.flexion.convertunits.model;

public class Problem {
  private Double inputValue;

  public Double getInputValue() {
    return inputValue;
  }

  public void setInputValue(Double inputValue) {
    this.inputValue = inputValue;
  }

  // inputUnit, targetUnit

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
    return String.format("Problem[inputValue=%f, studentResponse=%f, problemOutput=%s]", inputValue, studentResponse, problemOutput);
  }
}
