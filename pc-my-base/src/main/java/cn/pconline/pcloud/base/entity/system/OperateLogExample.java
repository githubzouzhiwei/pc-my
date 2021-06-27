package cn.pconline.pcloud.base.entity.system;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperateLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OperateLogExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andOperateLogIdIsNull() {
            addCriterion("operate_log_id is null");
            return (Criteria) this;
        }

        public Criteria andOperateLogIdIsNotNull() {
            addCriterion("operate_log_id is not null");
            return (Criteria) this;
        }

        public Criteria andOperateLogIdEqualTo(Long value) {
            addCriterion("operate_log_id =", value, "operateLogId");
            return (Criteria) this;
        }

        public Criteria andOperateLogIdNotEqualTo(Long value) {
            addCriterion("operate_log_id <>", value, "operateLogId");
            return (Criteria) this;
        }

        public Criteria andOperateLogIdGreaterThan(Long value) {
            addCriterion("operate_log_id >", value, "operateLogId");
            return (Criteria) this;
        }

        public Criteria andOperateLogIdGreaterThanOrEqualTo(Long value) {
            addCriterion("operate_log_id >=", value, "operateLogId");
            return (Criteria) this;
        }

        public Criteria andOperateLogIdLessThan(Long value) {
            addCriterion("operate_log_id <", value, "operateLogId");
            return (Criteria) this;
        }

        public Criteria andOperateLogIdLessThanOrEqualTo(Long value) {
            addCriterion("operate_log_id <=", value, "operateLogId");
            return (Criteria) this;
        }

        public Criteria andOperateLogIdIn(List<Long> values) {
            addCriterion("operate_log_id in", values, "operateLogId");
            return (Criteria) this;
        }

        public Criteria andOperateLogIdNotIn(List<Long> values) {
            addCriterion("operate_log_id not in", values, "operateLogId");
            return (Criteria) this;
        }

        public Criteria andOperateLogIdBetween(Long value1, Long value2) {
            addCriterion("operate_log_id between", value1, value2, "operateLogId");
            return (Criteria) this;
        }

        public Criteria andOperateLogIdNotBetween(Long value1, Long value2) {
            addCriterion("operate_log_id not between", value1, value2, "operateLogId");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andModuleIsNull() {
            addCriterion("module is null");
            return (Criteria) this;
        }

        public Criteria andModuleIsNotNull() {
            addCriterion("module is not null");
            return (Criteria) this;
        }

        public Criteria andModuleEqualTo(String value) {
            addCriterion("module =", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleNotEqualTo(String value) {
            addCriterion("module <>", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleGreaterThan(String value) {
            addCriterion("module >", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleGreaterThanOrEqualTo(String value) {
            addCriterion("module >=", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleLessThan(String value) {
            addCriterion("module <", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleLessThanOrEqualTo(String value) {
            addCriterion("module <=", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleLike(String value) {
            addCriterion("module like", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleNotLike(String value) {
            addCriterion("module not like", value, "module");
            return (Criteria) this;
        }

        public Criteria andModuleIn(List<String> values) {
            addCriterion("module in", values, "module");
            return (Criteria) this;
        }

        public Criteria andModuleNotIn(List<String> values) {
            addCriterion("module not in", values, "module");
            return (Criteria) this;
        }

        public Criteria andModuleBetween(String value1, String value2) {
            addCriterion("module between", value1, value2, "module");
            return (Criteria) this;
        }

        public Criteria andModuleNotBetween(String value1, String value2) {
            addCriterion("module not between", value1, value2, "module");
            return (Criteria) this;
        }

        public Criteria andUpdateTargetIsNull() {
            addCriterion("update_target is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTargetIsNotNull() {
            addCriterion("update_target is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTargetEqualTo(String value) {
            addCriterion("update_target =", value, "updateTarget");
            return (Criteria) this;
        }

        public Criteria andUpdateTargetNotEqualTo(String value) {
            addCriterion("update_target <>", value, "updateTarget");
            return (Criteria) this;
        }

        public Criteria andUpdateTargetGreaterThan(String value) {
            addCriterion("update_target >", value, "updateTarget");
            return (Criteria) this;
        }

        public Criteria andUpdateTargetGreaterThanOrEqualTo(String value) {
            addCriterion("update_target >=", value, "updateTarget");
            return (Criteria) this;
        }

        public Criteria andUpdateTargetLessThan(String value) {
            addCriterion("update_target <", value, "updateTarget");
            return (Criteria) this;
        }

        public Criteria andUpdateTargetLessThanOrEqualTo(String value) {
            addCriterion("update_target <=", value, "updateTarget");
            return (Criteria) this;
        }

        public Criteria andUpdateTargetLike(String value) {
            addCriterion("update_target like", value, "updateTarget");
            return (Criteria) this;
        }

        public Criteria andUpdateTargetNotLike(String value) {
            addCriterion("update_target not like", value, "updateTarget");
            return (Criteria) this;
        }

        public Criteria andUpdateTargetIn(List<String> values) {
            addCriterion("update_target in", values, "updateTarget");
            return (Criteria) this;
        }

        public Criteria andUpdateTargetNotIn(List<String> values) {
            addCriterion("update_target not in", values, "updateTarget");
            return (Criteria) this;
        }

        public Criteria andUpdateTargetBetween(String value1, String value2) {
            addCriterion("update_target between", value1, value2, "updateTarget");
            return (Criteria) this;
        }

        public Criteria andUpdateTargetNotBetween(String value1, String value2) {
            addCriterion("update_target not between", value1, value2, "updateTarget");
            return (Criteria) this;
        }

        public Criteria andUpdateContentIsNull() {
            addCriterion("update_content is null");
            return (Criteria) this;
        }

        public Criteria andUpdateContentIsNotNull() {
            addCriterion("update_content is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateContentEqualTo(String value) {
            addCriterion("update_content =", value, "updateContent");
            return (Criteria) this;
        }

        public Criteria andUpdateContentNotEqualTo(String value) {
            addCriterion("update_content <>", value, "updateContent");
            return (Criteria) this;
        }

        public Criteria andUpdateContentGreaterThan(String value) {
            addCriterion("update_content >", value, "updateContent");
            return (Criteria) this;
        }

        public Criteria andUpdateContentGreaterThanOrEqualTo(String value) {
            addCriterion("update_content >=", value, "updateContent");
            return (Criteria) this;
        }

        public Criteria andUpdateContentLessThan(String value) {
            addCriterion("update_content <", value, "updateContent");
            return (Criteria) this;
        }

        public Criteria andUpdateContentLessThanOrEqualTo(String value) {
            addCriterion("update_content <=", value, "updateContent");
            return (Criteria) this;
        }

        public Criteria andUpdateContentLike(String value) {
            addCriterion("update_content like", value, "updateContent");
            return (Criteria) this;
        }

        public Criteria andUpdateContentNotLike(String value) {
            addCriterion("update_content not like", value, "updateContent");
            return (Criteria) this;
        }

        public Criteria andUpdateContentIn(List<String> values) {
            addCriterion("update_content in", values, "updateContent");
            return (Criteria) this;
        }

        public Criteria andUpdateContentNotIn(List<String> values) {
            addCriterion("update_content not in", values, "updateContent");
            return (Criteria) this;
        }

        public Criteria andUpdateContentBetween(String value1, String value2) {
            addCriterion("update_content between", value1, value2, "updateContent");
            return (Criteria) this;
        }

        public Criteria andUpdateContentNotBetween(String value1, String value2) {
            addCriterion("update_content not between", value1, value2, "updateContent");
            return (Criteria) this;
        }

        public Criteria andCreateAtIsNull() {
            addCriterion("create_at is null");
            return (Criteria) this;
        }

        public Criteria andCreateAtIsNotNull() {
            addCriterion("create_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreateAtEqualTo(Date value) {
            addCriterion("create_at =", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtNotEqualTo(Date value) {
            addCriterion("create_at <>", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtGreaterThan(Date value) {
            addCriterion("create_at >", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtGreaterThanOrEqualTo(Date value) {
            addCriterion("create_at >=", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtLessThan(Date value) {
            addCriterion("create_at <", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtLessThanOrEqualTo(Date value) {
            addCriterion("create_at <=", value, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtIn(List<Date> values) {
            addCriterion("create_at in", values, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtNotIn(List<Date> values) {
            addCriterion("create_at not in", values, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtBetween(Date value1, Date value2) {
            addCriterion("create_at between", value1, value2, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateAtNotBetween(Date value1, Date value2) {
            addCriterion("create_at not between", value1, value2, "createAt");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}