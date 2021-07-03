package cn.pconline.pcloud.base.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityPacketExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ActivityPacketExample() {
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

        public Criteria andActivityPacketIdIsNull() {
            addCriterion("activity_packet_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityPacketIdIsNotNull() {
            addCriterion("activity_packet_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityPacketIdEqualTo(Long value) {
            addCriterion("activity_packet_id =", value, "activityPacketId");
            return (Criteria) this;
        }

        public Criteria andActivityPacketIdNotEqualTo(Long value) {
            addCriterion("activity_packet_id <>", value, "activityPacketId");
            return (Criteria) this;
        }

        public Criteria andActivityPacketIdGreaterThan(Long value) {
            addCriterion("activity_packet_id >", value, "activityPacketId");
            return (Criteria) this;
        }

        public Criteria andActivityPacketIdGreaterThanOrEqualTo(Long value) {
            addCriterion("activity_packet_id >=", value, "activityPacketId");
            return (Criteria) this;
        }

        public Criteria andActivityPacketIdLessThan(Long value) {
            addCriterion("activity_packet_id <", value, "activityPacketId");
            return (Criteria) this;
        }

        public Criteria andActivityPacketIdLessThanOrEqualTo(Long value) {
            addCriterion("activity_packet_id <=", value, "activityPacketId");
            return (Criteria) this;
        }

        public Criteria andActivityPacketIdIn(List<Long> values) {
            addCriterion("activity_packet_id in", values, "activityPacketId");
            return (Criteria) this;
        }

        public Criteria andActivityPacketIdNotIn(List<Long> values) {
            addCriterion("activity_packet_id not in", values, "activityPacketId");
            return (Criteria) this;
        }

        public Criteria andActivityPacketIdBetween(Long value1, Long value2) {
            addCriterion("activity_packet_id between", value1, value2, "activityPacketId");
            return (Criteria) this;
        }

        public Criteria andActivityPacketIdNotBetween(Long value1, Long value2) {
            addCriterion("activity_packet_id not between", value1, value2, "activityPacketId");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNull() {
            addCriterion("activity_id is null");
            return (Criteria) this;
        }

        public Criteria andActivityIdIsNotNull() {
            addCriterion("activity_id is not null");
            return (Criteria) this;
        }

        public Criteria andActivityIdEqualTo(Long value) {
            addCriterion("activity_id =", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotEqualTo(Long value) {
            addCriterion("activity_id <>", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThan(Long value) {
            addCriterion("activity_id >", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdGreaterThanOrEqualTo(Long value) {
            addCriterion("activity_id >=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThan(Long value) {
            addCriterion("activity_id <", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdLessThanOrEqualTo(Long value) {
            addCriterion("activity_id <=", value, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdIn(List<Long> values) {
            addCriterion("activity_id in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotIn(List<Long> values) {
            addCriterion("activity_id not in", values, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdBetween(Long value1, Long value2) {
            addCriterion("activity_id between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andActivityIdNotBetween(Long value1, Long value2) {
            addCriterion("activity_id not between", value1, value2, "activityId");
            return (Criteria) this;
        }

        public Criteria andPacketAmountIsNull() {
            addCriterion("packet_amount is null");
            return (Criteria) this;
        }

        public Criteria andPacketAmountIsNotNull() {
            addCriterion("packet_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPacketAmountEqualTo(Integer value) {
            addCriterion("packet_amount =", value, "packetAmount");
            return (Criteria) this;
        }

        public Criteria andPacketAmountNotEqualTo(Integer value) {
            addCriterion("packet_amount <>", value, "packetAmount");
            return (Criteria) this;
        }

        public Criteria andPacketAmountGreaterThan(Integer value) {
            addCriterion("packet_amount >", value, "packetAmount");
            return (Criteria) this;
        }

        public Criteria andPacketAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("packet_amount >=", value, "packetAmount");
            return (Criteria) this;
        }

        public Criteria andPacketAmountLessThan(Integer value) {
            addCriterion("packet_amount <", value, "packetAmount");
            return (Criteria) this;
        }

        public Criteria andPacketAmountLessThanOrEqualTo(Integer value) {
            addCriterion("packet_amount <=", value, "packetAmount");
            return (Criteria) this;
        }

        public Criteria andPacketAmountIn(List<Integer> values) {
            addCriterion("packet_amount in", values, "packetAmount");
            return (Criteria) this;
        }

        public Criteria andPacketAmountNotIn(List<Integer> values) {
            addCriterion("packet_amount not in", values, "packetAmount");
            return (Criteria) this;
        }

        public Criteria andPacketAmountBetween(Integer value1, Integer value2) {
            addCriterion("packet_amount between", value1, value2, "packetAmount");
            return (Criteria) this;
        }

        public Criteria andPacketAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("packet_amount not between", value1, value2, "packetAmount");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNull() {
            addCriterion("openid is null");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNotNull() {
            addCriterion("openid is not null");
            return (Criteria) this;
        }

        public Criteria andOpenidEqualTo(String value) {
            addCriterion("openid =", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotEqualTo(String value) {
            addCriterion("openid <>", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThan(String value) {
            addCriterion("openid >", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("openid >=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThan(String value) {
            addCriterion("openid <", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThanOrEqualTo(String value) {
            addCriterion("openid <=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLike(String value) {
            addCriterion("openid like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotLike(String value) {
            addCriterion("openid not like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidIn(List<String> values) {
            addCriterion("openid in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotIn(List<String> values) {
            addCriterion("openid not in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidBetween(String value1, String value2) {
            addCriterion("openid between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotBetween(String value1, String value2) {
            addCriterion("openid not between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andDrawAtIsNull() {
            addCriterion("draw_at is null");
            return (Criteria) this;
        }

        public Criteria andDrawAtIsNotNull() {
            addCriterion("draw_at is not null");
            return (Criteria) this;
        }

        public Criteria andDrawAtEqualTo(Date value) {
            addCriterion("draw_at =", value, "drawAt");
            return (Criteria) this;
        }

        public Criteria andDrawAtNotEqualTo(Date value) {
            addCriterion("draw_at <>", value, "drawAt");
            return (Criteria) this;
        }

        public Criteria andDrawAtGreaterThan(Date value) {
            addCriterion("draw_at >", value, "drawAt");
            return (Criteria) this;
        }

        public Criteria andDrawAtGreaterThanOrEqualTo(Date value) {
            addCriterion("draw_at >=", value, "drawAt");
            return (Criteria) this;
        }

        public Criteria andDrawAtLessThan(Date value) {
            addCriterion("draw_at <", value, "drawAt");
            return (Criteria) this;
        }

        public Criteria andDrawAtLessThanOrEqualTo(Date value) {
            addCriterion("draw_at <=", value, "drawAt");
            return (Criteria) this;
        }

        public Criteria andDrawAtIn(List<Date> values) {
            addCriterion("draw_at in", values, "drawAt");
            return (Criteria) this;
        }

        public Criteria andDrawAtNotIn(List<Date> values) {
            addCriterion("draw_at not in", values, "drawAt");
            return (Criteria) this;
        }

        public Criteria andDrawAtBetween(Date value1, Date value2) {
            addCriterion("draw_at between", value1, value2, "drawAt");
            return (Criteria) this;
        }

        public Criteria andDrawAtNotBetween(Date value1, Date value2) {
            addCriterion("draw_at not between", value1, value2, "drawAt");
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