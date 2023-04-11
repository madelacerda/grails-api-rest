package api.rest

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
