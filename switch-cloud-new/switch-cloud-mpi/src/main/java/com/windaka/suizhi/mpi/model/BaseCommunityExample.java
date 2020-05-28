package com.windaka.suizhi.mpi.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BaseCommunityExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BaseCommunityExample() {
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

        public Criteria andAddressCodeIsNull() {
            addCriterion("address_code is null");
            return (Criteria) this;
        }

        public Criteria andAddressCodeIsNotNull() {
            addCriterion("address_code is not null");
            return (Criteria) this;
        }

        public Criteria andAddressCodeEqualTo(String value) {
            addCriterion("address_code =", value, "addressCode");
            return (Criteria) this;
        }

        public Criteria andAddressCodeNotEqualTo(String value) {
            addCriterion("address_code <>", value, "addressCode");
            return (Criteria) this;
        }

        public Criteria andAddressCodeGreaterThan(String value) {
            addCriterion("address_code >", value, "addressCode");
            return (Criteria) this;
        }

        public Criteria andAddressCodeGreaterThanOrEqualTo(String value) {
            addCriterion("address_code >=", value, "addressCode");
            return (Criteria) this;
        }

        public Criteria andAddressCodeLessThan(String value) {
            addCriterion("address_code <", value, "addressCode");
            return (Criteria) this;
        }

        public Criteria andAddressCodeLessThanOrEqualTo(String value) {
            addCriterion("address_code <=", value, "addressCode");
            return (Criteria) this;
        }

        public Criteria andAddressCodeLike(String value) {
            addCriterion("address_code like", value, "addressCode");
            return (Criteria) this;
        }

        public Criteria andAddressCodeNotLike(String value) {
            addCriterion("address_code not like", value, "addressCode");
            return (Criteria) this;
        }

        public Criteria andAddressCodeIn(List<String> values) {
            addCriterion("address_code in", values, "addressCode");
            return (Criteria) this;
        }

        public Criteria andAddressCodeNotIn(List<String> values) {
            addCriterion("address_code not in", values, "addressCode");
            return (Criteria) this;
        }

        public Criteria andAddressCodeBetween(String value1, String value2) {
            addCriterion("address_code between", value1, value2, "addressCode");
            return (Criteria) this;
        }

        public Criteria andAddressCodeNotBetween(String value1, String value2) {
            addCriterion("address_code not between", value1, value2, "addressCode");
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

        public Criteria andFloorAreaIsNull() {
            addCriterion("floor_area is null");
            return (Criteria) this;
        }

        public Criteria andFloorAreaIsNotNull() {
            addCriterion("floor_area is not null");
            return (Criteria) this;
        }

        public Criteria andFloorAreaEqualTo(BigDecimal value) {
            addCriterion("floor_area =", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaNotEqualTo(BigDecimal value) {
            addCriterion("floor_area <>", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaGreaterThan(BigDecimal value) {
            addCriterion("floor_area >", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("floor_area >=", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaLessThan(BigDecimal value) {
            addCriterion("floor_area <", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("floor_area <=", value, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaIn(List<BigDecimal> values) {
            addCriterion("floor_area in", values, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaNotIn(List<BigDecimal> values) {
            addCriterion("floor_area not in", values, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("floor_area between", value1, value2, "floorArea");
            return (Criteria) this;
        }

        public Criteria andFloorAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("floor_area not between", value1, value2, "floorArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaIsNull() {
            addCriterion("building_area is null");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaIsNotNull() {
            addCriterion("building_area is not null");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaEqualTo(BigDecimal value) {
            addCriterion("building_area =", value, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaNotEqualTo(BigDecimal value) {
            addCriterion("building_area <>", value, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaGreaterThan(BigDecimal value) {
            addCriterion("building_area >", value, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("building_area >=", value, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaLessThan(BigDecimal value) {
            addCriterion("building_area <", value, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("building_area <=", value, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaIn(List<BigDecimal> values) {
            addCriterion("building_area in", values, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaNotIn(List<BigDecimal> values) {
            addCriterion("building_area not in", values, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("building_area between", value1, value2, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andBuildingAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("building_area not between", value1, value2, "buildingArea");
            return (Criteria) this;
        }

        public Criteria andPmCompanyCodeIsNull() {
            addCriterion("pm_company_code is null");
            return (Criteria) this;
        }

        public Criteria andPmCompanyCodeIsNotNull() {
            addCriterion("pm_company_code is not null");
            return (Criteria) this;
        }

        public Criteria andPmCompanyCodeEqualTo(String value) {
            addCriterion("pm_company_code =", value, "pmCompanyCode");
            return (Criteria) this;
        }

        public Criteria andPmCompanyCodeNotEqualTo(String value) {
            addCriterion("pm_company_code <>", value, "pmCompanyCode");
            return (Criteria) this;
        }

        public Criteria andPmCompanyCodeGreaterThan(String value) {
            addCriterion("pm_company_code >", value, "pmCompanyCode");
            return (Criteria) this;
        }

        public Criteria andPmCompanyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("pm_company_code >=", value, "pmCompanyCode");
            return (Criteria) this;
        }

        public Criteria andPmCompanyCodeLessThan(String value) {
            addCriterion("pm_company_code <", value, "pmCompanyCode");
            return (Criteria) this;
        }

        public Criteria andPmCompanyCodeLessThanOrEqualTo(String value) {
            addCriterion("pm_company_code <=", value, "pmCompanyCode");
            return (Criteria) this;
        }

        public Criteria andPmCompanyCodeLike(String value) {
            addCriterion("pm_company_code like", value, "pmCompanyCode");
            return (Criteria) this;
        }

        public Criteria andPmCompanyCodeNotLike(String value) {
            addCriterion("pm_company_code not like", value, "pmCompanyCode");
            return (Criteria) this;
        }

        public Criteria andPmCompanyCodeIn(List<String> values) {
            addCriterion("pm_company_code in", values, "pmCompanyCode");
            return (Criteria) this;
        }

        public Criteria andPmCompanyCodeNotIn(List<String> values) {
            addCriterion("pm_company_code not in", values, "pmCompanyCode");
            return (Criteria) this;
        }

        public Criteria andPmCompanyCodeBetween(String value1, String value2) {
            addCriterion("pm_company_code between", value1, value2, "pmCompanyCode");
            return (Criteria) this;
        }

        public Criteria andPmCompanyCodeNotBetween(String value1, String value2) {
            addCriterion("pm_company_code not between", value1, value2, "pmCompanyCode");
            return (Criteria) this;
        }

        public Criteria andPmCompanyNameIsNull() {
            addCriterion("pm_company_name is null");
            return (Criteria) this;
        }

        public Criteria andPmCompanyNameIsNotNull() {
            addCriterion("pm_company_name is not null");
            return (Criteria) this;
        }

        public Criteria andPmCompanyNameEqualTo(String value) {
            addCriterion("pm_company_name =", value, "pmCompanyName");
            return (Criteria) this;
        }

        public Criteria andPmCompanyNameNotEqualTo(String value) {
            addCriterion("pm_company_name <>", value, "pmCompanyName");
            return (Criteria) this;
        }

        public Criteria andPmCompanyNameGreaterThan(String value) {
            addCriterion("pm_company_name >", value, "pmCompanyName");
            return (Criteria) this;
        }

        public Criteria andPmCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("pm_company_name >=", value, "pmCompanyName");
            return (Criteria) this;
        }

        public Criteria andPmCompanyNameLessThan(String value) {
            addCriterion("pm_company_name <", value, "pmCompanyName");
            return (Criteria) this;
        }

        public Criteria andPmCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("pm_company_name <=", value, "pmCompanyName");
            return (Criteria) this;
        }

        public Criteria andPmCompanyNameLike(String value) {
            addCriterion("pm_company_name like", value, "pmCompanyName");
            return (Criteria) this;
        }

        public Criteria andPmCompanyNameNotLike(String value) {
            addCriterion("pm_company_name not like", value, "pmCompanyName");
            return (Criteria) this;
        }

        public Criteria andPmCompanyNameIn(List<String> values) {
            addCriterion("pm_company_name in", values, "pmCompanyName");
            return (Criteria) this;
        }

        public Criteria andPmCompanyNameNotIn(List<String> values) {
            addCriterion("pm_company_name not in", values, "pmCompanyName");
            return (Criteria) this;
        }

        public Criteria andPmCompanyNameBetween(String value1, String value2) {
            addCriterion("pm_company_name between", value1, value2, "pmCompanyName");
            return (Criteria) this;
        }

        public Criteria andPmCompanyNameNotBetween(String value1, String value2) {
            addCriterion("pm_company_name not between", value1, value2, "pmCompanyName");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPersonIsNull() {
            addCriterion("pm_company_person is null");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPersonIsNotNull() {
            addCriterion("pm_company_person is not null");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPersonEqualTo(String value) {
            addCriterion("pm_company_person =", value, "pmCompanyPerson");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPersonNotEqualTo(String value) {
            addCriterion("pm_company_person <>", value, "pmCompanyPerson");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPersonGreaterThan(String value) {
            addCriterion("pm_company_person >", value, "pmCompanyPerson");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPersonGreaterThanOrEqualTo(String value) {
            addCriterion("pm_company_person >=", value, "pmCompanyPerson");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPersonLessThan(String value) {
            addCriterion("pm_company_person <", value, "pmCompanyPerson");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPersonLessThanOrEqualTo(String value) {
            addCriterion("pm_company_person <=", value, "pmCompanyPerson");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPersonLike(String value) {
            addCriterion("pm_company_person like", value, "pmCompanyPerson");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPersonNotLike(String value) {
            addCriterion("pm_company_person not like", value, "pmCompanyPerson");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPersonIn(List<String> values) {
            addCriterion("pm_company_person in", values, "pmCompanyPerson");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPersonNotIn(List<String> values) {
            addCriterion("pm_company_person not in", values, "pmCompanyPerson");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPersonBetween(String value1, String value2) {
            addCriterion("pm_company_person between", value1, value2, "pmCompanyPerson");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPersonNotBetween(String value1, String value2) {
            addCriterion("pm_company_person not between", value1, value2, "pmCompanyPerson");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPhoneIsNull() {
            addCriterion("pm_company_phone is null");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPhoneIsNotNull() {
            addCriterion("pm_company_phone is not null");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPhoneEqualTo(String value) {
            addCriterion("pm_company_phone =", value, "pmCompanyPhone");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPhoneNotEqualTo(String value) {
            addCriterion("pm_company_phone <>", value, "pmCompanyPhone");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPhoneGreaterThan(String value) {
            addCriterion("pm_company_phone >", value, "pmCompanyPhone");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("pm_company_phone >=", value, "pmCompanyPhone");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPhoneLessThan(String value) {
            addCriterion("pm_company_phone <", value, "pmCompanyPhone");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPhoneLessThanOrEqualTo(String value) {
            addCriterion("pm_company_phone <=", value, "pmCompanyPhone");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPhoneLike(String value) {
            addCriterion("pm_company_phone like", value, "pmCompanyPhone");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPhoneNotLike(String value) {
            addCriterion("pm_company_phone not like", value, "pmCompanyPhone");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPhoneIn(List<String> values) {
            addCriterion("pm_company_phone in", values, "pmCompanyPhone");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPhoneNotIn(List<String> values) {
            addCriterion("pm_company_phone not in", values, "pmCompanyPhone");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPhoneBetween(String value1, String value2) {
            addCriterion("pm_company_phone between", value1, value2, "pmCompanyPhone");
            return (Criteria) this;
        }

        public Criteria andPmCompanyPhoneNotBetween(String value1, String value2) {
            addCriterion("pm_company_phone not between", value1, value2, "pmCompanyPhone");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperNameIsNull() {
            addCriterion("estate_developer_name is null");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperNameIsNotNull() {
            addCriterion("estate_developer_name is not null");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperNameEqualTo(String value) {
            addCriterion("estate_developer_name =", value, "estateDeveloperName");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperNameNotEqualTo(String value) {
            addCriterion("estate_developer_name <>", value, "estateDeveloperName");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperNameGreaterThan(String value) {
            addCriterion("estate_developer_name >", value, "estateDeveloperName");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperNameGreaterThanOrEqualTo(String value) {
            addCriterion("estate_developer_name >=", value, "estateDeveloperName");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperNameLessThan(String value) {
            addCriterion("estate_developer_name <", value, "estateDeveloperName");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperNameLessThanOrEqualTo(String value) {
            addCriterion("estate_developer_name <=", value, "estateDeveloperName");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperNameLike(String value) {
            addCriterion("estate_developer_name like", value, "estateDeveloperName");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperNameNotLike(String value) {
            addCriterion("estate_developer_name not like", value, "estateDeveloperName");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperNameIn(List<String> values) {
            addCriterion("estate_developer_name in", values, "estateDeveloperName");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperNameNotIn(List<String> values) {
            addCriterion("estate_developer_name not in", values, "estateDeveloperName");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperNameBetween(String value1, String value2) {
            addCriterion("estate_developer_name between", value1, value2, "estateDeveloperName");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperNameNotBetween(String value1, String value2) {
            addCriterion("estate_developer_name not between", value1, value2, "estateDeveloperName");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPersonIsNull() {
            addCriterion("estate_developer_person is null");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPersonIsNotNull() {
            addCriterion("estate_developer_person is not null");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPersonEqualTo(String value) {
            addCriterion("estate_developer_person =", value, "estateDeveloperPerson");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPersonNotEqualTo(String value) {
            addCriterion("estate_developer_person <>", value, "estateDeveloperPerson");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPersonGreaterThan(String value) {
            addCriterion("estate_developer_person >", value, "estateDeveloperPerson");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPersonGreaterThanOrEqualTo(String value) {
            addCriterion("estate_developer_person >=", value, "estateDeveloperPerson");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPersonLessThan(String value) {
            addCriterion("estate_developer_person <", value, "estateDeveloperPerson");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPersonLessThanOrEqualTo(String value) {
            addCriterion("estate_developer_person <=", value, "estateDeveloperPerson");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPersonLike(String value) {
            addCriterion("estate_developer_person like", value, "estateDeveloperPerson");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPersonNotLike(String value) {
            addCriterion("estate_developer_person not like", value, "estateDeveloperPerson");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPersonIn(List<String> values) {
            addCriterion("estate_developer_person in", values, "estateDeveloperPerson");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPersonNotIn(List<String> values) {
            addCriterion("estate_developer_person not in", values, "estateDeveloperPerson");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPersonBetween(String value1, String value2) {
            addCriterion("estate_developer_person between", value1, value2, "estateDeveloperPerson");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPersonNotBetween(String value1, String value2) {
            addCriterion("estate_developer_person not between", value1, value2, "estateDeveloperPerson");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPhoneIsNull() {
            addCriterion("estate_developer_phone is null");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPhoneIsNotNull() {
            addCriterion("estate_developer_phone is not null");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPhoneEqualTo(String value) {
            addCriterion("estate_developer_phone =", value, "estateDeveloperPhone");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPhoneNotEqualTo(String value) {
            addCriterion("estate_developer_phone <>", value, "estateDeveloperPhone");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPhoneGreaterThan(String value) {
            addCriterion("estate_developer_phone >", value, "estateDeveloperPhone");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("estate_developer_phone >=", value, "estateDeveloperPhone");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPhoneLessThan(String value) {
            addCriterion("estate_developer_phone <", value, "estateDeveloperPhone");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPhoneLessThanOrEqualTo(String value) {
            addCriterion("estate_developer_phone <=", value, "estateDeveloperPhone");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPhoneLike(String value) {
            addCriterion("estate_developer_phone like", value, "estateDeveloperPhone");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPhoneNotLike(String value) {
            addCriterion("estate_developer_phone not like", value, "estateDeveloperPhone");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPhoneIn(List<String> values) {
            addCriterion("estate_developer_phone in", values, "estateDeveloperPhone");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPhoneNotIn(List<String> values) {
            addCriterion("estate_developer_phone not in", values, "estateDeveloperPhone");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPhoneBetween(String value1, String value2) {
            addCriterion("estate_developer_phone between", value1, value2, "estateDeveloperPhone");
            return (Criteria) this;
        }

        public Criteria andEstateDeveloperPhoneNotBetween(String value1, String value2) {
            addCriterion("estate_developer_phone not between", value1, value2, "estateDeveloperPhone");
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

        public Criteria andAreaIsNull() {
            addCriterion("area is null");
            return (Criteria) this;
        }

        public Criteria andAreaIsNotNull() {
            addCriterion("area is not null");
            return (Criteria) this;
        }

        public Criteria andAreaEqualTo(String value) {
            addCriterion("area =", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotEqualTo(String value) {
            addCriterion("area <>", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThan(String value) {
            addCriterion("area >", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaGreaterThanOrEqualTo(String value) {
            addCriterion("area >=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThan(String value) {
            addCriterion("area <", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLessThanOrEqualTo(String value) {
            addCriterion("area <=", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaLike(String value) {
            addCriterion("area like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotLike(String value) {
            addCriterion("area not like", value, "area");
            return (Criteria) this;
        }

        public Criteria andAreaIn(List<String> values) {
            addCriterion("area in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotIn(List<String> values) {
            addCriterion("area not in", values, "area");
            return (Criteria) this;
        }

        public Criteria andAreaBetween(String value1, String value2) {
            addCriterion("area between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNotBetween(String value1, String value2) {
            addCriterion("area not between", value1, value2, "area");
            return (Criteria) this;
        }

        public Criteria andAreaNameIsNull() {
            addCriterion("area_name is null");
            return (Criteria) this;
        }

        public Criteria andAreaNameIsNotNull() {
            addCriterion("area_name is not null");
            return (Criteria) this;
        }

        public Criteria andAreaNameEqualTo(String value) {
            addCriterion("area_name =", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotEqualTo(String value) {
            addCriterion("area_name <>", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameGreaterThan(String value) {
            addCriterion("area_name >", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameGreaterThanOrEqualTo(String value) {
            addCriterion("area_name >=", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLessThan(String value) {
            addCriterion("area_name <", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLessThanOrEqualTo(String value) {
            addCriterion("area_name <=", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameLike(String value) {
            addCriterion("area_name like", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotLike(String value) {
            addCriterion("area_name not like", value, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameIn(List<String> values) {
            addCriterion("area_name in", values, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotIn(List<String> values) {
            addCriterion("area_name not in", values, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameBetween(String value1, String value2) {
            addCriterion("area_name between", value1, value2, "areaName");
            return (Criteria) this;
        }

        public Criteria andAreaNameNotBetween(String value1, String value2) {
            addCriterion("area_name not between", value1, value2, "areaName");
            return (Criteria) this;
        }

        public Criteria andLocationIsNull() {
            addCriterion("location is null");
            return (Criteria) this;
        }

        public Criteria andLocationIsNotNull() {
            addCriterion("location is not null");
            return (Criteria) this;
        }

        public Criteria andLocationEqualTo(String value) {
            addCriterion("location =", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotEqualTo(String value) {
            addCriterion("location <>", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThan(String value) {
            addCriterion("location >", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationGreaterThanOrEqualTo(String value) {
            addCriterion("location >=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThan(String value) {
            addCriterion("location <", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLessThanOrEqualTo(String value) {
            addCriterion("location <=", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationLike(String value) {
            addCriterion("location like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotLike(String value) {
            addCriterion("location not like", value, "location");
            return (Criteria) this;
        }

        public Criteria andLocationIn(List<String> values) {
            addCriterion("location in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotIn(List<String> values) {
            addCriterion("location not in", values, "location");
            return (Criteria) this;
        }

        public Criteria andLocationBetween(String value1, String value2) {
            addCriterion("location between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationNotBetween(String value1, String value2) {
            addCriterion("location not between", value1, value2, "location");
            return (Criteria) this;
        }

        public Criteria andLocationAreaIsNull() {
            addCriterion("location_area is null");
            return (Criteria) this;
        }

        public Criteria andLocationAreaIsNotNull() {
            addCriterion("location_area is not null");
            return (Criteria) this;
        }

        public Criteria andLocationAreaEqualTo(String value) {
            addCriterion("location_area =", value, "locationArea");
            return (Criteria) this;
        }

        public Criteria andLocationAreaNotEqualTo(String value) {
            addCriterion("location_area <>", value, "locationArea");
            return (Criteria) this;
        }

        public Criteria andLocationAreaGreaterThan(String value) {
            addCriterion("location_area >", value, "locationArea");
            return (Criteria) this;
        }

        public Criteria andLocationAreaGreaterThanOrEqualTo(String value) {
            addCriterion("location_area >=", value, "locationArea");
            return (Criteria) this;
        }

        public Criteria andLocationAreaLessThan(String value) {
            addCriterion("location_area <", value, "locationArea");
            return (Criteria) this;
        }

        public Criteria andLocationAreaLessThanOrEqualTo(String value) {
            addCriterion("location_area <=", value, "locationArea");
            return (Criteria) this;
        }

        public Criteria andLocationAreaLike(String value) {
            addCriterion("location_area like", value, "locationArea");
            return (Criteria) this;
        }

        public Criteria andLocationAreaNotLike(String value) {
            addCriterion("location_area not like", value, "locationArea");
            return (Criteria) this;
        }

        public Criteria andLocationAreaIn(List<String> values) {
            addCriterion("location_area in", values, "locationArea");
            return (Criteria) this;
        }

        public Criteria andLocationAreaNotIn(List<String> values) {
            addCriterion("location_area not in", values, "locationArea");
            return (Criteria) this;
        }

        public Criteria andLocationAreaBetween(String value1, String value2) {
            addCriterion("location_area between", value1, value2, "locationArea");
            return (Criteria) this;
        }

        public Criteria andLocationAreaNotBetween(String value1, String value2) {
            addCriterion("location_area not between", value1, value2, "locationArea");
            return (Criteria) this;
        }

        public Criteria andSubdistrictCodeIsNull() {
            addCriterion("subdistrict_code is null");
            return (Criteria) this;
        }

        public Criteria andSubdistrictCodeIsNotNull() {
            addCriterion("subdistrict_code is not null");
            return (Criteria) this;
        }

        public Criteria andSubdistrictCodeEqualTo(String value) {
            addCriterion("subdistrict_code =", value, "subdistrictCode");
            return (Criteria) this;
        }

        public Criteria andSubdistrictCodeNotEqualTo(String value) {
            addCriterion("subdistrict_code <>", value, "subdistrictCode");
            return (Criteria) this;
        }

        public Criteria andSubdistrictCodeGreaterThan(String value) {
            addCriterion("subdistrict_code >", value, "subdistrictCode");
            return (Criteria) this;
        }

        public Criteria andSubdistrictCodeGreaterThanOrEqualTo(String value) {
            addCriterion("subdistrict_code >=", value, "subdistrictCode");
            return (Criteria) this;
        }

        public Criteria andSubdistrictCodeLessThan(String value) {
            addCriterion("subdistrict_code <", value, "subdistrictCode");
            return (Criteria) this;
        }

        public Criteria andSubdistrictCodeLessThanOrEqualTo(String value) {
            addCriterion("subdistrict_code <=", value, "subdistrictCode");
            return (Criteria) this;
        }

        public Criteria andSubdistrictCodeLike(String value) {
            addCriterion("subdistrict_code like", value, "subdistrictCode");
            return (Criteria) this;
        }

        public Criteria andSubdistrictCodeNotLike(String value) {
            addCriterion("subdistrict_code not like", value, "subdistrictCode");
            return (Criteria) this;
        }

        public Criteria andSubdistrictCodeIn(List<String> values) {
            addCriterion("subdistrict_code in", values, "subdistrictCode");
            return (Criteria) this;
        }

        public Criteria andSubdistrictCodeNotIn(List<String> values) {
            addCriterion("subdistrict_code not in", values, "subdistrictCode");
            return (Criteria) this;
        }

        public Criteria andSubdistrictCodeBetween(String value1, String value2) {
            addCriterion("subdistrict_code between", value1, value2, "subdistrictCode");
            return (Criteria) this;
        }

        public Criteria andSubdistrictCodeNotBetween(String value1, String value2) {
            addCriterion("subdistrict_code not between", value1, value2, "subdistrictCode");
            return (Criteria) this;
        }

        public Criteria andSubdistrictNameIsNull() {
            addCriterion("subdistrict_name is null");
            return (Criteria) this;
        }

        public Criteria andSubdistrictNameIsNotNull() {
            addCriterion("subdistrict_name is not null");
            return (Criteria) this;
        }

        public Criteria andSubdistrictNameEqualTo(String value) {
            addCriterion("subdistrict_name =", value, "subdistrictName");
            return (Criteria) this;
        }

        public Criteria andSubdistrictNameNotEqualTo(String value) {
            addCriterion("subdistrict_name <>", value, "subdistrictName");
            return (Criteria) this;
        }

        public Criteria andSubdistrictNameGreaterThan(String value) {
            addCriterion("subdistrict_name >", value, "subdistrictName");
            return (Criteria) this;
        }

        public Criteria andSubdistrictNameGreaterThanOrEqualTo(String value) {
            addCriterion("subdistrict_name >=", value, "subdistrictName");
            return (Criteria) this;
        }

        public Criteria andSubdistrictNameLessThan(String value) {
            addCriterion("subdistrict_name <", value, "subdistrictName");
            return (Criteria) this;
        }

        public Criteria andSubdistrictNameLessThanOrEqualTo(String value) {
            addCriterion("subdistrict_name <=", value, "subdistrictName");
            return (Criteria) this;
        }

        public Criteria andSubdistrictNameLike(String value) {
            addCriterion("subdistrict_name like", value, "subdistrictName");
            return (Criteria) this;
        }

        public Criteria andSubdistrictNameNotLike(String value) {
            addCriterion("subdistrict_name not like", value, "subdistrictName");
            return (Criteria) this;
        }

        public Criteria andSubdistrictNameIn(List<String> values) {
            addCriterion("subdistrict_name in", values, "subdistrictName");
            return (Criteria) this;
        }

        public Criteria andSubdistrictNameNotIn(List<String> values) {
            addCriterion("subdistrict_name not in", values, "subdistrictName");
            return (Criteria) this;
        }

        public Criteria andSubdistrictNameBetween(String value1, String value2) {
            addCriterion("subdistrict_name between", value1, value2, "subdistrictName");
            return (Criteria) this;
        }

        public Criteria andSubdistrictNameNotBetween(String value1, String value2) {
            addCriterion("subdistrict_name not between", value1, value2, "subdistrictName");
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