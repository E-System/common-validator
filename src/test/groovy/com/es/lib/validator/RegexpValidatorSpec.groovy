package com.es.lib.validator


import com.es.lib.validator.annotaion.Regexp
import spock.lang.Shared
import spock.lang.Specification

import javax.validation.Payload
import java.lang.annotation.Annotation

class RegexpValidatorSpec extends Specification {

    @Shared
    def EMPTY = new Regexp() {

        @Override
        String value() {
            return null
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
    def VALID = new Regexp() {

        @Override
        String value() {
            return '\\d*'
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
    def INAVLID = new Regexp() {

        @Override
        String value() {
            return '\\d'
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
        def validator = new RegexpValidator()
        expect:
        validator.initialize(EMPTY)
    }

    def "Skip validation when value is null"() {
        setup:
        def validator = new RegexpValidator()
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
        def validator = new RegexpValidator()
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
        def validator = new RegexpValidator()
        validator.initialize(INAVLID)
        when:
        def res = validator.isValid(value as String, null)
        then:
        !res
        where:
        value << ["Hello", "Hello asdasd"]
    }
}
