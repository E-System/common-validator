/*
 * Copyright (c) E-System - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by E-System team (https://ext-system.com), 2015
 */

package com.es.lib.validator.inn

import spock.lang.Specification

import javax.validation.Payload
import java.lang.annotation.Annotation

/**
 * @author Zuzoev Dmitry - zuzoev.d@ext-system.com
 * @since 25.04.15
 */
class INNValidatorSpec extends Specification {

    def "Initialize"() {
        setup:
        def validator = new INNValidator()
        expect:
        validator.initialize(new INN(){

            @Override
            String message() {
                return null
            }

            @Override
            Class<?>[] groups() {
                return new Class[0]
            }

            @Override
            Class<? extends Payload>[] payload() {
                return new Class[0]
            }

            @Override
            Class<? extends Annotation> annotationType() {
                return null
            }
        })
    }

    def "Skip validation when value is null"() {
        setup:
        def validator = new INNValidator()
        when:
        def res = validator.isValid(value as String, null)
        then:
        res
        where:
        value << [null]
    }

    def "Exception when invalid values"() {
        setup:
        def validator = new INNValidator()
        when:
        def res = validator.isValid(value, null)
        then:
        !res
        where:
        value << ["", "1", "12", "123456789", "12345678901", "1234567890123", "1234567890", "1111111111", "123456789012", "111111111111", "qwe", "7й30002293"]
    }


    def "Success when valid values"() {
        setup:
        def validator = new INNValidator()
        when:
        def res = validator.isValid(value, null)
        then:
        res
        where:
        value << ["500100732259", "7830002293"]
    }
}
