/*
 * Copyright © 2024 Gregory P. Moyer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.syphr.wordplay.lang.french;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class WordsDictionaryTest
{
    @ParameterizedTest
    @CsvSource(nullValues = "NULL",
               value = { "NULL,false",
                         ",false",
                         "a,false",
                         "tu,true",
                         "ils,true",
                         "vEuX, true",
                         "VOULEZ,true",
                         "amorçât,true",
                         "amorcat,true",
                         "a-t-elle,true",
                         "atelle,true" })
    void isValid(String word, boolean expected)
    {
        // given
        var dictionary = new WordsDictionary();

        // when
        boolean result = dictionary.isValid(word);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void getWords()
    {
        // given
        var dictionary = new WordsDictionary();

        // when
        Set<String> result = dictionary.getWords();

        // then
        assertThat(result).hasSize(323417);
    }
}
