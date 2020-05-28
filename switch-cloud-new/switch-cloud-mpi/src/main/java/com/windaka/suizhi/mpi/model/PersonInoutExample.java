package com.windaka.suizhi.mpi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonInoutExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PersonInoutExample() {
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

        public Criteria andOpenResultIsNull() {
            addCriterion("open_result is null");
            return (Criteria) this;
        }

        public Criteria andOpenResultIsNotNull() {
            addCriterion("open_result is not null");
            return (Criteria) this;
        }

        public Criteria andOpenResultEqualTo(Short value) {
            addCriterion("open_result =", value, "openResult");
            return (Criteria) this;
        }

        public Criteria andOpenResultNotEqualTo(Short value) {
            addCriterion("open_result <>", value, "openResult");
            return (Criteria) this;
        }

        public Criteria andOpenResultGreaterThan(Short value) {
            addCriterion("open_result >", value, "openResult");
            return (Criteria) this;
        }

        public Criteria andOpenResultGreaterThanOrEqualTo(Short value) {
            addCriterion("open_result >=", value, "openResult");
            return (Criteria) this;
        }

        public Criteria andOpenResultLessThan(Short value) {
            addCriterion("open_result <", value, "openResult");
            return (Criteria) this;
        }

        public Criteria andOpenResultLessThanOrEqualTo(Short value) {
            addCriterion("open_result <=", value, "openResult");
            return (Criteria) this;
        }

        public Criteria andOpenResultIn(List<Short> values) {
            addCriterion("open_result in", values, "openResult");
            return (Criteria) this;
        }

        public Criteria andOpenResultNotIn(List<Short> values) {
            addCriterion("open_result not in", values, "openResult");
            return (Criteria) this;
        }

        public Criteria andOpenResultBetween(Short value1, Short value2) {
            addCriterion("open_result between", value1, value2, "openResult");
            return (Criteria) this;
        }

        public Criteria andOpenResultNotBetween(Short value1, Short value2) {
            addCriterion("open_result not between", value1, value2, "openResult");
            return (Criteria) this;
        }

        public Criteria andOpenResultNameIsNull() {
            addCriterion("open_result_name is null");
            return (Criteria) this;
        }

        public Criteria andOpenResultNameIsNotNull() {
            addCriterion("open_result_name is not null");
            return (Criteria) this;
        }

        public Criteria andOpenResultNameEqualTo(String value) {
            addCriterion("open_result_name =", value, "openResultName");
            return (Criteria) this;
        }

        public Criteria andOpenResultNameNotEqualTo(String value) {
            addCriterion("open_result_name <>", value, "openResultName");
            return (Criteria) this;
        }

        public Criteria andOpenResultNameGreaterThan(String value) {
            addCriterion("open_result_name >", value, "openResultName");
            return (Criteria) this;
        }

        public Criteria andOpenResultNameGreaterThanOrEqualTo(String value) {
            addCriterion("open_result_name >=", value, "openResultName");
            return (Criteria) this;
        }

        public Criteria andOpenResultNameLessThan(String value) {
            addCriterion("open_result_name <", value, "openResultName");
            return (Criteria) this;
        }

        public Criteria andOpenResultNameLessThanOrEqualTo(String value) {
            addCriterion("open_result_name <=", value, "openResultName");
            return (Criteria) this;
        }

        public Criteria andOpenResultNameLike(String value) {
            addCriterion("open_result_name like", value, "openResultName");
            return (Criteria) this;
        }

        public Criteria andOpenResultNameNotLike(String value) {
            addCriterion("open_result_name not like", value, "openResultName");
            return (Criteria) this;
        }

        public Criteria andOpenResultNameIn(List<String> values) {
            addCriterion("open_result_name in", values, "openResultName");
            return (Criteria) this;
        }

        public Criteria andOpenResultNameNotIn(List<String> values) {
            addCriterion("open_result_name not in", values, "openResultName");
            return (Criteria) this;
        }

        public Criteria andOpenResultNameBetween(String value1, String value2) {
            addCriterion("open_result_name between", value1, value2, "openResultName");
            return (Criteria) this;
        }

        public Criteria andOpenResultNameNotBetween(String value1, String value2) {
            addCriterion("open_result_name not between", value1, value2, "openResultName");
            return (Criteria) this;
        }

        public Criteria andOpenTypeIsNull() {
            addCriterion("open_type is null");
            return (Criteria) this;
        }

        public Criteria andOpenTypeIsNotNull() {
            addCriterion("open_type is not null");
            return (Criteria) this;
        }

        public Criteria andOpenTypeEqualTo(Short value) {
            addCriterion("open_type =", value, "openType");
            return (Criteria) this;
        }

        public Criteria andOpenTypeNotEqualTo(Short value) {
            addCriterion("open_type <>", value, "openType");
            return (Criteria) this;
        }

        public Criteria andOpenTypeGreaterThan(Short value) {
            addCriterion("open_type >", value, "openType");
            return (Criteria) this;
        }

        public Criteria andOpenTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("open_type >=", value, "openType");
            return (Criteria) this;
        }

        public Criteria andOpenTypeLessThan(Short value) {
            addCriterion("open_type <", value, "openType");
            return (Criteria) this;
        }

        public Criteria andOpenTypeLessThanOrEqualTo(Short value) {
            addCriterion("open_type <=", value, "openType");
            return (Criteria) this;
        }

        public Criteria andOpenTypeIn(List<Short> values) {
            addCriterion("open_type in", values, "openType");
            return (Criteria) this;
        }

        public Criteria andOpenTypeNotIn(List<Short> values) {
            addCriterion("open_type not in", values, "openType");
            return (Criteria) this;
        }

        public Criteria andOpenTypeBetween(Short value1, Short value2) {
            addCriterion("open_type between", value1, value2, "openType");
            return (Criteria) this;
        }

        public Criteria andOpenTypeNotBetween(Short value1, Short value2) {
            addCriterion("open_type not between", value1, value2, "openType");
            return (Criteria) this;
        }

        public Criteria andOpenTypeNameIsNull() {
            addCriterion("open_type_name is null");
            return (Criteria) this;
        }

        public Criteria andOpenTypeNameIsNotNull() {
            addCriterion("open_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andOpenTypeNameEqualTo(String value) {
            addCriterion("open_type_name =", value, "openTypeName");
            return (Criteria) this;
        }

        public Criteria andOpenTypeNameNotEqualTo(String value) {
            addCriterion("open_type_name <>", value, "openTypeName");
            return (Criteria) this;
        }

        public Criteria andOpenTypeNameGreaterThan(String value) {
            addCriterion("open_type_name >", value, "openTypeName");
            return (Criteria) this;
        }

        public Criteria andOpenTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("open_type_name >=", value, "openTypeName");
            return (Criteria) this;
        }

        public Criteria andOpenTypeNameLessThan(String value) {
            addCriterion("open_type_name <", value, "openTypeName");
            return (Criteria) this;
        }

        public Criteria andOpenTypeNameLessThanOrEqualTo(String value) {
            addCriterion("open_type_name <=", value, "openTypeName");
            return (Criteria) this;
        }

        public Criteria andOpenTypeNameLike(String value) {
            addCriterion("open_type_name like", value, "openTypeName");
            return (Criteria) this;
        }

        public Criteria andOpenTypeNameNotLike(String value) {
            addCriterion("open_type_name not like", value, "openTypeName");
            return (Criteria) this;
        }

        public Criteria andOpenTypeNameIn(List<String> values) {
            addCriterion("open_type_name in", values, "openTypeName");
            return (Criteria) this;
        }

        public Criteria andOpenTypeNameNotIn(List<String> values) {
            addCriterion("open_type_name not in", values, "openTypeName");
            return (Criteria) this;
        }

        public Criteria andOpenTypeNameBetween(String value1, String value2) {
            addCriterion("open_type_name between", value1, value2, "openTypeName");
            return (Criteria) this;
        }

        public Criteria andOpenTypeNameNotBetween(String value1, String value2) {
            addCriterion("open_type_name not between", value1, value2, "openTypeName");
            return (Criteria) this;
        }

        public Criteria andChannelNameIsNull() {
            addCriterion("channel_name is null");
            return (Criteria) this;
        }

        public Criteria andChannelNameIsNotNull() {
            addCriterion("channel_name is not null");
            return (Criteria) this;
        }

        public Criteria andChannelNameEqualTo(String value) {
            addCriterion("channel_name =", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotEqualTo(String value) {
            addCriterion("channel_name <>", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameGreaterThan(String value) {
            addCriterion("channel_name >", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameGreaterThanOrEqualTo(String value) {
            addCriterion("channel_name >=", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameLessThan(String value) {
            addCriterion("channel_name <", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameLessThanOrEqualTo(String value) {
            addCriterion("channel_name <=", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameLike(String value) {
            addCriterion("channel_name like", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotLike(String value) {
            addCriterion("channel_name not like", value, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameIn(List<String> values) {
            addCriterion("channel_name in", values, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotIn(List<String> values) {
            addCriterion("channel_name not in", values, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameBetween(String value1, String value2) {
            addCriterion("channel_name between", value1, value2, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelNameNotBetween(String value1, String value2) {
            addCriterion("channel_name not between", value1, value2, "channelName");
            return (Criteria) this;
        }

        public Criteria andChannelSeqIsNull() {
            addCriterion("channel_seq is null");
            return (Criteria) this;
        }

        public Criteria andChannelSeqIsNotNull() {
            addCriterion("channel_seq is not null");
            return (Criteria) this;
        }

        public Criteria andChannelSeqEqualTo(Short value) {
            addCriterion("channel_seq =", value, "channelSeq");
            return (Criteria) this;
        }

        public Criteria andChannelSeqNotEqualTo(Short value) {
            addCriterion("channel_seq <>", value, "channelSeq");
            return (Criteria) this;
        }

        public Criteria andChannelSeqGreaterThan(Short value) {
            addCriterion("channel_seq >", value, "channelSeq");
            return (Criteria) this;
        }

        public Criteria andChannelSeqGreaterThanOrEqualTo(Short value) {
            addCriterion("channel_seq >=", value, "channelSeq");
            return (Criteria) this;
        }

        public Criteria andChannelSeqLessThan(Short value) {
            addCriterion("channel_seq <", value, "channelSeq");
            return (Criteria) this;
        }

        public Criteria andChannelSeqLessThanOrEqualTo(Short value) {
            addCriterion("channel_seq <=", value, "channelSeq");
            return (Criteria) this;
        }

        public Criteria andChannelSeqIn(List<Short> values) {
            addCriterion("channel_seq in", values, "channelSeq");
            return (Criteria) this;
        }

        public Criteria andChannelSeqNotIn(List<Short> values) {
            addCriterion("channel_seq not in", values, "channelSeq");
            return (Criteria) this;
        }

        public Criteria andChannelSeqBetween(Short value1, Short value2) {
            addCriterion("channel_seq between", value1, value2, "channelSeq");
            return (Criteria) this;
        }

        public Criteria andChannelSeqNotBetween(Short value1, Short value2) {
            addCriterion("channel_seq not between", value1, value2, "channelSeq");
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

        public Criteria andCapTypeIsNull() {
            addCriterion("cap_type is null");
            return (Criteria) this;
        }

        public Criteria andCapTypeIsNotNull() {
            addCriterion("cap_type is not null");
            return (Criteria) this;
        }

        public Criteria andCapTypeEqualTo(String value) {
            addCriterion("cap_type =", value, "capType");
            return (Criteria) this;
        }

        public Criteria andCapTypeNotEqualTo(String value) {
            addCriterion("cap_type <>", value, "capType");
            return (Criteria) this;
        }

        public Criteria andCapTypeGreaterThan(String value) {
            addCriterion("cap_type >", value, "capType");
            return (Criteria) this;
        }

        public Criteria andCapTypeGreaterThanOrEqualTo(String value) {
            addCriterion("cap_type >=", value, "capType");
            return (Criteria) this;
        }

        public Criteria andCapTypeLessThan(String value) {
            addCriterion("cap_type <", value, "capType");
            return (Criteria) this;
        }

        public Criteria andCapTypeLessThanOrEqualTo(String value) {
            addCriterion("cap_type <=", value, "capType");
            return (Criteria) this;
        }

        public Criteria andCapTypeLike(String value) {
            addCriterion("cap_type like", value, "capType");
            return (Criteria) this;
        }

        public Criteria andCapTypeNotLike(String value) {
            addCriterion("cap_type not like", value, "capType");
            return (Criteria) this;
        }

        public Criteria andCapTypeIn(List<String> values) {
            addCriterion("cap_type in", values, "capType");
            return (Criteria) this;
        }

        public Criteria andCapTypeNotIn(List<String> values) {
            addCriterion("cap_type not in", values, "capType");
            return (Criteria) this;
        }

        public Criteria andCapTypeBetween(String value1, String value2) {
            addCriterion("cap_type between", value1, value2, "capType");
            return (Criteria) this;
        }

        public Criteria andCapTypeNotBetween(String value1, String value2) {
            addCriterion("cap_type not between", value1, value2, "capType");
            return (Criteria) this;
        }

        public Criteria andCapTypeNameIsNull() {
            addCriterion("cap_type_name is null");
            return (Criteria) this;
        }

        public Criteria andCapTypeNameIsNotNull() {
            addCriterion("cap_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andCapTypeNameEqualTo(String value) {
            addCriterion("cap_type_name =", value, "capTypeName");
            return (Criteria) this;
        }

        public Criteria andCapTypeNameNotEqualTo(String value) {
            addCriterion("cap_type_name <>", value, "capTypeName");
            return (Criteria) this;
        }

        public Criteria andCapTypeNameGreaterThan(String value) {
            addCriterion("cap_type_name >", value, "capTypeName");
            return (Criteria) this;
        }

        public Criteria andCapTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("cap_type_name >=", value, "capTypeName");
            return (Criteria) this;
        }

        public Criteria andCapTypeNameLessThan(String value) {
            addCriterion("cap_type_name <", value, "capTypeName");
            return (Criteria) this;
        }

        public Criteria andCapTypeNameLessThanOrEqualTo(String value) {
            addCriterion("cap_type_name <=", value, "capTypeName");
            return (Criteria) this;
        }

        public Criteria andCapTypeNameLike(String value) {
            addCriterion("cap_type_name like", value, "capTypeName");
            return (Criteria) this;
        }

        public Criteria andCapTypeNameNotLike(String value) {
            addCriterion("cap_type_name not like", value, "capTypeName");
            return (Criteria) this;
        }

        public Criteria andCapTypeNameIn(List<String> values) {
            addCriterion("cap_type_name in", values, "capTypeName");
            return (Criteria) this;
        }

        public Criteria andCapTypeNameNotIn(List<String> values) {
            addCriterion("cap_type_name not in", values, "capTypeName");
            return (Criteria) this;
        }

        public Criteria andCapTypeNameBetween(String value1, String value2) {
            addCriterion("cap_type_name between", value1, value2, "capTypeName");
            return (Criteria) this;
        }

        public Criteria andCapTypeNameNotBetween(String value1, String value2) {
            addCriterion("cap_type_name not between", value1, value2, "capTypeName");
            return (Criteria) this;
        }

        public Criteria andAccessIsNull() {
            addCriterion("access is null");
            return (Criteria) this;
        }

        public Criteria andAccessIsNotNull() {
            addCriterion("access is not null");
            return (Criteria) this;
        }

        public Criteria andAccessEqualTo(Short value) {
            addCriterion("access =", value, "access");
            return (Criteria) this;
        }

        public Criteria andAccessNotEqualTo(Short value) {
            addCriterion("access <>", value, "access");
            return (Criteria) this;
        }

        public Criteria andAccessGreaterThan(Short value) {
            addCriterion("access >", value, "access");
            return (Criteria) this;
        }

        public Criteria andAccessGreaterThanOrEqualTo(Short value) {
            addCriterion("access >=", value, "access");
            return (Criteria) this;
        }

        public Criteria andAccessLessThan(Short value) {
            addCriterion("access <", value, "access");
            return (Criteria) this;
        }

        public Criteria andAccessLessThanOrEqualTo(Short value) {
            addCriterion("access <=", value, "access");
            return (Criteria) this;
        }

        public Criteria andAccessIn(List<Short> values) {
            addCriterion("access in", values, "access");
            return (Criteria) this;
        }

        public Criteria andAccessNotIn(List<Short> values) {
            addCriterion("access not in", values, "access");
            return (Criteria) this;
        }

        public Criteria andAccessBetween(Short value1, Short value2) {
            addCriterion("access between", value1, value2, "access");
            return (Criteria) this;
        }

        public Criteria andAccessNotBetween(Short value1, Short value2) {
            addCriterion("access not between", value1, value2, "access");
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