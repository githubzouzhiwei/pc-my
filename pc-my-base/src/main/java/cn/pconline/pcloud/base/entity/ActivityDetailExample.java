package cn.pconline.pcloud.base.entity;

import java.util.ArrayList;
import java.util.List;

public class ActivityDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ActivityDetailExample() {
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

        public Criteria andShareTitleIsNull() {
            addCriterion("share_title is null");
            return (Criteria) this;
        }

        public Criteria andShareTitleIsNotNull() {
            addCriterion("share_title is not null");
            return (Criteria) this;
        }

        public Criteria andShareTitleEqualTo(String value) {
            addCriterion("share_title =", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleNotEqualTo(String value) {
            addCriterion("share_title <>", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleGreaterThan(String value) {
            addCriterion("share_title >", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleGreaterThanOrEqualTo(String value) {
            addCriterion("share_title >=", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleLessThan(String value) {
            addCriterion("share_title <", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleLessThanOrEqualTo(String value) {
            addCriterion("share_title <=", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleLike(String value) {
            addCriterion("share_title like", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleNotLike(String value) {
            addCriterion("share_title not like", value, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleIn(List<String> values) {
            addCriterion("share_title in", values, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleNotIn(List<String> values) {
            addCriterion("share_title not in", values, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleBetween(String value1, String value2) {
            addCriterion("share_title between", value1, value2, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareTitleNotBetween(String value1, String value2) {
            addCriterion("share_title not between", value1, value2, "shareTitle");
            return (Criteria) this;
        }

        public Criteria andShareDescIsNull() {
            addCriterion("share_desc is null");
            return (Criteria) this;
        }

        public Criteria andShareDescIsNotNull() {
            addCriterion("share_desc is not null");
            return (Criteria) this;
        }

        public Criteria andShareDescEqualTo(String value) {
            addCriterion("share_desc =", value, "shareDesc");
            return (Criteria) this;
        }

        public Criteria andShareDescNotEqualTo(String value) {
            addCriterion("share_desc <>", value, "shareDesc");
            return (Criteria) this;
        }

        public Criteria andShareDescGreaterThan(String value) {
            addCriterion("share_desc >", value, "shareDesc");
            return (Criteria) this;
        }

        public Criteria andShareDescGreaterThanOrEqualTo(String value) {
            addCriterion("share_desc >=", value, "shareDesc");
            return (Criteria) this;
        }

        public Criteria andShareDescLessThan(String value) {
            addCriterion("share_desc <", value, "shareDesc");
            return (Criteria) this;
        }

        public Criteria andShareDescLessThanOrEqualTo(String value) {
            addCriterion("share_desc <=", value, "shareDesc");
            return (Criteria) this;
        }

        public Criteria andShareDescLike(String value) {
            addCriterion("share_desc like", value, "shareDesc");
            return (Criteria) this;
        }

        public Criteria andShareDescNotLike(String value) {
            addCriterion("share_desc not like", value, "shareDesc");
            return (Criteria) this;
        }

        public Criteria andShareDescIn(List<String> values) {
            addCriterion("share_desc in", values, "shareDesc");
            return (Criteria) this;
        }

        public Criteria andShareDescNotIn(List<String> values) {
            addCriterion("share_desc not in", values, "shareDesc");
            return (Criteria) this;
        }

        public Criteria andShareDescBetween(String value1, String value2) {
            addCriterion("share_desc between", value1, value2, "shareDesc");
            return (Criteria) this;
        }

        public Criteria andShareDescNotBetween(String value1, String value2) {
            addCriterion("share_desc not between", value1, value2, "shareDesc");
            return (Criteria) this;
        }

        public Criteria andSharePicUrlIsNull() {
            addCriterion("share_pic_url is null");
            return (Criteria) this;
        }

        public Criteria andSharePicUrlIsNotNull() {
            addCriterion("share_pic_url is not null");
            return (Criteria) this;
        }

        public Criteria andSharePicUrlEqualTo(String value) {
            addCriterion("share_pic_url =", value, "sharePicUrl");
            return (Criteria) this;
        }

        public Criteria andSharePicUrlNotEqualTo(String value) {
            addCriterion("share_pic_url <>", value, "sharePicUrl");
            return (Criteria) this;
        }

        public Criteria andSharePicUrlGreaterThan(String value) {
            addCriterion("share_pic_url >", value, "sharePicUrl");
            return (Criteria) this;
        }

        public Criteria andSharePicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("share_pic_url >=", value, "sharePicUrl");
            return (Criteria) this;
        }

        public Criteria andSharePicUrlLessThan(String value) {
            addCriterion("share_pic_url <", value, "sharePicUrl");
            return (Criteria) this;
        }

        public Criteria andSharePicUrlLessThanOrEqualTo(String value) {
            addCriterion("share_pic_url <=", value, "sharePicUrl");
            return (Criteria) this;
        }

        public Criteria andSharePicUrlLike(String value) {
            addCriterion("share_pic_url like", value, "sharePicUrl");
            return (Criteria) this;
        }

        public Criteria andSharePicUrlNotLike(String value) {
            addCriterion("share_pic_url not like", value, "sharePicUrl");
            return (Criteria) this;
        }

        public Criteria andSharePicUrlIn(List<String> values) {
            addCriterion("share_pic_url in", values, "sharePicUrl");
            return (Criteria) this;
        }

        public Criteria andSharePicUrlNotIn(List<String> values) {
            addCriterion("share_pic_url not in", values, "sharePicUrl");
            return (Criteria) this;
        }

        public Criteria andSharePicUrlBetween(String value1, String value2) {
            addCriterion("share_pic_url between", value1, value2, "sharePicUrl");
            return (Criteria) this;
        }

        public Criteria andSharePicUrlNotBetween(String value1, String value2) {
            addCriterion("share_pic_url not between", value1, value2, "sharePicUrl");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(Byte value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(Byte value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(Byte value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(Byte value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(Byte value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(Byte value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<Byte> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<Byte> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(Byte value1, Byte value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(Byte value1, Byte value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceInfoIsNull() {
            addCriterion("source_info is null");
            return (Criteria) this;
        }

        public Criteria andSourceInfoIsNotNull() {
            addCriterion("source_info is not null");
            return (Criteria) this;
        }

        public Criteria andSourceInfoEqualTo(String value) {
            addCriterion("source_info =", value, "sourceInfo");
            return (Criteria) this;
        }

        public Criteria andSourceInfoNotEqualTo(String value) {
            addCriterion("source_info <>", value, "sourceInfo");
            return (Criteria) this;
        }

        public Criteria andSourceInfoGreaterThan(String value) {
            addCriterion("source_info >", value, "sourceInfo");
            return (Criteria) this;
        }

        public Criteria andSourceInfoGreaterThanOrEqualTo(String value) {
            addCriterion("source_info >=", value, "sourceInfo");
            return (Criteria) this;
        }

        public Criteria andSourceInfoLessThan(String value) {
            addCriterion("source_info <", value, "sourceInfo");
            return (Criteria) this;
        }

        public Criteria andSourceInfoLessThanOrEqualTo(String value) {
            addCriterion("source_info <=", value, "sourceInfo");
            return (Criteria) this;
        }

        public Criteria andSourceInfoLike(String value) {
            addCriterion("source_info like", value, "sourceInfo");
            return (Criteria) this;
        }

        public Criteria andSourceInfoNotLike(String value) {
            addCriterion("source_info not like", value, "sourceInfo");
            return (Criteria) this;
        }

        public Criteria andSourceInfoIn(List<String> values) {
            addCriterion("source_info in", values, "sourceInfo");
            return (Criteria) this;
        }

        public Criteria andSourceInfoNotIn(List<String> values) {
            addCriterion("source_info not in", values, "sourceInfo");
            return (Criteria) this;
        }

        public Criteria andSourceInfoBetween(String value1, String value2) {
            addCriterion("source_info between", value1, value2, "sourceInfo");
            return (Criteria) this;
        }

        public Criteria andSourceInfoNotBetween(String value1, String value2) {
            addCriterion("source_info not between", value1, value2, "sourceInfo");
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