package com.example.legalentity.model;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Accessors(chain = true)
@Data
@EqualsAndHashCode(callSuper = true)
public class CfccTcfdatetime extends CfccTcfdatetimeKey {

    private LocalDateTime operationDate;

}
