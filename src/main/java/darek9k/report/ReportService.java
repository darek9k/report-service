package darek9k.report;

import org.springframework.stereotype.Service;
import darek9k.customer.Customer;
import darek9k.customer.CustomerService;
import darek9k.product.Product;
import darek9k.product.ProductService;
import darek9k.transaction.Transaction;
import darek9k.transaction.TransactionService;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class ReportService {
    private final CustomerService customerService;
    private final ProductService productService;
    private final TransactionService transactionService;

    private final TransactionReportGeneratorProvider transactionReportGeneratorProvider;

    public ReportService(CustomerService customerService, ProductService productService, TransactionService transactionService,
                         TransactionReportGeneratorProvider transactionReportGeneratorProvider) {
        this.customerService = customerService;
        this.productService = productService;
        this.transactionService = transactionService;
        this.transactionReportGeneratorProvider = transactionReportGeneratorProvider;
    }

    public void generateTransactionsReport(ReportType reportType) {
        List<Transaction> transactions = transactionService.findAll();
        Set<Integer> customerIds = transactions.stream()
                .map(Transaction::getCustomerId)
                .collect(Collectors.toSet());

        Set<Integer> productIds = transactions.stream()
                .map(Transaction::getProductId)
                .collect(Collectors.toSet());

        Map<Integer, Customer> customerMap = customerService.find(customerIds);
        Map<Integer, Product> productMap = productService.find(productIds);

        // -----------------------------------
        List<TransactionsReportLine> transactionsReportLines = transactions.stream()
                .map(transaction -> {
                    Customer customer = customerMap.get(transaction.getCustomerId());
                    Product product = productMap.get(transaction.getProductId());

                    return new TransactionsReportLine(
                            transaction.getId(),
                            customer.firstAndLastName(),
                            product.getName(),
                            transaction.getDate(),
                            transaction.getQuantity(),
                            transaction.getPrice()
                    );
                })
                .toList();

        TransactionReportGenerator transactionReportGenerator = transactionReportGeneratorProvider.provide(reportType);
        transactionReportGenerator.generate(transactionsReportLines);
    }
}
