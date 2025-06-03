package com.example.legalentity.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Accessors(chain = true)
@Data
public class CfccTcfratingKey {

    private String systemId;

    private String issueCode;

    private LocalDateTime appliedDateFrom;
}
