package com.windaka.suizhi.webapi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CaptureAlarmAttentionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CaptureAlarmAttentionExample() {
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

        public Criteria andAttentionIdIsNull() {
            addCriterion("attention_id is null");
            return (Criteria) this;
        }

        public Criteria andAttentionIdIsNotNull() {
            addCriterion("attention_id is not null");
            return (Criteria) this;
        }

        public Criteria andAttentionIdEqualTo(Integer value) {
            addCriterion("attention_id =", value, "attentionId");
            return (Criteria) this;
        }

        public Criteria andAttentionIdNotEqualTo(Integer value) {
            addCriterion("attention_id <>", value, "attentionId");
            return (Criteria) this;
        }

        public Criteria andAttentionIdGreaterThan(Integer value) {
            addCriterion("attention_id >", value, "attentionId");
            return (Criteria) this;
        }

        public Criteria andAttentionIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("attention_id >=", value, "attentionId");
            return (Criteria) this;
        }

        public Criteria andAttentionIdLessThan(Integer value) {
            addCriterion("attention_id <", value, "attentionId");
            return (Criteria) this;
        }

        public Criteria andAttentionIdLessThanOrEqualTo(Integer value) {
            addCriterion("attention_id <=", value, "attentionId");
            return (Criteria) this;
        }

        public Criteria andAttentionIdIn(List<Integer> values) {
            addCriterion("attention_id in", values, "attentionId");
            return (Criteria) this;
        }

        public Criteria andAttentionIdNotIn(List<Integer> values) {
            addCriterion("attention_id not in", values, "attentionId");
            return (Criteria) this;
        }

        public Criteria andAttentionIdBetween(Integer value1, Integer value2) {
            addCriterion("attention_id between", value1, value2, "attentionId");
            return (Criteria) this;
        }

        public Criteria andAttentionIdNotBetween(Integer value1, Integer value2) {
            addCriterion("attention_id not between", value1, value2, "attentionId");
            return (Criteria) this;
        }

        public Criteria andDurationIsNull() {
            addCriterion("duration is null");
            return (Criteria) this;
        }

        public Criteria andDurationIsNotNull() {
            addCriterion("duration is not null");
            return (Criteria) this;
        }

        public Criteria andDurationEqualTo(String value) {
            addCriterion("duration =", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotEqualTo(String value) {
            addCriterion("duration <>", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThan(String value) {
            addCriterion("duration >", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationGreaterThanOrEqualTo(String value) {
            addCriterion("duration >=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThan(String value) {
            addCriterion("duration <", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLessThanOrEqualTo(String value) {
            addCriterion("duration <=", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationLike(String value) {
            addCriterion("duration like", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotLike(String value) {
            addCriterion("duration not like", value, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationIn(List<String> values) {
            addCriterion("duration in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotIn(List<String> values) {
            addCriterion("duration not in", values, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationBetween(String value1, String value2) {
            addCriterion("duration between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andDurationNotBetween(String value1, String value2) {
            addCriterion("duration not between", value1, value2, "duration");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNameIsNull() {
            addCriterion("level_name is null");
            return (Criteria) this;
        }

        public Criteria andLevelNameIsNotNull() {
            addCriterion("level_name is not null");
            return (Criteria) this;
        }

        public Criteria andLevelNameEqualTo(String value) {
            addCriterion("level_name =", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotEqualTo(String value) {
            addCriterion("level_name <>", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameGreaterThan(String value) {
            addCriterion("level_name >", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameGreaterThanOrEqualTo(String value) {
            addCriterion("level_name >=", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameLessThan(String value) {
            addCriterion("level_name <", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameLessThanOrEqualTo(String value) {
            addCriterion("level_name <=", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameLike(String value) {
            addCriterion("level_name like", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotLike(String value) {
            addCriterion("level_name not like", value, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameIn(List<String> values) {
            addCriterion("level_name in", values, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotIn(List<String> values) {
            addCriterion("level_name not in", values, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameBetween(String value1, String value2) {
            addCriterion("level_name between", value1, value2, "levelName");
            return (Criteria) this;
        }

        public Criteria andLevelNameNotBetween(String value1, String value2) {
            addCriterion("level_name not between", value1, value2, "levelName");
            return (Criteria) this;
        }

        public Criteria andAlarmTimeIsNull() {
            addCriterion("alarm_time is null");
            return (Criteria) this;
        }

        public Criteria andAlarmTimeIsNotNull() {
            addCriterion("alarm_time is not null");
            return (Criteria) this;
        }

        public Criteria andAlarmTimeEqualTo(Date value) {
            addCriterion("alarm_time =", value, "alarmTime");
            return (Criteria) this;
        }

        public Criteria andAlarmTimeNotEqualTo(Date value) {
            addCriterion("alarm_time <>", value, "alarmTime");
            return (Criteria) this;
        }

        public Criteria andAlarmTimeGreaterThan(Date value) {
            addCriterion("alarm_time >", value, "alarmTime");
            return (Criteria) this;
        }

        public Criteria andAlarmTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("alarm_time >=", value, "alarmTime");
            return (Criteria) this;
        }

        public Criteria andAlarmTimeLessThan(Date value) {
            addCriterion("alarm_time <", value, "alarmTime");
            return (Criteria) this;
        }

        public Criteria andAlarmTimeLessThanOrEqualTo(Date value) {
            addCriterion("alarm_time <=", value, "alarmTime");
            return (Criteria) this;
        }

        public Criteria andAlarmTimeIn(List<Date> values) {
            addCriterion("alarm_time in", values, "alarmTime");
            return (Criteria) this;
        }

        public Criteria andAlarmTimeNotIn(List<Date> values) {
            addCriterion("alarm_time not in", values, "alarmTime");
            return (Criteria) this;
        }

        public Criteria andAlarmTimeBetween(Date value1, Date value2) {
            addCriterion("alarm_time between", value1, value2, "alarmTime");
            return (Criteria) this;
        }

        public Criteria andAlarmTimeNotBetween(Date value1, Date value2) {
            addCriterion("alarm_time not between", value1, value2, "alarmTime");
            return (Criteria) this;
        }

        public Criteria andLastCaptureTimeIsNull() {
            addCriterion("last_capture_time is null");
            return (Criteria) this;
        }

        public Criteria andLastCaptureTimeIsNotNull() {
            addCriterion("last_capture_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastCaptureTimeEqualTo(Date value) {
            addCriterion("last_capture_time =", value, "lastCaptureTime");
            return (Criteria) this;
        }

        public Criteria andLastCaptureTimeNotEqualTo(Date value) {
            addCriterion("last_capture_time <>", value, "lastCaptureTime");
            return (Criteria) this;
        }

        public Criteria andLastCaptureTimeGreaterThan(Date value) {
            addCriterion("last_capture_time >", value, "lastCaptureTime");
            return (Criteria) this;
        }

        public Criteria andLastCaptureTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_capture_time >=", value, "lastCaptureTime");
            return (Criteria) this;
        }

        public Criteria andLastCaptureTimeLessThan(Date value) {
            addCriterion("last_capture_time <", value, "lastCaptureTime");
            return (Criteria) this;
        }

        public Criteria andLastCaptureTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_capture_time <=", value, "lastCaptureTime");
            return (Criteria) this;
        }

        public Criteria andLastCaptureTimeIn(List<Date> values) {
            addCriterion("last_capture_time in", values, "lastCaptureTime");
            return (Criteria) this;
        }

        public Criteria andLastCaptureTimeNotIn(List<Date> values) {
            addCriterion("last_capture_time not in", values, "lastCaptureTime");
            return (Criteria) this;
        }

        public Criteria andLastCaptureTimeBetween(Date value1, Date value2) {
            addCriterion("last_capture_time between", value1, value2, "lastCaptureTime");
            return (Criteria) this;
        }

        public Criteria andLastCaptureTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_capture_time not between", value1, value2, "lastCaptureTime");
            return (Criteria) this;
        }

        public Criteria andCaptureLocationIsNull() {
            addCriterion("capture_location is null");
            return (Criteria) this;
        }

        public Criteria andCaptureLocationIsNotNull() {
            addCriterion("capture_location is not null");
            return (Criteria) this;
        }

        public Criteria andCaptureLocationEqualTo(String value) {
            addCriterion("capture_location =", value, "captureLocation");
            return (Criteria) this;
        }

        public Criteria andCaptureLocationNotEqualTo(String value) {
            addCriterion("capture_location <>", value, "captureLocation");
            return (Criteria) this;
        }

        public Criteria andCaptureLocationGreaterThan(String value) {
            addCriterion("capture_location >", value, "captureLocation");
            return (Criteria) this;
        }

        public Criteria andCaptureLocationGreaterThanOrEqualTo(String value) {
            addCriterion("capture_location >=", value, "captureLocation");
            return (Criteria) this;
        }

        public Criteria andCaptureLocationLessThan(String value) {
            addCriterion("capture_location <", value, "captureLocation");
            return (Criteria) this;
        }

        public Criteria andCaptureLocationLessThanOrEqualTo(String value) {
            addCriterion("capture_location <=", value, "captureLocation");
            return (Criteria) this;
        }

        public Criteria andCaptureLocationLike(String value) {
            addCriterion("capture_location like", value, "captureLocation");
            return (Criteria) this;
        }

        public Criteria andCaptureLocationNotLike(String value) {
            addCriterion("capture_location not like", value, "captureLocation");
            return (Criteria) this;
        }

        public Criteria andCaptureLocationIn(List<String> values) {
            addCriterion("capture_location in", values, "captureLocation");
            return (Criteria) this;
        }

        public Criteria andCaptureLocationNotIn(List<String> values) {
            addCriterion("capture_location not in", values, "captureLocation");
            return (Criteria) this;
        }

        public Criteria andCaptureLocationBetween(String value1, String value2) {
            addCriterion("capture_location between", value1, value2, "captureLocation");
            return (Criteria) this;
        }

        public Criteria andCaptureLocationNotBetween(String value1, String value2) {
            addCriterion("capture_location not between", value1, value2, "captureLocation");
            return (Criteria) this;
        }

        public Criteria andCaptureDeviceIsNull() {
            addCriterion("capture_device is null");
            return (Criteria) this;
        }

        public Criteria andCaptureDeviceIsNotNull() {
            addCriterion("capture_device is not null");
            return (Criteria) this;
        }

        public Criteria andCaptureDeviceEqualTo(String value) {
            addCriterion("capture_device =", value, "captureDevice");
            return (Criteria) this;
        }

        public Criteria andCaptureDeviceNotEqualTo(String value) {
            addCriterion("capture_device <>", value, "captureDevice");
            return (Criteria) this;
        }

        public Criteria andCaptureDeviceGreaterThan(String value) {
            addCriterion("capture_device >", value, "captureDevice");
            return (Criteria) this;
        }

        public Criteria andCaptureDeviceGreaterThanOrEqualTo(String value) {
            addCriterion("capture_device >=", value, "captureDevice");
            return (Criteria) this;
        }

        public Criteria andCaptureDeviceLessThan(String value) {
            addCriterion("capture_device <", value, "captureDevice");
            return (Criteria) this;
        }

        public Criteria andCaptureDeviceLessThanOrEqualTo(String value) {
            addCriterion("capture_device <=", value, "captureDevice");
            return (Criteria) this;
        }

        public Criteria andCaptureDeviceLike(String value) {
            addCriterion("capture_device like", value, "captureDevice");
            return (Criteria) this;
        }

        public Criteria andCaptureDeviceNotLike(String value) {
            addCriterion("capture_device not like", value, "captureDevice");
            return (Criteria) this;
        }

        public Criteria andCaptureDeviceIn(List<String> values) {
            addCriterion("capture_device in", values, "captureDevice");
            return (Criteria) this;
        }

        public Criteria andCaptureDeviceNotIn(List<String> values) {
            addCriterion("capture_device not in", values, "captureDevice");
            return (Criteria) this;
        }

        public Criteria andCaptureDeviceBetween(String value1, String value2) {
            addCriterion("capture_device between", value1, value2, "captureDevice");
            return (Criteria) this;
        }

        public Criteria andCaptureDeviceNotBetween(String value1, String value2) {
            addCriterion("capture_device not between", value1, value2, "captureDevice");
            return (Criteria) this;
        }

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
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

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andIdNoIsNull() {
            addCriterion("id_no is null");
            return (Criteria) this;
        }

        public Criteria andIdNoIsNotNull() {
            addCriterion("id_no is not null");
            return (Criteria) this;
        }

        public Criteria andIdNoEqualTo(String value) {
            addCriterion("id_no =", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotEqualTo(String value) {
            addCriterion("id_no <>", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoGreaterThan(String value) {
            addCriterion("id_no >", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoGreaterThanOrEqualTo(String value) {
            addCriterion("id_no >=", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLessThan(String value) {
            addCriterion("id_no <", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLessThanOrEqualTo(String value) {
            addCriterion("id_no <=", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoLike(String value) {
            addCriterion("id_no like", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotLike(String value) {
            addCriterion("id_no not like", value, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoIn(List<String> values) {
            addCriterion("id_no in", values, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotIn(List<String> values) {
            addCriterion("id_no not in", values, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoBetween(String value1, String value2) {
            addCriterion("id_no between", value1, value2, "idNo");
            return (Criteria) this;
        }

        public Criteria andIdNoNotBetween(String value1, String value2) {
            addCriterion("id_no not between", value1, value2, "idNo");
            return (Criteria) this;
        }

        public Criteria andPicUrlIsNull() {
            addCriterion("pic_url is null");
            return (Criteria) this;
        }

        public Criteria andPicUrlIsNotNull() {
            addCriterion("pic_url is not null");
            return (Criteria) this;
        }

        public Criteria andPicUrlEqualTo(String value) {
            addCriterion("pic_url =", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotEqualTo(String value) {
            addCriterion("pic_url <>", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlGreaterThan(String value) {
            addCriterion("pic_url >", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlGreaterThanOrEqualTo(String value) {
            addCriterion("pic_url >=", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlLessThan(String value) {
            addCriterion("pic_url <", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlLessThanOrEqualTo(String value) {
            addCriterion("pic_url <=", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlLike(String value) {
            addCriterion("pic_url like", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotLike(String value) {
            addCriterion("pic_url not like", value, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlIn(List<String> values) {
            addCriterion("pic_url in", values, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotIn(List<String> values) {
            addCriterion("pic_url not in", values, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlBetween(String value1, String value2) {
            addCriterion("pic_url between", value1, value2, "picUrl");
            return (Criteria) this;
        }

        public Criteria andPicUrlNotBetween(String value1, String value2) {
            addCriterion("pic_url not between", value1, value2, "picUrl");
            return (Criteria) this;
        }

        public Criteria andAttentionCodeIsNull() {
            addCriterion("attention_code is null");
            return (Criteria) this;
        }

        public Criteria andAttentionCodeIsNotNull() {
            addCriterion("attention_code is not null");
            return (Criteria) this;
        }

        public Criteria andAttentionCodeEqualTo(String value) {
            addCriterion("attention_code =", value, "attentionCode");
            return (Criteria) this;
        }

        public Criteria andAttentionCodeNotEqualTo(String value) {
            addCriterion("attention_code <>", value, "attentionCode");
            return (Criteria) this;
        }

        public Criteria andAttentionCodeGreaterThan(String value) {
            addCriterion("attention_code >", value, "attentionCode");
            return (Criteria) this;
        }

        public Criteria andAttentionCodeGreaterThanOrEqualTo(String value) {
            addCriterion("attention_code >=", value, "attentionCode");
            return (Criteria) this;
        }

        public Criteria andAttentionCodeLessThan(String value) {
            addCriterion("attention_code <", value, "attentionCode");
            return (Criteria) this;
        }

        public Criteria andAttentionCodeLessThanOrEqualTo(String value) {
            addCriterion("attention_code <=", value, "attentionCode");
            return (Criteria) this;
        }

        public Criteria andAttentionCodeLike(String value) {
            addCriterion("attention_code like", value, "attentionCode");
            return (Criteria) this;
        }

        public Criteria andAttentionCodeNotLike(String value) {
            addCriterion("attention_code not like", value, "attentionCode");
            return (Criteria) this;
        }

        public Criteria andAttentionCodeIn(List<String> values) {
            addCriterion("attention_code in", values, "attentionCode");
            return (Criteria) this;
        }

        public Criteria andAttentionCodeNotIn(List<String> values) {
            addCriterion("attention_code not in", values, "attentionCode");
            return (Criteria) this;
        }

        public Criteria andAttentionCodeBetween(String value1, String value2) {
            addCriterion("attention_code between", value1, value2, "attentionCode");
            return (Criteria) this;
        }

        public Criteria andAttentionCodeNotBetween(String value1, String value2) {
            addCriterion("attention_code not between", value1, value2, "attentionCode");
            return (Criteria) this;
        }

        public Criteria andAttentionNameIsNull() {
            addCriterion("attention_name is null");
            return (Criteria) this;
        }

        public Criteria andAttentionNameIsNotNull() {
            addCriterion("attention_name is not null");
            return (Criteria) this;
        }

        public Criteria andAttentionNameEqualTo(String value) {
            addCriterion("attention_name =", value, "attentionName");
            return (Criteria) this;
        }

        public Criteria andAttentionNameNotEqualTo(String value) {
            addCriterion("attention_name <>", value, "attentionName");
            return (Criteria) this;
        }

        public Criteria andAttentionNameGreaterThan(String value) {
            addCriterion("attention_name >", value, "attentionName");
            return (Criteria) this;
        }

        public Criteria andAttentionNameGreaterThanOrEqualTo(String value) {
            addCriterion("attention_name >=", value, "attentionName");
            return (Criteria) this;
        }

        public Criteria andAttentionNameLessThan(String value) {
            addCriterion("attention_name <", value, "attentionName");
            return (Criteria) this;
        }

        public Criteria andAttentionNameLessThanOrEqualTo(String value) {
            addCriterion("attention_name <=", value, "attentionName");
            return (Criteria) this;
        }

        public Criteria andAttentionNameLike(String value) {
            addCriterion("attention_name like", value, "attentionName");
            return (Criteria) this;
        }

        public Criteria andAttentionNameNotLike(String value) {
            addCriterion("attention_name not like", value, "attentionName");
            return (Criteria) this;
        }

        public Criteria andAttentionNameIn(List<String> values) {
            addCriterion("attention_name in", values, "attentionName");
            return (Criteria) this;
        }

        public Criteria andAttentionNameNotIn(List<String> values) {
            addCriterion("attention_name not in", values, "attentionName");
            return (Criteria) this;
        }

        public Criteria andAttentionNameBetween(String value1, String value2) {
            addCriterion("attention_name between", value1, value2, "attentionName");
            return (Criteria) this;
        }

        public Criteria andAttentionNameNotBetween(String value1, String value2) {
            addCriterion("attention_name not between", value1, value2, "attentionName");
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

        public Criteria andHandelStatusEqualTo(Boolean value) {
            addCriterion("handel_status =", value, "handelStatus");
            return (Criteria) this;
        }

        public Criteria andHandelStatusNotEqualTo(Boolean value) {
            addCriterion("handel_status <>", value, "handelStatus");
            return (Criteria) this;
        }

        public Criteria andHandelStatusGreaterThan(Boolean value) {
            addCriterion("handel_status >", value, "handelStatus");
            return (Criteria) this;
        }

        public Criteria andHandelStatusGreaterThanOrEqualTo(Boolean value) {
            addCriterion("handel_status >=", value, "handelStatus");
            return (Criteria) this;
        }

        public Criteria andHandelStatusLessThan(Boolean value) {
            addCriterion("handel_status <", value, "handelStatus");
            return (Criteria) this;
        }

        public Criteria andHandelStatusLessThanOrEqualTo(Boolean value) {
            addCriterion("handel_status <=", value, "handelStatus");
            return (Criteria) this;
        }

        public Criteria andHandelStatusIn(List<Boolean> values) {
            addCriterion("handel_status in", values, "handelStatus");
            return (Criteria) this;
        }

        public Criteria andHandelStatusNotIn(List<Boolean> values) {
            addCriterion("handel_status not in", values, "handelStatus");
            return (Criteria) this;
        }

        public Criteria andHandelStatusBetween(Boolean value1, Boolean value2) {
            addCriterion("handel_status between", value1, value2, "handelStatus");
            return (Criteria) this;
        }

        public Criteria andHandelStatusNotBetween(Boolean value1, Boolean value2) {
            addCriterion("handel_status not between", value1, value2, "handelStatus");
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