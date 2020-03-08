/*
 * Copyright (c) E-System - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by E-System team (https://ext-system.com), 2015
 */

package com.es.lib.validator

import com.es.lib.validator.annotaion.Kpp
import spock.lang.Specification

import javax.validation.Payload
import java.lang.annotation.Annotation

/**
 * @author Zuzoev Dmitry - zuzoev.d@ext-system.com
 * @since 25.04.15
 */
class KppValidatorSpec extends Specification {

    def "Initialize"() {
        setup:
        def validator = new KppValidator()
        expect:
        validator.initialize(new Kpp(){

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
        def validator = new KppValidator()
        when:
        def res = validator.isValid(value as String, null)
        then:
        res
        where:
        value << [null]
    }

    def "Exception when invalid values"() {
        setup:
        def validator = new KppValidator()
        when:
        def res = validator.isValid(value as String, null)
        then:
        !res
        where:
        value << ["", "1", "12345678", "12345678910", "AAAA12123", "AAAA12BBB", "AAAAAAAAA", "фывапролд", "1111az122", "qwe", "1й1111122"]
    }


    def "Success when valid values"() {
        setup:
        def validator = new KppValidator()
        when:
        def res = validator.isValid(value as String, null)
        then:
        res
        where:
        value << ["1111AZ122", "111111122"]
    }
}
