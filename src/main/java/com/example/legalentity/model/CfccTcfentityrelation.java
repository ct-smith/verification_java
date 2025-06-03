package com.example.legalentity.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;
// import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Accessors(chain = true)
@Data
// @EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CfccTcfentityrelation {

    private String entityRelationType;

    private String entityRelationId;

    private String mainEntityId;

    private String entityId;

    private String userId;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private String recordStatus;

    private BigDecimal rowId;
}
