package net.novaborn.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MailExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andUidIsNull() {
            addCriterion("`uid` is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("`uid` is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Integer value) {
            addCriterion("`uid` =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Integer value) {
            addCriterion("`uid` <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Integer value) {
            addCriterion("`uid` >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Integer value) {
            addCriterion("`uid` >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Integer value) {
            addCriterion("`uid` <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Integer value) {
            addCriterion("`uid` <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Integer> values) {
            addCriterion("`uid` in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Integer> values) {
            addCriterion("`uid` not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Integer value1, Integer value2) {
            addCriterion("`uid` between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Integer value1, Integer value2) {
            addCriterion("`uid` not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andMessagIdIsNull() {
            addCriterion("messagId is null");
            return (Criteria) this;
        }

        public Criteria andMessagIdIsNotNull() {
            addCriterion("messagId is not null");
            return (Criteria) this;
        }

        public Criteria andMessagIdEqualTo(String value) {
            addCriterion("messagId =", value, "messagId");
            return (Criteria) this;
        }

        public Criteria andMessagIdNotEqualTo(String value) {
            addCriterion("messagId <>", value, "messagId");
            return (Criteria) this;
        }

        public Criteria andMessagIdGreaterThan(String value) {
            addCriterion("messagId >", value, "messagId");
            return (Criteria) this;
        }

        public Criteria andMessagIdGreaterThanOrEqualTo(String value) {
            addCriterion("messagId >=", value, "messagId");
            return (Criteria) this;
        }

        public Criteria andMessagIdLessThan(String value) {
            addCriterion("messagId <", value, "messagId");
            return (Criteria) this;
        }

        public Criteria andMessagIdLessThanOrEqualTo(String value) {
            addCriterion("messagId <=", value, "messagId");
            return (Criteria) this;
        }

        public Criteria andMessagIdLike(String value) {
            addCriterion("messagId like", value, "messagId");
            return (Criteria) this;
        }

        public Criteria andMessagIdNotLike(String value) {
            addCriterion("messagId not like", value, "messagId");
            return (Criteria) this;
        }

        public Criteria andMessagIdIn(List<String> values) {
            addCriterion("messagId in", values, "messagId");
            return (Criteria) this;
        }

        public Criteria andMessagIdNotIn(List<String> values) {
            addCriterion("messagId not in", values, "messagId");
            return (Criteria) this;
        }

        public Criteria andMessagIdBetween(String value1, String value2) {
            addCriterion("messagId between", value1, value2, "messagId");
            return (Criteria) this;
        }

        public Criteria andMessagIdNotBetween(String value1, String value2) {
            addCriterion("messagId not between", value1, value2, "messagId");
            return (Criteria) this;
        }

        public Criteria andUserAddressIsNull() {
            addCriterion("userAddress is null");
            return (Criteria) this;
        }

        public Criteria andUserAddressIsNotNull() {
            addCriterion("userAddress is not null");
            return (Criteria) this;
        }

        public Criteria andUserAddressEqualTo(String value) {
            addCriterion("userAddress =", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressNotEqualTo(String value) {
            addCriterion("userAddress <>", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressGreaterThan(String value) {
            addCriterion("userAddress >", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressGreaterThanOrEqualTo(String value) {
            addCriterion("userAddress >=", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressLessThan(String value) {
            addCriterion("userAddress <", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressLessThanOrEqualTo(String value) {
            addCriterion("userAddress <=", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressLike(String value) {
            addCriterion("userAddress like", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressNotLike(String value) {
            addCriterion("userAddress not like", value, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressIn(List<String> values) {
            addCriterion("userAddress in", values, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressNotIn(List<String> values) {
            addCriterion("userAddress not in", values, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressBetween(String value1, String value2) {
            addCriterion("userAddress between", value1, value2, "userAddress");
            return (Criteria) this;
        }

        public Criteria andUserAddressNotBetween(String value1, String value2) {
            addCriterion("userAddress not between", value1, value2, "userAddress");
            return (Criteria) this;
        }

        public Criteria andAvatarIsNull() {
            addCriterion("avatar is null");
            return (Criteria) this;
        }

        public Criteria andAvatarIsNotNull() {
            addCriterion("avatar is not null");
            return (Criteria) this;
        }

        public Criteria andAvatarEqualTo(String value) {
            addCriterion("avatar =", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotEqualTo(String value) {
            addCriterion("avatar <>", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarGreaterThan(String value) {
            addCriterion("avatar >", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarGreaterThanOrEqualTo(String value) {
            addCriterion("avatar >=", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLessThan(String value) {
            addCriterion("avatar <", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLessThanOrEqualTo(String value) {
            addCriterion("avatar <=", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarLike(String value) {
            addCriterion("avatar like", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotLike(String value) {
            addCriterion("avatar not like", value, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarIn(List<String> values) {
            addCriterion("avatar in", values, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotIn(List<String> values) {
            addCriterion("avatar not in", values, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarBetween(String value1, String value2) {
            addCriterion("avatar between", value1, value2, "avatar");
            return (Criteria) this;
        }

        public Criteria andAvatarNotBetween(String value1, String value2) {
            addCriterion("avatar not between", value1, value2, "avatar");
            return (Criteria) this;
        }

        public Criteria andFromIsNull() {
            addCriterion("`from` is null");
            return (Criteria) this;
        }

        public Criteria andFromIsNotNull() {
            addCriterion("`from` is not null");
            return (Criteria) this;
        }

        public Criteria andFromEqualTo(String value) {
            addCriterion("`from` =", value, "from");
            return (Criteria) this;
        }

        public Criteria andFromNotEqualTo(String value) {
            addCriterion("`from` <>", value, "from");
            return (Criteria) this;
        }

        public Criteria andFromGreaterThan(String value) {
            addCriterion("`from` >", value, "from");
            return (Criteria) this;
        }

        public Criteria andFromGreaterThanOrEqualTo(String value) {
            addCriterion("`from` >=", value, "from");
            return (Criteria) this;
        }

        public Criteria andFromLessThan(String value) {
            addCriterion("`from` <", value, "from");
            return (Criteria) this;
        }

        public Criteria andFromLessThanOrEqualTo(String value) {
            addCriterion("`from` <=", value, "from");
            return (Criteria) this;
        }

        public Criteria andFromLike(String value) {
            addCriterion("`from` like", value, "from");
            return (Criteria) this;
        }

        public Criteria andFromNotLike(String value) {
            addCriterion("`from` not like", value, "from");
            return (Criteria) this;
        }

        public Criteria andFromIn(List<String> values) {
            addCriterion("`from` in", values, "from");
            return (Criteria) this;
        }

        public Criteria andFromNotIn(List<String> values) {
            addCriterion("`from` not in", values, "from");
            return (Criteria) this;
        }

        public Criteria andFromBetween(String value1, String value2) {
            addCriterion("`from` between", value1, value2, "from");
            return (Criteria) this;
        }

        public Criteria andFromNotBetween(String value1, String value2) {
            addCriterion("`from` not between", value1, value2, "from");
            return (Criteria) this;
        }

        public Criteria andFromEmailIsNull() {
            addCriterion("fromEmail is null");
            return (Criteria) this;
        }

        public Criteria andFromEmailIsNotNull() {
            addCriterion("fromEmail is not null");
            return (Criteria) this;
        }

        public Criteria andFromEmailEqualTo(String value) {
            addCriterion("fromEmail =", value, "fromEmail");
            return (Criteria) this;
        }

        public Criteria andFromEmailNotEqualTo(String value) {
            addCriterion("fromEmail <>", value, "fromEmail");
            return (Criteria) this;
        }

        public Criteria andFromEmailGreaterThan(String value) {
            addCriterion("fromEmail >", value, "fromEmail");
            return (Criteria) this;
        }

        public Criteria andFromEmailGreaterThanOrEqualTo(String value) {
            addCriterion("fromEmail >=", value, "fromEmail");
            return (Criteria) this;
        }

        public Criteria andFromEmailLessThan(String value) {
            addCriterion("fromEmail <", value, "fromEmail");
            return (Criteria) this;
        }

        public Criteria andFromEmailLessThanOrEqualTo(String value) {
            addCriterion("fromEmail <=", value, "fromEmail");
            return (Criteria) this;
        }

        public Criteria andFromEmailLike(String value) {
            addCriterion("fromEmail like", value, "fromEmail");
            return (Criteria) this;
        }

        public Criteria andFromEmailNotLike(String value) {
            addCriterion("fromEmail not like", value, "fromEmail");
            return (Criteria) this;
        }

        public Criteria andFromEmailIn(List<String> values) {
            addCriterion("fromEmail in", values, "fromEmail");
            return (Criteria) this;
        }

        public Criteria andFromEmailNotIn(List<String> values) {
            addCriterion("fromEmail not in", values, "fromEmail");
            return (Criteria) this;
        }

        public Criteria andFromEmailBetween(String value1, String value2) {
            addCriterion("fromEmail between", value1, value2, "fromEmail");
            return (Criteria) this;
        }

        public Criteria andFromEmailNotBetween(String value1, String value2) {
            addCriterion("fromEmail not between", value1, value2, "fromEmail");
            return (Criteria) this;
        }

        public Criteria andToIsNull() {
            addCriterion("`to` is null");
            return (Criteria) this;
        }

        public Criteria andToIsNotNull() {
            addCriterion("`to` is not null");
            return (Criteria) this;
        }

        public Criteria andToEqualTo(String value) {
            addCriterion("`to` =", value, "to");
            return (Criteria) this;
        }

        public Criteria andToNotEqualTo(String value) {
            addCriterion("`to` <>", value, "to");
            return (Criteria) this;
        }

        public Criteria andToGreaterThan(String value) {
            addCriterion("`to` >", value, "to");
            return (Criteria) this;
        }

        public Criteria andToGreaterThanOrEqualTo(String value) {
            addCriterion("`to` >=", value, "to");
            return (Criteria) this;
        }

        public Criteria andToLessThan(String value) {
            addCriterion("`to` <", value, "to");
            return (Criteria) this;
        }

        public Criteria andToLessThanOrEqualTo(String value) {
            addCriterion("`to` <=", value, "to");
            return (Criteria) this;
        }

        public Criteria andToLike(String value) {
            addCriterion("`to` like", value, "to");
            return (Criteria) this;
        }

        public Criteria andToNotLike(String value) {
            addCriterion("`to` not like", value, "to");
            return (Criteria) this;
        }

        public Criteria andToIn(List<String> values) {
            addCriterion("`to` in", values, "to");
            return (Criteria) this;
        }

        public Criteria andToNotIn(List<String> values) {
            addCriterion("`to` not in", values, "to");
            return (Criteria) this;
        }

        public Criteria andToBetween(String value1, String value2) {
            addCriterion("`to` between", value1, value2, "to");
            return (Criteria) this;
        }

        public Criteria andToNotBetween(String value1, String value2) {
            addCriterion("`to` not between", value1, value2, "to");
            return (Criteria) this;
        }

        public Criteria andToEmailIsNull() {
            addCriterion("toEmail is null");
            return (Criteria) this;
        }

        public Criteria andToEmailIsNotNull() {
            addCriterion("toEmail is not null");
            return (Criteria) this;
        }

        public Criteria andToEmailEqualTo(String value) {
            addCriterion("toEmail =", value, "toEmail");
            return (Criteria) this;
        }

        public Criteria andToEmailNotEqualTo(String value) {
            addCriterion("toEmail <>", value, "toEmail");
            return (Criteria) this;
        }

        public Criteria andToEmailGreaterThan(String value) {
            addCriterion("toEmail >", value, "toEmail");
            return (Criteria) this;
        }

        public Criteria andToEmailGreaterThanOrEqualTo(String value) {
            addCriterion("toEmail >=", value, "toEmail");
            return (Criteria) this;
        }

        public Criteria andToEmailLessThan(String value) {
            addCriterion("toEmail <", value, "toEmail");
            return (Criteria) this;
        }

        public Criteria andToEmailLessThanOrEqualTo(String value) {
            addCriterion("toEmail <=", value, "toEmail");
            return (Criteria) this;
        }

        public Criteria andToEmailLike(String value) {
            addCriterion("toEmail like", value, "toEmail");
            return (Criteria) this;
        }

        public Criteria andToEmailNotLike(String value) {
            addCriterion("toEmail not like", value, "toEmail");
            return (Criteria) this;
        }

        public Criteria andToEmailIn(List<String> values) {
            addCriterion("toEmail in", values, "toEmail");
            return (Criteria) this;
        }

        public Criteria andToEmailNotIn(List<String> values) {
            addCriterion("toEmail not in", values, "toEmail");
            return (Criteria) this;
        }

        public Criteria andToEmailBetween(String value1, String value2) {
            addCriterion("toEmail between", value1, value2, "toEmail");
            return (Criteria) this;
        }

        public Criteria andToEmailNotBetween(String value1, String value2) {
            addCriterion("toEmail not between", value1, value2, "toEmail");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNull() {
            addCriterion("sendTime is null");
            return (Criteria) this;
        }

        public Criteria andSendTimeIsNotNull() {
            addCriterion("sendTime is not null");
            return (Criteria) this;
        }

        public Criteria andSendTimeEqualTo(Date value) {
            addCriterionForJDBCDate("sendTime =", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("sendTime <>", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("sendTime >", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("sendTime >=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThan(Date value) {
            addCriterionForJDBCDate("sendTime <", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("sendTime <=", value, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeIn(List<Date> values) {
            addCriterionForJDBCDate("sendTime in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("sendTime not in", values, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("sendTime between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSendTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("sendTime not between", value1, value2, "sendTime");
            return (Criteria) this;
        }

        public Criteria andSubjectIsNull() {
            addCriterion("subject is null");
            return (Criteria) this;
        }

        public Criteria andSubjectIsNotNull() {
            addCriterion("subject is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectEqualTo(String value) {
            addCriterion("subject =", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotEqualTo(String value) {
            addCriterion("subject <>", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectGreaterThan(String value) {
            addCriterion("subject >", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectGreaterThanOrEqualTo(String value) {
            addCriterion("subject >=", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLessThan(String value) {
            addCriterion("subject <", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLessThanOrEqualTo(String value) {
            addCriterion("subject <=", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLike(String value) {
            addCriterion("subject like", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotLike(String value) {
            addCriterion("subject not like", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectIn(List<String> values) {
            addCriterion("subject in", values, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotIn(List<String> values) {
            addCriterion("subject not in", values, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectBetween(String value1, String value2) {
            addCriterion("subject between", value1, value2, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotBetween(String value1, String value2) {
            addCriterion("subject not between", value1, value2, "subject");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(Integer value) {
            addCriterion("flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(Integer value) {
            addCriterion("flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(Integer value) {
            addCriterion("flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(Integer value) {
            addCriterion("flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(Integer value) {
            addCriterion("flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<Integer> values) {
            addCriterion("flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<Integer> values) {
            addCriterion("flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(Integer value1, Integer value2) {
            addCriterion("flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("flag not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNull() {
            addCriterion("deleted is null");
            return (Criteria) this;
        }

        public Criteria andDeletedIsNotNull() {
            addCriterion("deleted is not null");
            return (Criteria) this;
        }

        public Criteria andDeletedEqualTo(Integer value) {
            addCriterion("deleted =", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotEqualTo(Integer value) {
            addCriterion("deleted <>", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThan(Integer value) {
            addCriterion("deleted >", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedGreaterThanOrEqualTo(Integer value) {
            addCriterion("deleted >=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThan(Integer value) {
            addCriterion("deleted <", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedLessThanOrEqualTo(Integer value) {
            addCriterion("deleted <=", value, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedIn(List<Integer> values) {
            addCriterion("deleted in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotIn(List<Integer> values) {
            addCriterion("deleted not in", values, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedBetween(Integer value1, Integer value2) {
            addCriterion("deleted between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andDeletedNotBetween(Integer value1, Integer value2) {
            addCriterion("deleted not between", value1, value2, "deleted");
            return (Criteria) this;
        }

        public Criteria andDraftIsNull() {
            addCriterion("draft is null");
            return (Criteria) this;
        }

        public Criteria andDraftIsNotNull() {
            addCriterion("draft is not null");
            return (Criteria) this;
        }

        public Criteria andDraftEqualTo(Integer value) {
            addCriterion("draft =", value, "draft");
            return (Criteria) this;
        }

        public Criteria andDraftNotEqualTo(Integer value) {
            addCriterion("draft <>", value, "draft");
            return (Criteria) this;
        }

        public Criteria andDraftGreaterThan(Integer value) {
            addCriterion("draft >", value, "draft");
            return (Criteria) this;
        }

        public Criteria andDraftGreaterThanOrEqualTo(Integer value) {
            addCriterion("draft >=", value, "draft");
            return (Criteria) this;
        }

        public Criteria andDraftLessThan(Integer value) {
            addCriterion("draft <", value, "draft");
            return (Criteria) this;
        }

        public Criteria andDraftLessThanOrEqualTo(Integer value) {
            addCriterion("draft <=", value, "draft");
            return (Criteria) this;
        }

        public Criteria andDraftIn(List<Integer> values) {
            addCriterion("draft in", values, "draft");
            return (Criteria) this;
        }

        public Criteria andDraftNotIn(List<Integer> values) {
            addCriterion("draft not in", values, "draft");
            return (Criteria) this;
        }

        public Criteria andDraftBetween(Integer value1, Integer value2) {
            addCriterion("draft between", value1, value2, "draft");
            return (Criteria) this;
        }

        public Criteria andDraftNotBetween(Integer value1, Integer value2) {
            addCriterion("draft not between", value1, value2, "draft");
            return (Criteria) this;
        }

        public Criteria andSeenIsNull() {
            addCriterion("seen is null");
            return (Criteria) this;
        }

        public Criteria andSeenIsNotNull() {
            addCriterion("seen is not null");
            return (Criteria) this;
        }

        public Criteria andSeenEqualTo(Integer value) {
            addCriterion("seen =", value, "seen");
            return (Criteria) this;
        }

        public Criteria andSeenNotEqualTo(Integer value) {
            addCriterion("seen <>", value, "seen");
            return (Criteria) this;
        }

        public Criteria andSeenGreaterThan(Integer value) {
            addCriterion("seen >", value, "seen");
            return (Criteria) this;
        }

        public Criteria andSeenGreaterThanOrEqualTo(Integer value) {
            addCriterion("seen >=", value, "seen");
            return (Criteria) this;
        }

        public Criteria andSeenLessThan(Integer value) {
            addCriterion("seen <", value, "seen");
            return (Criteria) this;
        }

        public Criteria andSeenLessThanOrEqualTo(Integer value) {
            addCriterion("seen <=", value, "seen");
            return (Criteria) this;
        }

        public Criteria andSeenIn(List<Integer> values) {
            addCriterion("seen in", values, "seen");
            return (Criteria) this;
        }

        public Criteria andSeenNotIn(List<Integer> values) {
            addCriterion("seen not in", values, "seen");
            return (Criteria) this;
        }

        public Criteria andSeenBetween(Integer value1, Integer value2) {
            addCriterion("seen between", value1, value2, "seen");
            return (Criteria) this;
        }

        public Criteria andSeenNotBetween(Integer value1, Integer value2) {
            addCriterion("seen not between", value1, value2, "seen");
            return (Criteria) this;
        }

        public Criteria andMailBoxIsNull() {
            addCriterion("mailBox is null");
            return (Criteria) this;
        }

        public Criteria andMailBoxIsNotNull() {
            addCriterion("mailBox is not null");
            return (Criteria) this;
        }

        public Criteria andMailBoxEqualTo(String value) {
            addCriterion("mailBox =", value, "mailBox");
            return (Criteria) this;
        }

        public Criteria andMailBoxNotEqualTo(String value) {
            addCriterion("mailBox <>", value, "mailBox");
            return (Criteria) this;
        }

        public Criteria andMailBoxGreaterThan(String value) {
            addCriterion("mailBox >", value, "mailBox");
            return (Criteria) this;
        }

        public Criteria andMailBoxGreaterThanOrEqualTo(String value) {
            addCriterion("mailBox >=", value, "mailBox");
            return (Criteria) this;
        }

        public Criteria andMailBoxLessThan(String value) {
            addCriterion("mailBox <", value, "mailBox");
            return (Criteria) this;
        }

        public Criteria andMailBoxLessThanOrEqualTo(String value) {
            addCriterion("mailBox <=", value, "mailBox");
            return (Criteria) this;
        }

        public Criteria andMailBoxLike(String value) {
            addCriterion("mailBox like", value, "mailBox");
            return (Criteria) this;
        }

        public Criteria andMailBoxNotLike(String value) {
            addCriterion("mailBox not like", value, "mailBox");
            return (Criteria) this;
        }

        public Criteria andMailBoxIn(List<String> values) {
            addCriterion("mailBox in", values, "mailBox");
            return (Criteria) this;
        }

        public Criteria andMailBoxNotIn(List<String> values) {
            addCriterion("mailBox not in", values, "mailBox");
            return (Criteria) this;
        }

        public Criteria andMailBoxBetween(String value1, String value2) {
            addCriterion("mailBox between", value1, value2, "mailBox");
            return (Criteria) this;
        }

        public Criteria andMailBoxNotBetween(String value1, String value2) {
            addCriterion("mailBox not between", value1, value2, "mailBox");
            return (Criteria) this;
        }

        public Criteria andSizeIsNull() {
            addCriterion("`size` is null");
            return (Criteria) this;
        }

        public Criteria andSizeIsNotNull() {
            addCriterion("`size` is not null");
            return (Criteria) this;
        }

        public Criteria andSizeEqualTo(Integer value) {
            addCriterion("`size` =", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotEqualTo(Integer value) {
            addCriterion("`size` <>", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThan(Integer value) {
            addCriterion("`size` >", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("`size` >=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThan(Integer value) {
            addCriterion("`size` <", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeLessThanOrEqualTo(Integer value) {
            addCriterion("`size` <=", value, "size");
            return (Criteria) this;
        }

        public Criteria andSizeIn(List<Integer> values) {
            addCriterion("`size` in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotIn(List<Integer> values) {
            addCriterion("`size` not in", values, "size");
            return (Criteria) this;
        }

        public Criteria andSizeBetween(Integer value1, Integer value2) {
            addCriterion("`size` between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("`size` not between", value1, value2, "size");
            return (Criteria) this;
        }

        public Criteria andTextContentIsNull() {
            addCriterion("textContent is null");
            return (Criteria) this;
        }

        public Criteria andTextContentIsNotNull() {
            addCriterion("textContent is not null");
            return (Criteria) this;
        }

        public Criteria andTextContentEqualTo(String value) {
            addCriterion("textContent =", value, "textContent");
            return (Criteria) this;
        }

        public Criteria andTextContentNotEqualTo(String value) {
            addCriterion("textContent <>", value, "textContent");
            return (Criteria) this;
        }

        public Criteria andTextContentGreaterThan(String value) {
            addCriterion("textContent >", value, "textContent");
            return (Criteria) this;
        }

        public Criteria andTextContentGreaterThanOrEqualTo(String value) {
            addCriterion("textContent >=", value, "textContent");
            return (Criteria) this;
        }

        public Criteria andTextContentLessThan(String value) {
            addCriterion("textContent <", value, "textContent");
            return (Criteria) this;
        }

        public Criteria andTextContentLessThanOrEqualTo(String value) {
            addCriterion("textContent <=", value, "textContent");
            return (Criteria) this;
        }

        public Criteria andTextContentLike(String value) {
            addCriterion("textContent like", value, "textContent");
            return (Criteria) this;
        }

        public Criteria andTextContentNotLike(String value) {
            addCriterion("textContent not like", value, "textContent");
            return (Criteria) this;
        }

        public Criteria andTextContentIn(List<String> values) {
            addCriterion("textContent in", values, "textContent");
            return (Criteria) this;
        }

        public Criteria andTextContentNotIn(List<String> values) {
            addCriterion("textContent not in", values, "textContent");
            return (Criteria) this;
        }

        public Criteria andTextContentBetween(String value1, String value2) {
            addCriterion("textContent between", value1, value2, "textContent");
            return (Criteria) this;
        }

        public Criteria andTextContentNotBetween(String value1, String value2) {
            addCriterion("textContent not between", value1, value2, "textContent");
            return (Criteria) this;
        }

        public Criteria andReplyToIsNull() {
            addCriterion("replyTo is null");
            return (Criteria) this;
        }

        public Criteria andReplyToIsNotNull() {
            addCriterion("replyTo is not null");
            return (Criteria) this;
        }

        public Criteria andReplyToEqualTo(String value) {
            addCriterion("replyTo =", value, "replyTo");
            return (Criteria) this;
        }

        public Criteria andReplyToNotEqualTo(String value) {
            addCriterion("replyTo <>", value, "replyTo");
            return (Criteria) this;
        }

        public Criteria andReplyToGreaterThan(String value) {
            addCriterion("replyTo >", value, "replyTo");
            return (Criteria) this;
        }

        public Criteria andReplyToGreaterThanOrEqualTo(String value) {
            addCriterion("replyTo >=", value, "replyTo");
            return (Criteria) this;
        }

        public Criteria andReplyToLessThan(String value) {
            addCriterion("replyTo <", value, "replyTo");
            return (Criteria) this;
        }

        public Criteria andReplyToLessThanOrEqualTo(String value) {
            addCriterion("replyTo <=", value, "replyTo");
            return (Criteria) this;
        }

        public Criteria andReplyToLike(String value) {
            addCriterion("replyTo like", value, "replyTo");
            return (Criteria) this;
        }

        public Criteria andReplyToNotLike(String value) {
            addCriterion("replyTo not like", value, "replyTo");
            return (Criteria) this;
        }

        public Criteria andReplyToIn(List<String> values) {
            addCriterion("replyTo in", values, "replyTo");
            return (Criteria) this;
        }

        public Criteria andReplyToNotIn(List<String> values) {
            addCriterion("replyTo not in", values, "replyTo");
            return (Criteria) this;
        }

        public Criteria andReplyToBetween(String value1, String value2) {
            addCriterion("replyTo between", value1, value2, "replyTo");
            return (Criteria) this;
        }

        public Criteria andReplyToNotBetween(String value1, String value2) {
            addCriterion("replyTo not between", value1, value2, "replyTo");
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