package com.example.kinesiscommercesystemsample.purchase.api.service.purchase.regist;

import com.example.kinesiscommercesystemsample.purchase.api.controller.PurchaseApiController;

public interface PurchaseRegistService {

	PurchaseRegistResult execute(PurchaseApiController.PurchaseRegistRequest request);
}
