package com.billing_saas.satrup.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class BillResponse {
    private Long billId;
    private Long tenantId;
    private Long customerId;
    private Long createdById;
    private String billNumber;
    private LocalDateTime billDate;
    private BigDecimal subtotal;
    private BigDecimal gstAmount;
    private BigDecimal discount;
    private BigDecimal totalAmount;
    private String paymentStatus;
    private String paymentMethod;
    private Boolean whatsappSent;
    private Boolean whatsappDelivered;
    private LocalDateTime createdAt;
    private List<BillItemResponse> items;

    public Long getBillId() { return billId; }
    public void setBillId(Long billId) { this.billId = billId; }
    public Long getTenantId() { return tenantId; }
    public void setTenantId(Long tenantId) { this.tenantId = tenantId; }
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public Long getCreatedById() { return createdById; }
    public void setCreatedById(Long createdById) { this.createdById = createdById; }
    public String getBillNumber() { return billNumber; }
    public void setBillNumber(String billNumber) { this.billNumber = billNumber; }
    public LocalDateTime getBillDate() { return billDate; }
    public void setBillDate(LocalDateTime billDate) { this.billDate = billDate; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    public BigDecimal getGstAmount() { return gstAmount; }
    public void setGstAmount(BigDecimal gstAmount) { this.gstAmount = gstAmount; }
    public BigDecimal getDiscount() { return discount; }
    public void setDiscount(BigDecimal discount) { this.discount = discount; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public Boolean getWhatsappSent() { return whatsappSent; }
    public void setWhatsappSent(Boolean whatsappSent) { this.whatsappSent = whatsappSent; }
    public Boolean getWhatsappDelivered() { return whatsappDelivered; }
    public void setWhatsappDelivered(Boolean whatsappDelivered) { this.whatsappDelivered = whatsappDelivered; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public List<BillItemResponse> getItems() { return items; }
    public void setItems(List<BillItemResponse> items) { this.items = items; }
}