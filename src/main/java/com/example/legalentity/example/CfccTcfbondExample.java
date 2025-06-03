package com.example.legalentity.example;

public class CfccTcfbondExample {

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
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}
