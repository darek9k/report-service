package darek9k.customer;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Component
class CustomerDao {
    List<Customer> findAll(Set<Integer> ids) {
        try (Stream<String> lines = Files.lines(Path.of("files", "customers.csv"))) {
            return lines.map(Customer::fromCsvString)
                    .filter(customer -> ids.contains(customer.getId()))
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
