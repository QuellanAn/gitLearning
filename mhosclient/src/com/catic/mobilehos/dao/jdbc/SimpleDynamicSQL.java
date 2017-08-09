package com.catic.mobilehos.dao.jdbc;

import java.util.ArrayList;
import java.util.List;


public abstract class SimpleDynamicSQL {
	
	public static class SelectSQL{
		
		private String baseSQL;
		
		private List<Object> paras = new ArrayList<Object>();
		
		private StringBuilder fullSQL = new StringBuilder();
		
		private boolean blank;
		
		private int offset;
		
		private int length;
		
		public void setBaseSQL(String baseSQL, boolean blank){
			this.baseSQL = baseSQL;
			fullSQL.append(baseSQL);
			this.blank = blank;
		}
		
		public void setLimit(int offset, int length){
			this.offset = offset;
			this.length = length;
		}
		
		public void addPara(String name, String opt, Object v){
			if (blank && paras.size() == 0 && v != null){
				fullSQL.append(" where ");
				fullSQL.append(name);
				fullSQL.append(" ");
				fullSQL.append(opt);
				fullSQL.append(" ? ");
				paras.add(v);
			}else if (v != null){
				fullSQL.append(" and "); 
				fullSQL.append(name);
				fullSQL.append(" ");
				fullSQL.append(opt);
				fullSQL.append(" ? ");
				paras.add(v);
			}
		}
		
		private boolean isLimit(){
			return this.offset > 0 || length > 0;
		}
		
		public void addSQLPart(String part){
			this.fullSQL.append(" ");
			this.fullSQL.append(part);
		}
		
		public StringBuilder getFullSQL(){
			StringBuilder sb = new StringBuilder();
			sb.append(fullSQL.toString());
			if (isLimit()){
				sb.append(" limit ?, ?");
			}
			return sb;
		}
		
		public List<Object> getParas(){
			if (this.length > 0){
				List<Object> tmpParas = new ArrayList<Object>();
				tmpParas.addAll(paras);
				tmpParas.add(this.offset);
				tmpParas.add(this.length);
				return tmpParas;
			}else{
				return paras;
			}
		}
		
	}
	
	

}
