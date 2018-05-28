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

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.ValueRange;

import lombok.extern.slf4j.Slf4j;

/**
 * @author NARH https://github.com/NARH
 *
 */
@Slf4j
public class GoogleSheetAPITest {

  private static final String APPLICATION_NAME = "Google Sheets API Java Quickstart";
  private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
  private static final String CREDENTIALS_FOLDER = "credentials"; // Directory to store user credentials.

  private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
  private static final String CLIENT_SECRET_DIR = "client_secret.json";

  /**
   *
   * @param HTTP_TRANSPORT
   * @return
   * @throws IOException
   */
  private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT, List<String> scopes) throws IOException {
    // Load client secrets.
    InputStream in = GoogleSheetAPITest.class.getClassLoader().getResourceAsStream(CLIENT_SECRET_DIR);
    GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

    // Build flow and trigger user authorization request.
    GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, scopes)
          .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(CREDENTIALS_FOLDER)))
          .setAccessType("offline")
          .build();
    // create Credential
    return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
  }

  /**
   * Google Sheet の値を読み込む
   *
   * https://docs.google.com/spreadsheets/d/1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms/edit#gid=0
   *
   * @throws Exception
   */
  @Test
  public void testReadFromSheetValue() throws Exception {
    final List<String> majorList = new ArrayList<>();
    majorList.add("Art");
    majorList.add("English");
    majorList.add("Math");
    majorList.add("Physics");

    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    final String spreadsheetId = "1BxiMVs0XRA5nFMdKvBdBZjgmUUqptlbs74OgvE2upms";
    final String range = "Class Data!A2:E";

    Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT, SCOPES))
      .setApplicationName(APPLICATION_NAME)
      .build();
    ValueRange response = service.spreadsheets().values()
      .get(spreadsheetId, range)
      .execute();

    List<List<Object>> values = response.getValues();
    assertThat("values が null でないこと", values, not(nullValue()));
    assertThat("データ件数が 30件であること", values.size(), is(30));
    for(List<Object> row : values) {
      log.info("{}\t{}", row.get(0), row.get(4));
      assertThat("major がリストにあること", majorList, hasItem(row.get(4).toString()));
    }
  }

  /**
   * 新規のスプレッドシートを作成する
   *
   * @throws Exception
   */
  @Test
  public void testCreateSheet() throws Exception {
    Spreadsheet spreadsheet = new Spreadsheet();
    /*
    List<Sheet> sheetData = new ArrayList<>();
    sheetData.add(new Sheet());
    sheetData.get(0).set("A1", "Foo");
    sheetData.get(0).set("B1", "Bar");
    spreadsheet.setSheets(sheetData);
    */

    final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    Sheets sheets = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT, Arrays.asList(SheetsScopes.DRIVE, SheetsScopes.DRIVE_FILE, SheetsScopes.SPREADSHEETS)))
      .setApplicationName(APPLICATION_NAME)
      .build();

   Sheets.Spreadsheets.Create request = sheets.spreadsheets().create(spreadsheet);
   log.info("request json: {}", request.toString());
   Spreadsheet response = request.execute();
   log.info("response json: {}", response.toPrettyString());
  }

  @Test
  public void testAppendToSheetValue() throws Exception {

  }
}
