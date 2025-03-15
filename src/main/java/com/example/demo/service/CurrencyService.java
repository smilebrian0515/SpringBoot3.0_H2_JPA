package com.example.demo.service;

import com.example.demo.dao.CurrencyRepository;
import com.example.demo.dto.api.Currency;
import com.example.demo.dto.api.CurrencyApiModel;
import com.example.demo.dto.entity.CurrencyEntity;
import com.example.demo.module.CurrencyModule;
import com.example.demo.shared.HttpUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CurrencyService {

    @Autowired
    private HttpUtil httpUtil;

    @Autowired
    private CurrencyRepository repository;

    @Autowired
    private CurrencyModule module;

    Gson gson = new Gson();

    public CurrencyService() {
    }

    public void setHttpUtil(HttpUtil httpUtil) {
        this.httpUtil = httpUtil;
    }

    public void setRepository(CurrencyRepository repository) {
        this.repository = repository;
    }

    public void setModule(CurrencyModule module) {
        this.module = module;
    }

    public List<CurrencyEntity> getData() {
        List<CurrencyEntity> result = repository.findAll();
        return result;
    }

    public List<CurrencyEntity> getDataByApi() throws IOException {
        String response = getGet();
        CurrencyApiModel model = gson.fromJson(response, CurrencyApiModel.class);

        List<CurrencyEntity> result = new ArrayList<>();
        for (String key : model.getBpi().keySet()) {
            Currency currency = model.getBpi().get(key);
            CurrencyEntity entity = module.convertEntity(currency);
            result.add(entity);
        }

        return result;
    }

    private String getGet() throws IOException {
//        return httpUtil.Get("", new HashMap<>());
        return getMockData();
    }

    private String getMockData() throws IOException {
        Path path = Paths.get("src/test/resources/response.json");
        return Files.readString(path);
    }

    public boolean createData(CurrencyEntity input) {
        Optional<CurrencyEntity> optional = repository.findAll().stream()
                .filter(s -> s.getCode().equals(input.getCode()))
                .findAny();

        if (optional.isPresent()) {
            return false;
        }

        LocalDateTime now = LocalDateTime.now();
        input.setCreateTime(now);
        input.setUpdateTime(now);

        repository.save(input);
        return true;
    }

    public boolean createDataByApi() throws IOException {
        String response = getGet();
        CurrencyApiModel model = gson.fromJson(response, CurrencyApiModel.class);

        LocalDateTime now = LocalDateTime.now();
        List<CurrencyEntity> result = new ArrayList<>();
        for (String key : model.getBpi().keySet()) {
            Currency currency = model.getBpi().get(key);
            CurrencyEntity entity = module.convertEntity(currency);
            entity.setCreateTime(now);
            entity.setUpdateTime(now);
            result.add(entity);
        }

        repository.saveAll(result);
        return true;
    }

    public boolean updateData(CurrencyEntity input) {
        List<CurrencyEntity> all = repository.findAll();
        Optional<CurrencyEntity> optional = all.stream()
                .filter(s -> s.getCode().equals(input.getCode()))
                .findAny();

        if (optional.isPresent()) {
            CurrencyEntity entity = optional.get();
            if (entity.getId() != input.getId()) {
                return false;
            }
        }

        optional = all.stream()
                .filter(s -> s.getId() == input.getId())
                .findFirst();
        if(!optional.isPresent()){
            return false;
        }

        CurrencyEntity entity = optional.get();
        entity.setCode(input.getCode());
        entity.setSymbol(input.getSymbol());
        entity.setRate(input.getRate());
        entity.setDescription(input.getDescription());
        entity.setRateFloat(input.getRateFloat());

        LocalDateTime now = LocalDateTime.now();
        entity.setUpdateTime(now);

        repository.save(entity);
        return true;
    }

    public boolean deleteData(long id) {
        repository.deleteById(id);
        return true;
    }
}
