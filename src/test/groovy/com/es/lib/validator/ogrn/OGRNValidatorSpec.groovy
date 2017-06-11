/*
 * Copyright (c) E-System - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by E-System team (https://ext-system.com), 2015
 */

package com.es.lib.validator.ogrn

import spock.lang.Specification

/**
 * @author Zuzoev Dmitry - zuzoev.d@ext-system.com
 * @since 25.04.15
 */
class OGRNValidatorSpec extends Specification {

    def "Skip validation when value is null"() {
        setup:
        def validator = new OGRNValidator();
        when:
        def res = validator.isValid(value as String, null)
        then:
        res
        where:
        value << [null]
    }

    def "Exception when invalid values"() {
        setup:
        def validator = new OGRNValidator();
        when:
        def res = validator.isValid(value as String, null)
        then:
        !res
        where:
        value << ["", "1", "12345678", "123456789101", "12345678901234", "1234567890123456", "5077746887311", "1077746887312", "qwe", "3й4500116000221"]
    }


    def "Success when valid values"() {
        setup:
        def validator = new OGRNValidator();
        when:
        def res = validator.isValid(value as String, null)
        then:
        res
        where:
        value << ["5077746887312", "304500116000221"]
    }
}
