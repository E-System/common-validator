package com.es.lib.validator

import com.es.lib.validator.annotaion.Bik
import spock.lang.Specification

import javax.validation.Payload
import java.lang.annotation.Annotation

class BikValidatorSpec extends Specification {

    def "Initialize"() {
        setup:
        def validator = new BikValidator()
        expect:
        validator.initialize(new Bik() {

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
        def validator = new BikValidator()
        when:
        def res = validator.isValid(value as String, null)
        then:
        res
        where:
        value << [null]
    }

    def "Exception when invalid values"() {
        setup:
        def validator = new BikValidator()
        when:
        def res = validator.isValid(value, null)
        then:
        !res
        where:
        value << ["", "1", "12345678", "12345678910", "AAAA12123", "AAAA12BBB", "AAAAAAAAA", "фывапролд", "1111az122", "1й1111122", "041212049"]
    }

    def "Success when valid values"() {
        setup:
        def validator = new BikValidator()
        when:
        def res = validator.isValid(value, null)
        then:
        res
        where:
        value << ["043456789", "041212050"]
    }
}
