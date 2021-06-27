/*
 * Copyright 1997-2011
 *
 * http://www.pconline.com.cn
 *
 */
package cn.pconline.framework.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/*import org.gelivable.dao.GeliOrm;
import org.gelivable.web.EnvUtils;*/

/**
 * 查询条件类
 * @author xhchen
 */
public class Criteria {

    public static enum Operator {
        eq(" = ", " 等于 "),
        nq(" != ", " 不等于 "),
        gt(" > ", " 大于 "),
        ge(" >= ", " 大于等于 "),
        lt(" < ", " 小于 "),
        le(" <= ", " 小于等于 "),
        nl(" is null ", " 为空 "),
        nn(" is not null ", " 不为空 "),
        cn(" like ", " 包含 "),
        sw(" like ", " 开头为 "),
        ew(" like ", " 结尾为 "),
        es(" exists ", " 存在 ");

        String value;
        String label;
        Operator(String value, String label) {
            this.value = value;
            this.label = label;
        }
    }

    List<Criteria.Field> fieldList = new ArrayList<Criteria.Field>();
    String orderBy;
    //String[] orderBys;
    Map<String , Integer> orderByMap = new HashMap<String , Integer>();
    int pageSize = 25;
    int pageNo = 1;

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }
    
    public String getOrderBy(){
    	return orderBy;
    }
    
    public void setOrderBy(String orderBy){
    	this.orderBy = orderBy;
    }
    
    public void setPageSize(int pageSize){
    	this.pageSize = pageSize;
    }
    
    public void setPageNo(int pageNo){
    	this.pageNo = pageNo;
    }
    public Map<String, Integer> getOrderByMap() {
		return orderByMap;
	}

	public void setOrderByMap(Map<String, Integer> orderByMap) {
		this.orderByMap = orderByMap;
	}
	
	public Criteria() {
        
    }
    
    public void add(String field, Operator op, String value, String type) {
        fieldList.add(new Field(field, op, value, type));
    }

    public void add(String field, Operator op, String value) {
        fieldList.add(new Field(field, op, value));
    }
    public void add(String field, Operator op, Object value) {
        fieldList.add(new Field(field, op, value.toString()));
    }
    
    public void addOrderBy(String field , String orderBy){
    	if("desc".equals(orderBy.trim().toLowerCase())){
        	orderByMap.put(field, 1);
    	}else{
    		orderByMap.put(field, 0);
    	}
    }
    
    /**
     * 0:升序 1：降序
     * @param field
     * @param orderBy
     */
    public void addOrderBy(String field , int orderBy){
    	orderByMap.put(field, orderBy);
    }

	@SuppressWarnings({"rawtypes" })
	public String toSql(Class type) {
        StringBuilder buf = new StringBuilder();
        for (Field field : fieldList) {
            buf.append(field.toSql(type));
        }
        if (orderByMap.size() == 0 && orderBy != null) {
            buf.append(" order by ").append(orderBy);
        }else{      
        	if(orderByMap.size() > 0){
        		orderBy = "";
        		buf.append(" order by ");
        		Iterator<String> i = orderByMap.keySet().iterator();
        		int index = 0;        		
        		while(i.hasNext()){
        			String key = i.next();
        			if(index>0)orderBy+=",";
        			orderBy += key + " " + (orderByMap.get(key)==0?"asc":"desc");      
        			index ++;
        		}
        		buf.append(orderBy);
        	}
        }
        return buf.toString();
    }
    
	@SuppressWarnings({"rawtypes" })
	public String toCountSql(Class type) {
        StringBuilder buf = new StringBuilder();
        for (Field field : fieldList) {
            buf.append(field.toSql(type));
        }
        return buf.toString();
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        for (Field field : fieldList) {
            buf.append(field.field).append(field.op.label).append(field.value);
            buf.append(" 且 ");
        }
        if (buf.length() > 3) {
            buf.delete(buf.length() - 3, buf.length());
        }
        if (orderBy != null) {
            buf.append("; 排序: ").append(orderBy);
        }
        return buf.toString();
    }

	@SuppressWarnings({"rawtypes" })
	public Object[] getParameters(Class type) {
        List<Object> list = new ArrayList<Object>();
        for (Field field : fieldList) {
            if (field.op != Operator.nl && field.op != Operator.nn && field.op != Operator.es) {
                list.add(field.getValue());
            }
        }
        return list.toArray();
    }

	class Field {
		String field;
		Operator op;
		String value;
		String type;

		public Field(String field, Operator op, String value) {
			this.field = field;
			this.op = op;
			this.value = value;
		}

		public Field(String field, Operator op, String value, String type) {
			this.field = field;
			this.op = op;
			this.value = value;
			this.type = type;
		}

		@SuppressWarnings({ "rawtypes" })
		public String toSql(Class type) {
			StringBuilder buf = new StringBuilder();
			String column = getColumn(type);
			if (op == Operator.nn || op == Operator.nl) {
				buf.append(" and ").append(column).append(op.value);
			} else if(op == Operator.es){
				buf.append(" and ").append(op.value).append(getValue());
			}
			else {
				buf.append(" and ").append(column).append(op.value).append('?');
			}
			return buf.toString();
		}

		@SuppressWarnings({ "rawtypes" })
		public String getColumn(Class type) {
			/*GeliOrm orm = EnvUtils.getEnv().getBean(GeliOrm.class);
			return orm.getColumnByField(type, field);*/
			return "";
		}

		public Object getValue() {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");

			if (type == null) {
				if (op == Operator.cn)
					return '%' + value + '%';
				else if (op == Operator.sw)
					return value + '%';
				else if (op == Operator.ew)
					return '%' + value;
				else if (op == Operator.es)
					return '(' + value + ')';
				else
					return value;
			}
			if (type.charAt(0) == 'l') {
				return Long.valueOf(value);
			} else if (type.charAt(0) == 'i') {
				return Integer.valueOf(value);
			} else if (type.charAt(0) == 'f') {
				return Double.valueOf(value);
			} else if (type.charAt(0) == 'd') {
				return sdf.format(value);
			} else if (type.charAt(0) == 't') {
				return sdf.format(value);
			}
			return value;
		}
	}

	/**
	 * 用于后台高级查询
	 * 
	 * @param type
	 * @return
	 */
	public List<String> getListFieldStr() {
		List<String> list = new ArrayList<String>();
		for (Field field : fieldList) {
			String column = field.getColumn(String.class);
			Operator op = field.op;
			String value = field.getValue().toString();
			String result = column + ",";
			if (op.equals(Operator.eq)) {
				result += "2";
			} else if (op.equals(Operator.cn) || op.equals(Operator.sw) || op.equals(Operator.ew)) {
				result += "1";
				// 替换数值 前后的百分号
				value = value.replaceAll("^%", "");
				value = value.replaceAll("%$", "");
			} else if (op.equals(Operator.gt)) {
				result += "3";
			} else if (op.equals(Operator.ge)) {
				result += "4";
			} else if (op.equals(Operator.lt)) {
				result += "5";
			} else if (op.equals(Operator.le)) {
				result += "6";
			}
			result += "," + value;
			list.add(result);
		}
		return list;
	}
    
	/**
	 * 用于后台高级查询
	 * 
	 * @param type
	 * @return
	 */
	public List<String> getListOrder() {
		List<String> list = new ArrayList<String>();
		String[] orderBys = orderBy.split(",");
		for (String orderStr : orderBys) {
			String[] spilt = orderStr.split(" ");
			String field = spilt[0];

			if (spilt.length > 1) {
				String orderType = "";
				for (int i = 1; i < spilt.length; i++) {
					if (!"".equals(spilt[i])) {
						orderType = spilt[i];
						break;
					}
				}
				list.add(field + "," + orderTypeToNumber(orderType));
			} else if (spilt.length < 2) {
				list.add(field + ",0");
			}

		}
		return list;
	}
    
	/**
	 * 排序类型转为数字
	 * 0：升序
	 * 1：降序
	 * 
	 * @return
	 */
	private int orderTypeToNumber(String orderType) {
		if (orderType.trim().toLowerCase().equals("desc")) {
			return 1;
		} else {
			return 0;
		}
	}
}
