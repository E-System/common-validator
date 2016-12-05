/*
 * Copyright (c) Extended System - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by Extended System team (https://ext-system.com), 2015
 */

package com.es.lib.validator.snils

import spock.lang.Specification

/**
 * @author Zuzoev Dmitry - zuzoev.d@ext-system.com
 * @since 25.04.15
 */
class SNILSValidatorSpec extends Specification {

    def "Skip validation when value is null"() {
        setup:
        def validator = new SNILSValidator();
        when:
        def res = validator.isValid(value as String, null)
        then:
        res
        where:
        value << [null]
    }

    def "Exception when invalid values"() {
        setup:
        def validator = new SNILSValidator();
        when:
        def res = validator.isValid(value as String, null)
        then:
        !res
        where:
        value << ["", "1", "12", "123456789", "1234567890", "1234567890123", "11223344596", "08765430301", "08265430200", "08765430311"]
    }


    def "Success when valid values"() {
        setup:
        def validator = new SNILSValidator();
        when:
        def res = validator.isValid(value as String, null)
        then:
        res
        where:
        value << ["08765430300", "08765430200", "08765430300", "08675430300", "11223344595"]
    }
}
