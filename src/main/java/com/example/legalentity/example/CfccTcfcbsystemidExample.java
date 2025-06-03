package com.example.legalentity.example;

import java.util.List;

public class CfccTcfcbsystemidExample {

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

        public Criteria andBondSignIn(List<String> values) {
            return (Criteria) this;
        }

        public Criteria andSystemSign3In(List<String> values) {
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
