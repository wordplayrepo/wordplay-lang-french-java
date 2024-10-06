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
import static org.assertj.core.api.Assertions.catchThrowable;

import java.util.List;
import java.util.SequencedSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.syphr.wordplay.core.lang.InvalidCharacterException;
import org.syphr.wordplay.core.lang.Letter;

class FrenchLetterFactoryTest
{
    @ParameterizedTest
    @ValueSource(chars = { 'a', 'A', 'â', 'à' })
    void toLetter_Char_AnyCaseOrAccentValid(char input)
    {
        // given
        var factory = new FrenchLetterFactory();

        // when
        Letter result = factory.toLetter(input);

        // then
        assertThat(result).isSameAs(FrenchLetter.A);
    }

    @Test
    void toLetter_Char_Invalid()
    {
        // given
        var factory = new FrenchLetterFactory();

        // when
        var result = catchThrowable(() -> factory.toLetter('\n'));

        // then
        assertThat(result).isInstanceOf(InvalidCharacterException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = { "a", "A", "â", "à" })
    void toLetter_String_AnyCaseOrAccentValid(String input)
    {
        // given
        var factory = new FrenchLetterFactory();

        // when
        Letter result = factory.toLetter(input);

        // then
        assertThat(result).isSameAs(FrenchLetter.A);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = { "", "ab" })
    void toLetter_String_BadInput(String input)
    {
        // given
        var factory = new FrenchLetterFactory();

        // when
        var result = catchThrowable(() -> factory.toLetter(input));

        // then
        assertThat(result).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void toLetter_String_Invalid()
    {
        // given
        var factory = new FrenchLetterFactory();

        // when
        var result = catchThrowable(() -> factory.toLetter("#"));

        // then
        assertThat(result).isInstanceOf(InvalidCharacterException.class);
    }

    @Test
    void toLetters_NullInput()
    {
        // given
        var factory = new FrenchLetterFactory();

        // when
        List<Letter> result = factory.toLetters(null);

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void toLetters_EmptyInput()
    {
        // given
        var factory = new FrenchLetterFactory();

        // when
        List<Letter> result = factory.toLetters("");

        // then
        assertThat(result).isEmpty();
    }

    @Test
    void toLetters_SingleCharacterInput()
    {
        // given
        var factory = new FrenchLetterFactory();

        // when
        List<Letter> result = factory.toLetters("a");

        // then
        assertThat(result).contains(FrenchLetter.A);
    }

    @Test
    void toLetters_MultipleCharacterInput()
    {
        // given
        var factory = new FrenchLetterFactory();

        // when
        List<Letter> result = factory.toLetters("ab");

        // then
        assertThat(result).contains(FrenchLetter.A, FrenchLetter.B);
    }

    @Test
    void getLetters()
    {
        // given
        var factory = new FrenchLetterFactory();

        // when
        SequencedSet<Letter> result = factory.getLetters();

        // then
        assertThat(result).contains(FrenchLetter.values());
    }
}
