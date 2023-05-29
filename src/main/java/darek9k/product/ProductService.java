package darek9k.product;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
@Service
public class ProductService {
    private ProductDao productDao;

    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }


    public Map<Integer, Product> find(Set<Integer> ids){
        List<Product> products = productDao.findAll(ids);

        Map<Integer, Product> collect = products.stream()
                .collect(Collectors.toMap(Product::getId, product -> product));

        System.out.println("findPo wyjatku");
        return collect;
    }
}
