/*
 * Copyright (c) E-System - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by E-System team (https://ext-system.com), 2015
 */

package com.es.lib.validator.snils


import spock.lang.Specification

import javax.validation.Payload
import java.lang.annotation.Annotation

/**
 * @author Zuzoev Dmitry - zuzoev.d@ext-system.com
 * @since 25.04.15
 */
class SNILSValidatorSpec extends Specification {

    def "Initialize"() {
        setup:
        def validator = new SNILSValidator()
        expect:
        validator.initialize(new SNILS(){

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
        def validator = new SNILSValidator()
        when:
        def res = validator.isValid(value as String, null)
        then:
        res
        where:
        value << [null]
    }

    def "Exception when invalid values"() {
        setup:
        def validator = new SNILSValidator()
        when:
        def res = validator.isValid(value as String, null)
        then:
        !res
        where:
        value << ["", "1", "12", "123456789", "1234567890", "1234567890123", "11223344596", "08765430301", "08265430200", "08765430311", "qwe", "1й223344595"]
    }


    def "Success when valid values"() {
        setup:
        def validator = new SNILSValidator()
        when:
        def res = validator.isValid(value as String, null)
        then:
        res
        where:
        value << ["08765430300", "08765430200", "08765430300", "08675430300", "11223344595"]
    }
}
