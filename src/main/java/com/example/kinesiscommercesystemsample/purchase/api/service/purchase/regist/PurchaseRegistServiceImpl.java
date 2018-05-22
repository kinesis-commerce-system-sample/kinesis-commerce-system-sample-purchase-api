package com.example.kinesiscommercesystemsample.purchase.api.service.purchase.regist;

import com.eaio.uuid.UUID;
import com.example.kinesiscommercesystemsample.common.messaging.purchase.entity.v1.PurchaseRegistMessage;
import com.example.kinesiscommercesystemsample.common.messaging.purchase.mom.writer.AbstractPurchaseStreamWriter;
import com.example.kinesiscommercesystemsample.purchase.api.controller.PurchaseApiController;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseRegistServiceImpl implements PurchaseRegistService {

	@Autowired
	private AbstractPurchaseStreamWriter purchaseStreamWriter;

	@Override
	public PurchaseRegistResult execute(PurchaseApiController.PurchaseRegistRequest request) {

		try {

			val messageId = new UUID().toString();
			val purchaseId = new UUID().toString();

			val itemId = request.getItemId();
			val quantity = request.getQuantity();

			// TODO : derive domain model

			val message = new PurchaseRegistMessage(messageId, purchaseId, itemId, quantity);

			purchaseStreamWriter.write(message);

			val result = new PurchaseRegistResult();
			result.setMessageId(messageId);
			result.setPurchaseId(purchaseId);

			return result;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
