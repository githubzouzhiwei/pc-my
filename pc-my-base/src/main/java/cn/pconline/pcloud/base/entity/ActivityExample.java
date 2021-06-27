package cn.pconline.pcloud.base.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ActivityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ActivityExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
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

        public Criteria andContactNumberIsNull() {
            addCriterion("contact_number is null");
            return (Criteria) this;
        }

        public Criteria andContactNumberIsNotNull() {
            addCriterion("contact_number is not null");
            return (Criteria) this;
        }

        public Criteria andContactNumberEqualTo(String value) {
            addCriterion("contact_number =", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberNotEqualTo(String value) {
            addCriterion("contact_number <>", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberGreaterThan(String value) {
            addCriterion("contact_number >", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberGreaterThanOrEqualTo(String value) {
            addCriterion("contact_number >=", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberLessThan(String value) {
            addCriterion("contact_number <", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberLessThanOrEqualTo(String value) {
            addCriterion("contact_number <=", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberLike(String value) {
            addCriterion("contact_number like", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberNotLike(String value) {
            addCriterion("contact_number not like", value, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberIn(List<String> values) {
            addCriterion("contact_number in", values, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberNotIn(List<String> values) {
            addCriterion("contact_number not in", values, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberBetween(String value1, String value2) {
            addCriterion("contact_number between", value1, value2, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andContactNumberNotBetween(String value1, String value2) {
            addCriterion("contact_number not between", value1, value2, "contactNumber");
            return (Criteria) this;
        }

        public Criteria andApplyUrlIsNull() {
            addCriterion("apply_url is null");
            return (Criteria) this;
        }

        public Criteria andApplyUrlIsNotNull() {
            addCriterion("apply_url is not null");
            return (Criteria) this;
        }

        public Criteria andApplyUrlEqualTo(String value) {
            addCriterion("apply_url =", value, "applyUrl");
            return (Criteria) this;
        }

        public Criteria andApplyUrlNotEqualTo(String value) {
            addCriterion("apply_url <>", value, "applyUrl");
            return (Criteria) this;
        }

        public Criteria andApplyUrlGreaterThan(String value) {
            addCriterion("apply_url >", value, "applyUrl");
            return (Criteria) this;
        }

        public Criteria andApplyUrlGreaterThanOrEqualTo(String value) {
            addCriterion("apply_url >=", value, "applyUrl");
            return (Criteria) this;
        }

        public Criteria andApplyUrlLessThan(String value) {
            addCriterion("apply_url <", value, "applyUrl");
            return (Criteria) this;
        }

        public Criteria andApplyUrlLessThanOrEqualTo(String value) {
            addCriterion("apply_url <=", value, "applyUrl");
            return (Criteria) this;
        }

        public Criteria andApplyUrlLike(String value) {
            addCriterion("apply_url like", value, "applyUrl");
            return (Criteria) this;
        }

        public Criteria andApplyUrlNotLike(String value) {
            addCriterion("apply_url not like", value, "applyUrl");
            return (Criteria) this;
        }

        public Criteria andApplyUrlIn(List<String> values) {
            addCriterion("apply_url in", values, "applyUrl");
            return (Criteria) this;
        }

        public Criteria andApplyUrlNotIn(List<String> values) {
            addCriterion("apply_url not in", values, "applyUrl");
            return (Criteria) this;
        }

        public Criteria andApplyUrlBetween(String value1, String value2) {
            addCriterion("apply_url between", value1, value2, "applyUrl");
            return (Criteria) this;
        }

        public Criteria andApplyUrlNotBetween(String value1, String value2) {
            addCriterion("apply_url not between", value1, value2, "applyUrl");
            return (Criteria) this;
        }

        public Criteria andApplyTitleIsNull() {
            addCriterion("apply_title is null");
            return (Criteria) this;
        }

        public Criteria andApplyTitleIsNotNull() {
            addCriterion("apply_title is not null");
            return (Criteria) this;
        }

        public Criteria andApplyTitleEqualTo(String value) {
            addCriterion("apply_title =", value, "applyTitle");
            return (Criteria) this;
        }

        public Criteria andApplyTitleNotEqualTo(String value) {
            addCriterion("apply_title <>", value, "applyTitle");
            return (Criteria) this;
        }

        public Criteria andApplyTitleGreaterThan(String value) {
            addCriterion("apply_title >", value, "applyTitle");
            return (Criteria) this;
        }

        public Criteria andApplyTitleGreaterThanOrEqualTo(String value) {
            addCriterion("apply_title >=", value, "applyTitle");
            return (Criteria) this;
        }

        public Criteria andApplyTitleLessThan(String value) {
            addCriterion("apply_title <", value, "applyTitle");
            return (Criteria) this;
        }

        public Criteria andApplyTitleLessThanOrEqualTo(String value) {
            addCriterion("apply_title <=", value, "applyTitle");
            return (Criteria) this;
        }

        public Criteria andApplyTitleLike(String value) {
            addCriterion("apply_title like", value, "applyTitle");
            return (Criteria) this;
        }

        public Criteria andApplyTitleNotLike(String value) {
            addCriterion("apply_title not like", value, "applyTitle");
            return (Criteria) this;
        }

        public Criteria andApplyTitleIn(List<String> values) {
            addCriterion("apply_title in", values, "applyTitle");
            return (Criteria) this;
        }

        public Criteria andApplyTitleNotIn(List<String> values) {
            addCriterion("apply_title not in", values, "applyTitle");
            return (Criteria) this;
        }

        public Criteria andApplyTitleBetween(String value1, String value2) {
            addCriterion("apply_title between", value1, value2, "applyTitle");
            return (Criteria) this;
        }

        public Criteria andApplyTitleNotBetween(String value1, String value2) {
            addCriterion("apply_title not between", value1, value2, "applyTitle");
            return (Criteria) this;
        }

        public Criteria andApplyButtonUrlIsNull() {
            addCriterion("apply_button_url is null");
            return (Criteria) this;
        }

        public Criteria andApplyButtonUrlIsNotNull() {
            addCriterion("apply_button_url is not null");
            return (Criteria) this;
        }

        public Criteria andApplyButtonUrlEqualTo(String value) {
            addCriterion("apply_button_url =", value, "applyButtonUrl");
            return (Criteria) this;
        }

        public Criteria andApplyButtonUrlNotEqualTo(String value) {
            addCriterion("apply_button_url <>", value, "applyButtonUrl");
            return (Criteria) this;
        }

        public Criteria andApplyButtonUrlGreaterThan(String value) {
            addCriterion("apply_button_url >", value, "applyButtonUrl");
            return (Criteria) this;
        }

        public Criteria andApplyButtonUrlGreaterThanOrEqualTo(String value) {
            addCriterion("apply_button_url >=", value, "applyButtonUrl");
            return (Criteria) this;
        }

        public Criteria andApplyButtonUrlLessThan(String value) {
            addCriterion("apply_button_url <", value, "applyButtonUrl");
            return (Criteria) this;
        }

        public Criteria andApplyButtonUrlLessThanOrEqualTo(String value) {
            addCriterion("apply_button_url <=", value, "applyButtonUrl");
            return (Criteria) this;
        }

        public Criteria andApplyButtonUrlLike(String value) {
            addCriterion("apply_button_url like", value, "applyButtonUrl");
            return (Criteria) this;
        }

        public Criteria andApplyButtonUrlNotLike(String value) {
            addCriterion("apply_button_url not like", value, "applyButtonUrl");
            return (Criteria) this;
        }

        public Criteria andApplyButtonUrlIn(List<String> values) {
            addCriterion("apply_button_url in", values, "applyButtonUrl");
            return (Criteria) this;
        }

        public Criteria andApplyButtonUrlNotIn(List<String> values) {
            addCriterion("apply_button_url not in", values, "applyButtonUrl");
            return (Criteria) this;
        }

        public Criteria andApplyButtonUrlBetween(String value1, String value2) {
            addCriterion("apply_button_url between", value1, value2, "applyButtonUrl");
            return (Criteria) this;
        }

        public Criteria andApplyButtonUrlNotBetween(String value1, String value2) {
            addCriterion("apply_button_url not between", value1, value2, "applyButtonUrl");
            return (Criteria) this;
        }

        public Criteria andApplyLogoUrlIsNull() {
            addCriterion("apply_logo_url is null");
            return (Criteria) this;
        }

        public Criteria andApplyLogoUrlIsNotNull() {
            addCriterion("apply_logo_url is not null");
            return (Criteria) this;
        }

        public Criteria andApplyLogoUrlEqualTo(String value) {
            addCriterion("apply_logo_url =", value, "applyLogoUrl");
            return (Criteria) this;
        }

        public Criteria andApplyLogoUrlNotEqualTo(String value) {
            addCriterion("apply_logo_url <>", value, "applyLogoUrl");
            return (Criteria) this;
        }

        public Criteria andApplyLogoUrlGreaterThan(String value) {
            addCriterion("apply_logo_url >", value, "applyLogoUrl");
            return (Criteria) this;
        }

        public Criteria andApplyLogoUrlGreaterThanOrEqualTo(String value) {
            addCriterion("apply_logo_url >=", value, "applyLogoUrl");
            return (Criteria) this;
        }

        public Criteria andApplyLogoUrlLessThan(String value) {
            addCriterion("apply_logo_url <", value, "applyLogoUrl");
            return (Criteria) this;
        }

        public Criteria andApplyLogoUrlLessThanOrEqualTo(String value) {
            addCriterion("apply_logo_url <=", value, "applyLogoUrl");
            return (Criteria) this;
        }

        public Criteria andApplyLogoUrlLike(String value) {
            addCriterion("apply_logo_url like", value, "applyLogoUrl");
            return (Criteria) this;
        }

        public Criteria andApplyLogoUrlNotLike(String value) {
            addCriterion("apply_logo_url not like", value, "applyLogoUrl");
            return (Criteria) this;
        }

        public Criteria andApplyLogoUrlIn(List<String> values) {
            addCriterion("apply_logo_url in", values, "applyLogoUrl");
            return (Criteria) this;
        }

        public Criteria andApplyLogoUrlNotIn(List<String> values) {
            addCriterion("apply_logo_url not in", values, "applyLogoUrl");
            return (Criteria) this;
        }

        public Criteria andApplyLogoUrlBetween(String value1, String value2) {
            addCriterion("apply_logo_url between", value1, value2, "applyLogoUrl");
            return (Criteria) this;
        }

        public Criteria andApplyLogoUrlNotBetween(String value1, String value2) {
            addCriterion("apply_logo_url not between", value1, value2, "applyLogoUrl");
            return (Criteria) this;
        }

        public Criteria andFundsIsNull() {
            addCriterion("funds is null");
            return (Criteria) this;
        }

        public Criteria andFundsIsNotNull() {
            addCriterion("funds is not null");
            return (Criteria) this;
        }

        public Criteria andFundsEqualTo(Integer value) {
            addCriterion("funds =", value, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsNotEqualTo(Integer value) {
            addCriterion("funds <>", value, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsGreaterThan(Integer value) {
            addCriterion("funds >", value, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsGreaterThanOrEqualTo(Integer value) {
            addCriterion("funds >=", value, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsLessThan(Integer value) {
            addCriterion("funds <", value, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsLessThanOrEqualTo(Integer value) {
            addCriterion("funds <=", value, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsIn(List<Integer> values) {
            addCriterion("funds in", values, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsNotIn(List<Integer> values) {
            addCriterion("funds not in", values, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsBetween(Integer value1, Integer value2) {
            addCriterion("funds between", value1, value2, "funds");
            return (Criteria) this;
        }

        public Criteria andFundsNotBetween(Integer value1, Integer value2) {
            addCriterion("funds not between", value1, value2, "funds");
            return (Criteria) this;
        }

        public Criteria andPacketNumIsNull() {
            addCriterion("packet_num is null");
            return (Criteria) this;
        }

        public Criteria andPacketNumIsNotNull() {
            addCriterion("packet_num is not null");
            return (Criteria) this;
        }

        public Criteria andPacketNumEqualTo(Integer value) {
            addCriterion("packet_num =", value, "packetNum");
            return (Criteria) this;
        }

        public Criteria andPacketNumNotEqualTo(Integer value) {
            addCriterion("packet_num <>", value, "packetNum");
            return (Criteria) this;
        }

        public Criteria andPacketNumGreaterThan(Integer value) {
            addCriterion("packet_num >", value, "packetNum");
            return (Criteria) this;
        }

        public Criteria andPacketNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("packet_num >=", value, "packetNum");
            return (Criteria) this;
        }

        public Criteria andPacketNumLessThan(Integer value) {
            addCriterion("packet_num <", value, "packetNum");
            return (Criteria) this;
        }

        public Criteria andPacketNumLessThanOrEqualTo(Integer value) {
            addCriterion("packet_num <=", value, "packetNum");
            return (Criteria) this;
        }

        public Criteria andPacketNumIn(List<Integer> values) {
            addCriterion("packet_num in", values, "packetNum");
            return (Criteria) this;
        }

        public Criteria andPacketNumNotIn(List<Integer> values) {
            addCriterion("packet_num not in", values, "packetNum");
            return (Criteria) this;
        }

        public Criteria andPacketNumBetween(Integer value1, Integer value2) {
            addCriterion("packet_num between", value1, value2, "packetNum");
            return (Criteria) this;
        }

        public Criteria andPacketNumNotBetween(Integer value1, Integer value2) {
            addCriterion("packet_num not between", value1, value2, "packetNum");
            return (Criteria) this;
        }

        public Criteria andPacketTypeIsNull() {
            addCriterion("packet_type is null");
            return (Criteria) this;
        }

        public Criteria andPacketTypeIsNotNull() {
            addCriterion("packet_type is not null");
            return (Criteria) this;
        }

        public Criteria andPacketTypeEqualTo(Byte value) {
            addCriterion("packet_type =", value, "packetType");
            return (Criteria) this;
        }

        public Criteria andPacketTypeNotEqualTo(Byte value) {
            addCriterion("packet_type <>", value, "packetType");
            return (Criteria) this;
        }

        public Criteria andPacketTypeGreaterThan(Byte value) {
            addCriterion("packet_type >", value, "packetType");
            return (Criteria) this;
        }

        public Criteria andPacketTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("packet_type >=", value, "packetType");
            return (Criteria) this;
        }

        public Criteria andPacketTypeLessThan(Byte value) {
            addCriterion("packet_type <", value, "packetType");
            return (Criteria) this;
        }

        public Criteria andPacketTypeLessThanOrEqualTo(Byte value) {
            addCriterion("packet_type <=", value, "packetType");
            return (Criteria) this;
        }

        public Criteria andPacketTypeIn(List<Byte> values) {
            addCriterion("packet_type in", values, "packetType");
            return (Criteria) this;
        }

        public Criteria andPacketTypeNotIn(List<Byte> values) {
            addCriterion("packet_type not in", values, "packetType");
            return (Criteria) this;
        }

        public Criteria andPacketTypeBetween(Byte value1, Byte value2) {
            addCriterion("packet_type between", value1, value2, "packetType");
            return (Criteria) this;
        }

        public Criteria andPacketTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("packet_type not between", value1, value2, "packetType");
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

        public Criteria andMinPacketAmountIsNull() {
            addCriterion("min_packet_amount is null");
            return (Criteria) this;
        }

        public Criteria andMinPacketAmountIsNotNull() {
            addCriterion("min_packet_amount is not null");
            return (Criteria) this;
        }

        public Criteria andMinPacketAmountEqualTo(Integer value) {
            addCriterion("min_packet_amount =", value, "minPacketAmount");
            return (Criteria) this;
        }

        public Criteria andMinPacketAmountNotEqualTo(Integer value) {
            addCriterion("min_packet_amount <>", value, "minPacketAmount");
            return (Criteria) this;
        }

        public Criteria andMinPacketAmountGreaterThan(Integer value) {
            addCriterion("min_packet_amount >", value, "minPacketAmount");
            return (Criteria) this;
        }

        public Criteria andMinPacketAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("min_packet_amount >=", value, "minPacketAmount");
            return (Criteria) this;
        }

        public Criteria andMinPacketAmountLessThan(Integer value) {
            addCriterion("min_packet_amount <", value, "minPacketAmount");
            return (Criteria) this;
        }

        public Criteria andMinPacketAmountLessThanOrEqualTo(Integer value) {
            addCriterion("min_packet_amount <=", value, "minPacketAmount");
            return (Criteria) this;
        }

        public Criteria andMinPacketAmountIn(List<Integer> values) {
            addCriterion("min_packet_amount in", values, "minPacketAmount");
            return (Criteria) this;
        }

        public Criteria andMinPacketAmountNotIn(List<Integer> values) {
            addCriterion("min_packet_amount not in", values, "minPacketAmount");
            return (Criteria) this;
        }

        public Criteria andMinPacketAmountBetween(Integer value1, Integer value2) {
            addCriterion("min_packet_amount between", value1, value2, "minPacketAmount");
            return (Criteria) this;
        }

        public Criteria andMinPacketAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("min_packet_amount not between", value1, value2, "minPacketAmount");
            return (Criteria) this;
        }

        public Criteria andMaxPacketAmountIsNull() {
            addCriterion("max_packet_amount is null");
            return (Criteria) this;
        }

        public Criteria andMaxPacketAmountIsNotNull() {
            addCriterion("max_packet_amount is not null");
            return (Criteria) this;
        }

        public Criteria andMaxPacketAmountEqualTo(Integer value) {
            addCriterion("max_packet_amount =", value, "maxPacketAmount");
            return (Criteria) this;
        }

        public Criteria andMaxPacketAmountNotEqualTo(Integer value) {
            addCriterion("max_packet_amount <>", value, "maxPacketAmount");
            return (Criteria) this;
        }

        public Criteria andMaxPacketAmountGreaterThan(Integer value) {
            addCriterion("max_packet_amount >", value, "maxPacketAmount");
            return (Criteria) this;
        }

        public Criteria andMaxPacketAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_packet_amount >=", value, "maxPacketAmount");
            return (Criteria) this;
        }

        public Criteria andMaxPacketAmountLessThan(Integer value) {
            addCriterion("max_packet_amount <", value, "maxPacketAmount");
            return (Criteria) this;
        }

        public Criteria andMaxPacketAmountLessThanOrEqualTo(Integer value) {
            addCriterion("max_packet_amount <=", value, "maxPacketAmount");
            return (Criteria) this;
        }

        public Criteria andMaxPacketAmountIn(List<Integer> values) {
            addCriterion("max_packet_amount in", values, "maxPacketAmount");
            return (Criteria) this;
        }

        public Criteria andMaxPacketAmountNotIn(List<Integer> values) {
            addCriterion("max_packet_amount not in", values, "maxPacketAmount");
            return (Criteria) this;
        }

        public Criteria andMaxPacketAmountBetween(Integer value1, Integer value2) {
            addCriterion("max_packet_amount between", value1, value2, "maxPacketAmount");
            return (Criteria) this;
        }

        public Criteria andMaxPacketAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("max_packet_amount not between", value1, value2, "maxPacketAmount");
            return (Criteria) this;
        }

        public Criteria andRemainFundsIsNull() {
            addCriterion("remain_funds is null");
            return (Criteria) this;
        }

        public Criteria andRemainFundsIsNotNull() {
            addCriterion("remain_funds is not null");
            return (Criteria) this;
        }

        public Criteria andRemainFundsEqualTo(Integer value) {
            addCriterion("remain_funds =", value, "remainFunds");
            return (Criteria) this;
        }

        public Criteria andRemainFundsNotEqualTo(Integer value) {
            addCriterion("remain_funds <>", value, "remainFunds");
            return (Criteria) this;
        }

        public Criteria andRemainFundsGreaterThan(Integer value) {
            addCriterion("remain_funds >", value, "remainFunds");
            return (Criteria) this;
        }

        public Criteria andRemainFundsGreaterThanOrEqualTo(Integer value) {
            addCriterion("remain_funds >=", value, "remainFunds");
            return (Criteria) this;
        }

        public Criteria andRemainFundsLessThan(Integer value) {
            addCriterion("remain_funds <", value, "remainFunds");
            return (Criteria) this;
        }

        public Criteria andRemainFundsLessThanOrEqualTo(Integer value) {
            addCriterion("remain_funds <=", value, "remainFunds");
            return (Criteria) this;
        }

        public Criteria andRemainFundsIn(List<Integer> values) {
            addCriterion("remain_funds in", values, "remainFunds");
            return (Criteria) this;
        }

        public Criteria andRemainFundsNotIn(List<Integer> values) {
            addCriterion("remain_funds not in", values, "remainFunds");
            return (Criteria) this;
        }

        public Criteria andRemainFundsBetween(Integer value1, Integer value2) {
            addCriterion("remain_funds between", value1, value2, "remainFunds");
            return (Criteria) this;
        }

        public Criteria andRemainFundsNotBetween(Integer value1, Integer value2) {
            addCriterion("remain_funds not between", value1, value2, "remainFunds");
            return (Criteria) this;
        }

        public Criteria andRemainFundsAllocTypeIsNull() {
            addCriterion("remain_funds_alloc_type is null");
            return (Criteria) this;
        }

        public Criteria andRemainFundsAllocTypeIsNotNull() {
            addCriterion("remain_funds_alloc_type is not null");
            return (Criteria) this;
        }

        public Criteria andRemainFundsAllocTypeEqualTo(Byte value) {
            addCriterion("remain_funds_alloc_type =", value, "remainFundsAllocType");
            return (Criteria) this;
        }

        public Criteria andRemainFundsAllocTypeNotEqualTo(Byte value) {
            addCriterion("remain_funds_alloc_type <>", value, "remainFundsAllocType");
            return (Criteria) this;
        }

        public Criteria andRemainFundsAllocTypeGreaterThan(Byte value) {
            addCriterion("remain_funds_alloc_type >", value, "remainFundsAllocType");
            return (Criteria) this;
        }

        public Criteria andRemainFundsAllocTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("remain_funds_alloc_type >=", value, "remainFundsAllocType");
            return (Criteria) this;
        }

        public Criteria andRemainFundsAllocTypeLessThan(Byte value) {
            addCriterion("remain_funds_alloc_type <", value, "remainFundsAllocType");
            return (Criteria) this;
        }

        public Criteria andRemainFundsAllocTypeLessThanOrEqualTo(Byte value) {
            addCriterion("remain_funds_alloc_type <=", value, "remainFundsAllocType");
            return (Criteria) this;
        }

        public Criteria andRemainFundsAllocTypeIn(List<Byte> values) {
            addCriterion("remain_funds_alloc_type in", values, "remainFundsAllocType");
            return (Criteria) this;
        }

        public Criteria andRemainFundsAllocTypeNotIn(List<Byte> values) {
            addCriterion("remain_funds_alloc_type not in", values, "remainFundsAllocType");
            return (Criteria) this;
        }

        public Criteria andRemainFundsAllocTypeBetween(Byte value1, Byte value2) {
            addCriterion("remain_funds_alloc_type between", value1, value2, "remainFundsAllocType");
            return (Criteria) this;
        }

        public Criteria andRemainFundsAllocTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("remain_funds_alloc_type not between", value1, value2, "remainFundsAllocType");
            return (Criteria) this;
        }

        public Criteria andRemainPacketNumIsNull() {
            addCriterion("remain_packet_num is null");
            return (Criteria) this;
        }

        public Criteria andRemainPacketNumIsNotNull() {
            addCriterion("remain_packet_num is not null");
            return (Criteria) this;
        }

        public Criteria andRemainPacketNumEqualTo(Integer value) {
            addCriterion("remain_packet_num =", value, "remainPacketNum");
            return (Criteria) this;
        }

        public Criteria andRemainPacketNumNotEqualTo(Integer value) {
            addCriterion("remain_packet_num <>", value, "remainPacketNum");
            return (Criteria) this;
        }

        public Criteria andRemainPacketNumGreaterThan(Integer value) {
            addCriterion("remain_packet_num >", value, "remainPacketNum");
            return (Criteria) this;
        }

        public Criteria andRemainPacketNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("remain_packet_num >=", value, "remainPacketNum");
            return (Criteria) this;
        }

        public Criteria andRemainPacketNumLessThan(Integer value) {
            addCriterion("remain_packet_num <", value, "remainPacketNum");
            return (Criteria) this;
        }

        public Criteria andRemainPacketNumLessThanOrEqualTo(Integer value) {
            addCriterion("remain_packet_num <=", value, "remainPacketNum");
            return (Criteria) this;
        }

        public Criteria andRemainPacketNumIn(List<Integer> values) {
            addCriterion("remain_packet_num in", values, "remainPacketNum");
            return (Criteria) this;
        }

        public Criteria andRemainPacketNumNotIn(List<Integer> values) {
            addCriterion("remain_packet_num not in", values, "remainPacketNum");
            return (Criteria) this;
        }

        public Criteria andRemainPacketNumBetween(Integer value1, Integer value2) {
            addCriterion("remain_packet_num between", value1, value2, "remainPacketNum");
            return (Criteria) this;
        }

        public Criteria andRemainPacketNumNotBetween(Integer value1, Integer value2) {
            addCriterion("remain_packet_num not between", value1, value2, "remainPacketNum");
            return (Criteria) this;
        }

        public Criteria andIsSetStayTimeIsNull() {
            addCriterion("is_set_stay_time is null");
            return (Criteria) this;
        }

        public Criteria andIsSetStayTimeIsNotNull() {
            addCriterion("is_set_stay_time is not null");
            return (Criteria) this;
        }

        public Criteria andIsSetStayTimeEqualTo(Byte value) {
            addCriterion("is_set_stay_time =", value, "isSetStayTime");
            return (Criteria) this;
        }

        public Criteria andIsSetStayTimeNotEqualTo(Byte value) {
            addCriterion("is_set_stay_time <>", value, "isSetStayTime");
            return (Criteria) this;
        }

        public Criteria andIsSetStayTimeGreaterThan(Byte value) {
            addCriterion("is_set_stay_time >", value, "isSetStayTime");
            return (Criteria) this;
        }

        public Criteria andIsSetStayTimeGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_set_stay_time >=", value, "isSetStayTime");
            return (Criteria) this;
        }

        public Criteria andIsSetStayTimeLessThan(Byte value) {
            addCriterion("is_set_stay_time <", value, "isSetStayTime");
            return (Criteria) this;
        }

        public Criteria andIsSetStayTimeLessThanOrEqualTo(Byte value) {
            addCriterion("is_set_stay_time <=", value, "isSetStayTime");
            return (Criteria) this;
        }

        public Criteria andIsSetStayTimeIn(List<Byte> values) {
            addCriterion("is_set_stay_time in", values, "isSetStayTime");
            return (Criteria) this;
        }

        public Criteria andIsSetStayTimeNotIn(List<Byte> values) {
            addCriterion("is_set_stay_time not in", values, "isSetStayTime");
            return (Criteria) this;
        }

        public Criteria andIsSetStayTimeBetween(Byte value1, Byte value2) {
            addCriterion("is_set_stay_time between", value1, value2, "isSetStayTime");
            return (Criteria) this;
        }

        public Criteria andIsSetStayTimeNotBetween(Byte value1, Byte value2) {
            addCriterion("is_set_stay_time not between", value1, value2, "isSetStayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeIsNull() {
            addCriterion("stay_time is null");
            return (Criteria) this;
        }

        public Criteria andStayTimeIsNotNull() {
            addCriterion("stay_time is not null");
            return (Criteria) this;
        }

        public Criteria andStayTimeEqualTo(Integer value) {
            addCriterion("stay_time =", value, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeNotEqualTo(Integer value) {
            addCriterion("stay_time <>", value, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeGreaterThan(Integer value) {
            addCriterion("stay_time >", value, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("stay_time >=", value, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeLessThan(Integer value) {
            addCriterion("stay_time <", value, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeLessThanOrEqualTo(Integer value) {
            addCriterion("stay_time <=", value, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeIn(List<Integer> values) {
            addCriterion("stay_time in", values, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeNotIn(List<Integer> values) {
            addCriterion("stay_time not in", values, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeBetween(Integer value1, Integer value2) {
            addCriterion("stay_time between", value1, value2, "stayTime");
            return (Criteria) this;
        }

        public Criteria andStayTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("stay_time not between", value1, value2, "stayTime");
            return (Criteria) this;
        }

        public Criteria andIsSetJoinAreaIsNull() {
            addCriterion("is_set_join_area is null");
            return (Criteria) this;
        }

        public Criteria andIsSetJoinAreaIsNotNull() {
            addCriterion("is_set_join_area is not null");
            return (Criteria) this;
        }

        public Criteria andIsSetJoinAreaEqualTo(Byte value) {
            addCriterion("is_set_join_area =", value, "isSetJoinArea");
            return (Criteria) this;
        }

        public Criteria andIsSetJoinAreaNotEqualTo(Byte value) {
            addCriterion("is_set_join_area <>", value, "isSetJoinArea");
            return (Criteria) this;
        }

        public Criteria andIsSetJoinAreaGreaterThan(Byte value) {
            addCriterion("is_set_join_area >", value, "isSetJoinArea");
            return (Criteria) this;
        }

        public Criteria andIsSetJoinAreaGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_set_join_area >=", value, "isSetJoinArea");
            return (Criteria) this;
        }

        public Criteria andIsSetJoinAreaLessThan(Byte value) {
            addCriterion("is_set_join_area <", value, "isSetJoinArea");
            return (Criteria) this;
        }

        public Criteria andIsSetJoinAreaLessThanOrEqualTo(Byte value) {
            addCriterion("is_set_join_area <=", value, "isSetJoinArea");
            return (Criteria) this;
        }

        public Criteria andIsSetJoinAreaIn(List<Byte> values) {
            addCriterion("is_set_join_area in", values, "isSetJoinArea");
            return (Criteria) this;
        }

        public Criteria andIsSetJoinAreaNotIn(List<Byte> values) {
            addCriterion("is_set_join_area not in", values, "isSetJoinArea");
            return (Criteria) this;
        }

        public Criteria andIsSetJoinAreaBetween(Byte value1, Byte value2) {
            addCriterion("is_set_join_area between", value1, value2, "isSetJoinArea");
            return (Criteria) this;
        }

        public Criteria andIsSetJoinAreaNotBetween(Byte value1, Byte value2) {
            addCriterion("is_set_join_area not between", value1, value2, "isSetJoinArea");
            return (Criteria) this;
        }

        public Criteria andJoinAreaIsNull() {
            addCriterion("join_area is null");
            return (Criteria) this;
        }

        public Criteria andJoinAreaIsNotNull() {
            addCriterion("join_area is not null");
            return (Criteria) this;
        }

        public Criteria andJoinAreaEqualTo(String value) {
            addCriterion("join_area =", value, "joinArea");
            return (Criteria) this;
        }

        public Criteria andJoinAreaNotEqualTo(String value) {
            addCriterion("join_area <>", value, "joinArea");
            return (Criteria) this;
        }

        public Criteria andJoinAreaGreaterThan(String value) {
            addCriterion("join_area >", value, "joinArea");
            return (Criteria) this;
        }

        public Criteria andJoinAreaGreaterThanOrEqualTo(String value) {
            addCriterion("join_area >=", value, "joinArea");
            return (Criteria) this;
        }

        public Criteria andJoinAreaLessThan(String value) {
            addCriterion("join_area <", value, "joinArea");
            return (Criteria) this;
        }

        public Criteria andJoinAreaLessThanOrEqualTo(String value) {
            addCriterion("join_area <=", value, "joinArea");
            return (Criteria) this;
        }

        public Criteria andJoinAreaLike(String value) {
            addCriterion("join_area like", value, "joinArea");
            return (Criteria) this;
        }

        public Criteria andJoinAreaNotLike(String value) {
            addCriterion("join_area not like", value, "joinArea");
            return (Criteria) this;
        }

        public Criteria andJoinAreaIn(List<String> values) {
            addCriterion("join_area in", values, "joinArea");
            return (Criteria) this;
        }

        public Criteria andJoinAreaNotIn(List<String> values) {
            addCriterion("join_area not in", values, "joinArea");
            return (Criteria) this;
        }

        public Criteria andJoinAreaBetween(String value1, String value2) {
            addCriterion("join_area between", value1, value2, "joinArea");
            return (Criteria) this;
        }

        public Criteria andJoinAreaNotBetween(String value1, String value2) {
            addCriterion("join_area not between", value1, value2, "joinArea");
            return (Criteria) this;
        }

        public Criteria andIsNeedFollowIsNull() {
            addCriterion("is_need_follow is null");
            return (Criteria) this;
        }

        public Criteria andIsNeedFollowIsNotNull() {
            addCriterion("is_need_follow is not null");
            return (Criteria) this;
        }

        public Criteria andIsNeedFollowEqualTo(Byte value) {
            addCriterion("is_need_follow =", value, "isNeedFollow");
            return (Criteria) this;
        }

        public Criteria andIsNeedFollowNotEqualTo(Byte value) {
            addCriterion("is_need_follow <>", value, "isNeedFollow");
            return (Criteria) this;
        }

        public Criteria andIsNeedFollowGreaterThan(Byte value) {
            addCriterion("is_need_follow >", value, "isNeedFollow");
            return (Criteria) this;
        }

        public Criteria andIsNeedFollowGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_need_follow >=", value, "isNeedFollow");
            return (Criteria) this;
        }

        public Criteria andIsNeedFollowLessThan(Byte value) {
            addCriterion("is_need_follow <", value, "isNeedFollow");
            return (Criteria) this;
        }

        public Criteria andIsNeedFollowLessThanOrEqualTo(Byte value) {
            addCriterion("is_need_follow <=", value, "isNeedFollow");
            return (Criteria) this;
        }

        public Criteria andIsNeedFollowIn(List<Byte> values) {
            addCriterion("is_need_follow in", values, "isNeedFollow");
            return (Criteria) this;
        }

        public Criteria andIsNeedFollowNotIn(List<Byte> values) {
            addCriterion("is_need_follow not in", values, "isNeedFollow");
            return (Criteria) this;
        }

        public Criteria andIsNeedFollowBetween(Byte value1, Byte value2) {
            addCriterion("is_need_follow between", value1, value2, "isNeedFollow");
            return (Criteria) this;
        }

        public Criteria andIsNeedFollowNotBetween(Byte value1, Byte value2) {
            addCriterion("is_need_follow not between", value1, value2, "isNeedFollow");
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

        public Criteria andUpdateAtIsNull() {
            addCriterion("update_at is null");
            return (Criteria) this;
        }

        public Criteria andUpdateAtIsNotNull() {
            addCriterion("update_at is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateAtEqualTo(Date value) {
            addCriterion("update_at =", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtNotEqualTo(Date value) {
            addCriterion("update_at <>", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtGreaterThan(Date value) {
            addCriterion("update_at >", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtGreaterThanOrEqualTo(Date value) {
            addCriterion("update_at >=", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtLessThan(Date value) {
            addCriterion("update_at <", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtLessThanOrEqualTo(Date value) {
            addCriterion("update_at <=", value, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtIn(List<Date> values) {
            addCriterion("update_at in", values, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtNotIn(List<Date> values) {
            addCriterion("update_at not in", values, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtBetween(Date value1, Date value2) {
            addCriterion("update_at between", value1, value2, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateAtNotBetween(Date value1, Date value2) {
            addCriterion("update_at not between", value1, value2, "updateAt");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
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