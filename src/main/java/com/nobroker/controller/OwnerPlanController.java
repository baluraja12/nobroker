package com.nobroker.controller;

import com.nobroker.payload.OwnerPlanDto;
import com.nobroker.service.OwnerPlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/OwnerPlan")
public class OwnerPlanController {

    private OwnerPlanService ownerPlanService;

    public OwnerPlanController(OwnerPlanService ownerPlanService) {
        this.ownerPlanService = ownerPlanService;
    }

    @PostMapping
    public ResponseEntity<OwnerPlanDto> createOwnerPlan(@RequestBody OwnerPlanDto ownerPlanDto){
        OwnerPlanDto dto = ownerPlanService.createOwnerPlans(ownerPlanDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<OwnerPlanDto>> getAllOwnerPalns(){
        List<OwnerPlanDto> allOwnerPalnsDto = ownerPlanService.getAllOwnerPalns();
        return new ResponseEntity<>(allOwnerPalnsDto, HttpStatus.OK);
    }
}
