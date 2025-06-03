package com.example.legalentity.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class SiComponentHolder {

    /** メッセージソースアクセサ */
    @Autowired
    private MessageSourceAccessor messageSourceAccessor;

}
