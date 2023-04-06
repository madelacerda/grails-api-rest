package api.rest

import grails.rest.*

@Resource(uri='/customers')
class Customer {

    String firstName
    String lastName
    String email
    Float phone

    static constraints = {
        email(nullable: false, email: true)
        firstName( nullable:true)
        lastName( nullable: true)
        phone(nullable: true)
    }
}
