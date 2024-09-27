package br.edu.ufape.preco.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import br.edu.ufape.preco.exceptions.NotFoundException;
import br.edu.ufape.preco.model.PrecoProduto;
import br.edu.ufape.preco.service.MessageProducer;
import br.edu.ufape.preco.service.interfaces.IPrecoProdutoService;
import br.edu.ufape.residencia.util.dto.ProdutoDto;

@RestController
@RequestMapping("/api/preco/preco_produto")
public class PrecoProdutoController {

    private final IPrecoProdutoService precoProdutoService;
    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;

    @Autowired
    public PrecoProdutoController(IPrecoProdutoService precoProdutoService, DiscoveryClient discoveryClient, RestTemplate restTemplate) {
        this.precoProdutoService = precoProdutoService;
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public List<PrecoProduto> getAllPrecoProdutos() {
        return precoProdutoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrecoProduto> getPrecoProdutoById(@PathVariable Long id) throws NotFoundException {
        PrecoProduto precoProduto = precoProdutoService.findById(id);
        return ResponseEntity.ok(precoProduto);
    }

    @PostMapping
    public PrecoProduto createPrecoProduto(@RequestBody PrecoProduto precoProduto) {
        return precoProdutoService.save(precoProduto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrecoProduto(@PathVariable Long id) throws NotFoundException {
        precoProdutoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/price_product/{productId}")
    public double getPriceForProduct(@PathVariable Long productId) {
        // Use DiscoveryClient to find instances of catalog service
        List<ServiceInstance> instances = discoveryClient.getInstances("catalogo");
        if (instances.isEmpty()) {
            throw new RuntimeException("No instances found for 'Catalogo'");
        }
        ServiceInstance serviceInstance = instances.get(0);

        // Construct URL for the catalog service
        String url = serviceInstance.getUri() + "/api/catalogo/produtos/" + productId;

        // Make REST call to catalog service
        ProdutoDto product = restTemplate.getForObject(url, ProdutoDto.class);

        // Check if the product was retrieved
        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        List<PrecoProduto> precoProdutos = precoProdutoService.findAll();
        for (PrecoProduto precoProduto : precoProdutos) {
            if (precoProduto.getProduto_id().equals(product.getId())) {
                return precoProduto.getPrecoBase();
            }
        }
        return 0.0;
    }
    
    @Autowired
    private RabbitTemplate rabbitTemplate;
    
    @GetMapping("/test")
    public void test_rabbit() {
        MessageProducer message_producer = new MessageProducer(rabbitTemplate);
        message_producer.sendMessage("preco_exchange", "preco_routing_key", "catalogo_message");
    }
}
