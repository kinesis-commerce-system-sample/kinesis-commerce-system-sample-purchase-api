package com.example.kinesiscommercesystemsample.purchase.api.controller;

import com.example.kinesiscommercesystemsample.common.web.base.controller.api.AbstractRestController;
import com.example.kinesiscommercesystemsample.common.web.base.controller.api.resource.Resource;
import com.example.kinesiscommercesystemsample.common.web.base.controller.api.resource.ResourceFactory;
import com.example.kinesiscommercesystemsample.common.web.base.dto.Dto;
import com.example.kinesiscommercesystemsample.purchase.api.service.purchase.regist.PurchaseRegistResult;
import com.example.kinesiscommercesystemsample.purchase.api.service.purchase.regist.PurchaseRegistService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(path = {"/purchase"})
@Slf4j
public class PurchaseApiController extends AbstractRestController {

	@Autowired
	private PurchaseRegistService purchaseRegistService;

	@PostMapping(value = "/regist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Resource regist(@RequestBody PurchaseRegistRequest request) {

		PurchaseRegistResult purchaseRegistResult = purchaseRegistService.execute(request);

		val response = new PurchaseRegistResponse(purchaseRegistResult.getMessageId());
		val responses = new ArrayList<Dto>();
		responses.add(response);

		Resource resource = ResourceFactory.create();
		resource.setData(responses);
		resource.setMessage(getMessage(SUCCESS));

		return resource;
	}

	@Getter
	@Setter
	@ToString
	public static class PurchaseRegistRequest implements Dto {

		/**
		 * 商品ID
		 */
		@JsonProperty("item_id")
		private String itemId;

		/**
		 * 数量
		 */
		@JsonProperty("quantity")
		private Integer quantity;
	}

	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	@ToString
	public static class PurchaseRegistResponse implements Dto {

		/**
		 * 発番されたメッセージID
		 */
		@JsonProperty("message_id")
		private String messageId;
	}
}
