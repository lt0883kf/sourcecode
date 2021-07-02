package com.example.demo.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Account;

/**
 * @author takaaki
 * DAOクラス
 */
@Repository
public class AccountDAO {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public AccountDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// 全件検索処理
	public List<Account> findAll() {

		String sql = "SELECT id, date, type, item, price FROM account order by date, id";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql);
		List<Account> list = new ArrayList<>();

		for (Map<String, Object> result : resultList) {

			Account account = new Account();
			account.setId((int) result.get("id"));
			Date date = (Date) result.get("date");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = dateFormat.format(date);
			account.setDate(strDate);
			account.setType((int) result.get("type"));
			account.setItem((String) result.get("item"));
			account.setPrice((int) result.get("price"));
			list.add(account);
		}
		return list;
	}

	// 新規登録処理
	public void insertAccount(Account account) {

		jdbcTemplate.update("insert into account (date, type, item, price) values (?,?,?,?)",
				account.getDate(), account.getType(), account.getItem(), account.getPrice());
	}

	// ID検索処理
	public Account findAcountById(String id) {

		String sql = "SELECT id, date, type, item, price FROM account where id = ?";
		Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);

		Account account = new Account();
		account.setId((int) result.get("id"));
		Date date = (Date) result.get("date");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);
		account.setDate(strDate);
		account.setType((int) result.get("type"));
		account.setItem((String) result.get("item"));
		account.setPrice((int) result.get("price"));

		return account;
	}

	// 削除処理
	public void deleteAccountById(String id) {

		jdbcTemplate.update("delete from account where id = ?", id);
	}

	// 更新処理
	public void updateAccount(Account account) {

		jdbcTemplate.update("update account set date = ?, type = ?, item = ?, price = ? where id = ?",
				account.getDate(), account.getType(), account.getItem(), account.getPrice(), account.getId());
	}

	// 年別検索処理
	public List<Account> findAccountByYear(String startDate, String endDate) {

		String sql = "SELECT id, date, type, item, price FROM account "
				+ "where date between ? and ? order by date, id";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql,
				startDate, endDate);
		List<Account> list = new ArrayList<>();

		for (Map<String, Object> result : resultList) {

			Account account = new Account();
			account.setId((int) result.get("id"));
			Date date = (Date) result.get("date");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = dateFormat.format(date);
			account.setDate(strDate);
			account.setType((int) result.get("type"));
			account.setItem((String) result.get("item"));
			account.setPrice((int) result.get("price"));
			list.add(account);
		}
		return list;
	}

	// 年別月別検索処理
	public List<Account> findAccountByYearAndMonth(String startDate, String endDate) {

		String sql = "SELECT id, date, type, item, price FROM account "
				+ "where date between ? and ? order by date, id";
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql,
				startDate, endDate);
		List<Account> list = new ArrayList<>();

		for (Map<String, Object> result : resultList) {

			Account account = new Account();
			account.setId((int) result.get("id"));
			Date date = (Date) result.get("date");
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = dateFormat.format(date);
			account.setDate(strDate);
			account.setType((int) result.get("type"));
			account.setItem((String) result.get("item"));
			account.setPrice((int) result.get("price"));
			list.add(account);
		}
		return list;
	}
}
