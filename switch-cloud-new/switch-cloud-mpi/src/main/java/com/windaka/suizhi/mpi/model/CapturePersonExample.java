package com.windaka.suizhi.mpi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CapturePersonExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CapturePersonExample() {
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

        public Criteria andPersonCodeIsNull() {
            addCriterion("person_code is null");
            return (Criteria) this;
        }

        public Criteria andPersonCodeIsNotNull() {
            addCriterion("person_code is not null");
            return (Criteria) this;
        }

        public Criteria andPersonCodeEqualTo(String value) {
            addCriterion("person_code =", value, "personCode");
            return (Criteria) this;
        }

        public Criteria andPersonCodeNotEqualTo(String value) {
            addCriterion("person_code <>", value, "personCode");
            return (Criteria) this;
        }

        public Criteria andPersonCodeGreaterThan(String value) {
            addCriterion("person_code >", value, "personCode");
            return (Criteria) this;
        }

        public Criteria andPersonCodeGreaterThanOrEqualTo(String value) {
            addCriterion("person_code >=", value, "personCode");
            return (Criteria) this;
        }

        public Criteria andPersonCodeLessThan(String value) {
            addCriterion("person_code <", value, "personCode");
            return (Criteria) this;
        }

        public Criteria andPersonCodeLessThanOrEqualTo(String value) {
            addCriterion("person_code <=", value, "personCode");
            return (Criteria) this;
        }

        public Criteria andPersonCodeLike(String value) {
            addCriterion("person_code like", value, "personCode");
            return (Criteria) this;
        }

        public Criteria andPersonCodeNotLike(String value) {
            addCriterion("person_code not like", value, "personCode");
            return (Criteria) this;
        }

        public Criteria andPersonCodeIn(List<String> values) {
            addCriterion("person_code in", values, "personCode");
            return (Criteria) this;
        }

        public Criteria andPersonCodeNotIn(List<String> values) {
            addCriterion("person_code not in", values, "personCode");
            return (Criteria) this;
        }

        public Criteria andPersonCodeBetween(String value1, String value2) {
            addCriterion("person_code between", value1, value2, "personCode");
            return (Criteria) this;
        }

        public Criteria andPersonCodeNotBetween(String value1, String value2) {
            addCriterion("person_code not between", value1, value2, "personCode");
            return (Criteria) this;
        }

        public Criteria andPersonNameIsNull() {
            addCriterion("person_name is null");
            return (Criteria) this;
        }

        public Criteria andPersonNameIsNotNull() {
            addCriterion("person_name is not null");
            return (Criteria) this;
        }

        public Criteria andPersonNameEqualTo(String value) {
            addCriterion("person_name =", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotEqualTo(String value) {
            addCriterion("person_name <>", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameGreaterThan(String value) {
            addCriterion("person_name >", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameGreaterThanOrEqualTo(String value) {
            addCriterion("person_name >=", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameLessThan(String value) {
            addCriterion("person_name <", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameLessThanOrEqualTo(String value) {
            addCriterion("person_name <=", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameLike(String value) {
            addCriterion("person_name like", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotLike(String value) {
            addCriterion("person_name not like", value, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameIn(List<String> values) {
            addCriterion("person_name in", values, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotIn(List<String> values) {
            addCriterion("person_name not in", values, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameBetween(String value1, String value2) {
            addCriterion("person_name between", value1, value2, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonNameNotBetween(String value1, String value2) {
            addCriterion("person_name not between", value1, value2, "personName");
            return (Criteria) this;
        }

        public Criteria andPersonTypeIsNull() {
            addCriterion("person_type is null");
            return (Criteria) this;
        }

        public Criteria andPersonTypeIsNotNull() {
            addCriterion("person_type is not null");
            return (Criteria) this;
        }

        public Criteria andPersonTypeEqualTo(Short value) {
            addCriterion("person_type =", value, "personType");
            return (Criteria) this;
        }

        public Criteria andPersonTypeNotEqualTo(Short value) {
            addCriterion("person_type <>", value, "personType");
            return (Criteria) this;
        }

        public Criteria andPersonTypeGreaterThan(Short value) {
            addCriterion("person_type >", value, "personType");
            return (Criteria) this;
        }

        public Criteria andPersonTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("person_type >=", value, "personType");
            return (Criteria) this;
        }

        public Criteria andPersonTypeLessThan(Short value) {
            addCriterion("person_type <", value, "personType");
            return (Criteria) this;
        }

        public Criteria andPersonTypeLessThanOrEqualTo(Short value) {
            addCriterion("person_type <=", value, "personType");
            return (Criteria) this;
        }

        public Criteria andPersonTypeIn(List<Short> values) {
            addCriterion("person_type in", values, "personType");
            return (Criteria) this;
        }

        public Criteria andPersonTypeNotIn(List<Short> values) {
            addCriterion("person_type not in", values, "personType");
            return (Criteria) this;
        }

        public Criteria andPersonTypeBetween(Short value1, Short value2) {
            addCriterion("person_type between", value1, value2, "personType");
            return (Criteria) this;
        }

        public Criteria andPersonTypeNotBetween(Short value1, Short value2) {
            addCriterion("person_type not between", value1, value2, "personType");
            return (Criteria) this;
        }

        public Criteria andPersonTypeNameIsNull() {
            addCriterion("person_type_name is null");
            return (Criteria) this;
        }

        public Criteria andPersonTypeNameIsNotNull() {
            addCriterion("person_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andPersonTypeNameEqualTo(String value) {
            addCriterion("person_type_name =", value, "personTypeName");
            return (Criteria) this;
        }

        public Criteria andPersonTypeNameNotEqualTo(String value) {
            addCriterion("person_type_name <>", value, "personTypeName");
            return (Criteria) this;
        }

        public Criteria andPersonTypeNameGreaterThan(String value) {
            addCriterion("person_type_name >", value, "personTypeName");
            return (Criteria) this;
        }

        public Criteria andPersonTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("person_type_name >=", value, "personTypeName");
            return (Criteria) this;
        }

        public Criteria andPersonTypeNameLessThan(String value) {
            addCriterion("person_type_name <", value, "personTypeName");
            return (Criteria) this;
        }

        public Criteria andPersonTypeNameLessThanOrEqualTo(String value) {
            addCriterion("person_type_name <=", value, "personTypeName");
            return (Criteria) this;
        }

        public Criteria andPersonTypeNameLike(String value) {
            addCriterion("person_type_name like", value, "personTypeName");
            return (Criteria) this;
        }

        public Criteria andPersonTypeNameNotLike(String value) {
            addCriterion("person_type_name not like", value, "personTypeName");
            return (Criteria) this;
        }

        public Criteria andPersonTypeNameIn(List<String> values) {
            addCriterion("person_type_name in", values, "personTypeName");
            return (Criteria) this;
        }

        public Criteria andPersonTypeNameNotIn(List<String> values) {
            addCriterion("person_type_name not in", values, "personTypeName");
            return (Criteria) this;
        }

        public Criteria andPersonTypeNameBetween(String value1, String value2) {
            addCriterion("person_type_name between", value1, value2, "personTypeName");
            return (Criteria) this;
        }

        public Criteria andPersonTypeNameNotBetween(String value1, String value2) {
            addCriterion("person_type_name not between", value1, value2, "personTypeName");
            return (Criteria) this;
        }

        public Criteria andPersonPhoneIsNull() {
            addCriterion("person_phone is null");
            return (Criteria) this;
        }

        public Criteria andPersonPhoneIsNotNull() {
            addCriterion("person_phone is not null");
            return (Criteria) this;
        }

        public Criteria andPersonPhoneEqualTo(String value) {
            addCriterion("person_phone =", value, "personPhone");
            return (Criteria) this;
        }

        public Criteria andPersonPhoneNotEqualTo(String value) {
            addCriterion("person_phone <>", value, "personPhone");
            return (Criteria) this;
        }

        public Criteria andPersonPhoneGreaterThan(String value) {
            addCriterion("person_phone >", value, "personPhone");
            return (Criteria) this;
        }

        public Criteria andPersonPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("person_phone >=", value, "personPhone");
            return (Criteria) this;
        }

        public Criteria andPersonPhoneLessThan(String value) {
            addCriterion("person_phone <", value, "personPhone");
            return (Criteria) this;
        }

        public Criteria andPersonPhoneLessThanOrEqualTo(String value) {
            addCriterion("person_phone <=", value, "personPhone");
            return (Criteria) this;
        }

        public Criteria andPersonPhoneLike(String value) {
            addCriterion("person_phone like", value, "personPhone");
            return (Criteria) this;
        }

        public Criteria andPersonPhoneNotLike(String value) {
            addCriterion("person_phone not like", value, "personPhone");
            return (Criteria) this;
        }

        public Criteria andPersonPhoneIn(List<String> values) {
            addCriterion("person_phone in", values, "personPhone");
            return (Criteria) this;
        }

        public Criteria andPersonPhoneNotIn(List<String> values) {
            addCriterion("person_phone not in", values, "personPhone");
            return (Criteria) this;
        }

        public Criteria andPersonPhoneBetween(String value1, String value2) {
            addCriterion("person_phone between", value1, value2, "personPhone");
            return (Criteria) this;
        }

        public Criteria andPersonPhoneNotBetween(String value1, String value2) {
            addCriterion("person_phone not between", value1, value2, "personPhone");
            return (Criteria) this;
        }

        public Criteria andPersonSexIsNull() {
            addCriterion("person_sex is null");
            return (Criteria) this;
        }

        public Criteria andPersonSexIsNotNull() {
            addCriterion("person_sex is not null");
            return (Criteria) this;
        }

        public Criteria andPersonSexEqualTo(String value) {
            addCriterion("person_sex =", value, "personSex");
            return (Criteria) this;
        }

        public Criteria andPersonSexNotEqualTo(String value) {
            addCriterion("person_sex <>", value, "personSex");
            return (Criteria) this;
        }

        public Criteria andPersonSexGreaterThan(String value) {
            addCriterion("person_sex >", value, "personSex");
            return (Criteria) this;
        }

        public Criteria andPersonSexGreaterThanOrEqualTo(String value) {
            addCriterion("person_sex >=", value, "personSex");
            return (Criteria) this;
        }

        public Criteria andPersonSexLessThan(String value) {
            addCriterion("person_sex <", value, "personSex");
            return (Criteria) this;
        }

        public Criteria andPersonSexLessThanOrEqualTo(String value) {
            addCriterion("person_sex <=", value, "personSex");
            return (Criteria) this;
        }

        public Criteria andPersonSexLike(String value) {
            addCriterion("person_sex like", value, "personSex");
            return (Criteria) this;
        }

        public Criteria andPersonSexNotLike(String value) {
            addCriterion("person_sex not like", value, "personSex");
            return (Criteria) this;
        }

        public Criteria andPersonSexIn(List<String> values) {
            addCriterion("person_sex in", values, "personSex");
            return (Criteria) this;
        }

        public Criteria andPersonSexNotIn(List<String> values) {
            addCriterion("person_sex not in", values, "personSex");
            return (Criteria) this;
        }

        public Criteria andPersonSexBetween(String value1, String value2) {
            addCriterion("person_sex between", value1, value2, "personSex");
            return (Criteria) this;
        }

        public Criteria andPersonSexNotBetween(String value1, String value2) {
            addCriterion("person_sex not between", value1, value2, "personSex");
            return (Criteria) this;
        }

        public Criteria andPersonBirthdayIsNull() {
            addCriterion("person_birthday is null");
            return (Criteria) this;
        }

        public Criteria andPersonBirthdayIsNotNull() {
            addCriterion("person_birthday is not null");
            return (Criteria) this;
        }

        public Criteria andPersonBirthdayEqualTo(Date value) {
            addCriterion("person_birthday =", value, "personBirthday");
            return (Criteria) this;
        }

        public Criteria andPersonBirthdayNotEqualTo(Date value) {
            addCriterion("person_birthday <>", value, "personBirthday");
            return (Criteria) this;
        }

        public Criteria andPersonBirthdayGreaterThan(Date value) {
            addCriterion("person_birthday >", value, "personBirthday");
            return (Criteria) this;
        }

        public Criteria andPersonBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterion("person_birthday >=", value, "personBirthday");
            return (Criteria) this;
        }

        public Criteria andPersonBirthdayLessThan(Date value) {
            addCriterion("person_birthday <", value, "personBirthday");
            return (Criteria) this;
        }

        public Criteria andPersonBirthdayLessThanOrEqualTo(Date value) {
            addCriterion("person_birthday <=", value, "personBirthday");
            return (Criteria) this;
        }

        public Criteria andPersonBirthdayIn(List<Date> values) {
            addCriterion("person_birthday in", values, "personBirthday");
            return (Criteria) this;
        }

        public Criteria andPersonBirthdayNotIn(List<Date> values) {
            addCriterion("person_birthday not in", values, "personBirthday");
            return (Criteria) this;
        }

        public Criteria andPersonBirthdayBetween(Date value1, Date value2) {
            addCriterion("person_birthday between", value1, value2, "personBirthday");
            return (Criteria) this;
        }

        public Criteria andPersonBirthdayNotBetween(Date value1, Date value2) {
            addCriterion("person_birthday not between", value1, value2, "personBirthday");
            return (Criteria) this;
        }

        public Criteria andPersonCountryIsNull() {
            addCriterion("person_country is null");
            return (Criteria) this;
        }

        public Criteria andPersonCountryIsNotNull() {
            addCriterion("person_country is not null");
            return (Criteria) this;
        }

        public Criteria andPersonCountryEqualTo(Short value) {
            addCriterion("person_country =", value, "personCountry");
            return (Criteria) this;
        }

        public Criteria andPersonCountryNotEqualTo(Short value) {
            addCriterion("person_country <>", value, "personCountry");
            return (Criteria) this;
        }

        public Criteria andPersonCountryGreaterThan(Short value) {
            addCriterion("person_country >", value, "personCountry");
            return (Criteria) this;
        }

        public Criteria andPersonCountryGreaterThanOrEqualTo(Short value) {
            addCriterion("person_country >=", value, "personCountry");
            return (Criteria) this;
        }

        public Criteria andPersonCountryLessThan(Short value) {
            addCriterion("person_country <", value, "personCountry");
            return (Criteria) this;
        }

        public Criteria andPersonCountryLessThanOrEqualTo(Short value) {
            addCriterion("person_country <=", value, "personCountry");
            return (Criteria) this;
        }

        public Criteria andPersonCountryIn(List<Short> values) {
            addCriterion("person_country in", values, "personCountry");
            return (Criteria) this;
        }

        public Criteria andPersonCountryNotIn(List<Short> values) {
            addCriterion("person_country not in", values, "personCountry");
            return (Criteria) this;
        }

        public Criteria andPersonCountryBetween(Short value1, Short value2) {
            addCriterion("person_country between", value1, value2, "personCountry");
            return (Criteria) this;
        }

        public Criteria andPersonCountryNotBetween(Short value1, Short value2) {
            addCriterion("person_country not between", value1, value2, "personCountry");
            return (Criteria) this;
        }

        public Criteria andPersonCountryNameIsNull() {
            addCriterion("person_country_name is null");
            return (Criteria) this;
        }

        public Criteria andPersonCountryNameIsNotNull() {
            addCriterion("person_country_name is not null");
            return (Criteria) this;
        }

        public Criteria andPersonCountryNameEqualTo(String value) {
            addCriterion("person_country_name =", value, "personCountryName");
            return (Criteria) this;
        }

        public Criteria andPersonCountryNameNotEqualTo(String value) {
            addCriterion("person_country_name <>", value, "personCountryName");
            return (Criteria) this;
        }

        public Criteria andPersonCountryNameGreaterThan(String value) {
            addCriterion("person_country_name >", value, "personCountryName");
            return (Criteria) this;
        }

        public Criteria andPersonCountryNameGreaterThanOrEqualTo(String value) {
            addCriterion("person_country_name >=", value, "personCountryName");
            return (Criteria) this;
        }

        public Criteria andPersonCountryNameLessThan(String value) {
            addCriterion("person_country_name <", value, "personCountryName");
            return (Criteria) this;
        }

        public Criteria andPersonCountryNameLessThanOrEqualTo(String value) {
            addCriterion("person_country_name <=", value, "personCountryName");
            return (Criteria) this;
        }

        public Criteria andPersonCountryNameLike(String value) {
            addCriterion("person_country_name like", value, "personCountryName");
            return (Criteria) this;
        }

        public Criteria andPersonCountryNameNotLike(String value) {
            addCriterion("person_country_name not like", value, "personCountryName");
            return (Criteria) this;
        }

        public Criteria andPersonCountryNameIn(List<String> values) {
            addCriterion("person_country_name in", values, "personCountryName");
            return (Criteria) this;
        }

        public Criteria andPersonCountryNameNotIn(List<String> values) {
            addCriterion("person_country_name not in", values, "personCountryName");
            return (Criteria) this;
        }

        public Criteria andPersonCountryNameBetween(String value1, String value2) {
            addCriterion("person_country_name between", value1, value2, "personCountryName");
            return (Criteria) this;
        }

        public Criteria andPersonCountryNameNotBetween(String value1, String value2) {
            addCriterion("person_country_name not between", value1, value2, "personCountryName");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityIsNull() {
            addCriterion("person_nationality is null");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityIsNotNull() {
            addCriterion("person_nationality is not null");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityEqualTo(Short value) {
            addCriterion("person_nationality =", value, "personNationality");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityNotEqualTo(Short value) {
            addCriterion("person_nationality <>", value, "personNationality");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityGreaterThan(Short value) {
            addCriterion("person_nationality >", value, "personNationality");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityGreaterThanOrEqualTo(Short value) {
            addCriterion("person_nationality >=", value, "personNationality");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityLessThan(Short value) {
            addCriterion("person_nationality <", value, "personNationality");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityLessThanOrEqualTo(Short value) {
            addCriterion("person_nationality <=", value, "personNationality");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityIn(List<Short> values) {
            addCriterion("person_nationality in", values, "personNationality");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityNotIn(List<Short> values) {
            addCriterion("person_nationality not in", values, "personNationality");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityBetween(Short value1, Short value2) {
            addCriterion("person_nationality between", value1, value2, "personNationality");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityNotBetween(Short value1, Short value2) {
            addCriterion("person_nationality not between", value1, value2, "personNationality");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityNameIsNull() {
            addCriterion("person_nationality_name is null");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityNameIsNotNull() {
            addCriterion("person_nationality_name is not null");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityNameEqualTo(String value) {
            addCriterion("person_nationality_name =", value, "personNationalityName");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityNameNotEqualTo(String value) {
            addCriterion("person_nationality_name <>", value, "personNationalityName");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityNameGreaterThan(String value) {
            addCriterion("person_nationality_name >", value, "personNationalityName");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityNameGreaterThanOrEqualTo(String value) {
            addCriterion("person_nationality_name >=", value, "personNationalityName");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityNameLessThan(String value) {
            addCriterion("person_nationality_name <", value, "personNationalityName");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityNameLessThanOrEqualTo(String value) {
            addCriterion("person_nationality_name <=", value, "personNationalityName");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityNameLike(String value) {
            addCriterion("person_nationality_name like", value, "personNationalityName");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityNameNotLike(String value) {
            addCriterion("person_nationality_name not like", value, "personNationalityName");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityNameIn(List<String> values) {
            addCriterion("person_nationality_name in", values, "personNationalityName");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityNameNotIn(List<String> values) {
            addCriterion("person_nationality_name not in", values, "personNationalityName");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityNameBetween(String value1, String value2) {
            addCriterion("person_nationality_name between", value1, value2, "personNationalityName");
            return (Criteria) this;
        }

        public Criteria andPersonNationalityNameNotBetween(String value1, String value2) {
            addCriterion("person_nationality_name not between", value1, value2, "personNationalityName");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeIsNull() {
            addCriterion("person_paper_type is null");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeIsNotNull() {
            addCriterion("person_paper_type is not null");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeEqualTo(Short value) {
            addCriterion("person_paper_type =", value, "personPaperType");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeNotEqualTo(Short value) {
            addCriterion("person_paper_type <>", value, "personPaperType");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeGreaterThan(Short value) {
            addCriterion("person_paper_type >", value, "personPaperType");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("person_paper_type >=", value, "personPaperType");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeLessThan(Short value) {
            addCriterion("person_paper_type <", value, "personPaperType");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeLessThanOrEqualTo(Short value) {
            addCriterion("person_paper_type <=", value, "personPaperType");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeIn(List<Short> values) {
            addCriterion("person_paper_type in", values, "personPaperType");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeNotIn(List<Short> values) {
            addCriterion("person_paper_type not in", values, "personPaperType");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeBetween(Short value1, Short value2) {
            addCriterion("person_paper_type between", value1, value2, "personPaperType");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeNotBetween(Short value1, Short value2) {
            addCriterion("person_paper_type not between", value1, value2, "personPaperType");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeNameIsNull() {
            addCriterion("person_paper_type_name is null");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeNameIsNotNull() {
            addCriterion("person_paper_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeNameEqualTo(String value) {
            addCriterion("person_paper_type_name =", value, "personPaperTypeName");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeNameNotEqualTo(String value) {
            addCriterion("person_paper_type_name <>", value, "personPaperTypeName");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeNameGreaterThan(String value) {
            addCriterion("person_paper_type_name >", value, "personPaperTypeName");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("person_paper_type_name >=", value, "personPaperTypeName");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeNameLessThan(String value) {
            addCriterion("person_paper_type_name <", value, "personPaperTypeName");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeNameLessThanOrEqualTo(String value) {
            addCriterion("person_paper_type_name <=", value, "personPaperTypeName");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeNameLike(String value) {
            addCriterion("person_paper_type_name like", value, "personPaperTypeName");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeNameNotLike(String value) {
            addCriterion("person_paper_type_name not like", value, "personPaperTypeName");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeNameIn(List<String> values) {
            addCriterion("person_paper_type_name in", values, "personPaperTypeName");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeNameNotIn(List<String> values) {
            addCriterion("person_paper_type_name not in", values, "personPaperTypeName");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeNameBetween(String value1, String value2) {
            addCriterion("person_paper_type_name between", value1, value2, "personPaperTypeName");
            return (Criteria) this;
        }

        public Criteria andPersonPaperTypeNameNotBetween(String value1, String value2) {
            addCriterion("person_paper_type_name not between", value1, value2, "personPaperTypeName");
            return (Criteria) this;
        }

        public Criteria andPersonPaperNumberIsNull() {
            addCriterion("person_paper_number is null");
            return (Criteria) this;
        }

        public Criteria andPersonPaperNumberIsNotNull() {
            addCriterion("person_paper_number is not null");
            return (Criteria) this;
        }

        public Criteria andPersonPaperNumberEqualTo(String value) {
            addCriterion("person_paper_number =", value, "personPaperNumber");
            return (Criteria) this;
        }

        public Criteria andPersonPaperNumberNotEqualTo(String value) {
            addCriterion("person_paper_number <>", value, "personPaperNumber");
            return (Criteria) this;
        }

        public Criteria andPersonPaperNumberGreaterThan(String value) {
            addCriterion("person_paper_number >", value, "personPaperNumber");
            return (Criteria) this;
        }

        public Criteria andPersonPaperNumberGreaterThanOrEqualTo(String value) {
            addCriterion("person_paper_number >=", value, "personPaperNumber");
            return (Criteria) this;
        }

        public Criteria andPersonPaperNumberLessThan(String value) {
            addCriterion("person_paper_number <", value, "personPaperNumber");
            return (Criteria) this;
        }

        public Criteria andPersonPaperNumberLessThanOrEqualTo(String value) {
            addCriterion("person_paper_number <=", value, "personPaperNumber");
            return (Criteria) this;
        }

        public Criteria andPersonPaperNumberLike(String value) {
            addCriterion("person_paper_number like", value, "personPaperNumber");
            return (Criteria) this;
        }

        public Criteria andPersonPaperNumberNotLike(String value) {
            addCriterion("person_paper_number not like", value, "personPaperNumber");
            return (Criteria) this;
        }

        public Criteria andPersonPaperNumberIn(List<String> values) {
            addCriterion("person_paper_number in", values, "personPaperNumber");
            return (Criteria) this;
        }

        public Criteria andPersonPaperNumberNotIn(List<String> values) {
            addCriterion("person_paper_number not in", values, "personPaperNumber");
            return (Criteria) this;
        }

        public Criteria andPersonPaperNumberBetween(String value1, String value2) {
            addCriterion("person_paper_number between", value1, value2, "personPaperNumber");
            return (Criteria) this;
        }

        public Criteria andPersonPaperNumberNotBetween(String value1, String value2) {
            addCriterion("person_paper_number not between", value1, value2, "personPaperNumber");
            return (Criteria) this;
        }

        public Criteria andPersonAddressIsNull() {
            addCriterion("person_address is null");
            return (Criteria) this;
        }

        public Criteria andPersonAddressIsNotNull() {
            addCriterion("person_address is not null");
            return (Criteria) this;
        }

        public Criteria andPersonAddressEqualTo(String value) {
            addCriterion("person_address =", value, "personAddress");
            return (Criteria) this;
        }

        public Criteria andPersonAddressNotEqualTo(String value) {
            addCriterion("person_address <>", value, "personAddress");
            return (Criteria) this;
        }

        public Criteria andPersonAddressGreaterThan(String value) {
            addCriterion("person_address >", value, "personAddress");
            return (Criteria) this;
        }

        public Criteria andPersonAddressGreaterThanOrEqualTo(String value) {
            addCriterion("person_address >=", value, "personAddress");
            return (Criteria) this;
        }

        public Criteria andPersonAddressLessThan(String value) {
            addCriterion("person_address <", value, "personAddress");
            return (Criteria) this;
        }

        public Criteria andPersonAddressLessThanOrEqualTo(String value) {
            addCriterion("person_address <=", value, "personAddress");
            return (Criteria) this;
        }

        public Criteria andPersonAddressLike(String value) {
            addCriterion("person_address like", value, "personAddress");
            return (Criteria) this;
        }

        public Criteria andPersonAddressNotLike(String value) {
            addCriterion("person_address not like", value, "personAddress");
            return (Criteria) this;
        }

        public Criteria andPersonAddressIn(List<String> values) {
            addCriterion("person_address in", values, "personAddress");
            return (Criteria) this;
        }

        public Criteria andPersonAddressNotIn(List<String> values) {
            addCriterion("person_address not in", values, "personAddress");
            return (Criteria) this;
        }

        public Criteria andPersonAddressBetween(String value1, String value2) {
            addCriterion("person_address between", value1, value2, "personAddress");
            return (Criteria) this;
        }

        public Criteria andPersonAddressNotBetween(String value1, String value2) {
            addCriterion("person_address not between", value1, value2, "personAddress");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageIsNull() {
            addCriterion("person_marriage is null");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageIsNotNull() {
            addCriterion("person_marriage is not null");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageEqualTo(Short value) {
            addCriterion("person_marriage =", value, "personMarriage");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageNotEqualTo(Short value) {
            addCriterion("person_marriage <>", value, "personMarriage");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageGreaterThan(Short value) {
            addCriterion("person_marriage >", value, "personMarriage");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageGreaterThanOrEqualTo(Short value) {
            addCriterion("person_marriage >=", value, "personMarriage");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageLessThan(Short value) {
            addCriterion("person_marriage <", value, "personMarriage");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageLessThanOrEqualTo(Short value) {
            addCriterion("person_marriage <=", value, "personMarriage");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageIn(List<Short> values) {
            addCriterion("person_marriage in", values, "personMarriage");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageNotIn(List<Short> values) {
            addCriterion("person_marriage not in", values, "personMarriage");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageBetween(Short value1, Short value2) {
            addCriterion("person_marriage between", value1, value2, "personMarriage");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageNotBetween(Short value1, Short value2) {
            addCriterion("person_marriage not between", value1, value2, "personMarriage");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageNameIsNull() {
            addCriterion("person_marriage_name is null");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageNameIsNotNull() {
            addCriterion("person_marriage_name is not null");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageNameEqualTo(String value) {
            addCriterion("person_marriage_name =", value, "personMarriageName");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageNameNotEqualTo(String value) {
            addCriterion("person_marriage_name <>", value, "personMarriageName");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageNameGreaterThan(String value) {
            addCriterion("person_marriage_name >", value, "personMarriageName");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageNameGreaterThanOrEqualTo(String value) {
            addCriterion("person_marriage_name >=", value, "personMarriageName");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageNameLessThan(String value) {
            addCriterion("person_marriage_name <", value, "personMarriageName");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageNameLessThanOrEqualTo(String value) {
            addCriterion("person_marriage_name <=", value, "personMarriageName");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageNameLike(String value) {
            addCriterion("person_marriage_name like", value, "personMarriageName");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageNameNotLike(String value) {
            addCriterion("person_marriage_name not like", value, "personMarriageName");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageNameIn(List<String> values) {
            addCriterion("person_marriage_name in", values, "personMarriageName");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageNameNotIn(List<String> values) {
            addCriterion("person_marriage_name not in", values, "personMarriageName");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageNameBetween(String value1, String value2) {
            addCriterion("person_marriage_name between", value1, value2, "personMarriageName");
            return (Criteria) this;
        }

        public Criteria andPersonMarriageNameNotBetween(String value1, String value2) {
            addCriterion("person_marriage_name not between", value1, value2, "personMarriageName");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalIsNull() {
            addCriterion("person_political is null");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalIsNotNull() {
            addCriterion("person_political is not null");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalEqualTo(Short value) {
            addCriterion("person_political =", value, "personPolitical");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalNotEqualTo(Short value) {
            addCriterion("person_political <>", value, "personPolitical");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalGreaterThan(Short value) {
            addCriterion("person_political >", value, "personPolitical");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalGreaterThanOrEqualTo(Short value) {
            addCriterion("person_political >=", value, "personPolitical");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalLessThan(Short value) {
            addCriterion("person_political <", value, "personPolitical");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalLessThanOrEqualTo(Short value) {
            addCriterion("person_political <=", value, "personPolitical");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalIn(List<Short> values) {
            addCriterion("person_political in", values, "personPolitical");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalNotIn(List<Short> values) {
            addCriterion("person_political not in", values, "personPolitical");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalBetween(Short value1, Short value2) {
            addCriterion("person_political between", value1, value2, "personPolitical");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalNotBetween(Short value1, Short value2) {
            addCriterion("person_political not between", value1, value2, "personPolitical");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalNameIsNull() {
            addCriterion("person_political_name is null");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalNameIsNotNull() {
            addCriterion("person_political_name is not null");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalNameEqualTo(String value) {
            addCriterion("person_political_name =", value, "personPoliticalName");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalNameNotEqualTo(String value) {
            addCriterion("person_political_name <>", value, "personPoliticalName");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalNameGreaterThan(String value) {
            addCriterion("person_political_name >", value, "personPoliticalName");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalNameGreaterThanOrEqualTo(String value) {
            addCriterion("person_political_name >=", value, "personPoliticalName");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalNameLessThan(String value) {
            addCriterion("person_political_name <", value, "personPoliticalName");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalNameLessThanOrEqualTo(String value) {
            addCriterion("person_political_name <=", value, "personPoliticalName");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalNameLike(String value) {
            addCriterion("person_political_name like", value, "personPoliticalName");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalNameNotLike(String value) {
            addCriterion("person_political_name not like", value, "personPoliticalName");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalNameIn(List<String> values) {
            addCriterion("person_political_name in", values, "personPoliticalName");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalNameNotIn(List<String> values) {
            addCriterion("person_political_name not in", values, "personPoliticalName");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalNameBetween(String value1, String value2) {
            addCriterion("person_political_name between", value1, value2, "personPoliticalName");
            return (Criteria) this;
        }

        public Criteria andPersonPoliticalNameNotBetween(String value1, String value2) {
            addCriterion("person_political_name not between", value1, value2, "personPoliticalName");
            return (Criteria) this;
        }

        public Criteria andPersonEducationIsNull() {
            addCriterion("person_education is null");
            return (Criteria) this;
        }

        public Criteria andPersonEducationIsNotNull() {
            addCriterion("person_education is not null");
            return (Criteria) this;
        }

        public Criteria andPersonEducationEqualTo(Short value) {
            addCriterion("person_education =", value, "personEducation");
            return (Criteria) this;
        }

        public Criteria andPersonEducationNotEqualTo(Short value) {
            addCriterion("person_education <>", value, "personEducation");
            return (Criteria) this;
        }

        public Criteria andPersonEducationGreaterThan(Short value) {
            addCriterion("person_education >", value, "personEducation");
            return (Criteria) this;
        }

        public Criteria andPersonEducationGreaterThanOrEqualTo(Short value) {
            addCriterion("person_education >=", value, "personEducation");
            return (Criteria) this;
        }

        public Criteria andPersonEducationLessThan(Short value) {
            addCriterion("person_education <", value, "personEducation");
            return (Criteria) this;
        }

        public Criteria andPersonEducationLessThanOrEqualTo(Short value) {
            addCriterion("person_education <=", value, "personEducation");
            return (Criteria) this;
        }

        public Criteria andPersonEducationIn(List<Short> values) {
            addCriterion("person_education in", values, "personEducation");
            return (Criteria) this;
        }

        public Criteria andPersonEducationNotIn(List<Short> values) {
            addCriterion("person_education not in", values, "personEducation");
            return (Criteria) this;
        }

        public Criteria andPersonEducationBetween(Short value1, Short value2) {
            addCriterion("person_education between", value1, value2, "personEducation");
            return (Criteria) this;
        }

        public Criteria andPersonEducationNotBetween(Short value1, Short value2) {
            addCriterion("person_education not between", value1, value2, "personEducation");
            return (Criteria) this;
        }

        public Criteria andPersonEducationNameIsNull() {
            addCriterion("person_education_name is null");
            return (Criteria) this;
        }

        public Criteria andPersonEducationNameIsNotNull() {
            addCriterion("person_education_name is not null");
            return (Criteria) this;
        }

        public Criteria andPersonEducationNameEqualTo(String value) {
            addCriterion("person_education_name =", value, "personEducationName");
            return (Criteria) this;
        }

        public Criteria andPersonEducationNameNotEqualTo(String value) {
            addCriterion("person_education_name <>", value, "personEducationName");
            return (Criteria) this;
        }

        public Criteria andPersonEducationNameGreaterThan(String value) {
            addCriterion("person_education_name >", value, "personEducationName");
            return (Criteria) this;
        }

        public Criteria andPersonEducationNameGreaterThanOrEqualTo(String value) {
            addCriterion("person_education_name >=", value, "personEducationName");
            return (Criteria) this;
        }

        public Criteria andPersonEducationNameLessThan(String value) {
            addCriterion("person_education_name <", value, "personEducationName");
            return (Criteria) this;
        }

        public Criteria andPersonEducationNameLessThanOrEqualTo(String value) {
            addCriterion("person_education_name <=", value, "personEducationName");
            return (Criteria) this;
        }

        public Criteria andPersonEducationNameLike(String value) {
            addCriterion("person_education_name like", value, "personEducationName");
            return (Criteria) this;
        }

        public Criteria andPersonEducationNameNotLike(String value) {
            addCriterion("person_education_name not like", value, "personEducationName");
            return (Criteria) this;
        }

        public Criteria andPersonEducationNameIn(List<String> values) {
            addCriterion("person_education_name in", values, "personEducationName");
            return (Criteria) this;
        }

        public Criteria andPersonEducationNameNotIn(List<String> values) {
            addCriterion("person_education_name not in", values, "personEducationName");
            return (Criteria) this;
        }

        public Criteria andPersonEducationNameBetween(String value1, String value2) {
            addCriterion("person_education_name between", value1, value2, "personEducationName");
            return (Criteria) this;
        }

        public Criteria andPersonEducationNameNotBetween(String value1, String value2) {
            addCriterion("person_education_name not between", value1, value2, "personEducationName");
            return (Criteria) this;
        }

        public Criteria andPersonOccupationIsNull() {
            addCriterion("person_occupation is null");
            return (Criteria) this;
        }

        public Criteria andPersonOccupationIsNotNull() {
            addCriterion("person_occupation is not null");
            return (Criteria) this;
        }

        public Criteria andPersonOccupationEqualTo(String value) {
            addCriterion("person_occupation =", value, "personOccupation");
            return (Criteria) this;
        }

        public Criteria andPersonOccupationNotEqualTo(String value) {
            addCriterion("person_occupation <>", value, "personOccupation");
            return (Criteria) this;
        }

        public Criteria andPersonOccupationGreaterThan(String value) {
            addCriterion("person_occupation >", value, "personOccupation");
            return (Criteria) this;
        }

        public Criteria andPersonOccupationGreaterThanOrEqualTo(String value) {
            addCriterion("person_occupation >=", value, "personOccupation");
            return (Criteria) this;
        }

        public Criteria andPersonOccupationLessThan(String value) {
            addCriterion("person_occupation <", value, "personOccupation");
            return (Criteria) this;
        }

        public Criteria andPersonOccupationLessThanOrEqualTo(String value) {
            addCriterion("person_occupation <=", value, "personOccupation");
            return (Criteria) this;
        }

        public Criteria andPersonOccupationLike(String value) {
            addCriterion("person_occupation like", value, "personOccupation");
            return (Criteria) this;
        }

        public Criteria andPersonOccupationNotLike(String value) {
            addCriterion("person_occupation not like", value, "personOccupation");
            return (Criteria) this;
        }

        public Criteria andPersonOccupationIn(List<String> values) {
            addCriterion("person_occupation in", values, "personOccupation");
            return (Criteria) this;
        }

        public Criteria andPersonOccupationNotIn(List<String> values) {
            addCriterion("person_occupation not in", values, "personOccupation");
            return (Criteria) this;
        }

        public Criteria andPersonOccupationBetween(String value1, String value2) {
            addCriterion("person_occupation between", value1, value2, "personOccupation");
            return (Criteria) this;
        }

        public Criteria andPersonOccupationNotBetween(String value1, String value2) {
            addCriterion("person_occupation not between", value1, value2, "personOccupation");
            return (Criteria) this;
        }

        public Criteria andPersonImagePathIsNull() {
            addCriterion("person_image_path is null");
            return (Criteria) this;
        }

        public Criteria andPersonImagePathIsNotNull() {
            addCriterion("person_image_path is not null");
            return (Criteria) this;
        }

        public Criteria andPersonImagePathEqualTo(String value) {
            addCriterion("person_image_path =", value, "personImagePath");
            return (Criteria) this;
        }

        public Criteria andPersonImagePathNotEqualTo(String value) {
            addCriterion("person_image_path <>", value, "personImagePath");
            return (Criteria) this;
        }

        public Criteria andPersonImagePathGreaterThan(String value) {
            addCriterion("person_image_path >", value, "personImagePath");
            return (Criteria) this;
        }

        public Criteria andPersonImagePathGreaterThanOrEqualTo(String value) {
            addCriterion("person_image_path >=", value, "personImagePath");
            return (Criteria) this;
        }

        public Criteria andPersonImagePathLessThan(String value) {
            addCriterion("person_image_path <", value, "personImagePath");
            return (Criteria) this;
        }

        public Criteria andPersonImagePathLessThanOrEqualTo(String value) {
            addCriterion("person_image_path <=", value, "personImagePath");
            return (Criteria) this;
        }

        public Criteria andPersonImagePathLike(String value) {
            addCriterion("person_image_path like", value, "personImagePath");
            return (Criteria) this;
        }

        public Criteria andPersonImagePathNotLike(String value) {
            addCriterion("person_image_path not like", value, "personImagePath");
            return (Criteria) this;
        }

        public Criteria andPersonImagePathIn(List<String> values) {
            addCriterion("person_image_path in", values, "personImagePath");
            return (Criteria) this;
        }

        public Criteria andPersonImagePathNotIn(List<String> values) {
            addCriterion("person_image_path not in", values, "personImagePath");
            return (Criteria) this;
        }

        public Criteria andPersonImagePathBetween(String value1, String value2) {
            addCriterion("person_image_path between", value1, value2, "personImagePath");
            return (Criteria) this;
        }

        public Criteria andPersonImagePathNotBetween(String value1, String value2) {
            addCriterion("person_image_path not between", value1, value2, "personImagePath");
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

        public Criteria andTypeEqualTo(Byte value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Byte value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Byte value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Byte value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Byte value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Byte> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Byte> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Byte value1, Byte value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andCapSexIsNull() {
            addCriterion("cap_sex is null");
            return (Criteria) this;
        }

        public Criteria andCapSexIsNotNull() {
            addCriterion("cap_sex is not null");
            return (Criteria) this;
        }

        public Criteria andCapSexEqualTo(String value) {
            addCriterion("cap_sex =", value, "capSex");
            return (Criteria) this;
        }

        public Criteria andCapSexNotEqualTo(String value) {
            addCriterion("cap_sex <>", value, "capSex");
            return (Criteria) this;
        }

        public Criteria andCapSexGreaterThan(String value) {
            addCriterion("cap_sex >", value, "capSex");
            return (Criteria) this;
        }

        public Criteria andCapSexGreaterThanOrEqualTo(String value) {
            addCriterion("cap_sex >=", value, "capSex");
            return (Criteria) this;
        }

        public Criteria andCapSexLessThan(String value) {
            addCriterion("cap_sex <", value, "capSex");
            return (Criteria) this;
        }

        public Criteria andCapSexLessThanOrEqualTo(String value) {
            addCriterion("cap_sex <=", value, "capSex");
            return (Criteria) this;
        }

        public Criteria andCapSexLike(String value) {
            addCriterion("cap_sex like", value, "capSex");
            return (Criteria) this;
        }

        public Criteria andCapSexNotLike(String value) {
            addCriterion("cap_sex not like", value, "capSex");
            return (Criteria) this;
        }

        public Criteria andCapSexIn(List<String> values) {
            addCriterion("cap_sex in", values, "capSex");
            return (Criteria) this;
        }

        public Criteria andCapSexNotIn(List<String> values) {
            addCriterion("cap_sex not in", values, "capSex");
            return (Criteria) this;
        }

        public Criteria andCapSexBetween(String value1, String value2) {
            addCriterion("cap_sex between", value1, value2, "capSex");
            return (Criteria) this;
        }

        public Criteria andCapSexNotBetween(String value1, String value2) {
            addCriterion("cap_sex not between", value1, value2, "capSex");
            return (Criteria) this;
        }

        public Criteria andCapAgeIsNull() {
            addCriterion("cap_age is null");
            return (Criteria) this;
        }

        public Criteria andCapAgeIsNotNull() {
            addCriterion("cap_age is not null");
            return (Criteria) this;
        }

        public Criteria andCapAgeEqualTo(String value) {
            addCriterion("cap_age =", value, "capAge");
            return (Criteria) this;
        }

        public Criteria andCapAgeNotEqualTo(String value) {
            addCriterion("cap_age <>", value, "capAge");
            return (Criteria) this;
        }

        public Criteria andCapAgeGreaterThan(String value) {
            addCriterion("cap_age >", value, "capAge");
            return (Criteria) this;
        }

        public Criteria andCapAgeGreaterThanOrEqualTo(String value) {
            addCriterion("cap_age >=", value, "capAge");
            return (Criteria) this;
        }

        public Criteria andCapAgeLessThan(String value) {
            addCriterion("cap_age <", value, "capAge");
            return (Criteria) this;
        }

        public Criteria andCapAgeLessThanOrEqualTo(String value) {
            addCriterion("cap_age <=", value, "capAge");
            return (Criteria) this;
        }

        public Criteria andCapAgeLike(String value) {
            addCriterion("cap_age like", value, "capAge");
            return (Criteria) this;
        }

        public Criteria andCapAgeNotLike(String value) {
            addCriterion("cap_age not like", value, "capAge");
            return (Criteria) this;
        }

        public Criteria andCapAgeIn(List<String> values) {
            addCriterion("cap_age in", values, "capAge");
            return (Criteria) this;
        }

        public Criteria andCapAgeNotIn(List<String> values) {
            addCriterion("cap_age not in", values, "capAge");
            return (Criteria) this;
        }

        public Criteria andCapAgeBetween(String value1, String value2) {
            addCriterion("cap_age between", value1, value2, "capAge");
            return (Criteria) this;
        }

        public Criteria andCapAgeNotBetween(String value1, String value2) {
            addCriterion("cap_age not between", value1, value2, "capAge");
            return (Criteria) this;
        }

        public Criteria andCapCountryIsNull() {
            addCriterion("cap_country is null");
            return (Criteria) this;
        }

        public Criteria andCapCountryIsNotNull() {
            addCriterion("cap_country is not null");
            return (Criteria) this;
        }

        public Criteria andCapCountryEqualTo(String value) {
            addCriterion("cap_country =", value, "capCountry");
            return (Criteria) this;
        }

        public Criteria andCapCountryNotEqualTo(String value) {
            addCriterion("cap_country <>", value, "capCountry");
            return (Criteria) this;
        }

        public Criteria andCapCountryGreaterThan(String value) {
            addCriterion("cap_country >", value, "capCountry");
            return (Criteria) this;
        }

        public Criteria andCapCountryGreaterThanOrEqualTo(String value) {
            addCriterion("cap_country >=", value, "capCountry");
            return (Criteria) this;
        }

        public Criteria andCapCountryLessThan(String value) {
            addCriterion("cap_country <", value, "capCountry");
            return (Criteria) this;
        }

        public Criteria andCapCountryLessThanOrEqualTo(String value) {
            addCriterion("cap_country <=", value, "capCountry");
            return (Criteria) this;
        }

        public Criteria andCapCountryLike(String value) {
            addCriterion("cap_country like", value, "capCountry");
            return (Criteria) this;
        }

        public Criteria andCapCountryNotLike(String value) {
            addCriterion("cap_country not like", value, "capCountry");
            return (Criteria) this;
        }

        public Criteria andCapCountryIn(List<String> values) {
            addCriterion("cap_country in", values, "capCountry");
            return (Criteria) this;
        }

        public Criteria andCapCountryNotIn(List<String> values) {
            addCriterion("cap_country not in", values, "capCountry");
            return (Criteria) this;
        }

        public Criteria andCapCountryBetween(String value1, String value2) {
            addCriterion("cap_country between", value1, value2, "capCountry");
            return (Criteria) this;
        }

        public Criteria andCapCountryNotBetween(String value1, String value2) {
            addCriterion("cap_country not between", value1, value2, "capCountry");
            return (Criteria) this;
        }

        public Criteria andCapBmaskIsNull() {
            addCriterion("cap_bmask is null");
            return (Criteria) this;
        }

        public Criteria andCapBmaskIsNotNull() {
            addCriterion("cap_bmask is not null");
            return (Criteria) this;
        }

        public Criteria andCapBmaskEqualTo(Byte value) {
            addCriterion("cap_bmask =", value, "capBmask");
            return (Criteria) this;
        }

        public Criteria andCapBmaskNotEqualTo(Byte value) {
            addCriterion("cap_bmask <>", value, "capBmask");
            return (Criteria) this;
        }

        public Criteria andCapBmaskGreaterThan(Byte value) {
            addCriterion("cap_bmask >", value, "capBmask");
            return (Criteria) this;
        }

        public Criteria andCapBmaskGreaterThanOrEqualTo(Byte value) {
            addCriterion("cap_bmask >=", value, "capBmask");
            return (Criteria) this;
        }

        public Criteria andCapBmaskLessThan(Byte value) {
            addCriterion("cap_bmask <", value, "capBmask");
            return (Criteria) this;
        }

        public Criteria andCapBmaskLessThanOrEqualTo(Byte value) {
            addCriterion("cap_bmask <=", value, "capBmask");
            return (Criteria) this;
        }

        public Criteria andCapBmaskIn(List<Byte> values) {
            addCriterion("cap_bmask in", values, "capBmask");
            return (Criteria) this;
        }

        public Criteria andCapBmaskNotIn(List<Byte> values) {
            addCriterion("cap_bmask not in", values, "capBmask");
            return (Criteria) this;
        }

        public Criteria andCapBmaskBetween(Byte value1, Byte value2) {
            addCriterion("cap_bmask between", value1, value2, "capBmask");
            return (Criteria) this;
        }

        public Criteria andCapBmaskNotBetween(Byte value1, Byte value2) {
            addCriterion("cap_bmask not between", value1, value2, "capBmask");
            return (Criteria) this;
        }

        public Criteria andCapEyeglassIsNull() {
            addCriterion("cap_eyeglass is null");
            return (Criteria) this;
        }

        public Criteria andCapEyeglassIsNotNull() {
            addCriterion("cap_eyeglass is not null");
            return (Criteria) this;
        }

        public Criteria andCapEyeglassEqualTo(Byte value) {
            addCriterion("cap_eyeglass =", value, "capEyeglass");
            return (Criteria) this;
        }

        public Criteria andCapEyeglassNotEqualTo(Byte value) {
            addCriterion("cap_eyeglass <>", value, "capEyeglass");
            return (Criteria) this;
        }

        public Criteria andCapEyeglassGreaterThan(Byte value) {
            addCriterion("cap_eyeglass >", value, "capEyeglass");
            return (Criteria) this;
        }

        public Criteria andCapEyeglassGreaterThanOrEqualTo(Byte value) {
            addCriterion("cap_eyeglass >=", value, "capEyeglass");
            return (Criteria) this;
        }

        public Criteria andCapEyeglassLessThan(Byte value) {
            addCriterion("cap_eyeglass <", value, "capEyeglass");
            return (Criteria) this;
        }

        public Criteria andCapEyeglassLessThanOrEqualTo(Byte value) {
            addCriterion("cap_eyeglass <=", value, "capEyeglass");
            return (Criteria) this;
        }

        public Criteria andCapEyeglassIn(List<Byte> values) {
            addCriterion("cap_eyeglass in", values, "capEyeglass");
            return (Criteria) this;
        }

        public Criteria andCapEyeglassNotIn(List<Byte> values) {
            addCriterion("cap_eyeglass not in", values, "capEyeglass");
            return (Criteria) this;
        }

        public Criteria andCapEyeglassBetween(Byte value1, Byte value2) {
            addCriterion("cap_eyeglass between", value1, value2, "capEyeglass");
            return (Criteria) this;
        }

        public Criteria andCapEyeglassNotBetween(Byte value1, Byte value2) {
            addCriterion("cap_eyeglass not between", value1, value2, "capEyeglass");
            return (Criteria) this;
        }

        public Criteria andCapKnapsackIsNull() {
            addCriterion("cap_knapsack is null");
            return (Criteria) this;
        }

        public Criteria andCapKnapsackIsNotNull() {
            addCriterion("cap_knapsack is not null");
            return (Criteria) this;
        }

        public Criteria andCapKnapsackEqualTo(Byte value) {
            addCriterion("cap_knapsack =", value, "capKnapsack");
            return (Criteria) this;
        }

        public Criteria andCapKnapsackNotEqualTo(Byte value) {
            addCriterion("cap_knapsack <>", value, "capKnapsack");
            return (Criteria) this;
        }

        public Criteria andCapKnapsackGreaterThan(Byte value) {
            addCriterion("cap_knapsack >", value, "capKnapsack");
            return (Criteria) this;
        }

        public Criteria andCapKnapsackGreaterThanOrEqualTo(Byte value) {
            addCriterion("cap_knapsack >=", value, "capKnapsack");
            return (Criteria) this;
        }

        public Criteria andCapKnapsackLessThan(Byte value) {
            addCriterion("cap_knapsack <", value, "capKnapsack");
            return (Criteria) this;
        }

        public Criteria andCapKnapsackLessThanOrEqualTo(Byte value) {
            addCriterion("cap_knapsack <=", value, "capKnapsack");
            return (Criteria) this;
        }

        public Criteria andCapKnapsackIn(List<Byte> values) {
            addCriterion("cap_knapsack in", values, "capKnapsack");
            return (Criteria) this;
        }

        public Criteria andCapKnapsackNotIn(List<Byte> values) {
            addCriterion("cap_knapsack not in", values, "capKnapsack");
            return (Criteria) this;
        }

        public Criteria andCapKnapsackBetween(Byte value1, Byte value2) {
            addCriterion("cap_knapsack between", value1, value2, "capKnapsack");
            return (Criteria) this;
        }

        public Criteria andCapKnapsackNotBetween(Byte value1, Byte value2) {
            addCriterion("cap_knapsack not between", value1, value2, "capKnapsack");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeIsNull() {
            addCriterion("cap_coat_type is null");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeIsNotNull() {
            addCriterion("cap_coat_type is not null");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeEqualTo(Short value) {
            addCriterion("cap_coat_type =", value, "capCoatType");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeNotEqualTo(Short value) {
            addCriterion("cap_coat_type <>", value, "capCoatType");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeGreaterThan(Short value) {
            addCriterion("cap_coat_type >", value, "capCoatType");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("cap_coat_type >=", value, "capCoatType");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeLessThan(Short value) {
            addCriterion("cap_coat_type <", value, "capCoatType");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeLessThanOrEqualTo(Short value) {
            addCriterion("cap_coat_type <=", value, "capCoatType");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeIn(List<Short> values) {
            addCriterion("cap_coat_type in", values, "capCoatType");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeNotIn(List<Short> values) {
            addCriterion("cap_coat_type not in", values, "capCoatType");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeBetween(Short value1, Short value2) {
            addCriterion("cap_coat_type between", value1, value2, "capCoatType");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeNotBetween(Short value1, Short value2) {
            addCriterion("cap_coat_type not between", value1, value2, "capCoatType");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeNameIsNull() {
            addCriterion("cap_coat_type_name is null");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeNameIsNotNull() {
            addCriterion("cap_coat_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeNameEqualTo(String value) {
            addCriterion("cap_coat_type_name =", value, "capCoatTypeName");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeNameNotEqualTo(String value) {
            addCriterion("cap_coat_type_name <>", value, "capCoatTypeName");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeNameGreaterThan(String value) {
            addCriterion("cap_coat_type_name >", value, "capCoatTypeName");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("cap_coat_type_name >=", value, "capCoatTypeName");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeNameLessThan(String value) {
            addCriterion("cap_coat_type_name <", value, "capCoatTypeName");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeNameLessThanOrEqualTo(String value) {
            addCriterion("cap_coat_type_name <=", value, "capCoatTypeName");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeNameLike(String value) {
            addCriterion("cap_coat_type_name like", value, "capCoatTypeName");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeNameNotLike(String value) {
            addCriterion("cap_coat_type_name not like", value, "capCoatTypeName");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeNameIn(List<String> values) {
            addCriterion("cap_coat_type_name in", values, "capCoatTypeName");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeNameNotIn(List<String> values) {
            addCriterion("cap_coat_type_name not in", values, "capCoatTypeName");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeNameBetween(String value1, String value2) {
            addCriterion("cap_coat_type_name between", value1, value2, "capCoatTypeName");
            return (Criteria) this;
        }

        public Criteria andCapCoatTypeNameNotBetween(String value1, String value2) {
            addCriterion("cap_coat_type_name not between", value1, value2, "capCoatTypeName");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorIsNull() {
            addCriterion("cap_coat_color is null");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorIsNotNull() {
            addCriterion("cap_coat_color is not null");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorEqualTo(Short value) {
            addCriterion("cap_coat_color =", value, "capCoatColor");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorNotEqualTo(Short value) {
            addCriterion("cap_coat_color <>", value, "capCoatColor");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorGreaterThan(Short value) {
            addCriterion("cap_coat_color >", value, "capCoatColor");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorGreaterThanOrEqualTo(Short value) {
            addCriterion("cap_coat_color >=", value, "capCoatColor");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorLessThan(Short value) {
            addCriterion("cap_coat_color <", value, "capCoatColor");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorLessThanOrEqualTo(Short value) {
            addCriterion("cap_coat_color <=", value, "capCoatColor");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorIn(List<Short> values) {
            addCriterion("cap_coat_color in", values, "capCoatColor");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorNotIn(List<Short> values) {
            addCriterion("cap_coat_color not in", values, "capCoatColor");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorBetween(Short value1, Short value2) {
            addCriterion("cap_coat_color between", value1, value2, "capCoatColor");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorNotBetween(Short value1, Short value2) {
            addCriterion("cap_coat_color not between", value1, value2, "capCoatColor");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorNameIsNull() {
            addCriterion("cap_coat_color_name is null");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorNameIsNotNull() {
            addCriterion("cap_coat_color_name is not null");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorNameEqualTo(String value) {
            addCriterion("cap_coat_color_name =", value, "capCoatColorName");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorNameNotEqualTo(String value) {
            addCriterion("cap_coat_color_name <>", value, "capCoatColorName");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorNameGreaterThan(String value) {
            addCriterion("cap_coat_color_name >", value, "capCoatColorName");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorNameGreaterThanOrEqualTo(String value) {
            addCriterion("cap_coat_color_name >=", value, "capCoatColorName");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorNameLessThan(String value) {
            addCriterion("cap_coat_color_name <", value, "capCoatColorName");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorNameLessThanOrEqualTo(String value) {
            addCriterion("cap_coat_color_name <=", value, "capCoatColorName");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorNameLike(String value) {
            addCriterion("cap_coat_color_name like", value, "capCoatColorName");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorNameNotLike(String value) {
            addCriterion("cap_coat_color_name not like", value, "capCoatColorName");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorNameIn(List<String> values) {
            addCriterion("cap_coat_color_name in", values, "capCoatColorName");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorNameNotIn(List<String> values) {
            addCriterion("cap_coat_color_name not in", values, "capCoatColorName");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorNameBetween(String value1, String value2) {
            addCriterion("cap_coat_color_name between", value1, value2, "capCoatColorName");
            return (Criteria) this;
        }

        public Criteria andCapCoatColorNameNotBetween(String value1, String value2) {
            addCriterion("cap_coat_color_name not between", value1, value2, "capCoatColorName");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeIsNull() {
            addCriterion("cap_trousers_type is null");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeIsNotNull() {
            addCriterion("cap_trousers_type is not null");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeEqualTo(Short value) {
            addCriterion("cap_trousers_type =", value, "capTrousersType");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeNotEqualTo(Short value) {
            addCriterion("cap_trousers_type <>", value, "capTrousersType");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeGreaterThan(Short value) {
            addCriterion("cap_trousers_type >", value, "capTrousersType");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("cap_trousers_type >=", value, "capTrousersType");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeLessThan(Short value) {
            addCriterion("cap_trousers_type <", value, "capTrousersType");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeLessThanOrEqualTo(Short value) {
            addCriterion("cap_trousers_type <=", value, "capTrousersType");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeIn(List<Short> values) {
            addCriterion("cap_trousers_type in", values, "capTrousersType");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeNotIn(List<Short> values) {
            addCriterion("cap_trousers_type not in", values, "capTrousersType");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeBetween(Short value1, Short value2) {
            addCriterion("cap_trousers_type between", value1, value2, "capTrousersType");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeNotBetween(Short value1, Short value2) {
            addCriterion("cap_trousers_type not between", value1, value2, "capTrousersType");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeNameIsNull() {
            addCriterion("cap_trousers_type_name is null");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeNameIsNotNull() {
            addCriterion("cap_trousers_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeNameEqualTo(String value) {
            addCriterion("cap_trousers_type_name =", value, "capTrousersTypeName");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeNameNotEqualTo(String value) {
            addCriterion("cap_trousers_type_name <>", value, "capTrousersTypeName");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeNameGreaterThan(String value) {
            addCriterion("cap_trousers_type_name >", value, "capTrousersTypeName");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("cap_trousers_type_name >=", value, "capTrousersTypeName");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeNameLessThan(String value) {
            addCriterion("cap_trousers_type_name <", value, "capTrousersTypeName");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeNameLessThanOrEqualTo(String value) {
            addCriterion("cap_trousers_type_name <=", value, "capTrousersTypeName");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeNameLike(String value) {
            addCriterion("cap_trousers_type_name like", value, "capTrousersTypeName");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeNameNotLike(String value) {
            addCriterion("cap_trousers_type_name not like", value, "capTrousersTypeName");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeNameIn(List<String> values) {
            addCriterion("cap_trousers_type_name in", values, "capTrousersTypeName");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeNameNotIn(List<String> values) {
            addCriterion("cap_trousers_type_name not in", values, "capTrousersTypeName");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeNameBetween(String value1, String value2) {
            addCriterion("cap_trousers_type_name between", value1, value2, "capTrousersTypeName");
            return (Criteria) this;
        }

        public Criteria andCapTrousersTypeNameNotBetween(String value1, String value2) {
            addCriterion("cap_trousers_type_name not between", value1, value2, "capTrousersTypeName");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorIsNull() {
            addCriterion("cap_trousers_color is null");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorIsNotNull() {
            addCriterion("cap_trousers_color is not null");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorEqualTo(Short value) {
            addCriterion("cap_trousers_color =", value, "capTrousersColor");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorNotEqualTo(Short value) {
            addCriterion("cap_trousers_color <>", value, "capTrousersColor");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorGreaterThan(Short value) {
            addCriterion("cap_trousers_color >", value, "capTrousersColor");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorGreaterThanOrEqualTo(Short value) {
            addCriterion("cap_trousers_color >=", value, "capTrousersColor");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorLessThan(Short value) {
            addCriterion("cap_trousers_color <", value, "capTrousersColor");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorLessThanOrEqualTo(Short value) {
            addCriterion("cap_trousers_color <=", value, "capTrousersColor");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorIn(List<Short> values) {
            addCriterion("cap_trousers_color in", values, "capTrousersColor");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorNotIn(List<Short> values) {
            addCriterion("cap_trousers_color not in", values, "capTrousersColor");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorBetween(Short value1, Short value2) {
            addCriterion("cap_trousers_color between", value1, value2, "capTrousersColor");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorNotBetween(Short value1, Short value2) {
            addCriterion("cap_trousers_color not between", value1, value2, "capTrousersColor");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorNameIsNull() {
            addCriterion("cap_trousers_color_name is null");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorNameIsNotNull() {
            addCriterion("cap_trousers_color_name is not null");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorNameEqualTo(String value) {
            addCriterion("cap_trousers_color_name =", value, "capTrousersColorName");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorNameNotEqualTo(String value) {
            addCriterion("cap_trousers_color_name <>", value, "capTrousersColorName");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorNameGreaterThan(String value) {
            addCriterion("cap_trousers_color_name >", value, "capTrousersColorName");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorNameGreaterThanOrEqualTo(String value) {
            addCriterion("cap_trousers_color_name >=", value, "capTrousersColorName");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorNameLessThan(String value) {
            addCriterion("cap_trousers_color_name <", value, "capTrousersColorName");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorNameLessThanOrEqualTo(String value) {
            addCriterion("cap_trousers_color_name <=", value, "capTrousersColorName");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorNameLike(String value) {
            addCriterion("cap_trousers_color_name like", value, "capTrousersColorName");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorNameNotLike(String value) {
            addCriterion("cap_trousers_color_name not like", value, "capTrousersColorName");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorNameIn(List<String> values) {
            addCriterion("cap_trousers_color_name in", values, "capTrousersColorName");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorNameNotIn(List<String> values) {
            addCriterion("cap_trousers_color_name not in", values, "capTrousersColorName");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorNameBetween(String value1, String value2) {
            addCriterion("cap_trousers_color_name between", value1, value2, "capTrousersColorName");
            return (Criteria) this;
        }

        public Criteria andCapTrousersColorNameNotBetween(String value1, String value2) {
            addCriterion("cap_trousers_color_name not between", value1, value2, "capTrousersColorName");
            return (Criteria) this;
        }

        public Criteria andCapHatIsNull() {
            addCriterion("cap_hat is null");
            return (Criteria) this;
        }

        public Criteria andCapHatIsNotNull() {
            addCriterion("cap_hat is not null");
            return (Criteria) this;
        }

        public Criteria andCapHatEqualTo(Byte value) {
            addCriterion("cap_hat =", value, "capHat");
            return (Criteria) this;
        }

        public Criteria andCapHatNotEqualTo(Byte value) {
            addCriterion("cap_hat <>", value, "capHat");
            return (Criteria) this;
        }

        public Criteria andCapHatGreaterThan(Byte value) {
            addCriterion("cap_hat >", value, "capHat");
            return (Criteria) this;
        }

        public Criteria andCapHatGreaterThanOrEqualTo(Byte value) {
            addCriterion("cap_hat >=", value, "capHat");
            return (Criteria) this;
        }

        public Criteria andCapHatLessThan(Byte value) {
            addCriterion("cap_hat <", value, "capHat");
            return (Criteria) this;
        }

        public Criteria andCapHatLessThanOrEqualTo(Byte value) {
            addCriterion("cap_hat <=", value, "capHat");
            return (Criteria) this;
        }

        public Criteria andCapHatIn(List<Byte> values) {
            addCriterion("cap_hat in", values, "capHat");
            return (Criteria) this;
        }

        public Criteria andCapHatNotIn(List<Byte> values) {
            addCriterion("cap_hat not in", values, "capHat");
            return (Criteria) this;
        }

        public Criteria andCapHatBetween(Byte value1, Byte value2) {
            addCriterion("cap_hat between", value1, value2, "capHat");
            return (Criteria) this;
        }

        public Criteria andCapHatNotBetween(Byte value1, Byte value2) {
            addCriterion("cap_hat not between", value1, value2, "capHat");
            return (Criteria) this;
        }

        public Criteria andCapHandbagIsNull() {
            addCriterion("cap_handbag is null");
            return (Criteria) this;
        }

        public Criteria andCapHandbagIsNotNull() {
            addCriterion("cap_handbag is not null");
            return (Criteria) this;
        }

        public Criteria andCapHandbagEqualTo(Byte value) {
            addCriterion("cap_handbag =", value, "capHandbag");
            return (Criteria) this;
        }

        public Criteria andCapHandbagNotEqualTo(Byte value) {
            addCriterion("cap_handbag <>", value, "capHandbag");
            return (Criteria) this;
        }

        public Criteria andCapHandbagGreaterThan(Byte value) {
            addCriterion("cap_handbag >", value, "capHandbag");
            return (Criteria) this;
        }

        public Criteria andCapHandbagGreaterThanOrEqualTo(Byte value) {
            addCriterion("cap_handbag >=", value, "capHandbag");
            return (Criteria) this;
        }

        public Criteria andCapHandbagLessThan(Byte value) {
            addCriterion("cap_handbag <", value, "capHandbag");
            return (Criteria) this;
        }

        public Criteria andCapHandbagLessThanOrEqualTo(Byte value) {
            addCriterion("cap_handbag <=", value, "capHandbag");
            return (Criteria) this;
        }

        public Criteria andCapHandbagIn(List<Byte> values) {
            addCriterion("cap_handbag in", values, "capHandbag");
            return (Criteria) this;
        }

        public Criteria andCapHandbagNotIn(List<Byte> values) {
            addCriterion("cap_handbag not in", values, "capHandbag");
            return (Criteria) this;
        }

        public Criteria andCapHandbagBetween(Byte value1, Byte value2) {
            addCriterion("cap_handbag between", value1, value2, "capHandbag");
            return (Criteria) this;
        }

        public Criteria andCapHandbagNotBetween(Byte value1, Byte value2) {
            addCriterion("cap_handbag not between", value1, value2, "capHandbag");
            return (Criteria) this;
        }

        public Criteria andCapTypeIsNull() {
            addCriterion("cap_type is null");
            return (Criteria) this;
        }

        public Criteria andCapTypeIsNotNull() {
            addCriterion("cap_type is not null");
            return (Criteria) this;
        }

        public Criteria andCapTypeEqualTo(Short value) {
            addCriterion("cap_type =", value, "capType");
            return (Criteria) this;
        }

        public Criteria andCapTypeNotEqualTo(Short value) {
            addCriterion("cap_type <>", value, "capType");
            return (Criteria) this;
        }

        public Criteria andCapTypeGreaterThan(Short value) {
            addCriterion("cap_type >", value, "capType");
            return (Criteria) this;
        }

        public Criteria andCapTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("cap_type >=", value, "capType");
            return (Criteria) this;
        }

        public Criteria andCapTypeLessThan(Short value) {
            addCriterion("cap_type <", value, "capType");
            return (Criteria) this;
        }

        public Criteria andCapTypeLessThanOrEqualTo(Short value) {
            addCriterion("cap_type <=", value, "capType");
            return (Criteria) this;
        }

        public Criteria andCapTypeIn(List<Short> values) {
            addCriterion("cap_type in", values, "capType");
            return (Criteria) this;
        }

        public Criteria andCapTypeNotIn(List<Short> values) {
            addCriterion("cap_type not in", values, "capType");
            return (Criteria) this;
        }

        public Criteria andCapTypeBetween(Short value1, Short value2) {
            addCriterion("cap_type between", value1, value2, "capType");
            return (Criteria) this;
        }

        public Criteria andCapTypeNotBetween(Short value1, Short value2) {
            addCriterion("cap_type not between", value1, value2, "capType");
            return (Criteria) this;
        }

        public Criteria andCapPersonImageIsNull() {
            addCriterion("cap_person_image is null");
            return (Criteria) this;
        }

        public Criteria andCapPersonImageIsNotNull() {
            addCriterion("cap_person_image is not null");
            return (Criteria) this;
        }

        public Criteria andCapPersonImageEqualTo(String value) {
            addCriterion("cap_person_image =", value, "capPersonImage");
            return (Criteria) this;
        }

        public Criteria andCapPersonImageNotEqualTo(String value) {
            addCriterion("cap_person_image <>", value, "capPersonImage");
            return (Criteria) this;
        }

        public Criteria andCapPersonImageGreaterThan(String value) {
            addCriterion("cap_person_image >", value, "capPersonImage");
            return (Criteria) this;
        }

        public Criteria andCapPersonImageGreaterThanOrEqualTo(String value) {
            addCriterion("cap_person_image >=", value, "capPersonImage");
            return (Criteria) this;
        }

        public Criteria andCapPersonImageLessThan(String value) {
            addCriterion("cap_person_image <", value, "capPersonImage");
            return (Criteria) this;
        }

        public Criteria andCapPersonImageLessThanOrEqualTo(String value) {
            addCriterion("cap_person_image <=", value, "capPersonImage");
            return (Criteria) this;
        }

        public Criteria andCapPersonImageLike(String value) {
            addCriterion("cap_person_image like", value, "capPersonImage");
            return (Criteria) this;
        }

        public Criteria andCapPersonImageNotLike(String value) {
            addCriterion("cap_person_image not like", value, "capPersonImage");
            return (Criteria) this;
        }

        public Criteria andCapPersonImageIn(List<String> values) {
            addCriterion("cap_person_image in", values, "capPersonImage");
            return (Criteria) this;
        }

        public Criteria andCapPersonImageNotIn(List<String> values) {
            addCriterion("cap_person_image not in", values, "capPersonImage");
            return (Criteria) this;
        }

        public Criteria andCapPersonImageBetween(String value1, String value2) {
            addCriterion("cap_person_image between", value1, value2, "capPersonImage");
            return (Criteria) this;
        }

        public Criteria andCapPersonImageNotBetween(String value1, String value2) {
            addCriterion("cap_person_image not between", value1, value2, "capPersonImage");
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