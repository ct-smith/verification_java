package com.example.legalentity;

import org.springframework.stereotype.Component;

import com.example.legalentity.common.ScreenMode;
import com.example.legalentity.model.CsvCommonDto;

import lombok.RequiredArgsConstructor;

/**
 * LegalEntity登録 帳票出力クラス
 * 
 * 機能ID:CFIC000101
 * 
 * @author wild idea
 */
@RequiredArgsConstructor
@Component
public class LegalEntityPrint {

    /**
     * 帳票出力メソッド
     * 
     * @param newData    帳票出力対象データ(新)
     * @param oldData    帳票出力対象データ(旧)
     * @param screenMode 起動モード
     * @param printMode  プリントモード
     * @param UserId     ユーザID
     * @return プリントID
     */
    public String printOut(CsvCommonDto newData, CsvCommonDto oldData, ScreenMode screenMode, Integer printMode,
            String UserId) {

        return null;

    }
}
