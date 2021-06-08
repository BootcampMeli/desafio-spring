package br.com.mercadolivre.desafiospring.resources;

import br.com.mercadolivre.desafiospring.dto.FeedPromoPostDTO;
import br.com.mercadolivre.desafiospring.dto.PromoPostCountDTO;
import br.com.mercadolivre.desafiospring.dto.PromoPostDTO;
import br.com.mercadolivre.desafiospring.services.PromoPostService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class PromoPostController {

    private final PromoPostService promoPostService;

    public PromoPostController(PromoPostService promoPostService) {
        this.promoPostService = promoPostService;
    }

    @ApiOperation(value = "Create new Promo Post")
    @RequestMapping(value = "/newpromopost", method = RequestMethod.POST, consumes="application/json")
    public ResponseEntity<Void> newPromoPost(@RequestBody PromoPostDTO promoPostDTO) {
        promoPostService.insert(promoPostDTO);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Salesman count of Promo Posts")
    @RequestMapping(value = "{userID}/countPromo", method = RequestMethod.GET)
    public ResponseEntity<PromoPostCountDTO> getPromoPostCount(@PathVariable Integer userID) {
        PromoPostCountDTO promoPostCountDTO = promoPostService.getPromoPostCount(userID);
        return ResponseEntity.ok().body(promoPostCountDTO);
    }

    @ApiOperation(value = "Salesman Promo Posts list")
    @RequestMapping(value = "{userID}/list", method = RequestMethod.GET)
    public ResponseEntity<FeedPromoPostDTO> getPromoPostList(@PathVariable Integer userID) {
        FeedPromoPostDTO promoPostCountDTO = promoPostService.getPromoPostList(userID);
        return ResponseEntity.ok().body(promoPostCountDTO);
    }
}
