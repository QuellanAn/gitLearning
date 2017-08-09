package com.catic.mobilehos.pay.dao;

import java.util.List;

import com.catic.mobilehos.pay.entity.PayDictionary;

public interface IPayDictionaryDao {

	public List<PayDictionary> findAll(String dictionaryCode) throws Exception;
}
