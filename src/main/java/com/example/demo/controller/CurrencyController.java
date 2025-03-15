package com.example.demo.controller;

import com.example.demo.dto.entity.CurrencyEntity;
import com.example.demo.service.CurrencyService;
import com.example.demo.shared.ApiResponse;
import com.example.demo.shared.EResultStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/currency")
public class CurrencyController {

    private final CurrencyService service;

    public CurrencyController(CurrencyService service) {
        this.service = service;
    }

    @GetMapping("/byDb")
    public ResponseEntity<ApiResponse> getCurrencyByDb() {
        List<CurrencyEntity> result = service.getData();
        return ApiResponse.newInstance().success(result);
    }

    @GetMapping("/byApi")
    public ResponseEntity<ApiResponse> getCurrencyByApi() throws IOException {
        List<CurrencyEntity> result = service.getDataByApi();
        return ApiResponse.newInstance().success(result);
    }

    @GetMapping("/syncApi")
    public ResponseEntity<ApiResponse> syncCurrencyByApi() throws IOException {
        boolean isSuccess = service.createDataByApi();
        if (isSuccess) {
            return ApiResponse.newInstance().success("");
        } else {
            return ApiResponse.newInstance().error(EResultStatus.bad_request, 1001, "同步失敗");
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> postCurrency(@RequestBody CurrencyEntity request) {
        boolean isSuccess = service.createData(request);
        if (isSuccess) {
            return ApiResponse.newInstance().success("");
        } else {
            return ApiResponse.newInstance().error(EResultStatus.bad_request, 1001, "新增失敗");
        }
    }

    @PutMapping
    public ResponseEntity<ApiResponse> putCurrency(@RequestBody CurrencyEntity request) {
        boolean isSuccess = service.updateData(request);
        if (isSuccess) {
            return ApiResponse.newInstance().success("");
        } else {
            return ApiResponse.newInstance().error(EResultStatus.bad_request, 1001, "修改失敗");
        }
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse> putCurrency(@RequestParam long id) {
        boolean isSuccess = service.deleteData(id);
        if (isSuccess) {
            return ApiResponse.newInstance().success("");
        } else {
            return ApiResponse.newInstance().error(EResultStatus.bad_request, 1001, "刪除失敗");
        }
    }
}
