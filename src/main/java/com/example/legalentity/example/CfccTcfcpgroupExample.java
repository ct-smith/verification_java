package com.example.legalentity.example;

import java.time.LocalDateTime;
import java.util.List;

public class CfccTcfcpgroupExample {

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    protected abstract static class GeneratedCriteria {

        public Criteria andCpGroupIdIn(List<String> values) {
            return (Criteria) this;
        }

        public Criteria andEntityIdEqualTo(String value) {
            return (Criteria) this;
        }

        public Criteria andEntityIdLike(String value) {
            return (Criteria) this;
        }

        public Criteria andHOLocationEqualTo(String values) {
            return (Criteria) this;
        }

        public Criteria andHOLocationIn(List<String> value) {
            return (Criteria) this;
        }

        public Criteria andTypeOfIndustryEqualTo(String value) {
            return (Criteria) this;
        }

        public Criteria andBisIndustryEqualTo(String value) {
            return (Criteria) this;
        }

        public Criteria andCpCreditTypeEqualTo(String value) {
            return (Criteria) this;
        }

        public Criteria andCreditSecCodeEqualTo(String value) {
            return (Criteria) this;
        }

        public Criteria andIsdaMemberEqualTo(String value) {
            return (Criteria) this;
        }

        public Criteria andAttentionSignEqualTo(String value) {
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(LocalDateTime value) {
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(LocalDateTime value) {
            return (Criteria) this;
        }

        public Criteria andUpdateDateGreaterThanOrEqualTo(LocalDateTime value) {
            return (Criteria) this;
        }

        public Criteria andUpdateDateLessThanOrEqualTo(LocalDateTime value) {
            return (Criteria) this;
        }

        public Criteria andTemporarySignEqualTo(String value) {
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
