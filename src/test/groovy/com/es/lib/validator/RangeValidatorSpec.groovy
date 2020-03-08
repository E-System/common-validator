package com.es.lib.validator

import com.es.lib.validator.RangeValidator
import com.es.lib.validator.annotaion.Range
import spock.lang.Shared
import spock.lang.Specification

import javax.validation.Payload
import java.lang.annotation.Annotation

class RangeValidatorSpec extends Specification {

    @Shared
    def EMPTY = new Range() {

        @Override
        String value() {
            return ""
        }

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
    }
    @Shared
    def VALID = new Range() {

        @Override
        String value() {
            return "[123;124]"
        }

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
    }

    def "Initialize"() {
        setup:
        def validator = new RangeValidator()
        expect:
        validator.initialize(EMPTY)
    }

    def "Skip validation when value is null"() {
        setup:
        def validator = new RangeValidator()
        validator.initialize(EMPTY)
        when:
        def res = validator.isValid(value as String, null)
        then:
        res
        where:
        value << [null]
    }

    def "Success"() {
        setup:
        def validator = new RangeValidator()
        validator.initialize(VALID)
        when:
        def res = validator.isValid(value as String, null)
        then:
        res
        where:
        value << ["123", "124"]
    }

    def "Invalid"() {
        setup:
        def validator = new RangeValidator()
        validator.initialize(VALID)
        when:
        def res = validator.isValid(value as String, null)
        then:
        !res
        where:
        value << ["117", "300"]
    }
}
