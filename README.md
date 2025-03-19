# 使用 Java17 + Spring Boot 3.0 + H2 + JPA + JUnit + MockMvc + Mockito

## 如何開始使用?

> /src/test/resources/test.txt 

為基本使用的 url 和 資料

```
> 從DB撈資料 
> 模擬call api 取得資料後同步資料 
> 新增 
> 修改 
> 刪除 
```

## 說明

### 資料庫
DB設定
> /src/main/resources/application.properties
初始化Table
> /src/main/resources/schema.sql
初始化資料
> /src/main/resources/data.sql


### 測試程式

> /src/test/java/com/example/demo 

#### API 測試: MockMvc

> /src/test/java/com/example/demo/mockMvc 

目前只有簡單的 GET
還沒寫比較複雜的，甚至帶Token的

#### 單元測試: Mockito

> /src/test/java/com/example/demo/unittest 

ForTest.java 為基本模板
CurrencyUnitTest.java 為實際使用 Mockito 的範例
