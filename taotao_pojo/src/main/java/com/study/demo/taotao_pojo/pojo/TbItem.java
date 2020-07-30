package com.study.demo.taotao_pojo.pojo;


import org.apache.solr.client.solrj.beans.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * 商品表
 *
 * @author xxx
 * @date 2019-12-29
 */
public class TbItem implements Serializable {

	/**
	 * 商品id，同时也是商品编号
	 */
	@Field(value = "item_id")
	private Long id;

	/**
	 * 商品标题
	 */
	@Field(value = "item_title")
	private String title;

	/**
	 * 商品卖点
	 */
	private String sellPoint;

	/**
	 * 商品价格，单位为：元
	 */
	@Field(value = "item_price")
	private BigDecimal price;

	private Integer stockCount;

	/**
	 * 库存数量
	 */
	@Field(value = "item_num")
	private Integer num;

	/**
	 * 商品条形码
	 */
	private String barcode;

	/**
	 * 商品图片
	 */
	@Field(value = "item_image")
	private String image;

	/**
	 * 所属类目，叶子类目
	 */
	private Integer categoryId;

	/**
	 * 商品状态，1-正常，2-下架，3-删除
	 */
	private String status;

	private String itemSn;

	private BigDecimal costPirce;

	private BigDecimal marketPrice;

	private String isDefault;

	@Field(value = "item_goodsid")
	private Long goodsId;

	private String sellerId;

	private String cartThumbnail;

	@Field(value = "item_category")
	private String category;

	@Field(value = "item_brand")
	private String brand;

	private String spec;

	@Field(value = "item_seller")
	private String seller;

	private Timestamp createTime;

	private Timestamp updateTime;

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public void setPrice(Double price) {
		this.price = BigDecimal.valueOf(price);
	}

	public Integer getStockCount() {
		return stockCount;
	}

	public void setStockCount(Integer stockCount) {
		this.stockCount = stockCount;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getItemSn() {
		return itemSn;
	}

	public void setItemSn(String itemSn) {
		this.itemSn = itemSn;
	}

	public BigDecimal getCostPirce() {
		return costPirce;
	}

	public void setCostPirce(BigDecimal costPirce) {
		this.costPirce = costPirce;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getCartThumbnail() {
		return cartThumbnail;
	}

	public void setCartThumbnail(String cartThumbnail) {
		this.cartThumbnail = cartThumbnail;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getSeller() {
		return seller;
	}

	public void setSeller(String seller) {
		this.seller = seller;
	}
}
