package com.example.demo.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AccountDAO;
import com.example.demo.entity.Account;

/**
 * @author takaaki
 * ロジック担当
 */
@Service
public class AccountService {

	private final AccountDAO dao;

	// 合計金額格納用
	private int totalPrice;

	@Autowired
	public AccountService(AccountDAO dao) {
		this.dao = dao;
	}

	// 全件検索
	public List<Account> findAll() {

		List<Account> list = dao.findAll();
		totalPrice = 0;
		for (Account account : list) {
			totalPrice += account.getPrice();
		}
		return list;
	}

	// 新規登録
	public Account insertAccount(Account account) {

		dao.insertAccount(account);
		return account;
	}

	// 1件検索
	public Account findAcountById(String id) {

		return dao.findAcountById(id);
	}

	// 1件削除
	public void deleteAcountById(String id) {

		dao.deleteAccountById(id);
	}

	// 1件更新
	public void updateAccount(Account account) {

		dao.updateAccount(account);
	}

	// 年別処理を行う
	public List<Account> findAccountByYear(String year) {

		String startDate = year + "-01-01";
		String endDate = year + "-12-31";
		List<Account> list = dao.findAccountByYear(startDate, endDate);
		totalPrice = 0;
		for (Account account : list) {
			totalPrice += account.getPrice();
		}
		return list;

	}

	// 年別月別処理を行う
	public List<Account> findAccountByYearAndMonth(String year, String month) {

		int yearInt = Integer.parseInt(year);
		int monthInt = Integer.parseInt(month);
		String startDate = year + "-" + month + "-" + "01";
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, yearInt);
        calendar.set(Calendar.MONTH, monthInt - 1);
        int result = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        String endDate = year + "-" + month + "-" + result;
        List<Account> list = dao.findAccountByYearAndMonth(startDate, endDate);
        totalPrice = 0;
		for (Account account : list) {
			totalPrice += account.getPrice();
		}
		return list;

	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
}
