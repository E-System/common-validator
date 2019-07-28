/*
 * Copyright (c) E-System - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Written by E-System team (https://ext-system.com), 2015
 */

package com.es.lib.validator.ogrn


import spock.lang.Specification

import javax.validation.Payload
import java.lang.annotation.Annotation

/**
 * @author Zuzoev Dmitry - zuzoev.d@ext-system.com
 * @since 25.04.15
 */
class OGRNIPValidatorSpec extends Specification {

    def "Initialize"() {
        setup:
        def validator = new OGRNIPValidator()
        expect:
        validator.initialize(new OGRNIP(){
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
        def validator = new OGRNIPValidator()
        when:
        def res = validator.isValid(value as String, null)
        then:
        res
        where:
        value << [null]
    }

    def "Exception when invalid values"() {
        setup:
        def validator = new OGRNIPValidator()
        when:
        def res = validator.isValid(value as String, null)
        then:
        !res
        where:
        value << ["", "1", "12345678", "123456789101", "12345678901234", "1234567890123456", "5077746887311", "1077746887312", "qwe", "3й4500116000221"]
    }


    def "Success when valid values"() {
        setup:
        def validator = new OGRNIPValidator()
        when:
        def res = validator.isValid(value as String, null)
        then:
        res
        where:
        value << ["313132804400022", "304500116000157"]
    }
}
