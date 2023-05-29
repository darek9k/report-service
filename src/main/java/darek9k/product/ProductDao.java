package darek9k.product;

import darek9k.customer.Customer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
@Component
class ProductDao {
    List<Product> findAll(Set<Integer> ids) {
        try (Stream<String> lines = Files.lines(Path.of("files", "products.csv"))){
            return lines
                    .map(Product::fromCsvString)
                    .filter(product -> ids.contains(product.getId()))
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
