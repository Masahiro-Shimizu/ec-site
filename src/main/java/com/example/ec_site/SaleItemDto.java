package com.example.ec_site;

// セール品の表示用データを保持するDTOクラス
// DTOとはData Transfer Objectの略で、画面表示用にデータをまとめたクラス
public class SaleItemDto {

    private Integer productId;      // 商品ID
    private String saleName;        // セール名
    private String salesImgPath;    // セール画像パス
    private Integer discountRate;   // 割引率
    private Integer originalPrice;  // 通常価格
    private Integer salePrice;      // セール価格（割引後）

    public SaleItemDto(SaleItem saleItem, Product product) {
        this.productId = product.getProductId();
        this.saleName = saleItem.getSaleName();
        this.salesImgPath = saleItem.getSalesImgPath();
        this.discountRate = saleItem.getDiscountRate();
        this.originalPrice = product.getPrice();
        // 割引後価格を計算する
        // 例: 10%引きなら price * (100 - 10) / 100
        this.salePrice = product.getPrice() * (100 - saleItem.getDiscountRate()) / 100;
    }

    // getter
    public Integer getProductId() { return productId; }
    public String getSaleName() { return saleName; }
    public String getSalesImgPath() { return salesImgPath; }
    public Integer getDiscountRate() { return discountRate; }
    public Integer getOriginalPrice() { return originalPrice; }
    public Integer getSalePrice() { return salePrice; }
}