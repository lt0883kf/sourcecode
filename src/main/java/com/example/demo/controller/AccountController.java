package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;

/**
 * @author takaaki
 * コントローラークラス
 */
@Controller
@RequestMapping("/account")
public class AccountController {

	private final AccountService service;

	@Autowired
	public AccountController(AccountService service) {
		this.service = service;
	}

	// 全件表示を行う
	@GetMapping
	public String account(Model model) {

		List<Account> list = service.findAll();
		int totalPrice = service.getTotalPrice();
		model.addAttribute("list", list);
		model.addAttribute("totalPrice", totalPrice);
		return "account/index";
	}

	// 新規登録画面へ遷移
	@GetMapping("/insert")
	public String goInsert() {

		return "account/insert";
	}

	// 新規登録画面へ遷移
	@PostMapping("/insert")
	public String insert(Model model, Account account) {

		account = service.insertAccount(account);
		model.addAttribute("account", account);
		return "account/insertComplete";
	}

	// 削除画面へ遷移
	@GetMapping("/delete")
	public String goDelete(Model model) {

		List<Account> list = service.findAll();
		int totalPrice = service.getTotalPrice();
		model.addAttribute("list", list);
		model.addAttribute("totalPrice", totalPrice);
		return "account/delete";
	}

	// 削除確認画面へ遷移
	@GetMapping("/deleteConfirm")
	public String deleteConfirm(Model model, @RequestParam String id) {

		Account account = service.findAcountById(id);
		model.addAttribute("account", account);
		return "account/deleteConfirm";
	}

	// 削除処理を行う
	@PostMapping("/delete")
	public String delete(Model model, @RequestParam String id) {

		Account account = service.findAcountById(id);
		model.addAttribute("account", account);
		service.deleteAcountById(id);
		return "account/deleteComplete";
	}

	// 更新画面へ遷移
	@GetMapping("/update")
	public String goUpdate(Model model) {

		List<Account> list = service.findAll();
		int totalPrice = service.getTotalPrice();
		model.addAttribute("list", list);
		model.addAttribute("totalPrice", totalPrice);
		return "account/update";
	}

	// 更新入力画面へ遷移
	@GetMapping("/updateInput")
	public String updateInput(Model model, @RequestParam String id) {

		Account account = service.findAcountById(id);
		model.addAttribute("account", account);
		return "account/updateInput";
	}

	// 更新確認画面へ遷移
	@PostMapping("/updateConfirm")
	public String updateConfirm(Model model, Account account) {

		model.addAttribute("account", account);
		return "account/updateConfirm";
	}

	// 更新処理を行う
	@PostMapping("/update")
	public String update(Model model, Account account) {

		model.addAttribute("account", account);
		service.updateAccount(account);
		return "account/updateComplete";
	}

	// 年別集計画面へ遷移
	@GetMapping("/findByYear")
	public String goFindByYear() {

		return "account/findByYear";
	}

	// 年別集計処理を行う
	@PostMapping("/findByYear")
	public String findByYear(Model model, @RequestParam String year) {

		List<Account> list2 = service.findAccountByYear(year);
		int totalPrice = service.getTotalPrice();
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("year", year);
		model.addAttribute("list", list2);
		return "account/findByYear";
	}

	// 年別月別集計画面へ遷移
	@GetMapping("/findByYearAndMonth")
	public String goFindByYearAndMonth() {

		return "account/findByYearAndMonth";
	}

	// 年別月別集計処理を行う
	@PostMapping("/findByYearAndMonth")
	public String findByYearAndMonth(Model model, @RequestParam String year, @RequestParam String month) {

		List<Account> list2 = service.findAccountByYearAndMonth(year, month);
		int totalPrice = service.getTotalPrice();
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("year", year);
		model.addAttribute("month", month);
		model.addAttribute("list", list2);
		return "account/findByYearAndMonth";
	}
}
