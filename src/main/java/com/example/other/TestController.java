package com.example.other;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author huangqi
 * @Package com.example.other
 * @Description:
 * @date 2019-07-08 17:51
 */
@RestController
@RequestMapping("/test")
public class TestController {


	@PostMapping("fileAccept")
	public void fileAccept(@RequestParam("file") MultipartFile file) {
		System.out.println(file);
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
	}
}
