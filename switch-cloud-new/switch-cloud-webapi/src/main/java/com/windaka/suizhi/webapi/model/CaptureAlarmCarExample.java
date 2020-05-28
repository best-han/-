package com.windaka.suizhi.webapi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CaptureAlarmCarExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CaptureAlarmCarExample() {
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

        public Criteria andDeviceTypeEqualTo(Short value) {
            addCriterion("device_type =", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotEqualTo(Short value) {
            addCriterion("device_type <>", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeGreaterThan(Short value) {
            addCriterion("device_type >", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("device_type >=", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLessThan(Short value) {
            addCriterion("device_type <", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeLessThanOrEqualTo(Short value) {
            addCriterion("device_type <=", value, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeIn(List<Short> values) {
            addCriterion("device_type in", values, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotIn(List<Short> values) {
            addCriterion("device_type not in", values, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeBetween(Short value1, Short value2) {
            addCriterion("device_type between", value1, value2, "deviceType");
            return (Criteria) this;
        }

        public Criteria andDeviceTypeNotBetween(Short value1, Short value2) {
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

        public Criteria andCapSmallImageIsNull() {
            addCriterion("cap_small_image is null");
            return (Criteria) this;
        }

        public Criteria andCapSmallImageIsNotNull() {
            addCriterion("cap_small_image is not null");
            return (Criteria) this;
        }

        public Criteria andCapSmallImageEqualTo(String value) {
            addCriterion("cap_small_image =", value, "capSmallImage");
            return (Criteria) this;
        }

        public Criteria andCapSmallImageNotEqualTo(String value) {
            addCriterion("cap_small_image <>", value, "capSmallImage");
            return (Criteria) this;
        }

        public Criteria andCapSmallImageGreaterThan(String value) {
            addCriterion("cap_small_image >", value, "capSmallImage");
            return (Criteria) this;
        }

        public Criteria andCapSmallImageGreaterThanOrEqualTo(String value) {
            addCriterion("cap_small_image >=", value, "capSmallImage");
            return (Criteria) this;
        }

        public Criteria andCapSmallImageLessThan(String value) {
            addCriterion("cap_small_image <", value, "capSmallImage");
            return (Criteria) this;
        }

        public Criteria andCapSmallImageLessThanOrEqualTo(String value) {
            addCriterion("cap_small_image <=", value, "capSmallImage");
            return (Criteria) this;
        }

        public Criteria andCapSmallImageLike(String value) {
            addCriterion("cap_small_image like", value, "capSmallImage");
            return (Criteria) this;
        }

        public Criteria andCapSmallImageNotLike(String value) {
            addCriterion("cap_small_image not like", value, "capSmallImage");
            return (Criteria) this;
        }

        public Criteria andCapSmallImageIn(List<String> values) {
            addCriterion("cap_small_image in", values, "capSmallImage");
            return (Criteria) this;
        }

        public Criteria andCapSmallImageNotIn(List<String> values) {
            addCriterion("cap_small_image not in", values, "capSmallImage");
            return (Criteria) this;
        }

        public Criteria andCapSmallImageBetween(String value1, String value2) {
            addCriterion("cap_small_image between", value1, value2, "capSmallImage");
            return (Criteria) this;
        }

        public Criteria andCapSmallImageNotBetween(String value1, String value2) {
            addCriterion("cap_small_image not between", value1, value2, "capSmallImage");
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

        public Criteria andDetailIdIsNull() {
            addCriterion("detail_id is null");
            return (Criteria) this;
        }

        public Criteria andDetailIdIsNotNull() {
            addCriterion("detail_id is not null");
            return (Criteria) this;
        }

        public Criteria andDetailIdEqualTo(String value) {
            addCriterion("detail_id =", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotEqualTo(String value) {
            addCriterion("detail_id <>", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdGreaterThan(String value) {
            addCriterion("detail_id >", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdGreaterThanOrEqualTo(String value) {
            addCriterion("detail_id >=", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdLessThan(String value) {
            addCriterion("detail_id <", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdLessThanOrEqualTo(String value) {
            addCriterion("detail_id <=", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdLike(String value) {
            addCriterion("detail_id like", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotLike(String value) {
            addCriterion("detail_id not like", value, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdIn(List<String> values) {
            addCriterion("detail_id in", values, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotIn(List<String> values) {
            addCriterion("detail_id not in", values, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdBetween(String value1, String value2) {
            addCriterion("detail_id between", value1, value2, "detailId");
            return (Criteria) this;
        }

        public Criteria andDetailIdNotBetween(String value1, String value2) {
            addCriterion("detail_id not between", value1, value2, "detailId");
            return (Criteria) this;
        }

        public Criteria andCaptureIdIsNull() {
            addCriterion("capture_id is null");
            return (Criteria) this;
        }

        public Criteria andCaptureIdIsNotNull() {
            addCriterion("capture_id is not null");
            return (Criteria) this;
        }

        public Criteria andCaptureIdEqualTo(Integer value) {
            addCriterion("capture_id =", value, "captureId");
            return (Criteria) this;
        }

        public Criteria andCaptureIdNotEqualTo(Integer value) {
            addCriterion("capture_id <>", value, "captureId");
            return (Criteria) this;
        }

        public Criteria andCaptureIdGreaterThan(Integer value) {
            addCriterion("capture_id >", value, "captureId");
            return (Criteria) this;
        }

        public Criteria andCaptureIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("capture_id >=", value, "captureId");
            return (Criteria) this;
        }

        public Criteria andCaptureIdLessThan(Integer value) {
            addCriterion("capture_id <", value, "captureId");
            return (Criteria) this;
        }

        public Criteria andCaptureIdLessThanOrEqualTo(Integer value) {
            addCriterion("capture_id <=", value, "captureId");
            return (Criteria) this;
        }

        public Criteria andCaptureIdIn(List<Integer> values) {
            addCriterion("capture_id in", values, "captureId");
            return (Criteria) this;
        }

        public Criteria andCaptureIdNotIn(List<Integer> values) {
            addCriterion("capture_id not in", values, "captureId");
            return (Criteria) this;
        }

        public Criteria andCaptureIdBetween(Integer value1, Integer value2) {
            addCriterion("capture_id between", value1, value2, "captureId");
            return (Criteria) this;
        }

        public Criteria andCaptureIdNotBetween(Integer value1, Integer value2) {
            addCriterion("capture_id not between", value1, value2, "captureId");
            return (Criteria) this;
        }

        public Criteria andDetailLevelIsNull() {
            addCriterion("detail_level is null");
            return (Criteria) this;
        }

        public Criteria andDetailLevelIsNotNull() {
            addCriterion("detail_level is not null");
            return (Criteria) this;
        }

        public Criteria andDetailLevelEqualTo(Short value) {
            addCriterion("detail_level =", value, "detailLevel");
            return (Criteria) this;
        }

        public Criteria andDetailLevelNotEqualTo(Short value) {
            addCriterion("detail_level <>", value, "detailLevel");
            return (Criteria) this;
        }

        public Criteria andDetailLevelGreaterThan(Short value) {
            addCriterion("detail_level >", value, "detailLevel");
            return (Criteria) this;
        }

        public Criteria andDetailLevelGreaterThanOrEqualTo(Short value) {
            addCriterion("detail_level >=", value, "detailLevel");
            return (Criteria) this;
        }

        public Criteria andDetailLevelLessThan(Short value) {
            addCriterion("detail_level <", value, "detailLevel");
            return (Criteria) this;
        }

        public Criteria andDetailLevelLessThanOrEqualTo(Short value) {
            addCriterion("detail_level <=", value, "detailLevel");
            return (Criteria) this;
        }

        public Criteria andDetailLevelIn(List<Short> values) {
            addCriterion("detail_level in", values, "detailLevel");
            return (Criteria) this;
        }

        public Criteria andDetailLevelNotIn(List<Short> values) {
            addCriterion("detail_level not in", values, "detailLevel");
            return (Criteria) this;
        }

        public Criteria andDetailLevelBetween(Short value1, Short value2) {
            addCriterion("detail_level between", value1, value2, "detailLevel");
            return (Criteria) this;
        }

        public Criteria andDetailLevelNotBetween(Short value1, Short value2) {
            addCriterion("detail_level not between", value1, value2, "detailLevel");
            return (Criteria) this;
        }

        public Criteria andDetailLevelNameIsNull() {
            addCriterion("detail_level_name is null");
            return (Criteria) this;
        }

        public Criteria andDetailLevelNameIsNotNull() {
            addCriterion("detail_level_name is not null");
            return (Criteria) this;
        }

        public Criteria andDetailLevelNameEqualTo(String value) {
            addCriterion("detail_level_name =", value, "detailLevelName");
            return (Criteria) this;
        }

        public Criteria andDetailLevelNameNotEqualTo(String value) {
            addCriterion("detail_level_name <>", value, "detailLevelName");
            return (Criteria) this;
        }

        public Criteria andDetailLevelNameGreaterThan(String value) {
            addCriterion("detail_level_name >", value, "detailLevelName");
            return (Criteria) this;
        }

        public Criteria andDetailLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("detail_level_name >=", value, "detailLevelName");
            return (Criteria) this;
        }

        public Criteria andDetailLevelNameLessThan(String value) {
            addCriterion("detail_level_name <", value, "detailLevelName");
            return (Criteria) this;
        }

        public Criteria andDetailLevelNameLessThanOrEqualTo(String value) {
            addCriterion("detail_level_name <=", value, "detailLevelName");
            return (Criteria) this;
        }

        public Criteria andDetailLevelNameLike(String value) {
            addCriterion("detail_level_name like", value, "detailLevelName");
            return (Criteria) this;
        }

        public Criteria andDetailLevelNameNotLike(String value) {
            addCriterion("detail_level_name not like", value, "detailLevelName");
            return (Criteria) this;
        }

        public Criteria andDetailLevelNameIn(List<String> values) {
            addCriterion("detail_level_name in", values, "detailLevelName");
            return (Criteria) this;
        }

        public Criteria andDetailLevelNameNotIn(List<String> values) {
            addCriterion("detail_level_name not in", values, "detailLevelName");
            return (Criteria) this;
        }

        public Criteria andDetailLevelNameBetween(String value1, String value2) {
            addCriterion("detail_level_name between", value1, value2, "detailLevelName");
            return (Criteria) this;
        }

        public Criteria andDetailLevelNameNotBetween(String value1, String value2) {
            addCriterion("detail_level_name not between", value1, value2, "detailLevelName");
            return (Criteria) this;
        }

        public Criteria andDetailReasonIsNull() {
            addCriterion("detail_reason is null");
            return (Criteria) this;
        }

        public Criteria andDetailReasonIsNotNull() {
            addCriterion("detail_reason is not null");
            return (Criteria) this;
        }

        public Criteria andDetailReasonEqualTo(String value) {
            addCriterion("detail_reason =", value, "detailReason");
            return (Criteria) this;
        }

        public Criteria andDetailReasonNotEqualTo(String value) {
            addCriterion("detail_reason <>", value, "detailReason");
            return (Criteria) this;
        }

        public Criteria andDetailReasonGreaterThan(String value) {
            addCriterion("detail_reason >", value, "detailReason");
            return (Criteria) this;
        }

        public Criteria andDetailReasonGreaterThanOrEqualTo(String value) {
            addCriterion("detail_reason >=", value, "detailReason");
            return (Criteria) this;
        }

        public Criteria andDetailReasonLessThan(String value) {
            addCriterion("detail_reason <", value, "detailReason");
            return (Criteria) this;
        }

        public Criteria andDetailReasonLessThanOrEqualTo(String value) {
            addCriterion("detail_reason <=", value, "detailReason");
            return (Criteria) this;
        }

        public Criteria andDetailReasonLike(String value) {
            addCriterion("detail_reason like", value, "detailReason");
            return (Criteria) this;
        }

        public Criteria andDetailReasonNotLike(String value) {
            addCriterion("detail_reason not like", value, "detailReason");
            return (Criteria) this;
        }

        public Criteria andDetailReasonIn(List<String> values) {
            addCriterion("detail_reason in", values, "detailReason");
            return (Criteria) this;
        }

        public Criteria andDetailReasonNotIn(List<String> values) {
            addCriterion("detail_reason not in", values, "detailReason");
            return (Criteria) this;
        }

        public Criteria andDetailReasonBetween(String value1, String value2) {
            addCriterion("detail_reason between", value1, value2, "detailReason");
            return (Criteria) this;
        }

        public Criteria andDetailReasonNotBetween(String value1, String value2) {
            addCriterion("detail_reason not between", value1, value2, "detailReason");
            return (Criteria) this;
        }

        public Criteria andGroupCodeIsNull() {
            addCriterion("group_code is null");
            return (Criteria) this;
        }

        public Criteria andGroupCodeIsNotNull() {
            addCriterion("group_code is not null");
            return (Criteria) this;
        }

        public Criteria andGroupCodeEqualTo(String value) {
            addCriterion("group_code =", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeNotEqualTo(String value) {
            addCriterion("group_code <>", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeGreaterThan(String value) {
            addCriterion("group_code >", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeGreaterThanOrEqualTo(String value) {
            addCriterion("group_code >=", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeLessThan(String value) {
            addCriterion("group_code <", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeLessThanOrEqualTo(String value) {
            addCriterion("group_code <=", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeLike(String value) {
            addCriterion("group_code like", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeNotLike(String value) {
            addCriterion("group_code not like", value, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeIn(List<String> values) {
            addCriterion("group_code in", values, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeNotIn(List<String> values) {
            addCriterion("group_code not in", values, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeBetween(String value1, String value2) {
            addCriterion("group_code between", value1, value2, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupCodeNotBetween(String value1, String value2) {
            addCriterion("group_code not between", value1, value2, "groupCode");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNull() {
            addCriterion("group_name is null");
            return (Criteria) this;
        }

        public Criteria andGroupNameIsNotNull() {
            addCriterion("group_name is not null");
            return (Criteria) this;
        }

        public Criteria andGroupNameEqualTo(String value) {
            addCriterion("group_name =", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotEqualTo(String value) {
            addCriterion("group_name <>", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThan(String value) {
            addCriterion("group_name >", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameGreaterThanOrEqualTo(String value) {
            addCriterion("group_name >=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThan(String value) {
            addCriterion("group_name <", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLessThanOrEqualTo(String value) {
            addCriterion("group_name <=", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameLike(String value) {
            addCriterion("group_name like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotLike(String value) {
            addCriterion("group_name not like", value, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameIn(List<String> values) {
            addCriterion("group_name in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotIn(List<String> values) {
            addCriterion("group_name not in", values, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameBetween(String value1, String value2) {
            addCriterion("group_name between", value1, value2, "groupName");
            return (Criteria) this;
        }

        public Criteria andGroupNameNotBetween(String value1, String value2) {
            addCriterion("group_name not between", value1, value2, "groupName");
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

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(String value) {
            addCriterion("create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(String value) {
            addCriterion("create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(String value) {
            addCriterion("create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(String value) {
            addCriterion("create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(String value) {
            addCriterion("create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLike(String value) {
            addCriterion("create_user_id like", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotLike(String value) {
            addCriterion("create_user_id not like", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<String> values) {
            addCriterion("create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<String> values) {
            addCriterion("create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(String value1, String value2) {
            addCriterion("create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(String value1, String value2) {
            addCriterion("create_user_id not between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNull() {
            addCriterion("update_user is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIsNotNull() {
            addCriterion("update_user is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserEqualTo(String value) {
            addCriterion("update_user =", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotEqualTo(String value) {
            addCriterion("update_user <>", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThan(String value) {
            addCriterion("update_user >", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserGreaterThanOrEqualTo(String value) {
            addCriterion("update_user >=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThan(String value) {
            addCriterion("update_user <", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLessThanOrEqualTo(String value) {
            addCriterion("update_user <=", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserLike(String value) {
            addCriterion("update_user like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotLike(String value) {
            addCriterion("update_user not like", value, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIn(List<String> values) {
            addCriterion("update_user in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotIn(List<String> values) {
            addCriterion("update_user not in", values, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserBetween(String value1, String value2) {
            addCriterion("update_user between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserNotBetween(String value1, String value2) {
            addCriterion("update_user not between", value1, value2, "updateUser");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNull() {
            addCriterion("update_user_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNotNull() {
            addCriterion("update_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdEqualTo(String value) {
            addCriterion("update_user_id =", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotEqualTo(String value) {
            addCriterion("update_user_id <>", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThan(String value) {
            addCriterion("update_user_id >", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("update_user_id >=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThan(String value) {
            addCriterion("update_user_id <", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThanOrEqualTo(String value) {
            addCriterion("update_user_id <=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLike(String value) {
            addCriterion("update_user_id like", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotLike(String value) {
            addCriterion("update_user_id not like", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIn(List<String> values) {
            addCriterion("update_user_id in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotIn(List<String> values) {
            addCriterion("update_user_id not in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdBetween(String value1, String value2) {
            addCriterion("update_user_id between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotBetween(String value1, String value2) {
            addCriterion("update_user_id not between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdaetTimeIsNull() {
            addCriterion("updaet_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdaetTimeIsNotNull() {
            addCriterion("updaet_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdaetTimeEqualTo(Date value) {
            addCriterion("updaet_time =", value, "updaetTime");
            return (Criteria) this;
        }

        public Criteria andUpdaetTimeNotEqualTo(Date value) {
            addCriterion("updaet_time <>", value, "updaetTime");
            return (Criteria) this;
        }

        public Criteria andUpdaetTimeGreaterThan(Date value) {
            addCriterion("updaet_time >", value, "updaetTime");
            return (Criteria) this;
        }

        public Criteria andUpdaetTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updaet_time >=", value, "updaetTime");
            return (Criteria) this;
        }

        public Criteria andUpdaetTimeLessThan(Date value) {
            addCriterion("updaet_time <", value, "updaetTime");
            return (Criteria) this;
        }

        public Criteria andUpdaetTimeLessThanOrEqualTo(Date value) {
            addCriterion("updaet_time <=", value, "updaetTime");
            return (Criteria) this;
        }

        public Criteria andUpdaetTimeIn(List<Date> values) {
            addCriterion("updaet_time in", values, "updaetTime");
            return (Criteria) this;
        }

        public Criteria andUpdaetTimeNotIn(List<Date> values) {
            addCriterion("updaet_time not in", values, "updaetTime");
            return (Criteria) this;
        }

        public Criteria andUpdaetTimeBetween(Date value1, Date value2) {
            addCriterion("updaet_time between", value1, value2, "updaetTime");
            return (Criteria) this;
        }

        public Criteria andUpdaetTimeNotBetween(Date value1, Date value2) {
            addCriterion("updaet_time not between", value1, value2, "updaetTime");
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