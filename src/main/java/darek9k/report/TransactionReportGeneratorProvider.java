package darek9k.report;

import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class TransactionReportGeneratorProvider {

    //private final CsvTransactionReportGenerator csvTransactionReportGenerator;
    //private final XlsTransactionReportGenerator xlsTransactionReportGenerator;

    public TransactionReportGeneratorProvider(CsvTransactionReportGenerator csvTransactionReportGenerator,
                                              XlsTransactionReportGenerator xlsTransactionReportGenerator) {
        //this.csvTransactionReportGenerator = csvTransactionReportGenerator;
        //this.xlsTransactionReportGenerator = xlsTransactionReportGenerator;

        generatorMap = Map.of(
                ReportType.CSV, csvTransactionReportGenerator,
                ReportType.XLS, xlsTransactionReportGenerator
        );
    }

    private final Map<ReportType, TransactionReportGenerator> generatorMap;

    TransactionReportGenerator provide(ReportType reportType){
        return generatorMap.get(reportType);
    }
}
