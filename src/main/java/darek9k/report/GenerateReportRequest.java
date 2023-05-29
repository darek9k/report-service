package darek9k.report;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GenerateReportRequest(ReportType reportType) {
    public GenerateReportRequest(@JsonProperty("reportType") ReportType reportType){
        this.reportType = reportType;
    }
}
