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

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.SequencedSet;

import org.apache.commons.lang3.StringUtils;
import org.syphr.wordplay.core.lang.InvalidCharacterException;
import org.syphr.wordplay.core.lang.Letter;
import org.syphr.wordplay.core.lang.LetterFactory;

public class FrenchLetterFactory implements LetterFactory
{
    @Override
    public Letter toLetter(char character)
    {
        return toLetter(String.valueOf(character));
    }

    @Override
    public Letter toLetter(String character)
    {
        if (character == null || character.isEmpty() || character.length() > 1) {
            throw new IllegalArgumentException("The given input is missing or two long to be converted to a letter");
        }

        try {
            return FrenchLetter.valueOf(StringUtils.stripAccents(character).toUpperCase());
        } catch (RuntimeException e) {
            throw new InvalidCharacterException("Failed to convert '" + character + "' to a letter", e);
        }
    }

    @Override
    public List<Letter> toLetters(String word)
    {
        if (word == null) {
            return List.of();
        }

        return word.chars().mapToObj(c -> toLetter((char) c)).toList();
    }

    @Override
    public SequencedSet<Letter> getLetters()
    {
        return new LinkedHashSet<>(Arrays.asList(FrenchLetter.values()));
    }
}
