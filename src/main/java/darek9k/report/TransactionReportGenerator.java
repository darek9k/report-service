package darek9k.report;

import java.util.List;

interface TransactionReportGenerator {
    void generate(List<TransactionsReportLine> transactionsReportLines);
}
