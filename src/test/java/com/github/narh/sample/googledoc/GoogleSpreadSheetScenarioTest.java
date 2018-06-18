/*
 * Copyright (c) 2018, NARH https://github.com/NARH
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * * Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 * * Neither the name of the copyright holder nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.github.narh.sample.googledoc;

import java.util.List;

import org.junit.Test;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import lombok.extern.slf4j.Slf4j;

/**
 * @author NARH https://github.com/NARH
 *
 */
@Slf4j
public class GoogleSpreadSheetScenarioTest {

  /**
   * 初回のシナリオ
   *
   * @throws Exception
   */
  @Test
  public void testインストール直後の振る舞い() throws Exception {
    testフォルダがあるか判定する();
    testフォルダを作成する();
  }

  /**
   * 年初のシナリオ
   *
   * @throws Exception
   */
  @Test
  public void test年初の振る舞い() throws Exception {
    test新規ブックがあるか判定する();
    test新規ブックを作成する();
    testスプレッドシートにデータを書き込む();
    testスプレッドシートにデータを追加する();
  }

  /**
   * 月初のシナリオ
   *
   * @throws Exception
   */
  @Test
  public void test月初の振る舞い() throws Exception{
    test新規シートがあるか判定する();
    test新規シートを作成する();
    testスプレッドシートにデータを書き込む();
    testスプレッドシートにデータを追加する();
  }

  /**
   * 日々のシナリオ
   *
   * @throws Exception
   */
  @Test
  public void test日々の振る舞い() throws Exception {
    testスプレッドシートにデータを追加する();
  }

  /**
   * folder名 02.見積もり
   * book名 取引履歴_2018
   * sheet名 yyyymm
   *
   * @throws Exception
   */
  protected void test新規ブックを作成する() throws Exception {
    if(log.isInfoEnabled()) log.info("#### 新規ブックを作成する #### --- begin");
    if(log.isInfoEnabled()) log.info("#### 新規ブックを作成する #### --- end");
  }

  /**
   * folder名 02.見積もり
   * book名 取引履歴_2018
   *
   * @throws Exception
   */
  protected void test新規ブックがあるか判定する() throws Exception {
    if(log.isInfoEnabled()) log.info("#### 新規ブックがあるか判定する #### --- begin");
    if(log.isInfoEnabled()) log.info("#### 新規ブックがあるか判定する #### --- end");
  }

  /**
   * book名 取引履歴_2018
   * sheet名 yyyymm
   *
   * @throws Exception
   */
  protected void test新規シートがあるか判定する() throws Exception {
    if(log.isInfoEnabled()) log.info("#### 新規シートがあるか判定する #### --- begin");
    if(log.isInfoEnabled()) log.info("#### 新規シートがあるか判定する #### --- end");
  }

  /**
   * book名 取引履歴_2018
   * sheet名 yyyymm
   *
   * @throws Exception
   */
  protected void test新規シートを作成する() throws Exception {
    if(log.isInfoEnabled()) log.info("#### 新規シートを作成する #### --- begin");
    if(log.isInfoEnabled()) log.info("#### 新規シートを作成する #### --- end");
  }

  /**
   * book名 取引履歴_2018
   * sheet名 yyyymm
   *  A2: 取引ID yyyyMMdd-0001
   *  B2: 依頼日 yyyy-MM-dd HH:mm:ss (datetime)
   *  C2: 社名
   *  D2: 担当者名
   *  E2: メールアドレス
   *  F2: 商品コード
   *  G2: 商品名
   *  H2: ㎡数
   *  I2: 物件名
   *  J2: 工期 2018/12/1 〜 2018/12/31
   *  K2: 送信済みステータス
   * @throws Exception
   */
  protected void testスプレッドシートにデータを書き込む() throws Exception {
    if(log.isInfoEnabled()) log.info("#### スプレッドシートにデータを書き込む #### --- begin");
    if(log.isInfoEnabled()) log.info("#### スプレッドシートにデータを書き込む #### --- end");
  }

  /**
   * book名 取引履歴_2018
   * sheet名 yyyymm
   *  A2: 取引ID yyyyMMdd-0001
   *  B2: 依頼日 yyyy-MM-dd HH:mm:ss (datetime)
   *  C2: 社名
   *  D2: 担当者名
   *  E2: メールアドレス
   *  F2: 商品コード
   *  G2: 商品名
   *  H2: ㎡数
   *  I2: 物件名
   *  J2: 工期 2018/12/1 〜 2018/12/31
   *  K2: 送信済みステータス
   *
   * @throws Exception
   */
  protected void testスプレッドシートにデータを追加する() throws Exception {
    if(log.isInfoEnabled()) log.info("#### スプレッドシートにデータを追加する #### --- begin");
    if(log.isInfoEnabled()) log.info("#### スプレッドシートにデータを追加する #### --- end");
  }

  /**
   * folder名 02.見積もり
   *
   * @throws Exception
   */
  protected void testフォルダがあるか判定する() throws Exception {
    if(log.isInfoEnabled()) log.info("#### フォルダがあるか判定する #### --- begin");
    Drive service = GoogleDrive.Builder.build();
    FileList result = service.files().list()
        .setPageSize(10)
        .setFields("nextPageToken, files(id, name)")
        .execute();
    List<File> files = result.getFiles();
    if(null != files && !files.isEmpty()) {
      files.stream().forEach(f->log.info("- {}({})",f.getName(), f.getId()));
    }
    if(log.isInfoEnabled()) log.info("#### フォルダがあるか判定する #### --- end");
  }

  /**
   * folder名 02.見積もり
   *
   * @throws Exception
   */
  protected void testフォルダを作成する() throws Exception {
    if(log.isInfoEnabled()) log.info("#### フォルダを作成する #### --- begin");
    if(log.isInfoEnabled()) log.info("#### フォルダを作成する #### --- end");
  }
}
