/*
 * Copyright (c) Extended System - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by Extended System team (https://ext-system.com), 2015
 */

package com.es.lib.validator.kpp

import spock.lang.Specification

/**
 * @author Zuzoev Dmitry - zuzoev.d@ext-system.com
 * @since 25.04.15
 */
class KPPValidatorSpec extends Specification {

    def "Skip validation when value is null"() {
        setup:
        def validator = new KPPValidator();
        when:
        def res = validator.isValid(value as String, null)
        then:
        res
        where:
        value << [null]
    }

    def "Exception when invalid values"() {
        setup:
        def validator = new KPPValidator();
        when:
        def res = validator.isValid(value as String, null)
        then:
        !res
        where:
        value << ["", "1", "12345678", "12345678910", "AAAA12123", "AAAA12BBB", "AAAAAAAAA", "фывапролд", "1111az122"]
    }


    def "Success when valid values"() {
        setup:
        def validator = new KPPValidator();
        when:
        def res = validator.isValid(value as String, null)
        then:
        res
        where:
        value << ["1111AZ122", "111111122"]
    }
}
