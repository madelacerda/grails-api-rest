package api.rest

import grails.rest.*

@Resource(uri='/products')
class Product {

    String name
    String sku
    Float price

    static constraints = {
        name(nullable: false)
        sku(nullable: false)
        price(nullable: false)
    }
}
