/*
 * Copyright 2016 E-System LLC
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.es.lib.validator;

import com.es.lib.common.validation.Validators;
import com.es.lib.validator.annotaion.Range;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author Dmitriy Zuzoev - zuzoev.d@ext-system.com
 * @since 25.07.16
 */
public class RangeValidator implements ConstraintValidator<Range, String> {

    private String range;

    @Override
    public void initialize(Range inn) {
        range = inn.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Validators.RANGE.isValid(value, range);
    }
}
