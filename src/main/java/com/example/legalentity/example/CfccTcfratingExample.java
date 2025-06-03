package com.example.legalentity.example;

import java.time.LocalDateTime;

public class CfccTcfratingExample {

    protected String orderByClause;

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    protected abstract static class GeneratedCriteria {

        public Criteria andMaturityFlgEqualTo(String value) {
            return (Criteria) this;
        }

        public Criteria andSystemIdqualTo(String value) {
            return (Criteria) this;
        }

        public Criteria andIssueCodeEqualTo(String value) {
            return (Criteria) this;
        }

        public Criteria andIssueCodeLike(String value) {
            return (Criteria) this;
        }

        public Criteria andRecordStatusEqualTo(String value) {
            return (Criteria) this;
        }

        public Criteria andAppliedDateFromEqualTo(LocalDateTime value) {
            return (Criteria) this;
        }

        public Criteria andAppliedDateFromLessThanOrEqualTo(LocalDateTime value) {
            return (Criteria) this;
        }

        public Criteria andAppliedDateToGreaterThanOrEqualTo(LocalDateTime value) {
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}
