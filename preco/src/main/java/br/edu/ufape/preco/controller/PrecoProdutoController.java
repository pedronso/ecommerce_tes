package br.edu.ufape.preco.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;

import br.edu.ufape.preco.model.PrecoProduto;
import br.edu.ufape.preco.service.interfaces.IPrecoProdutoService;
import br.edu.ufape.residencia.util.dto.ProdutoDto;

@RestController
@RequestMapping("/api/preco_produto")
public class PrecoProdutoController {

    private final IPrecoProdutoService precoProdutoService;
    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    public PrecoProdutoController(IPrecoProdutoService precoProdutoService) {
        this.precoProdutoService = precoProdutoService;
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

    @GetMapping("/{id}/politica")
    public ResponseEntity<String> getPoliticaPrecoDescricaoById(@PathVariable Long id) {
        Optional<String> politicaDescricao = precoProdutoService.findPoliticaDescricaoById(id);
        return politicaDescricao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    public double getPriceForProduct(Long productId) {
        // Use DiscoveryClient to find instances of catalog service
        InstanceInfo instance = discoveryClient.getNextServerFromEureka("Catalogo", false);

        String url = instance.getHomePageUrl() + "api/produtos/" + productId;

        // Make REST call to catalog service
        RestTemplate restTemplate = new RestTemplate();
        ProdutoDto product = restTemplate.getForObject(url, ProdutoDto.class);

        List<PrecoProduto> preco_produtos = getAllPrecoProdutos();
        for (PrecoProduto preco_produto : preco_produtos) {
            if (preco_produto.getId() == product.getId()) {
            	return preco_produto.getPrecoBase();
            }
        }
    }
}
