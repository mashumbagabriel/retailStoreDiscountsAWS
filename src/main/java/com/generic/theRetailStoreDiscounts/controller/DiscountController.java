package com.generic.theRetailStoreDiscounts.controller;

import com.generic.theRetailStoreDiscounts.models.DiscountRequest;
import com.generic.theRetailStoreDiscounts.services.DiscountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
@Api(value="retail store", description="Operations pertaining to retail store discounts")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @ApiOperation(value = "Submit Bill and return calculated discount",response = BigDecimal.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved discount"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @PostMapping("/v1/discounts")
    public BigDecimal createDiscount(@Valid @RequestBody DiscountRequest request) {

        return discountService.discountCalculation(request.getUser(), request.getBasket());
    }

}
