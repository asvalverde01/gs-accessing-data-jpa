import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.example.accessingdatajpa.Customer;
import com.example.accessingdatajpa.CustomerRepository;

import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RepositoryRestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Customer>> findOne(@PathVariable Long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        EntityModel<Customer> resource = EntityModel.of(customer);
        resource.add(linkTo(methodOn(CustomerController.class).findOne(id)).withSelfRel());
        resource.add(linkTo(methodOn(CustomerController.class).findAll()).withRel("customers"));

        return ResponseEntity.ok(resource);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Customer>>> findAll() {
        Iterable<Customer> customerIterable = repository.findAll();
        List<Customer> customerList = StreamSupport.stream(customerIterable.spliterator(), false)
                .collect(Collectors.toList());
        List<EntityModel<Customer>> customers = customerList.stream()
                .map(customer -> EntityModel.of(customer,
                        linkTo(methodOn(CustomerController.class).findOne(customer.getId())).withSelfRel(),
                        linkTo(methodOn(CustomerController.class).findAll()).withRel("customers")))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<Customer>> resources = CollectionModel.of(customers,
                linkTo(methodOn(CustomerController.class).findAll()).withSelfRel());

        return ResponseEntity.ok(resources);
    }
}
