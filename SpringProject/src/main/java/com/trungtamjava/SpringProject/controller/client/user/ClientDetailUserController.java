package com.trungtamjava.SpringProject.controller.client.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientDetailUserController {
	@GetMapping(value = "/client/user/detail")
	public String getDetailUser() {
		return "client/user/CommonBase";
	}
}
