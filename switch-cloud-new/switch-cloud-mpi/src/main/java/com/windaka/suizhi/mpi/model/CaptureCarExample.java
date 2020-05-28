package com.windaka.suizhi.mpi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CaptureCarExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CaptureCarExample() {
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

        public Criteria andCarNumIsNull() {
            addCriterion("car_num is null");
            return (Criteria) this;
        }

        public Criteria andCarNumIsNotNull() {
            addCriterion("car_num is not null");
            return (Criteria) this;
        }

        public Criteria andCarNumEqualTo(String value) {
            addCriterion("car_num =", value, "carNum");
            return (Criteria) this;
        }

        public Criteria andCarNumNotEqualTo(String value) {
            addCriterion("car_num <>", value, "carNum");
            return (Criteria) this;
        }

        public Criteria andCarNumGreaterThan(String value) {
            addCriterion("car_num >", value, "carNum");
            return (Criteria) this;
        }

        public Criteria andCarNumGreaterThanOrEqualTo(String value) {
            addCriterion("car_num >=", value, "carNum");
            return (Criteria) this;
        }

        public Criteria andCarNumLessThan(String value) {
            addCriterion("car_num <", value, "carNum");
            return (Criteria) this;
        }

        public Criteria andCarNumLessThanOrEqualTo(String value) {
            addCriterion("car_num <=", value, "carNum");
            return (Criteria) this;
        }

        public Criteria andCarNumLike(String value) {
            addCriterion("car_num like", value, "carNum");
            return (Criteria) this;
        }

        public Criteria andCarNumNotLike(String value) {
            addCriterion("car_num not like", value, "carNum");
            return (Criteria) this;
        }

        public Criteria andCarNumIn(List<String> values) {
            addCriterion("car_num in", values, "carNum");
            return (Criteria) this;
        }

        public Criteria andCarNumNotIn(List<String> values) {
            addCriterion("car_num not in", values, "carNum");
            return (Criteria) this;
        }

        public Criteria andCarNumBetween(String value1, String value2) {
            addCriterion("car_num between", value1, value2, "carNum");
            return (Criteria) this;
        }

        public Criteria andCarNumNotBetween(String value1, String value2) {
            addCriterion("car_num not between", value1, value2, "carNum");
            return (Criteria) this;
        }

        public Criteria andCarNumColorIsNull() {
            addCriterion("car_num_color is null");
            return (Criteria) this;
        }

        public Criteria andCarNumColorIsNotNull() {
            addCriterion("car_num_color is not null");
            return (Criteria) this;
        }

        public Criteria andCarNumColorEqualTo(Short value) {
            addCriterion("car_num_color =", value, "carNumColor");
            return (Criteria) this;
        }

        public Criteria andCarNumColorNotEqualTo(Short value) {
            addCriterion("car_num_color <>", value, "carNumColor");
            return (Criteria) this;
        }

        public Criteria andCarNumColorGreaterThan(Short value) {
            addCriterion("car_num_color >", value, "carNumColor");
            return (Criteria) this;
        }

        public Criteria andCarNumColorGreaterThanOrEqualTo(Short value) {
            addCriterion("car_num_color >=", value, "carNumColor");
            return (Criteria) this;
        }

        public Criteria andCarNumColorLessThan(Short value) {
            addCriterion("car_num_color <", value, "carNumColor");
            return (Criteria) this;
        }

        public Criteria andCarNumColorLessThanOrEqualTo(Short value) {
            addCriterion("car_num_color <=", value, "carNumColor");
            return (Criteria) this;
        }

        public Criteria andCarNumColorIn(List<Short> values) {
            addCriterion("car_num_color in", values, "carNumColor");
            return (Criteria) this;
        }

        public Criteria andCarNumColorNotIn(List<Short> values) {
            addCriterion("car_num_color not in", values, "carNumColor");
            return (Criteria) this;
        }

        public Criteria andCarNumColorBetween(Short value1, Short value2) {
            addCriterion("car_num_color between", value1, value2, "carNumColor");
            return (Criteria) this;
        }

        public Criteria andCarNumColorNotBetween(Short value1, Short value2) {
            addCriterion("car_num_color not between", value1, value2, "carNumColor");
            return (Criteria) this;
        }

        public Criteria andCarNumColorNameIsNull() {
            addCriterion("car_num_color_name is null");
            return (Criteria) this;
        }

        public Criteria andCarNumColorNameIsNotNull() {
            addCriterion("car_num_color_name is not null");
            return (Criteria) this;
        }

        public Criteria andCarNumColorNameEqualTo(String value) {
            addCriterion("car_num_color_name =", value, "carNumColorName");
            return (Criteria) this;
        }

        public Criteria andCarNumColorNameNotEqualTo(String value) {
            addCriterion("car_num_color_name <>", value, "carNumColorName");
            return (Criteria) this;
        }

        public Criteria andCarNumColorNameGreaterThan(String value) {
            addCriterion("car_num_color_name >", value, "carNumColorName");
            return (Criteria) this;
        }

        public Criteria andCarNumColorNameGreaterThanOrEqualTo(String value) {
            addCriterion("car_num_color_name >=", value, "carNumColorName");
            return (Criteria) this;
        }

        public Criteria andCarNumColorNameLessThan(String value) {
            addCriterion("car_num_color_name <", value, "carNumColorName");
            return (Criteria) this;
        }

        public Criteria andCarNumColorNameLessThanOrEqualTo(String value) {
            addCriterion("car_num_color_name <=", value, "carNumColorName");
            return (Criteria) this;
        }

        public Criteria andCarNumColorNameLike(String value) {
            addCriterion("car_num_color_name like", value, "carNumColorName");
            return (Criteria) this;
        }

        public Criteria andCarNumColorNameNotLike(String value) {
            addCriterion("car_num_color_name not like", value, "carNumColorName");
            return (Criteria) this;
        }

        public Criteria andCarNumColorNameIn(List<String> values) {
            addCriterion("car_num_color_name in", values, "carNumColorName");
            return (Criteria) this;
        }

        public Criteria andCarNumColorNameNotIn(List<String> values) {
            addCriterion("car_num_color_name not in", values, "carNumColorName");
            return (Criteria) this;
        }

        public Criteria andCarNumColorNameBetween(String value1, String value2) {
            addCriterion("car_num_color_name between", value1, value2, "carNumColorName");
            return (Criteria) this;
        }

        public Criteria andCarNumColorNameNotBetween(String value1, String value2) {
            addCriterion("car_num_color_name not between", value1, value2, "carNumColorName");
            return (Criteria) this;
        }

        public Criteria andCarTypeIsNull() {
            addCriterion("car_type is null");
            return (Criteria) this;
        }

        public Criteria andCarTypeIsNotNull() {
            addCriterion("car_type is not null");
            return (Criteria) this;
        }

        public Criteria andCarTypeEqualTo(Short value) {
            addCriterion("car_type =", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeNotEqualTo(Short value) {
            addCriterion("car_type <>", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeGreaterThan(Short value) {
            addCriterion("car_type >", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("car_type >=", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeLessThan(Short value) {
            addCriterion("car_type <", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeLessThanOrEqualTo(Short value) {
            addCriterion("car_type <=", value, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeIn(List<Short> values) {
            addCriterion("car_type in", values, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeNotIn(List<Short> values) {
            addCriterion("car_type not in", values, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeBetween(Short value1, Short value2) {
            addCriterion("car_type between", value1, value2, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeNotBetween(Short value1, Short value2) {
            addCriterion("car_type not between", value1, value2, "carType");
            return (Criteria) this;
        }

        public Criteria andCarTypeNameIsNull() {
            addCriterion("car_type_name is null");
            return (Criteria) this;
        }

        public Criteria andCarTypeNameIsNotNull() {
            addCriterion("car_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andCarTypeNameEqualTo(String value) {
            addCriterion("car_type_name =", value, "carTypeName");
            return (Criteria) this;
        }

        public Criteria andCarTypeNameNotEqualTo(String value) {
            addCriterion("car_type_name <>", value, "carTypeName");
            return (Criteria) this;
        }

        public Criteria andCarTypeNameGreaterThan(String value) {
            addCriterion("car_type_name >", value, "carTypeName");
            return (Criteria) this;
        }

        public Criteria andCarTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("car_type_name >=", value, "carTypeName");
            return (Criteria) this;
        }

        public Criteria andCarTypeNameLessThan(String value) {
            addCriterion("car_type_name <", value, "carTypeName");
            return (Criteria) this;
        }

        public Criteria andCarTypeNameLessThanOrEqualTo(String value) {
            addCriterion("car_type_name <=", value, "carTypeName");
            return (Criteria) this;
        }

        public Criteria andCarTypeNameLike(String value) {
            addCriterion("car_type_name like", value, "carTypeName");
            return (Criteria) this;
        }

        public Criteria andCarTypeNameNotLike(String value) {
            addCriterion("car_type_name not like", value, "carTypeName");
            return (Criteria) this;
        }

        public Criteria andCarTypeNameIn(List<String> values) {
            addCriterion("car_type_name in", values, "carTypeName");
            return (Criteria) this;
        }

        public Criteria andCarTypeNameNotIn(List<String> values) {
            addCriterion("car_type_name not in", values, "carTypeName");
            return (Criteria) this;
        }

        public Criteria andCarTypeNameBetween(String value1, String value2) {
            addCriterion("car_type_name between", value1, value2, "carTypeName");
            return (Criteria) this;
        }

        public Criteria andCarTypeNameNotBetween(String value1, String value2) {
            addCriterion("car_type_name not between", value1, value2, "carTypeName");
            return (Criteria) this;
        }

        public Criteria andCarBrandIsNull() {
            addCriterion("car_brand is null");
            return (Criteria) this;
        }

        public Criteria andCarBrandIsNotNull() {
            addCriterion("car_brand is not null");
            return (Criteria) this;
        }

        public Criteria andCarBrandEqualTo(Short value) {
            addCriterion("car_brand =", value, "carBrand");
            return (Criteria) this;
        }

        public Criteria andCarBrandNotEqualTo(Short value) {
            addCriterion("car_brand <>", value, "carBrand");
            return (Criteria) this;
        }

        public Criteria andCarBrandGreaterThan(Short value) {
            addCriterion("car_brand >", value, "carBrand");
            return (Criteria) this;
        }

        public Criteria andCarBrandGreaterThanOrEqualTo(Short value) {
            addCriterion("car_brand >=", value, "carBrand");
            return (Criteria) this;
        }

        public Criteria andCarBrandLessThan(Short value) {
            addCriterion("car_brand <", value, "carBrand");
            return (Criteria) this;
        }

        public Criteria andCarBrandLessThanOrEqualTo(Short value) {
            addCriterion("car_brand <=", value, "carBrand");
            return (Criteria) this;
        }

        public Criteria andCarBrandIn(List<Short> values) {
            addCriterion("car_brand in", values, "carBrand");
            return (Criteria) this;
        }

        public Criteria andCarBrandNotIn(List<Short> values) {
            addCriterion("car_brand not in", values, "carBrand");
            return (Criteria) this;
        }

        public Criteria andCarBrandBetween(Short value1, Short value2) {
            addCriterion("car_brand between", value1, value2, "carBrand");
            return (Criteria) this;
        }

        public Criteria andCarBrandNotBetween(Short value1, Short value2) {
            addCriterion("car_brand not between", value1, value2, "carBrand");
            return (Criteria) this;
        }

        public Criteria andCarBrandNameIsNull() {
            addCriterion("car_brand_name is null");
            return (Criteria) this;
        }

        public Criteria andCarBrandNameIsNotNull() {
            addCriterion("car_brand_name is not null");
            return (Criteria) this;
        }

        public Criteria andCarBrandNameEqualTo(String value) {
            addCriterion("car_brand_name =", value, "carBrandName");
            return (Criteria) this;
        }

        public Criteria andCarBrandNameNotEqualTo(String value) {
            addCriterion("car_brand_name <>", value, "carBrandName");
            return (Criteria) this;
        }

        public Criteria andCarBrandNameGreaterThan(String value) {
            addCriterion("car_brand_name >", value, "carBrandName");
            return (Criteria) this;
        }

        public Criteria andCarBrandNameGreaterThanOrEqualTo(String value) {
            addCriterion("car_brand_name >=", value, "carBrandName");
            return (Criteria) this;
        }

        public Criteria andCarBrandNameLessThan(String value) {
            addCriterion("car_brand_name <", value, "carBrandName");
            return (Criteria) this;
        }

        public Criteria andCarBrandNameLessThanOrEqualTo(String value) {
            addCriterion("car_brand_name <=", value, "carBrandName");
            return (Criteria) this;
        }

        public Criteria andCarBrandNameLike(String value) {
            addCriterion("car_brand_name like", value, "carBrandName");
            return (Criteria) this;
        }

        public Criteria andCarBrandNameNotLike(String value) {
            addCriterion("car_brand_name not like", value, "carBrandName");
            return (Criteria) this;
        }

        public Criteria andCarBrandNameIn(List<String> values) {
            addCriterion("car_brand_name in", values, "carBrandName");
            return (Criteria) this;
        }

        public Criteria andCarBrandNameNotIn(List<String> values) {
            addCriterion("car_brand_name not in", values, "carBrandName");
            return (Criteria) this;
        }

        public Criteria andCarBrandNameBetween(String value1, String value2) {
            addCriterion("car_brand_name between", value1, value2, "carBrandName");
            return (Criteria) this;
        }

        public Criteria andCarBrandNameNotBetween(String value1, String value2) {
            addCriterion("car_brand_name not between", value1, value2, "carBrandName");
            return (Criteria) this;
        }

        public Criteria andCarColorIsNull() {
            addCriterion("car_color is null");
            return (Criteria) this;
        }

        public Criteria andCarColorIsNotNull() {
            addCriterion("car_color is not null");
            return (Criteria) this;
        }

        public Criteria andCarColorEqualTo(Short value) {
            addCriterion("car_color =", value, "carColor");
            return (Criteria) this;
        }

        public Criteria andCarColorNotEqualTo(Short value) {
            addCriterion("car_color <>", value, "carColor");
            return (Criteria) this;
        }

        public Criteria andCarColorGreaterThan(Short value) {
            addCriterion("car_color >", value, "carColor");
            return (Criteria) this;
        }

        public Criteria andCarColorGreaterThanOrEqualTo(Short value) {
            addCriterion("car_color >=", value, "carColor");
            return (Criteria) this;
        }

        public Criteria andCarColorLessThan(Short value) {
            addCriterion("car_color <", value, "carColor");
            return (Criteria) this;
        }

        public Criteria andCarColorLessThanOrEqualTo(Short value) {
            addCriterion("car_color <=", value, "carColor");
            return (Criteria) this;
        }

        public Criteria andCarColorIn(List<Short> values) {
            addCriterion("car_color in", values, "carColor");
            return (Criteria) this;
        }

        public Criteria andCarColorNotIn(List<Short> values) {
            addCriterion("car_color not in", values, "carColor");
            return (Criteria) this;
        }

        public Criteria andCarColorBetween(Short value1, Short value2) {
            addCriterion("car_color between", value1, value2, "carColor");
            return (Criteria) this;
        }

        public Criteria andCarColorNotBetween(Short value1, Short value2) {
            addCriterion("car_color not between", value1, value2, "carColor");
            return (Criteria) this;
        }

        public Criteria andCarColorNameIsNull() {
            addCriterion("car_color_name is null");
            return (Criteria) this;
        }

        public Criteria andCarColorNameIsNotNull() {
            addCriterion("car_color_name is not null");
            return (Criteria) this;
        }

        public Criteria andCarColorNameEqualTo(String value) {
            addCriterion("car_color_name =", value, "carColorName");
            return (Criteria) this;
        }

        public Criteria andCarColorNameNotEqualTo(String value) {
            addCriterion("car_color_name <>", value, "carColorName");
            return (Criteria) this;
        }

        public Criteria andCarColorNameGreaterThan(String value) {
            addCriterion("car_color_name >", value, "carColorName");
            return (Criteria) this;
        }

        public Criteria andCarColorNameGreaterThanOrEqualTo(String value) {
            addCriterion("car_color_name >=", value, "carColorName");
            return (Criteria) this;
        }

        public Criteria andCarColorNameLessThan(String value) {
            addCriterion("car_color_name <", value, "carColorName");
            return (Criteria) this;
        }

        public Criteria andCarColorNameLessThanOrEqualTo(String value) {
            addCriterion("car_color_name <=", value, "carColorName");
            return (Criteria) this;
        }

        public Criteria andCarColorNameLike(String value) {
            addCriterion("car_color_name like", value, "carColorName");
            return (Criteria) this;
        }

        public Criteria andCarColorNameNotLike(String value) {
            addCriterion("car_color_name not like", value, "carColorName");
            return (Criteria) this;
        }

        public Criteria andCarColorNameIn(List<String> values) {
            addCriterion("car_color_name in", values, "carColorName");
            return (Criteria) this;
        }

        public Criteria andCarColorNameNotIn(List<String> values) {
            addCriterion("car_color_name not in", values, "carColorName");
            return (Criteria) this;
        }

        public Criteria andCarColorNameBetween(String value1, String value2) {
            addCriterion("car_color_name between", value1, value2, "carColorName");
            return (Criteria) this;
        }

        public Criteria andCarColorNameNotBetween(String value1, String value2) {
            addCriterion("car_color_name not between", value1, value2, "carColorName");
            return (Criteria) this;
        }

        public Criteria andCarImageIsNull() {
            addCriterion("car_image is null");
            return (Criteria) this;
        }

        public Criteria andCarImageIsNotNull() {
            addCriterion("car_image is not null");
            return (Criteria) this;
        }

        public Criteria andCarImageEqualTo(String value) {
            addCriterion("car_image =", value, "carImage");
            return (Criteria) this;
        }

        public Criteria andCarImageNotEqualTo(String value) {
            addCriterion("car_image <>", value, "carImage");
            return (Criteria) this;
        }

        public Criteria andCarImageGreaterThan(String value) {
            addCriterion("car_image >", value, "carImage");
            return (Criteria) this;
        }

        public Criteria andCarImageGreaterThanOrEqualTo(String value) {
            addCriterion("car_image >=", value, "carImage");
            return (Criteria) this;
        }

        public Criteria andCarImageLessThan(String value) {
            addCriterion("car_image <", value, "carImage");
            return (Criteria) this;
        }

        public Criteria andCarImageLessThanOrEqualTo(String value) {
            addCriterion("car_image <=", value, "carImage");
            return (Criteria) this;
        }

        public Criteria andCarImageLike(String value) {
            addCriterion("car_image like", value, "carImage");
            return (Criteria) this;
        }

        public Criteria andCarImageNotLike(String value) {
            addCriterion("car_image not like", value, "carImage");
            return (Criteria) this;
        }

        public Criteria andCarImageIn(List<String> values) {
            addCriterion("car_image in", values, "carImage");
            return (Criteria) this;
        }

        public Criteria andCarImageNotIn(List<String> values) {
            addCriterion("car_image not in", values, "carImage");
            return (Criteria) this;
        }

        public Criteria andCarImageBetween(String value1, String value2) {
            addCriterion("car_image between", value1, value2, "carImage");
            return (Criteria) this;
        }

        public Criteria andCarImageNotBetween(String value1, String value2) {
            addCriterion("car_image not between", value1, value2, "carImage");
            return (Criteria) this;
        }

        public Criteria andCarDirectionIsNull() {
            addCriterion("car_direction is null");
            return (Criteria) this;
        }

        public Criteria andCarDirectionIsNotNull() {
            addCriterion("car_direction is not null");
            return (Criteria) this;
        }

        public Criteria andCarDirectionEqualTo(Byte value) {
            addCriterion("car_direction =", value, "carDirection");
            return (Criteria) this;
        }

        public Criteria andCarDirectionNotEqualTo(Byte value) {
            addCriterion("car_direction <>", value, "carDirection");
            return (Criteria) this;
        }

        public Criteria andCarDirectionGreaterThan(Byte value) {
            addCriterion("car_direction >", value, "carDirection");
            return (Criteria) this;
        }

        public Criteria andCarDirectionGreaterThanOrEqualTo(Byte value) {
            addCriterion("car_direction >=", value, "carDirection");
            return (Criteria) this;
        }

        public Criteria andCarDirectionLessThan(Byte value) {
            addCriterion("car_direction <", value, "carDirection");
            return (Criteria) this;
        }

        public Criteria andCarDirectionLessThanOrEqualTo(Byte value) {
            addCriterion("car_direction <=", value, "carDirection");
            return (Criteria) this;
        }

        public Criteria andCarDirectionIn(List<Byte> values) {
            addCriterion("car_direction in", values, "carDirection");
            return (Criteria) this;
        }

        public Criteria andCarDirectionNotIn(List<Byte> values) {
            addCriterion("car_direction not in", values, "carDirection");
            return (Criteria) this;
        }

        public Criteria andCarDirectionBetween(Byte value1, Byte value2) {
            addCriterion("car_direction between", value1, value2, "carDirection");
            return (Criteria) this;
        }

        public Criteria andCarDirectionNotBetween(Byte value1, Byte value2) {
            addCriterion("car_direction not between", value1, value2, "carDirection");
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andFullImageIsNull() {
            addCriterion("full_image is null");
            return (Criteria) this;
        }

        public Criteria andFullImageIsNotNull() {
            addCriterion("full_image is not null");
            return (Criteria) this;
        }

        public Criteria andFullImageEqualTo(String value) {
            addCriterion("full_image =", value, "fullImage");
            return (Criteria) this;
        }

        public Criteria andFullImageNotEqualTo(String value) {
            addCriterion("full_image <>", value, "fullImage");
            return (Criteria) this;
        }

        public Criteria andFullImageGreaterThan(String value) {
            addCriterion("full_image >", value, "fullImage");
            return (Criteria) this;
        }

        public Criteria andFullImageGreaterThanOrEqualTo(String value) {
            addCriterion("full_image >=", value, "fullImage");
            return (Criteria) this;
        }

        public Criteria andFullImageLessThan(String value) {
            addCriterion("full_image <", value, "fullImage");
            return (Criteria) this;
        }

        public Criteria andFullImageLessThanOrEqualTo(String value) {
            addCriterion("full_image <=", value, "fullImage");
            return (Criteria) this;
        }

        public Criteria andFullImageLike(String value) {
            addCriterion("full_image like", value, "fullImage");
            return (Criteria) this;
        }

        public Criteria andFullImageNotLike(String value) {
            addCriterion("full_image not like", value, "fullImage");
            return (Criteria) this;
        }

        public Criteria andFullImageIn(List<String> values) {
            addCriterion("full_image in", values, "fullImage");
            return (Criteria) this;
        }

        public Criteria andFullImageNotIn(List<String> values) {
            addCriterion("full_image not in", values, "fullImage");
            return (Criteria) this;
        }

        public Criteria andFullImageBetween(String value1, String value2) {
            addCriterion("full_image between", value1, value2, "fullImage");
            return (Criteria) this;
        }

        public Criteria andFullImageNotBetween(String value1, String value2) {
            addCriterion("full_image not between", value1, value2, "fullImage");
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