package com.example.legalentity.example;

public class CfccTcfcbcountryExample {

    protected String orderByClause;

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    protected abstract static class GeneratedCriteria {

        public Criteria andAreaCodeEqualTo(String value) {
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
