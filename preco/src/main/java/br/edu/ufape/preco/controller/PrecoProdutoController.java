package br.edu.ufape.preco.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import br.edu.ufape.preco.model.PrecoProduto;
import br.edu.ufape.preco.service.interfaces.IPrecoProdutoService;
import br.edu.ufape.residencia.util.dto.ProdutoDto;

@RestController
@RequestMapping("/api/preco_produto")
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
    public ResponseEntity<PrecoProduto> getPrecoProdutoById(@PathVariable Long id) {
        Optional<PrecoProduto> precoProduto = precoProdutoService.findById(id);
        return precoProduto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PrecoProduto createPrecoProduto(@RequestBody PrecoProduto precoProduto) {
        return precoProdutoService.save(precoProduto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrecoProduto(@PathVariable Long id) {
        precoProdutoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/price_product/{productId}")
    public double getPriceForProduct(@PathVariable Long productId) {
        // Use DiscoveryClient to find instances of catalog service
        List<ServiceInstance> instances = discoveryClient.getInstances("Catalogo");
        if (instances.isEmpty()) {
            throw new RuntimeException("No instances found for 'Catalogo'");
        }
        ServiceInstance serviceInstance = instances.get(0);

        // Construct URL for the catalog service
        String url = serviceInstance.getUri() + "/api/produtos/" + productId;

        // Make REST call to catalog service
        ProdutoDto product = restTemplate.getForObject(url, ProdutoDto.class);

        // Check if the product was retrieved
        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        List<PrecoProduto> precoProdutos = precoProdutoService.findAll();
        for (PrecoProduto precoProduto : precoProdutos) {
            if (precoProduto.getId().equals(product.getId())) {
                return precoProduto.getPrecoBase();
            }
        }
        return 0.0;
    }
}
