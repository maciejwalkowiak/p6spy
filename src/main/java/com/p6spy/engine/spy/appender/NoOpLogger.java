/**
 * P6Spy
 *
 * Copyright (C) 2002 P6Spy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.p6spy.engine.spy.appender;

import com.p6spy.engine.logging.Category;

/**
 * Logger that instead of logging does nothing.
 * Meant to be used to easily disable logging when other event listeners are in use.
 *
 * @author Maciej Walkowiak
 */
public class NoOpLogger implements P6Logger {
  @Override public void logSQL(int connectionId, String now, long elapsed, Category category,
    String prepared, String sql, String url) {
  }

  @Override public void logException(Exception e) {
  }

  @Override public void logText(String text) {
  }

  @Override public boolean isCategoryEnabled(Category category) {
    return false;
  }
}
