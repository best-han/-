package com.windaka.suizhi.webapi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BasePersonExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BasePersonExample() {
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

        public Criteria andRoomCodeIsNull() {
            addCriterion("room_code is null");
            return (Criteria) this;
        }

        public Criteria andRoomCodeIsNotNull() {
            addCriterion("room_code is not null");
            return (Criteria) this;
        }

        public Criteria andRoomCodeEqualTo(String value) {
            addCriterion("room_code =", value, "roomCode");
            return (Criteria) this;
        }

        public Criteria andRoomCodeNotEqualTo(String value) {
            addCriterion("room_code <>", value, "roomCode");
            return (Criteria) this;
        }

        public Criteria andRoomCodeGreaterThan(String value) {
            addCriterion("room_code >", value, "roomCode");
            return (Criteria) this;
        }

        public Criteria andRoomCodeGreaterThanOrEqualTo(String value) {
            addCriterion("room_code >=", value, "roomCode");
            return (Criteria) this;
        }

        public Criteria andRoomCodeLessThan(String value) {
            addCriterion("room_code <", value, "roomCode");
            return (Criteria) this;
        }

        public Criteria andRoomCodeLessThanOrEqualTo(String value) {
            addCriterion("room_code <=", value, "roomCode");
            return (Criteria) this;
        }

        public Criteria andRoomCodeLike(String value) {
            addCriterion("room_code like", value, "roomCode");
            return (Criteria) this;
        }

        public Criteria andRoomCodeNotLike(String value) {
            addCriterion("room_code not like", value, "roomCode");
            return (Criteria) this;
        }

        public Criteria andRoomCodeIn(List<String> values) {
            addCriterion("room_code in", values, "roomCode");
            return (Criteria) this;
        }

        public Criteria andRoomCodeNotIn(List<String> values) {
            addCriterion("room_code not in", values, "roomCode");
            return (Criteria) this;
        }

        public Criteria andRoomCodeBetween(String value1, String value2) {
            addCriterion("room_code between", value1, value2, "roomCode");
            return (Criteria) this;
        }

        public Criteria andRoomCodeNotBetween(String value1, String value2) {
            addCriterion("room_code not between", value1, value2, "roomCode");
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Short value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Short value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Short value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Short value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Short value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Short> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Short> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Short value1, Short value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Short value1, Short value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNull() {
            addCriterion("type_name is null");
            return (Criteria) this;
        }

        public Criteria andTypeNameIsNotNull() {
            addCriterion("type_name is not null");
            return (Criteria) this;
        }

        public Criteria andTypeNameEqualTo(String value) {
            addCriterion("type_name =", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotEqualTo(String value) {
            addCriterion("type_name <>", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThan(String value) {
            addCriterion("type_name >", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("type_name >=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThan(String value) {
            addCriterion("type_name <", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLessThanOrEqualTo(String value) {
            addCriterion("type_name <=", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameLike(String value) {
            addCriterion("type_name like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotLike(String value) {
            addCriterion("type_name not like", value, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameIn(List<String> values) {
            addCriterion("type_name in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotIn(List<String> values) {
            addCriterion("type_name not in", values, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameBetween(String value1, String value2) {
            addCriterion("type_name between", value1, value2, "typeName");
            return (Criteria) this;
        }

        public Criteria andTypeNameNotBetween(String value1, String value2) {
            addCriterion("type_name not between", value1, value2, "typeName");
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

        public Criteria andSexIsNull() {
            addCriterion("sex is null");
            return (Criteria) this;
        }

        public Criteria andSexIsNotNull() {
            addCriterion("sex is not null");
            return (Criteria) this;
        }

        public Criteria andSexEqualTo(String value) {
            addCriterion("sex =", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotEqualTo(String value) {
            addCriterion("sex <>", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThan(String value) {
            addCriterion("sex >", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexGreaterThanOrEqualTo(String value) {
            addCriterion("sex >=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThan(String value) {
            addCriterion("sex <", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLessThanOrEqualTo(String value) {
            addCriterion("sex <=", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexLike(String value) {
            addCriterion("sex like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotLike(String value) {
            addCriterion("sex not like", value, "sex");
            return (Criteria) this;
        }

        public Criteria andSexIn(List<String> values) {
            addCriterion("sex in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotIn(List<String> values) {
            addCriterion("sex not in", values, "sex");
            return (Criteria) this;
        }

        public Criteria andSexBetween(String value1, String value2) {
            addCriterion("sex between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNotBetween(String value1, String value2) {
            addCriterion("sex not between", value1, value2, "sex");
            return (Criteria) this;
        }

        public Criteria andSexNameIsNull() {
            addCriterion("sex_name is null");
            return (Criteria) this;
        }

        public Criteria andSexNameIsNotNull() {
            addCriterion("sex_name is not null");
            return (Criteria) this;
        }

        public Criteria andSexNameEqualTo(String value) {
            addCriterion("sex_name =", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotEqualTo(String value) {
            addCriterion("sex_name <>", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameGreaterThan(String value) {
            addCriterion("sex_name >", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameGreaterThanOrEqualTo(String value) {
            addCriterion("sex_name >=", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameLessThan(String value) {
            addCriterion("sex_name <", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameLessThanOrEqualTo(String value) {
            addCriterion("sex_name <=", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameLike(String value) {
            addCriterion("sex_name like", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotLike(String value) {
            addCriterion("sex_name not like", value, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameIn(List<String> values) {
            addCriterion("sex_name in", values, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotIn(List<String> values) {
            addCriterion("sex_name not in", values, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameBetween(String value1, String value2) {
            addCriterion("sex_name between", value1, value2, "sexName");
            return (Criteria) this;
        }

        public Criteria andSexNameNotBetween(String value1, String value2) {
            addCriterion("sex_name not between", value1, value2, "sexName");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNull() {
            addCriterion("birthday is null");
            return (Criteria) this;
        }

        public Criteria andBirthdayIsNotNull() {
            addCriterion("birthday is not null");
            return (Criteria) this;
        }

        public Criteria andBirthdayEqualTo(Date value) {
            addCriterion("birthday =", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotEqualTo(Date value) {
            addCriterion("birthday <>", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThan(Date value) {
            addCriterion("birthday >", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayGreaterThanOrEqualTo(Date value) {
            addCriterion("birthday >=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThan(Date value) {
            addCriterion("birthday <", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayLessThanOrEqualTo(Date value) {
            addCriterion("birthday <=", value, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayIn(List<Date> values) {
            addCriterion("birthday in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotIn(List<Date> values) {
            addCriterion("birthday not in", values, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayBetween(Date value1, Date value2) {
            addCriterion("birthday between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andBirthdayNotBetween(Date value1, Date value2) {
            addCriterion("birthday not between", value1, value2, "birthday");
            return (Criteria) this;
        }

        public Criteria andCountryIsNull() {
            addCriterion("country is null");
            return (Criteria) this;
        }

        public Criteria andCountryIsNotNull() {
            addCriterion("country is not null");
            return (Criteria) this;
        }

        public Criteria andCountryEqualTo(Short value) {
            addCriterion("country =", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotEqualTo(Short value) {
            addCriterion("country <>", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThan(Short value) {
            addCriterion("country >", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThanOrEqualTo(Short value) {
            addCriterion("country >=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThan(Short value) {
            addCriterion("country <", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThanOrEqualTo(Short value) {
            addCriterion("country <=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryIn(List<Short> values) {
            addCriterion("country in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotIn(List<Short> values) {
            addCriterion("country not in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryBetween(Short value1, Short value2) {
            addCriterion("country between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotBetween(Short value1, Short value2) {
            addCriterion("country not between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNameIsNull() {
            addCriterion("country_name is null");
            return (Criteria) this;
        }

        public Criteria andCountryNameIsNotNull() {
            addCriterion("country_name is not null");
            return (Criteria) this;
        }

        public Criteria andCountryNameEqualTo(String value) {
            addCriterion("country_name =", value, "countryName");
            return (Criteria) this;
        }

        public Criteria andCountryNameNotEqualTo(String value) {
            addCriterion("country_name <>", value, "countryName");
            return (Criteria) this;
        }

        public Criteria andCountryNameGreaterThan(String value) {
            addCriterion("country_name >", value, "countryName");
            return (Criteria) this;
        }

        public Criteria andCountryNameGreaterThanOrEqualTo(String value) {
            addCriterion("country_name >=", value, "countryName");
            return (Criteria) this;
        }

        public Criteria andCountryNameLessThan(String value) {
            addCriterion("country_name <", value, "countryName");
            return (Criteria) this;
        }

        public Criteria andCountryNameLessThanOrEqualTo(String value) {
            addCriterion("country_name <=", value, "countryName");
            return (Criteria) this;
        }

        public Criteria andCountryNameLike(String value) {
            addCriterion("country_name like", value, "countryName");
            return (Criteria) this;
        }

        public Criteria andCountryNameNotLike(String value) {
            addCriterion("country_name not like", value, "countryName");
            return (Criteria) this;
        }

        public Criteria andCountryNameIn(List<String> values) {
            addCriterion("country_name in", values, "countryName");
            return (Criteria) this;
        }

        public Criteria andCountryNameNotIn(List<String> values) {
            addCriterion("country_name not in", values, "countryName");
            return (Criteria) this;
        }

        public Criteria andCountryNameBetween(String value1, String value2) {
            addCriterion("country_name between", value1, value2, "countryName");
            return (Criteria) this;
        }

        public Criteria andCountryNameNotBetween(String value1, String value2) {
            addCriterion("country_name not between", value1, value2, "countryName");
            return (Criteria) this;
        }

        public Criteria andNationalityIsNull() {
            addCriterion("nationality is null");
            return (Criteria) this;
        }

        public Criteria andNationalityIsNotNull() {
            addCriterion("nationality is not null");
            return (Criteria) this;
        }

        public Criteria andNationalityEqualTo(Short value) {
            addCriterion("nationality =", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotEqualTo(Short value) {
            addCriterion("nationality <>", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityGreaterThan(Short value) {
            addCriterion("nationality >", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityGreaterThanOrEqualTo(Short value) {
            addCriterion("nationality >=", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityLessThan(Short value) {
            addCriterion("nationality <", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityLessThanOrEqualTo(Short value) {
            addCriterion("nationality <=", value, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityIn(List<Short> values) {
            addCriterion("nationality in", values, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotIn(List<Short> values) {
            addCriterion("nationality not in", values, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityBetween(Short value1, Short value2) {
            addCriterion("nationality between", value1, value2, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNotBetween(Short value1, Short value2) {
            addCriterion("nationality not between", value1, value2, "nationality");
            return (Criteria) this;
        }

        public Criteria andNationalityNameIsNull() {
            addCriterion("nationality_name is null");
            return (Criteria) this;
        }

        public Criteria andNationalityNameIsNotNull() {
            addCriterion("nationality_name is not null");
            return (Criteria) this;
        }

        public Criteria andNationalityNameEqualTo(String value) {
            addCriterion("nationality_name =", value, "nationalityName");
            return (Criteria) this;
        }

        public Criteria andNationalityNameNotEqualTo(String value) {
            addCriterion("nationality_name <>", value, "nationalityName");
            return (Criteria) this;
        }

        public Criteria andNationalityNameGreaterThan(String value) {
            addCriterion("nationality_name >", value, "nationalityName");
            return (Criteria) this;
        }

        public Criteria andNationalityNameGreaterThanOrEqualTo(String value) {
            addCriterion("nationality_name >=", value, "nationalityName");
            return (Criteria) this;
        }

        public Criteria andNationalityNameLessThan(String value) {
            addCriterion("nationality_name <", value, "nationalityName");
            return (Criteria) this;
        }

        public Criteria andNationalityNameLessThanOrEqualTo(String value) {
            addCriterion("nationality_name <=", value, "nationalityName");
            return (Criteria) this;
        }

        public Criteria andNationalityNameLike(String value) {
            addCriterion("nationality_name like", value, "nationalityName");
            return (Criteria) this;
        }

        public Criteria andNationalityNameNotLike(String value) {
            addCriterion("nationality_name not like", value, "nationalityName");
            return (Criteria) this;
        }

        public Criteria andNationalityNameIn(List<String> values) {
            addCriterion("nationality_name in", values, "nationalityName");
            return (Criteria) this;
        }

        public Criteria andNationalityNameNotIn(List<String> values) {
            addCriterion("nationality_name not in", values, "nationalityName");
            return (Criteria) this;
        }

        public Criteria andNationalityNameBetween(String value1, String value2) {
            addCriterion("nationality_name between", value1, value2, "nationalityName");
            return (Criteria) this;
        }

        public Criteria andNationalityNameNotBetween(String value1, String value2) {
            addCriterion("nationality_name not between", value1, value2, "nationalityName");
            return (Criteria) this;
        }

        public Criteria andPaperTypeIsNull() {
            addCriterion("paper_type is null");
            return (Criteria) this;
        }

        public Criteria andPaperTypeIsNotNull() {
            addCriterion("paper_type is not null");
            return (Criteria) this;
        }

        public Criteria andPaperTypeEqualTo(Short value) {
            addCriterion("paper_type =", value, "paperType");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNotEqualTo(Short value) {
            addCriterion("paper_type <>", value, "paperType");
            return (Criteria) this;
        }

        public Criteria andPaperTypeGreaterThan(Short value) {
            addCriterion("paper_type >", value, "paperType");
            return (Criteria) this;
        }

        public Criteria andPaperTypeGreaterThanOrEqualTo(Short value) {
            addCriterion("paper_type >=", value, "paperType");
            return (Criteria) this;
        }

        public Criteria andPaperTypeLessThan(Short value) {
            addCriterion("paper_type <", value, "paperType");
            return (Criteria) this;
        }

        public Criteria andPaperTypeLessThanOrEqualTo(Short value) {
            addCriterion("paper_type <=", value, "paperType");
            return (Criteria) this;
        }

        public Criteria andPaperTypeIn(List<Short> values) {
            addCriterion("paper_type in", values, "paperType");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNotIn(List<Short> values) {
            addCriterion("paper_type not in", values, "paperType");
            return (Criteria) this;
        }

        public Criteria andPaperTypeBetween(Short value1, Short value2) {
            addCriterion("paper_type between", value1, value2, "paperType");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNotBetween(Short value1, Short value2) {
            addCriterion("paper_type not between", value1, value2, "paperType");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameIsNull() {
            addCriterion("paper_type_name is null");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameIsNotNull() {
            addCriterion("paper_type_name is not null");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameEqualTo(String value) {
            addCriterion("paper_type_name =", value, "paperTypeName");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameNotEqualTo(String value) {
            addCriterion("paper_type_name <>", value, "paperTypeName");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameGreaterThan(String value) {
            addCriterion("paper_type_name >", value, "paperTypeName");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameGreaterThanOrEqualTo(String value) {
            addCriterion("paper_type_name >=", value, "paperTypeName");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameLessThan(String value) {
            addCriterion("paper_type_name <", value, "paperTypeName");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameLessThanOrEqualTo(String value) {
            addCriterion("paper_type_name <=", value, "paperTypeName");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameLike(String value) {
            addCriterion("paper_type_name like", value, "paperTypeName");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameNotLike(String value) {
            addCriterion("paper_type_name not like", value, "paperTypeName");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameIn(List<String> values) {
            addCriterion("paper_type_name in", values, "paperTypeName");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameNotIn(List<String> values) {
            addCriterion("paper_type_name not in", values, "paperTypeName");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameBetween(String value1, String value2) {
            addCriterion("paper_type_name between", value1, value2, "paperTypeName");
            return (Criteria) this;
        }

        public Criteria andPaperTypeNameNotBetween(String value1, String value2) {
            addCriterion("paper_type_name not between", value1, value2, "paperTypeName");
            return (Criteria) this;
        }

        public Criteria andPaperNumberIsNull() {
            addCriterion("paper_number is null");
            return (Criteria) this;
        }

        public Criteria andPaperNumberIsNotNull() {
            addCriterion("paper_number is not null");
            return (Criteria) this;
        }

        public Criteria andPaperNumberEqualTo(String value) {
            addCriterion("paper_number =", value, "paperNumber");
            return (Criteria) this;
        }

        public Criteria andPaperNumberNotEqualTo(String value) {
            addCriterion("paper_number <>", value, "paperNumber");
            return (Criteria) this;
        }

        public Criteria andPaperNumberGreaterThan(String value) {
            addCriterion("paper_number >", value, "paperNumber");
            return (Criteria) this;
        }

        public Criteria andPaperNumberGreaterThanOrEqualTo(String value) {
            addCriterion("paper_number >=", value, "paperNumber");
            return (Criteria) this;
        }

        public Criteria andPaperNumberLessThan(String value) {
            addCriterion("paper_number <", value, "paperNumber");
            return (Criteria) this;
        }

        public Criteria andPaperNumberLessThanOrEqualTo(String value) {
            addCriterion("paper_number <=", value, "paperNumber");
            return (Criteria) this;
        }

        public Criteria andPaperNumberLike(String value) {
            addCriterion("paper_number like", value, "paperNumber");
            return (Criteria) this;
        }

        public Criteria andPaperNumberNotLike(String value) {
            addCriterion("paper_number not like", value, "paperNumber");
            return (Criteria) this;
        }

        public Criteria andPaperNumberIn(List<String> values) {
            addCriterion("paper_number in", values, "paperNumber");
            return (Criteria) this;
        }

        public Criteria andPaperNumberNotIn(List<String> values) {
            addCriterion("paper_number not in", values, "paperNumber");
            return (Criteria) this;
        }

        public Criteria andPaperNumberBetween(String value1, String value2) {
            addCriterion("paper_number between", value1, value2, "paperNumber");
            return (Criteria) this;
        }

        public Criteria andPaperNumberNotBetween(String value1, String value2) {
            addCriterion("paper_number not between", value1, value2, "paperNumber");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andMarriageIsNull() {
            addCriterion("marriage is null");
            return (Criteria) this;
        }

        public Criteria andMarriageIsNotNull() {
            addCriterion("marriage is not null");
            return (Criteria) this;
        }

        public Criteria andMarriageEqualTo(Short value) {
            addCriterion("marriage =", value, "marriage");
            return (Criteria) this;
        }

        public Criteria andMarriageNotEqualTo(Short value) {
            addCriterion("marriage <>", value, "marriage");
            return (Criteria) this;
        }

        public Criteria andMarriageGreaterThan(Short value) {
            addCriterion("marriage >", value, "marriage");
            return (Criteria) this;
        }

        public Criteria andMarriageGreaterThanOrEqualTo(Short value) {
            addCriterion("marriage >=", value, "marriage");
            return (Criteria) this;
        }

        public Criteria andMarriageLessThan(Short value) {
            addCriterion("marriage <", value, "marriage");
            return (Criteria) this;
        }

        public Criteria andMarriageLessThanOrEqualTo(Short value) {
            addCriterion("marriage <=", value, "marriage");
            return (Criteria) this;
        }

        public Criteria andMarriageIn(List<Short> values) {
            addCriterion("marriage in", values, "marriage");
            return (Criteria) this;
        }

        public Criteria andMarriageNotIn(List<Short> values) {
            addCriterion("marriage not in", values, "marriage");
            return (Criteria) this;
        }

        public Criteria andMarriageBetween(Short value1, Short value2) {
            addCriterion("marriage between", value1, value2, "marriage");
            return (Criteria) this;
        }

        public Criteria andMarriageNotBetween(Short value1, Short value2) {
            addCriterion("marriage not between", value1, value2, "marriage");
            return (Criteria) this;
        }

        public Criteria andMarriageNameIsNull() {
            addCriterion("marriage_name is null");
            return (Criteria) this;
        }

        public Criteria andMarriageNameIsNotNull() {
            addCriterion("marriage_name is not null");
            return (Criteria) this;
        }

        public Criteria andMarriageNameEqualTo(String value) {
            addCriterion("marriage_name =", value, "marriageName");
            return (Criteria) this;
        }

        public Criteria andMarriageNameNotEqualTo(String value) {
            addCriterion("marriage_name <>", value, "marriageName");
            return (Criteria) this;
        }

        public Criteria andMarriageNameGreaterThan(String value) {
            addCriterion("marriage_name >", value, "marriageName");
            return (Criteria) this;
        }

        public Criteria andMarriageNameGreaterThanOrEqualTo(String value) {
            addCriterion("marriage_name >=", value, "marriageName");
            return (Criteria) this;
        }

        public Criteria andMarriageNameLessThan(String value) {
            addCriterion("marriage_name <", value, "marriageName");
            return (Criteria) this;
        }

        public Criteria andMarriageNameLessThanOrEqualTo(String value) {
            addCriterion("marriage_name <=", value, "marriageName");
            return (Criteria) this;
        }

        public Criteria andMarriageNameLike(String value) {
            addCriterion("marriage_name like", value, "marriageName");
            return (Criteria) this;
        }

        public Criteria andMarriageNameNotLike(String value) {
            addCriterion("marriage_name not like", value, "marriageName");
            return (Criteria) this;
        }

        public Criteria andMarriageNameIn(List<String> values) {
            addCriterion("marriage_name in", values, "marriageName");
            return (Criteria) this;
        }

        public Criteria andMarriageNameNotIn(List<String> values) {
            addCriterion("marriage_name not in", values, "marriageName");
            return (Criteria) this;
        }

        public Criteria andMarriageNameBetween(String value1, String value2) {
            addCriterion("marriage_name between", value1, value2, "marriageName");
            return (Criteria) this;
        }

        public Criteria andMarriageNameNotBetween(String value1, String value2) {
            addCriterion("marriage_name not between", value1, value2, "marriageName");
            return (Criteria) this;
        }

        public Criteria andPoliticalIsNull() {
            addCriterion("political is null");
            return (Criteria) this;
        }

        public Criteria andPoliticalIsNotNull() {
            addCriterion("political is not null");
            return (Criteria) this;
        }

        public Criteria andPoliticalEqualTo(Short value) {
            addCriterion("political =", value, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalNotEqualTo(Short value) {
            addCriterion("political <>", value, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalGreaterThan(Short value) {
            addCriterion("political >", value, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalGreaterThanOrEqualTo(Short value) {
            addCriterion("political >=", value, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalLessThan(Short value) {
            addCriterion("political <", value, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalLessThanOrEqualTo(Short value) {
            addCriterion("political <=", value, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalIn(List<Short> values) {
            addCriterion("political in", values, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalNotIn(List<Short> values) {
            addCriterion("political not in", values, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalBetween(Short value1, Short value2) {
            addCriterion("political between", value1, value2, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalNotBetween(Short value1, Short value2) {
            addCriterion("political not between", value1, value2, "political");
            return (Criteria) this;
        }

        public Criteria andPoliticalNameIsNull() {
            addCriterion("political_name is null");
            return (Criteria) this;
        }

        public Criteria andPoliticalNameIsNotNull() {
            addCriterion("political_name is not null");
            return (Criteria) this;
        }

        public Criteria andPoliticalNameEqualTo(String value) {
            addCriterion("political_name =", value, "politicalName");
            return (Criteria) this;
        }

        public Criteria andPoliticalNameNotEqualTo(String value) {
            addCriterion("political_name <>", value, "politicalName");
            return (Criteria) this;
        }

        public Criteria andPoliticalNameGreaterThan(String value) {
            addCriterion("political_name >", value, "politicalName");
            return (Criteria) this;
        }

        public Criteria andPoliticalNameGreaterThanOrEqualTo(String value) {
            addCriterion("political_name >=", value, "politicalName");
            return (Criteria) this;
        }

        public Criteria andPoliticalNameLessThan(String value) {
            addCriterion("political_name <", value, "politicalName");
            return (Criteria) this;
        }

        public Criteria andPoliticalNameLessThanOrEqualTo(String value) {
            addCriterion("political_name <=", value, "politicalName");
            return (Criteria) this;
        }

        public Criteria andPoliticalNameLike(String value) {
            addCriterion("political_name like", value, "politicalName");
            return (Criteria) this;
        }

        public Criteria andPoliticalNameNotLike(String value) {
            addCriterion("political_name not like", value, "politicalName");
            return (Criteria) this;
        }

        public Criteria andPoliticalNameIn(List<String> values) {
            addCriterion("political_name in", values, "politicalName");
            return (Criteria) this;
        }

        public Criteria andPoliticalNameNotIn(List<String> values) {
            addCriterion("political_name not in", values, "politicalName");
            return (Criteria) this;
        }

        public Criteria andPoliticalNameBetween(String value1, String value2) {
            addCriterion("political_name between", value1, value2, "politicalName");
            return (Criteria) this;
        }

        public Criteria andPoliticalNameNotBetween(String value1, String value2) {
            addCriterion("political_name not between", value1, value2, "politicalName");
            return (Criteria) this;
        }

        public Criteria andEducationIsNull() {
            addCriterion("education is null");
            return (Criteria) this;
        }

        public Criteria andEducationIsNotNull() {
            addCriterion("education is not null");
            return (Criteria) this;
        }

        public Criteria andEducationEqualTo(Short value) {
            addCriterion("education =", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotEqualTo(Short value) {
            addCriterion("education <>", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThan(Short value) {
            addCriterion("education >", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationGreaterThanOrEqualTo(Short value) {
            addCriterion("education >=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThan(Short value) {
            addCriterion("education <", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationLessThanOrEqualTo(Short value) {
            addCriterion("education <=", value, "education");
            return (Criteria) this;
        }

        public Criteria andEducationIn(List<Short> values) {
            addCriterion("education in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotIn(List<Short> values) {
            addCriterion("education not in", values, "education");
            return (Criteria) this;
        }

        public Criteria andEducationBetween(Short value1, Short value2) {
            addCriterion("education between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNotBetween(Short value1, Short value2) {
            addCriterion("education not between", value1, value2, "education");
            return (Criteria) this;
        }

        public Criteria andEducationNameIsNull() {
            addCriterion("education_name is null");
            return (Criteria) this;
        }

        public Criteria andEducationNameIsNotNull() {
            addCriterion("education_name is not null");
            return (Criteria) this;
        }

        public Criteria andEducationNameEqualTo(String value) {
            addCriterion("education_name =", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameNotEqualTo(String value) {
            addCriterion("education_name <>", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameGreaterThan(String value) {
            addCriterion("education_name >", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameGreaterThanOrEqualTo(String value) {
            addCriterion("education_name >=", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameLessThan(String value) {
            addCriterion("education_name <", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameLessThanOrEqualTo(String value) {
            addCriterion("education_name <=", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameLike(String value) {
            addCriterion("education_name like", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameNotLike(String value) {
            addCriterion("education_name not like", value, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameIn(List<String> values) {
            addCriterion("education_name in", values, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameNotIn(List<String> values) {
            addCriterion("education_name not in", values, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameBetween(String value1, String value2) {
            addCriterion("education_name between", value1, value2, "educationName");
            return (Criteria) this;
        }

        public Criteria andEducationNameNotBetween(String value1, String value2) {
            addCriterion("education_name not between", value1, value2, "educationName");
            return (Criteria) this;
        }

        public Criteria andOccupationIsNull() {
            addCriterion("occupation is null");
            return (Criteria) this;
        }

        public Criteria andOccupationIsNotNull() {
            addCriterion("occupation is not null");
            return (Criteria) this;
        }

        public Criteria andOccupationEqualTo(String value) {
            addCriterion("occupation =", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationNotEqualTo(String value) {
            addCriterion("occupation <>", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationGreaterThan(String value) {
            addCriterion("occupation >", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationGreaterThanOrEqualTo(String value) {
            addCriterion("occupation >=", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationLessThan(String value) {
            addCriterion("occupation <", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationLessThanOrEqualTo(String value) {
            addCriterion("occupation <=", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationLike(String value) {
            addCriterion("occupation like", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationNotLike(String value) {
            addCriterion("occupation not like", value, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationIn(List<String> values) {
            addCriterion("occupation in", values, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationNotIn(List<String> values) {
            addCriterion("occupation not in", values, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationBetween(String value1, String value2) {
            addCriterion("occupation between", value1, value2, "occupation");
            return (Criteria) this;
        }

        public Criteria andOccupationNotBetween(String value1, String value2) {
            addCriterion("occupation not between", value1, value2, "occupation");
            return (Criteria) this;
        }

        public Criteria andImageIsNull() {
            addCriterion("image is null");
            return (Criteria) this;
        }

        public Criteria andImageIsNotNull() {
            addCriterion("image is not null");
            return (Criteria) this;
        }

        public Criteria andImageEqualTo(String value) {
            addCriterion("image =", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotEqualTo(String value) {
            addCriterion("image <>", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThan(String value) {
            addCriterion("image >", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageGreaterThanOrEqualTo(String value) {
            addCriterion("image >=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThan(String value) {
            addCriterion("image <", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLessThanOrEqualTo(String value) {
            addCriterion("image <=", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageLike(String value) {
            addCriterion("image like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotLike(String value) {
            addCriterion("image not like", value, "image");
            return (Criteria) this;
        }

        public Criteria andImageIn(List<String> values) {
            addCriterion("image in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotIn(List<String> values) {
            addCriterion("image not in", values, "image");
            return (Criteria) this;
        }

        public Criteria andImageBetween(String value1, String value2) {
            addCriterion("image between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andImageNotBetween(String value1, String value2) {
            addCriterion("image not between", value1, value2, "image");
            return (Criteria) this;
        }

        public Criteria andCheckinTimeIsNull() {
            addCriterion("checkin_time is null");
            return (Criteria) this;
        }

        public Criteria andCheckinTimeIsNotNull() {
            addCriterion("checkin_time is not null");
            return (Criteria) this;
        }

        public Criteria andCheckinTimeEqualTo(Date value) {
            addCriterion("checkin_time =", value, "checkinTime");
            return (Criteria) this;
        }

        public Criteria andCheckinTimeNotEqualTo(Date value) {
            addCriterion("checkin_time <>", value, "checkinTime");
            return (Criteria) this;
        }

        public Criteria andCheckinTimeGreaterThan(Date value) {
            addCriterion("checkin_time >", value, "checkinTime");
            return (Criteria) this;
        }

        public Criteria andCheckinTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("checkin_time >=", value, "checkinTime");
            return (Criteria) this;
        }

        public Criteria andCheckinTimeLessThan(Date value) {
            addCriterion("checkin_time <", value, "checkinTime");
            return (Criteria) this;
        }

        public Criteria andCheckinTimeLessThanOrEqualTo(Date value) {
            addCriterion("checkin_time <=", value, "checkinTime");
            return (Criteria) this;
        }

        public Criteria andCheckinTimeIn(List<Date> values) {
            addCriterion("checkin_time in", values, "checkinTime");
            return (Criteria) this;
        }

        public Criteria andCheckinTimeNotIn(List<Date> values) {
            addCriterion("checkin_time not in", values, "checkinTime");
            return (Criteria) this;
        }

        public Criteria andCheckinTimeBetween(Date value1, Date value2) {
            addCriterion("checkin_time between", value1, value2, "checkinTime");
            return (Criteria) this;
        }

        public Criteria andCheckinTimeNotBetween(Date value1, Date value2) {
            addCriterion("checkin_time not between", value1, value2, "checkinTime");
            return (Criteria) this;
        }

        public Criteria andResidencePermitIsNull() {
            addCriterion("residence_permit is null");
            return (Criteria) this;
        }

        public Criteria andResidencePermitIsNotNull() {
            addCriterion("residence_permit is not null");
            return (Criteria) this;
        }

        public Criteria andResidencePermitEqualTo(Byte value) {
            addCriterion("residence_permit =", value, "residencePermit");
            return (Criteria) this;
        }

        public Criteria andResidencePermitNotEqualTo(Byte value) {
            addCriterion("residence_permit <>", value, "residencePermit");
            return (Criteria) this;
        }

        public Criteria andResidencePermitGreaterThan(Byte value) {
            addCriterion("residence_permit >", value, "residencePermit");
            return (Criteria) this;
        }

        public Criteria andResidencePermitGreaterThanOrEqualTo(Byte value) {
            addCriterion("residence_permit >=", value, "residencePermit");
            return (Criteria) this;
        }

        public Criteria andResidencePermitLessThan(Byte value) {
            addCriterion("residence_permit <", value, "residencePermit");
            return (Criteria) this;
        }

        public Criteria andResidencePermitLessThanOrEqualTo(Byte value) {
            addCriterion("residence_permit <=", value, "residencePermit");
            return (Criteria) this;
        }

        public Criteria andResidencePermitIn(List<Byte> values) {
            addCriterion("residence_permit in", values, "residencePermit");
            return (Criteria) this;
        }

        public Criteria andResidencePermitNotIn(List<Byte> values) {
            addCriterion("residence_permit not in", values, "residencePermit");
            return (Criteria) this;
        }

        public Criteria andResidencePermitBetween(Byte value1, Byte value2) {
            addCriterion("residence_permit between", value1, value2, "residencePermit");
            return (Criteria) this;
        }

        public Criteria andResidencePermitNotBetween(Byte value1, Byte value2) {
            addCriterion("residence_permit not between", value1, value2, "residencePermit");
            return (Criteria) this;
        }

        public Criteria andRelationIsNull() {
            addCriterion("relation is null");
            return (Criteria) this;
        }

        public Criteria andRelationIsNotNull() {
            addCriterion("relation is not null");
            return (Criteria) this;
        }

        public Criteria andRelationEqualTo(Short value) {
            addCriterion("relation =", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationNotEqualTo(Short value) {
            addCriterion("relation <>", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationGreaterThan(Short value) {
            addCriterion("relation >", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationGreaterThanOrEqualTo(Short value) {
            addCriterion("relation >=", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationLessThan(Short value) {
            addCriterion("relation <", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationLessThanOrEqualTo(Short value) {
            addCriterion("relation <=", value, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationIn(List<Short> values) {
            addCriterion("relation in", values, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationNotIn(List<Short> values) {
            addCriterion("relation not in", values, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationBetween(Short value1, Short value2) {
            addCriterion("relation between", value1, value2, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationNotBetween(Short value1, Short value2) {
            addCriterion("relation not between", value1, value2, "relation");
            return (Criteria) this;
        }

        public Criteria andRelationNameIsNull() {
            addCriterion("relation_name is null");
            return (Criteria) this;
        }

        public Criteria andRelationNameIsNotNull() {
            addCriterion("relation_name is not null");
            return (Criteria) this;
        }

        public Criteria andRelationNameEqualTo(String value) {
            addCriterion("relation_name =", value, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameNotEqualTo(String value) {
            addCriterion("relation_name <>", value, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameGreaterThan(String value) {
            addCriterion("relation_name >", value, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameGreaterThanOrEqualTo(String value) {
            addCriterion("relation_name >=", value, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameLessThan(String value) {
            addCriterion("relation_name <", value, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameLessThanOrEqualTo(String value) {
            addCriterion("relation_name <=", value, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameLike(String value) {
            addCriterion("relation_name like", value, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameNotLike(String value) {
            addCriterion("relation_name not like", value, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameIn(List<String> values) {
            addCriterion("relation_name in", values, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameNotIn(List<String> values) {
            addCriterion("relation_name not in", values, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameBetween(String value1, String value2) {
            addCriterion("relation_name between", value1, value2, "relationName");
            return (Criteria) this;
        }

        public Criteria andRelationNameNotBetween(String value1, String value2) {
            addCriterion("relation_name not between", value1, value2, "relationName");
            return (Criteria) this;
        }

        public Criteria andFaceGroupDetailIdIsNull() {
            addCriterion("face_group_detail_id is null");
            return (Criteria) this;
        }

        public Criteria andFaceGroupDetailIdIsNotNull() {
            addCriterion("face_group_detail_id is not null");
            return (Criteria) this;
        }

        public Criteria andFaceGroupDetailIdEqualTo(String value) {
            addCriterion("face_group_detail_id =", value, "faceGroupDetailId");
            return (Criteria) this;
        }

        public Criteria andFaceGroupDetailIdNotEqualTo(String value) {
            addCriterion("face_group_detail_id <>", value, "faceGroupDetailId");
            return (Criteria) this;
        }

        public Criteria andFaceGroupDetailIdGreaterThan(String value) {
            addCriterion("face_group_detail_id >", value, "faceGroupDetailId");
            return (Criteria) this;
        }

        public Criteria andFaceGroupDetailIdGreaterThanOrEqualTo(String value) {
            addCriterion("face_group_detail_id >=", value, "faceGroupDetailId");
            return (Criteria) this;
        }

        public Criteria andFaceGroupDetailIdLessThan(String value) {
            addCriterion("face_group_detail_id <", value, "faceGroupDetailId");
            return (Criteria) this;
        }

        public Criteria andFaceGroupDetailIdLessThanOrEqualTo(String value) {
            addCriterion("face_group_detail_id <=", value, "faceGroupDetailId");
            return (Criteria) this;
        }

        public Criteria andFaceGroupDetailIdLike(String value) {
            addCriterion("face_group_detail_id like", value, "faceGroupDetailId");
            return (Criteria) this;
        }

        public Criteria andFaceGroupDetailIdNotLike(String value) {
            addCriterion("face_group_detail_id not like", value, "faceGroupDetailId");
            return (Criteria) this;
        }

        public Criteria andFaceGroupDetailIdIn(List<String> values) {
            addCriterion("face_group_detail_id in", values, "faceGroupDetailId");
            return (Criteria) this;
        }

        public Criteria andFaceGroupDetailIdNotIn(List<String> values) {
            addCriterion("face_group_detail_id not in", values, "faceGroupDetailId");
            return (Criteria) this;
        }

        public Criteria andFaceGroupDetailIdBetween(String value1, String value2) {
            addCriterion("face_group_detail_id between", value1, value2, "faceGroupDetailId");
            return (Criteria) this;
        }

        public Criteria andFaceGroupDetailIdNotBetween(String value1, String value2) {
            addCriterion("face_group_detail_id not between", value1, value2, "faceGroupDetailId");
            return (Criteria) this;
        }

        public Criteria andOpDateIsNull() {
            addCriterion("op_date is null");
            return (Criteria) this;
        }

        public Criteria andOpDateIsNotNull() {
            addCriterion("op_date is not null");
            return (Criteria) this;
        }

        public Criteria andOpDateEqualTo(Date value) {
            addCriterion("op_date =", value, "opDate");
            return (Criteria) this;
        }

        public Criteria andOpDateNotEqualTo(Date value) {
            addCriterion("op_date <>", value, "opDate");
            return (Criteria) this;
        }

        public Criteria andOpDateGreaterThan(Date value) {
            addCriterion("op_date >", value, "opDate");
            return (Criteria) this;
        }

        public Criteria andOpDateGreaterThanOrEqualTo(Date value) {
            addCriterion("op_date >=", value, "opDate");
            return (Criteria) this;
        }

        public Criteria andOpDateLessThan(Date value) {
            addCriterion("op_date <", value, "opDate");
            return (Criteria) this;
        }

        public Criteria andOpDateLessThanOrEqualTo(Date value) {
            addCriterion("op_date <=", value, "opDate");
            return (Criteria) this;
        }

        public Criteria andOpDateIn(List<Date> values) {
            addCriterion("op_date in", values, "opDate");
            return (Criteria) this;
        }

        public Criteria andOpDateNotIn(List<Date> values) {
            addCriterion("op_date not in", values, "opDate");
            return (Criteria) this;
        }

        public Criteria andOpDateBetween(Date value1, Date value2) {
            addCriterion("op_date between", value1, value2, "opDate");
            return (Criteria) this;
        }

        public Criteria andOpDateNotBetween(Date value1, Date value2) {
            addCriterion("op_date not between", value1, value2, "opDate");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIsNull() {
            addCriterion("group_type is null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIsNotNull() {
            addCriterion("group_type is not null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeEqualTo(String value) {
            addCriterion("group_type =", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNotEqualTo(String value) {
            addCriterion("group_type <>", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeGreaterThan(String value) {
            addCriterion("group_type >", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeGreaterThanOrEqualTo(String value) {
            addCriterion("group_type >=", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeLessThan(String value) {
            addCriterion("group_type <", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeLessThanOrEqualTo(String value) {
            addCriterion("group_type <=", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeLike(String value) {
            addCriterion("group_type like", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNotLike(String value) {
            addCriterion("group_type not like", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIn(List<String> values) {
            addCriterion("group_type in", values, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNotIn(List<String> values) {
            addCriterion("group_type not in", values, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeBetween(String value1, String value2) {
            addCriterion("group_type between", value1, value2, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNotBetween(String value1, String value2) {
            addCriterion("group_type not between", value1, value2, "groupType");
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