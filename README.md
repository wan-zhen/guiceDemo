# guiceDemo

##### Guice Google 開源依賴注入類庫

核心概念：綁定 Binding 、範圍 Scope、注入 Injection

目的：減少工廠方法和new的使用，使代碼更易交付測試重用，降低耦合度

##### Scope
* Guice 每次都會提供新實例，通過配置 Scope 可重用實例

    a new instance => reuse instances


* @Singleton scope 

    重用同一實例

    引入部分 `javax.inject.Singleton` 和 `com.google.inject.Singleton` 都可使用，但偏好使用 `javax.inject.Singleton` ，因為其他 injection 的框架也支持
    
* Scope 也可以用 bind 的方式

    `bind(TrsLog.class).to(InMemoryTrsLog.class).in(Singleton.class);`
    
    `bind(TrsLog.class).to(InMemoryTrsLog.class).in(Scopes.SINGLETON);`
    
##### Binding
* extends AbstractModule Override configure
* 調用 bind() 指定每個綁定
1. Linked Bindings
```
   // 直接綁定沒有interface 的實例
   bind(ComplexHelloPrinter.class).in(Scopes.SINGLETON);
```

```
   // 綁定有 interface 的實例
   bind(IHelloPrinter.class).annotatedWith(Names.named("complex")).to(ComplexHelloPrinter.class);
```
2. Instance Bindings 
    
    直接把一個實例對象綁定到他的 class 對象
    用於對象自身沒有依賴，如果用來創建複雜的對象可能降低應用啟動速度，可使用 @Provides 代替
```    
    bind(String.class).annotatedWith(Names.named("JDBC URL")).toInstance("jdbc:mysql://localhost/pizza");
```
3. Provider Bingdings


##### Injections
* 設定依賴到對象稱為注入
1. Constructor Injection

易於測試，因為你不會忘記設置依賴關係
```
public class RealBillingService implements BillingService {
 	 private final CreditCardProcessor processorProvider;
 
  	@Inject
  	public RealBillingService(CreditCardProcessor processorProvider) {
    		this.processorProvider = processorProvider;
}
```
 2. Method Injection
 
 依賴關係採用參數的形式，此方法具有任意數量的參數，因此通常不建議這樣使用，這表示方法可以任意被注入改變，
 建議使用構造函數注入 (Constructor Injection)
 ```
 public class PayPalCreditCardProcessor implements CreditCardProcessor {

	  private static final String DEFAULT_API_KEY = "development-use-only";

	  private String apiKey = DEFAULT_API_KEY;

  	@Inject
  	public void setApiKey(@Named("PayPal API key") String apiKey) {
    		this.apiKey = apiKey;
}
```


參考資料：

https://zhuanlan.zhihu.com/p/32299568

https://github.com/google/guice/wiki/Motivation
