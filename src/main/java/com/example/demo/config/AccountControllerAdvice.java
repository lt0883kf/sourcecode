package com.example.demo.config;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author takaaki
 * 共通処理を定義
 */
@ControllerAdvice
public class AccountControllerAdvice {

	// レコードが見つからないときはエラーページに遷移させる
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public String handleException(EmptyResultDataAccessException e, Model model) {

		model.addAttribute("message", e.getMessage());
		return "error/systemError";
	}
}
