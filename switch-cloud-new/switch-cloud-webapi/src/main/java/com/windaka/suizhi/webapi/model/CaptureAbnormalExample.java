package com.windaka.suizhi.webapi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CaptureAbnormalExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CaptureAbnormalExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeIsNull() {
            addCriterion("capture_time is null");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeIsNotNull() {
            addCriterion("capture_time is not null");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeEqualTo(Date value) {
            addCriterion("capture_time =", value, "captureTime");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeNotEqualTo(Date value) {
            addCriterion("capture_time <>", value, "captureTime");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeGreaterThan(Date value) {
            addCriterion("capture_time >", value, "captureTime");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("capture_time >=", value, "captureTime");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeLessThan(Date value) {
            addCriterion("capture_time <", value, "captureTime");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeLessThanOrEqualTo(Date value) {
            addCriterion("capture_time <=", value, "captureTime");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeIn(List<Date> values) {
            addCriterion("capture_time in", values, "captureTime");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeNotIn(List<Date> values) {
            addCriterion("capture_time not in", values, "captureTime");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeBetween(Date value1, Date value2) {
            addCriterion("capture_time between", value1, value2, "captureTime");
            return (Criteria) this;
        }

        public Criteria andCaptureTimeNotBetween(Date value1, Date value2) {
            addCriterion("capture_time not between", value1, value2, "captureTime");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeIsNull() {
            addCriterion("device_code is null");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeIsNotNull() {
            addCriterion("device_code is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeEqualTo(String value) {
            addCriterion("device_code =", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeNotEqualTo(String value) {
            addCriterion("device_code <>", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeGreaterThan(String value) {
            addCriterion("device_code >", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeGreaterThanOrEqualTo(String value) {
            addCriterion("device_code >=", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeLessThan(String value) {
            addCriterion("device_code <", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeLessThanOrEqualTo(String value) {
            addCriterion("device_code <=", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeLike(String value) {
            addCriterion("device_code like", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeNotLike(String value) {
            addCriterion("device_code not like", value, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeIn(List<String> values) {
            addCriterion("device_code in", values, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeNotIn(List<String> values) {
            addCriterion("device_code not in", values, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeBetween(String value1, String value2) {
            addCriterion("device_code between", value1, value2, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceCodeNotBetween(String value1, String value2) {
            addCriterion("device_code not between", value1, value2, "deviceCode");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIsNull() {
            addCriterion("device_name is null");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIsNotNull() {
            addCriterion("device_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceNameEqualTo(String value) {
            addCriterion("device_name =", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotEqualTo(String value) {
            addCriterion("device_name <>", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameGreaterThan(String value) {
            addCriterion("device_name >", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameGreaterThanOrEqualTo(String value) {
            addCriterion("device_name >=", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLessThan(String value) {
            addCriterion("device_name <", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLessThanOrEqualTo(String value) {
            addCriterion("device_name <=", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameLike(String value) {
            addCriterion("device_name like", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotLike(String value) {
            addCriterion("device_name not like", value, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameIn(List<String> values) {
            addCriterion("device_name in", values, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotIn(List<String> values) {
            addCriterion("device_name not in", values, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameBetween(String value1, String value2) {
            addCriterion("device_name between", value1, value2, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceNameNotBetween(String value1, String value2) {
            addCriterion("device_name not between", value1, value2, "deviceName");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIsNull() {
            addCriterion("device_type is null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIsNotNull() {
            addCriterion("device_type is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeEqualTo(String value) {
            addCriterion("device_type =", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotEqualTo(String value) {
            addCriterion("device_type <>", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeGreaterThan(String value) {
            addCriterion("device_type >", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeGreaterThanOrEqualTo(String value) {
            addCriterion("device_type >=", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLessThan(String value) {
            addCriterion("device_type <", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLessThanOrEqualTo(String value) {
            addCriterion("device_type <=", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLike(String value) {
            addCriterion("device_type like", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotLike(String value) {
            addCriterion("device_type not like", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIn(List<String> values) {
            addCriterion("device_type in", values, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotIn(List<String> values) {
            addCriterion("device_type not in", values, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeBetween(String value1, String value2) {
            addCriterion("device_type between", value1, value2, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotBetween(String value1, String value2) {
            addCriterion("device_type not between", value1, value2, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameIsNull() {
            addCriterion("device_type_name is null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameIsNotNull() {
            addCriterion("device_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameEqualTo(String value) {
            addCriterion("device_type_name =", value, "deviceTypeName");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameNotEqualTo(String value) {
            addCriterion("device_type_name <>", value, "deviceTypeName");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameGreaterThan(String value) {
            addCriterion("device_type_name >", value, "deviceTypeName");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("device_type_name >=", value, "deviceTypeName");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameLessThan(String value) {
            addCriterion("device_type_name <", value, "deviceTypeName");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameLessThanOrEqualTo(String value) {
            addCriterion("device_type_name <=", value, "deviceTypeName");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameLike(String value) {
            addCriterion("device_type_name like", value, "deviceTypeName");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameNotLike(String value) {
            addCriterion("device_type_name not like", value, "deviceTypeName");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameIn(List<String> values) {
            addCriterion("device_type_name in", values, "deviceTypeName");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameNotIn(List<String> values) {
            addCriterion("device_type_name not in", values, "deviceTypeName");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameBetween(String value1, String value2) {
            addCriterion("device_type_name between", value1, value2, "deviceTypeName");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNameNotBetween(String value1, String value2) {
            addCriterion("device_type_name not between", value1, value2, "deviceTypeName");
            return (Criteria) this;
        }

        public Criteria andDeviceLocationIsNull() {
            addCriterion("device_location is null");
            return (Criteria) this;
        }

        public Criteria andDeviceLocationIsNotNull() {
            addCriterion("device_location is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceLocationEqualTo(String value) {
            addCriterion("device_location =", value, "deviceLocation");
            return (Criteria) this;
        }

        public Criteria andDeviceLocationNotEqualTo(String value) {
            addCriterion("device_location <>", value, "deviceLocation");
            return (Criteria) this;
        }

        public Criteria andDeviceLocationGreaterThan(String value) {
            addCriterion("device_location >", value, "deviceLocation");
            return (Criteria) this;
        }

        public Criteria andDeviceLocationGreaterThanOrEqualTo(String value) {
            addCriterion("device_location >=", value, "deviceLocation");
            return (Criteria) this;
        }

        public Criteria andDeviceLocationLessThan(String value) {
            addCriterion("device_location <", value, "deviceLocation");
            return (Criteria) this;
        }

        public Criteria andDeviceLocationLessThanOrEqualTo(String value) {
            addCriterion("device_location <=", value, "deviceLocation");
            return (Criteria) this;
        }

        public Criteria andDeviceLocationLike(String value) {
            addCriterion("device_location like", value, "deviceLocation");
            return (Criteria) this;
        }

        public Criteria andDeviceLocationNotLike(String value) {
            addCriterion("device_location not like", value, "deviceLocation");
            return (Criteria) this;
        }

        public Criteria andDeviceLocationIn(List<String> values) {
            addCriterion("device_location in", values, "deviceLocation");
            return (Criteria) this;
        }

        public Criteria andDeviceLocationNotIn(List<String> values) {
            addCriterion("device_location not in", values, "deviceLocation");
            return (Criteria) this;
        }

        public Criteria andDeviceLocationBetween(String value1, String value2) {
            addCriterion("device_location between", value1, value2, "deviceLocation");
            return (Criteria) this;
        }

        public Criteria andDeviceLocationNotBetween(String value1, String value2) {
            addCriterion("device_location not between", value1, value2, "deviceLocation");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeIsNull() {
            addCriterion("community_code is null");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeIsNotNull() {
            addCriterion("community_code is not null");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeEqualTo(String value) {
            addCriterion("community_code =", value, "communityCode");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeNotEqualTo(String value) {
            addCriterion("community_code <>", value, "communityCode");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeGreaterThan(String value) {
            addCriterion("community_code >", value, "communityCode");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeGreaterThanOrEqualTo(String value) {
            addCriterion("community_code >=", value, "communityCode");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeLessThan(String value) {
            addCriterion("community_code <", value, "communityCode");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeLessThanOrEqualTo(String value) {
            addCriterion("community_code <=", value, "communityCode");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeLike(String value) {
            addCriterion("community_code like", value, "communityCode");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeNotLike(String value) {
            addCriterion("community_code not like", value, "communityCode");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeIn(List<String> values) {
            addCriterion("community_code in", values, "communityCode");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeNotIn(List<String> values) {
            addCriterion("community_code not in", values, "communityCode");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeBetween(String value1, String value2) {
            addCriterion("community_code between", value1, value2, "communityCode");
            return (Criteria) this;
        }

        public Criteria andCommunityCodeNotBetween(String value1, String value2) {
            addCriterion("community_code not between", value1, value2, "communityCode");
            return (Criteria) this;
        }

        public Criteria andCommunityNameIsNull() {
            addCriterion("community_name is null");
            return (Criteria) this;
        }

        public Criteria andCommunityNameIsNotNull() {
            addCriterion("community_name is not null");
            return (Criteria) this;
        }

        public Criteria andCommunityNameEqualTo(String value) {
            addCriterion("community_name =", value, "communityName");
            return (Criteria) this;
        }

        public Criteria andCommunityNameNotEqualTo(String value) {
            addCriterion("community_name <>", value, "communityName");
            return (Criteria) this;
        }

        public Criteria andCommunityNameGreaterThan(String value) {
            addCriterion("community_name >", value, "communityName");
            return (Criteria) this;
        }

        public Criteria andCommunityNameGreaterThanOrEqualTo(String value) {
            addCriterion("community_name >=", value, "communityName");
            return (Criteria) this;
        }

        public Criteria andCommunityNameLessThan(String value) {
            addCriterion("community_name <", value, "communityName");
            return (Criteria) this;
        }

        public Criteria andCommunityNameLessThanOrEqualTo(String value) {
            addCriterion("community_name <=", value, "communityName");
            return (Criteria) this;
        }

        public Criteria andCommunityNameLike(String value) {
            addCriterion("community_name like", value, "communityName");
            return (Criteria) this;
        }

        public Criteria andCommunityNameNotLike(String value) {
            addCriterion("community_name not like", value, "communityName");
            return (Criteria) this;
        }

        public Criteria andCommunityNameIn(List<String> values) {
            addCriterion("community_name in", values, "communityName");
            return (Criteria) this;
        }

        public Criteria andCommunityNameNotIn(List<String> values) {
            addCriterion("community_name not in", values, "communityName");
            return (Criteria) this;
        }

        public Criteria andCommunityNameBetween(String value1, String value2) {
            addCriterion("community_name between", value1, value2, "communityName");
            return (Criteria) this;
        }

        public Criteria andCommunityNameNotBetween(String value1, String value2) {
            addCriterion("community_name not between", value1, value2, "communityName");
            return (Criteria) this;
        }

        public Criteria andEventIsNull() {
            addCriterion("event is null");
            return (Criteria) this;
        }

        public Criteria andEventIsNotNull() {
            addCriterion("event is not null");
            return (Criteria) this;
        }

        public Criteria andEventEqualTo(Short value) {
            addCriterion("event =", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotEqualTo(Short value) {
            addCriterion("event <>", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventGreaterThan(Short value) {
            addCriterion("event >", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventGreaterThanOrEqualTo(Short value) {
            addCriterion("event >=", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventLessThan(Short value) {
            addCriterion("event <", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventLessThanOrEqualTo(Short value) {
            addCriterion("event <=", value, "event");
            return (Criteria) this;
        }

        public Criteria andEventIn(List<Short> values) {
            addCriterion("event in", values, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotIn(List<Short> values) {
            addCriterion("event not in", values, "event");
            return (Criteria) this;
        }

        public Criteria andEventBetween(Short value1, Short value2) {
            addCriterion("event between", value1, value2, "event");
            return (Criteria) this;
        }

        public Criteria andEventNotBetween(Short value1, Short value2) {
            addCriterion("event not between", value1, value2, "event");
            return (Criteria) this;
        }

        public Criteria andEventNameIsNull() {
            addCriterion("event_name is null");
            return (Criteria) this;
        }

        public Criteria andEventNameIsNotNull() {
            addCriterion("event_name is not null");
            return (Criteria) this;
        }

        public Criteria andEventNameEqualTo(String value) {
            addCriterion("event_name =", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameNotEqualTo(String value) {
            addCriterion("event_name <>", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameGreaterThan(String value) {
            addCriterion("event_name >", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameGreaterThanOrEqualTo(String value) {
            addCriterion("event_name >=", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameLessThan(String value) {
            addCriterion("event_name <", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameLessThanOrEqualTo(String value) {
            addCriterion("event_name <=", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameLike(String value) {
            addCriterion("event_name like", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameNotLike(String value) {
            addCriterion("event_name not like", value, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameIn(List<String> values) {
            addCriterion("event_name in", values, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameNotIn(List<String> values) {
            addCriterion("event_name not in", values, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameBetween(String value1, String value2) {
            addCriterion("event_name between", value1, value2, "eventName");
            return (Criteria) this;
        }

        public Criteria andEventNameNotBetween(String value1, String value2) {
            addCriterion("event_name not between", value1, value2, "eventName");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNull() {
            addCriterion("begin_time is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(Date value) {
            addCriterion("begin_time =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(Date value) {
            addCriterion("begin_time <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(Date value) {
            addCriterion("begin_time >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("begin_time >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(Date value) {
            addCriterion("begin_time <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("begin_time <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<Date> values) {
            addCriterion("begin_time in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<Date> values) {
            addCriterion("begin_time not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(Date value1, Date value2) {
            addCriterion("begin_time between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("begin_time not between", value1, value2, "beginTime");
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

        public Criteria andCapImageIsNull() {
            addCriterion("cap_image is null");
            return (Criteria) this;
        }

        public Criteria andCapImageIsNotNull() {
            addCriterion("cap_image is not null");
            return (Criteria) this;
        }

        public Criteria andCapImageEqualTo(String value) {
            addCriterion("cap_image =", value, "capImage");
            return (Criteria) this;
        }

        public Criteria andCapImageNotEqualTo(String value) {
            addCriterion("cap_image <>", value, "capImage");
            return (Criteria) this;
        }

        public Criteria andCapImageGreaterThan(String value) {
            addCriterion("cap_image >", value, "capImage");
            return (Criteria) this;
        }

        public Criteria andCapImageGreaterThanOrEqualTo(String value) {
            addCriterion("cap_image >=", value, "capImage");
            return (Criteria) this;
        }

        public Criteria andCapImageLessThan(String value) {
            addCriterion("cap_image <", value, "capImage");
            return (Criteria) this;
        }

        public Criteria andCapImageLessThanOrEqualTo(String value) {
            addCriterion("cap_image <=", value, "capImage");
            return (Criteria) this;
        }

        public Criteria andCapImageLike(String value) {
            addCriterion("cap_image like", value, "capImage");
            return (Criteria) this;
        }

        public Criteria andCapImageNotLike(String value) {
            addCriterion("cap_image not like", value, "capImage");
            return (Criteria) this;
        }

        public Criteria andCapImageIn(List<String> values) {
            addCriterion("cap_image in", values, "capImage");
            return (Criteria) this;
        }

        public Criteria andCapImageNotIn(List<String> values) {
            addCriterion("cap_image not in", values, "capImage");
            return (Criteria) this;
        }

        public Criteria andCapImageBetween(String value1, String value2) {
            addCriterion("cap_image between", value1, value2, "capImage");
            return (Criteria) this;
        }

        public Criteria andCapImageNotBetween(String value1, String value2) {
            addCriterion("cap_image not between", value1, value2, "capImage");
            return (Criteria) this;
        }

        public Criteria andCapVideoIsNull() {
            addCriterion("cap_video is null");
            return (Criteria) this;
        }

        public Criteria andCapVideoIsNotNull() {
            addCriterion("cap_video is not null");
            return (Criteria) this;
        }

        public Criteria andCapVideoEqualTo(String value) {
            addCriterion("cap_video =", value, "capVideo");
            return (Criteria) this;
        }

        public Criteria andCapVideoNotEqualTo(String value) {
            addCriterion("cap_video <>", value, "capVideo");
            return (Criteria) this;
        }

        public Criteria andCapVideoGreaterThan(String value) {
            addCriterion("cap_video >", value, "capVideo");
            return (Criteria) this;
        }

        public Criteria andCapVideoGreaterThanOrEqualTo(String value) {
            addCriterion("cap_video >=", value, "capVideo");
            return (Criteria) this;
        }

        public Criteria andCapVideoLessThan(String value) {
            addCriterion("cap_video <", value, "capVideo");
            return (Criteria) this;
        }

        public Criteria andCapVideoLessThanOrEqualTo(String value) {
            addCriterion("cap_video <=", value, "capVideo");
            return (Criteria) this;
        }

        public Criteria andCapVideoLike(String value) {
            addCriterion("cap_video like", value, "capVideo");
            return (Criteria) this;
        }

        public Criteria andCapVideoNotLike(String value) {
            addCriterion("cap_video not like", value, "capVideo");
            return (Criteria) this;
        }

        public Criteria andCapVideoIn(List<String> values) {
            addCriterion("cap_video in", values, "capVideo");
            return (Criteria) this;
        }

        public Criteria andCapVideoNotIn(List<String> values) {
            addCriterion("cap_video not in", values, "capVideo");
            return (Criteria) this;
        }

        public Criteria andCapVideoBetween(String value1, String value2) {
            addCriterion("cap_video between", value1, value2, "capVideo");
            return (Criteria) this;
        }

        public Criteria andCapVideoNotBetween(String value1, String value2) {
            addCriterion("cap_video not between", value1, value2, "capVideo");
            return (Criteria) this;
        }

        public Criteria andHandelStatusIsNull() {
            addCriterion("handel_status is null");
            return (Criteria) this;
        }

        public Criteria andHandelStatusIsNotNull() {
            addCriterion("handel_status is not null");
            return (Criteria) this;
        }

        public Criteria andHandelStatusEqualTo(Byte value) {
            addCriterion("handel_status =", value, "handelStatus");
            return (Criteria) this;
        }

        public Criteria andHandelStatusNotEqualTo(Byte value) {
            addCriterion("handel_status <>", value, "handelStatus");
            return (Criteria) this;
        }

        public Criteria andHandelStatusGreaterThan(Byte value) {
            addCriterion("handel_status >", value, "handelStatus");
            return (Criteria) this;
        }

        public Criteria andHandelStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("handel_status >=", value, "handelStatus");
            return (Criteria) this;
        }

        public Criteria andHandelStatusLessThan(Byte value) {
            addCriterion("handel_status <", value, "handelStatus");
            return (Criteria) this;
        }

        public Criteria andHandelStatusLessThanOrEqualTo(Byte value) {
            addCriterion("handel_status <=", value, "handelStatus");
            return (Criteria) this;
        }

        public Criteria andHandelStatusIn(List<Byte> values) {
            addCriterion("handel_status in", values, "handelStatus");
            return (Criteria) this;
        }

        public Criteria andHandelStatusNotIn(List<Byte> values) {
            addCriterion("handel_status not in", values, "handelStatus");
            return (Criteria) this;
        }

        public Criteria andHandelStatusBetween(Byte value1, Byte value2) {
            addCriterion("handel_status between", value1, value2, "handelStatus");
            return (Criteria) this;
        }

        public Criteria andHandelStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("handel_status not between", value1, value2, "handelStatus");
            return (Criteria) this;
        }

        public Criteria andHandelTimeIsNull() {
            addCriterion("handel_time is null");
            return (Criteria) this;
        }

        public Criteria andHandelTimeIsNotNull() {
            addCriterion("handel_time is not null");
            return (Criteria) this;
        }

        public Criteria andHandelTimeEqualTo(Date value) {
            addCriterion("handel_time =", value, "handelTime");
            return (Criteria) this;
        }

        public Criteria andHandelTimeNotEqualTo(Date value) {
            addCriterion("handel_time <>", value, "handelTime");
            return (Criteria) this;
        }

        public Criteria andHandelTimeGreaterThan(Date value) {
            addCriterion("handel_time >", value, "handelTime");
            return (Criteria) this;
        }

        public Criteria andHandelTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("handel_time >=", value, "handelTime");
            return (Criteria) this;
        }

        public Criteria andHandelTimeLessThan(Date value) {
            addCriterion("handel_time <", value, "handelTime");
            return (Criteria) this;
        }

        public Criteria andHandelTimeLessThanOrEqualTo(Date value) {
            addCriterion("handel_time <=", value, "handelTime");
            return (Criteria) this;
        }

        public Criteria andHandelTimeIn(List<Date> values) {
            addCriterion("handel_time in", values, "handelTime");
            return (Criteria) this;
        }

        public Criteria andHandelTimeNotIn(List<Date> values) {
            addCriterion("handel_time not in", values, "handelTime");
            return (Criteria) this;
        }

        public Criteria andHandelTimeBetween(Date value1, Date value2) {
            addCriterion("handel_time between", value1, value2, "handelTime");
            return (Criteria) this;
        }

        public Criteria andHandelTimeNotBetween(Date value1, Date value2) {
            addCriterion("handel_time not between", value1, value2, "handelTime");
            return (Criteria) this;
        }

        public Criteria andHandelUserIsNull() {
            addCriterion("handel_user is null");
            return (Criteria) this;
        }

        public Criteria andHandelUserIsNotNull() {
            addCriterion("handel_user is not null");
            return (Criteria) this;
        }

        public Criteria andHandelUserEqualTo(String value) {
            addCriterion("handel_user =", value, "handelUser");
            return (Criteria) this;
        }

        public Criteria andHandelUserNotEqualTo(String value) {
            addCriterion("handel_user <>", value, "handelUser");
            return (Criteria) this;
        }

        public Criteria andHandelUserGreaterThan(String value) {
            addCriterion("handel_user >", value, "handelUser");
            return (Criteria) this;
        }

        public Criteria andHandelUserGreaterThanOrEqualTo(String value) {
            addCriterion("handel_user >=", value, "handelUser");
            return (Criteria) this;
        }

        public Criteria andHandelUserLessThan(String value) {
            addCriterion("handel_user <", value, "handelUser");
            return (Criteria) this;
        }

        public Criteria andHandelUserLessThanOrEqualTo(String value) {
            addCriterion("handel_user <=", value, "handelUser");
            return (Criteria) this;
        }

        public Criteria andHandelUserLike(String value) {
            addCriterion("handel_user like", value, "handelUser");
            return (Criteria) this;
        }

        public Criteria andHandelUserNotLike(String value) {
            addCriterion("handel_user not like", value, "handelUser");
            return (Criteria) this;
        }

        public Criteria andHandelUserIn(List<String> values) {
            addCriterion("handel_user in", values, "handelUser");
            return (Criteria) this;
        }

        public Criteria andHandelUserNotIn(List<String> values) {
            addCriterion("handel_user not in", values, "handelUser");
            return (Criteria) this;
        }

        public Criteria andHandelUserBetween(String value1, String value2) {
            addCriterion("handel_user between", value1, value2, "handelUser");
            return (Criteria) this;
        }

        public Criteria andHandelUserNotBetween(String value1, String value2) {
            addCriterion("handel_user not between", value1, value2, "handelUser");
            return (Criteria) this;
        }

        public Criteria andHandelUserIdIsNull() {
            addCriterion("handel_user_id is null");
            return (Criteria) this;
        }

        public Criteria andHandelUserIdIsNotNull() {
            addCriterion("handel_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andHandelUserIdEqualTo(String value) {
            addCriterion("handel_user_id =", value, "handelUserId");
            return (Criteria) this;
        }

        public Criteria andHandelUserIdNotEqualTo(String value) {
            addCriterion("handel_user_id <>", value, "handelUserId");
            return (Criteria) this;
        }

        public Criteria andHandelUserIdGreaterThan(String value) {
            addCriterion("handel_user_id >", value, "handelUserId");
            return (Criteria) this;
        }

        public Criteria andHandelUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("handel_user_id >=", value, "handelUserId");
            return (Criteria) this;
        }

        public Criteria andHandelUserIdLessThan(String value) {
            addCriterion("handel_user_id <", value, "handelUserId");
            return (Criteria) this;
        }

        public Criteria andHandelUserIdLessThanOrEqualTo(String value) {
            addCriterion("handel_user_id <=", value, "handelUserId");
            return (Criteria) this;
        }

        public Criteria andHandelUserIdLike(String value) {
            addCriterion("handel_user_id like", value, "handelUserId");
            return (Criteria) this;
        }

        public Criteria andHandelUserIdNotLike(String value) {
            addCriterion("handel_user_id not like", value, "handelUserId");
            return (Criteria) this;
        }

        public Criteria andHandelUserIdIn(List<String> values) {
            addCriterion("handel_user_id in", values, "handelUserId");
            return (Criteria) this;
        }

        public Criteria andHandelUserIdNotIn(List<String> values) {
            addCriterion("handel_user_id not in", values, "handelUserId");
            return (Criteria) this;
        }

        public Criteria andHandelUserIdBetween(String value1, String value2) {
            addCriterion("handel_user_id between", value1, value2, "handelUserId");
            return (Criteria) this;
        }

        public Criteria andHandelUserIdNotBetween(String value1, String value2) {
            addCriterion("handel_user_id not between", value1, value2, "handelUserId");
            return (Criteria) this;
        }

        public Criteria andHandelConnIsNull() {
            addCriterion("handel_conn is null");
            return (Criteria) this;
        }

        public Criteria andHandelConnIsNotNull() {
            addCriterion("handel_conn is not null");
            return (Criteria) this;
        }

        public Criteria andHandelConnEqualTo(String value) {
            addCriterion("handel_conn =", value, "handelConn");
            return (Criteria) this;
        }

        public Criteria andHandelConnNotEqualTo(String value) {
            addCriterion("handel_conn <>", value, "handelConn");
            return (Criteria) this;
        }

        public Criteria andHandelConnGreaterThan(String value) {
            addCriterion("handel_conn >", value, "handelConn");
            return (Criteria) this;
        }

        public Criteria andHandelConnGreaterThanOrEqualTo(String value) {
            addCriterion("handel_conn >=", value, "handelConn");
            return (Criteria) this;
        }

        public Criteria andHandelConnLessThan(String value) {
            addCriterion("handel_conn <", value, "handelConn");
            return (Criteria) this;
        }

        public Criteria andHandelConnLessThanOrEqualTo(String value) {
            addCriterion("handel_conn <=", value, "handelConn");
            return (Criteria) this;
        }

        public Criteria andHandelConnLike(String value) {
            addCriterion("handel_conn like", value, "handelConn");
            return (Criteria) this;
        }

        public Criteria andHandelConnNotLike(String value) {
            addCriterion("handel_conn not like", value, "handelConn");
            return (Criteria) this;
        }

        public Criteria andHandelConnIn(List<String> values) {
            addCriterion("handel_conn in", values, "handelConn");
            return (Criteria) this;
        }

        public Criteria andHandelConnNotIn(List<String> values) {
            addCriterion("handel_conn not in", values, "handelConn");
            return (Criteria) this;
        }

        public Criteria andHandelConnBetween(String value1, String value2) {
            addCriterion("handel_conn between", value1, value2, "handelConn");
            return (Criteria) this;
        }

        public Criteria andHandelConnNotBetween(String value1, String value2) {
            addCriterion("handel_conn not between", value1, value2, "handelConn");
            return (Criteria) this;
        }

        public Criteria andHandelAdviseIsNull() {
            addCriterion("handel_advise is null");
            return (Criteria) this;
        }

        public Criteria andHandelAdviseIsNotNull() {
            addCriterion("handel_advise is not null");
            return (Criteria) this;
        }

        public Criteria andHandelAdviseEqualTo(String value) {
            addCriterion("handel_advise =", value, "handelAdvise");
            return (Criteria) this;
        }

        public Criteria andHandelAdviseNotEqualTo(String value) {
            addCriterion("handel_advise <>", value, "handelAdvise");
            return (Criteria) this;
        }

        public Criteria andHandelAdviseGreaterThan(String value) {
            addCriterion("handel_advise >", value, "handelAdvise");
            return (Criteria) this;
        }

        public Criteria andHandelAdviseGreaterThanOrEqualTo(String value) {
            addCriterion("handel_advise >=", value, "handelAdvise");
            return (Criteria) this;
        }

        public Criteria andHandelAdviseLessThan(String value) {
            addCriterion("handel_advise <", value, "handelAdvise");
            return (Criteria) this;
        }

        public Criteria andHandelAdviseLessThanOrEqualTo(String value) {
            addCriterion("handel_advise <=", value, "handelAdvise");
            return (Criteria) this;
        }

        public Criteria andHandelAdviseLike(String value) {
            addCriterion("handel_advise like", value, "handelAdvise");
            return (Criteria) this;
        }

        public Criteria andHandelAdviseNotLike(String value) {
            addCriterion("handel_advise not like", value, "handelAdvise");
            return (Criteria) this;
        }

        public Criteria andHandelAdviseIn(List<String> values) {
            addCriterion("handel_advise in", values, "handelAdvise");
            return (Criteria) this;
        }

        public Criteria andHandelAdviseNotIn(List<String> values) {
            addCriterion("handel_advise not in", values, "handelAdvise");
            return (Criteria) this;
        }

        public Criteria andHandelAdviseBetween(String value1, String value2) {
            addCriterion("handel_advise between", value1, value2, "handelAdvise");
            return (Criteria) this;
        }

        public Criteria andHandelAdviseNotBetween(String value1, String value2) {
            addCriterion("handel_advise not between", value1, value2, "handelAdvise");
            return (Criteria) this;
        }

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
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